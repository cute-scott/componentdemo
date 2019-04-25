package com.qinyaoz.componentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qinyaoz.baselib.protocol.router.RouterConstants;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.app_btn)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.app_btn:
                ARouter.getInstance().build(RouterConstants.BizMain.main).navigation();
                break;
        }
    }
}
