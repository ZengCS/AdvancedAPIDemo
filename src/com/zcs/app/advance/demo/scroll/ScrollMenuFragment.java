package com.zcs.app.advance.demo.scroll;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcs.app.advance.R;
import com.zcs.app.advance.adapter.IndexViewPagerAdapter;
import com.zcs.app.advance.base.BaseFragment;

public class ScrollMenuFragment extends BaseFragment {

	private List<View> mPages;// 页面集合
	private LayoutInflater inflater;
	private ViewPager viewPager;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_scroll_menu, null);
		this.inflater = inflater;
		super.init();// 初始化
		initViewPager();
		return root;
	}

	public void setCurrentMenu(int index) {
		viewPager.setCurrentItem(index);
	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		mPages = new ArrayList<View>();
		View mPage1 = inflater.inflate(R.layout.page_scroll_menu_1, null);
		View mPage2 = inflater.inflate(R.layout.page_scroll_menu_2, null);
		View mPage3 = inflater.inflate(R.layout.page_scroll_menu_3, null);
		View mPage4 = inflater.inflate(R.layout.page_scroll_menu_4, null);

		mPages.add(mPage1);
		mPages.add(mPage2);
		mPages.add(mPage3);
		mPages.add(mPage4);

		viewPager.setAdapter(new IndexViewPagerAdapter(mPages));
		viewPager.setCurrentItem(0);

		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * Page切换监听
	 * 
	 * @author ZengCS
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		public void onPageScrollStateChanged(int arg0) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int position) {
			mListener.setCurrentTab(position);
			// Animation animation = new TranslateAnimation(offset * currIndex,
			// offset * position, 0, 0);
			// currIndex = position;
			// animation.setFillAfter(true);// True:图片停在动画结束位置
			// animation.setDuration(200);
			// imageView.startAnimation(animation);
			// setHighTitle(position);
		}
	}

	@Override
	protected void initComponent() {
		viewPager = (ViewPager) root.findViewById(R.id.vp_scroll_menu);
	}

	@Override
	public void onClick(View v) {

	}
}
