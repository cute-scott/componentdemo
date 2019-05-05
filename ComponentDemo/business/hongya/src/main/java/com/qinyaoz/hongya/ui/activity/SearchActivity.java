package com.qinyaoz.hongya.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyaoz.baselib.protocol.router.RouterConstants;
import com.qinyaoz.commonui.view.AutoTextSwitcher;
import com.qinyaoz.hongya.R;
import com.qinyaoz.hongya.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = RouterConstants.HongYa.SEARCH)
public class SearchActivity extends AppCompatActivity {

    @BindView(R2.id.hy_auto_text_switcher)
    AutoTextSwitcher autoTextSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hy_search_activity);
        ButterKnife.bind(this);

        List<String> list = new ArrayList<>();
        list.add("氢能有国家承认吗");
        list.add("缤越试驾视频");
        list.add("edg厂长首发");
        list.add("玉龙雪山疑山体崩塌");

        autoTextSwitcher.setData(list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoTextSwitcher.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        autoTextSwitcher.stop();
    }
}
