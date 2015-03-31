package com.zcs.app.advance.demo.puzzle;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zcs.app.advance.R;

public class PuzzleGridAdapter extends BaseAdapter {
	private static final int GAP = 24;
	private static final boolean SHOW_POSITION = false;
	private Context mContext;
	private List<PuzzleItem> mItems;
	private int screenWidth;
	private int rows;

	public PuzzleGridAdapter(int screenWidth, List<PuzzleItem> items, Context mContext, int rows, FrameLayout puzzleLayout) {
		super();
		this.mContext = mContext;
		this.mItems = items;
		this.screenWidth = screenWidth;
		this.rows = rows;

		try {
			LinearLayout.LayoutParams lp = (LayoutParams) puzzleLayout.getLayoutParams();
			lp.setMargins(GAP, GAP, GAP, GAP);

			ImageView lastImg = (ImageView) puzzleLayout.findViewById(R.id.img_puzzle_complete);
			ViewGroup.LayoutParams lp2 = lastImg.getLayoutParams();
			int size = (screenWidth - GAP * 2) / rows;
			lp2.width = size;
			lp2.height = size;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mItems.get(position).getPosition();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.item_puzzle, null);
			holder = new Holder();
			holder.img = (ImageView) convertView.findViewById(R.id.puzzle_img);
			holder.layout = (ViewGroup) convertView.findViewById(R.id.puzzle_layout);
			holder.position = (TextView) convertView.findViewById(R.id.tv_position);

			ViewGroup.LayoutParams lp = holder.layout.getLayoutParams();

			int realSize = (screenWidth - GAP * 2) / rows;
			lp.height = realSize;
			lp.width = realSize;

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		PuzzleItem item = mItems.get(position);
		if (SHOW_POSITION) {
			holder.position.setText(position + " / " + item.getPosition());
			if (item.getPosition() == position) {// 正确的
				holder.position.setBackgroundColor(Color.parseColor("#AA5ECFBA"));
			} else {// 错误的
				holder.position.setBackgroundColor(Color.parseColor("#AAFF725F"));
			}
		} else {
			holder.position.setVisibility(View.GONE);
		}

		if (item.getImgId() != 0) {
			holder.img.setImageResource(item.getImgId());
		} else {
			holder.img.setImageResource(0);
		}

		return convertView;
	}

	public void notifyDataSetChanged(List<PuzzleItem> items) {
		mItems = items;
		super.notifyDataSetChanged();
	}

	private static class Holder {
		ImageView img;
		ViewGroup layout;
		TextView position;
	}
}
