package com.qinyaoz.share;

import com.google.auto.service.AutoService;
import com.qinyaoz.baselib.protocol.dispatcher.AbstractComponentBoot;

@AutoService(AbstractComponentBoot.class)
public class ShareComponentBoot extends AbstractComponentBoot {
    @Override
    public String getName() {
        return "share";
    }

    @Override
    public int getType() {
        return TYPE_FUNCTIONAL;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
