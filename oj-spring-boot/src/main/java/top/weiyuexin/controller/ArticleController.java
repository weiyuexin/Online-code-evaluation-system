package top.weiyuexin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Article;
import top.weiyuexin.pojo.User;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.pojo.vo.W;
import top.weiyuexin.service.ArticleService;
import top.weiyuexin.service.UserService;
import top.weiyuexin.utils.OutHtml;
import top.weiyuexin.utils.Time;

import java.util.List;

/**
 * @PackageName: top.weiyuexin.controller
 * @ProjectName: Online-code-evaluation-system
 * @ClassName: ArticleController
 * @Author: Weiyuexin
 * @Email: 3022422894@qq.com
 * @Date: 2023/2/7 21:24
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    /**
     * 根据id查询文章
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        Article article = articleService.getById(id);
        article.setReadNum(article.getReadNum() + 1);
        articleService.updateById(article);
        User user = userService.getById(article.getAuthorId());
        article.setAuthorName(user.getUsername());
        return R.success(article);
    }


    @GetMapping("/list")
    public W getPage(@RequestParam("page") Integer page,
                     @RequestParam("limit") Integer limit,
                     Article article) {
        IPage<Article> Ipage = articleService.getPage(page, limit, article);
        //如果当前页码值大于当前页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if (page > Ipage.getPages()) {
            Ipage = articleService.getPage(page, limit, article);
        }
        //过滤html标签
        List<Article> articles = Ipage.getRecords();
        for (int i = 0; i < articles.size(); i++) {
            String content = articles.get(i).getContent();
            OutHtml outHtml = new OutHtml();
            content = outHtml.delHTMLTag(content);
            if (content.length() > 100) {
                content = content.substring(0, 100);
            }
            articles.get(i).setContent(content);
            //根据作者id查询作者
            User user = userService.getById(articles.get(i).getAuthorId());
            if (user != null) {
                System.out.println(user.getUsername());
                articles.get(i).setAuthorName(user.getUsername());
            }
        }
        Ipage.setRecords(articles);
        return new W(0, (int) Ipage.getTotal(), Ipage.getRecords());
    }

    /**
     * 发布文章
     *
     * @param article
     * @return
     */
    @PostMapping("")
    public R addArticle(Article article) {
        article.setTime(Time.CurrentTime());
        return R.success(articleService.save(article));
    }

    /**
     * 修改文章
     *
     * @param article
     * @return
     */
    @PutMapping("")
    public R updateArticle(Article article) {
        return R.success(articleService.updateById(article));
    }

    /**
     * 删除文章
     *
     * @param article
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteArticle(@PathVariable("id") Integer id) {
        return R.success(articleService.removeById(id), "删除成功");
    }
}
