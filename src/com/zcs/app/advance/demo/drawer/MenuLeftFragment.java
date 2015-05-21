package com.zcs.app.advance.demo.drawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragment;

public class MenuLeftFragment extends BaseFragment {
	protected View root;
	protected DrawerActivity drawerActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		drawerActivity = (DrawerActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.drawer_menu_left, container, false);
		super.init();
		return root;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_close:
			drawerActivity.closeLeftMenu();
			break;
		default:
			break;
		}
	}

	@Override
	protected void initComponent() {
		root.findViewById(R.id.btn_close).setOnClickListener(this);
	}
}
