package android.support.v4.text;

import java.nio.CharBuffer;
import java.util.Locale;

public class TextDirectionHeuristicsCompat
{
  public static final TextDirectionHeuristicCompat ANYRTL_LTR = new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, false, null);
  public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
  public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
  public static final TextDirectionHeuristicCompat LOCALE = TextDirectionHeuristicLocale.INSTANCE;
  public static final TextDirectionHeuristicCompat LTR = new TextDirectionHeuristicInternal(null, false, null);
  public static final TextDirectionHeuristicCompat RTL = new TextDirectionHeuristicInternal(null, true, null);
  private static final int STATE_FALSE = 1;
  private static final int STATE_TRUE = 0;
  private static final int STATE_UNKNOWN = 2;
  
  static
  {
    FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false, null);
    FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, true, null);
  }
  
  private static int isRtlText(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 2;
    case 0: 
      return 1;
    }
    return 0;
  }
  
  private static int isRtlTextOrFormat(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 2;
    case 0: 
    case 14: 
    case 15: 
      return 1;
    }
    return 0;
  }
  
  static class AnyStrong
    implements TextDirectionHeuristicsCompat.TextDirectionAlgorithm
  {
    public static final AnyStrong INSTANCE_LTR = new AnyStrong(false);
    public static final AnyStrong INSTANCE_RTL = new AnyStrong(true);
    private final boolean mLookForRtl;
    
    private AnyStrong(boolean paramBoolean)
    {
      mLookForRtl = paramBoolean;
    }
    
    public int checkRtl(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      int k = 1;
      int j = 0;
      int m;
      for (int i = paramInt1;; i = m)
      {
        m = i;
        if (m >= paramInt1 + paramInt2) {
          break;
        }
        switch (TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(paramCharSequence.charAt(m))))
        {
        default: 
          i = j;
          m += 1;
          j = i;
        }
      }
      if (mLookForRtl) {
        i = 0;
      }
      do
      {
        do
        {
          return i;
          i = 1;
          break;
          i = k;
        } while (!mLookForRtl);
        i = 1;
        break;
        if (j == 0) {
          break label131;
        }
        i = k;
      } while (mLookForRtl);
      return 0;
      label131:
      return 2;
    }
  }
  
  static class FirstStrong
    implements TextDirectionHeuristicsCompat.TextDirectionAlgorithm
  {
    public static final FirstStrong INSTANCE = new FirstStrong();
    
    public int checkRtl(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      int j = 2;
      int i = paramInt1;
      while ((i < paramInt1 + paramInt2) && (j == 2))
      {
        j = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(paramCharSequence.charAt(i)));
        i += 1;
      }
      return j;
    }
  }
  
  static abstract interface TextDirectionAlgorithm
  {
    public abstract int checkRtl(CharSequence paramCharSequence, int paramInt1, int paramInt2);
  }
  
  static abstract class TextDirectionHeuristicImpl
    implements TextDirectionHeuristicCompat
  {
    private final TextDirectionHeuristicsCompat.TextDirectionAlgorithm mAlgorithm;
    
    public TextDirectionHeuristicImpl(TextDirectionHeuristicsCompat.TextDirectionAlgorithm paramTextDirectionAlgorithm)
    {
      mAlgorithm = paramTextDirectionAlgorithm;
    }
    
    private boolean doCheck(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      switch (mAlgorithm.checkRtl(paramCharSequence, paramInt1, paramInt2))
      {
      default: 
        return defaultIsRtl();
      case 0: 
        return true;
      }
      return false;
    }
    
    protected abstract boolean defaultIsRtl();
    
    public boolean isRtl(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      if ((paramCharSequence == null) || (paramInt1 < 0) || (paramInt2 < 0) || (paramCharSequence.length() - paramInt2 < paramInt1)) {
        throw new IllegalArgumentException();
      }
      if (mAlgorithm == null) {
        return defaultIsRtl();
      }
      return doCheck(paramCharSequence, paramInt1, paramInt2);
    }
    
    public boolean isRtl(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      return isRtl(CharBuffer.wrap(paramArrayOfChar), paramInt1, paramInt2);
    }
  }
  
  static class TextDirectionHeuristicInternal
    extends TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
  {
    private final boolean mDefaultIsRtl;
    
    private TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.TextDirectionAlgorithm paramTextDirectionAlgorithm, boolean paramBoolean)
    {
      super();
      mDefaultIsRtl = paramBoolean;
    }
    
    protected boolean defaultIsRtl()
    {
      return mDefaultIsRtl;
    }
  }
  
  static class TextDirectionHeuristicLocale
    extends TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl
  {
    public static final TextDirectionHeuristicLocale INSTANCE = new TextDirectionHeuristicLocale();
    
    public TextDirectionHeuristicLocale()
    {
      super();
    }
    
    protected boolean defaultIsRtl()
    {
      return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.text.TextDirectionHeuristicsCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */