package com.zju.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModelUtil {
		
	/**
	 * 动态set方法
	 * @param object 执行方法的类的对象
	 * @param propertyName 需要set的变量名
	 * @param value 具体的变量值
	 */
	@SuppressWarnings("unchecked")
	public static void setValue(Object object, String propertyName, String value) {
		String firstLetter = propertyName.substring(0, 1).toUpperCase();
        String fieldName = propertyName;
        String setMethodName = "set" + firstLetter + propertyName.substring(1);
 
        try {  
        	Class<?> c = (Class<?>) object.getClass();
            Field f = c.getDeclaredField(fieldName);
            String fieldTypeName = f.getType().getName();
            if (fieldTypeName.equals("java.lang.Integer")) {
        		Class<?> paramsType[] = {Integer.class};
        		Object paramsValue[] = {new Integer(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue);  
            } else if (fieldTypeName.equals("java.lang.String")) {
            	Class<?> paramsType[] = {String.class};
        		Object paramsValue[] = {new String(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue); 
            } else if (fieldTypeName.equals("java.lang.Long")) {
            	Class<?> paramsType[] = {Long.class};
        		Object paramsValue[] = {new Long(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue); 
            } else if (fieldTypeName.equals("java.lang.Float")) {
            	Class<?> paramsType[] = {Float.class};
        		try {
	        		Object paramsValue[] = {new Float(value)};
	        		Method method = c.getMethod(setMethodName, paramsType);    
	                method.invoke(object, paramsValue);
    			} catch (NumberFormatException ex) {
    				System.out.println("数据浮点格式转化异常： " + value);
    				ex.printStackTrace();
    			}
            } else if (fieldTypeName.equals("java.lang.Boolean")) {
            	Class<?> paramsType[] = {Boolean.class};
        		Object paramsValue[] = {new Boolean(value)};
        		Method method = c.getMethod(setMethodName, paramsType);    
                method.invoke(object, paramsValue); 
            } else {
//            	if (fieldTypeName.equals("java.util.Date")) {
//					Class<?> paramsType[] = {java.util.Date.class};
//					Object paramsValue[] = {new SimpleDateFormat(Constants.DATETIME_FORMAT).parse(value)};
//					Method method = c.getMethod(setMethodName, paramsType);
//					method.invoke(object, paramsValue);
//				}
				System.out.println("\nModelUtil.setValue: Value中找不到数据类型：" + propertyName + "/" + fieldTypeName +".\n");
            }
        } catch (NoSuchFieldException e) {  
			System.out.println("\nModelUtil.setValue：找不到属性" + fieldName +".\n");
			e.printStackTrace();
        } catch (NoSuchMethodException e) {  
        	System.out.println("\nModelUtil.setValue：找不到方法" + setMethodName +".\n");
			e.printStackTrace();
        } catch (IllegalAccessException e) {  
        	System.out.println("\nModelUtil.setValue：参数不对" + ".\n");
			e.printStackTrace();
        } catch (InvocationTargetException e) {  
        	System.out.println("\nModelUtil.setValue：执行目标不对" + ".\n");
			e.printStackTrace();
        }
	}
	
	/**
	 * 动态get方法
	 * @param object 需要执行方法的类的对象
	 * @param name 变量的名称
	 * @return get的变量值
	 */
	public static Object getValue(Object object, String name){	
		if (name == null || name.equals("") || object == null) {
			return null;
		}
		String str = "get" + name.replaceFirst(name.substring(0, 1),name.substring(0, 1).toUpperCase()) ;
		Method method;
		try {
			//TODO:
			method = object.getClass().getMethod(str);
			Object obj = method.invoke(object);
			return obj;
		} catch (NoSuchMethodException e) {
        	System.out.println("\nModelUtil.getValue：找不到方法" + str +".\n");
			e.printStackTrace();
		} catch (IllegalAccessException e) {  
        	System.out.println("\nModelUtil.getValue：参数不对" + ".\n");
			e.printStackTrace();
        } catch (InvocationTargetException e) {  
        	System.out.println("\nModelUtil.getValue：执行目标不对" + ".\n");
			e.printStackTrace();
        }  
		return null;
	}
	
	/**
	 * 特殊类型的动态set
	 * @param object 执行set方法的对象
	 * @param propertyName 参数名
	 * @param value 参数值
	 * @param propertyClazz 参数类型
	 */
	public static void setValue(Object object, String propertyName, Object value, Class<?> propertyClazz) {
		String firstLetter = propertyName.substring(0, 1).toUpperCase();
        String setMethodName = "set" + firstLetter + propertyName.substring(1);        
        try {
        	@SuppressWarnings("unchecked")
			Class<?> c = (Class<?>) object.getClass();
			Class<?> paramsType[] = {propertyClazz};
    		Method method = c.getMethod(setMethodName, paramsType);    
            method.invoke(object, value);  
		} catch (NoSuchMethodException e) {  
        	System.out.println("\nModelUtil.setValue：找不到方法" + setMethodName +".\n");
			e.printStackTrace();
        } catch (IllegalAccessException e) {  
        	System.out.println("\nModelUtil.setValue：参数不对" + ".\n");
			e.printStackTrace();
        } catch (InvocationTargetException e) {  
        	System.out.println("\nModelUtil.setValue：执行目标不对" + ".\n");
			e.printStackTrace();
        } 
        
	}

}