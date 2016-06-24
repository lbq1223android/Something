package com.oasis.something.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oasis.something.BaseActivity;
import com.oasis.something.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by liling on 2016/6/24.
 */
public class CustomDialogToast extends BaseActivity {

    Button mButton,mButton1 ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialogtoast);

        mButton = (Button) findViewById(R.id.button);
        mButton1 = (Button) findViewById(R.id.button1);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(CustomDialogToast.this)
                        .setContentHolder(new ViewHolder(R.layout.customdialogtoast_background))
                        .setContentBackgroundResource(Color.TRANSPARENT)
                        .setCancelable(true)
                                .setGravity(Gravity.CENTER)
                        .create() ;
                dialog.show();
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout = LayoutInflater.from(context).inflate(R.layout.customdialogtoast_background,null);
                Toast toast = new Toast(context);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });


    }
}
