package com.zcs.app.advance.demo.scroll.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ScrollMenu extends ScrollView {
	private int tabHeight;// tab高度
	private int mHalfTabHeight;
	private boolean isTabOpen;
	private boolean isMenuOpen;
	private boolean isSmoothing;
	private boolean once;
	private int areaHeight;// 可视区域高度
	private ViewGroup contentLayout, tabLayout, menuLayout;

	public ScrollMenu(Context context) {
		super(context);
	}

	public ScrollMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScrollMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		/**
		 * 显示的设置一个高度
		 */
		int height = getMeasuredHeight();
		if (!once && height > 0) {
			LinearLayout wrapper = (LinearLayout) getChildAt(0);
			contentLayout = (ViewGroup) wrapper.getChildAt(0);
			tabLayout = (ViewGroup) wrapper.getChildAt(1);
			menuLayout = (ViewGroup) wrapper.getChildAt(2);

			ViewGroup.LayoutParams lp1 = contentLayout.getLayoutParams();
			lp1.height = height;

			ViewGroup.LayoutParams lp2 = tabLayout.getLayoutParams();
			this.tabHeight = lp2.height;

			ViewGroup.LayoutParams lp3 = menuLayout.getLayoutParams();
			lp3.height = height - tabHeight;

			once = true;
			this.areaHeight = height;
			this.mHalfTabHeight = tabHeight / 2;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		// Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
		case MotionEvent.ACTION_UP:
			int scrollY = getScrollY();
			if (!isTabOpen || !isMenuOpen) {
				if (scrollY > mHalfTabHeight) {
					this.smoothScrollTo(0, tabHeight);
					isTabOpen = true;
				} else {
					this.smoothScrollTo(0, 0);
					isTabOpen = false;
				}
			} else if (isMenuOpen) {
				if (scrollY < areaHeight * 0.8) {
					closeMenuDetail();
				} else {
					this.smoothScrollTo(0, areaHeight);
					isMenuOpen = true;
				}
			} else {
				System.out.println("not catch");
			}
			return true;
		case MotionEvent.ACTION_MOVE:
			if (getParent() != null) {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			if (isSmoothing) {
				return true;
			}
		}
		return super.onTouchEvent(ev);
	}

	public void closeMenuDetail() {
		if (isMenuOpen) {
			smoothScrollTo(0, 0);
			isSmoothing = true;
			isMenuOpen = false;
			isTabOpen = false;
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					menuLayout.setVisibility(View.GONE);
					isSmoothing = false;
				}
			}, 500);
		}
	}

	public void openMenuDetail() {
		if (!isMenuOpen) {
			menuLayout.setVisibility(View.VISIBLE);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					smoothScrollTo(0, areaHeight);
					isMenuOpen = true;
				}
			}, 60);
		}
	}
}
