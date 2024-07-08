package com.example.blogapp1.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class HomeModel {

    private String userName, profileImage,postImage, uid,comments,imageUrl, description, id;
    @ServerTimestamp
    private Date timestamp;

    private int likeCount;

    public HomeModel() {

    }

    public HomeModel(String userName, String profileImage, String postImage, String uid, String comments, String imageUrl, String description, String id, Date timestamp, int likeCount) {
        this.userName = userName;
        this.profileImage = profileImage;
        this.postImage = postImage;
        this.uid = uid;
        this.comments = comments;
        this.imageUrl = imageUrl;
        this.description = description;
        this.id = id;
        this.timestamp = timestamp;
        this.likeCount = likeCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
