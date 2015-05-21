package com.zcs.app.advance.demo.radar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseActivity;
import com.zcs.app.advance.demo.radar.view.RadarView;

public class RadarActivity extends BaseActivity {
	/** Constants */
	private static final String CURR_TITLE = "雷达";
	private static final float RADAR_SCALE_LEVEL = 0.9f;// 雷达缩放比例0.0~1.0之间，1.0表示不缩放

	/** Variables */
	private boolean startRadar = true;
	private boolean isFirst = true;// 是不是第一次
	private boolean hideOnEnd = false;// 结束时是否隐藏

	/** Views */
	private RadarView radarView;
	private Button controlBtn;
	private Thread radarSweepThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radar);
		// 初始化组件
		super.init();
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
		radarView = (RadarView) findViewById(R.id.radar);
		ViewGroup.LayoutParams lp = radarView.getLayoutParams();
		int radarSize = (int) (getScreenWidth() * RADAR_SCALE_LEVEL);
		lp.width = radarSize;
		lp.height = radarSize;

		controlBtn = (Button) findViewById(R.id.radar_control_btn);
		controlBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (startRadar) {
					controlBtn.setText(getString(R.string.txt_stop_en));
					controlBtn.setBackgroundResource(R.drawable.bg_red_selector);
					if (isFirst) {
						radarView.setVisibility(View.VISIBLE);// 设置可见
						Animation radarAnimEnter = AnimationUtils.loadAnimation(RadarActivity.this, R.anim.radar_anim_enter);// 初始化radarView进入动画
						radarView.startAnimation(radarAnimEnter);// 开始进入动画
						isFirst = false;
					}
					radarSweepThread = new Thread(new RadarSweep());// 雷达扫描线程
					radarSweepThread.start();
					startRadar = false;
				} else {
					controlBtn.setText(getString(R.string.txt_start_en));
					controlBtn.setBackgroundResource(R.drawable.bg_blue_selector);
					if (hideOnEnd) {// 结束时隐藏
						Animation radarAnimEnter = AnimationUtils.loadAnimation(RadarActivity.this, R.anim.radar_anim_exit);// 初始化radarView退出动画
						radarView.startAnimation(radarAnimEnter);// 开始进入动画
						radarView.setVisibility(View.INVISIBLE);// 设置不可见
					}
					radarSweepThread.interrupt();// 停止扫描更新
					startRadar = true;
				}
			}
		});
	}

	/**
	 * @ClassName RadarSweep
	 * @Description 雷达扫描动画刷新线程类
	 */
	private class RadarSweep implements Runnable {
		int i = 1;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!Thread.currentThread().isInterrupted() && i == 1) {
				try {
					radarView.postInvalidate();// 刷新radarView, 执行onDraw();
					Thread.sleep(10);// 暂停当前线程，更新UI线程
				} catch (InterruptedException e) {
					i = 0;// 结束当前扫描线程标志符
					break;
				}
			}
		}

	}

}
