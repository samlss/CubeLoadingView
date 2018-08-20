package com.iigo.cubeloadingview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.iigo.library.CubeLoadingView;

public class MainActivity extends AppCompatActivity {
    private CubeLoadingView cubeLoadingView;
    private CubeLoadingView cubeLoadingView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cubeLoadingView = findViewById(R.id.clv_loading);
        cubeLoadingView2 = findViewById(R.id.clv_loading2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cubeLoadingView.release();
        cubeLoadingView2.release();
    }

    public void onStart(View view) {
        cubeLoadingView.start();
        cubeLoadingView2.start();
    }

    public void onStop(View view) {
        cubeLoadingView.stop();
        cubeLoadingView2.stop();
    }

    public void onResume(View view) {
        cubeLoadingView.resume();
        cubeLoadingView2.resume();
    }

    public void onPause(View view) {
        cubeLoadingView.pause();
        cubeLoadingView2.pause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        cubeLoadingView.setFirstSideColor(Color.RED);
        cubeLoadingView.setSecondSideColor(Color.RED);
        cubeLoadingView.setThirdSideColor(Color.RED);
        cubeLoadingView.setFourthSideColor(Color.RED);

        ViewGroup.LayoutParams layoutParams = cubeLoadingView2.getLayoutParams();
        layoutParams.width = 300;
        layoutParams.height = 300;
        cubeLoadingView2.setLayoutParams(layoutParams);

        return super.onKeyDown(keyCode, event);
    }
}
