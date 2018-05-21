package com.cjj.learn.desginpattern.factory.method;

public class ExportUtil {

	public ExportUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        // TODO Auto-generated method stub
        String data = "";
        ExportFactory exportFactory = new ExportHtmlFactory();
        ExportFile ef = exportFactory.factory("financial");
        ef.export(data);
    }
}
