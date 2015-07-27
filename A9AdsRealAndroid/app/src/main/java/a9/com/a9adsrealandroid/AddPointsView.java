package a9.com.a9adsrealandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
public class AddPointsView extends View {


    Canvas cv;
    Paint mPaint;
    private ArrayList<PointF> mVertices;
    private static final int MAX_VERT = 4;
    float mWidth;
    float mHeight;

    public void addPoint(PointF p){
        if(mVertices.size() < MAX_VERT){
            mVertices.add(p);
        }
    }

    public boolean couldAdd(){
        return mVertices.size() < MAX_VERT;
    }

    public AddPointsView(Context context) {
        super(context);
        Init();
    }

    public AddPointsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public AddPointsView(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    private void Init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        //TODO color could be the A9 Point
        mPaint.setAntiAlias(true);
        mVertices = new ArrayList<PointF>();
    }



    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        for (PointF p : mVertices){
            canvas.drawColor(Color.RED);
            canvas.drawCircle(p.x * mWidth, p.y * mHeight, 0.0001f, mPaint);
            Log.e("would like to draw", "x" + p.x*mWidth + "\ty" + p.y*mHeight );
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX()/mWidth;
            float y = event.getY()/mHeight;
            Log.e("the location on screen ", "x: " + x + "\t y:" + y + "\n");
            addPoint(new PointF(x, y));
            postInvalidate();
            invalidate();
        }
        return true;
    }


    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        Log.e("the width is :", "" + xNew);
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        mWidth = xNew;
        mHeight = yNew;
    }
}
