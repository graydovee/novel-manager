package cn.graydove.ndovel.common;

/**
 * 创建ObjectMapper，但不引入，防止未依赖时报错
 * @author graydove
 */
public interface ObjectMapperFactory {

    /**
     * 创建ObjectMapper
     * @return ObjectMapper的实例
     */
    Object createObjectMapper();

    /**
     * 判断类是否是ObjectMapper
     * @param object object
     * @return object是否是ObjectMapper
     */
    Boolean isObjectMapper(Object object);
}
