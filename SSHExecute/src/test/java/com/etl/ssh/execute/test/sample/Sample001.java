package com.etl.ssh.execute.test.sample;

import net.neoremind.sshxcute.core.*;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class Sample001 {

	public static void main(String[] args) {
		String catCommand="less /etc/hosts";
		String startRegionserverCommand="/hadoopdata1/soft/hbase/bin/hbase-daemon.sh stop regionserver";
		String getMemUsage="top -n 1 | grep \"Mem\" | awk '{print $7}'";
		SSHExec ssh = null;
		try {
			ConnBean cb = new ConnBean("192.168.8.52", "hadoop","1234@abcd");
			cb.setPort(22);
			ssh = SSHExec.getInstance(cb);
			CustomTask echo = new ExecCommand(getMemUsage);
			ssh.connect();
			Result res = ssh.exec(echo);
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
