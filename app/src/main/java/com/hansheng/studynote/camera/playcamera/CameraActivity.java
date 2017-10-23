package com.hansheng.studynote.camera.playcamera;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.hansheng.studynote.R;

/**
 * Created by hansheng on 16-12-12.
 */

public class CameraActivity extends Activity implements CameraInterface.CamOpenOverCallback, View.OnTouchListener {
    private static final String TAG = "CameraActivity";
    CameraSurfaceView surfaceView = null;
    ImageButton shutterBtn;
    RelativeLayout rPhoto;
    float previewRate = -1f;

    boolean isQuit = false;

    private int x;
    private int y;
    private int rawx;
    private int rawy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread openThread = new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                CameraInterface.getInstance().doOpenCamera(CameraActivity.this);
            }
        };
        openThread.start();
        setContentView(R.layout.activity_camera);
        initUI();
        initViewParams();
        shutterBtn.setOnClickListener(new BtnListeners());
        rPhoto.setOnTouchListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera, menu);
        return true;
    }

    private void initUI() {
        rPhoto= (RelativeLayout) findViewById(R.id.re_photo);
        surfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);
        shutterBtn = (ImageButton) findViewById(R.id.btn_shutter);
        shutterBtn.getBackground().setAlpha(100);
    }

    private void initViewParams() {
        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        previewRate = DisplayUtil.getScreenRate(this); //默认全屏的比例预览
        surfaceView.setLayoutParams(params);

        //手动设置拍照ImageButton的大小为120dip×120dip,原图片大小是64×64
        ViewGroup.LayoutParams p2 = shutterBtn.getLayoutParams();
        p2.width = DisplayUtil.dip2px(this, 80);
        p2.height = DisplayUtil.dip2px(this, 80);
        shutterBtn.setLayoutParams(p2);

    }

    @Override
    public void cameraHasOpened() {
        // TODO Auto-generated method stub
        SurfaceHolder holder = surfaceView.getSurfaceHolder();
        CameraInterface.getInstance().doStartPreview(holder, previewRate);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                CameraInterface.getInstance().doTakePicture();
                break;
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();
                rawx = (int) event.getRawX();
                rawy = (int) event.getRawY();
                Log.d(TAG, "getX=" + x + "getY=" + y + "\n" + "getRawX=" + rawx
                        + "getRawY=" + rawy + "\n");

                break;

            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    private class BtnListeners implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.btn_shutter:
                    CameraInterface.getInstance().doTakePicture();
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isQuit) {
            Thread openThread = new Thread() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    CameraInterface.getInstance().doOpenCamera(CameraActivity.this);
                }
            };
            openThread.start();
        }
        isQuit = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isQuit = true;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: ");
//
//        x = (int) event.getX();
//        y = (int) event.getY();
//        rawx = (int) event.getRawX();
//        rawy = (int) event.getRawY();
//
//        if (x < 900 && x > 300 && y > 200 && y < 700) {
//            Log.d(TAG, "getX=" + x + "getY=" + y + "\n" + "getRawX=" + rawx
//                    + "getRawY=" + rawy + "\n");
//
//        }
//
////        CameraInterface.getInstance().doTakePicture();
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "dispatchTouchEvent: ");
//        CameraInterface.getInstance().doTakePicture();
//        return super.dispatchTouchEvent(ev);
//    }


}