package com.cjj.learn.concurrent.future;

public class TaskRunnable implements Runnable {
	
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

	public TaskRunnable(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		//
		System.out.println("当前线程id和名称为:" + this.id +", " + this.name);
		try {
			// 模拟下业务处理耗时
			System.out.println("子线程执行业务。。。");
			Thread.sleep(5*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Task1 [id=" + id + ", name=" + name + "]";
	}

}
