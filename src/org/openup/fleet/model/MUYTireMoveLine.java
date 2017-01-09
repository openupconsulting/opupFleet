package org.openup.fleet.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by sylvie on 05/12/16.
 */
public class MUYTireMoveLine extends X_UY_TireMoveLine {

    public MUYTireMoveLine(Properties ctx, int UY_TireMoveLine_ID, String trxName) {
        super(ctx, UY_TireMoveLine_ID, trxName);
    }

    public MUYTireMoveLine(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    public static void setTireModified(boolean inAction, int tireID , int tireMoveID, String trxName){
        String accion = "N";
        try{
            if(inAction)
                accion = "Y";
            int count =  DB.executeUpdate("UPDATE UY_TireMoveLine SET IsModified = '"+accion +
                    "' WHERE UY_TireMove_ID = "+tireMoveID+ " AND UY_Tire_ID = "+tireID,trxName);
            if(count>0){
                System.out.println("ALGO");
            }
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }

    }
}
