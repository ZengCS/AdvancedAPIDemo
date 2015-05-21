package com.zcs.app.advance.demo.drawer.listener;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class CustomDrawerListener implements DrawerListener {
	private DrawerLayout mDrawerLayout;

	public CustomDrawerListener(DrawerLayout mDrawerLayout) {
		this.mDrawerLayout = mDrawerLayout;
	}

	@Override
	public void onDrawerStateChanged(int newState) {
	}

	@Override
	public void onDrawerSlide(View drawerView, float slideOffset) {
		View mContent = mDrawerLayout.getChildAt(0);
		View mMenu = drawerView;
		float scale = 1 - slideOffset;
		float rightScale = 0.8f + scale * 0.2f;
		float rightScale_Y = 0.85f + scale * 0.15f;

		if (drawerView.getTag().equals("LEFT")) {
			// float leftScale = 1 - 0.5f * scale;

			// ViewHelper.setScaleX(mMenu, leftScale);
			// ViewHelper.setScaleY(mMenu, leftScale);
			//
			// ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
			ViewHelper.setTranslationX(mContent, mMenu.getMeasuredWidth() * (1 - scale));
			ViewHelper.setPivotX(mContent, 0);
			ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
			mContent.invalidate();
			ViewHelper.setScaleX(mContent, rightScale);

			ViewHelper.setScaleY(mContent, rightScale_Y);
		} else {
			ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * slideOffset);
			ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
			ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
			mContent.invalidate();
			// ViewHelper.setScaleX(mContent, rightScale);
			// ViewHelper.setScaleY(mContent, rightScale);
		}
	}

	@Override
	public void onDrawerOpened(View drawerView) {
	}

	@Override
	public void onDrawerClosed(View drawerView) {
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
	}
}
