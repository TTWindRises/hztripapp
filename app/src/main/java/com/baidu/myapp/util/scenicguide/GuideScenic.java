package com.baidu.myapp.util.scenicguide;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.myapp.bean.scenic.spot.SpotBean;
import com.baidu.myapp.util.Debbuger;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.location.g.j.B;
import static com.baidu.location.g.j.n;
import static com.baidu.mapapi.utils.DistanceUtil.getDistance;

/**
 * Created by Administrator on 2019/2/28.
 */

public class GuideScenic {
    List<SpotBean> spots = new ArrayList<>();

    public GuideScenic(List<SpotBean> spots) {
        this.spots = spots;
    }

    //景点排序
    public  List<SpotBean> SortingSpot(LatLng latLng) {
        //根据距离，还是什么呢
        for (SpotBean spot : spots) {
            double distance = DistanceUtil.getDistance(new LatLng(spot.getSpotLatitude(), spot.getSpotLongtitude()), latLng);
            Debbuger.LogE("distance:"+distance);

        }


        return spots;
    }

}
