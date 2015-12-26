import com.ted.android.data.bubbleAction.DateReminderAction;

class aru$a
{
  String a;
  String b;
  String c;
  String d;
  String e;
  String f;
  String g;
  String h;
  
  public DateReminderAction a(long paramLong)
  {
    boolean bool = true;
    if (aru.b(a)) {
      return null;
    }
    if ((aru.b(c)) && (aru.b(d))) {
      return null;
    }
    int j = aqn.b(paramLong);
    Object localObject = aqn.b(a, paramLong);
    int m = a;
    int i = j;
    if (b) {
      i = j + 1;
    }
    int k;
    if (!aru.b(c))
    {
      localObject = aqn.a(c, paramLong, m);
      k = a;
      j = m;
      if (!b) {
        break label415;
      }
      m += 1;
      j = m;
      if (m <= 12) {
        break label415;
      }
      i += 1;
      j = 1;
    }
    label409:
    label415:
    for (;;)
    {
      long l = aqn.a(i, j, k);
      if (l == -1L)
      {
        return null;
        k = aqn.a(d, i, m, paramLong);
        j = m;
      }
      else
      {
        k = j;
        j = i;
        if (!aru.b(e))
        {
          localObject = aqn.b(e, paramLong);
          m = a;
          k = m;
          j = i;
          if (b)
          {
            j = i + 1;
            k = m;
          }
        }
        int n;
        if (!aru.b(g))
        {
          localObject = aqn.a(g, paramLong, k);
          int i1 = a;
          n = i1;
          i = k;
          m = j;
          if (b)
          {
            k += 1;
            n = i1;
            i = k;
            m = j;
            if (k > 12)
            {
              m = j + 1;
              i = 1;
              n = i1;
            }
          }
          paramLong = aqn.a(m, i, n);
          if (paramLong != -1L) {
            break label409;
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
          n = aqn.a(h, j, k, paramLong);
          i = k;
          m = j;
          break;
          bool = false;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     aru.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */