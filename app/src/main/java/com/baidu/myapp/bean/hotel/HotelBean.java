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

}
