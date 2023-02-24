package com.esaysc.demo.mapper;

import com.esaysc.demo.entity.CyArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/03/12:35
 * @FileName: CyArticleMapper
 * @Description: ${}
 */
@Mapper
public interface CyArticleMapper {
    @Select("select * from cy_article where user_id = #{id}")
    public List<CyArticle> getArticleById(int id);
    @Select("select * from cy_article where article_id = #{id}")
    public CyArticle getArticleByArticleId(int id);
    @Select("select * from cy_article")
    public List<CyArticle> getAllArticle();
    @Select("select * from cy_article where user_id = #{userId} and title like concat('%',#{title},'%')")
    List<CyArticle> getUserArticleByKeyword(CyArticle cyArticle);
    @Select("select * from cy_article where title like concat('%',#{title},'%')")
    List<CyArticle> getArticleByKeyword(CyArticle cyArticle);

    // 插入文章
    @Select("insert into cy_article (user_id,title,content_markdown,create_time) values (#{userId},#{title},#{contentMarkdown},#{createTime})")
    List<CyArticle> insertArticle(CyArticle cyArticle);
}
