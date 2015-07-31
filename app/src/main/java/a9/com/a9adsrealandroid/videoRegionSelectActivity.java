package a9.com.a9adsrealandroid;

import a9.com.a9adsrealandroid.util.SystemUiHider;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.hardware.Camera;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import java.util.ArrayList;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class VideoRegionSelectActivity extends Activity{
    private Camera mCamera;
    private CameraPreview mCameraPreview;
    private RenderView mRenderView;
    private AddPointsView mAddPointsView;
    private Button bRenderRegion;
    private Button bReset;
    private Button bChooseAds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vedio_region_select);

        mCamera = getCameraInstance();
        mCameraPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraLayout);
        preview.addView(mCameraPreview);
        //add render view to the layout
        mRenderView = new RenderView(this.getApplicationContext(), this);
        preview.addView(mRenderView);
        //add point view to the layout
        mAddPointsView = new AddPointsView(this.getApplicationContext());
        preview.addView(mAddPointsView);
        //init the button
        bRenderRegion = new Button(this);
        bRenderRegion.setText("Render");
        bRenderRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRenderView.pleaseRender();
            }
        });

        //add a reset button
        bReset = new Button(this);
        bReset.setText("Reset");
        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRenderView.resetRegion();
            }
        });
        //
        GridLayout layoutButton = new GridLayout(this.getApplicationContext());
        layoutButton.setRowCount(1);
        layoutButton.setColumnCount(2);
        layoutButton.addView(bReset);
        layoutButton.addView(bRenderRegion);
        //add layout to view
        FrameLayout.LayoutParams layoutParam = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParam.gravity = Gravity.BOTTOM;
        preview.addView(layoutButton, layoutParam);
    }



    public PointF findCornerOnScreen(PointF curP){
        return findCornerOnScreen(curP, mCameraPreview.getFrameData());
    }

    //TODO implement this using JNI
    public PointF findCornerOnScreen(PointF curP, byte[] preFrame){
        return curP;
    }

    public void trackingPoint(byte[] preFrame, byte[] curFrame){
        ArrayList<PointF> ps = trackingPoint(mRenderView.getVertices(), preFrame, curFrame);
        mRenderView.updateVertices(ps);

    }

    //TODO implement this
    public ArrayList<PointF> trackingPoint(ArrayList<PointF> curPs, byte[] preFrame, byte[] curFrame){
        return curPs;
    }

    //TODO impliment this
    public void init(){
        return;
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
