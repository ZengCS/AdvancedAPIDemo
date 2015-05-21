package com.zcs.app.advance.demo.radar.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.zcs.app.advance.R;

public class RadarView extends View {
	private Paint circlePaint;// 圆形画笔
	private Paint linePaint;// 线形画笔
	private Paint sweepPaint;// 扫描画笔
	private SweepGradient sweepGradient;// 扇形渐变Shader
	private int degree = 0;

	public RadarView(Context context) {
		this(context, null);
	}

	public RadarView(Context context, AttributeSet att) {
		super(context, att);
		initPaint();
	}

	/**
	 * @param
	 * @return void
	 * @Description //初始化定义的画笔
	 */
	private void initPaint() {
		Resources r = this.getResources();

		circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 圆形画笔，设置Paint为抗锯齿
		circlePaint.setARGB(255, 50, 57, 74);// 设置透明度和RGB颜色
		circlePaint.setStrokeWidth(3);// 轮廓宽度
		circlePaint.setStyle(Paint.Style.STROKE);

		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 线性画笔
		linePaint.setStrokeCap(Paint.Cap.ROUND);
		linePaint.setARGB(150, 50, 57, 74);
		linePaint.setStrokeWidth(2);

		sweepPaint = new Paint(Paint.ANTI_ALIAS_FLAG);// 雷达Shader画笔
		sweepPaint.setStrokeCap(Paint.Cap.ROUND);
		sweepPaint.setStrokeWidth(4);
		sweepGradient = new SweepGradient(0, 0, r.getColor(R.color.v2_maizi_orange), r.getColor(R.color.end_color));
		sweepPaint.setShader(sweepGradient);
	}

	@Override
	protected void onMeasure(int wMeasureSpec, int hMeasureSpec) {
		int width = MeasureSpec.getSize(wMeasureSpec);
		int height = MeasureSpec.getSize(hMeasureSpec);
		int d = (width >= height) ? height : width; // 获取最短的边作为直径
		setMeasuredDimension(d, d); // 重写测量方法，保证获得的画布是正方形
	}

	@Override
	protected void onDraw(Canvas canvas) {

		int Width = getMeasuredWidth();// //计算控件的中心位置
		int Height = getMeasuredHeight();
		int pointX = Width / 2;// 获得圆心坐标
		int pointY = Height / 2;

		int radius = (pointX >= pointY) ? pointY : pointX;// 设置半径
		radius -= 10;// 设置半径
		canvas.save();// 保存Canvas坐标原点

		degree += 3;// 扫描旋转增量度数
		canvas.translate(pointX, pointY);// 设置旋转的原点
		canvas.rotate(270 + degree);
		canvas.drawCircle(0, 0, radius, sweepPaint);// 绘制扫描区域

		canvas.restore();// 恢复原Canvas坐标(0,0)

		canvas.drawCircle(pointX, pointY, radius, circlePaint);// 绘制3个嵌套同心圆形，使用circlePaint画笔
		circlePaint.setAlpha(100);// 降低内部圆形的透明度
		circlePaint.setStrokeWidth(2);// 轮廓宽度
		canvas.drawCircle(pointX, pointY, radius * 2 / 3, circlePaint);
		canvas.drawCircle(pointX, pointY, radius / 3, circlePaint);

		canvas.drawLine(pointX, 10, pointX, 2 * radius + 10, linePaint);// 绘制十字分割线
																		// ， 竖线
		canvas.drawLine(10, pointY, 2 * radius + 10, pointY, linePaint);

		canvas.save();// 保存Canvas坐标原点
		canvas.translate(10, radius + 10);// 设置相对横线起始坐标

		float s = radius / 12f;// 刻度间距
		float minlength = s / 2;// 短刻度线长度
		float maxlength = s;// 长刻度线长度

		for (int i = 0; i < 24; i++) {
			float fromX, toX;
			fromX = toX = s * i;
			if (i % 4 != 0) {// 与圆形重叠处不画刻度
				if (i % 2 != 0) {
					canvas.drawLine(fromX, -minlength, toX, minlength, linePaint);// 绘制X轴短刻度
				} else {
					canvas.drawLine(fromX, -maxlength, toX, maxlength, linePaint);// 绘制X轴长刻度
				}
			}
		}

		canvas.restore();
		canvas.translate(pointX, 10);// 设置相对竖线起始坐标
		for (int i = 0; i < 24; i++) {
			float fromY, toY;
			fromY = toY = s * i;
			if (i % 4 != 0) {
				if (i % 2 != 0) {
					canvas.drawLine(-minlength, fromY, minlength, toY, linePaint);// 绘制Y短轴刻度
				} else {
					canvas.drawLine(-maxlength, fromY, maxlength, toY, linePaint);// 绘制Y长轴刻度
				}
			}
		}

	}

}
