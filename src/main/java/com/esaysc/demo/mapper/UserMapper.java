package com.esaysc.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esaysc.demo.dto.ArticleInfoDTO;
import com.esaysc.demo.dto.RegisterDTO;
import com.esaysc.demo.dto.UserInfoDTO;
import com.esaysc.demo.entity.CyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: lbcf Email: lbcf@126.com
 * @version: 1.0
 * @Date: 2022/12/03/0:06
 * @FileName: UserMapper
 * @Description: ${}
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfoDTO> {
    // 查询所有用户 分页 返回列表
    @Select("select * from cy_user")
    List<CyUser> findPageUsers(Page<CyUser> page);

    // 查询所有用户 返回列表
    @Select("select * from cy_user")
    List<CyUser> getUser();

    // 添加用户 用户Id 用户名 注册邮箱 性别 默认头像 密码
    @Select("insert into cy_user (user_id,username,email,gender,avatar,password) values (#{userId},#{username},#{email},#{gender},#{avatar},#{password})")
    void insertUser(RegisterDTO registerDTO);

    // 根据用户名 查询用户详细信息
    @Select("select * from cy_user where username = #{username}")
    UserInfoDTO getUserInfo(UserInfoDTO userInfoDTO);

    // 通过用户名 更新用户信息
    @Select("update cy_user set nickname = #{nickname}, birth = #{birth}, gender = #{gender}, headline = #{headline} where username = #{username}")
    Integer updateUserInfo(UserInfoDTO userInfoDTO);

    // 通过用户ID 更新用户头像
    @Select("update cy_user set avatar = #{avatar} where user_id = #{userId}")
    void updateUserAvatar(UserInfoDTO userInfoDTO);

    // 根据用户ID 查询用户详细信息
    @Select("select * from cy_user where user_id = #{userId}")
    UserInfoDTO getUserInfoByUserId(UserInfoDTO userInfoDTO);

    // 根据文章ID 查询作者详细信息
    @Select("select * from cy_user where user_id in (select user_id from cy_article where article_id = #{articleId})")
    UserInfoDTO getUserInfoByArticleId(UserInfoDTO userInfoDTO);

    // 根据文章ID 查询文章详细信息
    @Select("select * from cy_article where article_id = #{articleId}")
    ArticleInfoDTO getArticleInfoByArticleId(ArticleInfoDTO articleInfoDTO);

    // 查询指定条文章数据
    @Select("select * from cy_article order by vote_up_count desc limit 0,#{num}")
    List<ArticleInfoDTO> getHotArticle(int num);

    // 根据用户名 查询用户ID
    @Select("select user_id from cy_user where username = #{username}")
    Integer getUserIdByUsername(String username);
}
