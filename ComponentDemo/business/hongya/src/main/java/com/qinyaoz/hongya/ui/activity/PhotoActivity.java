package com.qinyaoz.hongya.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyaoz.baselib.protocol.router.RouterConstants;
import com.qinyaoz.hongya.R;
@Route(path = RouterConstants.HongYa.PHOTO)
public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hy_photo_activity);
    }
}
