package org.jframe.infrastructure.redis;

import org.jframe.core.helpers.JsonHelper;
import org.jframe.core.helpers.StringHelper;
import org.jframe.core.weixin.core.WxCacheProvider;
import org.jframe.core.weixin.core.dtos.AccessTokenDto;
import org.jframe.core.weixin.core.dtos.JsApiTicketDto;
import org.jframe.infrastructure.AppContext;

/**
 * Created by leo on 2017-09-24.
 */
public class RedisWxCacheProvider implements WxCacheProvider {
    @Override
    public AccessTokenDto getAccessToken(String appId) {
        try(JframeRedisSession session = new JframeRedisSession()){
            String value = session.get(AppContext.RedisKeys.weixinAccessToken(appId));
            if(StringHelper.isNullOrEmpty(value)){
                return null;
            }
            return JsonHelper.deserialize(value, AccessTokenDto.class);
        }
    }

    @Override
    public void setAccessToken(String appId, AccessTokenDto tokenDto) {
        try(JframeRedisSession session = new JframeRedisSession()){
            session.setex(AppContext.RedisKeys.weixinAccessToken(appId), 6900, JsonHelper.serialize(tokenDto));
        }
    }

    @Override
    public JsApiTicketDto getJsApiTicket(String appId) {
        try(JframeRedisSession session = new JframeRedisSession()){
            String value = session.get(AppContext.RedisKeys.weixinJsApiTicket(appId));
            if(StringHelper.isNullOrEmpty(value)){
                return null;
            }
            return JsonHelper.deserialize(value, JsApiTicketDto.class);
        }
    }

    @Override
    public void setJsApiTicket(String appId, JsApiTicketDto ticketDto) {
        try(JframeRedisSession session = new JframeRedisSession()){
            session.setex(AppContext.RedisKeys.weixinJsApiTicket(appId), 6900, JsonHelper.serialize(ticketDto));
        }
    }

}
