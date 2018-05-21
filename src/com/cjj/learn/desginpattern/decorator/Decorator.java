package com.cjj.learn.desginpattern.decorator;

public class Decorator {

	public Decorator() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		TheMonkeyKing king = new Monkey();
		king.move();
		TheMonkeyKing bird = new Bird(king);
		bird.move();
		TheMonkeyKing fish = new Fish(bird);
		fish.move();
	}

}
