package com.cjj.learn.desginpattern.factory.method;

public class ExportHtmlFactory implements ExportFactory {

	public ExportHtmlFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExportFile factory(String type) {
		if("standard".equals(type)){
			return new ExportStandardHtmlFile();
		}else if("financial".equals(type)){
			return new ExportFinancialHtmlFile();
		}else{
			throw new RuntimeException("没有找到对象");
		}
	}

}
