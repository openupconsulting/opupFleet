package org.openup.fleet.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.openup.fleet.model.MUYTire;
import org.openup.fleet.model.MUYTireMove;
import org.openup.fleet.model.MUYTireMoveLog;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.stream.Stream;

/**
 * Created by sylvie on 23/12/16.
 */
public class ReplaceTireLocationEx extends ReplaceTireLocationExAbstract {

    /**	Create From Type	*/
    private static final String Vehicle = "V";
    private static final String Location = "L";
    private String tireAction = "";

    private int tireLocatorID_From = 0;
    private int tireLocatorID_To = 0;

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


        //super.prepare();
    }


    @Override
    protected String doIt() throws Exception {

        // Valid Record Identifier
        if(getRecord_ID() == 0)
            return "";

        if(tireAction.equals("exchange")){
            return exchangeTires();
        }else {
            return "Not implemented";
            //return placeSetFreeTires();
        }
    }


    //Metodos para intercambio de neumaticos entre vehiculos
    private String exchangeTires(){
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

        for( int i : recordIds2) {
            int key = i;


            //    }


            //     recordIds2.stream().forEach( key -> {
            // variable values

            int tireId = getSelectionAsInt(key, "T_UY_Tire_ID"); //Neumatico que voy a colocar en el vehiculo del cabezal
            MUYTire neumatico = new MUYTire(getCtx(), key, get_TrxName());
            //Verificar que el neumatico no este en la lista de acciones
            if (!isTireSlected(neumatico, move.get_ID())) {

                int mTireLocatorId_To = getSelectionAsInt(key, "T_UY_TireLocator_ID_To");//A que posicion

                //Verificamos si la posicion del vehiculo ya fue modificada
                if (!isTireLocatorUse(mTireLocatorId_To)) {

                    log.fine("Neumatico: " + neumatico.getValue());
                    int mVehicleId_From = getSelectionAsInt(key, "T_UY_Vehicle_ID"); //Obtengo de que vehiculo viene el neumatico
                    int mTireLocatorID_From = getSelectionAsInt(key, "T_UY_TireLocator_ID");//obtengo la posicion del neumatico

                    MUYTireMoveLog logAction = new MUYTireMoveLog(getCtx(), 0, get_TrxName());
                    // MUYTireMoveLog logAction = logAux;
                    logAction.setUY_Tire_ID(neumatico.get_ID());//Se indica neumatico
                    logAction.setTireAction(tireAction); // Se indica accion sobre el neumatico
                    logAction.setUY_TireMove_ID(move.get_ID());//Se indica id de recambio

                    logAction.setUY_Vehicle_ID_From(mVehicleId_From);
                    logAction.setUY_TireLocator_ID_From(mTireLocatorID_From);

                    logAction.setUY_Vehicle_ID_To(move.getUY_Vehicle_ID());//Vehiculo Origen el del cabezal
                    logAction.setUY_TireLocator_ID_To(mTireLocatorId_To);

                    logAction.saveEx();

                    int neumaticoAux = DB.getSQLValue(get_TrxName(), "SELECT UY_Tire_ID FROM UY_TireMoveLine WHERE UY_TireMove_ID = " +
                            move.get_ID() + " AND UY_TireLocator_ID = " + mTireLocatorId_To);
                    if (neumaticoAux > 0) {
                        //Marco neumatico como modificado
                        setTireModified(true, neumatico.getUY_Tire_ID());
                    }


                }
            }
        }
   //     });

        return "OK";
    }
    private void setTireModified(boolean inAction, int tireID){
        String accion = "N";
        try{
            if(inAction)
                accion = "Y";
            DB.executeUpdate(get_TrxName(),"UPDATE UY_TireMoveLine SET IsModified = '"+accion +
                    "' WHERE UY_TireMove_ID = "+move.get_ID()+ " AND UY_Tire_ID = "+tireID);
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }

    }
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

    private boolean isTireLocatorUse(int tireLocatorID_To){
        try{
            int cant = DB.getSQLValue(get_TrxName(),"SELECT COUNT(UY_Tire_ID) FROM UY_TireMoveLog WHERE " +
                    "UY_TireMove_ID = "+move.get_ID()+" AND (UY_TireLocator_ID_To = "+
                    tireLocatorID_To +" AND UY_Vehicle_ID_To = "+move.getUY_Vehicle_ID()+ ") OR " +
                    "(UY_TireLocator_ID_From = "+tireLocatorID_To+" AND  UY_Vehicle_ID_From = "+move.getUY_Vehicle_ID()+")" );
            if(cant>0){
                return true;
            }
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }
        return  false;
    }
}
