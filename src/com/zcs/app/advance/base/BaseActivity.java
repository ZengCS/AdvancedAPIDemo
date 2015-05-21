package com.zcs.app.advance.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zcs.app.advance.R;
import com.zcs.app.advance.utils.DialogParam;
import com.zcs.app.advance.utils.DialogUtil;

public abstract class BaseActivity extends Activity implements OnClickListener, IBaseConstant {
	/** Constants */
	protected final static String TAG = "BaseActivity";
	protected static final long SHOW_TOP_TIP_DELAY = 150;
	private static Animation ANIM_TOP_TIP_IN;
	private static Animation ANIM_TOP_TIP_OUT;

	/** Variable */
	protected Gson gson;
	protected Dialog mHelpDialog;

	/** Views */
	protected View netErrorLayout, loadingLayout;
	// 顶部提示信息
	private View topLayout;
	private TextView topTitle, topMsg;

	/** 屏幕尺寸 */
	private int screenWidth;
	private int screenHeight;

	/** 标题栏View */
	protected View titlebarView;
	protected ImageView titleBtnLeft, titleBtnRight;
	protected TextView titleTxtCenter, titleTxtRight;

	protected RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 注册未caught异常处理器
		// Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(this));

		getWindow().setBackgroundDrawable(null);
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		screenWidth = mDisplayMetrics.widthPixels;
		screenHeight = mDisplayMetrics.heightPixels;
		gson = new Gson();
		mQueue = Volley.newRequestQueue(this);
		if (ANIM_TOP_TIP_IN == null || ANIM_TOP_TIP_OUT == null) {
			ANIM_TOP_TIP_IN = AnimationUtils.loadAnimation(this, R.anim.push_down_in);
			ANIM_TOP_TIP_OUT = AnimationUtils.loadAnimation(this, R.anim.push_up_out);
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler baseHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case WHAT_HIDE_TOP_TIP_DELAY:
				hideTopTip(true);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 显示帮助Dialog
	 */
	protected void displayHelpDialog() {
		if (mHelpDialog == null) {
			initHelpDialog();
		}
		mHelpDialog.show();
	}

	/**
	 * 初始化帮助Dialog
	 */
	private void initHelpDialog() {
		DialogParam param = new DialogParam(this, getString(R.string.act_info, this.getClass().getName(), getString(R.string.source_info)), true);
		param.setTitle("帮助信息");
		param.setTextIsSelectable(true);
		param.setOkBtnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mHelpDialog.dismiss();
			}
		});
		String[] highStrs = new String[] { this.getClass().getName(), "https://github.com/zcs417327734/AdvanceAPIDemo", "zengcs@vip.qq.com" };
		int highColor = Color.parseColor("#E96B69");// 红色
		mHelpDialog = DialogUtil.createMessageDialog(param, highStrs, highColor);
	}

	/**
	 * 初始化布局，内部调用initTitlebar()和initComponent()
	 */
	protected void init() {
		initTitlebar();
		initComponent();
		initTopTip();
	}

	/**
	 * 初始化顶部提示信息
	 */
	private void initTopTip() {
		topLayout = findViewById(R.id.top_tip_layout);
		if (topLayout != null) {
			topTitle = (TextView) topLayout.findViewById(R.id.top_tip_title);
			topMsg = (TextView) topLayout.findViewById(R.id.top_tip_msg);
		}
	}

	/**
	 * 显示顶部提示信息
	 * 
	 * @param isError
	 *            是否是错误信息
	 * @param msg
	 *            信息内容
	 * @param autoHide
	 *            是否自动消失
	 * @param delayMillis
	 *            延时,单位ms
	 */
	protected void showTopTip(final boolean isError, final String msg, final boolean autoHide, long delayMillis) {
		baseHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				showTopTip(isError, msg, autoHide);
			}
		}, delayMillis);
	}

	/**
	 * 显示顶部提示信息
	 * 
	 * @param isError
	 *            是否是错误信息
	 * @param msg
	 *            信息内容
	 * @param autoHide
	 *            是否自动消失
	 */
	protected void showTopTip(boolean isError, String msg, boolean autoHide) {
		if (topLayout == null) {
			showToast(msg);
			return;
		}
		int bgColor;
		if (isError) {// 显示错误提示
			bgColor = getResources().getColor(R.color.v2_maizi_red);
			topTitle.setText("错误");
		} else {// 显示正常提示
			bgColor = getResources().getColor(R.color.v2_maizi_blue);
			topTitle.setText("提示");
		}
		topMsg.setText(msg);
		topLayout.setBackgroundColor(bgColor);
		// 显示布局
		topLayout.setVisibility(View.VISIBLE);
		topLayout.startAnimation(ANIM_TOP_TIP_IN);
		if (autoHide) {
			// 开启延时隐藏
			baseHandler.removeMessages(WHAT_HIDE_TOP_TIP_DELAY);
			baseHandler.sendEmptyMessageDelayed(WHAT_HIDE_TOP_TIP_DELAY, TOP_TIP_DURATION);
		} else {
			baseHandler.removeMessages(WHAT_HIDE_TOP_TIP_DELAY);
		}
	}

	/**
	 * 隐藏顶部提示信息
	 */
	protected void hideTopTip(boolean animation) {
		baseHandler.removeMessages(WHAT_HIDE_TOP_TIP_DELAY);
		if (topLayout == null) {
			return;
		}
		if (topLayout.getVisibility() == View.VISIBLE) {
			if (animation) {
				topLayout.startAnimation(ANIM_TOP_TIP_OUT);
			}
			topLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 展示网络错误提示界面
	 * 
	 * @param isError
	 *            是否网络错误
	 */
	protected void displayNetErrorLayout(boolean isError) {
		try {
			if (isError) {
				netErrorLayout.setVisibility(View.VISIBLE);
				if (loadingLayout != null) {
					loadingLayout.setVisibility(View.GONE);
				}
			} else {
				netErrorLayout.setVisibility(View.GONE);
				if (loadingLayout != null) {
					loadingLayout.setVisibility(View.VISIBLE);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化Titlebar，在super.init()中自动调用此方法
	 */
	protected abstract void initTitlebar();

	/**
	 * 初始化组件，在super.init()中自动调用此方法
	 */
	protected abstract void initComponent();

	protected static Toast mToast;
	private static Context context;

	protected void showToast(String text) {
		if (context == null) {
			context = this;
		}
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	/**
	 * 获取版本名
	 * 
	 * @return 当前应用的版本名
	 */
	public String getVersionName() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "0.0.0";
		}
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param view
	 */
	protected void hideSoftInputFromWindow(View view) {
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示软键盘
	 * 
	 * @param view
	 */
	protected void showSoftInput(View view) {
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		hideTopTip(false);// 隐藏顶部提示栏
	}

	@Override
	protected void onStop() {
		super.onStop();
		hideTopTip(false);// 隐藏顶部提示栏
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		hideTopTip(false);// 隐藏顶部提示栏
		if (mQueue != null) {
			mQueue.cancelAll(this);
		}
	}
}
