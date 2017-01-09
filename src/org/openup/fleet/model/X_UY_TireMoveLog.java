/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.openup.fleet.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for UY_TireMoveLog
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_UY_TireMoveLog extends PO implements I_UY_TireMoveLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170104L;

    /** Standard Constructor */
    public X_UY_TireMoveLog (Properties ctx, int UY_TireMoveLog_ID, String trxName)
    {
      super (ctx, UY_TireMoveLog_ID, trxName);
      /** if (UY_TireMoveLog_ID == 0)
        {
			setTireAction (null);
			setUY_Tire_ID (0);
			setUY_TireMove_ID (0);
			setUY_TireMoveLog_ID (0);
        } */
    }

    /** Load Constructor */
    public X_UY_TireMoveLog (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_UY_TireMoveLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ProcessingOrder.
		@param ProcessingOrder 
		Indica la prioridad de procesamiento (1 mas prioritario)
	  */
	public void setProcessingOrder (int ProcessingOrder)
	{
		set_Value (COLUMNNAME_ProcessingOrder, Integer.valueOf(ProcessingOrder));
	}

	/** Get ProcessingOrder.
		@return Indica la prioridad de procesamiento (1 mas prioritario)
	  */
	public int getProcessingOrder () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ProcessingOrder);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** TireAction AD_Reference_ID=1000015 */
	public static final int TIREACTION_AD_Reference_ID=1000015;
	/** Liberar del vehiculo = setfree */
	public static final String TIREACTION_LiberarDelVehiculo = "setfree";
	/** Colocar en vehiculo = place */
	public static final String TIREACTION_ColocarEnVehiculo = "place";
	/** Rotar en el vehiculo = rotate */
	public static final String TIREACTION_RotarEnElVehiculo = "rotate";
	/** Intercambiar neumaticos = exchange */
	public static final String TIREACTION_IntercambiarNeumaticos = "exchange";
	/** Set TireAction.
		@param TireAction TireAction	  */
	public void setTireAction (String TireAction)
	{

		set_Value (COLUMNNAME_TireAction, TireAction);
	}

	/** Get TireAction.
		@return TireAction	  */
	public String getTireAction () 
	{
		return (String)get_Value(COLUMNNAME_TireAction);
	}

	public I_UY_Tire getUY_Tire() throws RuntimeException
    {
		return (I_UY_Tire)MTable.get(getCtx(), I_UY_Tire.Table_Name)
			.getPO(getUY_Tire_ID(), get_TrxName());	}

	/** Set UY_Tire ID.
		@param UY_Tire_ID UY_Tire ID	  */
	public void setUY_Tire_ID (int UY_Tire_ID)
	{
		if (UY_Tire_ID < 1) 
			set_Value (COLUMNNAME_UY_Tire_ID, null);
		else 
			set_Value (COLUMNNAME_UY_Tire_ID, Integer.valueOf(UY_Tire_ID));
	}

	/** Get UY_Tire ID.
		@return UY_Tire ID	  */
	public int getUY_Tire_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_Tire_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UY_Tire_ID_To.
		@param UY_Tire_ID_To UY_Tire_ID_To	  */
	public void setUY_Tire_ID_To (int UY_Tire_ID_To)
	{
		set_Value (COLUMNNAME_UY_Tire_ID_To, Integer.valueOf(UY_Tire_ID_To));
	}

	/** Get UY_Tire_ID_To.
		@return UY_Tire_ID_To	  */
	public int getUY_Tire_ID_To () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_Tire_ID_To);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_UY_TireLocator getUY_TireLocator_ID_F() throws RuntimeException
    {
		return (I_UY_TireLocator)MTable.get(getCtx(), I_UY_TireLocator.Table_Name)
			.getPO(getUY_TireLocator_ID_From(), get_TrxName());	}

	/** Set UY_TireLocator_ID_From.
		@param UY_TireLocator_ID_From UY_TireLocator_ID_From	  */
	public void setUY_TireLocator_ID_From (int UY_TireLocator_ID_From)
	{
		set_Value (COLUMNNAME_UY_TireLocator_ID_From, Integer.valueOf(UY_TireLocator_ID_From));
	}

	/** Get UY_TireLocator_ID_From.
		@return UY_TireLocator_ID_From	  */
	public int getUY_TireLocator_ID_From () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireLocator_ID_From);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_UY_TireLocator getUY_TireLocator_To() throws RuntimeException
    {
		return (I_UY_TireLocator)MTable.get(getCtx(), I_UY_TireLocator.Table_Name)
			.getPO(getUY_TireLocator_ID_To(), get_TrxName());	}

	/** Set UY_TireLocator_ID_To.
		@param UY_TireLocator_ID_To UY_TireLocator_ID_To	  */
	public void setUY_TireLocator_ID_To (int UY_TireLocator_ID_To)
	{
		set_Value (COLUMNNAME_UY_TireLocator_ID_To, Integer.valueOf(UY_TireLocator_ID_To));
	}

	/** Get UY_TireLocator_ID_To.
		@return UY_TireLocator_ID_To	  */
	public int getUY_TireLocator_ID_To () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireLocator_ID_To);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_UY_TireMove getUY_TireMove() throws RuntimeException
    {
		return (I_UY_TireMove)MTable.get(getCtx(), I_UY_TireMove.Table_Name)
			.getPO(getUY_TireMove_ID(), get_TrxName());	}

	/** Set UY_TireMove ID.
		@param UY_TireMove_ID UY_TireMove ID	  */
	public void setUY_TireMove_ID (int UY_TireMove_ID)
	{
		if (UY_TireMove_ID < 1) 
			set_Value (COLUMNNAME_UY_TireMove_ID, null);
		else 
			set_Value (COLUMNNAME_UY_TireMove_ID, Integer.valueOf(UY_TireMove_ID));
	}

	/** Get UY_TireMove ID.
		@return UY_TireMove ID	  */
	public int getUY_TireMove_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireMove_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set UY_TireMoveLog ID.
		@param UY_TireMoveLog_ID UY_TireMoveLog ID	  */
	public void setUY_TireMoveLog_ID (int UY_TireMoveLog_ID)
	{
		if (UY_TireMoveLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_UY_TireMoveLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_UY_TireMoveLog_ID, Integer.valueOf(UY_TireMoveLog_ID));
	}

	/** Get UY_TireMoveLog ID.
		@return UY_TireMoveLog ID	  */
	public int getUY_TireMoveLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireMoveLog_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_UY_Vehicle getUY_Vehicle_ID_F() throws RuntimeException
    {
		return (I_UY_Vehicle)MTable.get(getCtx(), I_UY_Vehicle.Table_Name)
			.getPO(getUY_Vehicle_ID_From(), get_TrxName());	}

	/** Set Vehicle From.
		@param UY_Vehicle_ID_From 
		Vehicle From
	  */
	public void setUY_Vehicle_ID_From (int UY_Vehicle_ID_From)
	{
		set_Value (COLUMNNAME_UY_Vehicle_ID_From, Integer.valueOf(UY_Vehicle_ID_From));
	}

	/** Get Vehicle From.
		@return Vehicle From
	  */
	public int getUY_Vehicle_ID_From () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_Vehicle_ID_From);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_UY_Vehicle getUY_Vehicle_To() throws RuntimeException
    {
		return (I_UY_Vehicle)MTable.get(getCtx(), I_UY_Vehicle.Table_Name)
			.getPO(getUY_Vehicle_ID_To(), get_TrxName());	}

	/** Set Vehicle To.
		@param UY_Vehicle_ID_To 
		Vehicle To
	  */
	public void setUY_Vehicle_ID_To (int UY_Vehicle_ID_To)
	{
		set_Value (COLUMNNAME_UY_Vehicle_ID_To, Integer.valueOf(UY_Vehicle_ID_To));
	}

	/** Get Vehicle To.
		@return Vehicle To
	  */
	public int getUY_Vehicle_ID_To () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_Vehicle_ID_To);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}