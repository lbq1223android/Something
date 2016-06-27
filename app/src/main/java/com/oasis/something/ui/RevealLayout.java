package com.oasis.something.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.oasis.something.BaseActivity;
import com.oasis.something.R;

import org.xutils.common.util.DensityUtil;

import io.github.hendraanggrian.circularreveallayout.view.RevealFrameLayout;

/**
 * Created by liling on 2016/6/27.
 */
public class RevealLayout extends BaseActivity {

    LinearLayout linearLayout, linearLayout1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reveallayout);

       /* view 操作的视图
        centerX 动画开始的中心点X
        centerY 动画开始的中心点Y
        startRadius 动画开始半径
        startRadius 动画结束半径*/
        //android 5.0 以上实现
        linearLayout = (LinearLayout) findViewById(R.id.linear_reveallayout);
        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(
                        linearLayout,
                        linearLayout.getMeasuredWidth()/2,
                        linearLayout.getMeasuredHeight()/2,
                        0,
                        linearLayout.getMeasuredHeight());
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(500);
                animator.start();
            }
        });

        ((Button)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
