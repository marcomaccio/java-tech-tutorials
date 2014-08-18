package com.nagra.cms.pes.client;

public class ToDolistitem {


    private String assetName;
    private String status;
    private int progress;

    public ToDolistitem(){}

    public ToDolistitem(String assetName, String status, int progress) {
        this.assetName = assetName;
        this.status = status;
        this.progress = progress;
    }

    String getAssetName() {
        return assetName;
    }

    void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    int getProgress() {
        return progress;
    }

    void setProgress(int progress) {
        this.progress = progress;
    }

}