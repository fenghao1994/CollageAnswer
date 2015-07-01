package club.cqut.collageanswer.util.http;

/**
 * Http地址
 * Created by fenghao on 2015/6/27.
 */
public class HttpUrl {

    //根地址
    public static final String ROOT = "http://qa.tavern.name";

    //注册
    public static final String POST_SIGN_UP = ROOT + "/front/users/sign_up";

    //登陆
    public static final String POST_LOGIN = ROOT + "/front/users/validate";

    //获取最新问题
    public static final String GET_NEW_QUESTION = ROOT + "/front/questions";

    //获取最新问题
    public static final String GET_HOT_QUESTION = ROOT + "/front/question_hottest";
}
