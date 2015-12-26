import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.ted.android.contacts.block.SpamMsgEngine;
import com.ted.android.contacts.netparser.NumManager;
import com.ted.android.contacts.updatesdk.IParsedDownload;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class apu
  extends auy
{
  private Context a;
  private boolean b;
  private IParsedDownload c;
  
  public apu(Context paramContext, boolean paramBoolean)
  {
    super(paramContext);
    a = paramContext;
    b = paramBoolean;
    super.a(new bp(paramContext.getApplicationContext()).a("update", "url"));
  }
  
  public void a(be parambe) {}
  
  public void a(IParsedDownload paramIParsedDownload)
  {
    c = paramIParsedDownload;
  }
  
  public void a(List<be> paramList)
  {
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      Object localObject;
      for (paramList = (be)localIterator.next();; paramList = new File(a.getFilesDir(), paramList).getAbsolutePath())
      {
        try
        {
          localObject = paramList.f();
          paramList = paramList.b();
          if (!paramList.startsWith("/")) {
            continue;
          }
          paramList = new File(paramList, (String)localObject).getAbsolutePath();
          if ((!"html.zip".equals(localObject)) && (!"num_segment.dat".equals(localObject))) {
            break label122;
          }
          anz.a(paramList, a.getFilesDir());
        }
        catch (Exception paramList)
        {
          paramList.printStackTrace();
        }
        break;
      }
      label122:
      if ("offline.dat".equals(localObject))
      {
        if ("mounted".equals(Environment.getExternalStorageState())) {}
        for (localObject = Environment.getExternalStorageDirectory();; localObject = a.getFilesDir())
        {
          anz.a(paramList, new File((File)localObject, "yellowpage/offline/"));
          if (c == null) {
            break;
          }
          c.onParsed();
          break;
        }
      }
      if ("vcard.dat".equals(localObject)) {
        app.a(a).a(true, true);
      } else if ("config.ini".equals(localObject)) {
        NumManager.getInstnace().b();
      } else if (((String)localObject).equals("sms.model")) {
        SpamMsgEngine.a();
      }
    }
  }
  
  public boolean a()
  {
    boolean bool = false;
    if (b) {
      bool = b;
    }
    long l;
    do
    {
      return bool;
      l = a.getSharedPreferences("key_update_seciton", 0).getLong("local_current_time", 0L);
    } while (System.currentTimeMillis() - l < 28800000L);
    return true;
  }
  
  public boolean b()
  {
    long l = (new Random().nextInt(60) * 60.0F * 1000.0F + (float)System.currentTimeMillis());
    SharedPreferences.Editor localEditor = a.getSharedPreferences("key_update_seciton", 0).edit();
    localEditor.putLong("local_current_time", l);
    localEditor.commit();
    return true;
  }
}

/* Location:
 * Qualified Name:     apu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */