package com.zcs.app.advance.demo.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseActivity;

/**
 * 智力拼图游戏
 * 
 * @author ZengCS
 * @since 2015年3月20日16:19:23
 */
public class PuzzleActivity extends BaseActivity implements OnItemClickListener {
	/** Constants */
	private static final String CURR_TITLE = "智力拼图";
	private static final int RANDOM_COUNT = 33;// 打乱图片次数
	private static int rows;
	private static int max;

	/** Variables */
	private GridView mGridView;
	private PuzzleGridAdapter mAdapter;
	private List<PuzzleItem> mItems;
	private int currEmptyPosition;// 当前空白块的位置
	private int[] currPuzzle;
	private int stepCount = 0;// 步数
	private Random rand;

	/** Views */
	private TextView tvSteps;
	private FrameLayout puzzleLayout;
	private ImageView lastImg;
	private Button btnRestart, btn9, btn16;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzzle);

		rand = new Random();

		// 初始化组件
		super.init();

		initData(3);
	}

	/**
	 * 初始化数据
	 */
	private void initData(int row) {
		// 还原部分数据
		stepCount = 0;
		tvSteps.setText(String.valueOf(stepCount));
		lastImg.setVisibility(View.GONE);

		// 设置拼图
		row = row <= 0 ? 3 : row;
		mGridView.setNumColumns(row);
		rows = row;
		max = row * row;
		if (row == 3) {
			if (rand.nextBoolean()) {
				currPuzzle = PuzzleProvider.QQFamily.puzzle();
			} else {
				currPuzzle = PuzzleProvider.Doraemon.puzzle();
			}
		} else if (row == 4) {
			currPuzzle = PuzzleProvider.Nobita.puzzle();
		}

		mItems = new ArrayList<PuzzleItem>(max);

		for (int i = 0; i < max; i++) {
			PuzzleItem item = new PuzzleItem();
			item.setPosition(i);
			if (i != max - 1) {
				item.setImgId(currPuzzle[i]);
			} else {
				currEmptyPosition = i;
			}
			mItems.add(item);
		}

		// 打乱顺序
		for (int i = 0; i < RANDOM_COUNT; i++) {
			randomPuzzle();
		}

		// 初始化Adapter
		mAdapter = new PuzzleGridAdapter(getScreenWidth(), mItems, this, rows, puzzleLayout);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(this);
	}

	/**
	 * 打乱顺序
	 */
	private void randomPuzzle() {
		int i = rand.nextInt(max);
		if (isMoveAble(i)) {
			Collections.swap(mItems, i, currEmptyPosition);
			currEmptyPosition = i;
		} else {
			randomPuzzle();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_grid_9:// 九宫格模式
			initData(3);
			v.setEnabled(false);
			btn16.setEnabled(true);
			break;
		case R.id.btn_grid_16:// 十六宫格模式
			initData(4);
			btn9.setEnabled(true);
			v.setEnabled(false);
			break;
		case R.id.btn_restart:// 重新开始
			initData(rows);
			break;
		case R.id.titlebtn_left_act:// 返回
			finish();
			break;
		case R.id.titletxt_right_act:
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
		// TODO init the gridview
		mGridView = (GridView) findViewById(R.id.gv_puzzle);
		mGridView.setNumColumns(rows);
		mGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		mGridView.setSelector(R.color.transparent);

		// init other views
		tvSteps = (TextView) findViewById(R.id.tv_steps);
		puzzleLayout = (FrameLayout) findViewById(R.id.puzzle_layout);
		lastImg = (ImageView) findViewById(R.id.img_puzzle_complete);

		// Buttons
		btnRestart = (Button) findViewById(R.id.btn_restart);
		btnRestart.setOnClickListener(this);
		btn9 = (Button) findViewById(R.id.btn_grid_9);
		btn16 = (Button) findViewById(R.id.btn_grid_16);

		btn9.setOnClickListener(this);
		btn16.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// PuzzleItem item = (PuzzleItem) mAdapter.getItem(position);
		if (currEmptyPosition == -1) {
			showToast("恭喜你，已完成！");
		} else if (position == currEmptyPosition) {
			showToast("请点击非空白图片");
		} else {
			boolean moveAble = isMoveAble(position);
			if (moveAble) {
				stepCount++;// 步数自增长
				// 调换两个图片的位置
				Collections.swap(mItems, position, currEmptyPosition);
				currEmptyPosition = position;
				mAdapter.notifyDataSetInvalidated();

				if (isComplete()) {// 完成
					showToast("恭喜你，已完成！");

					lastImg.setVisibility(View.VISIBLE);
					lastImg.setImageResource(currPuzzle[max - 1]);
					Animation anim = AnimationUtils.loadAnimation(PuzzleActivity.this, R.anim.zoom_in_puzzle);
					lastImg.startAnimation(anim);

					// mItems.get(currEmptyPosition).setImgId(currPuzzle[max -
					// 1]);
					currEmptyPosition = -1;
				}
				tvSteps.setText(String.valueOf(stepCount));
			}
		}
	}

	/**
	 * 是否已完成
	 * 
	 * @param position
	 * @return
	 */
	private boolean isComplete() {
		boolean isComplete = true;
		if (currEmptyPosition != max - 1) {
			isComplete = false;
		} else {
			int position = 0;
			for (PuzzleItem item : mItems) {
				if (item.getPosition() != position) {
					isComplete = false;
					break;
				}
				position++;
			}
		}
		return isComplete;
	}

	/**
	 * 点击的图片是否是可移动的
	 * 
	 * @param position
	 * @return
	 */
	private boolean isMoveAble(int position) {
		int top = currEmptyPosition - rows;
		int bottom = currEmptyPosition + rows;
		int left = currEmptyPosition - 1;
		int right = currEmptyPosition + 1;

		// TODO 先判断是不是边缘图片
		if ((currEmptyPosition + 1) % rows == 1) {// 位于行首
			if (position == right) {
				return true;
			}
		} else if ((currEmptyPosition + 1) % rows == 0) {// 位于行尾
			if (position == left) {
				return true;
			}
		} else {// 位于行中部
			if (position == left) {
				return true;
			}
			if (position == right) {
				return true;
			}
		}
		if (position == top) {
			return true;
		}
		if (position == bottom) {
			return true;
		}
		return false;
	}

}
