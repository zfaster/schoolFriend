package com.sys.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 * 锟斤拷锟酵癸拷锟斤拷锟斤拷
 * @author ZZY
 *
 */
public class GenericsUtils {
	/**  
     * 通锟斤拷锟斤拷,锟斤拷锟街革拷锟斤拷锟侥革拷锟斤拷姆锟斤拷筒锟斤拷锟斤拷实锟斤拷锟斤拷锟斤拷. 锟斤拷BuyerServiceBean extends DaoSupport<Buyer>  
     *  
     * @param clazz clazz 锟斤拷要锟斤拷锟斤拷锟斤拷锟�锟斤拷锟斤拷锟斤拷锟教承凤拷锟酵革拷锟斤拷
     * @param index 锟斤拷锟酵诧拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷0锟斤拷始.  
     * @return 锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟斤拷<code>Object.class</code>
     */  
    @SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) {    
        Type genType = clazz.getGenericSuperclass();//锟矫碉拷锟斤拷锟酵革拷锟斤拷  
        //锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷直锟接凤拷锟斤拷Object.class   
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;   
        }  
        //锟斤拷锟截憋拷示锟斤拷锟斤拷锟斤拷实锟斤拷锟斤拷锟酵诧拷锟斤拷锟絋ype锟斤拷锟斤拷锟斤拷锟斤拷锟�锟斤拷锟斤拷锟斤拷诺亩锟斤拷嵌锟接︼拷锟斤拷偷锟紺lass, 锟斤拷BuyerServiceBean extends DaoSupport<Buyer,Contact>锟酵凤拷锟斤拷Buyer锟斤拷Contact锟斤拷锟斤拷   
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();                   
        if (index >= params.length || index < 0) { 
        	 throw new RuntimeException();
        }      
        if (!(params[index] instanceof Class)) {
            return Object.class;   
        }   
        return (Class) params[index];
    }
	/**  
     * 通锟斤拷锟斤拷,锟斤拷锟街革拷锟斤拷锟侥革拷锟斤拷牡锟揭伙拷锟斤拷锟斤拷筒锟斤拷锟斤拷实锟斤拷锟斤拷锟斤拷. 锟斤拷BuyerServiceBean extends DaoSupport<Buyer>  
     *  
     * @param clazz clazz 锟斤拷要锟斤拷锟斤拷锟斤拷锟�锟斤拷锟斤拷锟斤拷锟教承凤拷锟酵革拷锟斤拷
     * @return 锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟斤拷<code>Object.class</code>
     */  
    @SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz) {
    	return getSuperClassGenricType(clazz,0);
    }
	/**  
     * 通锟斤拷锟斤拷,锟斤拷梅锟斤拷锟斤拷锟斤拷锟街碉拷锟斤拷筒锟斤拷锟斤拷实锟斤拷锟斤拷锟斤拷. 锟斤拷: public Map<String, Buyer> getNames(){}
     *  
     * @param Method method 锟斤拷锟斤拷
     * @param int index 锟斤拷锟酵诧拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷0锟斤拷始.
     * @return 锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟斤拷<code>Object.class</code>
     */ 
    @SuppressWarnings("rawtypes")
	public static Class getMethodGenericReturnType(Method method, int index) {
    	Type returnType = method.getGenericReturnType();
    	if(returnType instanceof ParameterizedType){
    	    ParameterizedType type = (ParameterizedType) returnType;
    	    Type[] typeArguments = type.getActualTypeArguments();
            if (index >= typeArguments.length || index < 0) { 
            	 throw new RuntimeException();
            } 
    	    return (Class)typeArguments[index];
    	}
    	return Object.class;
    }
	/**  
     * 通锟斤拷锟斤拷,锟斤拷梅锟斤拷锟斤拷锟斤拷锟街碉拷锟揭伙拷锟斤拷锟斤拷筒锟斤拷锟斤拷实锟斤拷锟斤拷锟斤拷. 锟斤拷: public Map<String, Buyer> getNames(){}
     *  
     * @param Method method 锟斤拷锟斤拷
     * @return 锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟斤拷<code>Object.class</code>
     */ 
    @SuppressWarnings("rawtypes")
	public static Class getMethodGenericReturnType(Method method) {
    	return getMethodGenericReturnType(method, 0);
    }
    
	/**  
     * 通锟斤拷锟斤拷,锟斤拷梅锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟絠ndex锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫凤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷: public void add(Map<String, Buyer> maps, List<String> names){}
     *  
     * @param Method method 锟斤拷锟斤拷
     * @param int index 锟节硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
     * @return 锟斤拷锟斤拷锟斤拷锟侥凤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷图锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟截空硷拷锟斤拷
     */ 
    @SuppressWarnings("rawtypes")
	public static List<Class> getMethodGenericParameterTypes(Method method, int index) {
    	List<Class> results = new ArrayList<Class>();
    	Type[] genericParameterTypes = method.getGenericParameterTypes();
    	if (index >= genericParameterTypes.length ||index < 0) {
             throw new RuntimeException();
        } 
    	Type genericParameterType = genericParameterTypes[index];
    	if(genericParameterType instanceof ParameterizedType){
    	     ParameterizedType aType = (ParameterizedType) genericParameterType;
    	     Type[] parameterArgTypes = aType.getActualTypeArguments();
    	     for(Type parameterArgType : parameterArgTypes){
    	         Class parameterArgClass = (Class) parameterArgType;
    	         results.add(parameterArgClass);
    	     }
    	     return results;
    	}
    	return results;
    }
	/**  
     * 通锟斤拷锟斤拷,锟斤拷梅锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟揭伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷蟹锟斤拷筒锟斤拷锟斤拷实锟斤拷锟斤拷锟斤拷. 锟斤拷: public void add(Map<String, Buyer> maps, List<String> names){}
     *  
     * @param Method method 锟斤拷锟斤拷
     * @return 锟斤拷锟斤拷锟斤拷锟侥凤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷图锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟截空硷拷锟斤拷
     */ 
    @SuppressWarnings("rawtypes")
	public static List<Class> getMethodGenericParameterTypes(Method method) {
    	return getMethodGenericParameterTypes(method, 0);
    }
	/**  
     * 通锟斤拷锟斤拷,锟斤拷锟紽ield锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷: public Map<String, Buyer> names;
     *  
     * @param Field field 锟街讹拷
     * @param int index 锟斤拷锟酵诧拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷0锟斤拷始.
     * @return 锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟斤拷<code>Object.class</code>
     */ 
    @SuppressWarnings("rawtypes")
	public static Class getFieldGenericType(Field field, int index) {
    	Type genericFieldType = field.getGenericType();
    	
    	if(genericFieldType instanceof ParameterizedType){
    	    ParameterizedType aType = (ParameterizedType) genericFieldType;
    	    Type[] fieldArgTypes = aType.getActualTypeArguments();
    	    if (index >= fieldArgTypes.length || index < 0) { 
    	    	throw new RuntimeException();
            } 
    	    return (Class)fieldArgTypes[index];
    	}
    	return Object.class;
    }
	/**  
     * 通锟斤拷锟斤拷,锟斤拷锟紽ield锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷: public Map<String, Buyer> names;
     *  
     * @param Field field 锟街讹拷
     * @param int index 锟斤拷锟酵诧拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷0锟斤拷始.
     * @return 锟斤拷锟酵诧拷锟斤拷锟绞碉拷锟斤拷锟斤拷锟� 锟斤拷锟矫伙拷锟绞碉拷锟絇arameterizedType锟接口ｏ拷锟斤拷锟斤拷支锟街凤拷锟酵ｏ拷锟斤拷锟斤拷直锟接凤拷锟斤拷<code>Object.class</code>
     */ 
    @SuppressWarnings("rawtypes")
	public static Class getFieldGenericType(Field field) {
    	return getFieldGenericType(field, 0);
    }
}
