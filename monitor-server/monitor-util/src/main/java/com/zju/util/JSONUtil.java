package com.zju.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {
	/**
	 * 不用JSONObject来from的属性类型
	 */
	public static final String TYPES = "java.lang.String; java.lang.Integer; "
			+ "java.lang.Long; java.lang.Float; java.lang.Boolean;";

	/**
	 * JSON填参
	 * 
	 * @param json
	 *            JSONObject对象
	 * @param key
	 *            参数名
	 * @param object
	 *            参数值
	 * @return 填完参数的JSONObject对象
	 */
	public static JSONObject putObject(JSONObject json, String key, Object object) {
		try {
			if (TYPES.indexOf(object.getClass().getName()) >= 0) {
				json.put(key, object);
			} else {
				json.put(key, JSONObject.fromObject(object));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 根据参数来获取一个类的JSON对象
	 * 
	 * @param object
	 *            类
	 * @param selector
	 *            这是一个自定义的选择器，选择的标准如下：
	 *            "all"表示当前类的所有参数，其他情况下则按照当前参数来传，所有的参数需要有对应的get函数 参数之间用空格隔开，如
	 *            "name password"则表示取到当前类getName和getPassword的值，
	 *            并且值在JSON文件中存放的键名为"name"和"password"，此外如果参数有点隔开，则表示当前参数为一个类
	 *            如"user.name"则表示取到getUser方法的返回值（User类对象）的getName方法返回值 例如
	 *            "params user.name user.test.param1 user.test.param2"
	 *            当层级"."较多的时候，为了方便起见，我们还提供了根据快捷的方式，可以在.后的参数统一打上"{"和"}"，
	 *            并且括号内的第一个字符为分隔符如"{,params,user.name,user.test.param1,user.test.param2}"
	 *            但是这个分隔符必须不能是"."、"{"、"}"、"*"和26字母,也不能是上一级目录，否则就会报错
	 *            可以把上面那个多层次的JSON匹配符简单写成
	 *            "{ params user.{,name,test.{:param1:param2}}}"
	 * @return JSON文件
	 */
	public static JSONObject fromObject(Object object, String selector) {
		JSONObject json = new JSONObject();
		String cut = " ";
		try {
			if (selector.startsWith("{") && selector.endsWith("}")) {
				cut = selector.substring(1, 2);
				if (cut.matches("[a-zA-Z]|[}]|[{]|[.]|[*]|[!]")) {
					// 分隔符不能是特定符号
					throw new Exception("wrong cut char : " + cut);
				}
				selector = selector.substring(2, selector.length() - 1);
			}
			String[] strs = selector.split(cut);
			Map<String, String> parms = new HashMap<String, String>();
			for (String str : strs) {
				if (str == null) {
					continue;
				} else if (str.equals("*")) {
					json = JSONObject.fromObject(object);
					continue;
				} else if (str.indexOf(".") < 0) {
					if (str.startsWith("!")) {
						String key = str.substring(1);
						if (json.get(key) == null) {
							continue;
						}
						json.remove(key);
					} else {
						Object obj = ModelUtil.getValue(object, str);
						json = putObject(json, str, obj);
					}
				} else {
					int i = str.indexOf(".");
					String key = str.substring(0, i);
					String value = str.substring(i + 1);
					if (parms.get(key) != null) {
						value = parms.get(key) + " " + value;
					}
					parms.put(key, value);
				}
			}
			// 循环下一层
			for (String key : parms.keySet()) {
				json.put(key, fromObject(ModelUtil.getValue(object, key), parms.get(key)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 根据参数来获取一个集合的JSONArray
	 * 
	 * @param list
	 *            集合
	 * @param selector
	 *            这是一个自定义的选择器，选择的标准如下：
	 *            "all"表示当前类的所有参数，其他情况下则按照当前参数来传，所有的参数需要有对应的get函数 参数之间用空格隔开，如
	 *            "name password"则表示取到当前类getName和getPassword的值，
	 *            并且值在JSON文件中存放的键名为"name"和"password"，此外如果参数有点隔开，则表示当前参数为一个类
	 *            如"user.name"则表示取到getUser方法的返回值（User类对象）的getName方法返回值 例如
	 *            "params user.name user.test.param1 user.test.param2"
	 *            当层级"."较多的时候，为了方便起见，我们还提供了根据快捷的方式，可以在.后的参数统一打上"{"和"}"，
	 *            并且括号内的第一个字符为分隔符如"{,params,user.name,user.test.param1,user.test.param2}"
	 *            但是这个分隔符必须不能是"."、"{"、"}"、"*"和26字母,也不能是上一级目录，否则就会报错
	 *            可以把上面那个多层次的JSON匹配符简单写成
	 *            "{ params user.{,name,test.{:param1:param2}}}"
	 * @return JSON文件
	 */
	public static JSONArray fromList(List<?> list, String selector) {
		JSONArray array = new JSONArray();
		if (list != null) {
			for (Object object : list) {
				array.add(JSONUtil.fromObject(object, selector));
			}
		}
		return array;
	}

	public interface DoInItem<T> {
		void doing(T t, JSONObject item);
	}

	public static <T> JSONArray fromList(List<T> list, String selector, DoInItem<T> doInItem) {
		JSONArray array = new JSONArray();
		if (list != null) {
			for (T object : list) {
				JSONObject item = JSONUtil.fromObject(object, selector);
				doInItem.doing(object, item);
				array.add(item);
			}
		}
		return array;
	}
	
	public static JSONObject fromXml(String xml) {
		XMLSerializer xmlSerializer = new XMLSerializer(); 
        JSON json = xmlSerializer.read(xml);
        return (JSONObject)json;
	}
	
//	public static void main(String args[]) {
//		System.out.println(fromXml("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str><sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id><trade_type><![CDATA[APP]]></trade_type></xml>"));
//	}

}
