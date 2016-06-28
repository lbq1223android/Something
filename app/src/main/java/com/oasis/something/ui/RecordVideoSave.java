package com.oasis.something.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.jmolsmobile.landscapevideocapture.VideoCaptureActivity;
import com.oasis.something.BaseActivity;
import com.oasis.something.ProjectApplication;
import com.oasis.something.R;
import com.oasis.something.util.L;

import java.io.File;
import java.io.IOException;

/**
 * Created by liling on 2016/6/28.
 */
public class RecordVideoSave extends BaseActivity {
    int RESULT_CODE = 99 ;
    Button button  ;
    TextView textView5  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordvideosave);
        textView5 = (TextView) findViewById(R.id.textView5);
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordVideoSave.this, VideoCaptureActivity.class);
               // intent.putExtra(VideoCaptureActivity.EXTRA_CAPTURE_CONFIGURATION, config);
                intent.putExtra(VideoCaptureActivity.EXTRA_OUTPUT_FILENAME,"temp.3gp") ;
                startActivityForResult(intent, RESULT_CODE);
                }
        });
        ffmpeg = FFmpeg.getInstance(context);
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {

                @Override
                public void onStart() {
                    L.e("RecordVideoSave","loadBinary onStart");
                }

                @Override
                public void onFailure() {
                    L.e("RecordVideoSave","loadBinary onFailure");
                }

                @Override
                public void onSuccess() {
                    L.e("RecordVideoSave","loadBinary onSuccess");
                }

                @Override
                public void onFinish() {
                    L.e("RecordVideoSave","loadBinary onFinish");
                }
            });
        } catch (FFmpegNotSupportedException e) {
            // Handle if FFmpeg is not supported by device
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //  L.e("RecordVideoSave","");
        /*if(requestCode==RESULT_CODE&&resultCode==RESULT_OK&&data!=null){
            L.e("RecordVideoSave",data.getStringExtra("EXTRA_OUTPUT_FILENAME"));
        }*/

        if (resultCode == Activity.RESULT_OK) {
            L.e("RecordVideoSave","RESULT_OK");
            String filename = data.getStringExtra(VideoCaptureActivity.EXTRA_OUTPUT_FILENAME);
            L.e("RecordVideoSave","filename---"+filename);
            textView5.setText(filename);

            compress(filename) ;

          /*  filename = data.getStringExtra(VideoCaptureActivity.EXTRA_OUTPUT_FILENAME);
            statusMessage = String.format(getString(R.string.status_capturesuccess), filename);*/
        } else if (resultCode == Activity.RESULT_CANCELED) {
            L.e("RecordVideoSave","RESULT_CANCELED");
            textView5.setText("用户取消");
           /* filename = null;
            statusMessage = getString(R.string.status_capturecancelled);*/
        } else if (resultCode == VideoCaptureActivity.RESULT_ERROR) {
            L.e("RecordVideoSave","RESULT_ERROR");
            textView5.setText("发生错误");
           /* filename = null;
            statusMessage = getString(R.string.status_capturefailed);*/
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    FFmpeg ffmpeg ;
    public void compress(String filename){
        String VideoIn = filename ;
        String filePath = ProjectApplication.getInstance().getAppPath()+"Compressvideo/" ;
        String VideoOut = filePath+"temp.3gp";

        File file = new File(filePath) ;
        if(!file.exists()){
            file.mkdirs() ;
        }

        File file1 = new File(VideoOut) ;
        if(!file1.exists()){
            try {
                file1.createNewFile() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            String cmd = "-i "+VideoIn+" "+VideoOut;

            //String cmd = "-i "+VideoIn+" -s 1280*720 -aspect 1.77 "+VideoOut+"" ;
            //String cmd = "-y -i "+VideoIn+" -s 640x360 -vcodec libx264 -vpre -b 800000 "+VideoOut;
            //String cmd ="-i "+VideoIn+" -vcodec libx264 -maxrate 500k -bufsize 1000k -vf scale=-1:480 -threads 0 -f mp4 "+VideoOut;
            //String cmd = "-i "+VideoIn+" -c:a copy -c:v libx264 -profile:v high -preset slower -crf:v 21 "+VideoOut ;
            String[] command = cmd.split(" ");
            // to execute "ffmpeg -version" command you just need to pass "-version"
            ffmpeg.execute(command, new ExecuteBinaryResponseHandler() {

                @Override
                public void onStart() {
                    L.e("RecordVideoSave","onStart");
                }

                @Override
                public void onProgress(String message) {
                    L.e("RecordVideoSave","onProgress"+message);
                }

                @Override
                public void onFailure(String message) {
                    L.e("RecordVideoSave","onFailure"+message);
                }

                @Override
                public void onSuccess(String message) {
                    L.e("RecordVideoSave","onSuccess"+message);
                }

                @Override
                public void onFinish() {
                    L.e("RecordVideoSave","onFinish");
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            // Handle if FFmpeg is already running
            e.printStackTrace();
        }


    }
}
