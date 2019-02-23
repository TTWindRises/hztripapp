package com.baidu.myapp.util.scenicutil;

import android.view.View;
import android.widget.ImageView;

import com.baidu.myapp.util.Debbuger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/2/21.
 */

public class ClearViewManger {
    private  static ClearViewManger clearViewManger = new ClearViewManger();
    private List<ImageView> list = new ArrayList<>();
    public static ClearViewManger getInstance() {
        return clearViewManger;
    }


    public void HideAllView() {
        if (list != null && !list.isEmpty()) {
            for (ImageView img : list) {
                img.setVisibility(View.INVISIBLE);
            }
        } else {
            Debbuger.LogE("隐藏失败");
        }

    }

    public void ShowAllView() {
        for (ImageView img : list) {
            img.setVisibility(View.VISIBLE);
        }
    }
    public void addView(ImageView imageView) {
        list.add(imageView);

    }
    public void clearView() {
        list.clear();
    }
}
