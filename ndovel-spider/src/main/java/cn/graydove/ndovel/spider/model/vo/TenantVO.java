package cn.graydove.ndovel.spider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class TenantVO implements Serializable {

    private static final long serialVersionUID = -585127985019075697L;

    private String uid;

    private String publicKey;

    private String publishAddress;
}
