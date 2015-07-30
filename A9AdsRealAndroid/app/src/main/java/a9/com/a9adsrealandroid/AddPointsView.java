package a9.com.a9adsrealandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lianchengliang on 7/26/15.
 */
public class AddPointsView extends GLSurfaceView {


    Canvas cv;
    Paint mPaint;
    private ArrayList<PointF> mVertices;
    private static final int MAX_VERT = 4;
    private AdsImageRenderer mRenderer;
    float mWidth;
    float mHeight;

    public void addPoint(PointF p){
        if(mVertices.size() < MAX_VERT){
            mVertices.add(p);
            mRenderer.updateCoord(mVertices);
        }
    }


    public AddPointsView(Context context) {
        super(context);
        init(context);
    }

    public AddPointsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        mVertices = new ArrayList<PointF>();

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new AdsImageRenderer(context);
        //set background to be transparent
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setZOrderOnTop(true);
        setRenderer(mRenderer);
        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX()/mWidth;
            float y = event.getY() / mHeight;
            Log.e("the location on screen ", "x: " + x + "\t y:" + y + "\n");
            addPoint(new PointF(x, y));
        }
        return true;
    }


    public void pleaseRender(){
        requestRender();
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        mWidth = xNew;
        mHeight = yNew;
    }
}
