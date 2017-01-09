package org.openup.fleet.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.util.DB;
import org.compiere.model.Query;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * Created by sylvie on 12/12/16.
 */
public class MUYTireMoveLog extends X_UY_TireMoveLog {

    public MUYTireMoveLog(Properties ctx, int UY_TireMoveLog_ID, String trxName) {
        super(ctx, UY_TireMoveLog_ID, trxName);
    }

    public MUYTireMoveLog(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    @Override
    protected boolean afterDelete(boolean success) {

        if(success){
            //Si se elimina accion, se debe indicar como no modificado el neumatico
            MUYTireMoveLine.setTireModified(false,this.getUY_Tire_ID(),this.getUY_TireMove_ID(),get_TrxName());
            if(this.getTireAction().equalsIgnoreCase("rotate")){
                int tireAux = DB.getSQLValue(get_TrxName(),"SELECT UY_Tire_ID FROM UY_TireMoveLine WHERE UY_TireMove_ID="
                +this.getUY_TireMove_ID()+ " AND UY_TireLocator_ID = "+this.getUY_TireLocator_ID_To());

                MUYTireMoveLine.setTireModified(false,tireAux,this.getUY_TireMove_ID(),get_TrxName());
            }
        }
        return true;
    }

    public static List<MUYTireMoveLog> getLogsOfTireMove(Properties ctx,int tireMoveID, String trxName){
        String whereClause = " UY_TireMove_ID = "+tireMoveID;

        List<MUYTireMoveLog> models = new Query(ctx, I_UY_TireMoveLog.Table_Name, whereClause, trxName)
                .setOrderBy(X_UY_TireMoveLog.COLUMNNAME_ProcessingOrder)
                .list();

        return models;

    }

    @Override
    protected boolean beforeSave(boolean newRecord) {
        if(newRecord){
            if(this.getTireAction().equalsIgnoreCase("setfree")){
                this.setProcessingOrder(1);
            }else if(this.getTireAction().equalsIgnoreCase("place")){
                this.setProcessingOrder(2);
            }else if(this.getTireAction().equalsIgnoreCase("rotate")){
                this.setProcessingOrder(3);
            }else if(this.getTireAction().equalsIgnoreCase("exchange")){
                this.setProcessingOrder(4);
            }else{
                this.setProcessingOrder(100);
            }
        }
        return true;
    }

}
