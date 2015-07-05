package club.cqut.collageanswer.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 通知的实体
 * 根据通知的类型判别是什么通知
 * Created by fenghao on 2015/6/28.
 */
@JsonIgnoreProperties({"created_at", "updated_at"})
public class Notice {


    private int id;
    private String title;
    private String content;
    @JsonProperty("notice_type")
    private int type;//通知类型  1：相互认证 2：学院通知 3：学校通知 4：协会通知 5：老乡会通知
    private int extra_id;//附加信息id
    private int extra_type;//附加信息类容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getExtra_id() {
        return extra_id;
    }

    public void setExtra_id(int extra_id) {
        this.extra_id = extra_id;
    }

    public int getExtra_type() {
        return extra_type;
    }

    public void setExtra_type(int extra_type) {
        this.extra_type = extra_type;
    }
}
