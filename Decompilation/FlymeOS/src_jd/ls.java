import android.drm.DrmManagerClient;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.MzContentType;
import com.meizu.android.mms.pdu.MzPduBody;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.smil.SmilSimpleParse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ls
{
  public static auc a(MzPduBody paramMzPduBody)
  {
    int k = 1;
    int j = 0;
    Object localObject = b(paramMzPduBody);
    auc localauc = null;
    int i = j;
    if (localObject != null)
    {
      localauc = a((MzPduPart)localObject);
      if (localauc == null) {
        break label203;
      }
      localObject = localauc.l().getChildNodes();
      if (1 != ((NodeList)localObject).getLength()) {
        break label203;
      }
      localObject = ((aug)((NodeList)localObject).item(0)).getChildNodes();
      int m = ((NodeList)localObject).getLength();
      if (m >= 3) {
        break label203;
      }
      j = 0;
      i = k;
      while (j < m)
      {
        String str = ((auf)((NodeList)localObject).item(j)).getTagName();
        if ((str == null) || ((!str.equals("img")) && (!str.equals("video")) && (!str.equals("audio")) && (!str.equals("ref")) && (!str.equals("file")))) {
          break label200;
        }
        i = 0;
        j += 1;
      }
    }
    for (;;)
    {
      if ((localauc == null) || (i != 0)) {
        localauc = c(paramMzPduBody);
      }
      return localauc;
      label200:
      break;
      label203:
      i = j;
    }
  }
  
  private static auc a(MzPduPart paramMzPduPart)
  {
    try
    {
      paramMzPduPart = paramMzPduPart.getData();
      if (paramMzPduPart != null)
      {
        paramMzPduPart = new ByteArrayInputStream(paramMzPduPart);
        paramMzPduPart = b(new ip().a(paramMzPduPart));
        return paramMzPduPart;
      }
    }
    catch (IOException paramMzPduPart)
    {
      Log.e("Mms/smil", "Failed to parse SMIL document.", paramMzPduPart);
      return null;
    }
    catch (SAXException paramMzPduPart)
    {
      for (;;)
      {
        Log.e("Mms/smil", "Failed to parse SMIL document.", paramMzPduPart);
      }
    }
    catch (MmsException paramMzPduPart)
    {
      for (;;)
      {
        Log.e("Mms/smil", "Failed to parse SMIL document.", paramMzPduPart);
      }
    }
  }
  
  public static auc a(lr paramlr)
  {
    return b(paramlr);
  }
  
  public static auf a(String paramString1, auc paramauc, String paramString2)
  {
    paramString1 = (auf)paramauc.createElement(paramString1);
    paramString1.c(a(paramString2));
    return paramString1;
  }
  
  public static aug a(auc paramauc)
  {
    aug localaug = (aug)paramauc.createElement("par");
    localaug.b(8.0F);
    paramauc.l().appendChild(localaug);
    return localaug;
  }
  
  private static aui a(ArrayList<aui> paramArrayList, String paramString)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      aui localaui = (aui)paramArrayList.next();
      if (localaui.j().equals(paramString)) {
        return localaui;
      }
    }
    return null;
  }
  
  public static String a(String paramString)
  {
    return paramString.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
  }
  
  static void a(atw paramatw, lm paramlm)
  {
    paramatw.a("SmilMediaStart", paramlm, false);
    paramatw.a("SmilMediaEnd", paramlm, false);
    paramatw.a("SmilMediaPause", paramlm, false);
    paramatw.a("SmilMediaSeek", paramlm, false);
  }
  
  static void a(atw paramatw, lq paramlq)
  {
    paramatw.a("SmilSlideStart", paramlq, false);
    paramatw.a("SmilSlideEnd", paramlq, false);
  }
  
  private static boolean a(auk paramauk, ArrayList<aui> paramArrayList, aue paramaue, String paramString, boolean paramBoolean)
  {
    paramArrayList = a(paramArrayList, paramString);
    if ((!paramBoolean) && (paramArrayList != null))
    {
      paramauk.a(paramArrayList);
      paramaue.appendChild(paramArrayList);
      return true;
    }
    return false;
  }
  
  private static auc b(auc paramauc)
  {
    return paramauc;
  }
  
  private static auc b(lr paramlr)
  {
    hy localhy = new hy();
    Object localObject2 = (aud)localhy.createElement("smil");
    localhy.appendChild((Node)localObject2);
    Object localObject1 = (aud)localhy.createElement("head");
    ((aud)localObject2).appendChild((Node)localObject1);
    aue localaue = (aue)localhy.createElement("layout");
    ((aud)localObject1).appendChild(localaue);
    localObject1 = (aul)localhy.createElement("root-layout");
    Object localObject3 = paramlr.f();
    ((aul)localObject1).d(((ll)localObject3).d());
    ((aul)localObject1).c(((ll)localObject3).e());
    Object localObject4 = ((ll)localObject3).f();
    if (!TextUtils.isEmpty((CharSequence)localObject4)) {
      ((aul)localObject1).c((String)localObject4);
    }
    localaue.appendChild((Node)localObject1);
    localObject3 = ((ll)localObject3).c();
    localObject1 = new ArrayList();
    localObject3 = ((ArrayList)localObject3).iterator();
    Object localObject5;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (lp)((Iterator)localObject3).next();
      localObject5 = (aui)localhy.createElement("region");
      ((aui)localObject5).a(((lp)localObject4).a());
      ((aui)localObject5).a(((lp)localObject4).c());
      ((aui)localObject5).b(((lp)localObject4).d());
      ((aui)localObject5).d(((lp)localObject4).e());
      ((aui)localObject5).c(((lp)localObject4).f());
      ((aui)localObject5).b(((lp)localObject4).b());
      ((ArrayList)localObject1).add(localObject5);
    }
    ((aud)localObject2).appendChild((aud)localhy.createElement("body"));
    localObject2 = paramlr.iterator();
    boolean bool2;
    boolean bool1;
    label392:
    boolean bool3;
    if (((Iterator)localObject2).hasNext())
    {
      paramlr = (lq)((Iterator)localObject2).next();
      localObject3 = a(localhy);
      ((aug)localObject3).b(paramlr.a() / 1000.0F);
      a((atw)localObject3, paramlr);
      localObject4 = paramlr.iterator();
      bool2 = false;
      bool1 = false;
      for (;;)
      {
        if (((Iterator)localObject4).hasNext())
        {
          localObject5 = (lm)((Iterator)localObject4).next();
          paramlr = ((lm)localObject5).n();
          if ((localObject5 instanceof lu))
          {
            if (TextUtils.isEmpty(((lu)localObject5).a())) {
              continue;
            }
            paramlr = a("text", localhy, paramlr);
            bool3 = a((auk)paramlr, (ArrayList)localObject1, localaue, "Text", bool1);
            bool1 = bool2;
            bool2 = bool3;
          }
        }
      }
    }
    for (;;)
    {
      int i = ((lm)localObject5).g();
      if (i != 0) {
        paramlr.setAttribute("begin", String.valueOf(i / 1000));
      }
      i = ((lm)localObject5).i();
      if (i != 0) {
        paramlr.b(i / 1000.0F);
      }
      ((aug)localObject3).appendChild(paramlr);
      a((atw)paramlr, (lm)localObject5);
      bool3 = bool2;
      bool2 = bool1;
      bool1 = bool3;
      break label392;
      if ((localObject5 instanceof lk))
      {
        paramlr = a("img", localhy, paramlr);
        bool2 = a((auk)paramlr, (ArrayList)localObject1, localaue, "Image", bool2);
        if (SmilSimpleParse.isLocationUri(((lm)localObject5).o()))
        {
          paramlr.b(a(((lm)localObject5).o()));
          bool3 = bool1;
          bool1 = bool2;
          bool2 = bool3;
        }
      }
      else
      {
        if ((localObject5 instanceof lw))
        {
          paramlr = a("video", localhy, paramlr);
          bool2 = a((auk)paramlr, (ArrayList)localObject1, localaue, "Image", bool2);
          bool3 = bool1;
          bool1 = bool2;
          bool2 = bool3;
          continue;
        }
        if ((localObject5 instanceof le))
        {
          paramlr = a("audio", localhy, paramlr);
          bool3 = bool1;
          bool1 = bool2;
          bool2 = bool3;
          continue;
        }
        if ((localObject5 instanceof lv))
        {
          paramlr = a("ref", localhy, paramlr);
          bool3 = bool1;
          bool1 = bool2;
          bool2 = bool3;
          continue;
        }
        if ((localObject5 instanceof lt))
        {
          Log.w("Mms/smil", "createSmilDocument()--->TALK media: " + ((lm)localObject5).n());
          break label392;
        }
        if ((localObject5 instanceof li))
        {
          Log.w("Mms/smil", "createSmilDocument()--->FILE media: " + ((lm)localObject5).n());
          break label392;
        }
        Log.w("Mms/smil", "Unsupport media: " + localObject5);
        break label392;
        break;
        return localhy;
      }
      bool3 = bool1;
      bool1 = bool2;
      bool2 = bool3;
    }
  }
  
  private static MzPduPart b(MzPduBody paramMzPduBody)
  {
    int i = paramMzPduBody.getPartsNum() - 1;
    while (i >= 0)
    {
      MzPduPart localMzPduPart = paramMzPduBody.getPart(i);
      if (Arrays.equals(localMzPduPart.getContentType(), "application/smil".getBytes())) {
        return localMzPduPart;
      }
      i -= 1;
    }
    return null;
  }
  
  private static auc c(MzPduBody paramMzPduBody)
  {
    int j = 0;
    hy localhy = new hy();
    Object localObject1 = (aud)localhy.createElement("smil");
    ((aud)localObject1).setAttribute("xmlns", "http://www.w3.org/2001/SMIL20/Language");
    localhy.appendChild((Node)localObject1);
    Object localObject2 = (aud)localhy.createElement("head");
    ((aud)localObject1).appendChild((Node)localObject2);
    ((aud)localObject2).appendChild((aue)localhy.createElement("layout"));
    ((aud)localObject1).appendChild((aud)localhy.createElement("body"));
    localObject1 = a(localhy);
    int k = paramMzPduBody.getPartsNum();
    if (k == 0) {
      return localhy;
    }
    DrmManagerClient localDrmManagerClient = MmsApp.c().i();
    int i = 0;
    if (j < k)
    {
      MzPduPart localMzPduPart = paramMzPduBody.getPart(j);
      String str = new String(localMzPduPart.getContentType());
      localObject2 = str;
      if (MzContentType.isDrmType(str)) {
        localObject2 = localDrmManagerClient.getOriginalMimeType(localMzPduPart.getDataUri());
      }
      if ((((String)localObject2).equals("text/plain")) || (((String)localObject2).equalsIgnoreCase("application/vnd.wap.xhtml+xml")) || (((String)localObject2).equals("text/html")))
      {
        localObject2 = a("text", localhy, localMzPduPart.generateLocation());
        if ((i & 0x1) == 0)
        {
          ((aug)localObject1).appendChild((Node)localObject2);
          i |= 0x1;
        }
      }
      for (;;)
      {
        j += 1;
        break;
        localObject1 = a(localhy);
        ((aug)localObject1).appendChild((Node)localObject2);
        i = 1;
        continue;
        if (MzContentType.isImageType((String)localObject2))
        {
          localObject2 = a("img", localhy, localMzPduPart.generateLocation());
          if ((i & 0x10) == 0)
          {
            ((aug)localObject1).appendChild((Node)localObject2);
            i |= 0x10;
          }
          else
          {
            localObject1 = a(localhy);
            ((aug)localObject1).appendChild((Node)localObject2);
            i = 16;
          }
        }
        else if (MzContentType.isVideoType((String)localObject2))
        {
          localObject2 = a("video", localhy, localMzPduPart.generateLocation());
          if ((i & 0x100) == 0)
          {
            ((aug)localObject1).appendChild((Node)localObject2);
            i |= 0x100;
          }
          else
          {
            localObject1 = a(localhy);
            ((aug)localObject1).appendChild((Node)localObject2);
            i = 256;
          }
        }
        else if (MzContentType.isAudioType((String)localObject2))
        {
          localObject2 = a("audio", localhy, localMzPduPart.generateLocation());
          if ((i & 0x1000) == 0)
          {
            ((aug)localObject1).appendChild((Node)localObject2);
            i |= 0x1000;
          }
          else
          {
            localObject1 = a(localhy);
            ((aug)localObject1).appendChild((Node)localObject2);
            i = 4096;
          }
        }
        else if (MzContentType.isVcardType((String)localObject2))
        {
          localObject2 = a("ref", localhy, localMzPduPart.generateLocation());
          if ((i & 0x10000) == 0)
          {
            ((aug)localObject1).appendChild((Node)localObject2);
            i |= 0x10000;
          }
          else
          {
            localObject1 = a(localhy);
            ((aug)localObject1).appendChild((Node)localObject2);
            i = 65536;
          }
        }
        else
        {
          Log.w("Mms/smil", "unsupport media type");
        }
      }
    }
    return localhy;
  }
}

/* Location:
 * Qualified Name:     ls
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */