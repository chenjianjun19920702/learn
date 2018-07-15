package com.cjj.learn.desginpattern.state;

/**
 * 具体状态(ConcreteState)角色：每一个具体状态类都实现了环境（Context）的一个状态所对应的行为。
 */
public class RepeatVoteState implements VoteState {

	public RepeatVoteState() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void vote(String user, String voteItem, VoteManager voteManager) {
		// 重复投票，暂时不做处理
        System.out.println("请不要重复投票");
	}

}
