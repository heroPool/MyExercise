package com.example.mvcdemo.model.net;

import android.content.Context;
import android.widget.ImageView;

import com.example.mvcdemo.model.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/3/9.
 */

public class ModelBaseImage extends ModelBase implements IModelBaseImage {
    @Override
    public void releaseImage() {
        OkImageLoader.release();

    }

    @Override
    public void showImage(Context context, String url, ImageView imageView, int defaulePicIc, boolean isDragging) {
    OkImageLoader.build(url)
            .imageView(imageView)
            .defaultPicture(defaulePicIc)
            .setDragging(isDragging)
            .showImage(context);
    }

    @Override
    public void showImage(Context context, String url, ImageView imageView, int width, int height, int defaulePicIc, boolean isDragging) {
        OkImageLoader.build(url)
                .imageView(imageView)
                .defaultPicture(defaulePicIc)
                .width(width)
                .height(height)
                .setDragging(isDragging)
                .showImage(context);

    }
}
