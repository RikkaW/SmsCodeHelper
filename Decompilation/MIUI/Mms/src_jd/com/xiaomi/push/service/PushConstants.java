package com.xiaomi.push.service;

public abstract class PushConstants
{
  public static String ACTION_BATCH_SEND_MESSAGE;
  public static String ACTION_CHANGE_HOST;
  public static String ACTION_CLOSE_CHANNEL;
  public static String ACTION_FORCE_RECONNECT;
  public static String ACTION_OPEN_CHANNEL;
  public static String ACTION_PING_TIMER;
  public static String ACTION_RESET_CONNECTION;
  public static String ACTION_SEND_IQ;
  public static String ACTION_SEND_MESSAGE;
  public static String ACTION_SEND_PRESENCE;
  public static String ACTION_SEND_STATS;
  public static String ACTION_UPDATE_CHANNEL_INFO;
  public static String EXTRA_AUTH_METHOD;
  public static String EXTRA_CHANNEL_ID;
  public static String EXTRA_CLIENT_ATTR;
  public static String EXTRA_CLOUD_ATTR;
  public static String EXTRA_KICK;
  public static String EXTRA_NOTIFY_ID = "ext_notify_id";
  public static String EXTRA_NOTIFY_TYPE = "ext_notify_type";
  public static String EXTRA_PACKAGE_NAME;
  public static String EXTRA_SECURITY;
  public static String EXTRA_SESSION = "ext_session";
  public static String EXTRA_SID;
  public static String EXTRA_SIG = "sig";
  public static String EXTRA_TOKEN;
  public static String EXTRA_USER_ID;
  public static String NOTIFICATION_CLICK_DEFAULT = "1";
  public static String NOTIFICATION_CLICK_INTENT = "2";
  public static String NOTIFICATION_CLICK_WEB_PAGE = "3";
  
  static
  {
    ACTION_OPEN_CHANNEL = "com.xiaomi.push.OPEN_CHANNEL";
    ACTION_SEND_MESSAGE = "com.xiaomi.push.SEND_MESSAGE";
    ACTION_SEND_IQ = "com.xiaomi.push.SEND_IQ";
    ACTION_BATCH_SEND_MESSAGE = "com.xiaomi.push.BATCH_SEND_MESSAGE";
    ACTION_SEND_PRESENCE = "com.xiaomi.push.SEND_PRES";
    ACTION_CLOSE_CHANNEL = "com.xiaomi.push.CLOSE_CHANNEL";
    ACTION_FORCE_RECONNECT = "com.xiaomi.push.FORCE_RECONN";
    ACTION_RESET_CONNECTION = "com.xiaomi.push.RESET_CONN";
    ACTION_UPDATE_CHANNEL_INFO = "com.xiaomi.push.UPDATE_CHANNEL_INFO";
    ACTION_SEND_STATS = "com.xiaomi.push.SEND_STATS";
    ACTION_CHANGE_HOST = "com.xiaomi.push.CHANGE_HOST";
    ACTION_PING_TIMER = "com.xiaomi.push.PING_TIMER";
    EXTRA_USER_ID = "ext_user_id";
    EXTRA_CHANNEL_ID = "ext_chid";
    EXTRA_SID = "ext_sid";
    EXTRA_TOKEN = "ext_token";
    EXTRA_AUTH_METHOD = "ext_auth_method";
    EXTRA_SECURITY = "ext_security";
    EXTRA_KICK = "ext_kick";
    EXTRA_CLIENT_ATTR = "ext_client_attr";
    EXTRA_CLOUD_ATTR = "ext_cloud_attr";
    EXTRA_PACKAGE_NAME = "ext_pkg_name";
  }
  
  public static String getErrorDesc(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return String.valueOf(paramInt);
    case 0: 
      return "ERROR_OK";
    case 1: 
      return "ERROR_SERVICE_NOT_INSTALLED";
    case 2: 
      return "ERROR_NETWORK_NOT_AVAILABLE";
    case 3: 
      return "ERROR_NETWORK_FAILED";
    case 4: 
      return "ERROR_ACCESS_DENIED";
    case 5: 
      return "ERROR_AUTH_FAILED";
    case 6: 
      return "ERROR_MULTI_LOGIN";
    case 7: 
      return "ERROR_SERVER_ERROR";
    case 8: 
      return "ERROR_RECEIVE_TIMEOUT";
    case 9: 
      return "ERROR_READ_ERROR";
    case 10: 
      return "ERROR_SEND_ERROR";
    case 11: 
      return "ERROR_RESET";
    case 12: 
      return "ERROR_NO_CLIENT";
    case 13: 
      return "ERROR_SERVER_STREAM";
    case 14: 
      return "ERROR_THREAD_BLOCK";
    case 15: 
      return "ERROR_SERVICE_DESTROY";
    case 16: 
      return "ERROR_SESSION_CHANGED";
    case 17: 
      return "ERROR_READ_TIMEOUT";
    case 18: 
      return "ERROR_CONNECTIING_TIMEOUT";
    case 19: 
      return "ERROR_USER_BLOCKED";
    case 20: 
      return "ERROR_REDIRECT";
    case 21: 
      return "ERROR_BIND_TIMEOUT";
    }
    return "ERROR_PING_TIMEOUT";
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PushConstants
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */