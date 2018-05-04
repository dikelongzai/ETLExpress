package net.neoremind.sshxcute.core;

import static net.neoremind.sshxcute.core.SysConfigOption.SSH_PORT_NUMBER;

/**
 * This is a Javabean class that used to construct a connection. It stores host, usernamd and password for a connection.
 * <br>
 * The three parameters has getter and setter method.
 * 
 * @author zxucdl
 *
 */
public class ConnBean {

	private String host;
	
	private String user;
	
	private String password;

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	private Integer port=SSH_PORT_NUMBER;

	public ConnBean(String host, String user, String password){
		this.host = host;
		this.password = password;
		this.user = user;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}

