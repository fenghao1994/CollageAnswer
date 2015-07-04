package club.cqut.collageanswer.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * 问题实体
 * Created by fenghao on 2015/6/28.
 */
@JsonIgnoreProperties({"created_at", "updated_at", "avatar_file_name","avatar_content_type","avatar_file_size", "avatar_updated_at"})
public class Question implements Serializable{


    private int id; //问题的id
    @JsonProperty("user_id")
    private int userId; //提问者的id
    @JsonProperty("username")
    private String userName;//提问者的名字
    @JsonProperty("head_image")
    private String headImage; //提问者的头像
//    private int answerNum;//回答数
    private String title;
    private String content;
    private String label; //问题标签 各个标签按逗号隔开
    @JsonProperty("read_num")
    private String readNum; //阅读量
    @JsonProperty("owner_id")
    private int ownerId;//上一级id ，为0表示没有追问

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

//    public int getAnswerNum() {
//        return answerNum;
//    }
//
//    public void setAnswerNum(int answerNum) {
//        this.answerNum = answerNum;
//    }
}
