package club.cqut.collageanswer.model;

import java.util.Date;

/**
 * 好友实体
 * Created by fenghao on 2015/6/28.
 */
public class Friend {
    //TODO 根据后台返回修改属性
    private int id;
    private String email;
    private String username;
    private String userSign;
    private Boolean sex;
    private String hobby;
    private String headImage;
    private String fellow;
    private String subCollage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getFellow() {
        return fellow;
    }

    public void setFellow(String fellow) {
        this.fellow = fellow;
    }

    public String getSubCollage() {
        return subCollage;
    }

    public void setSubCollage(String subCollage) {
        this.subCollage = subCollage;
    }
}
