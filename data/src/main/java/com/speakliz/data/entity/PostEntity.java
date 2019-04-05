package com.speakliz.data.entity;

import com.google.gson.annotations.SerializedName;

public class PostEntity {

    @SerializedName("userId")
    int userId;

    @SerializedName("postId")
    int postId;

    @SerializedName("title")
    String title;

    @SerializedName("body")
    String body;

    public PostEntity() {
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
