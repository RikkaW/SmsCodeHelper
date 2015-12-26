package com.xiaomi.mms.mx.audio.player;

import android.content.Context;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.media.MediaRecorder.OnInfoListener;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.File;

public class XMAudioRecorder
{
  private Context mContext;
  private int mCount;
  private MediaRecorder.OnErrorListener mErrorListener = new MediaRecorder.OnErrorListener()
  {
    public void onError(MediaRecorder paramAnonymousMediaRecorder, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      XMAudioRecorder.this.sendMsg(-2);
      Log.v("XMAudioRecorder", "unknown audio error failed, errorcode=" + paramAnonymousInt1);
      XMAudioRecorder.access$102(XMAudioRecorder.this, false);
    }
  };
  private Handler mHandler;
  private MediaRecorder.OnInfoListener mInfoListener = new MediaRecorder.OnInfoListener()
  {
    public void onInfo(MediaRecorder paramAnonymousMediaRecorder, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      XMAudioRecorder.this.sendMsg(-3);
      Log.v("XMAudioRecorder", "unknown audio failed, errorcode=" + paramAnonymousInt1);
      XMAudioRecorder.access$102(XMAudioRecorder.this, false);
    }
  };
  private boolean mIsRecording;
  private Object mLock = new Object();
  private String mPath;
  private MediaRecorder mRecorder;
  private Runnable mSamplingRunnable = new Runnable()
  {
    public void run()
    {
      if (mIsRecording)
      {
        Message localMessage = mHandler.obtainMessage();
        what = 2;
        obj = Integer.valueOf(mRecorder.getMaxAmplitude());
        localMessage.sendToTarget();
        XMAudioRecorder.access$404(XMAudioRecorder.this);
        mHandler.postDelayed(this, 100L);
      }
    }
  };
  
  public XMAudioRecorder(Handler paramHandler, Context paramContext)
  {
    mHandler = paramHandler;
    mContext = paramContext;
  }
  
  private void sendMsg(int paramInt)
  {
    if (paramInt < 0) {
      clear();
    }
    Message localMessage = mHandler.obtainMessage();
    what = paramInt;
    localMessage.sendToTarget();
  }
  
  public void clear()
  {
    if (mPath == null) {}
    File localFile;
    do
    {
      return;
      localFile = new File(mPath);
    } while (!localFile.exists());
    localFile.delete();
  }
  
  public String getAudioPath()
  {
    return mPath;
  }
  
  public int getDuration()
  {
    return mCount * 100;
  }
  
  public void init(String paramString)
  {
    mPath = paramString;
    mRecorder = new MediaRecorder();
    mRecorder.setAudioSource(1);
    mRecorder.setOutputFormat(3);
    mRecorder.setAudioEncoder(1);
    mRecorder.setOutputFile(mPath);
    mRecorder.setOnInfoListener(mInfoListener);
    mRecorder.setOnErrorListener(mErrorListener);
  }
  
  public boolean isRecording()
  {
    return mIsRecording;
  }
  
