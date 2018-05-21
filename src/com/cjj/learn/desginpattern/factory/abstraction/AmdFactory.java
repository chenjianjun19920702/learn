package com.cjj.learn.desginpattern.factory.abstraction;

public class AmdFactory implements AbstractFactory{

	public AmdFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public Cpu createCpu() {
        // TODO Auto-generated method stub
        return new AmdCpu(938);
    }

    @Override
    public Mainboard createMainboard() {
        // TODO Auto-generated method stub
        return new AmdMainboard(938);
    }

}
