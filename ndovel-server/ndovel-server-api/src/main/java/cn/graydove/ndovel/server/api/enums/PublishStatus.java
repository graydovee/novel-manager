package cn.graydove.ndovel.server.api.enums;

/**
 * 书籍状态
 * @author graydove
 */
public enum PublishStatus {

    /**
     * 保存
     */
    SAVE("save"),

    /**
     * 审核
     */
    REVIEW("review"),

    /**
     * 发布
     */
    RELEASE("release");

    private String status;

    public String getStatus() {
        return status;
    }

    PublishStatus(String status) {
        this.status = status;
    }
}
