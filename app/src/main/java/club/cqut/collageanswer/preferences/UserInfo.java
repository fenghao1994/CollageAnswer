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
     *用户昵称
     * @return
     */
    String name();

    /**
     * 用户的真名
     */

    String realName();

    /**
     * 用户邮箱
     */
    String email();

    /**
     * 学号
     */
    String stuNumber();

    /**
     * 是否认证
     */
    @DefaultInt(-1)
    int attest();

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
