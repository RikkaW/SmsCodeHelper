import android.content.Context;
import com.ted.android.contacts.common.util.FileUtil;
import java.io.File;
import java.util.List;

public abstract class auy
{
  private static final boolean a = aux.a;
  private Context b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private String m;
  
  public auy(Context paramContext)
  {
    b = paramContext.getApplicationContext();
  }
  
  public abstract void a(be parambe);
  
  public void a(String paramString)
  {
    l = paramString;
  }
  
  public abstract void a(List<be> paramList);
  
  public abstract boolean a();
  
  public abstract boolean b();
  
  public void c()
  {
    c = b.getFilesDir().getAbsolutePath();
    d = c.concat("/u1");
    f = d.concat("/files");
    e = d.concat("/temp");
    k = d.concat("/upload");
    FileUtil.ensurePathExists(d);
    FileUtil.ensurePathExists(f);
    FileUtil.ensurePathExists(e);
    FileUtil.ensurePathExists(k);
    g = c.concat("/u.md5");
    h = f.concat("/u.md5");
    i = c.concat("/u.json");
    j = f.concat("/u.json");
    m = f.concat("/w.json");
    af.a = new b(b);
    if ((l == null) || (l.length() == 0)) {
      throw new RuntimeException("更新的url不能为空！实现类必须设置下载url");
    }
  }
  
  public Context d()
  {
    return b;
  }
  
  public String e()
  {
    return f;
  }
  
  public String f()
  {
    return g;
  }
  
  protected void finalize()
  {
    super.finalize();
  }
  
  public String g()
  {
    return i;
  }
  
  public String h()
  {
    return j;
  }
  
  public String i()
  {
    return h;
  }
  
  public String j()
  {
    return l;
  }
  
  public String k()
  {
    return k;
  }
  
  public String l()
  {
    return m;
  }
}

/* Location:
 * Qualified Name:     auy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */