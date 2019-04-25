package com.qinyaoz.baselib;

import com.google.auto.service.AutoService;
import com.qinyaoz.baselib.protocol.dispatcher.AbstractComponentBoot;

@AutoService(AbstractComponentBoot.class)
public class BaseComponentBoot extends AbstractComponentBoot {

    @Override
    public String getName() {
        return "baseLib";
    }

    @Override
    public int getType() {
        return TYPE_BASE_LIB;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
