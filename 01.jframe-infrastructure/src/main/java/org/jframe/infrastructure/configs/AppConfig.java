package org.jframe.infrastructure.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by screw on 2017/5/22.
 */
@Component
@PropertySource(value = "/WEB-INF/app.properties", ignoreResourceNotFound = true)
public class AppConfig {

    @Value("${app.serverName}")
    private String serverName;

    @Value("${app.host}")
    private String host;

    @Value("${app.isTestServer}")
    private boolean isTestServer;

    @Value("${app.redisKeyPrefix}")
    private String redisKeyPrefix;

    @Value("${app.logsDbName}")
    private String logsDbName;

    @Value("${app.cdnHostOfImages}")
    private String cdnHostOfImages;

    @Value("${app.cookieDomain}")
    private String cookieDomain;

    @Value("${app.authCookieName}")
    private String authCookieName;

    @Value("${app.authCookieExpireMinutes}")
    private int authCookieExpireMinutes;

    @Value("${app.sessionExpireSeconds}")
    private int sessionExpireSeconds;

    @Value("${app.printStackTrace}")
    private boolean printStackTrace;

    @Value("${app.mock}")
    private boolean mock=false;

    @Value("${app.entityPackage}")
    private String entityPackage;

    @Value("${app.isDeveloperMode}")
    private boolean isDeveloperMode=false;

    @Value("${app.sms.ipLimit}")
    private int smsIpLimit = 1000;

    @Value("${app.sms.generalLimit}")
    private int smsGeneralLimit = 20;

    @Value("${app.sms.registerLimit}")
    private int smsRegisterLimit = 5;

    @Value("${app.sms.loginLimit}")
    private int smsLoginLimit = 5;

    @Value("${app.sms.resetPasswordLimit}")
    private int smsResetPasswordLimit = 5;

    @Value("${app.sms.resetPayPasswordLimit:10}")
    private int smsResetPayPasswordLimit = 5;

    @Value("${app.fileLogPath}")
    private String fileLogPath;

    //---------------------------------------------

    public String getServerName() {
        return this.serverName;
    }

    public boolean isTestServer() {
        return isTestServer;
    }

    public String getHost() {
        return host;
    }

    public String getRedisKeyPrefix() {
        return redisKeyPrefix;
    }

    public String getCdnHostOfImages() {
        return cdnHostOfImages;
    }

    public String getLogsDbName() {
        return logsDbName;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public String getAuthCookieName() {
        return authCookieName;
    }

    public int getAuthCookieExpireMinutes() {
        return authCookieExpireMinutes;
    }

    public int getSessionExpireSeconds() {
        return sessionExpireSeconds;
    }

    public boolean isPrintStackTrace() {
        return printStackTrace;
    }

    public boolean isMock() {
        return mock;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public boolean isDeveloperMode() {
        return isDeveloperMode;
    }

    public int getSmsIpLimit() {
        return smsIpLimit;
    }

    public int getSmsGeneralLimit() {
        return smsGeneralLimit;
    }

    public int getSmsRegisterLimit() {
        return smsRegisterLimit;
    }

    public int getSmsLoginLimit() {
        return smsLoginLimit;
    }

    public int getSmsResetPasswordLimit() {
        return smsResetPasswordLimit;
    }

    public int getSmsResetPayPasswordLimit() {
        return smsResetPayPasswordLimit;
    }

    public String getFileLogPath() {
        return fileLogPath;
    }
}
