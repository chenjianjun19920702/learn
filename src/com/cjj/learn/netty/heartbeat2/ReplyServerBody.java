package com.cjj.learn.netty.heartbeat2;

/**
 * Created by yaozb on 15-4-11.
 */
public class ReplyServerBody extends ReplyBody {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serverInfo;
    public ReplyServerBody(String serverInfo) {
        this.serverInfo = serverInfo;
    }
    public String getServerInfo() {
        return serverInfo;
    }
    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
}
