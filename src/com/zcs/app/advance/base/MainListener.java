package com.zcs.app.advance.base;

import android.support.v4.app.Fragment;

public interface MainListener {
	/**
	 * 切换主Fragment内容
	 * 
	 * @param targetFragment
	 *            要展示的Fragment
	 */
	public void changeMainFragment(Fragment targetFragment);

	public void changeActivity(Class<?> cls);

	/**
	 * 切换主Fragment内容
	 * 
	 * @param currFragment
	 *            当前Fragment
	 * @param targetFragment
	 *            目标Fragment
	 * @param direction
	 *            滑动方向
	 */
	public void changeMainFragment(Fragment currFragment, Fragment targetFragment, int direction);

	public void showToast(String text);

	public void showToastSingle(String text);

	public String getVersionName();
	
	public void openMenu(int index);
	
	public void setCurrentTab(int index);

	public int getScreenWidth();
}
