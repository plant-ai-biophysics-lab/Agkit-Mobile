package com.schneewittchen.rosandroid.widgets.togglebutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.text.DynamicLayout;

import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.ui.views.PublisherView;

import androidx.annotation.Nullable;

/**
 * TODO: Description
 *
 * @author Dragos Circa
 * @version 1.0.0
 * @created on 02.11.2020
 * @updated on 18.11.2020
 * @modified by Nils Rottmann
 */

public class ToggleButtonView extends PublisherView {
    public static final String TAG = "ToggleButtonView";

    Paint ToggleButtonPaint;
    TextPaint textPaint;
    DynamicLayout dynamicLayout;
    public boolean press = false;
    public String status = "\nOFF";

    public ToggleButtonView(Context context) {
        super(context);
        init();
    }

    public ToggleButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ToggleButtonPaint = new Paint();
        ToggleButtonPaint.setColor(getResources().getColor(R.color.colorPrimary));
        ToggleButtonPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextSize(26 * getResources().getDisplayMetrics().density);
    }

    private void changeState(boolean pressed) {
        this.publishViewData(new ToggleButtonData(pressed));
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (!press) { // Currently off, turn on
                    ToggleButtonPaint.setColor(getResources().getColor(R.color.color_attention));
                    changeState(true);
                    status = "\nON";
                    press = !press;
                    break;
                } else if (press) { // Currently on, turn off
                    ToggleButtonPaint.setColor(getResources().getColor(R.color.colorPrimary));
                    changeState(false);
                    status = "\nOFF";
                    press = !press;
                    break;
                }
            default:
                return false;
        }

        return true;
    }


    @Override
    public void onDraw(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();
        float textLayoutWidth = width;

        ToggleButtonEntity entity = (ToggleButtonEntity) widgetEntity;

        if (entity.rotation == 90 || entity.rotation == 270) {
            textLayoutWidth = height;
        }

        canvas.drawRect(new Rect(0,0,(int)width,(int)height),ToggleButtonPaint);

        dynamicLayout = new DynamicLayout(entity.text + status,
                textPaint,
                (int) textLayoutWidth,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0,
                false);
        canvas.save();
        canvas.rotate(entity.rotation,width / 2,height / 2);
        canvas.translate( ((width / 2)-dynamicLayout.getWidth()/2), height / 2 - dynamicLayout.getHeight() / 2);
        dynamicLayout.draw(canvas);
        canvas.restore();
    }
}
