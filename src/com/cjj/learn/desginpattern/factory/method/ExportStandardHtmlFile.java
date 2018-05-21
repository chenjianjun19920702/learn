package com.cjj.learn.desginpattern.factory.method;

public class ExportStandardHtmlFile implements ExportFile {

	public ExportStandardHtmlFile() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean export(String data) {
		// TODO Auto-generated method stub
		/**
         * 业务逻辑
         */
        System.out.println("导出标准HTML文件");
        return true;
	}

}
