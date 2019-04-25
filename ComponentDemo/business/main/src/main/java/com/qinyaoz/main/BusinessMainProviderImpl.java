package com.qinyaoz.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qinyaoz.baselib.protocol.provider.IBusinessMainProvider;
import com.qinyaoz.baselib.protocol.provider.ProviderConstants;

/**
 * Created by qinyaoz on 2019/4/25
 * Describe:
 **/
@Route(path = ProviderConstants.BUSINESS_MAIN)
public class BusinessMainProviderImpl implements IBusinessMainProvider {
    private Context context;

    @Override
    public void test(String desc) {
        Toast.makeText(context, "desc=" + desc, Toast.LENGTH_LONG).show();
    }

    @Override
    public void init(Context c) {
        context = c.getApplicationContext();
        Log.i(BusinessMainProviderImpl.class.getSimpleName(), "BusinessMainProviderImpl has been load");
    }
}
