package com.oasis.something;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.orhanobut.logger.Logger;

import org.xutils.x;

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

        Logger.init(Constants.logtag) ;
        x.Ext.init(this);
       // x.Ext.setDebug(BuildConfig.DEBUG);

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(4 * 1024 * 1024))
                .memoryCacheSize(4 * 1024 * 1024)
                .memoryCacheSizePercentage(12)
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
