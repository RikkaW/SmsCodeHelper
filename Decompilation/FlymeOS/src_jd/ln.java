import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.meizu.android.mms.MzContentType;
import com.meizu.android.mms.pdu.MzPduBody;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.MzPduPersister;

public class ln
{
  private static MzPduPart a(MzPduBody paramMzPduBody, String paramString)
  {
    Object localObject1 = null;
    if (paramString != null)
    {
      paramString = a(paramString);
      if (!paramString.startsWith("cid:")) {
        break label180;
      }
      paramString = paramString.substring("cid:".length());
      localObject1 = paramString;
      if (paramString.length() > 0)
      {
        localObject1 = paramString;
        if (paramString.charAt(0) != '<') {
          localObject1 = "<" + paramString + ">";
        }
      }
      localObject2 = paramMzPduBody.getPartByContentId((String)localObject1);
      paramString = (String)localObject2;
      if (localObject2 != null) {
        return paramString;
      }
      paramString = (String)localObject2;
      if (((String)localObject1).lastIndexOf(".") <= 0) {
        return paramString;
      }
      paramString = ((String)localObject1).substring("<".length(), ((String)localObject1).length() - 1);
      localObject1 = paramMzPduBody.getPartByContentId(paramString);
      if (localObject1 == null) {
        break label127;
      }
    }
    label127:
    do
    {
      return (MzPduPart)localObject1;
      localObject2 = paramString.substring(0, paramString.lastIndexOf("."));
      paramString = paramMzPduBody.getPartByContentId((String)localObject2);
      localObject1 = paramString;
    } while (paramString != null);
    return paramMzPduBody.getPartByContentId("<" + (String)localObject2 + ">");
    label180:
    Object localObject2 = paramString;
    if (paramString.length() > 0)
    {
      localObject2 = paramString;
      if (paramString.charAt(0) == '<') {
        localObject2 = paramString.substring("<".length(), paramString.length() - 1);
      }
    }
    paramString = paramMzPduBody.getPartByName((String)localObject2);
    localObject1 = paramString;
    if (paramString == null)
    {
      paramString = paramMzPduBody.getPartByFileName((String)localObject2);
      localObject1 = paramString;
      if (paramString == null) {
        localObject1 = paramMzPduBody.getPartByContentLocation((String)localObject2);
      }
    }
    paramString = (String)localObject1;
    if (localObject1 == null)
    {
      paramString = (String)localObject1;
      if (((String)localObject2).lastIndexOf(".") > 0)
      {
        ((String)localObject2).substring(0, ((String)localObject2).lastIndexOf("."));
        localObject1 = paramMzPduBody.getPartByName((String)localObject2);
        paramString = (String)localObject1;
        if (localObject1 == null)
        {
          localObject1 = paramMzPduBody.getPartByFileName((String)localObject2);
          paramString = (String)localObject1;
          if (localObject1 == null) {
            return paramMzPduBody.getPartByContentLocation((String)localObject2);
          }
        }
      }
    }
    return paramString;
  }
  
  private static MzPduPart a(MzPduBody paramMzPduBody, String paramString, boolean paramBoolean)
  {
    Object localObject1 = null;
    Object localObject2 = paramString;
    if (paramString != null)
    {
      if (!paramBoolean) {
        break label111;
      }
      paramString = new String(MzPduPersister.getBytes(paramString));
      if (!paramString.startsWith("cid:")) {
        break label119;
      }
      localObject2 = paramString.substring("cid:".length());
      localObject1 = localObject2;
      if (((String)localObject2).length() > 0)
      {
        localObject1 = localObject2;
        if (((String)localObject2).charAt(0) != '<') {
          localObject1 = "<" + (String)localObject2 + ">";
        }
      }
      localObject1 = paramMzPduBody.getPartByContentId((String)localObject1);
      localObject2 = paramString;
    }
    for (;;)
    {
      label105:
      if (localObject1 != null) {}
      label111:
      label119:
      do
      {
        return (MzPduPart)localObject1;
        paramString = a(paramString);
        break;
        localObject2 = paramMzPduBody.getPartByName(paramString);
        localObject1 = localObject2;
        if (localObject2 != null) {
          break label226;
        }
        localObject2 = paramMzPduBody.getPartByFileName(paramString);
        localObject1 = localObject2;
        if (localObject2 != null) {
          break label226;
        }
        localObject2 = paramMzPduBody.getPartByContentLocation(paramString);
        localObject1 = localObject2;
        if (localObject2 != null) {
          break label226;
        }
        localObject1 = b(paramMzPduBody, paramString);
        localObject2 = paramString;
        break label105;
        localObject1 = a(paramMzPduBody, (String)localObject2);
        paramString = (String)localObject1;
        if (localObject1 == null)
        {
          paramString = (String)localObject1;
          if (!paramBoolean)
          {
            paramString = (String)localObject1;
            if (localObject2 != null) {
              paramString = a(paramMzPduBody, (String)localObject2, true);
            }
          }
        }
        localObject1 = paramString;
      } while (paramString != null);
      throw new IllegalArgumentException("No part found for the model.");
      label226:
      localObject2 = paramString;
    }
  }
  
