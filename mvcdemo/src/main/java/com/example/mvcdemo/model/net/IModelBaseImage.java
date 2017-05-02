package com.example.mvcdemo.model.net;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/3/9.
 */

public interface IModelBaseImage extends IModelBase {
    void releaseImage();

    void showImage(Context context, String url, ImageView imageView, int defaulePicIc, boolean isDragging);

    void showImage(Context context, String url, ImageView imageView, int width, int height, int defaulePicIc, boolean isDragging);

}
