package com.qinyaoz.baselib.mvp;

/**
 * Created by haoyuew on 16/3/30.
 */
public interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
