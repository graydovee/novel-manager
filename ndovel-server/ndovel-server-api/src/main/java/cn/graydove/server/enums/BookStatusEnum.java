package cn.graydove.server.enums;

public enum BookStatusEnum {
    SAVE("save"),
    REVIEW("review"),
    RELEASE("release");

    private String status;

    public String getStatus() {
        return status;
    }

    BookStatusEnum(String status) {
        this.status = status;
    }
}
