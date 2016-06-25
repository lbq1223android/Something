package com.oasis.something.ui;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.oasis.something.BaseActivity;
import com.oasis.something.R;
import com.oasis.something.util.L;

import net.colindodd.toggleimagebutton.ToggleImageButton;

/**
 * Created by liling on 2016/6/25.
 */
public class ManyButton extends BaseActivity {

    ToggleImageButton imgbutton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manybutton);
        imgbutton = (ToggleImageButton) findViewById(R.id.imagebutton);
        imgbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                L.e("ManyButton",String.valueOf(isChecked));
            }
        });


    }
}
