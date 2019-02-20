package com.baidu.myapp.adapter.scenic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.scenic.spot.SpotBean;
import com.baidu.myapp.util.foodutil.FoodGlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/11/29.
 */

public class SpotShowAdapter extends RecyclerView.Adapter<SpotShowAdapter.ViewHolder> {
    private Context context;
    private List<SpotBean> data;
    public SpotShowAdapter(Context context, List<SpotBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scenic_horizontal_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FoodGlideUtil.getInstance().setSquareGlide(context, Integer.valueOf(data.get(position).getSpotImg()), holder.imageView);
        holder.title.setText(data.get(position).getSpotName());
        holder.describe.setText(data.get(position).getSpotDescribe());


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView describe;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.scenic_horizontal_item_img);
            title = itemView.findViewById(R.id.scenic_horizontal_item_title);
            describe = itemView.findViewById(R.id.scenic_horizontal_item_describe);
        }
    }

}
