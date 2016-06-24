package com.oasis.something;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.oasis.something.util.Images;
import com.oasis.something.util.LogUtil;

import java.io.File;
import java.text.NumberFormat;

/**
 * Created by liling on 2016/6/23.
 */
public class LoadImage extends BaseActivity {

    GridView mGridView ;
    String[] imgs  = Images.imageThumbUrls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadimage);

        //计算缓存大小
        double daxiao = getDirSize(new File(ProjectApplication.getInstance().getImagePath())) ;
        NumberFormat ddf1= NumberFormat.getNumberInstance() ;
        ddf1.setMaximumFractionDigits(2);
        String s= ddf1.format(daxiao) ;
        LogUtil.e("llll", "LoadImage 缓存大小" +s);

        imageOptions =new   DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.preview_card_pic_loading)
                .showImageOnFail(R.mipmap.preview_card_pic_loadfail)
                .bitmapConfig(Bitmap.Config.RGB_565)
                        .cacheInMemory(true)
                //.displayer(new RoundedBitmapDisplayer(5))//圆角
                .displayer(new FadeInBitmapDisplayer(500))
                .cacheOnDisk(true)
                .build();


        mGridView = (GridView) findViewById(R.id.gridView);
        //mGridView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(),true,true));
        mGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public Object getItem(int position) {
                return imgs[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                {
                    convertView = LoadImage.this.getLayoutInflater().inflate(
                            R.layout.item_fragment_list_imgs, parent, false);
                }
                final ImageView imageview = (ImageView) convertView
                        .findViewById(R.id.id_img);
                ImageLoader.getInstance().displayImage(imgs[position], imageview, imageOptions);
                //displayImage(), loadImage(),loadImageSync()，loadImageSync()  这几个方法是同步的
                /*ImageLoader.getInstance().loadImage(imgs[position],imageOptions,new SimpleImageLoadingListener(){
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        imageview.setImageBitmap(loadedImage);
                    }
                });*/

                //Glide.with(context).load(imgs[position]).into(imageview) ;

                return convertView;
            }
        });
    }


    /**
     * 得到某路径下的文件大小
     * @param file
     * @return
     */
    public static double getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0.0;
        }
    }
}
