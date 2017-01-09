package org.openup.fleet.model;

import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by sylvie on 05/12/16.
 */
public class MUYTireMove extends X_UY_TireMove implements DocAction, DocOptions {

    /**	Process Message 			*/
    private String		m_processMsg = null;
    /**	Just Prepared Flag			*/
    private boolean		m_justPrepared = false;

    public MUYTireMove(Properties ctx, int UY_TireMove_ID, String trxName) {
        super(ctx, UY_TireMove_ID, trxName);
    }

    public MUYTireMove(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public void setM_processMsg(String m_processMsg) {
        this.m_processMsg = m_processMsg;
    }

    @Override
    public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx, int AD_Table_ID, String[] docAction, String[] options, int index) {

        int newIndex = 0;

        if ((docStatus.equalsIgnoreCase(STATUS_Drafted))
                || (docStatus.equalsIgnoreCase(STATUS_Invalid))
                || (docStatus.equalsIgnoreCase(STATUS_InProgress))){

            options[newIndex++] = DocumentEngine.ACTION_Complete;

        }
        else if (docStatus.equalsIgnoreCase(STATUS_Completed)){
            //options[newIndex++] = DocumentEngine.ACTION_ReActivate; No esta implementado en MMovement SBT 24-11-16
            //  options[newIndex++] = DocumentEngine.ACTION_Void;
        }

        return newIndex;

    }
    @Override
    public boolean processIt(String action) throws Exception {
        m_processMsg = null;
        DocumentEngine engine = new DocumentEngine (this, getDocStatus());
        return engine.processIt (action, getDocAction());
    }
    @Override
    public boolean unlockIt() {
        log.info("unlockIt - " + toString());
        setProcessing(false);
        return true;
    }

    @Override
    public boolean invalidateIt() {
        log.info(toString());
        setDocAction(DOCACTION_Prepare);
        return true;
    }

    @Override
    public String prepareIt() {
        // Todo bien
        m_justPrepared = true;
        if (!DocAction.ACTION_Complete.equals(getDocAction()))
            setDocAction(DocAction.ACTION_Complete);
        return DocAction.STATUS_InProgress;
    }

    @Override
    public boolean approveIt() {
        return false;
    }

    @Override
    public boolean rejectIt() {
        return false;
    }

    @Override
    public String completeIt() {
        //	Re-Check
        if (!m_justPrepared)
        {
            String status = prepareIt();
            if (!DocAction.STATUS_InProgress.equals(status))
                return status;
        }

        if (this.isProcessed()){
            return DocAction.STATUS_Completed;
        }

        // Timing Before Complete
        m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
        if (m_processMsg != null)
            return DocAction.STATUS_Invalid;

        // Preparo y completo modelo adempiere
        MUYTireMove model = new MUYTireMove(getCtx(),this.get_ID(),get_TrxName());

        //Obtengo lienas de accion ordenadas por orden de accion (setfree-place-rotate-exchange)
        List<MUYTireMoveLog> logs = MUYTireMoveLog.getLogsOfTireMove(getCtx(),this.get_ID(),get_TrxName());
        if(null!=logs && logs.size()>0){
            for(MUYTireMoveLog mlog : logs){
                if(!applyActionToTire(mlog)) {//Aplico la o las acciones que corresponde
                    return DocAction.STATUS_Drafted;
                }
            }
        }else {
            m_processMsg = "No existen lineas de accion para procesar";
            return DocAction.STATUS_Invalid;
        }

        // Refresco atributos
        this.setDocAction(DocAction.ACTION_Close);
        this.setDocStatus(DocumentEngine.STATUS_Completed);
        this.setProcessing(false);
        this.setProcessed(true);

        return DocAction.STATUS_Completed;
    }

    //Verifico si el neumatico que estoy colocando se encuentra en la ubicacion inicial
    private boolean verifyStateTireToPlace(MUYTireMoveLog mlog){
        String sql = "SELECT count(*) FROM UY_Tire WHERE UY_Tire_ID = "
                +mlog.getUY_Tire_ID()+" AND M_Locator_ID = "+mlog.getM_Locator_ID();

        int mOk = DB.getSQLValue(get_TrxName(),sql);

        if(mOk>0)
            return true;
        else return  false;
    }

    //Verifico si el neumatico se encuentra asociado al mismo vehiculo y en la misma posicion
    private boolean verifyStateTire(int tireID, int vehicleID,int tireLocatorID ){

        String sql = "SELECT count(*) FROM UY_Tire WHERE UY_Tire_ID = "+tireID+
                " AND UY_Vehicle_ID = "+vehicleID +" AND UY_TireLocator_ID ="+tireLocatorID;
        int mOk = DB.getSQLValue(get_TrxName(),sql);
        if(mOk>0)
            return true;
        else return  false;
    }

    private boolean applyActionToTire(MUYTireMoveLog log){
        //Neumatico
        MUYTire tire = (MUYTire) log.getUY_Tire();

        //Realizar la modificacion para liberar neumatico del vehiculo
        if(log.getTireAction().equalsIgnoreCase("setfree")){

            //Verifico si el neumatico se encuentra asociado al vehiculo y en la posicion inicial
            if(!verifyStateTire(log.getUY_Tire_ID(),log.getUY_Vehicle_ID_From(),
                    log.getUY_TireLocator_ID_From())){
                this.setM_processMsg("No se puede liberar neumatico ya que sufrio cambios");
                return false;
            }
            //Se debe liberar el neumatico y colocarlo en la ubicacion que se indica en la accion
            tire.setUY_Vehicle_ID(0);
            tire.setUY_TireLocator_ID(0);
            int wID = DB.getSQLValue(null,"SELECT M_Warehouse_ID from M_Locator WHERE " +
                    "M_Locator_ID = "+log.getM_Locator_ID());
            if(log.getM_Locator_ID()>0 && wID>0){
                tire.setM_Locator_ID(log.getM_Locator_ID());
                tire.setM_Warehouse_ID(wID);
            }else {
                this.setM_processMsg("No se puede liberar neumatico sin ubicacion");
                return false;
            }

        }else if(log.getTireAction().equalsIgnoreCase("place")){
            //De debe colocar neumatico en vehiculo asociado al modelo y en la posicion que indica accion

            if(log.getUY_Vehicle_ID_To()>0 && log.getUY_TireLocator_ID_To()>0){

                //Puedo colocar neumatico en el vehiculo si el vehiculo no tiene actualmente un neumatico en dicha posicion
                MUYVehicle mVehicle = (MUYVehicle) this.getUY_Vehicle();
                if(mVehicle==null || mVehicle.get_ID()<=0){
                    this.setM_processMsg("No se puede instanciar el vehiculo");
                    return false;
                }
                if(!mVehicle.isPositionFree(getCtx(),log.getUY_TireLocator_ID_To(),get_TrxName())){
                    this.setM_processMsg("El vehiculo tiene ocupada la posición en la que se desea colocar el neumatico: "
                            +log.getUY_Tire().getValue());
                    return false;
                }
                //Verifico si el neumatico continua en el mismo almacen
                if(!verifyStateTireToPlace(log)){
                    this.setM_processMsg("El neumatico "+log.getUY_Tire().getValue()+" sufrio cambios" +
                            ", verifique accion");
                    return false;
                }

                tire.setUY_Vehicle_ID(log.getUY_Vehicle_ID_To());
                tire.setUY_TireLocator_ID(log.getUY_TireLocator_ID_To());

            }else{
                this.setM_processMsg("No se puede colocar neumatico sin indicar posicion y vehiculos correctos");
                return  false;
            }

            tire.setM_Warehouse_ID(0);
            tire.setM_Locator_ID(0);

        }else if(log.getTireAction().equalsIgnoreCase("rotate")){
            //Se debe rotar la posicion de los neumaticos en el propio vehiculo (queda en el mismo vehiculo)
            //Verifico los datos
            if(log.getUY_TireLocator_ID_To()<=0 || log.getUY_TireLocator_ID_From()<=0){
                this.setM_processMsg("Falta posicion origen o destino para rotar internamente");
                return false;
            }
            //Verifico si el neumatico DESDE se encuentra asociado al vehiculo y en la posicion inicial
            if(!verifyStateTire(log.getUY_Tire_ID(),log.getUY_Vehicle_ID_From(),
                    log.getUY_TireLocator_ID_From())){
                this.setM_processMsg("El neumatico origen ("+log.getUY_Tire().getValue()
                        +") sufio cambios en otra rotacion, verifique accion");
                return false;
            }
            //Verifico si el neumatico HASTA se encuentra asociado al vehiculo y en la posicion inicial
            if(!verifyStateTire(log.getUY_Tire_ID_To(),log.getUY_Vehicle_ID_To(),
                    log.getUY_TireLocator_ID_To())){
                this.setM_processMsg("El neumatico destino  ("+log.getUY_Tire().getValue()
                        +") sufio cambios en otra rotacion, verifique accion.");
                return false;
            }
            /*
            int idTireTo = DB.getSQLValue(null, "SELECT UY_Tire_ID FROM UY_TireMoveLine WHERE UY_TireMove_ID ="+
                log.getUY_TireMove_ID()+" AND UY_TireLocator_ID = "+log.getUY_TireLocator_ID_To());
            */
            //Obtengo neumatico a
            MUYTire tireTo = new MUYTire(getCtx(),log.getUY_Tire_ID_To(),get_TrxName());

            if(tireTo==null || tireTo.get_ID()<=0){
                this.setM_processMsg("No es posible rotar neumaticos ya que no se obtiene neumatico destino");
                return false;
            }

            //Seteo posicion al neumatico origen
            tire.setUY_TireLocator_ID(log.getUY_TireLocator_ID_To());

            //Seteo posicion al neumatico destino
            tireTo.setUY_TireLocator_ID(log.getUY_TireLocator_ID_From());

            //Se asocia al neumatico destino el movimiento y se guardan los cambios
            tireTo.set_ValueOfColumn("UY_TireMove_ID",log.getUY_TireMove_ID());
            tireTo.saveEx();

        }else if(log.getTireAction().equalsIgnoreCase("exchange")){
            //El neumatico del log tiene que venir a la
            //tire.setUY_Vehicle_ID(0);
            this.setM_processMsg("No implementado");
            return  false;
        }
        //Se asocia al neumatico el movimiento y se guardan los cambios
        tire.set_ValueOfColumn("UY_TireMove_ID",log.getUY_TireMove_ID());
        tire.saveEx();
        return true;
    }
    @Override
    public boolean voidIt() {
        return false;
    }

    @Override
    public boolean closeIt() {
        return false;
    }

    @Override
    public boolean reverseCorrectIt() {
        return false;
    }

    @Override
    public boolean reverseAccrualIt() {
        return false;
    }

    @Override
    public boolean reActivateIt() {
        return false;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public String getDocumentInfo() {
        return null;
    }

    @Override
    public File createPDF() {
        return null;
    }

    @Override
    public String getProcessMsg() {
        return this.m_processMsg;
    }

    @Override
    public int getDoc_User_ID() {
        return getCreatedBy();
    }

    @Override
    public int getC_Currency_ID() {
        return 0;
    }

    @Override
    public BigDecimal getApprovalAmt() {
        return null;
    }

    private int [] getTireMoveLogIDs(){
        return MUYTireMoveLog.getAllIDs(X_UY_TireMoveLog.Table_Name," IsActive = 'Y' AND UY_TireMove_ID = "+this.get_ID(),get_TrxName());
    }
}
