package com.baidu.myapp.adapter.food;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodBean;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.sticky.StickyHeadersAdapter;
import com.baidu.myapp.util.Debbuger;

import java.util.List;

import static com.baidu.location.g.j.D;

public class BigramHeaderAdapter implements StickyHeadersAdapter<BigramHeaderAdapter.ViewHolder> {

    private Context mContext;
    private List<FoodBean> foodlist;
    private List<FoodCategory> foodcategory;
    public BigramHeaderAdapter(Context context, List<FoodBean> items
            , List<FoodCategory> goodscatrgoryEntities) {
        this.mContext = context;
        this.foodlist = items;
        this.foodcategory = goodscatrgoryEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_header_list, parent, false);
        return new BigramHeaderAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BigramHeaderAdapter.ViewHolder headerViewHolder, int position) {
        Debbuger.LogE("categoryName:"+foodcategory.get(Integer.parseInt(foodlist.get(position).getCategory_id())).getCategoryName());
        headerViewHolder.tvGoodsItemTitle.setText(foodcategory.get(Integer.parseInt(foodlist.get(position).getCategory_id())).getCategoryName());
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(foodlist.get(position).getCategory_id());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvGoodsItemTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvGoodsItemTitle = (TextView) itemView.findViewById(R.id.food_header_title);
        }
    }
}
