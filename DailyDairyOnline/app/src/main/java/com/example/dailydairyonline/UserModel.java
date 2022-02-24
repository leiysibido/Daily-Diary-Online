package com.example.dailydairyonline;

public class UserModel {

    String Date;
    String Privacy;
    String Title;
    String Description;
    String image;
    private boolean expanded;

    public UserModel() {
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public UserModel(String date, String privacy, String title, String description, String image) {
        Date = date;
        Privacy = privacy;
        Title = title;
        Description = description;
        this.image = image;
        this.expanded = false;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPrivacy() {
        return Privacy;
    }

    public void setPrivacy(String privacy) {
        Privacy = privacy;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
