package club.cqut.collageanswer.model;

/**
 * 协会实体
 * Created by fenghao on 2015/6/28.
 */
public class Society {
    private int id;
    private String cover; //会徽
    private String name;//协会名称
    private Object master;//会长对象
    private String telephone;//协会联系方式
    private String introduction; //协会简介
    private String link;//协会主页链接


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
