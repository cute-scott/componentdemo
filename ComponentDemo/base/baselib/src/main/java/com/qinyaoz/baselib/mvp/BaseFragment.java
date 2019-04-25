package com.qinyaoz.baselib.mvp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

/**
 * Created by haoyuew on 16/3/31.
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected BasePresenter mPresenter;

    public abstract BasePresenter getPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();

        if(null != mPresenter){
            mPresenter.attachView(this);
            mPresenter.onCreate(getArguments());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(null != mPresenter){
            mPresenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(null != mPresenter){
            mPresenter.onResume();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(null != mPresenter){
            mPresenter.onStop();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != mPresenter){
            mPresenter.onDestroy();
        }
    }

    private static final int MSG_TOAST_MESSAGE = 0x10;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_TOAST_MESSAGE:
                    String message = msg.obj.toString();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //防止非UI线程
    @Override
    public void toastMessage(String message) {
        Message msg = mHandler.obtainMessage();
        msg.what = MSG_TOAST_MESSAGE;
        msg.obj = message;

        mHandler.sendMessage(msg);
    }
}
