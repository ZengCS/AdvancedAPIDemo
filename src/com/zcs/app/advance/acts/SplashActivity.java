package com.zcs.app.advance.acts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zcs.app.advance.R;

public class SplashActivity extends Activity {
	/** Constants */
	private static final int WHAT_START_INDEX_ACT = 0x001;// 进入主界面标记
	private static final long SPLASH_DELAY = 2000;// 延时

	/** Views */
	private TextView tvVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		tvVersion = (TextView) findViewById(R.id.tv_splash_version);
		tvVersion.setText(getString(R.string.app_name) + " Ver" + getVersionName());

		mHandler.sendEmptyMessageDelayed(WHAT_START_INDEX_ACT, SPLASH_DELAY);
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case WHAT_START_INDEX_ACT:// 进入主界面
				if (!isFinishing()) {
					Intent intent = new Intent(SplashActivity.this, MainActivity.class);
					// 通过Intent打开最终真正的主界面Main这个Activity
					SplashActivity.this.startActivity(intent); // 启动Main界面
					SplashActivity.this.finish(); // 关闭自己这个开场屏
					// overridePendingTransition(R.anim.fade, R.anim.hold);
					overridePendingTransition(R.anim.hold, R.anim.zoom_out);
				}
				break;
			default:
				break;
			}
		}

	};

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
}
