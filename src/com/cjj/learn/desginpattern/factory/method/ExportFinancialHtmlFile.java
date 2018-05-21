package com.cjj.learn.desginpattern.factory.method;

public class ExportFinancialHtmlFile implements ExportFile {

	public ExportFinancialHtmlFile() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean export(String data) {
		// TODO Auto-generated method stub
		/**
         * 业务逻辑
         */
        System.out.println("导出财务版HTML文件");
		return true;
	}

}
