package com.qinyaoz.componentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qinyaoz.baselib.protocol.provider.IBusinessMainProvider;
import com.qinyaoz.baselib.protocol.provider.ProviderConstants;
import com.qinyaoz.baselib.protocol.router.RouterConstants;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppActivity extends AppCompatActivity {
    @Autowired(name = ProviderConstants.BUSINESS_MAIN)
    IBusinessMainProvider businessMainProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
    }

    @OnClick({R.id.app_btn, R.id.app_btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.app_btn:
                ARouter.getInstance().build(RouterConstants.BizMain.MAIN).navigation();
                break;
            case R.id.app_btn2:
                businessMainProvider.test("provider test");
                break;
        }
    }
}
