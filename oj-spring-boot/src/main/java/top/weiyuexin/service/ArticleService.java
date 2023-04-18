package top.weiyuexin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.weiyuexin.pojo.Article;

/**
 * @PackageName: top.weiyuexin.service
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: ArticleService
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:26
 */
public interface ArticleService extends IService<Article> {
    IPage<Article> getPage(Integer currentPage, Integer pageSize, Article article);
}
