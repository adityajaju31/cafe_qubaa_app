package com.example.cafequbaa;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class PanoramicViewActivity extends Activity {

    private static final String                 TAG           = PanoramicViewActivity.class.getSimpleName();
    private static final int                    IMAGES_AMOUNT = 4;
    private static final int                    DELAY         = 5000; //Expressed in milliseconds
    /**
     * Actual panorama widget.
     **/
    private              VrPanoramaView         panoWidgetView;
    /**
     * Tracks the file to be loaded across the lifetime of this app.
     **/
    private              Uri                    fileUri;
    /**
     * Configuration information for the panorama.
     **/
    private              VrPanoramaView.Options panoOptions   = new VrPanoramaView.Options();
    private              ImageLoaderTask        backgroundImageLoaderTask;
    private              Handler                handler;
    private              int                    imageIndex    = 0;

    /**
     * Called when the app is launched via the app icon or an intent using the adb command above. This
     * initializes the app and loads the image to render.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeActivity();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                changeImage();
                handler.postDelayed(this, DELAY);
            }
        }, DELAY);
    }

    protected void initializeActivity() {
        setContentView(R.layout.activity_panoramic_view);
        panoWidgetView = (VrPanoramaView) findViewById(R.id.pano_view);

        panoWidgetView.setInfoButtonEnabled(false);
        panoWidgetView.setFullscreenButtonEnabled(false);
        panoWidgetView.setStereoModeButtonEnabled(false);
        panoWidgetView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_STEREO);

        panoWidgetView.setEventListener(new VrPanoramaEventListener());
        changeImage();
    }

    protected void changeImage() {
        this.imageIndex = this.imageIndex % IMAGES_AMOUNT + 1;
        Log.i(TAG, "New Image. imageIndex = " + imageIndex);
        fileUri = null;
        panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
        // Load the bitmap in a background thread to avoid blocking the UI thread. This operation can
        // take 100s of milliseconds.
        if (backgroundImageLoaderTask != null) {
            // Cancel any task from a previous intent sent to this activity.
            backgroundImageLoaderTask.cancel(true);
        }
        backgroundImageLoaderTask = new ImageLoaderTask();
        backgroundImageLoaderTask.execute(Pair.create(fileUri, panoOptions));
    }

    @Override
    protected void onPause() {
        panoWidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        panoWidgetView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView.shutdown();

        // The background task has a 5 second timeout so it can potentially stay alive for 5 seconds
        // after the activity is destroyed unless it is explicitly cancelled.
        if (backgroundImageLoaderTask != null) {
            backgroundImageLoaderTask.cancel(true);
        }
        super.onDestroy();
    }

    /**
     * Helper class to manage threading.
     */
    class ImageLoaderTask extends AsyncTask<Pair<Uri, VrPanoramaView.Options>, Void, Boolean> {
        /**
         * Reads the bitmap from disk in the background and waits until it's loaded by pano widget.
         */
        @Override
        protected Boolean doInBackground(Pair<Uri, VrPanoramaView.Options>... fileInformation) {
            VrPanoramaView.Options panoOptions  = null;  // It's safe to use null VrPanoramaView.Options.
            InputStream            istr         = null;
            String                 imageName    = "image_" + imageIndex + ".jpg";
            AssetManager           assetManager = getAssets();
            try {
                istr = assetManager.open(imageName);
                panoOptions = new VrPanoramaView.Options();
                panoOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
            } catch (IOException e) {
                Log.e(TAG, "Could not decode default bitmap: " + e);
                return false;
            }

            panoWidgetView.loadImageFromBitmap(BitmapFactory.decodeStream(istr), panoOptions);
            try {
                istr.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close input stream: " + e);
            }

            return true;
        }
    }
}