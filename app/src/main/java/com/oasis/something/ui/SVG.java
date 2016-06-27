package com.oasis.something.ui;

import android.os.Bundle;

import com.jrummyapps.android.widget.AnimatedSvgView;
import com.oasis.something.BaseActivity;
import com.oasis.something.R;

/**
 * Created by liling on 2016/6/27.
 */
public class SVG extends BaseActivity {
    private AnimatedSvgView svgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.svg);

        svgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);
        svgView.postDelayed(new Runnable() {

            @Override public void run() {
                svgView.start();
            }
        }, 500);
    }
}
