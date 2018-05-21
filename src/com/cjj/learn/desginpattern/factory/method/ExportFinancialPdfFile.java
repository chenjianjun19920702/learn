package com.cjj.learn.desginpattern.factory.method;

public class ExportFinancialPdfFile implements ExportFile {

	public ExportFinancialPdfFile() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean export(String data) {
		// TODO Auto-generated method stub
		/**
         * 业务逻辑
         */
        System.out.println("导出财务版PDF文件");
		return true;
	}

}
