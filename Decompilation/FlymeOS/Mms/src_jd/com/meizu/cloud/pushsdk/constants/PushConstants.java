package com.meizu.cloud.pushsdk.constants;

public abstract interface PushConstants
{
  public static final String C2DM_INTENT = "com.meizu.c2dm.intent.RECEIVE";
  public static final String CLICK_TYPE = "clickType";
  public static final String CLICK_TYPE_ACTIVITY = "1";
  public static final String CLICK_TYPE_LAUNCHER_ACTIVITY = "0";
  public static final String CLICK_TYPE_WEB = "2";
  public static final String CONTENT = "content";
  public static final String EXTRA = "extra";
  public static final String EXTRA_APPLICATION_PENDING_INTENT = "app";
  public static final String EXTRA_APP_IS_UNREGISTER_SUCCESS = "extra_app_is_unregister_success";
  public static final String EXTRA_APP_PUSH_ID = "registration_id";
  public static final String EXTRA_APP_PUSH_MESSAGE = "message";
  public static final String EXTRA_APP_PUSH_TASK_ID = "extra_app_push_task_Id";
  public static final String EXTRA_PUSH_MESSAGE = "message";
  public static final String EXTRA_REGISTRATION_ERROR = "registration_error";
  public static final String EXTRA_REGISTRATION_ID = "registration_id";
  public static final String EXTRA_SENDER = "sender";
  public static final String EXTRA_UNREGISTERED = "unregistered";
  public static final String INTENT_ACTIVITY_NAME = "activity";
  public static final String IS_DISCARD = "isDiscard";
  public static final String MZ_PUSH_MESSAGE_METHOD = "method";
  public static final String MZ_PUSH_MESSAGE_METHOD_ACTION_ARRIVED = "com.meizu.cloud.pushservice.action.notification.ARRIVED";
  public static final String MZ_PUSH_MESSAGE_METHOD_ACTION_MESSAGE = "message";
  public static final String MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_DELETE = "notification_delete";
  public static final String MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_SHOW = "notification_show";
  public static final String MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE = "private";
  public static final String MZ_PUSH_MESSAGE_STATISTICS_IMEI_KEY = "statistics_imei_key";
  public static final String MZ_PUSH_ON_MESSAGE_ACTION = "com.meizu.flyme.push.intent.MESSAGE";
  public static final String MZ_PUSH_ON_PUSH_NOTIFICATION_SHOW_ACTION = "com.meizu.cloud.pushservice.action.notification.SHOW";
  public static final String MZ_PUSH_ON_REGISTER_ACTION = "com.meizu.flyme.push.intent.REGISTER.FEEDBACK";
  public static final String MZ_PUSH_ON_START_PUSH_REGISTER = "com.meizu.flyme.push.intent.REGISTER";
  public static final String MZ_PUSH_ON_STOP_PUSH_REGISTER = "com.meizu.flyme.push.intent.UNREGISTER";
  public static final String MZ_PUSH_ON_UNREGISTER_ACTION = "com.meizu.flyme.push.intent.UNREGISTER.FEEDBACK";
  public static final String MZ_PUSH_PRIVATE_MESSAGE = "pushMessage";
  public static final String MZ_PUSH_PRIVATE_NOTIFICATION_CLICK = "com.meizu.cloud.pushservice.action.privatenotification.CLICK";
  public static final String MZ_PUSH_PRIVATE_NOTIFICATION_DELETE = "com.meizu.clod.pushservice.action.privatenotification.DELETE";
  public static final String MZ_PUSH_SERVICE_ACTION = "com.meizu.cloud.pushservice.action.PUSH_SERVICE";
  public static final String NOTIFY_TYPE = "notify_type";
  public static final String PARAMS = "parameters";
  public static final String PUSH_TYPE = "push_type";
  public static final String PUSH_TYPE_NOTIFY = "0";
  public static final String REGISTER_PACKAGE_NAME = "sender";
  public static final String REGISTRATION_CALLBACK_INTENT = "com.meizu.c2dm.intent.REGISTRATION";
  public static final String REQUEST_REGISTRATION_INTENT = "com.meizu.c2dm.intent.REGISTER";
  public static final String REQUEST_UNREGISTRATION_INTENT = "com.meizu.c2dm.intent.UNREGISTER";
  public static final String TASK_ID = "taskId";
  public static final String TITLE = "title";
  public static final String UNREGISTER_PACKAGE_NAME = "sender";
  public static final String WEB_URL = "url";
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.constants.PushConstants
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */