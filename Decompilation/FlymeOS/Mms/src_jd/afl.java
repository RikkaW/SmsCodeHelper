import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class afl
{
  private static final Map<Integer, String> a = new HashMap();
  private static final Set<String> b;
  private static final Map<String, Integer> c = new HashMap();
  private static final Map<Integer, String> d;
  private static final Set<String> e = new HashSet(Arrays.asList(new String[] { "MOBILE", "携帯電話", "携帯", "ケイタイ", "ｹｲﾀｲ" }));
  private static final Set<Character> f = new HashSet(Arrays.asList(new Character[] { Character.valueOf('['), Character.valueOf(']'), Character.valueOf('='), Character.valueOf(':'), Character.valueOf('.'), Character.valueOf(','), Character.valueOf(' ') }));
  private static final int[] g = { 58, 59, 44, 32 };
  private static final int[] h = { 59, 58 };
  
  static
  {
    a.put(Integer.valueOf(9), "CAR");
    c.put("CAR", Integer.valueOf(9));
    a.put(Integer.valueOf(6), "PAGER");
    c.put("PAGER", Integer.valueOf(6));
    a.put(Integer.valueOf(11), "ISDN");
    c.put("ISDN", Integer.valueOf(11));
    c.put("HOME", Integer.valueOf(1));
    c.put("WORK", Integer.valueOf(3));
    c.put("CELL", Integer.valueOf(2));
    c.put("OTHER", Integer.valueOf(7));
    c.put("CALLBACK", Integer.valueOf(8));
    c.put("COMPANY-MAIN", Integer.valueOf(10));
    c.put("RADIO", Integer.valueOf(14));
    c.put("TTY-TDD", Integer.valueOf(16));
    c.put("ASSISTANT", Integer.valueOf(19));
    c.put("VOICE", Integer.valueOf(7));
    b = new HashSet();
    b.add("MODEM");
    b.add("MSG");
    b.add("BBS");
    b.add("VIDEO");
    d = new HashMap();
    d.put(Integer.valueOf(0), "X-AIM");
    d.put(Integer.valueOf(1), "X-MSN");
    d.put(Integer.valueOf(2), "X-YAHOO");
    d.put(Integer.valueOf(3), "X-SKYPE-USERNAME");
    d.put(Integer.valueOf(5), "X-GOOGLE-TALK");
    d.put(Integer.valueOf(6), "X-ICQ");
    d.put(Integer.valueOf(7), "X-JABBER");
    d.put(Integer.valueOf(4), "X-QQ");
    d.put(Integer.valueOf(8), "X-NETMEETING");
  }
  
  public static int a(int paramInt)
  {
    if (aex.e(paramInt)) {
      return 2;
    }
    return 1;
  }
  
  public static Object a(Collection<String> paramCollection, String paramString)
  {
    int m = 0;
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    int j = -1;
    paramString = null;
    int k;
    int i;
    int n;
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      k = 0;
      i = 0;
      paramCollection = paramString;
      while (localIterator.hasNext())
      {
        paramString = (String)localIterator.next();
        if (paramString != null)
        {
          Object localObject = paramString.toUpperCase();
          if (((String)localObject).equals("PREF"))
          {
            m = 1;
            k = j;
            j = i;
            i = m;
          }
          for (;;)
          {
            m = k;
            k = i;
            i = j;
            j = m;
            break;
            if (((String)localObject).equals("FAX"))
            {
              i = k;
              m = 1;
              k = j;
              j = m;
            }
            else
            {
              if ((((String)localObject).startsWith("X-")) && (j < 0)) {
                paramString = paramString.substring(2);
              }
              for (;;)
              {
                if (paramString.length() == 0) {
                  break label258;
                }
                localObject = (Integer)c.get(paramString.toUpperCase());
                if (localObject == null) {
                  break label260;
                }
                m = ((Integer)localObject).intValue();
                n = str.indexOf("@");
                if (((m != 6) || (n <= 0) || (n >= str.length() - 1)) && (j >= 0) && (j != 0) && (j != 7)) {
                  break label374;
                }
                j = ((Integer)localObject).intValue();
                label238:
                m = i;
                n = j;
                i = k;
                j = m;
                k = n;
                break;
              }
              label258:
              break;
              label260:
              if (j >= 0) {
                break label355;
              }
              m = 0;
              j = i;
              paramCollection = paramString;
              i = k;
              k = m;
            }
          }
        }
      }
      m = k;
      k = i;
    }
    for (;;)
    {
      i = j;
      if (j < 0)
      {
        if (m != 0) {
          i = 12;
        }
      }
      else
      {
        if (k == 0) {
          break label352;
        }
        if (i != 1) {
          break label325;
        }
        i = 5;
      }
      label325:
      label352:
      for (;;)
      {
        if (i == 0)
        {
          return paramCollection;
          i = 1;
          break;
          if (i == 3)
          {
            i = 4;
            continue;
          }
          if (i != 7) {
            break label352;
          }
          i = 13;
          continue;
        }
        return Integer.valueOf(i);
      }
      label355:
      m = i;
      n = j;
      i = k;
      j = m;
      k = n;
      break;
      label374:
      break label238;
      k = 0;
      paramCollection = null;
      j = -1;
    }
  }
  
  public static String a(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString1 = a(paramInt, paramString1, paramString2, paramString3);
    paramInt = 1;
    if (!TextUtils.isEmpty(paramString4))
    {
      localStringBuilder.append(paramString4);
      paramInt = 0;
    }
    int k = paramString1.length;
    int i = 0;
    if (i < k)
    {
      paramString2 = paramString1[i];
      int j = paramInt;
      if (!TextUtils.isEmpty(paramString2))
      {
        if (paramInt == 0) {
          break label94;
        }
        paramInt = 0;
      }
      for (;;)
      {
        localStringBuilder.append(paramString2);
        j = paramInt;
        i += 1;
        paramInt = j;
        break;
        label94:
        localStringBuilder.append(' ');
      }
    }
    if (!TextUtils.isEmpty(paramString5))
    {
      if (paramInt == 0) {
        localStringBuilder.append(' ');
      }
      localStringBuilder.append(paramString5);
    }
    return localStringBuilder.toString();
  }
  
  public static final String a(String paramString1, String paramString2, String paramString3)
  {
    if (paramString2.equalsIgnoreCase(paramString3)) {
      return paramString1;
    }
    paramString1 = Charset.forName(paramString2).encode(paramString1);
    paramString2 = new byte[paramString1.remaining()];
    paramString1.get(paramString2);
    try
    {
      paramString1 = new String(paramString2, paramString3);
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      Log.e("vCard", "Failed to encode: charset=" + paramString3);
    }
    return null;
  }
  
  public static String a(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    Object localObject1 = new StringBuilder();
    int j = paramString1.length();
    int i = 0;
    char c1;
    if (i < j)
    {
      c1 = paramString1.charAt(i);
      if ((c1 == '=') && (i < j - 1))
      {
        char c2 = paramString1.charAt(i + 1);
        if ((c2 == ' ') || (c2 == '\t'))
        {
          ((StringBuilder)localObject1).append(c2);
          i += 1;
        }
      }
      for (;;)
      {
        i += 1;
        break;
        ((StringBuilder)localObject1).append(c1);
      }
    }
    Object localObject2 = ((StringBuilder)localObject1).toString();
    if (paramBoolean) {}
    ArrayList localArrayList;
    for (paramString1 = ((String)localObject2).split("\r\n");; paramString1 = (String[])localArrayList.toArray(new String[0]))
    {
      localObject2 = new StringBuilder();
      j = paramString1.length;
      i = 0;
      while (i < j)
      {
        localArrayList = paramString1[i];
        localObject1 = localArrayList;
        if (localArrayList.endsWith("=")) {
          localObject1 = localArrayList.substring(0, localArrayList.length() - 1);
        }
        ((StringBuilder)localObject2).append((String)localObject1);
        i += 1;
      }
      paramString1 = new StringBuilder();
      int k = ((String)localObject2).length();
      localArrayList = new ArrayList();
      i = 0;
      if (i < k)
      {
        c1 = ((String)localObject2).charAt(i);
        if (c1 == '\n')
        {
          localArrayList.add(paramString1.toString());
          paramString1 = new StringBuilder();
          j = i;
        }
        for (;;)
        {
          i = j + 1;
          break;
          if (c1 == '\r')
          {
            localArrayList.add(paramString1.toString());
            localObject1 = new StringBuilder();
            j = i;
            paramString1 = (String)localObject1;
            if (i < k - 1)
            {
              j = i;
              paramString1 = (String)localObject1;
              if (((String)localObject2).charAt(i + 1) == '\n')
              {
                j = i + 1;
                paramString1 = (String)localObject1;
              }
            }
          }
          else
          {
            paramString1.append(c1);
            j = i;
          }
        }
      }
      paramString1 = paramString1.toString();
      if (paramString1.length() > 0) {
        localArrayList.add(paramString1);
      }
    }
    localObject1 = ((StringBuilder)localObject2).toString();
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      Log.w("vCard", "Given raw string is empty.");
    }
    try
    {
      paramString1 = ((String)localObject1).getBytes(paramString2);
    }
    catch (UnsupportedEncodingException paramString1)
    {
      for (;;)
      {
        try
        {
          paramString2 = afl.c.a(paramString1);
          paramString1 = paramString2;
        }
        catch (afl.a paramString2)
        {
          Log.e("vCard", "DecoderException is thrown.");
          continue;
        }
        try
        {
          paramString2 = new String(paramString1, paramString3);
          return paramString2;
        }
        catch (UnsupportedEncodingException paramString2)
        {
          Log.e("vCard", "Failed to encode: charset=" + paramString3);
        }
        paramString1 = paramString1;
        Log.w("vCard", "Failed to decode: " + paramString2);
        paramString1 = ((String)localObject1).getBytes();
      }
    }
    return new String(paramString1);
  }
  
  public static List<String> a(String paramString, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      char c1 = paramString.charAt(i);
      char c2;
      String str;
      if ((c1 == '\\') && (i < j - 1))
      {
        c2 = paramString.charAt(i + 1);
        if (aex.c(paramInt))
        {
          str = aff.c(c2);
          label78:
          if (str == null) {
            break label148;
          }
          localStringBuilder.append(str);
          i += 1;
        }
      }
      for (;;)
      {
        i += 1;
        break;
        if (aex.b(paramInt))
        {
          str = afe.b(c2);
          break label78;
        }
        if (!aex.a(paramInt)) {
          Log.w("vCard", "Unknown vCard type");
        }
        str = afd.a(c2);
        break label78;
        label148:
        localStringBuilder.append(c1);
        continue;
        if (c1 == ';')
        {
          localArrayList.add(localStringBuilder.toString());
          localStringBuilder = new StringBuilder();
        }
        else
        {
          localStringBuilder.append(c1);
        }
      }
    }
    localArrayList.add(localStringBuilder.toString());
    return localArrayList;
  }
  
  public static boolean a(String paramString)
  {
    int i = paramString.length() % 3;
    if ((paramString.length() < 2) || ((i != 1) && (i != 0))) {
      return false;
    }
    i = 0;
    for (;;)
    {
      if (i >= paramString.length()) {
        break label53;
      }
      if (paramString.charAt(i) != '=') {
        break;
      }
      i += 3;
    }
    label53:
    return true;
  }
  
  public static boolean a(Collection<String> paramCollection)
  {
    if (paramCollection == null) {
      return true;
    }
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      String str = (String)paramCollection.next();
      if ((!TextUtils.isEmpty(str)) && (!afl.d.a(str))) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean a(String... paramVarArgs)
  {
    if (paramVarArgs == null) {
      return true;
    }
    return a(Arrays.asList(paramVarArgs));
  }
  
  public static String[] a(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    String[] arrayOfString = new String[3];
    switch (aex.d(paramInt))
    {
    default: 
      arrayOfString[0] = paramString3;
      arrayOfString[1] = paramString2;
      arrayOfString[2] = paramString1;
      return arrayOfString;
    case 8: 
      if (a(new String[] { paramString1 })) {
        if (a(new String[] { paramString3 }))
        {
          arrayOfString[0] = paramString3;
          arrayOfString[1] = paramString2;
          arrayOfString[2] = paramString1;
          return arrayOfString;
        }
      }
      arrayOfString[0] = paramString1;
      arrayOfString[1] = paramString2;
      arrayOfString[2] = paramString3;
      return arrayOfString;
    }
    arrayOfString[0] = paramString2;
    arrayOfString[1] = paramString3;
    arrayOfString[2] = paramString1;
    return arrayOfString;
  }
  
  public static String b(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    return a(paramInt, paramString1, paramString2, paramString3, null, null);
  }
  
  static class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
  
  public static class b
  {
    public static String a(String paramString, int paramInt)
    {
      paramString = new SpannableStringBuilder(paramString);
      PhoneNumberUtils.formatNumber(paramString, paramInt);
      return paramString.toString();
    }
  }
  
  static class c
  {
    private static byte a = 61;
    
    public static final byte[] a(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte == null) {
        return null;
      }
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      int i = 0;
      if (i < paramArrayOfByte.length)
      {
        int j = paramArrayOfByte[i];
        if (j == a)
        {
          i += 1;
          int k;
          try
          {
            j = Character.digit((char)paramArrayOfByte[i], 16);
            i += 1;
            k = Character.digit((char)paramArrayOfByte[i], 16);
            if ((j == -1) || (k == -1)) {
              throw new afl.a("Invalid quoted-printable encoding");
            }
          }
          catch (ArrayIndexOutOfBoundsException paramArrayOfByte)
          {
            throw new afl.a("Invalid quoted-printable encoding");
          }
          j = (char)((j << 4) + k);
          localByteArrayOutputStream.write(j);
        }
        for (;;)
        {
          i += 1;
          break;
          localByteArrayOutputStream.write(j);
        }
      }
      return localByteArrayOutputStream.toByteArray();
    }
  }
  
  public static class d
  {
    public static boolean a(char paramChar)
    {
      return ((' ' <= paramChar) && (paramChar <= '~')) || (paramChar == '\r') || (paramChar == '\n');
    }
    
    public static boolean a(CharSequence paramCharSequence)
    {
      int j = paramCharSequence.length();
      int i = 0;
      while (i < j)
      {
        if (!a(paramCharSequence.charAt(i))) {
          return false;
        }
        i += 1;
      }
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     afl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */