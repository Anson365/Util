package com.anson.util.beanUtil;


import com.anson.util.external.command.CommandAction;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ludao on 16/2/4.
 */
public class BeanUtils {

    /**
     * copy bean property
     * @param source
     * @param target
     */
    public static void copyProperties(Object source,Object target){
        try {
            Class targetClass = target.getClass();
            Class sourceClass = source.getClass();
//          get targetClass's fields
            Field[] targetFields = targetClass.getDeclaredFields();
            for (Field field : targetFields) {
                Field sourceField = getBeanField(sourceClass,field.getName());
//              source bean contain this field
                if(sourceField==null){
                    continue;
                }
                sourceField.setAccessible(true);
                field.setAccessible(true);

                Object sourceVal = sourceField.get(source);   //get the value of source field
                field.set(target,sourceVal);             //set the value of target field
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
     }

    /**
     * when source field is not exists ,return null instead of throw exception
     * @param clazz
     * @param fieldName
     * @return
     */
    private static Field getBeanField(Class clazz,String fieldName){
        Field result = null;
        try {
            result = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return result;
        }
        return result;
    }



    public static Boolean isSimpleBeanEmpty(Class clazz,Object object ){
        Boolean result = true;
        if(!(object.getClass()==clazz||clazz.isAssignableFrom(object.getClass()))){
            throw new IllegalAccessError(String.format("%s is not instance of %s",object.getClass().getName(),clazz.getName()));
        }
        if(object == null){
            return result;
        }else {
            Field[] fields = clazz.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                if(Modifier.isStatic(field.getModifiers())||Modifier.isFinal(field.getModifiers())
                        || Modifier.isAbstract(field.getModifiers())){
                    continue;
                }else {
                    try {
                        Object value = field.get(object);
                        if (value != null){
                            result = false;
                            return result;
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            Class parentClass = clazz.getSuperclass();
            if(parentClass.getName().equalsIgnoreCase("java.lang.Object")){
                return true;
            }
            result = isSimpleBeanEmpty(parentClass,object);
            return result;
        }
    }

    /**
     * change list bean to map
     * @param beanList
     * @return
     */
    public static Map<String,Object> changeBean2Map (List<Object> beanList){
        Map<String,Object> result = new HashMap<String, Object>();
        if(beanList==null||beanList.size()<1){
            return result;
        }else {
            for(Object bean:beanList){
                Class clazz = bean.getClass();
                result = changeBean2Map(result,clazz,bean);
            }
            return result;
        }
    }

    /**
     * change bean to map
     * @param map
     * @param clazz
     * @param bean
     * @return
     */
    public static Map<String,Object> changeBean2Map(Map<String,Object> map,Class clazz,Object bean){
        if(bean == null){
            return map;
        }
        if(!(bean.getClass()==clazz||clazz.isAssignableFrom(bean.getClass()))){
            throw new IllegalAccessError(String.format("%s is not instance of %s",bean.getClass().getName(),clazz.getName()));
        }
        Field[] fields = clazz.getDeclaredFields();
        try {
            for(Field field:fields){
                field.setAccessible(true);
                if(Modifier.isStatic(field.getModifiers())||Modifier.isAbstract(field.getModifiers())
                        ||Modifier.isFinal(field.getModifiers())){
                    continue;
                }
                String key = field.getName();
                Object value = field.get(bean);
                if(value != null){
                    map.put(key,value);
                }
            }
            if(!"java.lang.Object".equalsIgnoreCase(clazz.getSuperclass().getName())){
                map = changeBean2Map(map,clazz.getSuperclass(),bean);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * target object'field do change
     * @param object
     * @param targetFieldClass
     * @param commandAction
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static <T> T doAssignField(T object,Class targetFieldClass,CommandAction commandAction) throws IllegalAccessException {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            Class fieldName = field.getType();
            if(fieldName.equals(targetFieldClass)){
                Object result = commandAction.execute(field.get(object));
                field.set(object,result);
            }
        }
        return object;
    }

}
