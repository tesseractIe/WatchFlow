package com.save.bike.watchflow.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.save.bike.watchflow.HeartRate;
import com.save.bike.watchflow.HeartRateList;

import java.util.ArrayList;
import java.util.List;

public class GraphView extends SurfaceView implements SurfaceHolder.Callback {

    private HeartRateList heartRateList;
    Context context;
    private static final int MINUTES_OF_DAY = 1440;
    private final String TAG = this.getClass().getCanonicalName();
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private SurfaceHolder gHolder;
    private float lineWidth;
    private boolean drawDetailList = false;

    private float pressedCoordX;

    private float canvasHeight;
    private float canvasWidth;
    private float stepWidth;
    private float stepHeight;

    //--------------------------------------------------------------------------------------------->start constructors here
    public GraphView(Context context) {
        super(context);
        this.context = context;
        gHolder = getHolder();
        gHolder.addCallback(this);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        gHolder = getHolder();
        gHolder.addCallback(this);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        gHolder = getHolder();
        gHolder.addCallback(this);
    }
    //--------------------------------------------------------------------------------------------->end constructors here


    //--------------------------------------------------------------------------------------------->start override methods
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gHolder = holder;
        drawShortList();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX() < lineWidth / 2) {
            pressedCoordX = lineWidth / 2;
        } else if (event.getX() > canvasWidth - lineWidth / 2) {
            pressedCoordX = canvasWidth - lineWidth / 2;
        } else {
            pressedCoordX = event.getX();
        }

        if (drawDetailList) {
            updateDetailList();
        } else {
            drawShortList();
        }
        return true;
    }
    //--------------------------------------------------------------------------------------------->end override methods

    public void init(List<HeartRate> list) {
        heartRateList = new HeartRateList(list);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint2.setColor(Color.rgb(255, 165, 0));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(2);
        paint2.setTextSize(13);
    }

    public void drawShortList() {
        drawDetailList = false;
        Canvas canvas = gHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        canvasWidth = canvas.getWidth() - 25;
        canvasHeight = canvas.getHeight() - 17;
        stepWidth = canvasWidth / (float) (MINUTES_OF_DAY - heartRateList.getShortHeartRateList().get(0).getMinutes());
        stepHeight = canvasHeight / (float) heartRateList.getFullHeartRateRange();
        lineWidth = 120 * stepWidth;
        //Draw selector
        canvas.drawLine(pressedCoordX - (lineWidth / 2), canvasHeight + 1, pressedCoordX + (lineWidth / 2), canvasHeight + 1, paint2);//horizontal line
        canvas.drawLine(pressedCoordX - (lineWidth / 2), canvasHeight + 1, pressedCoordX - (lineWidth / 2), 0, paint2);//left vertical line
        canvas.drawLine(pressedCoordX + (lineWidth / 2), canvasHeight + 1, pressedCoordX + (lineWidth / 2), 0, paint2);//right vertical line
        //Calculated
        float pressedPercent = ((pressedCoordX - lineWidth / 2) * 100 / (canvasWidth - lineWidth));
        float allStartSelectedTime = (MINUTES_OF_DAY - heartRateList.getFullHeartRateList().get(0).getMinutes()) - 120;
        float startSelectedTime = ((allStartSelectedTime / 100 * pressedPercent) + heartRateList.getFullHeartRateList().get(0).getMinutes());
        //Draw min max and middle heart rate
        canvas.drawText(String.valueOf(heartRateList.getMaxFullListHeartRate()), canvasWidth + 1, 10, paint2);
        canvas.drawText(String.valueOf(heartRateList.getMinFullListHeartRate() + (heartRateList.getMaxFullListHeartRate() - heartRateList.getMinFullListHeartRate()) / 2), canvasWidth + 1, canvasHeight / 2, paint2);
        canvas.drawText(String.valueOf(heartRateList.getMinFullListHeartRate()), canvasWidth + 1, canvasHeight, paint2);
        //Draw max and min time
        canvas.drawText("First: " + timeConvert(heartRateList.getShortHeartRateList().get(0).getMinutes()), 0, canvasHeight + 14, paint2);
        canvas.drawText("Last: " + timeConvert(heartRateList.getShortHeartRateList().get(heartRateList.getShortHeartRateList().size() - 1).getMinutes()), canvasWidth - 40, canvasHeight + 14, paint2);
        //Draw selected time
        canvas.drawText(timeConvert((int) startSelectedTime) + " : " + timeConvert((int) (startSelectedTime + 120)), canvasWidth / 2 - 15, canvasHeight + 14, paint2);
        //Draw Graph
        for (int i = 0; i < heartRateList.getShortHeartRateList().size() - 1; i++) {
            canvas.drawLine(
                    stepWidth * ((float) heartRateList.getShortHeartRateList().get(i).getMinutes() - (float) heartRateList.getShortHeartRateList().get(0).getMinutes()),
                    stepHeight * ((float) heartRateList.getShortHeartRateList().get(i).getHeartRate() - (float) heartRateList.getMinFullListHeartRate()),
                    stepWidth * ((float) heartRateList.getShortHeartRateList().get(i + 1).getMinutes() - (float) heartRateList.getShortHeartRateList().get(0).getMinutes()),
                    stepHeight * ((float) heartRateList.getShortHeartRateList().get(i + 1).getHeartRate() - (float) heartRateList.getMinFullListHeartRate()),
                    paint);
        }
        gHolder.unlockCanvasAndPost(canvas);
    }

    //--------------------------------------------------------------------------------------------->start Draw detail here
    public boolean drawDetailList() {
        drawDetailList = true;
        List<HeartRate> tempList = new ArrayList<>();
        float percent = ((pressedCoordX - lineWidth / 2) * 100 / (canvasWidth - lineWidth));
        float firsttime = ((((MINUTES_OF_DAY - heartRateList.getFullHeartRateList().get(0).getMinutes()) - 120) / 100 * percent) + heartRateList.getFullHeartRateList().get(0).getMinutes());
        for (HeartRate hr : heartRateList.getFullHeartRateList()) {
            if ((hr.getMinutes() > firsttime) && (hr.getMinutes() < firsttime + 120)) {
                tempList.add(hr);
            }
        }
        heartRateList.setDetailHeartRateList(tempList);
        if (heartRateList.getDetailHeartRateList().size() < 2) {
            drawDetailList = false;
            Toast.makeText(context, "Empty(", Toast.LENGTH_SHORT).show();
        } else {
            updateDetailList();
        }
        return !drawDetailList;
    }

    private void updateDetailList() {
        Canvas canvas = gHolder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        stepWidth = canvasWidth / 120;
        stepHeight = canvasHeight / heartRateList.getDetailHeartRateRange();
        //Draw max and min time
        canvas.drawText("First: " + timeConvert(heartRateList.getDetailHeartRateList().get(0).getMinutes()), 0, canvasHeight + 14, paint2);
        canvas.drawText("Last: " + timeConvert(heartRateList.getDetailHeartRateList().get(0).getMinutes() + 120), canvasWidth - 40, canvasHeight + 14, paint2);
        //Draw min max and middle heart rate
        canvas.drawText(String.valueOf(heartRateList.getMaxDetailListHeartRate()), canvasWidth + 1, 10, paint2);
        canvas.drawText(String.valueOf(heartRateList.getMinDetailListHeartRate() + (heartRateList.getMaxDetailListHeartRate() - heartRateList.getMinDetailListHeartRate()) / 2), canvasWidth + 1, canvasHeight / 2, paint2);
        canvas.drawText(String.valueOf(heartRateList.getMinDetailListHeartRate()), canvasWidth + 1, canvasHeight, paint2);
        //Draw Graph
        for (int i = 0; i < heartRateList.getDetailHeartRateList().size() - 1; i++) {
            canvas.drawLine(
                    stepWidth * ((float) heartRateList.getDetailHeartRateList().get(i).getMinutes() - (float) heartRateList.getDetailHeartRateList().get(0).getMinutes()),
                    stepHeight * ((float) heartRateList.getDetailHeartRateList().get(i).getHeartRate() - (float) heartRateList.getMinDetailListHeartRate()),
                    stepWidth * ((float) heartRateList.getDetailHeartRateList().get(i + 1).getMinutes() - (float) heartRateList.getDetailHeartRateList().get(0).getMinutes()),
                    stepHeight * ((float) heartRateList.getDetailHeartRateList().get(i + 1).getHeartRate() - (float) heartRateList.getMinDetailListHeartRate()),
                    paint);
        }
        gHolder.unlockCanvasAndPost(canvas);
    }
    //--------------------------------------------------------------------------------------------->end Draw detail here


    //--------------------------------------------------------------------------------------------->start utils
    private String timeConvert(int time) {
        StringBuilder result = new StringBuilder();
        int h = (time % (24 * 60)) / 60;
        int m = (time % (24 * 60)) % 60;
        if (h < 10) {
            result.append(0).append(h);
        } else {
            result.append(h);
        }
        result.append(":");
        if (m < 10) {
            result.append(0).append(m);
        } else {
            result.append(m);
        }
        return String.valueOf(result);
    }
}