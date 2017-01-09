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
package org.openup.fleet.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for UY_TireMoveLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_UY_TireMoveLine 
{

    /** TableName=UY_TireMoveLine */
    public static final String Table_Name = "UY_TireMoveLine";

    /** AD_Table_ID=1000041 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID(int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive(boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsModified */
    public static final String COLUMNNAME_IsModified = "IsModified";

	/** Set Modified.
	  * The record is modified
	  */
	public void setIsModified(boolean IsModified);

	/** Get Modified.
	  * The record is modified
	  */
	public boolean isModified();

    /** Column name IsTireAuxiliary */
    public static final String COLUMNNAME_IsTireAuxiliary = "IsTireAuxiliary";

	/** Set IsTireAuxiliary.
	  * IsTireAuxiliary
	  */
	public void setIsTireAuxiliary(boolean IsTireAuxiliary);

	/** Get IsTireAuxiliary.
	  * IsTireAuxiliary
	  */
	public boolean isTireAuxiliary();

    /** Column name KmTireTotal */
    public static final String COLUMNNAME_KmTireTotal = "KmTireTotal";

	/** Set KmTireTotal	  */
	public void setKmTireTotal(int KmTireTotal);

	/** Get KmTireTotal	  */
	public int getKmTireTotal();

    /** Column name TireModel */
    public static final String COLUMNNAME_TireModel = "TireModel";

	/** Set TireModel	  */
	public void setTireModel(String TireModel);

	/** Get TireModel	  */
	public String getTireModel();

    /** Column name TireStatus */
    public static final String COLUMNNAME_TireStatus = "TireStatus";

	/** Set TireStatus	  */
	public void setTireStatus(String TireStatus);

	/** Get TireStatus	  */
	public String getTireStatus();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UY_Tire_ID */
    public static final String COLUMNNAME_UY_Tire_ID = "UY_Tire_ID";

	/** Set UY_Tire ID	  */
	public void setUY_Tire_ID(int UY_Tire_ID);

	/** Get UY_Tire ID	  */
	public int getUY_Tire_ID();

	public I_UY_Tire getUY_Tire() throws RuntimeException;

    /** Column name UY_TireLocator_ID */
    public static final String COLUMNNAME_UY_TireLocator_ID = "UY_TireLocator_ID";

	/** Set UY_TireLocator ID	  */
	public void setUY_TireLocator_ID(int UY_TireLocator_ID);

	/** Get UY_TireLocator ID	  */
	public int getUY_TireLocator_ID();

	public I_UY_TireLocator getUY_TireLocator() throws RuntimeException;

    /** Column name UY_TireMark_ID */
    public static final String COLUMNNAME_UY_TireMark_ID = "UY_TireMark_ID";

	/** Set UY_TireMark ID	  */
	public void setUY_TireMark_ID(int UY_TireMark_ID);

	/** Get UY_TireMark ID	  */
	public int getUY_TireMark_ID();

	public I_UY_TireMark getUY_TireMark() throws RuntimeException;

    /** Column name UY_TireMeasure_ID */
    public static final String COLUMNNAME_UY_TireMeasure_ID = "UY_TireMeasure_ID";

	/** Set UY_TireMeasure ID	  */
	public void setUY_TireMeasure_ID(int UY_TireMeasure_ID);

	/** Get UY_TireMeasure ID	  */
	public int getUY_TireMeasure_ID();

	public I_UY_TireMeasure getUY_TireMeasure() throws RuntimeException;

    /** Column name UY_TireMove_ID */
    public static final String COLUMNNAME_UY_TireMove_ID = "UY_TireMove_ID";

	/** Set UY_TireMove ID	  */
	public void setUY_TireMove_ID(int UY_TireMove_ID);

	/** Get UY_TireMove ID	  */
	public int getUY_TireMove_ID();

	public I_UY_TireMove getUY_TireMove() throws RuntimeException;

    /** Column name UY_TireMoveLine_ID */
    public static final String COLUMNNAME_UY_TireMoveLine_ID = "UY_TireMoveLine_ID";

	/** Set UY_TireMoveLine ID	  */
	public void setUY_TireMoveLine_ID(int UY_TireMoveLine_ID);

	/** Get UY_TireMoveLine ID	  */
	public int getUY_TireMoveLine_ID();
}
