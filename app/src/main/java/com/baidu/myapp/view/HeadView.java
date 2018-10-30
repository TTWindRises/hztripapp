package com.baidu.myapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.myapp.R;
import com.baidu.myapp.activity.FNmapActivity;
import com.baidu.myapp.util.CircleCrop;
import com.baidu.myapp.util.Debbuger;
import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2018/10/25.
 */

public class HeadView extends RelativeLayout {

    private  ImageView headshape;
    private  ImageView headview;

    public ImageView getHeadshape() {
        return headshape;
    }

    public void setHeadshape(ImageView headshape) {
        this.headshape = headshape;
    }

    public ImageView getHeadview() {
        return headview;
    }

    public void setHeadview(ImageView headview) {
        this.headview = headview;
    }

    public HeadView(Context context) {
        super(context);
    }

    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context) {
       View c= LayoutInflater.from(context).inflate(R.layout.head, this);
        headview = (ImageView) c.findViewById(R.id.head_portrait);
        if (headview == null) {
            Debbuger.LogE("head找不到");
        } else {
            Debbuger.LogE("有head");
            Glide.with(context).load(R.drawable.hp_01).transform(new CircleCrop(context)).into(headview);
        }
        headview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Debbuger.LogE("点击了头像");
            }
        });

    }

}
