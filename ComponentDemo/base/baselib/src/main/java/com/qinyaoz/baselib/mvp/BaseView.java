package com.qinyaoz.baselib.mvp;

import android.content.Context;

/**
 * Created by haoge on 2017/6/12.
 */

public interface BaseView {
    void toastMessage(String message);
    Context getContext();
}
