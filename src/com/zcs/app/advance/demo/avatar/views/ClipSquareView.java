package com.zcs.app.advance.demo.avatar.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 麦子学院头像裁剪
 * @author ZengCS
 * @since 2014年8月11日
 */
public class ClipSquareView extends View {
	public static final int BORDERDISTANCE = 50;// 距离屏幕的边距
	private final float LINE_BORDER_WIDTH = 2f;// 框框宽度
	// private final int LINE_COLOR = Color.WHITE;
	private final int LINE_COLOR = Color.parseColor("#5ECFBA");
	private final Paint mPaint = new Paint();

	public ClipSquareView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = getWidth();
		int height = getHeight();

		boolean isHorizontal = false;
		if (width > height) {
			isHorizontal = true;
		}

		int outLeft = 0;
		int outTop = 0;
		int outRight = width;
		int outBottom = height;
		int borderlength = isHorizontal ? (height - BORDERDISTANCE * 2) : (width - BORDERDISTANCE * 2);
		int inLeft = isHorizontal ? ((width - borderlength) / 2) : BORDERDISTANCE;
		int inTop = isHorizontal ? BORDERDISTANCE : ((height - borderlength) / 2);
		int inRight = isHorizontal ? (inLeft + borderlength) : borderlength + BORDERDISTANCE;
		int inBottom = isHorizontal ? borderlength + BORDERDISTANCE : (inTop + borderlength);

		mPaint.setColor(Color.parseColor("#76000000"));
		canvas.drawRect(outLeft, outTop, outRight, inTop, mPaint);
		canvas.drawRect(outLeft, inBottom, outRight, outBottom, mPaint);
		canvas.drawRect(outLeft, inTop, inLeft, inBottom, mPaint);
		canvas.drawRect(inRight, inTop, outRight, inBottom, mPaint);

		mPaint.setColor(LINE_COLOR);
		mPaint.setStrokeWidth(LINE_BORDER_WIDTH);

		canvas.drawLine(inLeft, inTop, inLeft, inBottom, mPaint);
		canvas.drawLine(inRight, inTop, inRight, inBottom, mPaint);
		canvas.drawLine(inLeft, inTop, inRight, inTop, mPaint);
		canvas.drawLine(inLeft, inBottom, inRight, inBottom, mPaint);
	}
}
