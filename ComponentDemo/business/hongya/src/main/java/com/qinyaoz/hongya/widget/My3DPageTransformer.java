package com.qinyaoz.hongya.widget;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by qinyaoz on 2019/4/30
 * Describe:
 **/
public class My3DPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View view, float v) {
        float MINALPHA = 0.3f;
        float MINSCALE = 0.7f;

        float alpha;
        float scale;
        if (v < -1 || v > 1) {
            alpha = MINALPHA;
            scale = MINSCALE;
        } else if (v < 0) {
            alpha = MINALPHA + (1 + v) * (1 - MINALPHA);
            scale = 1 + 0.3f * v;
        } else {
            alpha = MINALPHA + (1 - v) * (1 - MINALPHA);
            scale = 1 - 0.3f * v;
        }
        view.setAlpha(alpha);
        view.setScaleY(scale);
        view.setScaleX(scale);
    }
}
