package top.weiyuexin.pojo;

/**
 * @PackageName: top.weiyuexin.pojo
 * @ProjectName: oj-spring-boot
 * @ClassName: FilePath
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/4/8 19:29
 */
public enum FilePath {
    C("/data/code/c/"), CPP("/data/code/cpp/"),
    JAVA("/data/code/java/"), PYTHON("/data/code/python/"),
    GOLANG("/data/code/golang/"), JAVASCRIPT("/data/code/js/");
    // 以上是枚举的成员，必须先定义，而且使用分号结束
    private final String path;

    private FilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
