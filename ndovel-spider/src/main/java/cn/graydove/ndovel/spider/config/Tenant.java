package cn.graydove.ndovel.spider.config;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author graydove
 */
@Data
public class Tenant {

    private String uid;

    private String publicKey;

    private String publishAddress;

    Map<String, Tenant> parents = new ConcurrentHashMap<>();

    Map<String, Tenant> children = new ConcurrentHashMap<>();
}
