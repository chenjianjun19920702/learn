package com.cjj.learn.desginpattern.state;

/**
 * 具体状态(ConcreteState)角色：每一个具体状态类都实现了环境（Context）的一个状态所对应的行为。
 */
public class BlackVoteState implements VoteState {

	public BlackVoteState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void vote(String user, String voteItem, VoteManager voteManager) {
		// 记录黑名单中，禁止登录系统
        System.out.println("进入黑名单，将禁止登录和使用本系统");
	}

}
