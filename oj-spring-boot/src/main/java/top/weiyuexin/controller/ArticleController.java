package top.weiyuexin.controller;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.weiyuexin.pojo.Article;
import top.weiyuexin.pojo.vo.R;
import top.weiyuexin.service.ArticleService;

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
    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") String id){
        return R.success(articleService.getById(id));
    }

    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R getPage(Integer start, Integer pageSize){
        return R.success();
    }

    /**
     * 发布文章
     * @param article
     * @return
     */
    @PostMapping("/add")
    public R addArticle(Article article){
        return R.success(articleService.save(article));
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @PutMapping("/update")
    public R updateArticle(Article article){
        return R.success(articleService.updateById(article));
    }

    /**
     * 删除文章
     * @param article
     * @return
     */
    @DeleteMapping("/delete")
    public R deleteArticle(Article article){
        return R.success(articleService.removeById(article));
    }
}
