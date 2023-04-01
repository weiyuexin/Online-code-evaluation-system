package top.weiyuexin.pojo.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    //定义两个常量，成功的code是200，失败的是400
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = 400;
    //日期格式类：定义你需要的日期格式
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

    private Integer code;
    private Object data;
    private String msg;
    private String time;

    //把构造方法私有,不让别人new该类
    private R() {
    }

    //无参数的成功方法，只返回成功代码“200”
    public static R success() {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        //当前时间毫秒数转换为日期
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String currentDate = simpleDateFormat.format(date);
        r.setTime(currentDate);
        return r;
    }

    //有参数的成功方法，返回成功代码“200” 和 数据data
    public static R success(Object data) {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setData(data);
        //当前时间毫秒数转换为日期
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String currentDate = simpleDateFormat.format(date);
        r.setTime(currentDate);
        return r;
    }

    public static R success(String msg) {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setMsg(msg);
        //当前时间毫秒数转换为日期
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String currentDate = simpleDateFormat.format(date);
        r.setTime(currentDate);
        return r;
    }

    public static R success(Object data, String msg) {
        R r = new R();
        r.setCode(SUCCESS_CODE);
        r.setData(data);
        r.setMsg(msg);
        //当前时间毫秒数转换为日期
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String currentDate = simpleDateFormat.format(date);
        r.setTime(currentDate);
        return r;
    }

    //失败的方法，返回自定义错误信息 和 错误代码“-1”
    public static R error(String msg) {
        R r = new R();
        r.setCode(ERROR_CODE);
        r.setMsg(msg);
        //当前时间毫秒数转换为日期
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String currentDate = simpleDateFormat.format(date);
        r.setTime(currentDate);
        return r;
    }

    //失败的方法，返回自定义错误信息 和 自定义错误代码
    public static R error(Integer code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        //当前时间毫秒数转换为日期
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        String currentDate = simpleDateFormat.format(date);
        r.setTime(currentDate);
        return r;
    }

}
