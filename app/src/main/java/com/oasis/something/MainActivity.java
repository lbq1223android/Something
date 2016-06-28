package com.oasis.something;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.oasis.something.bean.FuctionBean;
import com.oasis.something.ui.CustomDialogToast;
import com.oasis.something.ui.ManyButton;
import com.oasis.something.ui.ObserverScrollViewSample;
import com.oasis.something.ui.RecordVideoSave;
import com.oasis.something.ui.RevealLayout;
import com.oasis.something.ui.SVG;
import com.oasis.something.ui.Vector;
import com.oasis.something.util.L;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

public class MainActivity extends BaseActivity{

    ListView mListView;
    ArrayList<FuctionBean> mList = new ArrayList<>() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setColor(this, getResources().getColor(R.color.app_basecolor));

        mList.add(new FuctionBean("加载图片", LoadImage.class)) ;
        mList.add(new FuctionBean("自定义dialog", CustomDialogToast.class)) ;
        mList.add(new FuctionBean("各种Button", ManyButton.class)) ;
        mList.add(new FuctionBean("ObserverScrollView", ObserverScrollViewSample.class)) ;
        mList.add(new FuctionBean("SVG", SVG.class)) ;
        mList.add(new FuctionBean("Vector", Vector.class)) ;
        mList.add(new FuctionBean("RevealLayout", RevealLayout.class)) ;
        mList.add(new FuctionBean("录制视频 压缩", RecordVideoSave.class)) ;


        RequestParams requestParams = new RequestParams("http://101.200.234.105/api/v1/android/get_filter_list") ;
        loadData(requestParams, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {

                L.json(TAG,result);
            }

            @Override
            public void onFinished() {
                Logger.e("onFinished");
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                Logger.e("onFinished");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Logger.e("onFinished",ex);
            }
        });


       // Logger.e("出错了");

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public Object getItem(int position) {
                return mList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, null);
                TextView textView = (TextView) v.findViewById(R.id.textView2);
                textView.setText(mList.get(position).getFuncionName() + "");
                return v;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, mList.get(position).getClassName());
                startActivity(intent);
            }
        });

    }

    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

}
