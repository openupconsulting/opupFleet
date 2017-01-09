package org.openup.fleet.process;

import org.compiere.process.SvrProcess;

/**
 * Created by sylvie on 05/12/16.
 */
public abstract class ReplaceTireLocationAbstract extends SvrProcess {
    /** Process Value 	*/
    private static final String VALUE = "UY_SBP_ReplaceTireLocation";
    /** Process Name 	*/
    private static final String NAME = "UY_SBP_ReplaceTireLocation";
    /** Process Id 	*/
    private static final int ID = 1000009;




    @Override
    protected void prepare()
    {
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
    }}
