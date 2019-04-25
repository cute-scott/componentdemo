package com.qinyaoz.main;

import com.google.auto.service.AutoService;
import com.qinyaoz.baselib.protocol.dispatcher.AbstractComponentBoot;

@AutoService(AbstractComponentBoot.class)
public class MainComponentBoot extends AbstractComponentBoot {
    @Override
    public String getName() {
        return "main";
    }

    @Override
    public int getType() {
        return TYPE_BUSINESS;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
