package com.baidu.myapp.fragment.hotel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.myapp.R;
import com.baidu.myapp.fragment.BaseFragment;
import com.baidu.myapp.fragment.food.FoodMainFragment;

/**
 * Created by Administrator on 2019/2/20.
 */

public class HotelMainFragment extends Fragment{
    private String keywords;


    public static HotelMainFragment getInstance(String mTitle) {
        HotelMainFragment tabFragment = null;


        if (tabFragment == null) {
            tabFragment = new HotelMainFragment();
        }
        tabFragment.keywords = mTitle;
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_evaluate_fragment, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.food_evaluate_recyclerview);

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
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.food_evaluate_item,
                    viewGroup, false);
            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {


            holder.textView.setText("111");

        }


        @Override
        public int getItemCount() {
            return 15;
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final TextView textView;


            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击：" + getPosition(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
