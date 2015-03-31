package com.zcs.app.advance.demo.index;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragment;

public class ProfileFragment extends BaseFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "ProfileFragment onCreateView called!");
		root = inflater.inflate(R.layout.fragment_profile, null);
		super.init();
		mListener.showToast("初始化:ProfileFragment");
		return root;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if (hidden) {// 被隐藏
			Log.d(TAG, "ProfileFragment hidden");
		} else {// 被显示
			Log.d(TAG, "ProfileFragment display");
		}
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	protected void initComponent() {
	}

}
