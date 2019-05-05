package com.qinyaoz.componentdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qinyaoz.baselib.protocol.provider.IBusinessMainProvider;
import com.qinyaoz.baselib.protocol.provider.ProviderConstants;
import com.qinyaoz.baselib.protocol.router.RouterConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppActivity extends AppCompatActivity {
    @Autowired(name = ProviderConstants.BUSINESS_MAIN)
    IBusinessMainProvider businessMainProvider;

    @BindView(R.id.app_recyclerview)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        init();
    }

    private void init() {
        List<ItemData> data = new ArrayList<>();
        data.add(new ItemData(RouterConstants.HongYa.INDEX, "tablelayout + viewpager示例"));
        data.add(new ItemData(null, "ARouter IOC示例"));
        data.add(new ItemData(RouterConstants.HongYa.PHOTO, "ScaleGestureDetector 使用"));
        data.add(new ItemData(RouterConstants.HongYa.PHOTO, "ScaleGestureDetector 使用"));
        data.add(new ItemData(RouterConstants.HongYa.SEARCH, "自定义TextSwitcher"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(data));
    }

    private class ListAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ItemData> itemDataList;
        ListAdapter(List<ItemData> d) {
            itemDataList = d;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(AppActivity.this).inflate(R.layout.app_data_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.bindView(itemDataList.get(i));
        }

        @Override
        public int getItemCount() {
            return itemDataList.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.app_item_title);
        }

        public void bindView(ItemData itemData) {
            textView.setText(itemData.getTitle());
            textView.setOnClickListener(v -> {
                if (TextUtils.isEmpty(itemData.getRouterUrl())) {
                    businessMainProvider.test("provider test");
                } else {
                    ARouter.getInstance().build(itemData.getRouterUrl()).navigation();
                }
            });
        }
    }
}
