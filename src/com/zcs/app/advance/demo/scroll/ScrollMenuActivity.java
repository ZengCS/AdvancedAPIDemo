package com.zcs.app.advance.demo.scroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragmentActivity;
import com.zcs.app.advance.base.MainListener;
import com.zcs.app.advance.demo.scroll.views.ScrollMenu;

public class ScrollMenuActivity extends BaseFragmentActivity implements MainListener {
	/** Constants */

	/** Variables */
	private ScrollIndexFragment indexFragment;
	private ScrollTabFragment tabFragment;
	private ScrollMenuFragment menuFragment;
	private static Toast mToast;

	/** Views */
	private ScrollMenu mMenu;
	private View guideLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll_menu);
		// 初始化组件
		super.init();
		indexFragment = new ScrollIndexFragment();
		tabFragment = new ScrollTabFragment();
		menuFragment = new ScrollMenuFragment();
		// 加载主界面
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_content, indexFragment).commit();

		// 加载Tab
		getSupportFragmentManager().beginTransaction().replace(R.id.scroll_tab_frame, tabFragment).commit();

		// 加载Menu
		getSupportFragmentManager().beginTransaction().replace(R.id.layout_menu_detail, menuFragment).commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_i_know:// 我知道了
			guideLayout.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	@Override
	protected void initTitlebar() {
	}

	@Override
	protected void initComponent() {
		mMenu = (ScrollMenu) findViewById(R.id.scroll_menu);
		guideLayout = findViewById(R.id.guide_layout);
		findViewById(R.id.btn_i_know).setOnClickListener(this);
	}

	@Override
	public void changeMainFragment(Fragment targetFragment) {
	}

	@Override
	public void changeActivity(Class<?> cls) {
		if (cls == null) {
			showToast("The target Activity is Null");
			return;
		}
		startActivity(new Intent(this, cls));

	}

	@Override
	public void changeMainFragment(Fragment currFragment, Fragment targetFragment, int direction) {

	}

	@Override
	public void showToast(String text) {
		if (mToast == null) {
			mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
		}
		mToast.show();
	}

	@Override
	public void showToastSingle(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public String getVersionName() {
		return null;
	}

	@Override
	public void openMenu(int index) {
		menuFragment.setCurrentMenu(index);
		mMenu.openMenuDetail();
	}

	@Override
	public void setCurrentTab(int index) {
		tabFragment.tabSelection(index + 1);
	}

	@Override
	public void onBackPressed() {
		if (mMenu.isMenuOpen()) {
			mMenu.closeMenuDetail();
		} else {
			super.onBackPressed();
		}
	}
}
