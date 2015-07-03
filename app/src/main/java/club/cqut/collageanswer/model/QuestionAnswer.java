package club.cqut.collageanswer.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 问题及对应答案实体
 * “我的回答”
 * Created by Howe on 2015/7/2.
 */
public class QuestionAnswer {

    private int questionId;            //问题id
    private String questionCover;      //提问者的头像
    private String questionName;       //提问者昵称
    @JsonProperty("questionContent")
    private String content;            //问题内容
    @JsonProperty("content")
    private String answer;             //回答的内容

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionCover() {
        return questionCover;
    }

    public void setQuestionCover(String questionCover) {
        this.questionCover = questionCover;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}



