package com.qinyaoz.baselib.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by haoyuew on 16/4/1.
 */
public abstract class BaseActivity<P extends BasePresenter, V extends BaseView> extends Activity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNeedCustomTheme() != -1) {
            setTheme(isNeedCustomTheme());
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }


    @Override
    protected void onStop() {
        super.onStop();
//        if (deep_link) {
//            DeepShare.onStop();//停止DeepShare
//        }

    }

    /**
     * 给Activity绑定Presenter
     * 注意：Presenter必须在View之前进行绑定，否则抛出异常
     *
     * @param presenter
     */
    protected void bindPresenter(P presenter) {
        mPresenter = presenter;
    }

    /**
     * 给Presenter绑定view
     *
     * @param view
     */
    protected void bindView(V view) {
        if (mPresenter == null)
            throw new IllegalStateException("presenter must be bind before view");
        mPresenter.attachView(view);
    }

    /**
     * 获取绑定在Activity上的Presenter
     *
     * @return
     */
    protected P getPresener() {
        return mPresenter;
    }

    protected int isNeedCustomTheme() {
        return -1;
    }

    /**
     * 初始化页面组件
     */
    public abstract void initPages();

    /**
     * 设置页面的布局文件,返回布局文件的id数值,例如R.layout.index
     *
     * @return
     */
    protected abstract int setLayoutId();

    public boolean isHandlerValid(Handler handler) {
        return !(null == handler
                || null == BaseActivity.this
                || BaseActivity.this.isFinishing());
    }

    protected static boolean hasGetPrize = false;

    public void setHasGetPrize(boolean has) {
        hasGetPrize = has;
    }

    public boolean hasGetPrize() {
        return hasGetPrize;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //处理崩溃后Fragment重叠的问题
        //super.onSaveInstanceState(outState);
    }
}
