package com.cjj.learn.desginpattern.factory.abstraction;

public class AmdCpu implements Cpu {

	/**
     * CPU的针脚数
     */
    private int pins = 0;
    
    public  AmdCpu(int pins){
        this.pins = pins;
    }
    
    @Override
    public void calculate() {
        System.out.println("Amd CPU的针脚数：" + pins);
    }

}
