package com.baidu.myapp.bean.hotel;

import java.security.Timestamp;

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
    private Timestamp roomStarTime;//房间入住时间
    private Timestamp roomEndTime;//房间退房时间
    private HotelOrder hotelOrder;//单个房间不能被多个用户在同一时间段共同订购所以只能属于一个订单
    public Timestamp getRoomStarTime() {
        return roomStarTime;
    }

    public HotelOrder getHotelOrder() {
        return hotelOrder;
    }

    public void setHotelOrder(HotelOrder hotelOrder) {
        this.hotelOrder = hotelOrder;
    }

    public void setRoomStarTime(Timestamp roomStarTime) {
        this.roomStarTime = roomStarTime;
    }

    public Timestamp getRoomEndTime() {
        return roomEndTime;
    }

    public void setRoomEndTime(Timestamp roomEndTime) {
        this.roomEndTime = roomEndTime;
    }

    @Override
    public String toString() {
        return "RoomBean{" +
                "hotelFloor=" + hotelFloor +
                ", roomID='" + roomID + '\'' +
                ", roomCategory='" + roomCategory + '\'' +
                ", roomPeopleNumber='" + roomPeopleNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomStatus=" + roomStatus +
                '}';
    }

    public HotelFloor getHotelFloor() {
        return hotelFloor;
    }

    public void setHotelFloor(HotelFloor hotelFloor) {
        this.hotelFloor = hotelFloor;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public String getRoomPeopleNumber() {
        return roomPeopleNumber;
    }

    public void setRoomPeopleNumber(String roomPeopleNumber) {
        this.roomPeopleNumber = roomPeopleNumber;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }
}
