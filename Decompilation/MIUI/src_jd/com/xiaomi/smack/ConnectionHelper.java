package com.xiaomi.smack;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ConnectionHelper
{
  public static int asErrorCode(Throwable paramThrowable)
  {
    Object localObject2 = paramThrowable;
    Object localObject1 = localObject2;
    if ((localObject2 instanceof XMPPException))
    {
      localObject1 = localObject2;
      if (((XMPPException)localObject2).getWrappedThrowable() != null) {
        localObject1 = ((XMPPException)localObject2).getWrappedThrowable();
      }
    }
    localObject2 = ((Throwable)localObject1).getMessage();
    if (((Throwable)localObject1).getCause() != null) {
      localObject2 = ((Throwable)localObject1).getCause().getMessage();
    }
    if ((localObject1 instanceof SocketTimeoutException)) {
      return 105;
    }
    if ((localObject1 instanceof SocketException))
    {
      if (((String)localObject2).indexOf("Network is unreachable") != -1) {
        return 102;
      }
      if (((String)localObject2).indexOf("Connection refused") != -1) {
        return 103;
      }
      if (((String)localObject2).indexOf("Connection timed out") != -1) {
        return 105;
      }
      if (((String)localObject2).endsWith("EACCES (Permission denied)")) {
        return 101;
      }
      if (((String)localObject2).indexOf("Connection reset by peer") != -1) {
        return 109;
      }
      if (((String)localObject2).indexOf("Broken pipe") != -1) {
        return 110;
      }
      if (((String)localObject2).indexOf("No route to host") != -1) {
        return 104;
      }
      if (((String)localObject2).endsWith("EINVAL (Invalid argument)")) {
        return 106;
      }
      return 199;
    }
    if ((localObject1 instanceof UnknownHostException)) {
      return 107;
    }
    if ((paramThrowable instanceof XMPPException)) {
      return 399;
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.ConnectionHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */