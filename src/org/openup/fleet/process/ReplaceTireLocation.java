package org.openup.fleet.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.openup.fleet.model.MUYTire;
import org.openup.fleet.model.MUYTireMove;
import org.openup.fleet.model.MUYTireMoveLine;
import org.openup.fleet.model.MUYTireMoveLog;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

/**
 * Created by sylvie on 05/12/16.
 */
public class ReplaceTireLocation extends ReplaceTireLocationAbstract{

    /**	Create From Type	*/
    private static final String Vehicle = "V";
    private static final String Location = "L";
    private String tireAction = "";

    private int tireLocatorID_From = 0;
    private int tireLocatorID_To = 0;

    private MUYTireMoveLog logAux = null;

    private MUYTireMove move = null;
    @Override
    protected void prepare() {
        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++)
        {
            String name = para[i].getParameterName();
            if (para[i].getParameter() == null)
                ;
            else if (name.equals("TireAction"))
               tireAction = para[i].getParameterAsString();
            else if (name.equals("UY_TireLocator_ID_From"))
                tireLocatorID_From = para[i].getParameterAsInt();
            else if (name.equals("UY_TireLocator_ID_To"))
                tireLocatorID_To =  para[i].getParameterAsInt();
            else
                log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
        }

        if(tireAction.equals("rotate")){
            if(tireLocatorID_From == tireLocatorID_To)
                throw new AdempiereException("No se puede rotar sobre la misma posicion.");
        }
        //super.prepare();
    }

    @Override
    protected String doIt() throws Exception {

        logAux = new MUYTireMoveLog(getCtx(),0,get_TrxName());
        // Valid Record Identifier
        if(getRecord_ID() == 0)
            return "";

        //Si la accion es rotar (rotacion entre neumaticos internamente)
        if(tireAction.equals("rotate")){
            return internalRotation();
        /*}else if(tireAction.equals("exchange")){
            return exchangeTires();*/
        }else {
            return placeSetFreeTires();
        }

    }

    //Metodos para colocar neumaticos en vehiculo o liberar del vehiculo

    private String placeSetFreeTires(){

        move = new MUYTireMove(this.getCtx(),getRecord_ID(),this.get_TrxName());

        AtomicInteger referenceId = new AtomicInteger(0);
        AtomicInteger 	created = new AtomicInteger(0);
        List<Integer> recordIds =  getSelectionKeys();
        //Accion Colocar desde Ubicacion, Liberar de vehiculo a Ubicacion
        //String createFromType = recordIds.size() > 0 ?  getSelectionAsString(recordIds.get(0), "A_TireAction") : null;
        log.fine("CreateFromType=" + recordIds.size());
        //if (createFromType == null || createFromType.length() == 0)
        //throw new AdempiereException("@CreateFromType@ @NotFound@");
        if(tireAction == null ){
            throw  new AdempiereException("Debe indicar accion");
        }

        //	Loop
        recordIds.stream().forEach( key -> {
            // variable values

            int tireId = getSelectionAsInt(key, "A_UY_Tire_ID"); //Neumatico
            MUYTire neumatico = new MUYTire(getCtx(),key,get_TrxName());
            //Verificar que el neumatico no este en la lista de acciones
            if(!isTireSlected(neumatico,move.get_ID())){

                log.fine("Neumatico: "+neumatico.getValue());
                int vehicleId = move.getUY_Vehicle_ID();//Vehiculo
                int mLocatorId = getSelectionAsInt(key, "A_M_Locator_ID"); //Obtengo Ubicacion (Desde donde se obtiene o donde se libera)
                int tireLocatorId = 0;

                MUYTireMoveLog logAction = new MUYTireMoveLog(getCtx(),0,get_TrxName());
                logAction.setUY_Tire_ID(neumatico.get_ID());//Se indica neumatico
                logAction.setTireAction(tireAction); // Se indica accion sobre el neumatico
                logAction.setUY_TireMove_ID(move.get_ID());//Se indica id de recambio

                if(this.tireAction.equalsIgnoreCase("place")){//Colocar neumatico en vehiculo
                    //Tengo que indicar en que posicion del vehiculo lo coloco (lo tiene que indicar el usuario)
                    tireLocatorId = getSelectionAsInt(key, "A_UY_TireLocator_ID");// Posicion en el vehiculo
                    if(tireLocatorId == 0){
                        throw  new AdempiereException("Se tiene que indicar posicion donde colocar el neumatico: "+neumatico.getValue());
                    }
                    logAction.setUY_TireLocator_ID_To(tireLocatorId); //_TO

                    //Indico ubicacion desde donde obtengo el neumatico
                    logAction.setM_Locator_ID(neumatico.getM_Locator_ID());
                    //Indico a que vehiculo se le coloca el neumatico (_TO)
                    logAction.setUY_Vehicle_ID_To(vehicleId);

                    //Se verifica que la posicion no este rotada ni intercambiada ni colocada si puede estar liberada
                    if(isTireLocatorRotated(logAction.getUY_TireLocator_ID_To()) ||
                            isTireLocatorExchanged(logAction.getUY_TireLocator_ID_To()) ||
                            isTireLocatorPlaced(logAction.getUY_TireLocator_ID_To()) ){
                        int auxpos = DB.getSQLValue(get_TrxName(),"Select value from uy_tirelocator " +
                                "where uy_tirelocator_id = "+logAction.getUY_TireLocator_ID_To());
                   // if(!isTireLocatorUse(logAction.getUY_TireLocator_ID_To())){
                    //if(!verifyPositionFree(logAction.getUY_TireMove_ID(),logAction.getUY_TireLocator_ID_To(),logAction.getUY_Vehicle_ID_To())){
                        throw new AdempiereException("El neumatico "+neumatico.getValue()+",no se puede colocar en la  posición : "+auxpos+
                                ", ya que hay una acción aplicada en dicha posición");
                    }

                }else if(this.tireAction.equalsIgnoreCase("setfree")) {//Liberar neumatico de vehiculo
                    //Tengo que indicar a que ubicacion lo libero obligatoriamente
                    if (mLocatorId == 0) {
                        throw new AdempiereException("Se tiene que indicar ubicacion donde liberar el neumatico: " + neumatico.getValue());
                    }
                    if(isTireLocatorRotated(mLocatorId) || isTireLocatorExchanged(mLocatorId)){
                        throw new AdempiereException("El neumatico "+neumatico.getValue()+", no se puede liberar " +
                                "ya que hay una acción aplicada a dicho neumático");
                    }
                    logAction.setM_Locator_ID(mLocatorId);
                    //Indico el vehiculo desde donde se va a liberar (_FROM)
                    logAction.setUY_Vehicle_ID_From(vehicleId);
                    //Indico posicion donde se encuentra en el vehiculo (_FROM)
                    logAction.setUY_TireLocator_ID_From(neumatico.getUY_TireLocator_ID()); //Posicion que tiene actualmente el neumatico
                    //Marco neumatico como modificado
                    setTireModified(true,neumatico.get_ID());
                }

                logAction.saveEx();
            }

        });

        return "OK";
    }

    /**
     * Se verifica si el neumatico esta como seleccionado en la lista de neumaticos del vehiculo cargados anteriormente
     * @param neumatico
     * @param tireMoveID
     * @return true si se encuentran registros marcados para la rotacion en curso y el neumatico recibido
     */
    private boolean isTireSlected(MUYTire neumatico, int tireMoveID){
        try{
            int cant = DB.getSQLValue(get_TrxName(),"SELECT COUNT(UY_Tire_ID) FROM UY_TireMoveLog WHERE UY_Tire_ID = "+
                    neumatico.get_ID()+ " AND UY_TireMove_ID = "+tireMoveID);
            if(cant>0){
                return true;
            }
        }catch (Exception e){
            throw new AdempiereException("El neumático "+neumatico.getValue()+" ya esta seleccionado para ser rotado");
        }

        return false;
    }

    //Verifica si la posición recibida esta modificada (esta presente en las acciones ya realizadas)
    private boolean isTireLocatorUse(int tireLocatorID_To, String action){
        try{
            //Consulta si la posicion esta en los valores desde
            String sqlTo = "SELECt COUNT(*) FROM UY_TireMoveLog WHERE UY_TireMove_ID = "+move.get_ID()+
                    " AND UY_TireLocator_ID_To = "+tireLocatorID_To+" AND UY_Vehicle_ID_To = "+move.getUY_Vehicle_ID();
            //Consulta si la posicion esta en los valores hasta
            String sqlFrom = "SELECt COUNT(*) FROM UY_TireMoveLog WHERE UY_TireMove_ID = "+move.get_ID()+
                    " AND UY_TireLocator_ID_From = "+tireLocatorID_To+" AND  UY_Vehicle_ID_From = "+move.getUY_Vehicle_ID();
            if(!action.isEmpty()){
                sqlFrom += " AND TireAction = '"+action+"'";
                sqlTo += " AND TireAction = '"+action+"'";
            }
            int cant = DB.getSQLValue(get_TrxName(),sqlTo);
            if(cant>0){
                return true;
            }else{
                cant = DB.getSQLValue(get_TrxName(),sqlFrom);
                if(cant>0) return true;
            }
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }
        return  false;
    }

    private boolean isTireLocatorSetFree(int tireLocatorID_To) {
        return isTireLocatorUse(tireLocatorID_To,"setfree");
    }

    private boolean isTireLocatorPlaced(int tireLocatorID_To) {
        return isTireLocatorUse(tireLocatorID_To,"place");
    }

    private boolean isTireLocatorExchanged(int tireLocatorID_To) {
        return isTireLocatorUse(tireLocatorID_To,"exchange");
    }

    private boolean isTireLocatorRotated(int tireLocatorID_To){
        return isTireLocatorUse(tireLocatorID_To,"rotate");
    }

    //Metodos para rotacion interna

    private String internalRotation(){

        if(tireLocatorID_From>0 && tireLocatorID_To>0){

            move = new MUYTireMove(this.getCtx(),getRecord_ID(),this.get_TrxName());

            MUYTireMoveLog logAction = new MUYTireMoveLog(getCtx(),0,get_TrxName());
            logAction.setUY_TireMove_ID(move.get_ID());
            logAction.setTireAction(this.tireAction);

            logAction.setUY_TireLocator_ID_From(tireLocatorID_From);
            int tireIDFrom = DB.getSQLValue(get_TrxName(),
                    "SELECT UY_Tire_ID FROM UY_TireMoveLine WHERE UY_TireMove_ID = "+move.get_ID()+
                        " AND UY_TireLocator_ID = "+tireLocatorID_From );
            logAction.setUY_Tire_ID(tireIDFrom);

            int tireIDTo = DB.getSQLValue(get_TrxName(),
                    "SELECT UY_Tire_ID FROM UY_TireMoveLine WHERE UY_TireMove_ID = "+move.get_ID()+
                            " AND UY_TireLocator_ID = "+tireLocatorID_To );
            logAction.setUY_TireLocator_ID_To(tireLocatorID_To);
            logAction.setUY_Tire_ID_To(tireIDTo);

            logAction.setUY_Vehicle_ID_To(move.getUY_Vehicle_ID());
            logAction.setUY_Vehicle_ID_From(move.getUY_Vehicle_ID());

            logAction.saveEx();

            //Marco neumaticos como modificados
            MUYTireMoveLine.setTireModified(true,tireIDFrom,move.get_ID(),get_TrxName());
            MUYTireMoveLine.setTireModified(true,tireIDTo,move.get_ID(),get_TrxName());
        }
        return "Ok";
    }

    private void setTireModified(boolean inAction, int tireID){
        String accion = "N";
        try{
            if(inAction)
                accion = "Y";

            int count = DB.getSQLValue(get_TrxName(),"UPDATE UY_TireMoveLine SET IsModified = '"+accion +
                    "' WHERE UY_TireMove_ID = "+move.get_ID()+ " AND UY_Tire_ID = "+tireID);
            if(count>0)
                System.out.println("Se modificó");
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }

    }
    //Fin metosod para rotacion interna

    //Metodos para intercambio de neumaticos entre vehiculos
    /*private String exchangeTires(){
        //Permito intercambiar neumaticos si los mismos ya no se encuentran en una accion.
        move = new MUYTireMove(this.getCtx(),getRecord_ID(),this.get_TrxName());

        AtomicInteger referenceId = new AtomicInteger(0);
        AtomicInteger 	created = new AtomicInteger(0);
        List<Integer> recordIds2 =  getSelectionKeys();
        //Accion Colocar desde Ubicacion, Liberar de vehiculo a Ubicacion
        //String createFromType = recordIds.size() > 0 ?  getSelectionAsString(recordIds.get(0), "A_TireAction") : null;
        log.fine("CreateFromType=" + recordIds2.size());
        //if (createFromType == null || createFromType.length() == 0)
        //throw new AdempiereException("@CreateFromType@ @NotFound@");
        if(tireAction == null ){
            throw  new AdempiereException("Debe indicar accion");
        }
        if(!this.tireAction.equalsIgnoreCase("exchange")) throw new AdempiereException("No se permite otra accion que no sea intercambio de neumaticos");
        //	Loop
        recordIds2.stream().forEach( key -> {
            // variable values

            int tireId = getSelectionAsInt(key, "T_UY_Tire_ID"); //Neumatico que voy a colocar en el vehiculo del cabezal
            MUYTire neumatico = new MUYTire(getCtx(),key,get_TrxName());
            //Verificar que el neumatico no este en la lista de acciones
            if(!isTireSlected(neumatico,move.get_ID())){

                int mTireLocatorId_To = getSelectionAsInt(key,"T_UY_TireLocator_ID_To");//A que posicion

                //Verificamos si la posicion del vehiculo ya fue modificada para otra accion
                if(!isTireLocatorUse(mTireLocatorId_To,"")){

                    log.fine("Neumatico: "+neumatico.getValue());
                    int mVehicleId_From = getSelectionAsInt(key, "T_UY_Vehicle_ID"); //Obtengo de que vehiculo viene el neumatico
                    int mTireLocatorID_From = getSelectionAsInt(key,"T_UY_TireLocator_ID");//obtengo la posicion del neumatico

                   // MUYTireMoveLog logAction = new MUYTireMoveLog(getCtx(),0,get_TrxName());
                   // MUYTireMoveLog logAction = logAux;
                    logAux.setUY_Tire_ID(neumatico.get_ID());//Se indica neumatico
                    logAux.setTireAction(tireAction); // Se indica accion sobre el neumatico
                    logAux.setUY_TireMove_ID(move.get_ID());//Se indica id de recambio

                    logAux.setUY_Vehicle_ID_From(mVehicleId_From);
                    logAux.setUY_TireLocator_ID_From(mTireLocatorID_From);

                    logAux.setUY_Vehicle_ID_To(move.getUY_Vehicle_ID());//Vehiculo Origen el del cabezal
                    logAux.setUY_TireLocator_ID_To(mTireLocatorId_To);

                    logAux.saveEx();

                    int neumaticoAux = DB.getSQLValue(get_TrxName(),"SELECT UY_Tire_ID FROM UY_TireMoveLine WHERE UY_TireMove_ID = "+
                                        move.get_ID()+ " AND UY_TireLocator_ID = "+mTireLocatorId_To);
                    if(neumaticoAux>0){
                        //Marco neumatico como modificado
                        setTireModified(true,neumatico.get_ID());
                    }


                }
            }

        });

        return "OK";
    }*/
}
