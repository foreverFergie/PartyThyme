package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class TextDrawable extends Drawable {
    private final String text;
    private final Paint paint;

    public TextDrawable(String text, Context context) {

        this.text = text;

        this.paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.colorPrimary);
        paint.setColor(color);
        paint.setTextSize(30f);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        //paint.setShadowLayer(6f, 0, 0, Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(text, 0, 130, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
