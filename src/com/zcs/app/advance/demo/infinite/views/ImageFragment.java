package com.zcs.app.advance.demo.infinite.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zcs.app.advance.R;

/**
 * Example Fragment class that shows an identifier inside a TextView.
 */
public class ImageFragment extends Fragment {
	private int identifier;
	private int ad_id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		ad_id = args.getInt("ad_id");
		identifier = args.getInt("identifier");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.common_image_fragment, null);
		ImageView img = (ImageView) root.findViewById(R.id.img_fragment);
		img.setImageResource(ad_id);
		Log.d("ImageFragment", "identifier:" + identifier);
		return root;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("dummy", true);
	}
}
