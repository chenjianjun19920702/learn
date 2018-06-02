package com.cjj.learn.netty.heartbeat2;

/**
 * Created by yaozb on 15-4-11.
 */
public class ReplyMsg extends BaseMsg {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReplyMsg() {
        super();
        setType(MsgType.REPLY);
    }
    private ReplyBody body;

    public ReplyBody getBody() {
        return body;
    }

    public void setBody(ReplyBody body) {
        this.body = body;
    }
}
