import android.os.AsyncTask;
import com.meizu.common.app.SlideNotice;

class aak
  extends AsyncTask<Void, Void, String>
{
  aak(aaj paramaaj) {}
  
  protected String a(Void... paramVarArgs)
  {
    paramVarArgs = null;
    if (a.a.equalsIgnoreCase("android.intent.action.INCALL_BLOCK_LIST")) {
      paramVarArgs = aai.a.a(a.b);
    }
    while (!a.a.equalsIgnoreCase("android.intent.action.MMS_BLOCK_LIST")) {
      return paramVarArgs;
    }
    return aai.a.b(a.b);
  }
  
  protected void a(String paramString)
  {
    if (paramString == null) {
      return;
    }
    paramString = SlideNotice.makeNotice(a.b, paramString, 1, 1);
    paramString.setActionBarToTop(true);
    paramString.setOnClickNoticeListener(new aal(this));
    paramString.show();
  }
}

/* Location:
 * Qualified Name:     aak
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */