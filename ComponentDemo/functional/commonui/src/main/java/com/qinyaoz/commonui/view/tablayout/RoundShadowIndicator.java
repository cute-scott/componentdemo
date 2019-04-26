package com.qinyaoz.commonui.view.tablayout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Author: tone
 * Date: 2019-04-23 15:40
 * Description:
 */
public class RoundShadowIndicator extends Drawable {

    private Paint paint;
    private float roundRadius;//圆角
    private boolean useShadow = false;
    private boolean useGradient = false;

    private int[] colors;


    public RoundShadowIndicator() {
        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        colors = new int[]{Color.parseColor("#FF2B67"), Color.parseColor("#FF6447")};
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect rect = getBounds();

        if (useShadow) {
            paint.setShader(null);
            paint.setColor(Color.WHITE);
            //paint.setShader 后 阴影颜色和shader一样
            paint.setShadowLayer(25, 0, 8, Color.parseColor("#FFAAAAAA"));
            if (roundRadius > 0) {
                canvas.drawRoundRect(new RectF(rect), roundRadius, roundRadius, paint);
            } else {
                canvas.drawRect(new RectF(rect), paint);
            }
        }

        paint.setShadowLayer(0, 0, 0, 0);
        if (useGradient && colors.length > 1) {
            Shader shader = new LinearGradient(rect.left, 0, rect.right, 0, colors, null, Shader.TileMode.CLAMP);
            paint.setShader(shader);
        } else {
            paint.setColor(colors[0]);
        }
        if (roundRadius > 0) {
            canvas.drawRoundRect(new RectF(rect), roundRadius, roundRadius, paint);
        } else {
            canvas.drawRect(new RectF(rect), paint);
        }


    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    public void setRoundRadius(float roundRadius) {
        this.roundRadius = roundRadius;
    }

    public void setUseGradient(boolean useGradient) {
        this.useGradient = useGradient;
    }

    public void setUseShadow(boolean useShadow) {
        this.useShadow = useShadow;
    }

    public void setColor(int[] colors) {
        if (colors == null || colors.length == 0)
            return;
        this.colors = colors;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }


    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
