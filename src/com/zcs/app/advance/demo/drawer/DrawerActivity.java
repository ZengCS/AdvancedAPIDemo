package com.zcs.app.advance.demo.drawer;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.adapter.IndexViewPagerAdapter;
import com.zcs.app.advance.base.BaseFragmentActivity;
import com.zcs.app.advance.demo.drawer.listener.CustomDrawerListener;

public class DrawerActivity extends BaseFragmentActivity {
	/** Constants */
	private static final String CURR_TITLE = "侧滑菜单";

	/** Views */
	private DrawerLayout mDrawerLayout;

	private List<View> mPages;// 页面集合
	private LayoutInflater inflater;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);
		this.inflater = getLayoutInflater();
		super.init();
		mDrawerLayout.setDrawerListener(new CustomDrawerListener(mDrawerLayout));

		getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu, new MenuLeftFragment()).commit();
		getSupportFragmentManager().beginTransaction().replace(R.id.id_right_menu, new MenuRightFragment()).commit();

		initViewPager();
		autoSizi();
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
	}

	private void autoSizi() {
		FrameLayout leftMenu = (FrameLayout) findViewById(R.id.id_left_menu);
		ViewGroup.LayoutParams lp = leftMenu.getLayoutParams();
		lp.width = (int) (getScreenWidth() * 0.8);
	}

	public void openRightMenu() {
		mDrawerLayout.openDrawer(Gravity.RIGHT);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, Gravity.RIGHT);
	}

	public void openLeftMenu() {
		mDrawerLayout.openDrawer(Gravity.LEFT);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebtn_left_act:// 返回
			openLeftMenu();
			break;
		case R.id.titletxt_right_act:// 帮助
			openRightMenu();
			break;
		default:
			break;
		}
	}

	@Override
	protected void initTitlebar() {

		// TODO 初始化标题栏
		super.titlebarView = findViewById(R.id.common_titlebar);

		// 左侧返回按钮
		super.titleBtnLeft = (ImageView) titlebarView.findViewById(R.id.titlebtn_left_act);
		super.titleBtnLeft.setOnClickListener(this);
		super.titleBtnLeft.setImageResource(R.drawable.ic_profile);

		// 中间文字
		super.titleTxtCenter = (TextView) titlebarView.findViewById(R.id.titletxt_center_act);
		super.titleTxtCenter.setText(CURR_TITLE);

		// 右侧文字
		super.titleTxtRight = (TextView) titlebarView.findViewById(R.id.titletxt_right_act);
		super.titleTxtRight.setOnClickListener(this);
	}

	@Override
	protected void initComponent() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
		viewPager = (ViewPager) findViewById(R.id.vp_drawer_index);
	}

	public void closeLeftMenu() {
		mDrawerLayout.closeDrawer(Gravity.LEFT);
	}
}
