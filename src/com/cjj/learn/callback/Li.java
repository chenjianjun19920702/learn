package com.cjj.learn.callback;

public class Li {

	public void executeMessage(Listener listener, String question) {
		System.out.println("小王的说 ---> " + question);

		for (int i = 0; i < 10000; i++) {

		}
		String result = "我去啊";
		listener.onSucceed(result);
	}
}