  private static String a(MzPduPart paramMzPduPart, String paramString)
  {
    if ((paramMzPduPart.getName() != null) || (paramMzPduPart.getContentLocation() != null))
    {
      paramMzPduPart = a(paramMzPduPart.getName(), paramMzPduPart.getContentLocation());
      int i = paramMzPduPart.lastIndexOf(".");
      if ((i >= 0) && (i < paramMzPduPart.length()))
      {
        paramString = paramMzPduPart.substring(i + 1, paramMzPduPart.length());
        String str = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString);
        Log.v("Mms:media", "fixMediaType(): name = " + paramMzPduPart + ", extension = " + paramString + ", mimeType = " + str);
        return str;
      }
    }
    return "text/x-vcard";
  }
  
  private static String a(String paramString)
  {
    return paramString.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&amp;", "&");
  }
  
  private static String a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    String str = "";
    if (paramArrayOfByte1 != null) {
      str = new String(paramArrayOfByte1).toLowerCase();
    }
    paramArrayOfByte1 = str;
    if (str.indexOf(".") < 0)
    {
      paramArrayOfByte1 = str;
      if (paramArrayOfByte2 != null) {
        paramArrayOfByte1 = new String(paramArrayOfByte2).toLowerCase();
      }
    }
    return paramArrayOfByte1;
  }
  
  public static lm a(Context paramContext, auf paramauf, ll paramll, MzPduBody paramMzPduBody)
  {
    String str1 = paramauf.getTagName();
    String str2 = paramauf.k();
    paramMzPduBody = a(paramMzPduBody, str2, false);
    if ((paramauf instanceof auk)) {
      return a(paramContext, str1, str2, (auk)paramauf, paramll, paramMzPduBody);
    }
    return a(paramContext, str1, str2, paramauf, paramMzPduBody, null);
  }
  
  private static lm a(Context paramContext, String paramString1, String paramString2, auf paramauf, MzPduPart paramMzPduPart, lp paramlp)
  {
    if (paramMzPduPart != null) {}
    for (Object localObject = paramMzPduPart.getContentType(); localObject == null; localObject = null) {
      throw new IllegalArgumentException("Content-Type of the part may not be null.");
    }
    String str = new String((byte[])localObject);
    if (paramString1.equals("text"))
    {
      paramContext = new lu(paramContext, str, paramString2, paramMzPduPart.getCharset(), paramMzPduPart.getData(), paramlp);
      paramString2 = paramauf.g();
      if ((paramString2 == null) || (paramString2.a() <= 0)) {
        break label736;
      }
    }
    label736:
    for (int i = (int)(paramString2.a(0).c() * 1000.0D);; i = 0)
    {
      paramContext.a(i);
      int j = (int)(paramauf.b() * 1000.0F);
      if (j <= 0)
      {
        paramString2 = paramauf.h();
        if ((paramString2 != null) && (paramString2.a() > 0))
        {
          paramString2 = paramString2.a(0);
          if (paramString2.d() != 0)
          {
            j = (int)(paramString2.c() * 1000.0D) - i;
            i = j;
            if (j == 0) {
              if (!(paramContext instanceof le))
              {
                i = j;
                if (!(paramContext instanceof lw)) {}
              }
              else
              {
                j = ga.s();
                i = j;
                if (Log.isLoggable("Mms:app", 2)) {
                  Log.d("Mms:media", "[MediaModelFactory] compute new duration for " + paramString1 + ", duration=" + j);
                }
              }
            }
          }
        }
      }
      for (i = j;; i = j)
      {
        paramContext.b(i);
        if (!ga.u())
        {
          paramContext.a((short)1);
          return paramContext;
          if (paramString1.equals("img"))
          {
            paramString2 = new lk(paramContext, str, paramString2, paramMzPduPart.getDataUri(), paramlp);
            paramContext = paramString2;
            if (TextUtils.isEmpty(paramauf.a())) {
              break;
            }
            paramString2.b(paramauf.a());
            paramContext = paramString2;
            break;
          }
          if (paramString1.equals("video"))
          {
            paramContext = new lw(paramContext, str, paramString2, paramMzPduPart.getDataUri(), paramlp);
            break;
          }
          if (paramString1.equals("audio"))
          {
            paramContext = new le(paramContext, str, paramString2, paramMzPduPart.getDataUri());
            break;
          }
          if (paramString1.equals("talk"))
          {
            paramContext = new lt(paramContext, str, paramString2, paramMzPduPart.getDataUri());
            break;
          }
          if (paramString1.equals("file"))
          {
            paramContext = new li(paramContext, str, paramString2, paramMzPduPart.getDataUri());
            break;
          }
          if (paramString1.equals("ref"))
          {
            localObject = str;
            if ("application/oct-stream".equals(str)) {
              localObject = a(paramMzPduPart, "text/x-vcard");
            }
            if (MzContentType.isTextType((String)localObject))
            {
              if (MzContentType.isVcardType((String)localObject))
              {
                paramContext = new lv(paramContext, (String)localObject, paramString2, paramMzPduPart.getDataUri());
                break;
              }
              paramContext = new lu(paramContext, (String)localObject, paramString2, paramMzPduPart.getCharset(), paramMzPduPart.getData(), paramlp);
              break;
            }
            if (MzContentType.isImageType((String)localObject))
            {
              paramContext = new lk(paramContext, (String)localObject, paramString2, paramMzPduPart.getDataUri(), paramlp);
              break;
            }
            if (MzContentType.isVideoType((String)localObject))
            {
              paramContext = new lw(paramContext, (String)localObject, paramString2, paramMzPduPart.getDataUri(), paramlp);
              break;
            }
            if (MzContentType.isAudioType((String)localObject))
            {
              paramContext = new le(paramContext, (String)localObject, paramString2, paramMzPduPart.getDataUri());
              break;
            }
            throw new gd("Unsupported Content-Type: " + (String)localObject);
          }
          throw new IllegalArgumentException("Unsupported TAG: " + paramString1);
        }
        paramContext.a(paramauf.i());
        return paramContext;
      }
    }
  }
  
  private static lm a(Context paramContext, String paramString1, String paramString2, auk paramauk, ll paramll, MzPduPart paramMzPduPart)
  {
    Object localObject = paramauk.l();
    if (localObject != null)
    {
      paramll = paramll.a(((aui)localObject).j());
      if (paramll != null) {
        return a(paramContext, paramString1, paramString2, paramauk, paramMzPduPart, paramll);
      }
    }
    else
    {
      if (paramString1.equals("text")) {}
      for (localObject = "Text";; localObject = "Image")
      {
        paramll = paramll.a((String)localObject);
        if (paramll == null) {
          break;
        }
        return a(paramContext, paramString1, paramString2, paramauk, paramMzPduPart, paramll);
      }
    }
    throw new IllegalArgumentException("Region not found or bad region ID.");
  }
  
  private static MzPduPart b(MzPduBody paramMzPduBody, String paramString)
  {
    int i = paramString.lastIndexOf(".");
    if (i < 1) {
      paramString = null;
    }
    String str;
    MzPduPart localMzPduPart;
    do
    {
      do
      {
        return paramString;
        str = paramString.substring(0, i);
        localMzPduPart = paramMzPduBody.getPartByName(str);
        paramString = localMzPduPart;
      } while (localMzPduPart != null);
      localMzPduPart = paramMzPduBody.getPartByFileName(str);
      paramString = localMzPduPart;
    } while (localMzPduPart != null);
    return paramMzPduBody.getPartByContentLocation(str);
  }
}

/* Location:
 * Qualified Name:     ln
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */