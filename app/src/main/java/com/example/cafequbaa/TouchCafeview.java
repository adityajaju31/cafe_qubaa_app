package com.example.cafequbaa;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.panoramagl.utils.PLUtils;

public class TouchCafeview extends AppCompatActivity {

    private SphericalView sphericalView;
    private Bitmap[] bitmaps = new Bitmap[2];

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.button1:
                    changePanorama(0);
                    break;
                case R.id.button2:
                    changePanorama(1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafeview);
        bitmaps[0] = PLUtils.getBitmap(this, R.raw.sunset_at_pier);
        bitmaps[1] = PLUtils.getBitmap(this, R.raw.sunset_at_pier_grey);

        sphericalView = findViewById(R.id.spherical_view);
        sphericalView.setPanorama(bitmaps[0], true);

        findViewById(R.id.button1).setOnClickListener(buttonClickListener);
        findViewById(R.id.button2).setOnClickListener(buttonClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sphericalView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sphericalView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sphericalView.onDestroy();
    }

    private void changePanorama(int index) {
        sphericalView.setPanorama(bitmaps[index], true);
    }
}
