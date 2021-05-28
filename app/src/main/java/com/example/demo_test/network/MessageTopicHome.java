package com.example.demo_test.network;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/13.
 */
public class MessageTopicHome {
    private int count;
    private int page;
    private List<ListBean> list;
    private List<TopicPacketBean> packetList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static ListBean gson(String str) {
        return new Gson().fromJson(str, ListBean.class);
    }

    public static String toJson(List<ListBean> list) {
        return new Gson().toJson(list);
    }

    public List<TopicPacketBean> getPacketList() {
        return packetList;
    }

    public void setPacketList(List<TopicPacketBean> packetList) {
        this.packetList = packetList;
    }

    public class ListBean implements Serializable {
        private String id;
        private String title;
        private String content;
        private String describe;
        private String topicType;
        private String isV;
        private List<String> ids;
        private List<String> pic_url;
        private List<String> play_num;
        private int type; //帖子类型
        private String isHot;
        private int praiseNum;
        private int commentNum;
        private int isShowAuthIcon;
        private String ownUid;//贴主id
        private String ownName;//贴主名
        private int sex;
        private String icon;
        private String userType;
        private int hasPraise;
        private String viewNum;
        private String url;
        private String newUrl;
        private List<Comment> comment = new ArrayList<Comment>();
        private CircleData circleData;
        /**
         * 推广位标识，恒为1
         */
        private String adId = "0";
        /**
         * 倾听者果号
         */
        private String uid;
        /**
         * 用户简介
         */
        private String introduce;
        /**
         * 心情推广语
         */
        private String slogan;
        /**
         * 月售时长，单位小时
         */
        private String thirtyDaysServiceDur;
        /**
         * 得分
         */
        private String levelScore;
        /**
         * 等级图标数量
         */
        private String levelIcoNum;
        /**
         * 等级图标类型
         */
        private String levelIcoType;
        /**
         * 用户名
         */
        private String username;
        /**
         * 起步价，同首页，单位元
         */
        private String miniCharge;

        public String getAdId() {
            return adId;
        }

        public void setAdId(String adId) {
            this.adId = adId;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public String getThirtyDaysServiceDur() {
            return thirtyDaysServiceDur;
        }

        public void setThirtyDaysServiceDur(String thirtyDaysServiceDur) {
            this.thirtyDaysServiceDur = thirtyDaysServiceDur;
        }

        public String getLevelScore() {
            return levelScore;
        }

        public void setLevelScore(String levelScore) {
            this.levelScore = levelScore;
        }

        public String getLevelIcoNum() {
            return levelIcoNum;
        }

        public void setLevelIcoNum(String levelIcoNum) {
            this.levelIcoNum = levelIcoNum;
        }

        public String getLevelIcoType() {
            return levelIcoType;
        }

        public void setLevelIcoType(String levelIcoType) {
            this.levelIcoType = levelIcoType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMiniCharge() {
            return miniCharge;
        }

        public void setMiniCharge(String miniCharge) {
            this.miniCharge = miniCharge;
        }

        public String getUrl() {
            return url;
        }

        public String getNewUrl() {
            return newUrl;
        }

        public void setNewUrl(String newUrl) {
            this.newUrl = newUrl;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getTopicType() {
            return topicType;
        }

        public void setTopicType(String topicType) {
            this.topicType = topicType;
        }

        public String getIsV() {
            return isV;
        }

        public void setIsV(String isV) {
            this.isV = isV;
        }

        public List<String> getIds() {
            return ids;
        }

        public void setIds(List<String> ids) {
            this.ids = ids;
        }

        public List<String> getPic_url() {
            return pic_url;
        }

        public void setPic_url(List<String> pic_url) {
            this.pic_url = pic_url;
        }

        public List<String> getPlay_num() {
            return play_num;
        }

        public void setPlay_num(List<String> play_num) {
            this.play_num = play_num;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getIsShowAuthIcon() {
            return isShowAuthIcon;
        }

        public void setIsShowAuthIcon(int isShowAuthIcon) {
            this.isShowAuthIcon = isShowAuthIcon;
        }

        public String getOwnUid() {
            return ownUid;
        }

        public void setOwnUid(String ownUid) {
            this.ownUid = ownUid;
        }

        public String getOwnName() {
            return ownName;
        }

        public void setOwnName(String ownName) {
            this.ownName = ownName;
        }


        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getHasPraise() {
            return hasPraise;
        }

        public void setHasPraise(int hasPraise) {
            this.hasPraise = hasPraise;
        }

        public String getViewNum() {
            return viewNum;
        }

        public void setViewNum(String viewNum) {
            this.viewNum = viewNum;
        }

        public List<Comment> getComment() {
            return comment;
        }

        public void setComment(List<Comment> comment) {
            this.comment = comment;
        }

        public CircleData getCircleData() {
            return circleData;
        }

        public void setCircleData(CircleData circleData) {
            this.circleData = circleData;
        }

    }

    public static class Comment {
        private String praiseNum;
        private String id;
        private String topic_id;
        private String uid;
        private String content;
        private String username;
        private int sex;
        private String addTime;
        private String time;
        private UserInfo userInfo;
        private int count;

        public String getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(String praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    static public class UserInfo {
        private String uid;
        private String username;
        private String name;
        private String sex;
        private String date;
        private String type;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            if (TextUtils.isEmpty(username)) {
                return name;
            }
            return username;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            if (TextUtils.isEmpty(name)) {
                return username;
            }
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public static class CircleData {
        private String type;
        private String url;
        private String title;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}
