package com.baidu.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodStore;

import java.util.List;

import static android.R.attr.data;

/**
 * Created by Administrator on 2018/11/8.
 */

public class FoodBeanAdapter extends BaseAdapter {
    private List<FoodBean> foodBeanList;//数据源
    private LayoutInflater mInflater;//布局装载器对象


    public FoodBeanAdapter(Context context, List<FoodBean> data) {
        this.foodBeanList = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodBeanList.size();
    }
    //指定的索引对应的数据项
    @Override
    public Object getItem(int i) {
        return foodBeanList.get(i);
    }
   //指定的索引对应的数据项ID
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;//这个只是一个中间存储变量
        //要看缓存池中有没有相对应的视图资源还得判断获取到的
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.food_item, null);//将xml文件转换成为视图

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.food_item_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.food_item_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.food_item_content);
            //通过setTag将convertView与viewHolder关联
            convertView.setTag(viewHolder);

        } else {//缓存池中有对应的view缓存，则直接通过getTag取出viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //拿出单个item的内容放入视图中
        FoodBean foodBean = foodBeanList.get(i);
        viewHolder.imageView.setImageResource(Integer.parseInt(foodBean.getFoodImg()));
        viewHolder.title.setText(foodBean.getFoodName());
        viewHolder.content.setText(foodBean.getFoodDescribe());

        return convertView;//将xml中的属性设置完成后，返回xml转化的VIEW视图
    }
    class ViewHolder{
        //item的控件内容
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }
}
