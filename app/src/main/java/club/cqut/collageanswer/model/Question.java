package club.cqut.collageanswer.model;

/**
 * 问题实体
 * Created by fenghao on 2015/6/28.
 */
public class Question {

    private int id; //问题的id
    private int userId; //提问者的id
    private String title;
    private String content;
    private String label; //问题标签 各个标签按逗号隔开
    private String readNum; //阅读量
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
}
