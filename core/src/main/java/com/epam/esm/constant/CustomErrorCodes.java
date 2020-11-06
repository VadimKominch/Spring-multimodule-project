package com.epam.esm.constant;

public enum CustomErrorCodes {
    READ_ERROR(1),CREATE_ERROR(2),UPDATE_ERROR(3),DELETE_ERROR(4);
    private int errorId;

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    CustomErrorCodes(int errorId) {
        this.errorId = errorId;
    }
}
