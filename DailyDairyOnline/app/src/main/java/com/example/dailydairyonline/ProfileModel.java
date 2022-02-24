package com.example.dailydairyonline;

public class ProfileModel {

    String Name;
    String Nickname;
    String Lives;
    String From;
    String Work;
    String Education;
    String Status;
    String Bday;
    String Email;

    public ProfileModel() {
    }

    public ProfileModel(String name, String nickname, String lives, String from, String work, String education, String status, String bday, String email) {
        this.Name = name;
        this.Nickname = nickname;
        this.Lives = lives;
        this.From = from;
        this.Work = work;
        this.Education = education;
        this.Status = status;
        this.Bday = bday;
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        this.Nickname = nickname;
    }

    public String getLives() {
        return Lives;
    }

    public void setLives(String lives) {
        this.Lives = lives;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        this.From = from;
    }

    public String getWork() {
        return Work;
    }

    public void setWork(String work) {
        this.Work = work;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        this.Education = education;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getBday() {
        return Bday;
    }

    public void setBday(String bday) {
        this.Bday = bday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
}
