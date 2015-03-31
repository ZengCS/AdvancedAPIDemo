package com.zcs.app.advance.acts;

import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.adapter.MainOptionListAdapter;
import com.zcs.app.advance.base.BaseActivity;
import com.zcs.app.advance.entity.MainOptionItem;
import com.zcs.app.advance.provider.MainOptionListProvider;
import com.zcs.app.advance.utils.DialogParam;
import com.zcs.app.advance.utils.DialogUtil;

public class MainActivity extends BaseActivity implements OnItemClickListener {
	/** Constants */
	private static final String CURR_TITLE = "";
	private static final String GIT_HOST = "https://github.com/zcs417327734";

	/** Variables */
	private long lastBackTime = 0;// 上次点击back键的时间

	/** Views */
	private Dialog msgDialog;
	private ListView mListView;

	/** ListView */
	private List<MainOptionItem> mItems;
	private MainOptionListAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Init Variable

		// 初始化组件
		super.init();
		initData();
	}

	private void initData() {
		mItems = MainOptionListProvider.getMainOptions();
		mAdapter = new MainOptionListAdapter(this, mItems);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titletxt_right_act:// 关于
			if (msgDialog == null) {
				initMsgDialog();
			}
			msgDialog.show();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// TODO 监控、拦截、屏蔽返回键
			if ((System.currentTimeMillis() - lastBackTime) < 2000) {
				mToast.cancel();
				super.onBackPressed();
			} else {
				lastBackTime = System.currentTimeMillis();
				showToast("再按一次退出");
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void initTitlebar() {

		// TODO 初始化标题栏
		super.titlebarView = findViewById(R.id.common_titlebar);

		// 左侧返回按钮
		super.titleBtnLeft = (ImageView) titlebarView.findViewById(R.id.titlebtn_left_act);
		// super.titleBtnLeft.setOnClickListener(this);
		super.titleBtnLeft.setVisibility(View.INVISIBLE);

		// 中间文字
		super.titleTxtCenter = (TextView) titlebarView.findViewById(R.id.titletxt_center_act);
		if (!TextUtils.isEmpty(CURR_TITLE)) {
			super.titleTxtCenter.setText(CURR_TITLE);
		}

		// 右侧文字
		super.titleTxtRight = (TextView) titlebarView.findViewById(R.id.titletxt_right_act);
		super.titleTxtRight.setText("关于");
		super.titleTxtRight.setOnClickListener(this);
	}

	@Override
	protected void initComponent() {
		mListView = (ListView) findViewById(R.id.lv_main_option);
	}

	private void initMsgDialog() {
		StringBuffer sb = new StringBuffer();
		sb.append("E-Mail：zengcs@vip.qq.com");
		sb.append("\nG-Mail：zcs417327734@gmail.com");
		sb.append("\n\nGitHub托管主页");
		sb.append("\n" + GIT_HOST);

		DialogParam param = new DialogParam(this, sb.toString(), false);
		StringBuffer sb_title = new StringBuffer();
		sb_title.append(getString(R.string.app_name) + " Ver" + getVersionName());
		sb_title.append("\n作者：曾成顺");
		param.setTitle(sb_title.toString());
		param.setOkBtnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				msgDialog.dismiss();
			}
		});
		String[] highStrs = { GIT_HOST };
		int highColor = getResources().getColor(R.color.C4);
		msgDialog = DialogUtil.createMessageDialog(param, highStrs, highColor);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		MainOptionItem item = (MainOptionItem) mAdapter.getItem(position);
		if (item != null) {
			Intent intent = new Intent(this, item.getTargetActivity());
			startActivity(intent);
		}
	}
}
