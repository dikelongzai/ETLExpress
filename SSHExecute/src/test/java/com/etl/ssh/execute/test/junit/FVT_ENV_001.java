package com.etl.ssh.execute.test.junit;

import junit.framework.TestCase;
import net.neoremind.sshxcute.core.SSHExec;

/**
 * 
 * Test case number: FVT_CMD_002
 * 
 * Objective: Print configuration parameters
 *  
 * Procedure: 
 *   
 * Expected Results:
 *            All parameters and their values are return.
 * 
 * @author neo
 *
 */
public class FVT_ENV_001 extends TestCase{

	public void setUp() throws Exception {
		super.setUp();
	}

	public void testFVT_ENV_001() throws Exception
	{
		SSHExec.showEnvConfig();
	}
	
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
}
