package top.weiyuexin.pojo.vo;

import lombok.Data;

/**
 * @PackageName: top.weiyuexin.pojo.vo
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: R
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/8 18:54
 */
@Data
public class R {
    //定义两个常量，成功的code是200，失败的是-1
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "-1";

    private String code;
    private Object data;
    private String msg;
    private Long time;
    //把构造方法私有,不让别人new该类
    private R() {}
    //无参数的成功方法，只返回成功代码“200”
    public static R success() {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setTime(System.currentTimeMillis());
        return r;
    }
    //有参数的成功方法，返回成功代码“200” 和 数据data
    public static R success(Object data) {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setData(data);
        r.setTime(System.currentTimeMillis());
        return r;
    }
    //失败的方法，返回自定义错误信息 和 错误代码“-1”
    public static R error(String msg) {
        R r = new R();
        r.setCode(ERROR_CODE);
        r.setMsg(msg);
        r.setTime(System.currentTimeMillis());
        return r;
    }

    //失败的方法，返回自定义错误信息 和 自定义错误代码
    public static R error(String code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setTime(System.currentTimeMillis());
        return r;
    }

}
