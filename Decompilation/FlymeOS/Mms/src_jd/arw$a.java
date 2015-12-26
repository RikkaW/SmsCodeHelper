import com.ted.android.data.bubbleAction.DateReminderAction;

class arw$a
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
  
  public DateReminderAction a(long paramLong)
  {
    if (arw.b(a)) {
      return null;
    }
    if ((arw.b(c)) && (arw.b(d))) {
      return null;
    }
    int k = aqn.b(paramLong);
    Object localObject = aqn.b(a, paramLong);
    int n = a;
    int j = k;
    if (b) {
      j = k + 1;
    }
    int m;
    if (!arw.b(c))
    {
      localObject = aqn.a(c, paramLong, n);
      m = a;
      k = n;
      if (!b) {
        break label378;
      }
      n += 1;
      k = n;
      if (n <= 12) {
        break label378;
      }
      n = j + 1;
      k = 1;
      j = m;
      m = n;
    }
    for (;;)
    {
      paramLong = aqn.a(m, k, j);
      if (paramLong == -1L)
      {
        return null;
        int i1 = aqn.a(d, j, n, paramLong);
        k = n;
        m = j;
        j = i1;
      }
      else
      {
        label276:
        long l1;
        if (!arw.b(f))
        {
          n = aqn.a(f, e);
          j = 0;
          if (!arw.b(g)) {
            j = aqn.a(g);
          }
          if (!arw.b(h))
          {
            k = aqn.a(h, e);
            if (!arw.b(i))
            {
              m = aqn.a(i);
              long l2 = n;
              long l3 = j;
              l1 = k * 3600000L + paramLong + m * 60000L;
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
          m = j;
          break label276;
          k = n;
          break;
          l1 = paramLong;
        }
        label378:
        n = j;
        j = m;
        m = n;
      }
    }
  }
}

/* Location:
 * Qualified Name:     arw.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */