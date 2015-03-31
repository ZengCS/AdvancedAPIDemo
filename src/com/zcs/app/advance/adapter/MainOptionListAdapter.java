package com.zcs.app.advance.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.entity.MainOptionItem;

@SuppressLint("InflateParams")
public class MainOptionListAdapter extends BaseAdapter {
	private final String TAG = "LPSMyCourseListAdapter";
	private Context context;
	private List<MainOptionItem> mItems;

	public MainOptionListAdapter(Context context, List<MainOptionItem> mItems) {
		super();
		Log.d(TAG, "new LPSMyCourseListAdapter");
		this.context = context;
		this.mItems = mItems;
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
		return mItems.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_main_option, null);
			holder = new Holder();
			holder.img = (ImageView) convertView.findViewById(R.id.main_option_img);
			holder.name = (TextView) convertView.findViewById(R.id.main_option_name);
			holder.desc = (TextView) convertView.findViewById(R.id.main_option_desc);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		MainOptionItem item = mItems.get(position);
		holder.img.setImageResource(R.drawable.ic_option);
		holder.name.setText(item.getName());
		holder.desc.setText(item.getDesc());
		
		return convertView;
	}

	/**
	 * 刷新列表
	 */
	public void notifyDataSetChanged(List<MainOptionItem> mItems) {
		this.mItems = mItems;
		super.notifyDataSetChanged();
	}

	private static class Holder {
		TextView name, desc;
		ImageView img;
	}

}
