import com.ted.android.data.bubbleAction.DateReminderAction;

class arv$a
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
    if (arv.b(a)) {
      return null;
    }
    if ((arv.b(d)) && (arv.b(e))) {
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
    if (!arv.b(d))
    {
      localObject = aqn.a(d, paramLong, i1);
      n = a;
      m = i1;
      if (!b) {
        break label382;
      }
      i1 += 1;
      m = i1;
      if (i1 <= 12) {
        break label382;
      }
      i1 = k + 1;
      m = 1;
      k = n;
      n = i1;
    }
    for (;;)
    {
      paramLong = aqn.a(n, m, k);
      if (paramLong == -1L)
      {
        return null;
        int i2 = aqn.a(e, k, i1, paramLong);
        m = i1;
        n = k;
        k = i2;
      }
      else
      {
        label280:
        long l1;
        if (!arv.b(g))
        {
          i1 = aqn.a(g, f);
          k = 0;
          if (!arv.b(h)) {
            k = aqn.a(h);
          }
          if (!arv.b(i))
          {
            m = aqn.a(i, f);
            if (!arv.b(j))
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
          break label280;
          m = i1;
          break;
          l1 = paramLong;
        }
        label382:
        i1 = k;
        k = n;
        n = i1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     arv.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */