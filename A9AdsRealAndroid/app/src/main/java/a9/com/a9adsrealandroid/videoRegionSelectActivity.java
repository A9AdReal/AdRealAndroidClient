package a9.com.a9adsrealandroid;

import a9.com.a9adsrealandroid.util.SystemUiHider;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.hardware.Camera;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class videoRegionSelectActivity extends Activity{
    private Camera mCamera;
    private CameraPreview mCameraPreview;
    private AddPointsView mAddPointsView;
    private Button bRenderRegion;
    private Button bChooseAds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vedio_region_select);

        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraLayout);
        preview.addView(mCameraPreview);
        //prepare to add points on AddPointsView
        mAddPointsView = new AddPointsView(this.getApplicationContext());
        preview.addView(mAddPointsView);
        //init the button
        bRenderRegion = new Button(this);
//        bRenderRegion.setWidth(100);
        bRenderRegion.setText("Render");
        //add a button
        FrameLayout.LayoutParams buttonLayoutParameters = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        buttonLayoutParameters.gravity = Gravity.BOTTOM;
        preview.addView(bRenderRegion, buttonLayoutParameters);
        bRenderRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddPointsView.pleaseRender();
            }
        });
    }



    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return c; // returns null if camera is unavailable
    }

}
