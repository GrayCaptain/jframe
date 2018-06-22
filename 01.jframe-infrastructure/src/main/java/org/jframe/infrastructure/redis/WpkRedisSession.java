package org.jframe.infrastructure.redis;

import org.jframe.infrastructure.AppContext;
import org.jframe.core.redis.RedisSession;

/**
 * Created by Leo on 2017/11/3.
 */
public class WpkRedisSession extends RedisSession {

    public WpkRedisSession() {
        super(RedisWpkPoolContext.getInstance());
    }

    @Override
    protected String getActualKey(String key) {
        return AppContext.getAppConfig().getRedisKeyPrefix() + key;
    }


}
