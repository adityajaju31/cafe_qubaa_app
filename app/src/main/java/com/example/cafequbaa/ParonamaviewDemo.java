package com.example.cafequbaa;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import pl.rjuszczyk.panorama.viewer.PanoramaGLSurfaceView;

public class ParonamaviewDemo extends AppCompatActivity {
    PanoramaGLSurfaceView panoramaGLSurfaceView;
    int                   currentTexture = 0;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        panoramaGLSurfaceView = (PanoramaGLSurfaceView) findViewById(R.id.panorama);
        img = (ImageView) findViewById(R.id.image);
        panoramaGLSurfaceView.setPanoramaResourceId(R.raw.abc);
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panoramaGLSurfaceView.setTexDrawableResourceID(R.drawable.vrimage);

                Glide
                        .with(getApplicationContext())
                        .load("http://michel.thoby.free.fr/360x180_Vs_360x360_Contreversy/North_South_Panorama_Equirect_360x180.jpg")
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                panoramaGLSurfaceView.setPanoramaBitmap(resource);
                            }
                        });
//                Glide.with(MainActivity.this)
//                        .load("http://michel.thoby.free.fr/360x180_Vs_360x360_Contreversy/North_South_Panorama_Equirect_360x180.jpg")
//                        .asBitmap()
//                        .into(img);


//                int resId;
//                switch (currentTexture++) {
//                    case 0:
//                        resId = R.raw.pano10;
//                        break;
//                    case 1:
//                        resId = R.raw.pano;
//                        break;
//                    case 2:
//                        resId = R.raw.pano2;
//                        break;
//                    case 3:
//                        resId = R.raw.pano3;
//                        break;
//                    case 4:
//                        resId = R.raw.pano4;
//                        break;
//                    case 5:
//                        resId = R.raw.pano5;
//                        break;
//                    case 6:
//                        resId = R.raw.pano6;
//                        break;
//                    case 7:
//                        resId = R.raw.pano7;
//                        break;
//                    case 8:
//                        resId = R.raw.pano8;
//                        break;
//                    case 9:
//                        resId = R.raw.pano9;
//                        currentTexture = 0;
//                        break;
//                    default:
//                        resId = R.raw.pano;
//                }
//                panoramaGLSurfaceView.setPanoramaResourceId(resId);
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panoramaGLSurfaceView.reset();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        panoramaGLSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        panoramaGLSurfaceView.onResume();
    }
}