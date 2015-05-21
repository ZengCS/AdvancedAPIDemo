package com.zcs.app.advance.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseFragment extends Fragment implements OnClickListener {
	/** Constant */
	protected final static String TAG = "BaseFragment";
	protected static final long INIT_DELAY = 350;// 初始化数据延时
	protected static final long TIP_DELAY = 1500;// tip展示时间
	protected static final long SAVE_DB_DELAY = 400;// 延时保存到数据库
	// Handler
	protected static final int WHAT_NOTIFY = 0x001;// 告知更新数据
	protected static final int WHAT_REFRESH = 0x002;// 下拉刷新
	protected static final int WHAT_LOAD_MORE = 0x003;// 加载更多
	protected static final int WHAT_INIT = 0x004;// 初始化数据
	protected static final int WHAT_LOAD_FAILED = 0x005;// 数据加载失败
	protected static final int WHAT_SAVE_TO_DB = 0x006;// 保存到数据库
	protected static final int WHAT_RELOAD = 0x007;// 重新加载

	/** Variable */
	protected MainListener mListener;
	protected Context mContext;

	/** Views */
	protected View root;
	protected View netErrorLayout;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (MainListener) activity;
		} catch (Exception e) {
			mListener = null;
		}
		mContext = activity;
	}

	/**
	 * 展示网络错误提示界面
	 * 
	 * @param isError
	 *            是否网络错误
	 */
	protected void displayNetErrorLayout(boolean isError) {
		if (isError) {
			netErrorLayout.setVisibility(View.VISIBLE);
		} else {
			netErrorLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 初始化布局,内部调用initTitlebar()和initComponent()
	 */
	protected void init() {
		initComponent();
	}

	/**
	 * 初始化组件
	 */
	protected abstract void initComponent();
}
