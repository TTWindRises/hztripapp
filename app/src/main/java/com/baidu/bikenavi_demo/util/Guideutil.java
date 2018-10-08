package com.baidu.bikenavi_demo.util;

import android.util.Log;

import com.baidu.bikenavi_demo.bean.scenic.SpotBean;
import com.baidu.mapapi.search.core.RouteLine;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Guideutil {
    public void GetAllNode(List<RouteLine> routes){
        for(int i=0;i<routes.size();i++){
            Log.i(TAG, "ALLNODE : " +i+ " : "  +routes.get(i).getAllStep().size());
            for (int j=0;j<routes.get(i).getAllStep().size();j++){
                Log.i(TAG, "NODE : " +j);
            }
        }


    }
    //传入某个景区进来 然后规划好路线
    public void GuidePlan(Object scenic){

        if (scenic instanceof SpotBean){

            Log.i(TAG,"panduanleixing: " +scenic);
        }
        if (scenic instanceof SpotBean){

            Log.i(TAG,"panduanleixing: " +scenic);
        }

    }
}
