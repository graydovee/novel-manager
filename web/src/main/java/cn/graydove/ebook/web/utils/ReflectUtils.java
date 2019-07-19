package cn.graydove.ebook.web.utils;

import cn.graydove.ebook.web.model.dto.base.BaseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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

    public static void setValues(Object obj, Map<String, Object> map){
        for (PropertyDescriptor descriptor : ReflectUtils.getDescriptors(obj)) {
            Method writeMethod = descriptor.getWriteMethod();
            try {
                writeMethod.invoke(obj,map.get(descriptor.getName()));
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
