package com.ndovel.ebook.model.dto.base;

import com.ndovel.ebook.model.entity.base.BaseEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.ndovel.ebook.utils.ReflectUtils.*;

@SuppressWarnings("unchecked")
public interface BaseDTO<U extends BaseDTO<U, T>, T>{


    default <D extends BaseDTO> D init(T entity) {

        Map<String,Object> map = getFieldNameAndValue(entity);

        setValues(this,map);

        return (D) this;
    }

    default T writeToDomain() {
        Type type = Objects.requireNonNull(getParameterizedType(BaseDTO.class, getClass())).getActualTypeArguments()[1];
        T domain = newBean((Class<T>) type);

        Map<String,Object> map = getFieldNameAndValue(this);

        setValuesToDOMAIN(domain, map, this.getClass());

        return domain;
    }

    default List<U> writeToDTOList(List<T> list){
        Type type = Objects.requireNonNull(getParameterizedType(BaseDTO.class, getClass())).getActualTypeArguments()[0];
        Class<U> clazz = (Class<U>) type;
        List<U> l = new ArrayList<>();
        U u;
        for(T t: list){
            u = newBean(clazz).init(t);
            l.add(u);
        }
        return l;
    }

    default List<T> writeToDomainList(List<? extends BaseDTO<U, T>> list){
        List<T> l = new ArrayList<>();
        for(BaseDTO<U,T> each: list){
            l.add(each.writeToDomain());
        }
        return l;
    }

}

