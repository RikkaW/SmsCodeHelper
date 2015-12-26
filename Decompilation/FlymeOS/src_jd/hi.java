import android.util.Log;

class hi
  implements Runnable
{
  hi(hb paramhb, gr paramgr, boolean paramBoolean, String paramString) {}
  
  public void run()
  {
    try
    {
      zt.c().a(true);
      if (a.h().isEmpty())
      {
        if (Log.isLoggable("Mms:app", 2)) {
          fl.a("asyncUpdateDraftSmsMessage no recipients, not saving", new Object[0]);
        }
        return;
      }
      hb.a(d, a, b);
      a.b(true);
      hb.a(d, a, c);
      return;
    }
    finally
    {
      zt.c().a(false);
    }
  }
}

/* Location:
 * Qualified Name:     hi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */