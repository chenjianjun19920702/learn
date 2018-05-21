package com.cjj.learn.desginpattern.factory.abstraction;

public class IntelFactory implements AbstractFactory{

	public IntelFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public Cpu createCpu() {
        // TODO Auto-generated method stub
        return new IntelCpu(755);
    }

    @Override
    public Mainboard createMainboard() {
        // TODO Auto-generated method stub
        return new IntelMainboard(755);
    }

}
