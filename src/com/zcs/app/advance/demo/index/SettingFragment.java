package com.zcs.app.advance.demo.index;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragment;

public class SettingFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "SettingFragment onCreateView called!");
		root = inflater.inflate(R.layout.fragment_setting, null);
		super.init();
		mListener.showToast("初始化:SettingFragment");
		return root;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (hidden) {// 被隐藏
			Log.d(TAG, "SettingFragment hidden");
		} else {// 被显示
			Log.d(TAG, "SettingFragment display");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initComponent() {
		// TODO Auto-generated method stub
	}

}
