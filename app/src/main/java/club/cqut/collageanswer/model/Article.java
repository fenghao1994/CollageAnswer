package club.cqut.collageanswer.model;

/**
 * 文章实体
 * Created by fenghao on 2015/6/28.
 */
public class Article {
    private int id;
    private String articleType;//文章类型
    private String author;//作者
    private String link;//根目录
    private String moreLink;//网站详细链接
    private String intor;//网站简介

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }

    public String getIntor() {
        return intor;
    }

    public void setIntor(String intor) {
        this.intor = intor;
    }
}
