package com.zcs.app.advance.demo.flow;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseActivity;
import com.zcs.app.advance.demo.flow.view.FlowLayout;

public class FlowLayoutActivity extends BaseActivity {
	/** Constants */
	private static final String CURR_TITLE = "流式布局";
	private static final String[] LETTERS = { "A", "B", "C", "D", "E", "F", "H", "I", "J", "K", "L", "M", "N" };

	/** Variables */
	private LinearLayout.LayoutParams lp;

	/** Views */
	private FlowLayout mFlowLayout;
	private EditText addEt;
	private Button addBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow_layout);
		// 初始化组件
		super.init();
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(16, 16, 16, 16);

		addTag("Added onCreate");
		addTag("Name");
		addTag("Is");
		addTag("ZengCS");
		addTag("QQ");
		addTag("Is");
		addTag("417327734");
		addTag("E-mail");
		addTag("Is");
		addTag("zengcs@vip.qq.com");
		for (String s : LETTERS) {
			addTag(s);
		}
		addTag("onCreate Complete");
	}

	/**
	 * 添加标签
	 * 
	 * @param tag
	 */
	private void addTag(String tag) {
		TextView child = new TextView(this);
		child.setText(tag);
		child.setLayoutParams(lp);
		child.setBackgroundResource(R.drawable.flag_01);
		child.setTextAppearance(this, R.style.text_flag_01);
		mFlowLayout.addView(child);
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
		case R.id.btn_add:// 添加
			String tag = String.valueOf(addEt.getText());
			if (TextUtils.isEmpty(tag)) {
				showToast("请输入标签");
			} else {
				addTag(tag);
				addEt.setText(null);
			}
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
		mFlowLayout = (FlowLayout) findViewById(R.id.custom_flow_layout);
		addBtn = (Button) findViewById(R.id.btn_add);
		addEt = (EditText) findViewById(R.id.et_add);

		addBtn.setOnClickListener(this);
	}

}
