package com.zcs.app.advance.demo.avatar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zcs.app.advance.R;
import com.zcs.app.advance.base.BaseActivity;
import com.zcs.app.advance.demo.avatar.views.ClipSquareImageView;

/**
 * 头像裁剪
 * 
 * @author ZengCS
 * @since 2015年3月27日14:19:17
 */
public class ClipAvatarActivity extends BaseActivity {
	/** Constants */
	private static final String CURR_TITLE = "头像裁剪";
	public static final String AVATAR_CACHE_DIR = Environment.getExternalStorageDirectory() + File.separator + "AvatarTemp";
	public static final String AVATAR_TEMP_URL = AVATAR_CACHE_DIR + File.separator + "avatar_temp.jpg";
	public static final String AVATAR_URL = AVATAR_CACHE_DIR + File.separator + "avatar.jpg";

	private static final int RESULT_TAKE_CAMEAR = 10;
	private static final int RESULT_TAKE_ALBUM = 11;

	/** Variables */

	/** Views */
	private ClipSquareImageView clipImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clip_avatar);

		File dir = new File(AVATAR_CACHE_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 初始化组件
		super.init();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebtn_left_act:// 返回
			finish();
			break;
		case R.id.titletxt_right_act:
			displayHelpDialog();
			break;
		case R.id.photoBtn:// 拍照
			Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(AVATAR_TEMP_URL)));
			startActivityForResult(cameraIntent, RESULT_TAKE_CAMEAR);
			break;
		case R.id.selectBtn:// 从相册选择
			Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
			albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
			startActivityForResult(albumIntent, RESULT_TAKE_ALBUM);
			break;
		case R.id.doneBtn:// 完成
			// 此处获取剪裁后的bitmap
			Bitmap bitmap = clipImageView.clip();

			// 由于Intent传递bitmap不能超过40k,此处使用二进制数组传递
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			byte[] bitmapByte = baos.toByteArray();

			Intent intent = new Intent(ClipAvatarActivity.this, ResultActivity.class);
			intent.putExtra("bitmap", bitmapByte);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RESULT_TAKE_CAMEAR && resultCode == RESULT_OK) {
			Bitmap bmp = BitmapFactory.decodeFile(AVATAR_TEMP_URL);
			Matrix matrix = new Matrix();
			matrix.postScale(0.5f, 0.5f);
			bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
			clipImageView.setIniting(false);
			clipImageView.setImageBitmap(bmp);
		} else if (requestCode == RESULT_TAKE_ALBUM && resultCode == RESULT_OK) {
			if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				return;
			}
			Uri uri = data.getData();
			try {
				Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
				clipImageView.setIniting(false);
				clipImageView.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
		clipImageView = (ClipSquareImageView) findViewById(R.id.clipSquareIV);
		clipImageView.setImageBitmap(bmp);

		findViewById(R.id.doneBtn).setOnClickListener(this);
		findViewById(R.id.photoBtn).setOnClickListener(this);
		findViewById(R.id.selectBtn).setOnClickListener(this);
	}

}
