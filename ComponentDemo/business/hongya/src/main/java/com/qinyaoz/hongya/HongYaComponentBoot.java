package com.qinyaoz.hongya;

import com.google.auto.service.AutoService;
import com.qinyaoz.baselib.protocol.dispatcher.AbstractComponentBoot;

/**
 * Created by qinyaoz on 2019/4/30
 * Describe:
 **/
@AutoService(AbstractComponentBoot.class)
public class HongYaComponentBoot extends AbstractComponentBoot {
    @Override
    public String getName() {
        return "hongya";
    }

    @Override
    public int getType() {
        return TYPE_BUSINESS;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
