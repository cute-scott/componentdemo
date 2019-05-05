package com.qinyaoz.commonui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.qinyaoz.baselib.utils.DensityUtil;
import com.qinyaoz.commonui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinyaoz on 2019/5/5
 * Describe:
 **/
public class AutoTextSwitcher extends TextSwitcher {
    private final int MSG_STARTING = 100;

    private int textColor = Color.BLACK;
    private int textSize = 18;
    private int inAnim = R.anim.fade_in_slide_in;
    private int outAnim = R.anim.fade_out_slide_out;
    private int gravity = Gravity.CENTER_VERTICAL | Gravity.START;
    private int duration = 3000;

    private OnItemClickListener itemClickListener;
    private List<String> data = new ArrayList<>();
    private int showPosition = -1;

    private List<TextView> caches = new ArrayList<>();
    private boolean status = false;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (MSG_STARTING == msg.what) {
                next();
                handler.sendEmptyMessageDelayed(MSG_STARTING, duration);
            }
        }
    };


    public AutoTextSwitcher(Context context) {
        super(context);
        init();
    }

    public AutoTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.cui_textswitcher);
        textColor = ta.getColor(R.styleable.cui_textswitcher_cui_textColor, Color.BLACK);
        textSize = (int) ta.getDimension(R.styleable.cui_textswitcher_cui_textSize, 18);
        inAnim = ta.getResourceId(R.styleable.cui_textswitcher_cui_inAnimation, R.anim.fade_in_slide_in);
        outAnim = ta.getResourceId(R.styleable.cui_textswitcher_cui_outAnimation, R.anim.fade_out_slide_out);
        duration = ta.getInt(R.styleable.cui_textswitcher_cui_duration, 2000);
        int g = ta.getInt(R.styleable.cui_textswitcher_cui_gravity, 2);
        switch (g) {
            case 1:
                gravity = Gravity.CENTER_VERTICAL | Gravity.END;
                break;
            case 3:
                gravity = Gravity.CENTER;
                break;
            default:
                gravity = Gravity.CENTER_VERTICAL | Gravity.START;
                break;
        }
        ta.recycle();
        init();
    }

    private void init() {
        setFactory(() -> {
            TextView textView = new TextView(getContext());
            textView.setTextColor(textColor);
            textView.setTextSize(DensityUtil.px2sp(getContext(), textSize));
            textView.setGravity(gravity);
            FrameLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.gravity = gravity;
            textView.setLayoutParams(layoutParams);
            textView.setOnClickListener(v -> {
                if (showPosition >= 0 && itemClickListener != null) {
                    itemClickListener.onItemClick(showPosition);
                }
            });
            caches.add(textView);
            return textView;
        });
        setAnim(inAnim, outAnim);
    }

    private void updateTextView() {
        for (TextView textView : caches) {
            textView.setTextSize(textSize);
            textView.setTextColor(textColor);
            textView.setGravity(gravity);
        }
    }

    private synchronized void next() {
        if (data.size() > 0) {
            if (showPosition < data.size() - 1) {
                showPosition++;
            } else {
                showPosition = 0;
            }
            setText(data.get(showPosition));
        }
    }

    public synchronized void setData(List<String> d) {
        data = d;
        reset();
    }

    public void start() {
        if (!status) {
            status = true;
            handler.sendEmptyMessageDelayed(MSG_STARTING, duration);
        }
    }

    public void stop() {
        if (status) {
            status = false;
            handler.removeMessages(MSG_STARTING);
        }
    }

    public synchronized void reset() {
        showPosition = -1;
        next();
    }

    public void setTextColor(int color) {
        this.textColor = color;
        updateTextView();
    }

    public void setTextSize(int size) {
        this.textSize = size;
        updateTextView();
    }

    public void setTextGravity(int g) {
        this.gravity = g;
        updateTextView();
    }

    public void setAnim(int inResId, int outResId) {
        setInAnimation(AnimationUtils.loadAnimation(getContext(), inResId));
        setOutAnimation(AnimationUtils.loadAnimation(getContext(), outResId));
    }

    public void setAnim(Animation inAnimation, Animation outAnimation) {
        setInAnimation(inAnimation);
        setOutAnimation(outAnimation);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
