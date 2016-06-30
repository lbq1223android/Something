package com.oasis.something.adapter;

import java.util.ArrayList;
import java.util.Random;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.oasis.something.R;
import com.oasis.something.view.ScaleImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by liling on 2016/6/30.
 */
public class ImageGridAdapter extends BaseAdapter {
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private static final String TAG = "ImageGridAdapter";
    private ImageLoader mLoader;
    DisplayImageOptions imageOptions ;
    private ArrayList<String> mImageList;
    private LayoutInflater mLayoutInflater;
    public ImageGridAdapter(Context context,
                            ArrayList<String> list) {
        mImageList = list;
        mLayoutInflater = LayoutInflater.from(context);
        mRandom = new Random();
        imageOptions =new   DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.preview_card_pic_loading)
                .showImageOnFail(R.mipmap.preview_card_pic_loadfail)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheInMemory(true)
                .displayer(new RoundedBitmapDisplayer(5))//圆角
               // .displayer(new FadeInBitmapDisplayer(500))
                .cacheOnDisk(true)
                .build();

    }

    public int getCount() {
        return mImageList.size();
    }
    public Object getItem(int arg0) {
        return null;
    }
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_image,
                    null);
            holder = new ViewHolder();
            holder.imageView = (DynamicHeightImageView) convertView .findViewById(R.id.imageView);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        double positionHeight = getPositionRatio(position);
        holder.imageView.setHeightRatio(positionHeight);
        ImageLoader.getInstance().displayImage(mImageList.get(position),  holder.imageView, imageOptions);
        return convertView;
    }

    static class ViewHolder {
        DynamicHeightImageView imageView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
    }
}
