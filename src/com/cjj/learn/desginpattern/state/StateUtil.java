package com.cjj.learn.desginpattern.state;

public class StateUtil {

	public StateUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		VoteManager vm = new VoteManager();
		for(int i=0;i<10;i++) {
			vm.vote("u1","A");
		}
	}

}
