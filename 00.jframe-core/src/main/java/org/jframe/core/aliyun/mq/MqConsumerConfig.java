package org.jframe.core.aliyun.mq;

import org.jframe.core.aliyun.AliyunAccountConfig;

/**
 * Created by leo on 2017-10-21.
 */
public interface MqConsumerConfig extends AliyunAccountConfig {
    String getTopic();
    String getConsumerI();
}
