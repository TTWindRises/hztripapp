package com.baidu.myapp.adapter.food;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.baidu.myapp.R;
import com.baidu.myapp.bean.food.FoodCategory;
import com.baidu.myapp.interfaces.ifood.IFoodBuy;
import com.baidu.myapp.util.Debbuger;

import java.util.HashMap;
import java.util.List;

import static com.baidu.location.g.j.D;

/**
 * Created by Administrator on 2018/11/29.
 */

public class FoodLeftRecyclerAdapter extends RecyclerView.Adapter<FoodLeftRecyclerAdapter.ViewHolder> {
    //当前选中的位置
    private int selectPosition;
    private List<FoodCategory> categoryList;
    private Context context;
    private List<FoodCategory> data;
    private HashMap<String, Integer> badges = new HashMap<>();
    public FoodLeftRecyclerAdapter(Context context, List<FoodCategory> data) {
        this.context = context;
        this.data = data;
    }
    public  void  changeData(List<FoodCategory> categoryList){
        this.categoryList=categoryList;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_vertical_left_item, parent, false);
        return new ViewHolder(view);

    }
    public void updateBadge(HashMap<String, Integer> badges) {
        this.badges = badges;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Debbuger.LogE("vertical load");
        holder.category.setText(data.get(position).getCategoryName());
        holder.category_badge.setVisibility(View.INVISIBLE);
        if (selectPosition != -1) {
            if (selectPosition == position) {

                holder.itemView.setBackgroundResource(R.drawable.goods_category_list_bg_select);
            } else {
                holder.itemView.setBackgroundResource(R.drawable.goods_category_list_bg_normal);
            }
        } else {
            holder.itemView.setBackgroundResource(R.drawable.goods_category_list_bg_normal);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(view,position);
                }
            }
        });
        if (badges.containsKey(String.valueOf(data.get(position).getCategoryID())) && badges.get(String.valueOf(data.get(position).getCategoryID())) > 0) {//其实人家是个键值对，老想成数组干什么
            holder.category_badge.setVisibility(View.VISIBLE);
            holder.category_badge.setText(String.valueOf(badges.get(String.valueOf(data.get(position).getCategoryID()))));
        } else {
            holder.category_badge.setVisibility(View.INVISIBLE);
        }


    }
    /**
     * 设置选中index
     * @param position
     */
    public void setCheckPosition(int position) {
        this.selectPosition = position;
        Debbuger.LogE("selectPosition:" + position);
        notifyDataSetChanged();

    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        TextView category_badge;
        public ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.food_store_vertical_left_category_name);
            category_badge =  itemView.findViewById(R.id.item_badge);
        }
    }

}
