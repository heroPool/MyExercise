package com.example.xingnengyouhua;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by AIqinfeng on 2017/4/28.
 */

public class MyCardView extends View {
	private static int WIDTH = 200;

	int[] mCards = {R.drawable.goods01, R.drawable.goods02, R.drawable.goods03, R.drawable.goods04};
	Bitmap[] mBitmaps = new Bitmap[mCards.length];

	public MyCardView(Context context) {
		super(context);
		init();
	}

	private void init() {
		for (int i = 0; i < mBitmaps.length; i++) {
			mBitmaps[i] = BitmapFactory.decodeResource(getResources(), mCards[i]);
		}
	}

	public MyCardView(Context context,
		@Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(getMeasuredWidth(widthMeasureSpec), getMeasuredHeight(heightMeasureSpec));
	}

	private int getMeasuredWidth(int widthMeasureSpec) {
		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);
		int result = size;
		if (mode != MeasureSpec.EXACTLY) {
			result = (mCards.length - 1) * WIDTH + mBitmaps[mCards.length - 1].getWidth();
		}
		return Math.min(result, size);
	}

	private int getMeasuredHeight(int heightMeasureSpec) {
		int mode = MeasureSpec.getMode(heightMeasureSpec);
		int size = MeasureSpec.getSize(heightMeasureSpec);
		int result = size;
		if (mode == MeasureSpec.AT_MOST) {
			for (int i = 0; i < mBitmaps.length; i++) {
				result = Math.max(result, mBitmaps[i].getHeight());
			}
		}
		return Math.min(result, size);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		for (int i = 0; i < mBitmaps.length; i++) {
//			canvas.drawBitmap(mBitmaps[i], i * WIDTH, 0, null);
//		}

		for (int i = 0; i < mBitmaps.length; i++) {
			// 保存 canvas 状态的
			canvas.save();
			if ( i < mBitmaps.length - 1) {
				// 裁剪一个矩形区域
				canvas.clipRect(0, 0, WIDTH, mBitmaps[i].getHeight());
			}
			canvas.drawBitmap(mBitmaps[i], 0, 0, null);
			// 恢复 canvas 的状态
			canvas.restore();
			// 移动 canvas
			canvas.translate(WIDTH, 0);
		}
	}
}
