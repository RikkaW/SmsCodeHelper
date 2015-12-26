import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

class aaq$b
  implements SensorEventListener
{
  private final SensorManager a;
  private final Sensor b;
  private final float c;
  private final aaq.a d;
  private aaq.c e;
  private boolean f;
  
  public aaq$b(SensorManager paramSensorManager, Sensor paramSensor, aaq.a parama)
  {
    a = paramSensorManager;
    b = paramSensor;
    c = paramSensor.getMaximumRange();
    d = parama;
    e = aaq.c.b;
    f = false;
  }
  
  private aaq.c a(float paramFloat)
  {
    float f1 = Math.min(c, 5.0F);
    if ((paramFloat >= 0.0F) && (paramFloat < f1)) {
      return aaq.c.a;
    }
    return aaq.c.b;
  }
  
  private void d()
  {
    a.unregisterListener(this);
    f = false;
  }
  
  /* Error */
  public void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	aaq$b:e	Laaq$c;
    //   6: getstatic 43	aaq$c:b	Laaq$c;
    //   9: if_acmpne +10 -> 19
    //   12: aload_0
    //   13: invokespecial 66	aaq$b:d	()V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: aload_0
    //   20: iconst_1
    //   21: putfield 47	aaq$b:f	Z
    //   24: goto -8 -> 16
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	b
    //   27	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	27	finally
    //   19	24	27	finally
  }
  
  public void b()
  {
    try
    {
      a.registerListener(this, b, 2);
      f = false;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void c()
  {
    try
    {
      d();
      aaq.c localc = e;
      e = aaq.c.b;
      if (localc != aaq.c.b) {
        d.b();
      }
      return;
    }
    finally {}
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    Log.d("VoicePlayer", "onSensorChanged()~~~~");
    if (values == null) {}
    while (values.length == 0) {
      return;
    }
    float f1 = values[0];
    Log.d("VoicePlayer", "onSensorChanged() --> value = " + f1);
    paramSensorEvent = a(f1);
    try
    {
      if (paramSensorEvent == e) {
        return;
      }
    }
    finally {}
    e = paramSensorEvent;
    if ((f) && (e == aaq.c.b)) {
      d();
    }
    switch (aaq.1.a[paramSensorEvent.ordinal()])
    {
    default: 
      return;
    case 1: 
      d.a();
      return;
    }
    d.b();
  }
}

/* Location:
 * Qualified Name:     aaq.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */