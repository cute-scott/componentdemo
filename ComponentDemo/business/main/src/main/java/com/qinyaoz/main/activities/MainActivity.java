package com.qinyaoz.main.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyaoz.baselib.protocol.router.RouterConstants;
import com.qinyaoz.main.R;
import com.qinyaoz.main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = RouterConstants.BizMain.MAIN)
public class MainActivity extends AppCompatActivity {
    @BindView(R2.id.main_textview)
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        textView.setText("测试");
    }
}
