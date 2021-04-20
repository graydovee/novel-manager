package cn.graydove.ndovel.spider.model;

import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class CryptoData<T> implements Serializable {

    private static final long serialVersionUID = -4766193949440806190L;

    private String senderUid;

    private String sourceUid;

    private T data;

}
