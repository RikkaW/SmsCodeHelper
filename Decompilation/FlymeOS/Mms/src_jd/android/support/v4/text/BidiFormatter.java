package android.support.v4.text;

import java.util.Locale;

public final class BidiFormatter
{
  private static final int DEFAULT_FLAGS = 2;
  private static final BidiFormatter DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
  private static final BidiFormatter DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
  private static TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
  private static final int DIR_LTR = -1;
  private static final int DIR_RTL = 1;
  private static final int DIR_UNKNOWN = 0;
  private static final String EMPTY_STRING = "";
  private static final int FLAG_STEREO_RESET = 2;
  private static final char LRE = '‪';
  private static final char LRM = '‎';
  private static final String LRM_STRING = Character.toString('‎');
  private static final char PDF = '‬';
  private static final char RLE = '‫';
  private static final char RLM = '‏';
  private static final String RLM_STRING = Character.toString('‏');
  private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
  private final int mFlags;
  private final boolean mIsRtlContext;
  
  private BidiFormatter(boolean paramBoolean, int paramInt, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    mIsRtlContext = paramBoolean;
    mFlags = paramInt;
    mDefaultTextDirectionHeuristicCompat = paramTextDirectionHeuristicCompat;
  }
  
  private static int getEntryDir(String paramString)
  {
    return new DirectionalityEstimator(paramString, false).getEntryDir();
  }
  
  private static int getExitDir(String paramString)
  {
    return new DirectionalityEstimator(paramString, false).getExitDir();
  }
  
  public static BidiFormatter getInstance()
  {
    return new Builder().build();
  }
  
  public static BidiFormatter getInstance(Locale paramLocale)
  {
    return new Builder(paramLocale).build();
  }
  
  public static BidiFormatter getInstance(boolean paramBoolean)
  {
    return new Builder(paramBoolean).build();
  }
  
  private static boolean isRtlLocale(Locale paramLocale)
  {
    return TextUtilsCompat.getLayoutDirectionFromLocale(paramLocale) == 1;
  }
  
  private String markAfter(String paramString, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    boolean bool = paramTextDirectionHeuristicCompat.isRtl(paramString, 0, paramString.length());
    if ((!mIsRtlContext) && ((bool) || (getExitDir(paramString) == 1))) {
      return LRM_STRING;
    }
    if ((mIsRtlContext) && ((!bool) || (getExitDir(paramString) == -1))) {
      return RLM_STRING;
    }
    return "";
  }
  
  private String markBefore(String paramString, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    boolean bool = paramTextDirectionHeuristicCompat.isRtl(paramString, 0, paramString.length());
    if ((!mIsRtlContext) && ((bool) || (getEntryDir(paramString) == 1))) {
      return LRM_STRING;
    }
    if ((mIsRtlContext) && ((!bool) || (getEntryDir(paramString) == -1))) {
      return RLM_STRING;
    }
    return "";
  }
  
  public boolean getStereoReset()
  {
    return (mFlags & 0x2) != 0;
  }
  
  public boolean isRtl(String paramString)
  {
    return mDefaultTextDirectionHeuristicCompat.isRtl(paramString, 0, paramString.length());
  }
  
  public boolean isRtlContext()
  {
    return mIsRtlContext;
  }
  
  public String unicodeWrap(String paramString)
  {
    return unicodeWrap(paramString, mDefaultTextDirectionHeuristicCompat, true);
  }
  
