package com.zcs.app.advance.demo.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragmentActivity;
import com.zcs.app.advance.base.MainListener;

public class IndexHomeActivity extends BaseFragmentActivity implements MainListener {
	/** Constants */
	private static final String CURR_TITLE = "主界面Title";

	/** Variables */

	/** Views */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index_home);
		// 初始化组件
		super.init();

		// 设置内容Fragment(主)
		TabFragment.mainFragment = new ProfileFragment();
		TabFragment.currFragment = TabFragment.mainFragment;
		changeMainFragment(null, TabFragment.mainFragment, -1);

		// 设置Tab
		getSupportFragmentManager().beginTransaction().replace(R.id.tab_frame, new TabFragment()).commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebtn_left_act:// 返回
			finish();
			break;
		case R.id.titletxt_right_act:// 帮助信息
			displayHelpDialog();
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

		// 中间文字
		super.titleTxtCenter = (TextView) titlebarView.findViewById(R.id.titletxt_center_act);
		super.titleTxtCenter.setText(CURR_TITLE);

		// 右侧文字
		super.titleTxtRight = (TextView) titlebarView.findViewById(R.id.titletxt_right_act);
		super.titleTxtRight.setOnClickListener(this);
	}

	@Override
	protected void initComponent() {

	}

	@Override
	public void changeMainFragment(Fragment targetFragment) {
		// TODO Auto-generated method stub
	}

	@Override
	public void changeActivity(Class<?> cls) {
		if (cls == null) {
			return;
		}
		startActivity(new Intent(this, cls));
	}

	@Override
	public void changeMainFragment(Fragment currFragment, Fragment targetFragment, int direction) {
		if (targetFragment == currFragment) {
			return;
		}
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		// TODO 设置滑动方向
		if (direction == TabFragment.DIRECTION_LEFT) {
			transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
		} else if (direction == TabFragment.DIRECTION_RIGHT) {
			transaction.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out);
		}

		// 先判断是否被add过
		if (targetFragment.isAdded()) {
			// 隐藏当前的fragment，显示下一个
			if (currFragment != null) {
				transaction.hide(currFragment);
			}
			transaction.show(targetFragment);
		} else {
			// 隐藏当前的fragment，add下一个到Activity中
			if (currFragment != null) {
				transaction.hide(currFragment);
			}
			transaction.add(R.id.content_frame, targetFragment);
		}
		transaction.commit();
	}

	@Override
	public void showToast(String text) {
		// TODO Auto-generated method stub
	}

	@Override
	public void showToastSingle(String text) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getVersionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openMenu(int index) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setCurrentTab(int index) {
		// TODO Auto-generated method stub
	}
}
