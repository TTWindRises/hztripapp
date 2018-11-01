package com.baidu.myapp.bean.hotel;

import java.util.List;

public class HotelBean {
    private int hotelID;//酒店的唯一识别号
    private String hotelName;//酒店的名称
    private double hotelLatitude;//酒店坐标的经度
    private double hotelLongtitude;//酒店坐标的纬度
    private String hotelShowImg;//预计是一个数组，里面包含酒店的封面和一个数组图表、最大不可以超过6张~突然觉得C+里面的结构体机制挺方便的啊
    private String hotelDescribe;//酒店简介描述
    private List<HotelFloor> hotelFloorList;//一个酒店中所以的楼层信息
    private int hotelLevel;//酒店星级,可以为0
    private String hotelSecurityCode;//一串可以确定这个酒店是真实存在的识别码
    private String hotelDistance;//距离酒店的距离   ps：好复杂好复杂这个数据，暂时写死,不然得給这个数据单独设计一个算法出来

    @Override
    public String toString() {
        return "HotelBean{" +
                "hotelID=" + hotelID +
                ", hotelName='" + hotelName + '\'' +
                ", hotelLatitude=" + hotelLatitude +
                ", hotelLongtitude=" + hotelLongtitude +
                ", hotelShowImg='" + hotelShowImg + '\'' +
                ", hotelDescribe='" + hotelDescribe + '\'' +
                ", hotelFloorList=" + hotelFloorList +
                ", hotelLevel=" + hotelLevel +
                ", hotelSecurityCode='" + hotelSecurityCode + '\'' +
                ", hotelDistance='" + hotelDistance + '\'' +
                '}';
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getHotelLatitude() {
        return hotelLatitude;
    }

    public void setHotelLatitude(double hotelLatitude) {
        this.hotelLatitude = hotelLatitude;
    }

    public double getHotelLongtitude() {
        return hotelLongtitude;
    }

    public void setHotelLongtitude(double hotelLongtitude) {
        this.hotelLongtitude = hotelLongtitude;
    }

    public String getHotelShowImg() {
        return hotelShowImg;
    }

    public void setHotelShowImg(String hotelShowImg) {
        this.hotelShowImg = hotelShowImg;
    }

    public String getHotelDescribe() {
        return hotelDescribe;
    }

    public void setHotelDescribe(String hotelDescribe) {
        this.hotelDescribe = hotelDescribe;
    }

    public List<HotelFloor> getHotelFloorList() {
        return hotelFloorList;
    }

    public void setHotelFloorList(List<HotelFloor> hotelFloorList) {
        this.hotelFloorList = hotelFloorList;
    }

    public int getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(int hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    public String getHotelSecurityCode() {
        return hotelSecurityCode;
    }

    public void setHotelSecurityCode(String hotelSecurityCode) {
        this.hotelSecurityCode = hotelSecurityCode;
    }

    public String getHotelDistance() {
        return hotelDistance;
    }

    public void setHotelDistance(String hotelDistance) {
        this.hotelDistance = hotelDistance;
    }
}
