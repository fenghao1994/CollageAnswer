package club.cqut.collageanswer.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * 用户实体
 * Created by fenghao on 2015/6/28.
 */
@JsonIgnoreProperties({"name", "created_at", "updated_at", "enable", "remark", "organization_id", "parent_id", "society_id"})
@DatabaseTable(tableName = "users")
public class User {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_REALNAME = "realname";
    public static final String COLUMN_PINGYIN = "pingyin";
    public static final String COLUMN_STU_NUM = "stu_num"; //学号
    public static final String COLUMN_USER_SIGN = "user_sign";//个性签名
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_BIRTH = "birth";
    public static final String COLUMN_HOBBY = "hobby";//兴趣爱好
    public static final String COLUMN_HEAD_IMAGE = "head_image";
    public static final String COLUMN_SCHOOL_EMAIL = "school_email";
    public static final String COLUMN_FELLOW = "fellow";//老乡会
    public static final String COLUMN_CLASS = "class";
    public static final String COLUMN_DEFAULT_ORG = "default_org";//默认组织身份
    public static final String COLUMN_MAJOR = "major";//专业
    public static final String COLUMN_SUB_COLLAGE = "sub_collage";//学院
    public static final String COLUMN_TOKEN = "token";//登陆令牌


    @DatabaseField(id = true, columnName = COLUMN_ID)
    private int id;
    @DatabaseField(columnName = COLUMN_PHONE)
    private String phone;
    @DatabaseField(columnName = COLUMN_EMAIL)
    private String email;
    @DatabaseField(columnName = COLUMN_PASSWORD)
    private String password;
    @DatabaseField(columnName = COLUMN_USERNAME)
    private String username;
    @JsonProperty("real_name")
    @DatabaseField(columnName = COLUMN_REALNAME)
    private String realname;
    @DatabaseField(columnName = COLUMN_PINGYIN)
    @JsonProperty("pinyin")
    private String pingyin;
    @DatabaseField(columnName = COLUMN_STU_NUM)
    @JsonProperty("stu_num")
    private String stuNum;
    @DatabaseField(columnName = COLUMN_USER_SIGN)
    @JsonProperty("user_sign")
    private String userSign;
    @DatabaseField(columnName = COLUMN_SEX)
    private Boolean sex;
    @DatabaseField(columnName = COLUMN_BIRTH)
    private Date birth;
    @DatabaseField(columnName = COLUMN_HOBBY)
    private String hobby;
    @DatabaseField(columnName = COLUMN_HEAD_IMAGE)
    @JsonProperty("head_image")
    private String headImage;
    @DatabaseField(columnName = COLUMN_SCHOOL_EMAIL)
    @JsonProperty("school_image")
    private String schoolEmail;
    @DatabaseField(columnName = COLUMN_FELLOW)
    @JsonProperty("fellow_id")
    private String fellow;
    @DatabaseField(columnName = COLUMN_CLASS)
    @JsonProperty("clazz")
    private String classes;
    @DatabaseField(columnName = COLUMN_DEFAULT_ORG)
    @JsonProperty("default_org")
    private String defaultOrg;
    @DatabaseField(columnName = COLUMN_MAJOR)
    @JsonProperty("major_id")
    private String major;
    @DatabaseField(columnName = COLUMN_SUB_COLLAGE)
    @JsonProperty("sub_college_id")
    private String subCollage;
    @DatabaseField(columnName = COLUMN_TOKEN)
    @JsonProperty("authentication_token")
    private String token;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSubCollage() {
        return subCollage;
    }

    public void setSubCollage(String subCollage) {
        this.subCollage = subCollage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
