package com.cjj.learn.callback;

public class Wang implements Listener {

	private Li li;

	public Wang(Li li) {
		this.li = li;
	}

	public void askQusetion(final String question) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				li.executeMessage(Wang.this, question);
			}
		}).start();
		play();
	}

	public void play() {
		System.out.println("我要去逛街去了，你去吗？");
	}

	@Override
	public void onSucceed(String result) {
		System.out.println("小李回答说 ---> " + result);
	}

	@Override
	public void onFailure(String error) {
		// TODO Auto-generated method stub
	}
}
