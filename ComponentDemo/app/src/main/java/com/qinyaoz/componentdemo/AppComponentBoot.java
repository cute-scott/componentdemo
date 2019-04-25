package com.qinyaoz.componentdemo;

import com.google.auto.service.AutoService;
import com.qinyaoz.baselib.protocol.dispatcher.AbstractComponentBoot;

@AutoService(AbstractComponentBoot.class)
public class AppComponentBoot extends AbstractComponentBoot {
    @Override
    public String getName() {
        return "app";
    }

    @Override
    public int getType() {
        return TYPE_APP;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
