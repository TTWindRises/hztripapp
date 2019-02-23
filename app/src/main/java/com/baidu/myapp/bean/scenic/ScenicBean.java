package com.baidu.myapp.bean.scenic;

import com.baidu.myapp.bean.scenic.spot.SpotBean;
import com.baidu.myapp.util.Debbuger;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 曾志荣年 on 2018/5/17.
 */

public class ScenicBean extends DataSupport implements Serializable {
    @Column(unique = true)
    private int scenicId;
    private double scenicLatitude;//景区位置的经度
    private double scenicLongtitude;//景区位置的纬度
    private String scenicImg;//封面+介绍六宫格图片
    private int scenicOverlayImg;//景区覆盖物图标
    private String scenicName;//景区名
    private int isTickets; // 默认0（未购票),1（已购票)
    private String scenicDistance;//到景区的距离
    private int scenicPraise;//景区的点赞数
    private String scenicTime;//游玩时长
    private double scenicPrice;//景区的门票价
    private String scenicDescribe;//景区的文字描述
    private String voiceSrc;//景点介绍的音频路径---可以用路径来区分于那个~比如说景区+景点的名称缩写字母排列等
    private String videoSrc;//景点介绍的视屏路径---不必要单独去列多一个表去区别这些信息了
    private List<SpotBean> spotBeanList;//景区里面的所有景点
    private String scenicLine;//景区的景点线路区阵——存放所有导游游览的线路
    private static final long serialVersionUID = -1010711755;

    public int getTickets() {
        return isTickets;
    }

    public void setTickets(int tickets) {
        isTickets = tickets;
    }

    public double getScenicLatitude() {
        return scenicLatitude;
    }

    public void setScenicLatitude(double scenicLatitude) {
        this.scenicLatitude = scenicLatitude;
    }

    public double getScenicLongtitude() {
        return scenicLongtitude;
    }

    public void setScenicLongtitude(double scenicLongtitude) {
        this.scenicLongtitude = scenicLongtitude;
    }
    public int getScenicId() {
        return scenicId;
    }

    public void setScenicId(int scenicId) {
        this.scenicId = scenicId;
    }

    public String getScenicTime() {
        return scenicTime;
    }

    public void setScenicTime(String scenicTime) {
        this.scenicTime = scenicTime;
    }
    public List<SpotBean> getAllSpotById(String scenicid) {
        return DataSupport.where("scenic_id=?", scenicid).find(SpotBean.class);
    }
    public int isTickets() {
        return isTickets;
    }
    public String getScenicImg() {
        return scenicImg;
    }

    public void setScenicImg(String scenicImg) {
        this.scenicImg = scenicImg;
    }

    public int getScenicOverlayImg() {
        return scenicOverlayImg;
    }

    public void setScenicOverlayImg(int scenicOverlayImg) {
        this.scenicOverlayImg = scenicOverlayImg;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getScenicDistance() {
        return scenicDistance;
    }

    public void setScenicDistance(String scenicDistance) {
        this.scenicDistance = scenicDistance;
    }

    public int getScenicPraise() {
        return scenicPraise;
    }

    public void setScenicPraise(int scenicPraise) {
        this.scenicPraise = scenicPraise;
    }

    public double getScenicPrice() {
        return scenicPrice;
    }

    public void setScenicPrice(double scenicPrice) {
        this.scenicPrice = scenicPrice;
    }

    public String getScenicDescribe() {
        return scenicDescribe;
    }

    public void setScenicDescribe(String scenicDescribe) {
        this.scenicDescribe = scenicDescribe;
    }

    public String getVoiceSrc() {
        return voiceSrc;
    }

    public void setVoiceSrc(String voiceSrc) {
        this.voiceSrc = voiceSrc;
    }

    public String getVideoSrc() {
        return videoSrc;
    }

    public void setVideoSrc(String videoSrc) {
        this.videoSrc = videoSrc;
    }

    public List<SpotBean> getSpotBeanList() {
        return spotBeanList;
    }

    public void setSpotBeanList(List<SpotBean> spotBeanList) {
        this.spotBeanList = spotBeanList;
    }

    public String getScenicLine() {
        return scenicLine;
    }

    public void setScenicLine(String scenicLine) {
        this.scenicLine = scenicLine;
    }

    @Override
    public String toString() {
        return "ScenicBean{" +
                "scenicLatitude=" + scenicLatitude +
                ", scenicLongtitude=" + scenicLongtitude +
                ", scenicImg='" + scenicImg + '\'' +
                ", scenicOverlayImg=" + scenicOverlayImg +
                ", scenicName='" + scenicName + '\'' +
                ", scenicDistance='" + scenicDistance + '\'' +
                ", scenicPraise=" + scenicPraise +
                ", scenicPrice=" + scenicPrice +
                ", scenicDescribe='" + scenicDescribe + '\'' +
                ", voiceSrc='" + voiceSrc + '\'' +
                ", videoSrc='" + videoSrc + '\'' +
                ", spotBeanList=" + spotBeanList +
                ", scenicLine='" + scenicLine + '\'' +
                '}';
    }
    public void saveScenicBean(List<ScenicBean> scenicBeans) {
        for (ScenicBean scenicBean : scenicBeans) {
            if (scenicBean.save()) {
            } else {
                Debbuger.LogE("保存失败");
            }
        }

    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
