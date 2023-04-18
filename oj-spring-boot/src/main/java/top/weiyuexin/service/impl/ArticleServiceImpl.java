package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.weiyuexin.mapper.ArticleMapper;
import top.weiyuexin.pojo.Article;
import top.weiyuexin.service.ArticleService;

/**
 * @PackageName: top.weiyuexin.service.impl
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: ArticleServiceImpl
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param article
     * @return
     */
    @Override
    public IPage<Article> getPage(Integer currentPage, Integer pageSize, Article article) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Article::getTime);
        lqw.like(article.getAuthorId() != null, Article::getAuthorId, article.getAuthorId());
        lqw.like(Strings.isNotEmpty(article.getTitle()), Article::getTitle, article.getTitle());
        IPage<Article> page = new Page<>(currentPage, pageSize);
        articleMapper.selectPage(page, lqw);
        return page;
    }
}
