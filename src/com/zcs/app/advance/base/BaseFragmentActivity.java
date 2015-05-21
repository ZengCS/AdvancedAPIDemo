package com.zcs.app.advance.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.utils.DialogParam;
import com.zcs.app.advance.utils.DialogUtil;

public abstract class BaseFragmentActivity extends FragmentActivity implements OnClickListener, IBaseConstant {
	protected final static String TAG = "BaseFragmentActivity";

	private int screenWidth;
	private int screenHeight;

	/** Constants */
	private static Animation ANIM_TOP_TIP_IN;
	private static Animation ANIM_TOP_TIP_OUT;

	/** 标题栏View */
	protected View titlebarView;
	protected ImageView titleBtnLeft, titleBtnRight;
	protected TextView titleTxtCenter, titleTxtRight;

	protected Dialog mHelpDialog;

	// 顶部提示信息
	private View topLayout;
	private TextView topTitle, topMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		screenWidth = mDisplayMetrics.widthPixels;
		screenHeight = mDisplayMetrics.heightPixels;
		if (ANIM_TOP_TIP_IN == null || ANIM_TOP_TIP_OUT == null) {
			ANIM_TOP_TIP_IN = AnimationUtils.loadAnimation(this, R.anim.push_down_in);
			ANIM_TOP_TIP_OUT = AnimationUtils.loadAnimation(this, R.anim.push_up_out);
		}
	}

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

	@Override
	protected void onResume() {
		super.onResume();
		setDefualtFont();
	}

	@SuppressLint("HandlerLeak")
	private Handler baseHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case WHAT_HIDE_TOP_TIP_DELAY:
				hideTopTip();
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 初始化布局,内部调用initTitlebar()和initComponent()
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
	 */
	protected void showTopTip(boolean isError, String msg, boolean autoHide) {
		if (topLayout == null) {
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
	protected void hideTopTip() {
		baseHandler.removeMessages(WHAT_HIDE_TOP_TIP_DELAY);
		if (topLayout == null) {
			return;
		}
		if (topLayout.getVisibility() == View.VISIBLE) {
			topLayout.startAnimation(ANIM_TOP_TIP_OUT);
			topLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 初始化Titlebar,在super.init()中自动调用此方法
	 */
	protected abstract void initTitlebar();

	/**
	 * 初始化组件,在super.init()中自动调用此方法
	 */
	protected abstract void initComponent();

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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		hideTopTip();// 隐藏顶部提示栏
	}

	private void setDefualtFont() {
		try {
			Resources res = getResources();
			Configuration config = new Configuration();
			config.setToDefaults();
			res.updateConfiguration(config, res.getDisplayMetrics());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
