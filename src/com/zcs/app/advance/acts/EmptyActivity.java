package com.zcs.app.advance.acts;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseActivity;

public class EmptyActivity extends BaseActivity {
	/** Constants */
	private static final String CURR_TITLE = "EmptyActivity";

	/** Variables */

	/** Views */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empty);
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

	}

}
