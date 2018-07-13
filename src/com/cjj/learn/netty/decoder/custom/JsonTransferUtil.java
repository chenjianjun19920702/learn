package com.cjj.learn.netty.decoder.custom;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
*  json转Map，转bean，转List<bean> 
*/ 
public class JsonTransferUtil {
	
    public static String ObjectToJson(Object value) {  
        try {  
            ObjectMapper mapper = new ObjectMapper();  
            String js = mapper.writeValueAsString(value);  
            return js;  
        } catch (Exception e) {  
            System.out.println(e.toString());  
            return "Error";  
        }  
    }  
  
    public static Object JsonToList(String json, Class<?> bean) throws Exception {  
        ObjectMapper mapper = new ObjectMapper();  
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, bean);  
        return mapper.readValue(json, javaType);  
    }  
  
    public static Object JsonToBean(String json, Class<?> bean) throws Exception {  
        ObjectMapper mapper = new ObjectMapper();  
        @SuppressWarnings("deprecation")
		JavaType javaType = mapper.getTypeFactory().uncheckedSimpleType(bean);  
        return mapper.readValue(json, javaType);  
    }  

	public static Map<String, Object> jsonFile2Map(String path) {
		try{  
			ObjectMapper mapper = new ObjectMapper();  
			Map<String, Object> map = mapper.readValue(new File(path),new TypeReference<Map<String, Object>>(){});  
			return map;
		} catch(Exception e){  
			e.printStackTrace();  
		}  
		return null;
	}

	public static Map<String, Object> json2Map(String json) {
		ObjectMapper mapper = new ObjectMapper();  
		try{  
			Map<String, Object> map = mapper.readValue(json, new TypeReference<HashMap<String, Object>>(){});  
			return map;
		}catch(Exception e){  
			e.printStackTrace();  
		}  
		return null;
	}

	public static void map2JsonFile(Map<String, Object> map, String path) {
		ObjectMapper mapper = new ObjectMapper();  
		try {  
			mapper.writeValue(new File(path), map);  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}

	public static String map2Json(Map<String, Object> map) {
		try{  
			ObjectMapper mapper = new ObjectMapper();  
			String json = mapper.writeValueAsString(map);  
			return json; 
		}catch(Exception e){  
			e.printStackTrace();  
		}  
		return null;
	}
	
	/**
	 * 对象深拷贝
	 */
	public static Object deepClone(Object obj) {  
        try {	// 将对象写到流里  
            ByteArrayOutputStream bo = new ByteArrayOutputStream();  
            ObjectOutputStream oo = new ObjectOutputStream(bo);  
            oo.writeObject(obj);// 从流里读出来  
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());  
            ObjectInputStream oi = new ObjectInputStream(bi);  
            return (oi.readObject());  
        } catch (Exception e) {  
            return null;  
        }  
    }  
	
	public static void main(String[] args) {  
        String json = "{\"name\":\"zitong\", \"age\":\"26\"}";  
        System.out.println(JsonTransferUtil.map2Json(JsonTransferUtil.json2Map(json)));
        
//        Map<String, Object> map = JsonMapTransferUtil.jsonFile2Map("e://test_json.js");
////      Map<String, Object> map2 = JsonMapTransferUtil.jsonFile2Map("e://test_json2.js");
////      map2JsonFile(map, "e://test_json2.js");
//        for (Entry<String, Object> en : map.entrySet()) {
//        	System.out.println("key : " + en.getKey());
//        }
        
//        JsonMapTransferUtil.map2JsonFile(GrafanaUtil.generateDashboardTemplate(), "e://test_json_11111.js");
		
//		System.out.println(JsonMapTransferUtil.map2Json(JsonMapTransferUtil.jsonFile2Map("src//main//resources//dashboard_template.json")));
	
		/*Map<String, Object> queryMap = JsonTransferUtil.json2Map(GrafanaUtil.METRICS_QUERY_JSON);
		Map<String, Object> queryMap1 = (Map<String, Object>) JsonTransferUtil.deepClone(queryMap);
		queryMap1.put("alias", "cpu/usage_rate1");
		
		Map<String, Object> queryMap2 = (Map<String, Object>) JsonTransferUtil.deepClone(queryMap);
		queryMap2.put("alias", "cpu/usage_rate2");
		
		Map<String, Object> queryMap3 = (Map<String, Object>) JsonTransferUtil.deepClone(queryMap);
		queryMap3.put("alias", "cpu/usage_rate3");
		
		System.out.println(queryMap1);
		System.out.println(queryMap2);
		System.out.println(queryMap3);*/
	}
}
