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

    //获取最热问题
    public static final String GET_HOT_QUESTION = ROOT + "/front/question_hottest";

    //获得一个问题的所有答案
    public static final String GET_ALL_ANSWERS = ROOT + "/front/answers?question_id=";

    //获得一个答案的详细信息
    public static final String GET_ONE_ANSWER = ROOT + "/front/answers";

    //新增答案
    public static final String POST_ONE_ANSWER = ROOT + "/front/answers";

    //新增问题
    public static final String POST_ONE_QUESTION = ROOT + "/front/questions";

    //是否可以进行点赞
    public static final String POST_CHECK_PRISE = ROOT + "/front/association/judge_praise";

    //搜索问题
    public static final String POST_SEARCH_QUESTION = ROOT + "/front/questions/search";

    //点赞
    public static final String POST_PRISE = ROOT + "/front/answers/praise";

    //推荐问题
    public static final String GET_RECOMMMENT = ROOT + "/front/questions/recommend_info";

    //推荐问题
    public static final String GET_MYANSWER = ROOT + "/front/answers/user_already_answer";

    //推荐问题
    public static final String GET_QUESTION = ROOT + "/front/questions/user_already_ask";


}
