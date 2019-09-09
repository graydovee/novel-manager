package com.ndovel.ebook.utils;

import com.ndovel.ebook.model.dto.base.BaseDTO;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SuppressWarnings("unchecked")
public class ReflectUtils {

    private ReflectUtils(){
    }

    public static List<PropertyDescriptor> getDescriptors(Object template){
        BeanInfo entityBeanInfo;
        try {
            entityBeanInfo = Introspector.getBeanInfo(template.getClass());
        } catch (IntrospectionException e) {
            log.error(e.getMessage(),e);
            return Collections.emptyList();
        }

        return Arrays.stream(entityBeanInfo.getPropertyDescriptors()).filter(p -> {
            String name = p.getName();
            return !"class".equals(name);
        }).collect(Collectors.toList());

    }

    public static Map<String, Object> getFieldNameAndValue(Object template){
        Map<String,Object> map = new HashMap<>();
        for(PropertyDescriptor descriptor : getDescriptors(template)){
            Method readMethod = descriptor.getReadMethod();
            Object o = null;
            try {
                o = readMethod.invoke(template);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error(e.getMessage(),e);
            }
            map.put(descriptor.getName(),o);
        }
        return map;
    }

    public static Type[] getCollectionType(Class c, String fieldName){
        Field listField;
        Type[] listActualTypeArguments = new Type[0];
        try {
            listField = c.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            log.error(e.getMessage(),e);
            return listActualTypeArguments;
        }
        //获取 fieldName 字段的泛型参数
        ParameterizedType listGenericType = (ParameterizedType) listField.getGenericType();
        listActualTypeArguments = listGenericType.getActualTypeArguments();
        return listActualTypeArguments;
    }

    public static void setValues(Object obj, Map<String, Object> map){
        for (PropertyDescriptor descriptor : ReflectUtils.getDescriptors(obj)) {
            Method writeMethod = descriptor.getWriteMethod();
            Class clazz = descriptor.getPropertyType();
            try {
                Object value = map.get(descriptor.getName());

                if(BaseDTO.class.isAssignableFrom(clazz)){
                    //如果属性是DOMAIN则转化为相应DTO
                    BaseDTO baseDTO = newBean((Class<BaseDTO>) clazz);
                    if(baseDTO!=null)
                        writeMethod.invoke(obj, baseDTO.init(value));
                } else if(Collection.class.isAssignableFrom(clazz)){
                    //如果属性是Collection,且容器内的值的属性类型为DOMAIN,则转化为相应DTO的容器

                    Class type = (Class) getCollectionType(obj.getClass(),descriptor.getName())[0];
                    if(BaseDTO.class.isAssignableFrom(type)){
                        Collection domainCollection = (Collection) value;
                        Collection dtoCollection = (Collection)descriptor.getReadMethod().invoke(obj);
                        for (Object o : domainCollection){
                            BaseDTO baseDTO = newBean((Class<BaseDTO>) type);
                            if (baseDTO != null)
                                dtoCollection.add(baseDTO.init(o));
                        }
                    }else {
                        writeMethod.invoke(obj, value);
                    }
                } else {
                    writeMethod.invoke(obj, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error(e.getMessage(),e);
            }
        }
    }

    public static void setValuesToDOMAIN(Object obj, Map<String, Object> map, Class clazz){
        for (PropertyDescriptor descriptor : ReflectUtils.getDescriptors(obj)) {
            Method writeMethod = descriptor.getWriteMethod();
            try {
                Object value = map.get(descriptor.getName());
                if(value==null)
                    continue;

                if(BaseDTO.class.isAssignableFrom(value.getClass())){
                    //如果属性是DTO则转化为相应DOMAIN
                    value = ((BaseDTO)value).writeToDomain();
                    if(value!=null)
                        writeMethod.invoke(obj, value);
                } else if(Collection.class.isAssignableFrom(value.getClass())){
                    //如果属性是Collection,且容器内的值的属性类型为DTO,则转化为相应DOMAIN的容器

                    Class type = (Class) getCollectionType(clazz,descriptor.getName())[0];
                    if(BaseDTO.class.isAssignableFrom(type)){
                        Collection dtoCollection = (Collection) value;
                        Collection domainCollection = (Collection)descriptor.getReadMethod().invoke(obj);
                        for (Object o : dtoCollection){
                            Object baseEntity = ((BaseDTO)o).writeToDomain();
                            if (baseEntity != null)
                                domainCollection.add(baseEntity);
                        }
                    }else {

                        writeMethod.invoke(obj, value);
                    }
                } else {
                    writeMethod.invoke(obj, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error(e.getMessage(),e);
            }
        }
    }

    /**
     * 通过class对象实例化一个对象
     */
    public static <T> T newBean(Class<T> clazz){
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(),e);
        }
        return t;
    }

    /**
     * 获得泛型的class对象
     */
    public static ParameterizedType getParameterizedType( Class<?> interfaceType, Class<?> implementationClass) {
        if (implementationClass == null) {
            // If the super class is Object parent then return null
            return null;
        }

        // Get parameterized type
        ParameterizedType currentType = getParameterizedType(interfaceType, implementationClass.getGenericInterfaces());

        if (currentType != null) {
            // return the current type
            return currentType;
        }

        Class<?> superclass = implementationClass.getSuperclass();

        return getParameterizedType(interfaceType, superclass);
    }

    public static ParameterizedType getParameterizedType(Class<?> superType, Type... genericTypes) {
        ParameterizedType currentType = null;

        for (Type genericType : genericTypes) {
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                if (parameterizedType.getRawType().getTypeName().equals(superType.getTypeName())) {
                    currentType = parameterizedType;
                    break;
                }
            }
        }

        return currentType;
    }
}
