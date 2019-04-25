package com.qinyaoz.baselib.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

/**
 * Created by haoyuew on 16/3/30.
 */
public abstract class BasePresenter<T extends BaseView> implements Presenter<T>, PresenterDelegate {

    private T mMvpView;

    public BasePresenter() {
    }

    public BasePresenter(T view) {
        mMvpView = view;
    }

    @Override
    public void attachView(T view) {
        mMvpView = view;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return null != mMvpView;
    }

    public T getView() {
        return mMvpView;
    }

    public Context getContext(){
        if (mMvpView == null){
            return null;
        }
        return mMvpView.getContext();
    }

    public void checkViewAttached(){
        if(!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    @Override
    public void onCreate(Intent mIntent) {

    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onDestroy() {
        detachView();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    /**
     * 为intent增加部分参数,便于统计浏览数据的统计
     *
     * @param intent
     * @param fromPage
     * @param fromType
     * @param fromId
     * @param fromPageAttri
     */
    public void putBrowseParamToIntent(Intent intent, String fromPage, String fromType, String fromId, String fromPageAttri) {
        intent.putExtra("fromPage", fromPage);
        intent.putExtra("fromType", fromType);
        intent.putExtra("fromId", fromId);
        intent.putExtra("fromPageAttri", fromPageAttri);
    }

    /**
     * 判断view是否为空 或activity是否销毁
     *
     * @return true为空
     */
    protected boolean isNullView() {
        boolean bool = getView() == null || getView().getContext() == null || ((Activity) getView().getContext()).isFinishing();
        if (!bool && Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            bool = ((Activity) getView().getContext()).isDestroyed();
        }
        return bool;
    }


}
