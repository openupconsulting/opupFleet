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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for UY_TireMove
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_UY_TireMove extends PO implements I_UY_TireMove, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20161222L;

    /** Standard Constructor */
    public X_UY_TireMove (Properties ctx, int UY_TireMove_ID, String trxName)
    {
      super (ctx, UY_TireMove_ID, trxName);
      /** if (UY_TireMove_ID == 0)
        {
			setUY_TireMove_ID (0);
        } */
    }

    /** Load Constructor */
    public X_UY_TireMove (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_UY_TireMove[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_DocType getC_DocType() throws RuntimeException
    {
		return (I_C_DocType)MTable.get(getCtx(), I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set ExecuteAction.
		@param ExecuteAction ExecuteAction	  */
	public void setExecuteAction (String ExecuteAction)
	{
		set_Value (COLUMNNAME_ExecuteAction, ExecuteAction);
	}

	/** Get ExecuteAction.
		@return ExecuteAction	  */
	public String getExecuteAction () 
	{
		return (String)get_Value(COLUMNNAME_ExecuteAction);
	}

	/** Set ExecuteAction2.
		@param ExecuteAction2 ExecuteAction2	  */
	public void setExecuteAction2 (String ExecuteAction2)
	{
		set_Value (COLUMNNAME_ExecuteAction2, ExecuteAction2);
	}

	/** Get ExecuteAction2.
		@return ExecuteAction2	  */
	public String getExecuteAction2 () 
	{
		return (String)get_Value(COLUMNNAME_ExecuteAction2);
	}

	/** Set ExecuteAction3.
		@param ExecuteAction3 ExecuteAction3	  */
	public void setExecuteAction3 (String ExecuteAction3)
	{
		set_Value (COLUMNNAME_ExecuteAction3, ExecuteAction3);
	}

	/** Get ExecuteAction3.
		@return ExecuteAction3	  */
	public String getExecuteAction3 () 
	{
		return (String)get_Value(COLUMNNAME_ExecuteAction3);
	}

	/** Set ExecuteAction4.
		@param ExecuteAction4 ExecuteAction4	  */
	public void setExecuteAction4 (String ExecuteAction4)
	{
		set_Value (COLUMNNAME_ExecuteAction4, ExecuteAction4);
	}

	/** Get ExecuteAction4.
		@return ExecuteAction4	  */
	public String getExecuteAction4 () 
	{
		return (String)get_Value(COLUMNNAME_ExecuteAction4);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set QtyAuxiliary.
		@param QtyAuxiliary QtyAuxiliary	  */
	public void setQtyAuxiliary (BigDecimal QtyAuxiliary)
	{
		set_Value (COLUMNNAME_QtyAuxiliary, QtyAuxiliary);
	}

	/** Get QtyAuxiliary.
		@return QtyAuxiliary	  */
	public BigDecimal getQtyAuxiliary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyAuxiliary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QtyTires.
		@param QtyTires QtyTires	  */
	public void setQtyTires (BigDecimal QtyTires)
	{
		set_Value (COLUMNNAME_QtyTires, QtyTires);
	}

	/** Get QtyTires.
		@return QtyTires	  */
	public BigDecimal getQtyTires () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyTires);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set UY_TireMove ID.
		@param UY_TireMove_ID UY_TireMove ID	  */
	public void setUY_TireMove_ID (int UY_TireMove_ID)
	{
		if (UY_TireMove_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_UY_TireMove_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_UY_TireMove_ID, Integer.valueOf(UY_TireMove_ID));
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

	public I_UY_Vehicle getUY_Vehicle() throws RuntimeException
    {
		return (I_UY_Vehicle)MTable.get(getCtx(), I_UY_Vehicle.Table_Name)
			.getPO(getUY_Vehicle_ID(), get_TrxName());	}

	/** Set UY_Vehicle ID.
		@param UY_Vehicle_ID UY_Vehicle ID	  */
	public void setUY_Vehicle_ID (int UY_Vehicle_ID)
	{
		if (UY_Vehicle_ID < 1) 
			set_Value (COLUMNNAME_UY_Vehicle_ID, null);
		else 
			set_Value (COLUMNNAME_UY_Vehicle_ID, Integer.valueOf(UY_Vehicle_ID));
	}

	/** Get UY_Vehicle ID.
		@return UY_Vehicle ID	  */
	public int getUY_Vehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UY_Vehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}