package com.zcs.app.advance.demo.scroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragment;

public class ScrollIndexFragment extends BaseFragment {

	// private List<View> mPages;// 页面集合
	// private LayoutInflater inflater;
	// private ViewPager viewPager;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_scroll_index, null);
		// this.inflater = inflater;
		super.init();// 初始化
		root.findViewById(R.id.titlebtn_left_act).setVisibility(View.GONE);
		root.findViewById(R.id.titletxt_right_act).setVisibility(View.GONE);
		((TextView) root.findViewById(R.id.titletxt_center_act)).setText("纵向菜单");
		// initViewPager();
		return root;
	}

	/**
	 * 初始化ViewPager
	 */
	// private void initViewPager() {
	// mPages = new ArrayList<View>();
	// View mPage1 = inflater.inflate(R.layout.page_scroll_menu_1, null);
	// View mPage2 = inflater.inflate(R.layout.page_scroll_menu_2, null);
	// View mPage3 = inflater.inflate(R.layout.page_scroll_menu_3, null);
	// View mPage4 = inflater.inflate(R.layout.page_scroll_menu_4, null);
	//
	// mPages.add(mPage1);
	// mPages.add(mPage2);
	// mPages.add(mPage3);
	// mPages.add(mPage4);
	//
	// viewPager.setAdapter(new IndexViewPagerAdapter(mPages));
	// viewPager.setCurrentItem(0);
	// }

	@Override
	protected void initComponent() {
		// viewPager = (ViewPager) root.findViewById(R.id.vp_scroll_menu);
	}

	@Override
	public void onClick(View v) {

	}
}
