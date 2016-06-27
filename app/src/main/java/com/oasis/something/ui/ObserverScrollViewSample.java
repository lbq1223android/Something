package com.oasis.something.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.nineoldandroids.view.ViewHelper;
import com.oasis.something.BaseActivity;
import com.oasis.something.R;
import com.oasis.something.util.LogUtil;

import org.xutils.common.util.DensityUtil;

/**
 * Created by liling on 2016/6/27.
 */
public class ObserverScrollViewSample extends BaseActivity {

    ObservableScrollView observableScrollView ;
    LinearLayout linearLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oberserverscrollview);

        linearLayout = (LinearLayout) findViewById(R.id.linear);
        observableScrollView = (ObservableScrollView) findViewById(R.id.ob);
        observableScrollView.setScrollViewCallbacks(new ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
                LogUtil.e("ObserverScrollViewSample", "scrollY----" + scrollY + "firstScroll----" + firstScroll + "dragging----" + dragging);
                //  LogUtil.e("ObserverScrollViewSample","linearLayout height"+linearLayout.getMeasuredHeight());

                // if()
            }

            @Override
            public void onDownMotionEvent() {
                LogUtil.e("ObserverScrollViewSample", "onDownMotionEvent");
            }

            @Override
            public void onUpOrCancelMotionEvent(ScrollState scrollState) {
                //LogUtil.e("ObserverScrollViewSample","onUpOrCancelMotionEvent"+scrollState);

                if (scrollState == ScrollState.UP) {
                    LogUtil.e("ObserverScrollViewSample", "onUpOrCancelMotionEvent" + "ScrollState.UP");
                    if (toolbarIsShown()) {   // 需要完成方法
                        hideToolbar(); // 需要完成方法
                    }

                } else if (scrollState == ScrollState.DOWN) {
                    LogUtil.e("ObserverScrollViewSample", "onUpOrCancelMotionEvent" + "ScrollState.DOWN");

                    if (toolbarIsHidden()) { // 需要完成方法
                        showToolbar(); // 需要完成方法
                    }
                }
            }
        });
    }

    private void showToolbar() {
        moveToolbar(0);
    }

    private void hideToolbar() {
        moveToolbar(-linearLayout.getHeight());
    }

    private boolean toolbarIsShown() {
        // Toolbar 在Y轴坐标为0，也就是正在显示
        return ViewHelper.getTranslationY(linearLayout) == 0;
    }

    private boolean toolbarIsHidden() {
        // Toolbar 在屏幕外，并且Y坐标的绝对值等于它的高度，也就是正在隐藏
        return ViewHelper.getTranslationY(linearLayout) == -linearLayout.getHeight();
    }

    private void moveToolbar(float toTranslationY) {

        if (ViewHelper.getTranslationY(linearLayout) == toTranslationY) {
            return;
        }

        ValueAnimator animator = ValueAnimator.ofFloat(ViewHelper.getTranslationY(linearLayout), toTranslationY).setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translationY = (float) animation.getAnimatedValue();
                ViewHelper.setTranslationY(linearLayout, translationY);
                ViewHelper.setTranslationY((View) observableScrollView, translationY);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) ((View) observableScrollView).getLayoutParams();
                lp.height = (int) -translationY + DensityUtil.getScreenHeight() - lp.topMargin;
                ((View) observableScrollView).requestLayout();
            }
        });
        animator.start();
    }
}
