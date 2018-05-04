package com.etl.ssh.execute.test.sample;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class Sample002 {

	public static void main(String[] args) {
		SSHExec ssh = null;
		try {
			ConnBean cb = new ConnBean("192.168.8.52", "hadoop","1234@abcd");
			ssh = SSHExec.getInstance(cb);		
			CustomTask abcd = new ExecCommand("abcd");
			ssh.connect();
			Result res = ssh.exec(abcd);
			if (res.isSuccess)
			{
				System.out.println("Return code: " + res.rc);
				System.out.println("sysout: " + res.sysout);
			}
			else
			{
				System.out.println("Return code: " + res.rc);
				System.out.println("error message: " + res.error_msg);
			}
		} catch (TaskExecFailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ssh.disconnect();	
		}
	}

}
