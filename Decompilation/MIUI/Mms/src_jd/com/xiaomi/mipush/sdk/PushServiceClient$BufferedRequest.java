package com.xiaomi.mipush.sdk;

import com.xiaomi.xmpush.thrift.ActionType;
import org.apache.thrift.TBase;

class PushServiceClient$BufferedRequest<T extends TBase<T, ?>>
{
  ActionType actionType;
  boolean encrypt;
  T message;
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushServiceClient.BufferedRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */