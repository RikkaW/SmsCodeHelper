package android.support.v4.text;

class BidiFormatter$DirectionalityEstimator
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
  
  BidiFormatter$DirectionalityEstimator(String paramString, boolean paramBoolean)
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

/* Location:
 * Qualified Name:     android.support.v4.text.BidiFormatter.DirectionalityEstimator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */