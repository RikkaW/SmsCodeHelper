import com.ted.android.data.bubbleAction.DateReminderAction;

class aqp$a
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
    boolean bool = true;
    if ((aqp.b(a)) || (aqp.b(b))) {
      return null;
    }
    if ((aqp.b(d)) && (aqp.b(e))) {
      return null;
    }
    int m = aqn.a(a, paramLong);
    Object localObject = aqn.b(b, paramLong);
    int i1 = a;
    int k = m;
    if (b) {
      k = m + 1;
    }
    int n;
    if (!aqp.b(d))
    {
      localObject = aqn.a(d, paramLong, i1);
      n = a;
      m = i1;
      if (!b) {
        break label454;
      }
      i1 += 1;
      m = i1;
      if (i1 <= 12) {
        break label454;
      }
      m = k + 1;
      k = 1;
    }
    for (;;)
    {
      long l = aqn.a(m, k, n);
      if (l == -1L)
      {
        return null;
        n = aqn.a(e, k, i1, paramLong);
        m = k;
        k = i1;
      }
      else
      {
        n = m;
        if (!aqp.b(f)) {
          n = aqn.a(f, paramLong);
        }
        m = k;
        k = n;
        if (!aqp.b(g))
        {
          localObject = aqn.b(g, paramLong);
          i1 = a;
          m = i1;
          k = n;
          if (b)
          {
            k = n + 1;
            m = i1;
          }
        }
        int i2;
        if (!aqp.b(i))
        {
          localObject = aqn.a(i, paramLong, m);
          int i3 = a;
          i2 = i3;
          n = m;
          i1 = k;
          if (b)
          {
            m += 1;
            i2 = i3;
            n = m;
            i1 = k;
            if (m > 12)
            {
              i1 = k + 1;
              n = 1;
              i2 = i3;
            }
          }
          paramLong = aqn.a(i1, n, i2);
          if (paramLong != -1L) {
            break label448;
          }
          paramLong = l;
        }
        for (;;)
        {
          localObject = new DateReminderAction(null);
          isAllDay = bool;
          startTime = l;
          endTime = (paramLong + 86400000L - 1000L);
          return (DateReminderAction)localObject;
          i2 = aqn.a(j, k, m, paramLong);
          n = m;
          i1 = k;
          break;
          label448:
          bool = false;
        }
        label454:
        i1 = k;
        k = m;
        m = i1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     aqp.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */