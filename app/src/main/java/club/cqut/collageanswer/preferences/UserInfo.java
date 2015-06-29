package club.cqut.collageanswer.preferences;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by fenghao on 2015/6/27.
 */

/**
 * 个人信息
 */
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface UserInfo {

    /**
     * 用户id
     * 判断是否登陆
     * @return
     */
    @DefaultInt(-1)
    int id();

    /**
     *用户名称
     * @return
     */
    String name();

    /**
     * 用户登录令牌
     * @return
     */
    String token();

    /**
     * 是否显示启动页
     * @return
     */
    @DefaultBoolean(false)
    boolean guide();
}
