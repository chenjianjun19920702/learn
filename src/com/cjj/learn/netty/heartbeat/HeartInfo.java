package com.cjj.learn.netty.heartbeat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HeartInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9086362157623641393L;

	private String ip;

	private int port;

	private Date lastTime;

	private Map<String , String> cpuInfo = new HashMap<String,String>();

	private Map<String , String> memInfo = new HashMap<String, String>();

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Map<String, String> getCpuInfo() {
		return cpuInfo;
	}

	public void setCpuInfo(Map<String, String> cpuInfo) {
		this.cpuInfo = cpuInfo;
	}

	public Map<String, String> getMemInfo() {
		return memInfo;
	}

	public void setMemInfo(Map<String, String> memInfo) {
		this.memInfo = memInfo;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	@Override
	public String toString() {
		return "HeartInfo{" +
				"ip='" + ip + '\'' +
				", port=" + port +
				", lastTime=" + lastTime +
				", cpuInfo=" + cpuInfo +
				", memInfo=" + memInfo +
				'}';
	}
}
