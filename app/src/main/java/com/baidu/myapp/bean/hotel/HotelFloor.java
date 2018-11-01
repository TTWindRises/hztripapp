package com.baidu.myapp.bean.hotel;

import java.util.List;

/**
 * Created by Administrator on 2018/10/30.
 * 加了个楼层中间表，数据结构就变得立体了。我的个妈呀
 */

public class HotelFloor {
    private HotelBean hotelBean;//多个楼层对应酒店的单一关系
    private int hotelFloorID;//楼层号
    private int hotelFloorRoomNum;//该楼层的房间数量
    private List<RoomBean> roomBeanList;//一个楼层中所有房间的信息

    public HotelBean getHotelBean() {
        return hotelBean;
    }

    public void setHotelBean(HotelBean hotelBean) {
        this.hotelBean = hotelBean;
    }

    public int getHotelFloorID() {
        return hotelFloorID;
    }

    public void setHotelFloorID(int hotelFloorID) {
        this.hotelFloorID = hotelFloorID;
    }

    public int getHotelFloorRoomNum() {
        return hotelFloorRoomNum;
    }

    public void setHotelFloorRoomNum(int hotelFloorRoomNum) {
        this.hotelFloorRoomNum = hotelFloorRoomNum;
    }

    public List<RoomBean> getRoomBeanList() {
        return roomBeanList;
    }

    public void setRoomBeanList(List<RoomBean> roomBeanList) {
        this.roomBeanList = roomBeanList;
    }

    @Override
    public String toString() {
        return "HotelFloor{" +
                "hotelBean=" + hotelBean +
                ", hotelFloorID=" + hotelFloorID +
                ", hotelFloorRoomNum=" + hotelFloorRoomNum +
                ", roomBeanList=" + roomBeanList +
                '}';
    }
}
