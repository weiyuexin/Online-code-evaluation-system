package top.weiyuexin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
