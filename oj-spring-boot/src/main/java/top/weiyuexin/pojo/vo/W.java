package top.weiyuexin.pojo.vo;

import lombok.Data;

@Data
public class W {
    Integer code;
    String msg;
    Integer count;
    Object data;

    public W() {

    }

    public W(Integer code) {
        this.code = code;
    }

    public W(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public W(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public W(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public W(String msg) {
        this.msg = msg;
    }

    public W(Integer code, Integer count, Object data) {
        this.code = code;
        this.count = count;
        this.data = data;
    }
}
