package com.oasis.something;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * Created by liling on 2016/6/22.
 */
public class ProjectApplication extends Application {

    private final String appPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/com.oasis.something/" ;
    private final String imagePath = appPath+"Image/" ;
    private static ProjectApplication instance;

    public static ProjectApplication getInstance() {
        if (null == instance) {
            instance = new ProjectApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .diskCache(new UnlimitedDiscCache(new File(imagePath)))
                .denyCacheImageMultipleSizesInMemory()
                .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(configuration);
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAppPath() {
        return appPath;
    }
}
