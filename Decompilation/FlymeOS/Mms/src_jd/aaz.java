import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aaz
{
  private static aaz a;
  private final Pattern b = Pattern.compile("@[\\u4e00-\\u9fa5\\w\\-]+");
  private final Pattern c = Pattern.compile("#([^\\#|.]+)#");
  private final Pattern d = Pattern.compile("http://t\\.cn/[a-zA-Z0-9\\-_]+");
  
  public static aaz a()
  {
    if (a == null) {
      a = new aaz();
    }
    return a;
  }
  
  public static void a(SpannableStringBuilder paramSpannableStringBuilder)
  {
    a().b(paramSpannableStringBuilder);
  }
  
  public static boolean a(URLSpan[] paramArrayOfURLSpan, String paramString, SpannableStringBuilder paramSpannableStringBuilder)
  {
    if (paramArrayOfURLSpan == null) {}
    for (;;)
    {
      return false;
      int i = 0;
      while (i < paramArrayOfURLSpan.length)
      {
        if (paramArrayOfURLSpan[i].getURL().startsWith(paramString))
        {
          paramSpannableStringBuilder.removeSpan(paramArrayOfURLSpan[i]);
          return true;
        }
        i += 1;
      }
    }
  }
  
  public void b(SpannableStringBuilder paramSpannableStringBuilder)
  {
    Matcher localMatcher = b.matcher(paramSpannableStringBuilder.toString());
    while (localMatcher.find())
    {
      localObject = localMatcher.group();
      paramSpannableStringBuilder.setSpan(new aaz.a(((String)localObject).substring(1, ((String)localObject).length())), localMatcher.start(), localMatcher.end(), 33);
    }
    localMatcher = c.matcher(paramSpannableStringBuilder.toString());
    while (localMatcher.find())
    {
      localObject = localMatcher.group();
      paramSpannableStringBuilder.setSpan(new aaz.b(((String)localObject).substring(1, ((String)localObject).length() - 1)), localMatcher.start(), localMatcher.end(), 33);
    }
    localMatcher = d.matcher(paramSpannableStringBuilder.toString());
    Object localObject = (URLSpan[])paramSpannableStringBuilder.getSpans(0, paramSpannableStringBuilder.length(), URLSpan.class);
    while (localMatcher.find())
    {
      String str = localMatcher.group();
      a((URLSpan[])localObject, str, paramSpannableStringBuilder);
      paramSpannableStringBuilder.setSpan(new URLSpan(str), localMatcher.start(), localMatcher.end(), 33);
    }
  }
  
  class a
    extends aaz.c
  {
    public a(String paramString)
    {
      super(paramString);
    }
    
    private String a(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return "@";
      }
      return "@" + paramString;
    }
    
    void a(View paramView, String paramString)
    {
      Intent localIntent = new Intent("com.meizu.weibo.action.TIMELINE");
      localIntent.putExtra("sns_name", a(paramString));
      paramView.getContext().startActivity(localIntent);
    }
  }
  
  class b
    extends aaz.c
  {
    public b(String paramString)
    {
      super(paramString);
    }
    
    void a(View paramView, String paramString)
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("http://huati.weibo.com/k/" + paramString));
      paramView.getContext().startActivity(paramString);
    }
  }
  
  abstract class c
    extends ClickableSpan
  {
    private String a;
    
    public c(String paramString)
    {
      a = paramString;
    }
    
    abstract void a(View paramView, String paramString);
    
    public void onClick(View paramView)
    {
      a(paramView, a);
    }
  }
}

/* Location:
 * Qualified Name:     aaz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */