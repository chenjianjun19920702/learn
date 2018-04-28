package com.cjj.learn.concurrent.future;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<Object> {
	
	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public TaskCallable(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Task1 [id=" + id + ", name=" + name + "]";
	}

	@Override
	public Object call() throws Exception {
		//
		System.out.println("当前线程id和名称为:" + this.id +", " + this.name);
		try {
			// 模拟下业务处理耗时
			System.out.println("子线程执行业务。。。");
			Thread.sleep(5*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "子线程处理完成";
	}

}
