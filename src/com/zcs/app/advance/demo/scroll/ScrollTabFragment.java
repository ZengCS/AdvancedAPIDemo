package com.zcs.app.advance.demo.scroll;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragment;

public class ScrollTabFragment extends BaseFragment {
	/** tabs */
	private ImageView tab1, tab2, tab3, tab4;
	private ImageView[] tabs;
	private static final int[] TAB_ON_ICONS = { R.drawable.tab_1_on, R.drawable.tab_2_on, R.drawable.tab_3_on, R.drawable.tab_4_on };

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d("SlidingMenu", "MainFragment onCreateView called!");
		root = inflater.inflate(R.layout.fragment_tab, null);
		super.init();// 初始化
		tabSelection(1);
		return root;
	}

	/**
	 * 刷新tab状态
	 */
	public void tabSelection(int index) {
		// 第一步,恢复默认状态
		tab1.setImageResource(R.drawable.tab_1);
		tab2.setImageResource(R.drawable.tab_2);
		tab3.setImageResource(R.drawable.tab_3);
		tab4.setImageResource(R.drawable.tab_4);
		index--;
		tabs[index].setImageResource(TAB_ON_ICONS[index]);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.index_tab_1:
			tabSelection(1);
			// 打开菜单
			mListener.openMenu(0);
			break;
		case R.id.index_tab_2:
			tabSelection(2);
			// 打开菜单
			mListener.openMenu(1);
			break;
		case R.id.index_tab_3:
			tabSelection(3);
			// 打开菜单
			mListener.openMenu(2);
			break;
		case R.id.index_tab_4:
			tabSelection(4);
			// 打开菜单
			mListener.openMenu(3);
			break;
		default:
			break;
		}
	}

	@Override
	protected void initComponent() {
		tabs = new ImageView[TAB_ON_ICONS.length];
		tab1 = (ImageView) root.findViewById(R.id.index_tab_1);
		tab2 = (ImageView) root.findViewById(R.id.index_tab_2);
		tab3 = (ImageView) root.findViewById(R.id.index_tab_3);
		tab4 = (ImageView) root.findViewById(R.id.index_tab_4);
		tabs[0] = tab1;
		tabs[1] = tab2;
		tabs[2] = tab3;
		tabs[3] = tab4;
		tab1.setOnClickListener(this);
		tab2.setOnClickListener(this);
		tab3.setOnClickListener(this);
		tab4.setOnClickListener(this);
	}
}
