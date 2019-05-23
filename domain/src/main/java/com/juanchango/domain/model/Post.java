package com.juanchango.domain.model;

/**
 * ViewModelPost, notice userId is privateFinal and it have to be initialized from constructor
 */
public class Post {

    private final int postId;

    private int userId;

    private String title;

    private String body;

    public Post(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
