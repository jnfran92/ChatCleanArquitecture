package com.speakliz.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Object created to test Json Request:
 * It is supposed to consume the public REST API: https://jsonplaceholder.typicode.com/posts
 */
public class PostEntity {

    @SerializedName("postId")
    private int postId;

    @SerializedName("userId")
    private int userId;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

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
