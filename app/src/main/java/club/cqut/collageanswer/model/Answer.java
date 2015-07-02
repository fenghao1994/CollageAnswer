package club.cqut.collageanswer.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * 答案
 * Created by fenghao on 2015/6/28.
 */
@JsonIgnoreProperties({"created_at", "updated_at"})
public class Answer implements Serializable{

    private int id;//答案id
    @JsonProperty("user_id")
    private int userId;//回答人id
    @JsonProperty("content")
    private String context;
    @JsonProperty("praise_num")
    private int priseNum;//点赞数
    @JsonProperty("user_role")
    private String userRole; //回答人的角色
    @JsonProperty("question_id")
    private int questionId;
    @JsonProperty("head_image")
    private String headImage;//回答人头像
    @JsonProperty("user_sign")
    private String userSign;//回答人的个性签名
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPriseNum() {
        return priseNum;
    }

    public void setPriseNum(int priseNum) {
        this.priseNum = priseNum;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }
}
