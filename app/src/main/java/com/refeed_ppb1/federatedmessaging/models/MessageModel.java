package com.refeed_ppb1.federatedmessaging.models;

import com.google.gson.annotations.SerializedName;

public class MessageModel {
    @SerializedName("ID")
    public String id;

    @SerializedName("UpdatedAt")
    public String updatedAt;

    @SerializedName("Source")
    public String source;

    @SerializedName("Sender")
    public String sender;

    @SerializedName("Body")
    public String body;

    @SerializedName("IsAnnouncement")
    public boolean isAnnouncement;

    public MessageModel(String id, String updatedAt, String source, String sender, String body, boolean isAnnouncement) {
        this.id = id;
        this.updatedAt = updatedAt;
        this.source = source;
        this.sender = sender;
        this.body = body;
        this.isAnnouncement = isAnnouncement;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "id='" + id + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", source='" + source + '\'' +
                ", sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                ", isAnnouncement='" + isAnnouncement + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getSource() {
        return source;
    }

    public String getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public boolean getIsAnnouncement() {
        return isAnnouncement;
    }
}
