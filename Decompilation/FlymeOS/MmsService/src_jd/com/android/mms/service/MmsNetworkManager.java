package com.android.mms.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.NetworkRequest.Builder;
import android.util.Log;
import com.android.okhttp.ConnectionPool;
import com.android.okhttp.HostResolver;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MmsNetworkManager
  implements HostResolver
{
  private static final InetAddress[] EMPTY_ADDRESS_ARRAY;
  private static final boolean httpKeepAlive = Boolean.parseBoolean(System.getProperty("http.keepAlive", "true"));
  private static final long httpKeepAliveDurationMs;
  private static final int httpMaxConnections;
  private ConnectionPool mConnectionPool;
  private volatile ConnectivityManager mConnectivityManager;
  private final Context mContext;
  private boolean mIsNetworkLost;
  private boolean mIsNetworkReleased;
  private MmsHttpClient mMmsHttpClient;
  private int mMmsRequestCount;
  private Network mNetwork;
  private ConnectivityManager.NetworkCallback mNetworkCallback;
  private final NetworkRequest mNetworkRequest;
  private int mPendingMmsRequestCount;
  private final int mSubId;
  
  static
  {
    if (httpKeepAlive) {}
    for (int i = Integer.parseInt(System.getProperty("http.maxConnections", "5"));; i = 0)
    {
      httpMaxConnections = i;
      httpKeepAliveDurationMs = Long.parseLong(System.getProperty("http.keepAliveDuration", "300000"));
      EMPTY_ADDRESS_ARRAY = new InetAddress[0];
      return;
    }
  }
  
  public MmsNetworkManager(Context paramContext, int paramInt)
  {
    mContext = paramContext;
    mNetworkCallback = null;
    mNetwork = null;
    mMmsRequestCount = 0;
    mConnectivityManager = null;
    mConnectionPool = null;
    mMmsHttpClient = null;
    mSubId = paramInt;
    mIsNetworkLost = false;
    mIsNetworkReleased = true;
    mNetworkRequest = new NetworkRequest.Builder().addTransportType(0).addCapability(0).setNetworkSpecifier(Integer.toString(mSubId)).build();
  }
  
  private ConnectivityManager getConnectivityManager()
  {
    if (mConnectivityManager == null) {
      mConnectivityManager = ((ConnectivityManager)mContext.getSystemService("connectivity"));
    }
    return mConnectivityManager;
  }
  
  private ConnectionPool getOrCreateConnectionPoolLocked()
  {
    if (mConnectionPool == null) {
      mConnectionPool = new ConnectionPool(httpMaxConnections, httpKeepAliveDurationMs);
    }
    return mConnectionPool;
  }
  
  private void newRequest()
  {
    ConnectivityManager localConnectivityManager = getConnectivityManager();
    mNetworkCallback = new ConnectivityManager.NetworkCallback()
    {
      public void onAvailable(Network paramAnonymousNetwork)
      {
        super.onAvailable(paramAnonymousNetwork);
        Log.d("MmsService", "NetworkCallbackListener.onAvailable: network=" + paramAnonymousNetwork);
        synchronized (MmsNetworkManager.this)
        {
          MmsNetworkManager.access$002(MmsNetworkManager.this, paramAnonymousNetwork);
          notifyAll();
          return;
        }
      }
      
      public void onLost(Network arg1)
      {
        super.onLost(???);
        Log.d("MmsService", "NetworkCallbackListener.onLost: network=" + ???);
        synchronized (MmsNetworkManager.this)
        {
          MmsNetworkManager.access$102(MmsNetworkManager.this, true);
          MmsNetworkManager.this.releaseRequestLocked(this);
          notifyAll();
          return;
        }
      }
      
      public void onUnavailable()
      {
        super.onUnavailable();
        Log.d("MmsService", "NetworkCallbackListener.onUnavailable");
        synchronized (MmsNetworkManager.this)
        {
          MmsNetworkManager.access$102(MmsNetworkManager.this, true);
          MmsNetworkManager.this.releaseRequestLocked(this);
          notifyAll();
          return;
        }
      }
    };
    Log.d("MmsService", "newRequest subid = " + mSubId);
    localConnectivityManager.requestNetwork(mNetworkRequest, mNetworkCallback, 60000);
  }
  
  private void releaseRequestLocked(ConnectivityManager.NetworkCallback paramNetworkCallback)
  {
    if (paramNetworkCallback != null)
    {
      Log.d("MmsService", "MmsNetworkManager: releaseRequestLocked");
      getConnectivityManager().unregisterNetworkCallback(paramNetworkCallback);
    }
    resetLocked();
  }
  
  private void resetLocked()
  {
    mIsNetworkReleased = true;
    mNetworkCallback = null;
    mNetwork = null;
    mMmsRequestCount = 0;
    mConnectionPool = null;
    mMmsHttpClient = null;
  }
  
  /* Error */
  public void acquireNetwork()
    throws com.android.mms.service.exception.MmsNetworkException
  {
    // Byte code:
    //   0: ldc -87
    //   2: ldc -46
    //   4: invokestatic 190	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   7: pop
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: aload_0
    //   12: getfield 96	com/android/mms/service/MmsNetworkManager:mMmsRequestCount	I
    //   15: iconst_1
    //   16: iadd
    //   17: putfield 96	com/android/mms/service/MmsNetworkManager:mMmsRequestCount	I
    //   20: aload_0
    //   21: iconst_0
    //   22: putfield 106	com/android/mms/service/MmsNetworkManager:mIsNetworkLost	Z
    //   25: aload_0
    //   26: iconst_0
    //   27: putfield 108	com/android/mms/service/MmsNetworkManager:mIsNetworkReleased	Z
    //   30: aload_0
    //   31: getfield 94	com/android/mms/service/MmsNetworkManager:mNetwork	Landroid/net/Network;
    //   34: ifnull +14 -> 48
    //   37: ldc -87
    //   39: ldc -44
    //   41: invokestatic 190	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   44: pop
    //   45: aload_0
    //   46: monitorexit
    //   47: return
    //   48: ldc -87
    //   50: ldc -42
    //   52: invokestatic 190	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: aload_0
    //   57: invokespecial 216	com/android/mms/service/MmsNetworkManager:newRequest	()V
    //   60: invokestatic 222	android/os/SystemClock:elapsedRealtime	()J
    //   63: lstore_3
    //   64: ldc2_w 223
    //   67: lstore_1
    //   68: lload_1
    //   69: lconst_0
    //   70: lcmp
    //   71: ifle +53 -> 124
    //   74: aload_0
    //   75: lload_1
    //   76: invokevirtual 228	java/lang/Object:wait	(J)V
    //   79: aload_0
    //   80: getfield 94	com/android/mms/service/MmsNetworkManager:mNetwork	Landroid/net/Network;
    //   83: ifnull +26 -> 109
    //   86: aload_0
    //   87: monitorexit
    //   88: return
    //   89: astore 5
    //   91: aload_0
    //   92: monitorexit
    //   93: aload 5
    //   95: athrow
    //   96: astore 5
    //   98: ldc -87
    //   100: ldc -26
    //   102: invokestatic 233	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   105: pop
    //   106: goto -27 -> 79
    //   109: aload_0
    //   110: getfield 106	com/android/mms/service/MmsNetworkManager:mIsNetworkLost	Z
    //   113: ifeq +37 -> 150
    //   116: ldc -87
    //   118: ldc -21
    //   120: invokestatic 190	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: ldc -87
    //   126: ldc -19
    //   128: invokestatic 190	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   131: pop
    //   132: aload_0
    //   133: aload_0
    //   134: getfield 92	com/android/mms/service/MmsNetworkManager:mNetworkCallback	Landroid/net/ConnectivityManager$NetworkCallback;
    //   137: invokespecial 142	com/android/mms/service/MmsNetworkManager:releaseRequestLocked	(Landroid/net/ConnectivityManager$NetworkCallback;)V
    //   140: new 206	com/android/mms/service/exception/MmsNetworkException
    //   143: dup
    //   144: ldc -17
    //   146: invokespecial 242	com/android/mms/service/exception/MmsNetworkException:<init>	(Ljava/lang/String;)V
    //   149: athrow
    //   150: invokestatic 222	android/os/SystemClock:elapsedRealtime	()J
    //   153: lstore_1
    //   154: lload_3
    //   155: ldc2_w 223
    //   158: ladd
    //   159: lload_1
    //   160: lsub
    //   161: lstore_1
    //   162: goto -94 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	MmsNetworkManager
    //   67	95	1	l1	long
    //   63	92	3	l2	long
    //   89	5	5	localObject	Object
    //   96	1	5	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   10	47	89	finally
    //   48	64	89	finally
    //   74	79	89	finally
    //   79	88	89	finally
    //   91	93	89	finally
    //   98	106	89	finally
    //   109	124	89	finally
    //   124	150	89	finally
    //   150	154	89	finally
    //   74	79	96	java/lang/InterruptedException
  }
  
  public InetAddress[] getAllByName(String paramString)
    throws UnknownHostException
  {
    try
    {
      if (mNetwork == null)
      {
        paramString = EMPTY_ADDRESS_ARRAY;
        return paramString;
      }
      Network localNetwork = mNetwork;
      return localNetwork.getAllByName(paramString);
    }
    finally {}
  }
  
  public String getApnName()
  {
    try
    {
      if (mNetwork == null)
      {
        Log.d("MmsService", "MmsNetworkManager: getApnName: network not available");
        return null;
      }
      Object localObject2 = mNetwork;
      String str = null;
      localObject2 = getConnectivityManager().getNetworkInfo((Network)localObject2);
      if (localObject2 != null) {
        str = ((NetworkInfo)localObject2).getExtraInfo();
      }
      Log.d("MmsService", "MmsNetworkManager: getApnName: " + str);
      return str;
    }
    finally {}
  }
  
  public MmsHttpClient getOrCreateHttpClient()
  {
    try
    {
      if ((mMmsHttpClient == null) && (mNetwork != null)) {
        mMmsHttpClient = new MmsHttpClient(mContext, mNetwork.getSocketFactory(), this, getOrCreateConnectionPoolLocked());
      }
      MmsHttpClient localMmsHttpClient = mMmsHttpClient;
      return localMmsHttpClient;
    }
    finally {}
  }
  
  public boolean isNetworkReleased()
  {
    return mIsNetworkReleased;
  }
  
  public void releaseNetwork()
  {
    try
    {
      if (mMmsRequestCount > 0)
      {
        mMmsRequestCount -= 1;
        Log.d("MmsService", "MmsNetworkManager: release, count=" + mMmsRequestCount + ", mPendingMmsRequestCount = " + mPendingMmsRequestCount);
        if ((mMmsRequestCount < 1) && (mPendingMmsRequestCount < 1)) {
          releaseRequestLocked(mNetworkCallback);
        }
      }
      return;
    }
    finally {}
  }
  
  public void releaseNetworkImmediately()
  {
    try
    {
      Log.d("MmsService", "MmsNetworkManager: release network immdiately");
      releaseRequestLocked(mNetworkCallback);
      return;
    }
    finally {}
  }
  
  public void updatePendingMmsRequestCount(int paramInt)
  {
    try
    {
      Log.d("MmsService", "MmsNetworkManager: update pending count: " + paramInt);
      mPendingMmsRequestCount = paramInt;
      return;
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsNetworkManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */