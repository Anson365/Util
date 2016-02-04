package com.anson.util.beanUtil;


import java.lang.reflect.Field;

/**
 * Created by ludao on 16/2/4.
 */
public class BeanUtils {

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
}
