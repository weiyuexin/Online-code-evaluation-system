package top.weiyuexin.pojo;

import lombok.Data;

@Data
public class File {
    private Integer id;
    private String originalFilename;
    private String newFilename;
    private String url;
    private String date;
}