  public String unicodeWrap(String paramString, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
  {
    return unicodeWrap(paramString, paramTextDirectionHeuristicCompat, true);
  }
  
  public String unicodeWrap(String paramString, TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat, boolean paramBoolean)
  {
    boolean bool = paramTextDirectionHeuristicCompat.isRtl(paramString, 0, paramString.length());
    StringBuilder localStringBuilder = new StringBuilder();
    char c;
    if ((getStereoReset()) && (paramBoolean))
    {
      if (bool)
      {
        paramTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.RTL;
        localStringBuilder.append(markBefore(paramString, paramTextDirectionHeuristicCompat));
      }
    }
    else
    {
      if (bool == mIsRtlContext) {
        break label144;
      }
      if (!bool) {
        break label136;
      }
      c = '‫';
      label74:
      localStringBuilder.append(c);
      localStringBuilder.append(paramString);
      localStringBuilder.append('‬');
      label98:
      if (paramBoolean) {
        if (!bool) {
          break label154;
        }
      }
    }
    label136:
    label144:
    label154:
    for (paramTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.RTL;; paramTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.LTR)
    {
      localStringBuilder.append(markAfter(paramString, paramTextDirectionHeuristicCompat));
      return localStringBuilder.toString();
      paramTextDirectionHeuristicCompat = TextDirectionHeuristicsCompat.LTR;
      break;
      c = '‪';
      break label74;
      localStringBuilder.append(paramString);
      break label98;
    }
  }
  
  public String unicodeWrap(String paramString, boolean paramBoolean)
  {
    return unicodeWrap(paramString, mDefaultTextDirectionHeuristicCompat, paramBoolean);
  }
  
  public static final class Builder
  {
    private int mFlags;
    private boolean mIsRtlContext;
    private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;
    
    public Builder()
    {
      initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
    }
    
    public Builder(Locale paramLocale)
    {
      initialize(BidiFormatter.isRtlLocale(paramLocale));
    }
    
    public Builder(boolean paramBoolean)
    {
      initialize(paramBoolean);
    }
    
    private static BidiFormatter getDefaultInstanceFromContext(boolean paramBoolean)
    {
      if (paramBoolean) {
        return BidiFormatter.DEFAULT_RTL_INSTANCE;
      }
      return BidiFormatter.DEFAULT_LTR_INSTANCE;
    }
    
    private void initialize(boolean paramBoolean)
    {
      mIsRtlContext = paramBoolean;
      mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
      mFlags = 2;
    }
    
    public BidiFormatter build()
    {
      if ((mFlags == 2) && (mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC)) {
        return getDefaultInstanceFromContext(mIsRtlContext);
      }
      return new BidiFormatter(mIsRtlContext, mFlags, mTextDirectionHeuristicCompat, null);
    }
    
    public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat paramTextDirectionHeuristicCompat)
    {
      mTextDirectionHeuristicCompat = paramTextDirectionHeuristicCompat;
      return this;
    }
    
    public Builder stereoReset(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        mFlags |= 0x2;
        return this;
      }
      mFlags &= 0xFFFFFFFD;
      return this;
    }
  }
  
  static class DirectionalityEstimator
  {
    private static final byte[] DIR_TYPE_CACHE = new byte['܀'];
    private static final int DIR_TYPE_CACHE_SIZE = 1792;
    private int charIndex;
    private final boolean isHtml;
    private char lastChar;
    private final int length;
    private final String text;
    
    static
    {
      int i = 0;
      while (i < 1792)
      {
        DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
        i += 1;
      }
    }
    
    DirectionalityEstimator(String paramString, boolean paramBoolean)
    {
      text = paramString;
      isHtml = paramBoolean;
      length = paramString.length();
    }
    
    private static byte getCachedDirectionality(char paramChar)
    {
      if (paramChar < '܀') {
        return DIR_TYPE_CACHE[paramChar];
      }
      return Character.getDirectionality(paramChar);
    }
    
    private byte skipEntityBackward()
    {
      int i = charIndex;
      do
      {
        if (charIndex <= 0) {
          break;
        }
        String str = text;
        int j = charIndex - 1;
        charIndex = j;
        lastChar = str.charAt(j);
        if (lastChar == '&') {
          return 12;
        }
      } while (lastChar != ';');
      charIndex = i;
      lastChar = ';';
      return 13;
    }
    
    private byte skipEntityForward()
    {
      char c;
      do
      {
        if (charIndex >= length) {
          break;
        }
        String str = text;
        int i = charIndex;
        charIndex = (i + 1);
        c = str.charAt(i);
        lastChar = c;
      } while (c != ';');
      return 12;
    }
    
    private byte skipTagBackward()
    {
      int j = charIndex;
      label144:
      for (;;)
      {
        String str;
        int k;
        if (charIndex > 0)
        {
          str = text;
          k = charIndex - 1;
          charIndex = k;
          lastChar = str.charAt(k);
          if (lastChar == '<') {
            return 12;
          }
          if (lastChar != '>') {}
        }
        else
        {
          charIndex = j;
          lastChar = '>';
          return 13;
        }
        if ((lastChar == '"') || (lastChar == '\''))
        {
          k = lastChar;
          for (;;)
          {
            if (charIndex <= 0) {
              break label144;
            }
            str = text;
            int m = charIndex - 1;
            charIndex = m;
            int i = str.charAt(m);
            lastChar = i;
            if (i == k) {
              break;
            }
          }
        }
      }
    }
    
    private byte skipTagForward()
    {
      int j = charIndex;
      label129:
      while (charIndex < length)
      {
        String str = text;
        int k = charIndex;
        charIndex = (k + 1);
        lastChar = str.charAt(k);
        if (lastChar == '>') {
          return 12;
        }
        if ((lastChar == '"') || (lastChar == '\''))
        {
          k = lastChar;
          for (;;)
          {
            if (charIndex >= length) {
              break label129;
            }
            str = text;
            int m = charIndex;
            charIndex = (m + 1);
            int i = str.charAt(m);
            lastChar = i;
            if (i == k) {
              break;
            }
          }
        }
      }
      charIndex = j;
      lastChar = '<';
      return 13;
    }
    
    byte dirTypeBackward()
    {
      lastChar = text.charAt(charIndex - 1);
      byte b1;
      if (Character.isLowSurrogate(lastChar))
      {
        int i = Character.codePointBefore(text, charIndex);
        charIndex -= Character.charCount(i);
        b1 = Character.getDirectionality(i);
      }
      do
      {
        byte b2;
        do
        {
          return b1;
          charIndex -= 1;
          b2 = getCachedDirectionality(lastChar);
          b1 = b2;
        } while (!isHtml);
        if (lastChar == '>') {
          return skipTagBackward();
        }
        b1 = b2;
      } while (lastChar != ';');
      return skipEntityBackward();
    }
    
    byte dirTypeForward()
    {
      lastChar = text.charAt(charIndex);
      byte b1;
      if (Character.isHighSurrogate(lastChar))
      {
        int i = Character.codePointAt(text, charIndex);
        charIndex += Character.charCount(i);
        b1 = Character.getDirectionality(i);
      }
      do
      {
        byte b2;
        do
        {
          return b1;
          charIndex += 1;
          b2 = getCachedDirectionality(lastChar);
          b1 = b2;
        } while (!isHtml);
        if (lastChar == '<') {
          return skipTagForward();
        }
        b1 = b2;
      } while (lastChar != '&');
      return skipEntityForward();
    }
    
    int getEntryDir()
    {
      charIndex = 0;
      int j = 0;
      int k = 0;
      int i = 0;
      while ((charIndex < length) && (j == 0)) {
        switch (dirTypeForward())
        {
        case 9: 
        case 3: 
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
        case 10: 
        case 11: 
        case 12: 
        case 13: 
        default: 
          j = i;
          break;
        case 14: 
        case 15: 
          i += 1;
          k = -1;
          break;
        case 16: 
        case 17: 
          i += 1;
          k = 1;
          break;
        case 18: 
          i -= 1;
          k = 0;
          break;
        case 0: 
          if (i == 0) {
            return -1;
          }
          j = i;
          break;
        case 1: 
        case 2: 
          if (i == 0) {
            return 1;
          }
          j = i;
        }
      }
      if (j == 0) {
        return 0;
      }
      if (k != 0) {
        return k;
      }
      for (;;)
      {
        if (charIndex <= 0) {
          break label268;
        }
        switch (dirTypeBackward())
        {
        default: 
          break;
        case 14: 
        case 15: 
          if (j == i) {
            break;
          }
          i -= 1;
          break;
        case 16: 
        case 17: 
          if (j == i) {
            return 1;
          }
          i -= 1;
          break;
        case 18: 
          i += 1;
        }
      }
      label268:
      return 0;
    }
    
    int getExitDir()
    {
      int m = 0;
      charIndex = length;
      int j = 0;
      int i = 0;
      for (;;)
      {
        int k = m;
        if (charIndex > 0) {}
        switch (dirTypeBackward())
        {
        case 9: 
        case 3: 
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
        case 10: 
        case 11: 
        case 12: 
        case 13: 
        default: 
          if (j == 0) {
            j = i;
          }
          break;
        case 0: 
          if (i == 0)
          {
            k = -1;
            return k;
          }
          if (j == 0) {
            j = i;
          }
          break;
        case 14: 
        case 15: 
          if (j == i) {
            return -1;
          }
          i -= 1;
          break;
        case 1: 
        case 2: 
          if (i == 0) {
            return 1;
          }
          if (j == 0) {
            j = i;
          }
          break;
        case 16: 
        case 17: 
          if (j == i) {
            return 1;
          }
          i -= 1;
          break;
        case 18: 
          i += 1;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.text.BidiFormatter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */