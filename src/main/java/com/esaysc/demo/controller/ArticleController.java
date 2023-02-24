package com.esaysc.demo.controller;

import com.esaysc.demo.dto.ArticleInfoDTO;
import com.esaysc.demo.entity.CyArticle;
import com.esaysc.demo.mapper.CyArticleMapper;
import com.esaysc.demo.mapper.UserMapper;
import com.esaysc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/03/12:19
 * @FileName: ArticleController
 * @Description: ${}
 */
/*
 * @description:
 * @ClassName: ArticleController
 * @author: ccs
 * @Date: 2022/12/3 12:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/article") // 请求总映射
//@CrossOrigin // 接收所有访问 设置后可以跨域访问
public class ArticleController {
    @Autowired
    public CyArticleMapper cyArticleMapper;
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/userId")
    // @RequestBody 前端发的 json 数据
    public List<CyArticle> getArticleByUserId(@RequestBody CyArticle cyArticles) {
        int id = cyArticles.getUserId();
        System.out.println("id:"+id);
        List<CyArticle> articles = cyArticleMapper.getArticleById(id);
        return articles;
    }

    /*
     * @description: 通过文章ID获取文章数据
     * @author: esaysc
     * @date: 2023/1/3 15:04
     * @param: [cyArticles]
     * @return: com.esaysc.demo.entity.CyArticle
     */
    @PostMapping("/articleId")
    public CyArticle getArticleByArticleId(@RequestBody CyArticle cyArticles) {

        int id = cyArticles.getArticleId();
        System.out.println("id:"+id);
        CyArticle article = cyArticleMapper.getArticleByArticleId(id);
        return article;
    }

    @GetMapping("/hot")
    public List<CyArticle> getArticle() {
        List<CyArticle> articles = cyArticleMapper.getAllArticle();
        return articles;
    }
    @Resource
    private UserService userService;

    @PostMapping("/search/articleAll") // 通过关键字查询文章
    public List<CyArticle> search(@RequestBody CyArticle cyArticle) {
        List<CyArticle> articleList = cyArticleMapper.getArticleByKeyword(cyArticle);
        return articleList;
    }
    @PostMapping("/search/articleUser") // 通过关键字查询文章
    public List<CyArticle> searchUserArticle(@RequestBody CyArticle cyArticle) {
        List<CyArticle> articleList = cyArticleMapper.getUserArticleByKeyword(cyArticle);
        return articleList;
    }
    @PostMapping("/publish/article") // 发布文章
    public List<CyArticle> publishArticle(@RequestBody CyArticle cyArticle) {
        List<CyArticle> articleList = cyArticleMapper.insertArticle(cyArticle);
        return articleList;
    }
    @PostMapping("/publish") // 发布文章
    public List<CyArticle> publish(@RequestBody CyArticle cyArticle) {
        List<CyArticle> articleList = cyArticleMapper.insertArticle(cyArticle);
        return articleList;
    }
    @PostMapping("/info/articleId") // 接收文章ID返回文章的详细信息
    public ArticleInfoDTO getArticleInfoByArticleId(@RequestBody ArticleInfoDTO articleInfoDTO) {
        System.out.println("articleInfoDTO"+articleInfoDTO);
        ArticleInfoDTO info = userMapper.getArticleInfoByArticleId(articleInfoDTO);
        return info;
    }
    @PostMapping("/hot/article") // 查询前四的文章
    public List<ArticleInfoDTO> getHotArticle() {
        int num = 4;
        List<ArticleInfoDTO> info = userMapper.getHotArticle(num);
        return info;
    }
}
