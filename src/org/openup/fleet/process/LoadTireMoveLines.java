package org.openup.fleet.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.openup.fleet.model.MUYTire;
import org.openup.fleet.model.MUYTireMove;
import org.openup.fleet.model.MUYTireMoveLine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by sylvie on 13/12/16.
 */
public class LoadTireMoveLines extends SvrProcess {

    private MUYTireMove model = null;
    private int mVehicleID = 0;

    @Override
    protected void prepare() {
        model = new MUYTireMove(getCtx(),getRecord_ID(),get_TrxName());
        if(model.get_ID()>0){
            mVehicleID = model.getUY_Vehicle_ID();
        }

        /*ProcessInfoParameter[] para = getParameter();
        for(int i =0 ; i<para.length; i++){
            String name  = para[i].getParameterName();
            if (para[i].getParameter() == null)
                ;
            else if(name.equals("UY_TireMove_ID")){

            }
        }*/
    }

    @Override
    protected String doIt() throws Exception {
        if(countLogLines()>0){
            throw new AdempiereException("No se pueden actualizar neumaticos, existen lineas de accion.");
        }
        if(mVehicleID>0) {

            deleteTireMoveLines(model.get_ID());
            final String whereClause = " UY_Vehicle_ID=? ";

            List<MUYTire> neumaticos = new Query(getCtx(), MUYTire.Table_Name, whereClause, null)
                    .setParameters(mVehicleID)
                    .setOnlyActiveRecords(true)
                    .setOrderBy("UY_TireLocator_ID")
                    .list();

            for (MUYTire neumatico : neumaticos) {

                MUYTireMoveLine line = new MUYTireMoveLine(getCtx(), 0, get_TrxName());
                line.setUY_TireMove_ID(model.get_ID());
                line.setUY_Tire_ID(neumatico.get_ID());
                line.setUY_TireLocator_ID(neumatico.getUY_TireLocator_ID());
                line.setIsTireAuxiliary(neumatico.isAuxiliary());
                line.setKmTireTotal(neumatico.getKmTireTotal());
                line.setTireStatus(neumatico.getTireStatus());

                String sql = "";
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                sql = "SELECT tc.TireModel, tc.UY_TireMeasure_ID,tc.UY_TireMark_ID FROM UY_Tire t JOIN UY_TireCategory tc ON t.UY_TireCategory_ID = tc.UY_TireCategory_ID" +
                        " AND  t.UY_Tire_ID = ?";
                try {
                    pstmt = DB.prepareStatement(sql, null);
                    pstmt.setInt(1, neumatico.get_ID());
                    rs = pstmt.executeQuery();

                    if(rs.next()){
                        line.setTireModel(rs.getString("TireModel"));
                        line.setUY_TireMeasure_ID(rs.getInt("UY_TireMeasure_ID"));
                        line.setUY_TireMark_ID(rs.getInt("UY_TireMark_ID"));
                    }

                }catch (Exception e){
                    throw new AdempiereException(e.getMessage());
                }finally {
                    DB.close(rs,pstmt);
                }
                line.saveEx();

            }
        }
        return "OK";
    }

    private boolean deleteTireMoveLines(int tireMoveID){
        try {
            DB.getSQLValue(get_TrxName(),"DELETE FROM UY_TireMoveLine WHERE UY_TireMove_ID = "+
                    tireMoveID);
            return true;
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }
    }

    private int countLogLines(){
        try{
            return DB.getSQLValue(this.get_TrxName(),"SELECT COUNT(*) FROM UY_TireMoveLog WHERE UY_TireMove_ID ="+model.get_ID());
        }catch (Exception e){
            throw new AdempiereException(e.getMessage());
        }
    }


}
