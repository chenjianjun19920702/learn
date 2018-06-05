package com.cjj.learn.callback;

public interface Listener {
	
	void onSucceed(String result);

	void onFailure(String error);

}
