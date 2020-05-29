package com.ytsakura.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YtSakura(Yin Tao)
 * @date 2020/5/29 12:21
 * @describe 博客实体类
 */
@Entity
@Table(name="t_blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    //博客id

    private String title;               //标题
    private String content;             //内容

    private String firstPicture;        //首图
    private String flag;                //标记
    private Integer views;              //浏览次数
    private boolean appreciation;       //赞赏是否开启
    private boolean shareStatement;     //转载声明是否开启
    private boolean commentable;        //评论是否开启
    private boolean recommend;          //是否推荐
    private boolean published; //是否发布还是保存为草稿
    @Temporal(TemporalType.TIMESTAMP)   //指定时间的类型
    private Date createTime; //博客创建时间
    @Temporal(TemporalType.TIMESTAMP)   //指定时间的类型
    private Date updateTime; //博客更新时间

    @ManyToOne                          //指定对应关系为多对一
    private Type type;                  //多个博客可从属于一个分类

    @ManyToMany(cascade = {CascadeType.PERSIST})    //cascade设置级联关系,当新增一个博客的同时如果需要新增一个标签，则也将标签新增进数据库
    private List<Tag> tags = new ArrayList<>();     //博客与标签的关系为多对多

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    public Blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(boolean commentable) {
        this.commentable = commentable;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentable=" + commentable +
                ", recommend=" + recommend +
                ", published=" + published +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
