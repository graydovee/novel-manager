package cn.graydove.ndovel.spider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class BroadCastDTO implements Serializable {

    private static final long serialVersionUID = -3807555038054230517L;

    private String senderUid;

    private String cryptoData;

}
