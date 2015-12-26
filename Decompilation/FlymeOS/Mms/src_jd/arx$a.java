import com.ted.android.data.bubbleAction.DateReminderAction;

class arx$a
{
  String a;
  String b;
  String c;
  String d;
  String e;
  String f;
  String g;
  String h;
  String i;
  String j;
  
  public DateReminderAction a(long paramLong)
  {
    if ((arx.b(b)) && (arx.b(c)) && (arx.b(d)) && (arx.b(e))) {
      return null;
    }
    int i2 = aqn.b(paramLong);
    int i3 = aqn.c(paramLong);
    if (!arx.b(d)) {
      paramLong = aqn.c(d, paramLong);
    }
    Object localObject;
    int i1;
    int n;
    int k;
    int m;
    while (paramLong == -1L)
    {
      return null;
      if (!arx.b(e))
      {
        paramLong = aqn.d(e, paramLong);
      }
      else
      {
        if (!arx.b(b))
        {
          localObject = aqn.a(b, paramLong, i3);
          i1 = a;
          n = i1;
          k = i3;
          m = i2;
          if (b)
          {
            i3 += 1;
            n = i1;
            k = i3;
            m = i2;
            if (i3 > 12)
            {
              m = i2 + 1;
              k = 1;
              n = i1;
            }
          }
        }
        for (;;)
        {
          paramLong = aqn.a(m, k, n);
          break;
          n = aqn.a(c, i2, i3, paramLong);
          k = i3;
          m = i2;
        }
      }
    }
    label310:
    long l1;
    if (!arx.b(g))
    {
      i1 = aqn.a(g, f);
      k = 0;
      if (!arx.b(h)) {
        k = aqn.a(h);
      }
      if (!arx.b(i))
      {
        m = aqn.a(i, f);
        if (!arx.b(j))
        {
          n = aqn.a(j);
          long l2 = i1;
          long l3 = k;
          l1 = m * 3600000L + paramLong + n * 60000L;
          paramLong = l2 * 3600000L + paramLong + l3 * 60000L;
        }
      }
    }
    for (boolean bool = false;; bool = true)
    {
      localObject = new DateReminderAction(null);
      isAllDay = bool;
      startTime = paramLong;
      endTime = l1;
      return (DateReminderAction)localObject;
      n = k;
      break label310;
      m = i1;
      break;
      l1 = paramLong;
    }
  }
}

/* Location:
 * Qualified Name:     arx.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */