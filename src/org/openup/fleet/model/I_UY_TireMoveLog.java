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

/** Generated Interface for UY_TireMoveLog
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0
 */
public interface I_UY_TireMoveLog 
{

    /** TableName=UY_TireMoveLog */
    public static final String Table_Name = "UY_TireMoveLog";

    /** AD_Table_ID=1000043 */
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

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID(int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name ProcessingOrder */
    public static final String COLUMNNAME_ProcessingOrder = "ProcessingOrder";

	/** Set ProcessingOrder.
	  * Indica la prioridad de procesamiento (1 mas prioritario)
	  */
	public void setProcessingOrder(int ProcessingOrder);

	/** Get ProcessingOrder.
	  * Indica la prioridad de procesamiento (1 mas prioritario)
	  */
	public int getProcessingOrder();

    /** Column name TireAction */
    public static final String COLUMNNAME_TireAction = "TireAction";

	/** Set TireAction	  */
	public void setTireAction(String TireAction);

	/** Get TireAction	  */
	public String getTireAction();

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

    /** Column name UY_Tire_ID_To */
    public static final String COLUMNNAME_UY_Tire_ID_To = "UY_Tire_ID_To";

	/** Set UY_Tire_ID_To	  */
	public void setUY_Tire_ID_To(int UY_Tire_ID_To);

	/** Get UY_Tire_ID_To	  */
	public int getUY_Tire_ID_To();

    /** Column name UY_TireLocator_ID_From */
    public static final String COLUMNNAME_UY_TireLocator_ID_From = "UY_TireLocator_ID_From";

	/** Set UY_TireLocator_ID_From	  */
	public void setUY_TireLocator_ID_From(int UY_TireLocator_ID_From);

	/** Get UY_TireLocator_ID_From	  */
	public int getUY_TireLocator_ID_From();

	public I_UY_TireLocator getUY_TireLocator_ID_F() throws RuntimeException;

    /** Column name UY_TireLocator_ID_To */
    public static final String COLUMNNAME_UY_TireLocator_ID_To = "UY_TireLocator_ID_To";

	/** Set UY_TireLocator_ID_To	  */
	public void setUY_TireLocator_ID_To(int UY_TireLocator_ID_To);

	/** Get UY_TireLocator_ID_To	  */
	public int getUY_TireLocator_ID_To();

	public I_UY_TireLocator getUY_TireLocator_To() throws RuntimeException;

    /** Column name UY_TireMove_ID */
    public static final String COLUMNNAME_UY_TireMove_ID = "UY_TireMove_ID";

	/** Set UY_TireMove ID	  */
	public void setUY_TireMove_ID(int UY_TireMove_ID);

	/** Get UY_TireMove ID	  */
	public int getUY_TireMove_ID();

	public I_UY_TireMove getUY_TireMove() throws RuntimeException;

    /** Column name UY_TireMoveLog_ID */
    public static final String COLUMNNAME_UY_TireMoveLog_ID = "UY_TireMoveLog_ID";

	/** Set UY_TireMoveLog ID	  */
	public void setUY_TireMoveLog_ID(int UY_TireMoveLog_ID);

	/** Get UY_TireMoveLog ID	  */
	public int getUY_TireMoveLog_ID();

    /** Column name UY_Vehicle_ID_From */
    public static final String COLUMNNAME_UY_Vehicle_ID_From = "UY_Vehicle_ID_From";

	/** Set Vehicle From.
	  * Vehicle From
	  */
	public void setUY_Vehicle_ID_From(int UY_Vehicle_ID_From);

	/** Get Vehicle From.
	  * Vehicle From
	  */
	public int getUY_Vehicle_ID_From();

	public I_UY_Vehicle getUY_Vehicle_ID_F() throws RuntimeException;

    /** Column name UY_Vehicle_ID_To */
    public static final String COLUMNNAME_UY_Vehicle_ID_To = "UY_Vehicle_ID_To";

	/** Set Vehicle To.
	  * Vehicle To
	  */
	public void setUY_Vehicle_ID_To(int UY_Vehicle_ID_To);

	/** Get Vehicle To.
	  * Vehicle To
	  */
	public int getUY_Vehicle_ID_To();

	public I_UY_Vehicle getUY_Vehicle_To() throws RuntimeException;
}
