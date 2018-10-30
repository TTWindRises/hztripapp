package com.baidu.myapp.bean.hotel;

/**
 * Created by Administrator on 2018/10/30.
 * 妈耶50个房间要加五十个该类数据
 */

public class RoomBean {
    private HotelFloor hotelFloor;//属于哪个楼层
    private String roomID;//房间号
    private String roomCategory;//房间类型
    private String roomPeopleNumber;//房间的容纳人数
    private int roomPrice;//房间的价格
    private int roomStatus;//房间入住状态0：空闲 1：入住
}
