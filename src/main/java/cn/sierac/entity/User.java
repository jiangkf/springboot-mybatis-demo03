package cn.sierac.entity;

/**
 * Created by Jack on 2017/7/26.
 */
public class User {

    /**
     * id
     */
    private Integer id ;
    /**
     * 用户名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;

    public User(Integer id, String loginName, String password) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
