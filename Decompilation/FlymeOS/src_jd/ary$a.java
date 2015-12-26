import com.ted.android.data.bubbleAction.DateReminderAction;

class ary$a
{
  String a;
  String b;
  String c;
  String d;
  String e;
  
  public DateReminderAction a(long paramLong)
  {
    if (ary.b(a)) {
      return null;
    }
    paramLong = aqn.a(aqn.b(paramLong), aqn.c(paramLong), aqn.d(paramLong));
    int m;
    int i;
    int j;
    int k;
    label113:
    long l1;
    boolean bool;
    if (!ary.b(b))
    {
      m = aqn.a(b, a);
      i = 0;
      if (!ary.b(c)) {
        i = aqn.a(c);
      }
      if (!ary.b(d))
      {
        j = aqn.a(d, a);
        if (!ary.b(e))
        {
          k = aqn.a(e);
          long l2 = m;
          long l3 = i;
          l1 = j * 3600000L + paramLong + k * 60000L;
          paramLong = l2 * 3600000L + paramLong + l3 * 60000L;
          bool = false;
        }
      }
    }
    for (;;)
    {
      DateReminderAction localDateReminderAction = new DateReminderAction(null);
      isAllDay = bool;
      startTime = paramLong;
      endTime = l1;
      return localDateReminderAction;
      k = i;
      break label113;
      j = m;
      break;
      bool = true;
      l1 = paramLong;
    }
  }
}

/* Location:
 * Qualified Name:     ary.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */