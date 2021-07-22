package com.example.prdocutservice.rabbitmq;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String id;
    private Date createdAt;
    private Boolean isSeen;
    private String messageText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public String getMessage() {
        return messageText;
    }

    public void setMessage(String message) {
        this.messageText = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", isSeen=" + isSeen +
                ", message='" + messageText + '\'' +
                '}';
    }
}
