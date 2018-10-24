package com.baidu.myapp.overlay.util;

import android.util.Log;

import com.baidu.myapp.bean.scenic.ScenicBean;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import java.util.List;

import static android.content.ContentValues.TAG;

public class RoutePlan {

    //就把选择的点都添加到一个数组内，然后放到这里进行规划整理
    public void PlanSenic(List<ScenicBean> scenics, LatLng mylocation) {
        //根据距离
        double [] d = new double[scenics.size()];
        int i=0;
        for (ScenicBean scenic : scenics) {
            LatLng scenicLaLng = new LatLng(scenic.getLatitude(), scenic.getLongtitude());
            i++;
            double distanceMytoScenic=DistanceUtil.getDistance(mylocation,scenicLaLng);
            d[i]=distanceMytoScenic;




        }
        double c;
        for (int j=0;j<scenics.size()-1;j++){
            Log.i(TAG, "distance :  "+ d[j]);
            if(d[j]>d[j+1]){

                c=d[j];
                d[j]=d[j+1];
                d[j+1]=c;

            }
        }
        for (int k=0;k<scenics.size();k++){
            Log.i(TAG, "DDDD i: "+d[k]);
        }
        //根据总距离最短来计算.?
        /**
         * 总距离包括我的位置 、 当天游玩景区、返回酒店、 吃美食
         */
        //根据金钱最少来计算?
        //根据时间最短来算？？？
        //给用户进行选择就好了。


    }
}
