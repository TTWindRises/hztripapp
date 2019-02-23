package com.baidu.myapp.fragment.food;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.myapp.MainActivity;
import com.baidu.myapp.R;
import com.baidu.myapp.activity.FoodStoreActivity;
import com.baidu.myapp.activity.scenic.ScenicActivity;
import com.baidu.myapp.bean.food.FoodStore;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.GlideRoundTransform;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Administrator on 2018/12/15.
 */

public class FoodMainFragment extends Fragment {
    private String keywords;
    private List<FoodStore> list;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static FoodMainFragment getInstance(String mTitle) {
        FoodMainFragment tabFragment = null;


        if (tabFragment == null) {
            tabFragment = new FoodMainFragment();
        }
        tabFragment.keywords = mTitle;
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_main_fragment, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.food_main_recyclerview);

        init(recyclerView);

        return view;
    }


    private void init(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAapter());
    }


    private class MyAapter extends RecyclerView.Adapter<MyAapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_main_item,
                    viewGroup, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            if (list != null && !list.isEmpty()) {
                Glide.with(getActivity()).load(Integer.parseInt(list.get(position).getStore_heard_img()))
                        .centerCrop().transform(new GlideRoundTransform(getActivity()))
                        .placeholder(R.drawable.food_store_placeholder_square)
                        .dontAnimate()
                        .into(holder.img);

                holder.title.setText(list.get(position).getStoreName());
                holder.slaeMoon.setText("月销：" + list.get(position).getTotalSales());
                holder.praise.setText("好评："+list.get(position).getStorePraise());
                holder.describe.setText(list.get(position).getStoreDescribe());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), FoodStoreActivity.class);
                        intent.putExtra("FoodStore", list.get(position));
                        startActivity(intent);
                        Toast.makeText(getActivity(), list.get(position).getStoreName(), Toast.LENGTH_SHORT).show();

                    }
                });
            } else {
                Toast.makeText(getActivity(), "该区域暂无合作餐饮", Toast.LENGTH_SHORT).show();
            }


        }


        @Override
        public int getItemCount() {
            return list.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private  TextView title;
            private ImageView img;
            private TextView praise;
            private TextView slaeMoon;
            private TextView describe;
            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.food_main_title);
                img = itemView.findViewById(R.id.food_main_img);
                praise = itemView.findViewById(R.id.food_main_praise);
                slaeMoon = itemView.findViewById(R.id.food_main_sale);
                describe = itemView.findViewById(R.id.food_main_describe);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

            }
        }
    }

    public void setData(List<FoodStore> list) {
        this.list = list;//数据在MainActivity生成了
        Debbuger.LogE("DataList:"+list);
    }
}
