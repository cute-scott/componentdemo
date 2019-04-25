package com.qinyaoz.baselib.protocol.router;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Created by qinyaoz on 2019/4/25
 * Describe:登录拦截器
 **/
@Interceptor(priority = 0)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        //判断当前路径是否需要拦截
        Log.i(LoginInterceptor.class.getSimpleName(), "拦截路径：" + postcard.getPath());
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

    }
}
