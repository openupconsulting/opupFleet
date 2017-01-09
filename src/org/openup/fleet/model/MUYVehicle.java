package org.openup.fleet.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by sylvie on 14/12/16.
 */
public class MUYVehicle extends X_UY_Vehicle{

    public MUYVehicle(Properties ctx, int UY_Vehicle_ID, String trxName) {
        super(ctx, UY_Vehicle_ID, trxName);
    }

    public MUYVehicle(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    /**
     * Verifica si el vehiculo tiene un neumatico asociado para la posicion recibida
     * @param ctx
     * @param tireLocatorID
     * @param trxName
     * @return
     */
    public  boolean isPositionFree(Properties ctx,int tireLocatorID,String trxName){
        try{
            int count = DB.getSQLValue(trxName,"select count(uy_tire_id) from uy_tire where uy_vehicle_id = "+
                    this.get_ID()+" AND UY_TireLocator_ID = "+tireLocatorID);
            if(count>0)
                return false;
            else
                return true;
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }
    }
}
