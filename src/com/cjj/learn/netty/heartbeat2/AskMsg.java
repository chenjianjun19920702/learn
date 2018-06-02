package com.cjj.learn.netty.heartbeat2;

/**
 * Created by yaozb on 15-4-11.
 * 请求类型的消息
 */
public class AskMsg extends BaseMsg {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AskMsg() {
        super();
        setType(MsgType.ASK);
    }
    private AskParams params;

    public AskParams getParams() {
        return params;
    }

    public void setParams(AskParams params) {
        this.params = params;
    }
}