  public void start()
  {
    mIsRecording = true;
    new AsyncTask()
    {
      /* Error */
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   4: invokestatic 36	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$500	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;)Ljava/lang/Object;
        //   7: astore_1
        //   8: aload_1
        //   9: monitorenter
        //   10: aload_0
        //   11: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   14: invokestatic 40	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$100	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;)Z
        //   17: ifne +7 -> 24
        //   20: aload_1
        //   21: monitorexit
        //   22: aconst_null
        //   23: areturn
        //   24: iconst_1
        //   25: istore_2
        //   26: aload_0
        //   27: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   30: invokestatic 44	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$300	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;)Landroid/media/MediaRecorder;
        //   33: invokevirtual 49	android/media/MediaRecorder:prepare	()V
        //   36: iload_2
        //   37: ifeq +60 -> 97
        //   40: aload_0
        //   41: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   44: invokestatic 44	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$300	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;)Landroid/media/MediaRecorder;
        //   47: invokevirtual 50	android/media/MediaRecorder:start	()V
        //   50: aload_0
        //   51: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   54: iconst_1
        //   55: invokestatic 54	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$000	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;I)V
        //   58: aload_0
        //   59: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   62: iconst_1
        //   63: invokestatic 58	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$102	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;Z)Z
        //   66: pop
        //   67: aload_0
        //   68: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   71: iconst_0
        //   72: invokestatic 62	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$402	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;I)I
        //   75: pop
        //   76: aload_0
        //   77: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   80: invokestatic 66	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$200	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;)Landroid/os/Handler;
        //   83: aload_0
        //   84: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   87: invokestatic 70	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$700	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;)Ljava/lang/Runnable;
        //   90: ldc2_w 71
        //   93: invokevirtual 78	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
        //   96: pop
        //   97: aload_1
        //   98: monitorexit
        //   99: aconst_null
        //   100: areturn
        //   101: astore_3
        //   102: aload_1
        //   103: monitorexit
        //   104: aload_3
        //   105: athrow
        //   106: astore_3
        //   107: aload_3
        //   108: invokevirtual 81	java/lang/IllegalStateException:printStackTrace	()V
        //   111: aload_0
        //   112: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   115: iconst_m1
        //   116: invokestatic 54	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$000	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;I)V
        //   119: iconst_0
        //   120: istore_2
        //   121: goto -85 -> 36
        //   124: astore_3
        //   125: aload_3
        //   126: instanceof 83
        //   129: ifeq +23 -> 152
        //   132: invokestatic 89	com/xiaomi/common/library/utils/SDCardUtils:isSDCardFull	()Z
        //   135: ifeq +17 -> 152
        //   138: aload_0
        //   139: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   142: invokestatic 93	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$600	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;)Landroid/content/Context;
        //   145: ldc 95
        //   147: iconst_1
        //   148: invokestatic 101	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
        //   151: pop
        //   152: aload_3
        //   153: invokevirtual 102	java/io/IOException:printStackTrace	()V
        //   156: aload_0
        //   157: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   160: iconst_m1
        //   161: invokestatic 54	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$000	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;I)V
        //   164: iconst_0
        //   165: istore_2
        //   166: goto -130 -> 36
        //   169: astore_3
        //   170: aload_3
        //   171: invokevirtual 103	java/lang/Exception:printStackTrace	()V
        //   174: aload_0
        //   175: getfield 16	com/xiaomi/mms/mx/audio/player/XMAudioRecorder$4:this$0	Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;
        //   178: iconst_m1
        //   179: invokestatic 54	com/xiaomi/mms/mx/audio/player/XMAudioRecorder:access$000	(Lcom/xiaomi/mms/mx/audio/player/XMAudioRecorder;I)V
        //   182: iconst_0
        //   183: istore_2
        //   184: goto -148 -> 36
        //   187: astore_3
        //   188: ldc 105
        //   190: ldc 107
        //   192: aload_3
        //   193: invokestatic 113	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   196: pop
        //   197: goto -100 -> 97
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	200	0	this	4
        //   0	200	1	paramAnonymousVarArgs	Void[]
        //   25	159	2	i	int
        //   101	4	3	localObject	Object
        //   106	2	3	localIllegalStateException	IllegalStateException
        //   124	29	3	localIOException	java.io.IOException
        //   169	2	3	localException1	Exception
        //   187	6	3	localException2	Exception
        // Exception table:
        //   from	to	target	type
        //   10	22	101	finally
        //   26	36	101	finally
        //   40	97	101	finally
        //   97	99	101	finally
        //   102	104	101	finally
        //   107	119	101	finally
        //   125	152	101	finally
        //   152	164	101	finally
        //   170	182	101	finally
        //   188	197	101	finally
        //   26	36	106	java/lang/IllegalStateException
        //   26	36	124	java/io/IOException
        //   26	36	169	java/lang/Exception
        //   40	97	187	java/lang/Exception
      }
    }.execute(new Void[0]);
  }
  
  public void stop()
  {
    synchronized (mLock)
    {
      mIsRecording = false;
      if (mRecorder == null) {
        return;
      }
    }
    try
    {
      mRecorder.stop();
      sendMsg(3);
      mRecorder.release();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        clear();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.XMAudioRecorder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */