package com.zcs.app.advance.demo.infinite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseFragmentActivity;
import com.zcs.app.advance.demo.infinite.views.ImageFragment;
import com.zcs.app.advance.demo.infinite.views.InfinitePagerAdapter;
import com.zcs.app.advance.demo.infinite.views.InfiniteViewPager;
import com.zcs.app.advance.demo.infinite.views.MinFragmentPagerAdapter;

public class InfiniteActivity extends BaseFragmentActivity {
	/** Constants */
	private static final String CURR_TITLE = "无限滚动";
	private static final int[] ADS = { R.drawable.ad_1, R.drawable.ad_2, R.drawable.ad_3 };

	/** Variables */
	InfiniteViewPager mViewPager;

	/** Views */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infinite);
		// 初始化组件
		super.init();

		initViewPager();
	}

	private void initViewPager() {
		FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return ADS.length;
			}

			@Override
			public Fragment getItem(int position) {
				Fragment fragment = new ImageFragment();
				Bundle args = new Bundle();
				args.putInt("ad_id", ADS[position]);
				args.putInt("identifier", position);
				fragment.setArguments(args);
				return fragment;
			}
		};

		// wrap pager to provide a minimum of 4 pages
		MinFragmentPagerAdapter wrappedMinAdapter = new MinFragmentPagerAdapter(getSupportFragmentManager());
		wrappedMinAdapter.setAdapter(adapter);

		// wrap pager to provide infinite paging with wrap-around
		PagerAdapter wrappedAdapter = new InfinitePagerAdapter(wrappedMinAdapter);

		// actually an InfiniteViewPager
		ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(wrappedAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				position = position % ADS.length;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
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
