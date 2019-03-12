package com.baidu.myapp.behaviors;


import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.baidu.myapp.R;
import com.baidu.myapp.util.Debbuger;
import com.baidu.myapp.util.foodutil.ViewUtils;
import com.baidu.myapp.view.ShopHeaderInfo;

import java.lang.ref.WeakReference;

import static com.baidu.location.g.j.D;
import static com.baidu.myapp.util.foodutil.ViewUtils.getStatusBarHeight;

public class ShopContainerBehavior extends CoordinatorLayout.Behavior<ShopHeaderInfo> {

	private Context mContext;
	private int iconHeight, iconWidth;
	private int totalRange;
	private float startX, startY, bgRange;
	private float dx, dy;
	private View name_container, iv_shop;
	private WeakReference<ShopHeaderInfo> mContainer;
	private View iv_shop_bg;

	public ShopContainerBehavior() {
		super();
	}

	public ShopContainerBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	@Override
	public boolean onLayoutChild(CoordinatorLayout parent, ShopHeaderInfo child, int layoutDirection) {
		boolean handled = super.onLayoutChild(parent, child, layoutDirection);
		if (mContainer == null) {
			mContainer = new WeakReference<>(child);
			name_container = child.findViewById(R.id.food_layout);
			iv_shop = child.findViewById(R.id.food_store_head_img);
			iv_shop_bg = child.findViewById(R.id.food_store_head_bg);
		}
		return handled;
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, ShopHeaderInfo child, View dependency) {
		return dependency instanceof AppBarLayout;
	}

	public boolean onDependentViewChanged(CoordinatorLayout parent, ShopHeaderInfo child, View dependency) {
		/*if (dependency instanceof AppBarLayout) {
			Debbuger.LogE("AppBarLayout：cute");
			AppBarLayout appBarLayout = (AppBarLayout) dependency;
			Debbuger.LogE("iconHeightMeusr:"+iconHeight);
			if (iconHeight > 0) {
				Debbuger.LogE("iconHeight: surpass  zero");
				// 店名
				float dTop = dependency.getTop();
				float dt = dTop / totalRange;
//				name_container.setX(startX + dx * dt);
				name_container.setY(startY+ dTop / 4);
				Debbuger.LogE("getContainerY:" + name_container.getY());
				child.setWgAlpha(1 + dt * 2);
				//图标
				iv_shop.setY(startY + dTop / 4);
				RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) iv_shop.getLayoutParams();
				lp.width = iconWidth + (int) dTop / 2;
				lp.height = iconHeight + (int) dTop / 2;
				iv_shop.setLayoutParams(lp);
				//背景
				iv_shop_bg.setTranslationY(dTop * bgRange);
			} else {

				totalRange = appBarLayout.getTotalScrollRange();
				shouldInitProperties();
				Debbuger.LogE("totalRange:"+totalRange);
			}
		} else {


		}*/

		return true;
	}


	private void shouldInitProperties() {

		startX = iv_shop.getX();
		startY = iv_shop.getY();
		Debbuger.LogE("startX:" + startX);
		Debbuger.LogE("startY:" + startY);
		int statusBarHeight = com.baidu.myapp.util.foodutil.ViewUtils.getStatusBarHeight(mContext);
		int acBarHeight = com.baidu.myapp.util.foodutil.ViewUtils.dip2px(mContext, 56);
		Debbuger.LogE("statusBarHeight:" + statusBarHeight);
		Debbuger.LogE("acBarHeight:"+acBarHeight);

		dx = startX - acBarHeight - 8;
		dy = -(statusBarHeight + acBarHeight / 2 - getContainer().shop_name.getHeight() / 2 - startY);
		Debbuger.LogE("dx:" + dx);
		Debbuger.LogE("dy:" + dy);
		iconHeight = iv_shop.getHeight();
		iconWidth = iv_shop.getWidth();
		Debbuger.LogE("iconHeight:" + iv_shop.getHeight());
		Debbuger.LogE("iconWidth:" + iconWidth);
		bgRange = (totalRange - acBarHeight) / (float) totalRange;
		Debbuger.LogE("bgRange:" + bgRange);
	}

	private ShopHeaderInfo getContainer() {
		return mContainer.get();
	}

}
