package com.cjj.learn.desginpattern.factory.method;

public class ExportPdfFactory implements ExportFactory {

	public ExportPdfFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExportFile factory(String type) {
		if("standard".equals(type)){
			return new ExportStandardPdfFile();
		}else if("financial".equals(type)){
			return new ExportFinancialPdfFile();
		}else{
			throw new RuntimeException("没有找到对象");
		}
	}

}
