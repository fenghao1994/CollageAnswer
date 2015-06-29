package club.cqut.collageanswer.model;

import java.util.Date;

/**
 * 好友实体
 * Created by fenghao on 2015/6/28.
 */
public class Friend {
    //TODO 根据后台返回修改属性
    private int id;
    private String phone;
    private String email;
    private String username;
    private String realname;
    private String pingyin;
    private String stuNum;
    private String userSign;
    private Boolean sex;
    private Date brith;
    private String hobby;
    private String headImage;
    private String schoolEmail;
    private String fellow;
    private String classes;
    private String defaultOrg;
    private String subCollage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
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

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
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

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getFellow() {
        return fellow;
    }

    public void setFellow(String fellow) {
        this.fellow = fellow;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getDefaultOrg() {
        return defaultOrg;
    }

    public void setDefaultOrg(String defaultOrg) {
        this.defaultOrg = defaultOrg;
    }

    public String getSubCollage() {
        return subCollage;
    }

    public void setSubCollage(String subCollage) {
        this.subCollage = subCollage;
    }
}
