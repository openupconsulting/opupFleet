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

package org.openup.fleet.process;

import org.compiere.process.SvrProcess;
/** Generated Process for (UY_P_ExchangeTires_For_SB)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class ReplaceTireLocationExAbstract extends SvrProcess
{
	/** Process Value 	*/
	private static final String VALUE = "UY_P_ExchangeTires_For_SB";
	/** Process Name 	*/
	private static final String NAME = "UY_P_ExchangeTires_For_SB";
	/** Process Id 	*/
	private static final int ID = 1000013;
 
	/**	Parameter Name for TireAction	*/
	public static final String TireAction = "TireAction";

	/**	Parameter Value for tireAction	*/
	private String tireAction;
 

	@Override
	protected void prepare()
	{
		tireAction = getParameterAsString(TireAction);
	}

	/**	 Getter Parameter Value for tireAction	*/
	protected String getTireAction() {
		return tireAction;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME;
	}
}