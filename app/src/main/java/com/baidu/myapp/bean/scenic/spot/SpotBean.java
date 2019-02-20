package com.baidu.myapp.bean.scenic.spot;

import com.baidu.myapp.util.Debbuger;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

public class SpotBean extends DataSupport implements Serializable{
    private static final long serialVersionUID=1L;
    private double spotLatitude;//景点的纬度
    private double spotLongtitude;//景点的经度
    @Column(unique = true)
    private int spotId;//景点编号，按照大小执行默认优先序列
    private String spotName;//景点的名称
    private String spotDescribe;//景点的文字描述
    private String spotImg;//景点的介绍图片：封面+6宫格
    private String spotDistance;//景点的路程
    private String voiceSrc;//景点介绍的音频路径---可以用路径来区分于那个~比如说景区+景点的名称缩写字母排列等
    private String nextVoiceSrc;//下一个景点走向音频描述文件路径
    private String videoSrc;//景点介绍的视屏路径---不必要单独去列多一个表去区别这些信息了
    private int scenic_id;

    public int getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(int scenic_id) {
        this.scenic_id = scenic_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public String getNextVoiceSrc() {
        return nextVoiceSrc;
    }

    public void setNextVoiceSrc(String nextVoiceSrc) {
        this.nextVoiceSrc = nextVoiceSrc;
    }

    public double getSpotLatitude() {
        return spotLatitude;
    }

    public void setSpotLatitude(double spotLatitude) {
        this.spotLatitude = spotLatitude;
    }

    public double getSpotLongtitude() {
        return spotLongtitude;
    }

    public void setSpotLongtitude(double spotLongtitude) {
        this.spotLongtitude = spotLongtitude;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getSpotDescribe() {
        return spotDescribe;
    }

    public void setSpotDescribe(String spotDescribe) {
        this.spotDescribe = spotDescribe;
    }

    public String getSpotImg() {
        return spotImg;
    }

    public void setSpotImg(String spotImg) {
        this.spotImg = spotImg;
    }

    public String getSpotDistance() {
        return spotDistance;
    }

    public void setSpotDistance(String spotDistance) {
        this.spotDistance = spotDistance;
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
    @Override
    public String toString() {
        return "SpotBean{" +
                "spotLatitude=" + spotLatitude +
                ", spotLongtitude=" + spotLongtitude +
                ", spotName='" + spotName + '\'' +
                ", spotDescribe='" + spotDescribe + '\'' +
                ", spotImg='" + spotImg + '\'' +
                ", spotDistance='" + spotDistance + '\'' +
                ", voiceSrc='" + voiceSrc + '\'' +
                ", videoSrc='" + videoSrc + '\'' +
                '}';
    }

    public void SaveAllSpotBean(List<SpotBean> spotBeenList) {
        for (SpotBean spotBean : spotBeenList) {
            if (spotBean.save()) {

            } else {
                Debbuger.LogE("保存景点信息失败");
            }
        }
    }
}
