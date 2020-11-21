package cn.graydove.server.enums;

public enum  NovelStatusEnum {
    SAVE("save"),
    REVIEW("review"),
    RELEASE("release");

    private String status;

    public String getStatus() {
        return status;
    }

    NovelStatusEnum(String status) {
        this.status = status;
    }
}
