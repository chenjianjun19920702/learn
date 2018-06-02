package com.cjj.learn.netty.heartbeat2;

/**
 * Created by yaozb on 15-4-11.
 */
public class ReplyClientBody extends ReplyBody {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clientInfo;

    public ReplyClientBody(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }
}
