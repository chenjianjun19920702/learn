package com.cjj.learn.netty.heartbeat2;

/**
 * Created by yaozb on 15-4-11.
 * 登录验证类型的消息
 */
public class LoginMsg extends BaseMsg {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
    private String password;
    public LoginMsg() {
        super();
        setType(MsgType.LOGIN);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
