import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class vw
  implements InputFilter
{
  private int a;
  private vw.a b;
  private int c = 0;
  
  public vw(int paramInt1, vw.a parama, int paramInt2)
  {
    a = paramInt1;
    b = parama;
    c = paramInt2;
  }
  
  private int a(CharSequence paramCharSequence)
  {
    int i = 0;
    int j = 0;
    if (TextUtils.isEmpty(paramCharSequence)) {
      return j;
    }
    paramCharSequence = Pattern.compile("[一-龥]").matcher(paramCharSequence);
    for (;;)
    {
      j = i;
      if (!paramCharSequence.find()) {
        break;
      }
      i += 1;
    }
  }
  
  private int a(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (!TextUtils.isEmpty(paramCharSequence))
    {
      paramCharSequence = paramCharSequence.subSequence(paramInt1, paramInt2);
      if (!TextUtils.isEmpty(paramCharSequence)) {}
    }
    else
    {
      return 0;
    }
    return paramCharSequence.length() + a(paramCharSequence) * c;
  }
  
  private boolean a(char paramChar)
  {
    return (paramChar >= '一') && (paramChar <= 40869);
  }
  
  public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
  {
    int i = 0;
    int j = a - (paramSpanned.length() + a(paramSpanned) * c - (a(paramSpanned.subSequence(paramInt3, paramInt4)) * c + paramInt4 - paramInt3));
    if (j <= 0)
    {
      if ((!TextUtils.isEmpty(paramCharSequence)) && (b != null)) {
        b.a();
      }
      return "";
    }
    if (j >= a(paramCharSequence, paramInt1, paramInt2)) {
      return null;
    }
    paramInt4 = 0;
    paramInt3 = i;
    while (paramInt4 < paramInt2)
    {
      if (a(paramCharSequence.charAt(paramInt4))) {
        paramInt3 += c + 1;
      }
      while (paramInt3 == j)
      {
        return paramCharSequence.subSequence(paramInt1, paramInt4 + 1 + paramInt1);
        paramInt3 += 1;
      }
      if (paramInt3 > j) {
        return paramCharSequence.subSequence(paramInt1, paramInt4 + paramInt1);
      }
      paramInt4 += 1;
    }
    return paramCharSequence.subSequence(paramInt1, j + paramInt1);
  }
  
  public static abstract interface a
  {
    public abstract void a();
  }
}

/* Location:
 * Qualified Name:     vw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */