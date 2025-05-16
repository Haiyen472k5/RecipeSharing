package com.recipeshare.backend.Comment;

public class CommentDTO {
    private String commenterUsername;
    private String content;

    public CommentDTO() {}

    public CommentDTO(String commenterUsername, String content) {
        this.commenterUsername = commenterUsername;
        this.content = content;
    }

    public String getCommenterUsername() {
        return commenterUsername;
    }

    public void setCommenterUsername(String commenterUsername) {
        this.commenterUsername = commenterUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
