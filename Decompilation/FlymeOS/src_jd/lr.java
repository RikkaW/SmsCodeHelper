import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.model.Model;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.MzContentType;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzPduBody;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.util.MzPduCacheEntry;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.w3c.dom.NodeList;

public class lr
  extends Model
  implements List<lq>, lj
{
  int a = 0;
  private final ll b;
  private final ArrayList<lq> c;
  private auc d;
  private MzPduBody e;
  private long f;
  private long g;
  private Context h;
  private long i = 1408L;
  
  private lr(Context paramContext)
  {
    b = new ll();
    c = new ArrayList();
    h = paramContext;
  }
  
  private lr(ll paramll, ArrayList<lq> paramArrayList, auc paramauc, MzPduBody paramMzPduBody, Context paramContext)
  {
    b = paramll;
    c = paramArrayList;
    h = paramContext;
    d = paramauc;
    e = paramMzPduBody;
    paramll = c.iterator();
    while (paramll.hasNext())
    {
      paramArrayList = (lq)paramll.next();
      b(paramArrayList.b());
      paramArrayList.a(this);
    }
  }
  
  public static final Uri a(String paramString)
  {
    if (paramString.startsWith("file://")) {
      return wd.m(paramString.substring("file://".length()));
    }
    if (paramString.startsWith("content:")) {
      return Uri.parse(paramString);
    }
    return wd.m(paramString);
  }
  
  private MzPduBody a(auc paramauc)
  {
    MzPduBody localMzPduBody = new MzPduBody();
    Iterator localIterator1 = c.iterator();
    if (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((lq)localIterator1.next()).iterator();
      label43:
      lm locallm;
      MzPduPart localMzPduPart;
      Object localObject2;
      while (localIterator2.hasNext())
      {
        locallm = (lm)localIterator2.next();
        if ((locallm.w()) || (locallm.v()))
        {
          Log.d("Mms/slideshow", "makePduBody()---> media.isFile() = " + locallm.w());
        }
        else
        {
          localMzPduPart = new MzPduPart();
          if (locallm.q())
          {
            localObject1 = (lu)locallm;
            if (!TextUtils.isEmpty(((lu)localObject1).a())) {
              localMzPduPart.setCharset(((lu)localObject1).d());
            }
          }
          else
          {
            localMzPduPart.setContentType(locallm.j().getBytes());
            localObject2 = locallm.n();
            boolean bool = ((String)localObject2).startsWith("cid:");
            localObject1 = localObject2;
            if (bool) {
              localObject1 = ((String)localObject2).substring("cid:".length());
            }
            localMzPduPart.setContentLocation(((String)localObject1).getBytes());
            if (!bool) {
              break label265;
            }
            localMzPduPart.setContentId(((String)localObject1).getBytes());
            if (!locallm.q()) {
              break label311;
            }
            localMzPduPart.setData(((lu)locallm).a().getBytes());
          }
        }
      }
      for (;;)
      {
        localMzPduBody.addPart(localMzPduPart);
        break label43;
        break;
        label265:
        int j = ((String)localObject1).lastIndexOf(".");
        if ((j == -1) || (j == 0)) {}
        for (localObject2 = localObject1;; localObject2 = ((String)localObject1).substring(0, j))
        {
          localMzPduPart.setContentId(((String)localObject2).getBytes());
          break;
        }
        label311:
        if ((locallm.r()) || (locallm.s()) || (locallm.t()) || (locallm.u()))
        {
          localMzPduPart.setDataUri(locallm.k());
          localMzPduPart.setFilename(((String)localObject1).getBytes());
        }
        else
        {
          Log.w("Mms/slideshow", "Unsupport media: " + locallm);
        }
      }
    }
    Object localObject1 = new ByteArrayOutputStream();
    iq.a(paramauc, (OutputStream)localObject1);
    paramauc = new MzPduPart();
    paramauc.setContentId("smil".getBytes());
    paramauc.setContentLocation("smil.xml".getBytes());
    paramauc.setContentType("application/smil".getBytes());
    paramauc.setData(((ByteArrayOutputStream)localObject1).toByteArray());
    localMzPduBody.addPart(0, paramauc);
    return localMzPduBody;
  }
  
  public static MzPduCacheEntry a(Context paramContext, Uri paramUri)
  {
    paramContext = MzPduPersister.getPduPersister(paramContext).load(paramUri, true);
    int j = paramContext.getPdu().getMessageType();
    if ((j == 128) || (j == 132)) {
      return paramContext;
    }
    throw new MmsException();
  }
  
  public static String a(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {}
    int j;
    do
    {
      return null;
      j = paramString1.lastIndexOf(";file#");
    } while (j <= 0);
    paramString1 = TextUtils.split(paramString1.substring(j + 1), "#")[1];
    paramString1.endsWith(";");
    return paramString1.substring(0, paramString1.length() - 1);
  }
  
  public static lr a(Context paramContext)
  {
    return new lr(paramContext);
  }
  
  public static lr a(Context paramContext, MzPduBody paramMzPduBody)
  {
    auc localauc = ls.a(paramMzPduBody);
    Object localObject3 = localauc.m();
    Object localObject1 = ((aue)localObject3).b();
    int k = ((aul)localObject1).f();
    int m = ((aul)localObject1).e();
    if (k != 0)
    {
      j = m;
      if (m != 0) {}
    }
    else
    {
      k = kd.a().b().a();
      j = kd.a().b().b();
      ((aul)localObject1).d(k);
      ((aul)localObject1).c(j);
    }
    localObject1 = new lp(null, 0, 0, k, j);
    Object localObject2 = new ArrayList();
    localObject3 = ((aue)localObject3).a();
    k = ((NodeList)localObject3).getLength();
    int j = 0;
    Object localObject4;
    while (j < k)
    {
      localObject4 = (aui)((NodeList)localObject3).item(j);
      ((ArrayList)localObject2).add(new lp(((aui)localObject4).j(), ((aui)localObject4).a(), ((aui)localObject4).b(), ((aui)localObject4).c(), ((aui)localObject4).f(), ((aui)localObject4).e(), ((aui)localObject4).d()));
      j += 1;
    }
    localObject1 = new ll((lp)localObject1, (ArrayList)localObject2);
    localObject2 = localauc.l().getChildNodes();
    int n = ((NodeList)localObject2).getLength();
    localObject3 = new ArrayList(n);
    long l1 = 0L;
    j = 0;
    Object localObject5;
    int i1;
    ArrayList localArrayList;
    if (j < n)
    {
      localObject4 = (aug)((NodeList)localObject2).item(j);
      localObject5 = ((aug)localObject4).getChildNodes();
      i1 = ((NodeList)localObject5).getLength();
      localArrayList = new ArrayList(i1);
      k = 0;
    }
    for (;;)
    {
      if (k < i1)
      {
        auf localauf = (auf)((NodeList)localObject5).item(k);
        for (;;)
        {
          try
          {
            locallm = ln.a(paramContext, localauf, (ll)localObject1, paramMzPduBody);
            if (!ga.u())
            {
              m = locallm.i();
              f1 = ((aug)localObject4).b();
              if (f1 == 0.0F)
              {
                m = ga.s() * 1000;
                locallm.b(m);
              }
              if (m / 1000 != f1)
              {
                String str = localauf.getTagName();
                if ((!MzContentType.isVideoType(g)) && (!str.equals("video")) && (!MzContentType.isAudioType(g)) && (!str.equals("audio"))) {
                  continue;
                }
                ((aug)localObject4).b(m / 1000.0F + 1.0F);
              }
            }
          }
          catch (IOException localIOException)
          {
            lm locallm;
            long l2;
            Log.e("Mms/slideshow", localIOException.getMessage(), localIOException);
            break label740;
            if ((int)f1 == 0) {
              continue;
            }
            m = (int)f1;
            locallm.b(m * 1000);
            continue;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            Log.e("Mms/slideshow", localIllegalArgumentException.getMessage(), localIllegalArgumentException);
            break label740;
            float f1 = m / 1000.0F;
            ((aug)localObject4).b(f1);
            continue;
          }
          catch (gd localgd)
          {
            Log.e("Mms/slideshow", localgd.getMessage(), localgd);
          }
          ls.a((atw)localauf, locallm);
          localArrayList.add(locallm);
          l2 = l1 + locallm.p();
          l1 = l2;
          break label740;
          if (m / 1000 >= f1) {
            continue;
          }
          locallm.b((int)f1 * 1000);
        }
      }
      localObject5 = new lq((int)(((aug)localObject4).b() * 1000.0F), localArrayList);
      ((lq)localObject5).a(((aug)localObject4).i());
      ((lq)localObject5).e(j);
      ls.a((atw)localObject4, (lq)localObject5);
      ((ArrayList)localObject3).add(localObject5);
      j += 1;
      break;
      paramContext = new lr((ll)localObject1, (ArrayList)localObject3, localauc, paramMzPduBody, paramContext);
      g = l1;
      paramContext.c(paramContext);
      return paramContext;
      label740:
      k += 1;
    }
  }
  
  public static lr a(Context paramContext, MzPduCacheEntry paramMzPduCacheEntry)
  {
    MzPduBody localMzPduBody = ((MzMultimediaMessagePdu)paramMzPduCacheEntry.getPdu()).getBody();
    String str1 = a(paramMzPduCacheEntry.getMmsDescription(), paramMzPduCacheEntry.getFileLink());
    if (TextUtils.isEmpty(str1)) {
      return a(paramContext, localMzPduBody);
    }
    auc localauc = ls.a(localMzPduBody);
    Object localObject2 = localauc.m();
    Object localObject1 = ((aue)localObject2).b();
    int k = ((aul)localObject1).f();
    int m = ((aul)localObject1).e();
    if (k != 0)
    {
      j = m;
      if (m != 0) {}
    }
    else
    {
      k = kd.a().b().a();
      j = kd.a().b().b();
      ((aul)localObject1).d(k);
      ((aul)localObject1).c(j);
    }
    localObject1 = new lp(null, 0, 0, k, j);
    ArrayList localArrayList1 = new ArrayList();
    localObject2 = ((aue)localObject2).a();
    k = ((NodeList)localObject2).getLength();
    int j = 0;
    Object localObject3;
    while (j < k)
    {
      localObject3 = (aui)((NodeList)localObject2).item(j);
      localArrayList1.add(new lp(((aui)localObject3).j(), ((aui)localObject3).a(), ((aui)localObject3).b(), ((aui)localObject3).c(), ((aui)localObject3).f(), ((aui)localObject3).e(), ((aui)localObject3).d()));
      j += 1;
    }
    localObject1 = new ll((lp)localObject1, localArrayList1);
    localObject2 = localauc.l().getChildNodes();
    int n = ((NodeList)localObject2).getLength();
    localArrayList1 = new ArrayList(n);
    long l1 = 0L;
    j = 0;
    Object localObject4;
    int i1;
    ArrayList localArrayList2;
    if (j < n)
    {
      localObject3 = (aug)((NodeList)localObject2).item(j);
      localObject4 = ((aug)localObject3).getChildNodes();
      i1 = ((NodeList)localObject4).getLength();
      localArrayList2 = new ArrayList(i1);
      k = 0;
    }
    for (;;)
    {
      if (k < i1)
      {
        auf localauf = (auf)((NodeList)localObject4).item(k);
        for (;;)
        {
          float f1;
          try
          {
            locallm = ln.a(paramContext, localauf, (ll)localObject1, localMzPduBody);
            if (!ga.u())
            {
              m = locallm.i();
              f1 = ((aug)localObject3).b();
              if (f1 == 0.0F)
              {
                m = ga.s() * 1000;
                locallm.b(m);
              }
              if (m / 1000 != f1)
              {
                String str2 = localauf.getTagName();
                if ((!MzContentType.isVideoType(g)) && (!str2.equals("video")) && (!MzContentType.isAudioType(g)) && (!str2.equals("audio"))) {
                  continue;
                }
                ((aug)localObject3).b(m / 1000.0F + 1.0F);
              }
            }
          }
          catch (IOException localIOException)
          {
            lm locallm;
            Log.e("Mms/slideshow", localIOException.getMessage(), localIOException);
            break;
            if ((int)f1 == 0) {
              break label650;
            }
            m = (int)f1;
            locallm.b(m * 1000);
            continue;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            Log.e("Mms/slideshow", localIllegalArgumentException.getMessage(), localIllegalArgumentException);
          }
          ls.a((atw)localauf, locallm);
          localArrayList2.add(locallm);
          l2 = l1 + locallm.p();
          l1 = l2;
          break;
          if (m / 1000 < f1)
          {
            locallm.b((int)f1 * 1000);
          }
          else
          {
            label650:
            f1 = m / 1000.0F;
            ((aug)localObject3).b(f1);
          }
        }
      }
      localObject4 = new lq((int)(((aug)localObject3).b() * 1000.0F), localArrayList2);
      ((lq)localObject4).a(((aug)localObject3).i());
      ((lq)localObject4).e(j);
      ls.a((atw)localObject3, (lq)localObject4);
      localArrayList1.add(localObject4);
      j += 1;
      break;
      paramMzPduCacheEntry = paramMzPduCacheEntry.getFileLink();
      paramMzPduCacheEntry = new li(paramContext, str1, b(paramMzPduCacheEntry), a(paramMzPduCacheEntry));
      long l2 = paramMzPduCacheEntry.p();
      if (localArrayList1.size() == 0) {
        localArrayList1.add(new lq(1000, new ArrayList(1)));
      }
      for (;;)
      {
        paramContext = new lr((ll)localObject1, localArrayList1, localauc, localMzPduBody, paramContext);
        g = (l2 + l1);
        paramContext.d(1);
        paramContext.c(paramContext);
        return paramContext;
        ((lq)localArrayList1.get(0)).a(paramMzPduCacheEntry);
      }
      k += 1;
    }
  }
  
  public static final String b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    int j;
    do
    {
      return paramString;
      j = paramString.lastIndexOf("/");
    } while (j < 0);
    return paramString.substring(j + 1);
  }
  
  public static lr b(Context paramContext, Uri paramUri)
  {
    return a(paramContext, a(paramContext, paramUri));
  }
  
  public static long c(int paramInt)
  {
    if (MmsApp.a) {
      return ga.f();
    }
    if (paramInt == 2) {
      return ga.d();
    }
    if (paramInt == 1) {
      return ga.e();
    }
    return ga.f();
  }
  
  public MzPduBody a()
  {
    if (e == null)
    {
      d = ls.a(this);
      e = a(d);
    }
    return e;
  }
  
  public HashMap<Uri, InputStream> a(ContentResolver paramContentResolver)
  {
    Object localObject2 = null;
    Iterator localIterator1 = c.iterator();
    if (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((lq)localIterator1.next()).iterator();
      Object localObject1 = localObject2;
      do
      {
        localObject2 = localObject1;
        if (!localIterator2.hasNext()) {
          break;
        }
        localObject2 = (lm)localIterator2.next();
      } while (((lm)localObject2).q());
      Uri localUri = ((lm)localObject2).k();
      for (;;)
      {
        try
        {
          localInputStream = paramContentResolver.openInputStream(localUri);
          if (localInputStream == null) {
            continue;
          }
          if (localObject1 != null) {
            continue;
          }
          localObject2 = new HashMap();
          localObject1 = localObject2;
        }
        catch (FileNotFoundException localFileNotFoundException2)
        {
          InputStream localInputStream;
          continue;
          continue;
          continue;
        }
        try
        {
          ((HashMap)localObject1).put(localUri, localInputStream);
        }
        catch (FileNotFoundException localFileNotFoundException1) {}
        Log.e("Mms/slideshow", "openPartFiles couldn't open: " + localUri, localFileNotFoundException1);
      }
    }
    return localFileNotFoundException1;
  }
  
  public lq a(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < c.size())) {
      return (lq)c.get(paramInt);
    }
    return null;
  }
  
  public void a(int paramInt, lq paramlq)
  {
    if (paramlq != null)
    {
      long l = paramlq.b();
      d(l);
      c.add(paramInt, paramlq);
      b(l);
      paramlq.c(this);
      Iterator localIterator = n.iterator();
      while (localIterator.hasNext()) {
        paramlq.c((lj)localIterator.next());
      }
      a(true);
    }
  }
  
  public void a(long paramLong)
  {
    if (MmsApp.a)
    {
      i = paramLong;
      return;
    }
    f = paramLong;
  }
  
  public void a(Uri paramUri)
  {
    long l1 = 0L;
    long l2 = 0L;
    Object localObject = c.iterator();
    int k = 0;
    long l5 = 0L;
    Iterator localIterator;
    long l6;
    long l4;
    long l3;
    int j;
    if (((Iterator)localObject).hasNext())
    {
      localIterator = ((lq)((Iterator)localObject).next()).iterator();
      l6 = l1;
      l4 = l2;
      l3 = l5;
      j = k;
    }
    for (;;)
    {
      k = j;
      l5 = l3;
      l2 = l4;
      l1 = l6;
      if (!localIterator.hasNext()) {
        break;
      }
      lm locallm = (lm)localIterator.next();
      if (locallm.e())
      {
        j += 1;
        l6 += locallm.p();
        if (MmsApp.a)
        {
          if (locallm.q()) {}
          for (l1 = 172L;; l1 = 102L)
          {
            l4 = l1;
            break;
          }
        }
      }
      else
      {
        l3 += locallm.p();
        continue;
        l6 = c(a);
        if (Log.isLoggable("Mms:app", 2)) {
          Log.v("Mms/slideshow", "finalResize: original message size: " + d() + " getMaxMessageSize: " + l6 + " fixedSizeTotal: " + l5 + " resizeSizeTotal: " + l1);
        }
        if (k > 0)
        {
          if (MmsApp.a) {}
          for (j = 1408;; j = 1024)
          {
            l4 = l6 - l5 - j;
            if (l4 > 0L) {
              break;
            }
            throw new fk("No room for pictures");
          }
          l5 = ContentUris.parseId(paramUri);
          l3 = l4;
          if (MmsApp.a) {
            l3 = l4 - l2;
          }
          l2 = l3 / k;
          if ((a == 0) && (l1 > l3))
          {
            localObject = c.iterator();
            while (((Iterator)localObject).hasNext())
            {
              localIterator = ((lq)((Iterator)localObject).next()).iterator();
              while (localIterator.hasNext())
              {
                locallm = (lm)localIterator.next();
                if (locallm.e()) {
                  locallm.a(l2, l5);
                }
              }
            }
          }
          localObject = c.iterator();
          j = 0;
          if (((Iterator)localObject).hasNext())
          {
            localIterator = ((lq)((Iterator)localObject).next()).iterator();
            for (k = j;; k = (int)(locallm.p() + l1))
            {
              j = k;
              if (!localIterator.hasNext()) {
                break;
              }
              locallm = (lm)localIterator.next();
              l1 = k;
            }
          }
          if (Log.isLoggable("Mms:app", 2)) {
            Log.v("Mms/slideshow", "finalResize: new message size: " + j);
          }
          if (j > l6) {
            throw new fk("After compressing pictures, message too big");
          }
          a(j);
          onModelChanged(this, true);
          localObject = a();
          MzPduPersister.getPduPersister(h).updateParts(paramUri, (MzPduBody)localObject, null, hb.c(a));
        }
        return;
      }
    }
  }
  
  public void a(MzPduBody paramMzPduBody)
  {
    Iterator localIterator1 = c.iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((lq)localIterator1.next()).iterator();
      while (localIterator2.hasNext())
      {
        lm locallm = (lm)localIterator2.next();
        MzPduPart localMzPduPart = paramMzPduBody.getPartByContentLocation(locallm.n());
        if (localMzPduPart != null) {
          locallm.a(localMzPduPart.getDataUri());
        }
      }
    }
  }
  
  protected void a(lj paramlj)
  {
    b.c(paramlj);
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext()) {
      ((lq)localIterator.next()).c(paramlj);
    }
  }
  
  public boolean a(lq paramlq)
  {
    long l = paramlq.b();
    d(l);
    if ((paramlq != null) && (c.add(paramlq)))
    {
      b(l);
      paramlq.c(this);
      Iterator localIterator = n.iterator();
      while (localIterator.hasNext()) {
        paramlq.c((lj)localIterator.next());
      }
      a(true);
      return true;
    }
    return false;
  }
  
  public boolean addAll(int paramInt, Collection<? extends lq> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean addAll(Collection<? extends lq> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public MzPduBody b()
  {
    return a(ls.a(this));
  }
  
  public lq b(int paramInt)
  {
    lq locallq = (lq)c.remove(paramInt);
    if (locallq != null)
    {
      c(locallq.b());
      locallq.B();
      a(true);
    }
    return locallq;
  }
  
  public void b(int paramInt, lq paramlq)
  {
    if (paramlq != null)
    {
      long l = paramlq.b();
      c.add(paramInt, paramlq);
      b(l);
      paramlq.c(this);
      Iterator localIterator = n.iterator();
      while (localIterator.hasNext()) {
        paramlq.c((lj)localIterator.next());
      }
      a(true);
    }
  }
  
  public void b(long paramLong)
  {
    if (paramLong > 0L)
    {
      if (MmsApp.a) {
        i += paramLong;
      }
    }
    else {
      return;
    }
    f += paramLong;
  }
  
  protected void b(lj paramlj)
  {
    b.d(paramlj);
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext()) {
      ((lq)localIterator.next()).d(paramlj);
    }
  }
  
  public lq c(int paramInt, lq paramlq)
  {
    lq locallq = (lq)c.get(paramInt);
    long l2;
    if (paramlq != null)
    {
      l2 = paramlq.b();
      if (locallq == null) {
        break label145;
      }
    }
    label145:
    for (long l1 = locallq.b();; l1 = 0L)
    {
      if (l2 > l1)
      {
        d(l2 - l1);
        b(l2 - l1);
      }
      for (;;)
      {
        locallq = (lq)c.set(paramInt, paramlq);
        if (locallq != null) {
          locallq.B();
        }
        if (paramlq == null) {
          break;
        }
        paramlq.c(this);
        Iterator localIterator = n.iterator();
        while (localIterator.hasNext()) {
          paramlq.c((lj)localIterator.next());
        }
        c(l1 - l2);
      }
      a(true);
      return locallq;
    }
  }
  
  public void c()
  {
    if (MmsApp.a)
    {
      i = 1408L;
      return;
    }
    f = 0L;
  }
  
  public void c(long paramLong)
  {
    if (paramLong > 0L)
    {
      if (MmsApp.a) {
        i -= paramLong;
      }
    }
    else {
      return;
    }
    f -= paramLong;
  }
  
  public boolean c(String paramString)
  {
    if (size() != 1) {}
    lm locallm;
    do
    {
      return false;
      locallm = j();
    } while (!(locallm instanceof li));
    locallm.a(Uri.parse(paramString));
    return true;
  }
  
  public void clear()
  {
    if (c.size() > 0)
    {
      Iterator localIterator1 = c.iterator();
      while (localIterator1.hasNext())
      {
        lq locallq = (lq)localIterator1.next();
        locallq.d(this);
        Iterator localIterator2 = n.iterator();
        while (localIterator2.hasNext()) {
          locallq.d((lj)localIterator2.next());
        }
      }
      c();
      c.clear();
      a(true);
    }
  }
  
  public boolean contains(Object paramObject)
  {
    return c.contains(paramObject);
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    return c.containsAll(paramCollection);
  }
  
  public long d()
  {
    if (MmsApp.a) {
      return i;
    }
    return f;
  }
  
  public void d(int paramInt)
  {
    if (size() > 1) {
      a = 0;
    }
    while (a == paramInt) {
      return;
    }
    a = paramInt;
  }
  
  public void d(long paramLong)
  {
    lh.a().a(a, d(), paramLong, h.getContentResolver());
  }
  
  public long e()
  {
    if (MmsApp.a) {
      return i;
    }
    return g;
  }
  
  public ll f()
  {
    return b;
  }
  
  public boolean g()
  {
    if (size() != 1) {
      return false;
    }
    lq locallq = a(0);
    if (locallq.e()) {}
    for (int k = 1;; k = 0)
    {
      int j = k;
      if (locallq.g()) {
        j = k + 1;
      }
      k = j;
      if (locallq.i()) {
        k = j + 1;
      }
      j = k;
      if (locallq.j()) {
        j = k + 1;
      }
      if ((j != 1) || (locallq.f())) {
        break;
      }
      return true;
    }
  }
  
  protected void h()
  {
    b.B();
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext()) {
      ((lq)localIterator.next()).B();
    }
  }
  
  public void i()
  {
    if (size() == 1)
    {
      lu locallu = a(0).p();
      if (locallu != null) {
        locallu.b();
      }
    }
  }
  
  public int indexOf(Object paramObject)
  {
    return c.indexOf(paramObject);
  }
  
  public boolean isEmpty()
  {
    return c.isEmpty();
  }
  
  public Iterator<lq> iterator()
  {
    return c.iterator();
  }
  
  public lm j()
  {
    if (size() == 0) {}
    lq locallq;
    do
    {
      return null;
      locallq = a(0);
      if (locallq.e()) {
        return locallq.q();
      }
      if (locallq.g()) {
        return locallq.s();
      }
      if (locallq.f()) {
        return locallq.r();
      }
      if (locallq.i()) {
        return locallq.t();
      }
    } while (!locallq.j());
    return locallq.u();
  }
  
  public Uri k()
  {
    if (size() == 0) {}
    lq locallq;
    do
    {
      return null;
      locallq = a(0);
      if (locallq.e()) {
        return locallq.q().k();
      }
      if (locallq.g()) {
        return locallq.s().k();
      }
      if (locallq.f()) {
        return locallq.r().k();
      }
      if (locallq.i()) {
        return locallq.t().k();
      }
    } while (!locallq.j());
    return locallq.u().k();
  }
  
  public String l()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (size() == 0) {
      return "";
    }
    lq locallq;
    if (size() >= 1)
    {
      locallq = a(0);
      if (locallq.size() == 0) {
        return "";
      }
      if ((size() != 1) || (!a(0).w())) {
        break label132;
      }
      localStringBuffer.append("2;");
      if (!locallq.d()) {
        break label150;
      }
      localStringBuffer.append("text#").append(locallq.p().j()).append(";");
      label102:
      if (!locallq.k()) {
        break label161;
      }
      localStringBuffer.append("location#").append(locallq.q().j());
    }
    for (;;)
    {
      return localStringBuffer.toString();
      label132:
      localStringBuffer.append(size()).append(";");
      break;
      label150:
      localStringBuffer.append(";");
      break label102;
      label161:
      if (locallq.e()) {
        localStringBuffer.append("img#").append(locallq.q().j());
      } else if (locallq.g()) {
        localStringBuffer.append("video#").append(locallq.s().j());
      } else if (locallq.f()) {
        localStringBuffer.append("audio#").append(locallq.r().j());
      } else if (locallq.i()) {
        localStringBuffer.append("ref#").append(locallq.t().j());
      } else if (locallq.j()) {
        localStringBuffer.append("file#").append(locallq.u().j());
      } else {
        localStringBuffer.append(";");
      }
    }
  }
  
  public int lastIndexOf(Object paramObject)
  {
    return c.lastIndexOf(paramObject);
  }
  
  public ListIterator<lq> listIterator()
  {
    return c.listIterator();
  }
  
  public ListIterator<lq> listIterator(int paramInt)
  {
    return c.listIterator(paramInt);
  }
  
  public String m()
  {
    if (size() == 0) {
      return "";
    }
    if (size() >= 1)
    {
      lq locallq = a(0);
      if (locallq.size() == 0) {
        return "";
      }
      if (locallq.e()) {
        return locallq.q().n();
      }
      if (locallq.g()) {
        return locallq.s().n();
      }
      if (locallq.f()) {
        return locallq.r().n();
      }
      if (locallq.i()) {
        return locallq.t().n();
      }
      if (locallq.j()) {
        return locallq.u().n();
      }
    }
    return "";
  }
  
  public int n()
  {
    if (a != 0) {}
    long l;
    do
    {
      do
      {
        do
        {
          return 0;
        } while (size() != 1);
        localObject = a(0);
        if (((lq)localObject).j()) {
          return 1;
        }
      } while ((((lq)localObject).e()) && (((lq)localObject).q().e()));
      l = c(a);
      Object localObject = j();
      if ((localObject != null) && (((lm)localObject).e()))
      {
        d(j().p());
        return 0;
      }
    } while ((f >= 0L) && (f <= l));
    return 2;
  }
  
  public boolean o()
  {
    if (size() != 1) {}
    lq locallq;
    do
    {
      return true;
      locallq = a(0);
      if (locallq.j()) {
        return false;
      }
    } while (((locallq.e()) && (locallq.q().e())) || (d() <= ga.f()));
    return false;
  }
  
  public void onModelChanged(Model paramModel, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      d = null;
      e = null;
    }
  }
  
  public long p()
  {
    if (MmsApp.a) {
      return d();
    }
    if (size() != 1) {
      return d();
    }
    lm locallm = j();
    if ((locallm != null) && (locallm.e()))
    {
      long l = d();
      return locallm.p() + l;
    }
    return d();
  }
  
  public int q()
  {
    return a;
  }
  
  public boolean remove(Object paramObject)
  {
    if ((paramObject != null) && (c.remove(paramObject)))
    {
      paramObject = (lq)paramObject;
      c(((lq)paramObject).b());
      ((lq)paramObject).B();
      a(true);
      return true;
    }
    return false;
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException("Operation not supported.");
  }
  
  public int size()
  {
    if (c != null) {
      return c.size();
    }
    return 0;
  }
  
  public List<lq> subList(int paramInt1, int paramInt2)
  {
    return c.subList(paramInt1, paramInt2);
  }
  
  public Object[] toArray()
  {
    return c.toArray();
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return c.toArray(paramArrayOfT);
  }
}

/* Location:
 * Qualified Name:     lr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */