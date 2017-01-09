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

/** Generated Model for UY_TireMoveLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_UY_TireMoveLine extends PO implements I_UY_TireMoveLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161213L;

    /** Standard Constructor */
    public X_UY_TireMoveLine (Properties ctx, int UY_TireMoveLine_ID, String trxName)
    {
      super (ctx, UY_TireMoveLine_ID, trxName);
      /** if (UY_TireMoveLine_ID == 0)
        {
			setUY_TireMoveLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_UY_TireMoveLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_UY_TireMoveLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Modified.
		@param IsModified 
		The record is modified
	  */
	public void setIsModified (boolean IsModified)
	{
		set_Value (COLUMNNAME_IsModified, Boolean.valueOf(IsModified));
	}

	/** Get Modified.
		@return The record is modified
	  */
	public boolean isModified () 
	{
		Object oo = get_Value(COLUMNNAME_IsModified);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set IsTireAuxiliary.
		@param IsTireAuxiliary 
		IsTireAuxiliary
	  */
	public void setIsTireAuxiliary (boolean IsTireAuxiliary)
	{
		set_Value (COLUMNNAME_IsTireAuxiliary, Boolean.valueOf(IsTireAuxiliary));
	}

	/** Get IsTireAuxiliary.
		@return IsTireAuxiliary
	  */
	public boolean isTireAuxiliary () 
	{
		Object oo = get_Value(COLUMNNAME_IsTireAuxiliary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set KmTireTotal.
		@param KmTireTotal KmTireTotal	  */
	public void setKmTireTotal (int KmTireTotal)
	{
		set_Value (COLUMNNAME_KmTireTotal, Integer.valueOf(KmTireTotal));
	}

	/** Get KmTireTotal.
		@return KmTireTotal	  */
	public int getKmTireTotal () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_KmTireTotal);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set TireModel.
		@param TireModel TireModel	  */
	public void setTireModel (String TireModel)
	{
		set_Value (COLUMNNAME_TireModel, TireModel);
	}

	/** Get TireModel.
		@return TireModel	  */
	public String getTireModel () 
	{
		return (String)get_Value(COLUMNNAME_TireModel);
	}

	/** TireStatus AD_Reference_ID=1000008 */
	public static final int TIRESTATUS_AD_Reference_ID=1000008;
	/** NUEVO = nuevo */
	public static final String TIRESTATUS_NUEVO = "nuevo";
	/** RECAUCHUTADO = recauchutado */
	public static final String TIRESTATUS_RECAUCHUTADO = "recauchutado";
	/** BAJA = baja */
	public static final String TIRESTATUS_BAJA = "baja";
	/** Set TireStatus.
		@param TireStatus TireStatus	  */
	public void setTireStatus (String TireStatus)
	{

		set_Value (COLUMNNAME_TireStatus, TireStatus);
	}

	/** Get TireStatus.
		@return TireStatus	  */
	public String getTireStatus () 
	{
		return (String)get_Value(COLUMNNAME_TireStatus);
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

	public I_UY_TireLocator getUY_TireLocator() throws RuntimeException
    {
		return (I_UY_TireLocator)MTable.get(getCtx(), I_UY_TireLocator.Table_Name)
			.getPO(getUY_TireLocator_ID(), get_TrxName());	}

	/** Set UY_TireLocator ID.
		@param UY_TireLocator_ID UY_TireLocator ID	  */
	public void setUY_TireLocator_ID (int UY_TireLocator_ID)
	{
		if (UY_TireLocator_ID < 1) 
			set_Value (COLUMNNAME_UY_TireLocator_ID, null);
		else 
			set_Value (COLUMNNAME_UY_TireLocator_ID, Integer.valueOf(UY_TireLocator_ID));
	}

	/** Get UY_TireLocator ID.
		@return UY_TireLocator ID	  */
	public int getUY_TireLocator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireLocator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_UY_TireMark getUY_TireMark() throws RuntimeException
    {
		return (I_UY_TireMark)MTable.get(getCtx(), I_UY_TireMark.Table_Name)
			.getPO(getUY_TireMark_ID(), get_TrxName());	}

	/** Set UY_TireMark ID.
		@param UY_TireMark_ID UY_TireMark ID	  */
	public void setUY_TireMark_ID (int UY_TireMark_ID)
	{
		if (UY_TireMark_ID < 1) 
			set_Value (COLUMNNAME_UY_TireMark_ID, null);
		else 
			set_Value (COLUMNNAME_UY_TireMark_ID, Integer.valueOf(UY_TireMark_ID));
	}

	/** Get UY_TireMark ID.
		@return UY_TireMark ID	  */
	public int getUY_TireMark_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireMark_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_UY_TireMeasure getUY_TireMeasure() throws RuntimeException
    {
		return (I_UY_TireMeasure)MTable.get(getCtx(), I_UY_TireMeasure.Table_Name)
			.getPO(getUY_TireMeasure_ID(), get_TrxName());	}

	/** Set UY_TireMeasure ID.
		@param UY_TireMeasure_ID UY_TireMeasure ID	  */
	public void setUY_TireMeasure_ID (int UY_TireMeasure_ID)
	{
		if (UY_TireMeasure_ID < 1) 
			set_Value (COLUMNNAME_UY_TireMeasure_ID, null);
		else 
			set_Value (COLUMNNAME_UY_TireMeasure_ID, Integer.valueOf(UY_TireMeasure_ID));
	}

	/** Get UY_TireMeasure ID.
		@return UY_TireMeasure ID	  */
	public int getUY_TireMeasure_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireMeasure_ID);
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

	/** Set UY_TireMoveLine ID.
		@param UY_TireMoveLine_ID UY_TireMoveLine ID	  */
	public void setUY_TireMoveLine_ID (int UY_TireMoveLine_ID)
	{
		if (UY_TireMoveLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_UY_TireMoveLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_UY_TireMoveLine_ID, Integer.valueOf(UY_TireMoveLine_ID));
	}

	/** Get UY_TireMoveLine ID.
		@return UY_TireMoveLine ID	  */
	public int getUY_TireMoveLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_TireMoveLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}