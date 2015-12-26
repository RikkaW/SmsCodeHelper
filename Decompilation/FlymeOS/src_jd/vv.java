import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.android.internal.telephony.IccUtils;
import com.android.mms.MmsApp;
import com.android.mms.MmsApp.h;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzRetrieveConf;
import com.meizu.android.mms.pdu.MzSendReq;
import java.util.HashMap;
import java.util.regex.Pattern;

public class vv
  implements nj
{
  public static int a = 9204;
  private static String af = "MessageItem";
  public static int b = -1;
  public String A;
  public lr B;
  public long C;
  public long D;
  public int E;
  public int F;
  public int G;
  public Cursor H;
  public vx.a I;
  public int J;
  public int K;
  public int L;
  public int M;
  public final long N;
  public final String O;
  public String P;
  public boolean Q;
  public String R;
  public int S;
  public int T;
  public String U;
  public long V;
  public String W;
  public long X = 0L;
  public String Y = "";
  public String Z = "";
  public int aa = 1;
  public long ab;
  boolean ac;
  HashMap ad = null;
  public boolean ae = false;
  private long ag;
  private vv.d ah;
  private zz ai;
  private long aj;
  private boolean ak;
  private vv.b al;
  private Uri am = null;
  private View an = null;
  public final Context c;
  public final String d;
  public final long e;
  public final long f;
  public final int g;
  public vv.a h;
  public boolean i;
  public boolean j;
  public String k;
  public String l;
  public String m;
  public String n;
  public String o;
  public String p;
  public Pattern q;
  public CharSequence r;
  public CharSequence s;
  public Uri t;
  public int u;
  public int v;
  public int w;
  public String x;
  public boolean y = true;
  public long z;
  
  public vv(Context paramContext, String paramString, Cursor paramCursor, vx.a parama, Pattern paramPattern)
  {
    this(paramContext, paramString, paramCursor, parama, paramPattern, false);
  }
  
  public vv(Context paramContext, String paramString, Cursor paramCursor, vx.a parama, Pattern paramPattern, boolean paramBoolean)
  {
    c = paramContext;
    e = paramCursor.getLong(b);
    f = paramCursor.getLong(c);
    N = paramCursor.getLong(z);
    O = paramCursor.getString(I);
    q = paramPattern;
    Q = paramBoolean;
    d = paramString;
    H = paramCursor;
    I = parama;
    L = paramCursor.getInt(B);
    S = paramCursor.getInt(J);
    T = paramCursor.getInt(K);
    U = paramCursor.getString(L);
    V = paramCursor.getLong(g);
    W = paramCursor.getString(d);
    if ("sms".equals(paramString))
    {
      i = false;
      aj = paramCursor.getLong(i);
      if (aj == -1L)
      {
        h = vv.a.a;
        M = paramCursor.getInt(A);
        t = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, e);
        g = paramCursor.getInt(h);
        m = paramCursor.getString(d);
        if (!((Boolean)aau.a("android.provider.Telephony$Sms", "isOutgoingFolder", Integer.TYPE, Integer.valueOf(g))).booleanValue()) {
          break label543;
        }
        n = paramContext.getString(2131493015);
        label377:
        if (M == a) {
          break label561;
        }
        o = paramCursor.getString(e);
        label402:
        ab = paramCursor.getLong(f);
        k = wd.a(paramContext, ab, 1, true);
        if (paramCursor.getInt(j) == 0) {
          break label619;
        }
      }
      label543:
      label561:
      label619:
      for (paramBoolean = true;; paramBoolean = false)
      {
        j = paramBoolean;
        F = paramCursor.getInt(k);
        ac = paramCursor.getString(y).equals("true");
        return;
        if (aj >= 64L)
        {
          h = vv.a.c;
          break;
        }
        if (aj >= 32L)
        {
          h = vv.a.d;
          break;
        }
        h = vv.a.e;
        break;
        n = gm.a(m, false).g();
        break label377;
        o = (c.getString(2131493489) + c.getString(2131493602));
        P = paramCursor.getString(e);
        break label402;
      }
    }
    if ("mms".equals(paramString))
    {
      Z = paramCursor.getString(E);
      t = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, e);
      g = paramCursor.getInt(p);
      u = paramCursor.getInt(o);
      E = paramCursor.getInt(s);
      K = paramCursor.getInt(w);
      paramString = paramCursor.getString(m);
      if (!TextUtils.isEmpty(paramString)) {
        A = wd.a(paramContext, new MzEncodedStringValue(paramCursor.getInt(n), MzPduPersister.getBytes(paramString)).getString());
      }
      if (paramCursor.getInt(t) != 0)
      {
        paramBoolean = true;
        label793:
        j = paramBoolean;
        J = paramCursor.getInt(x);
        B = null;
        v = b;
        h = vv.a.a;
        i = false;
        o = null;
        long l1 = paramCursor.getLong(F);
        z = l1;
        C = l1;
        D = paramCursor.getLong(H);
        p = null;
        G = paramCursor.getInt(u);
        if (u == 130) {
          break label1206;
        }
        paramBoolean = true;
        label916:
        R = null;
        ai = MmsApp.c().e().a(t, paramBoolean, new vv.e());
        if ((130 != u) || (L == 2))
        {
          b(paramCursor.getString(C));
          Y = paramCursor.getString(D);
          c(Y);
          o = paramCursor.getString(e);
          if ((!n()) || (TextUtils.isEmpty(Z)) || (Z.lastIndexOf("/") < 0)) {
            break label1212;
          }
        }
      }
      for (x = Z.substring(Z.lastIndexOf("/") + 1);; x = Z.substring(Z.lastIndexOf("/") + 1)) {
        label1206:
        label1212:
        do
        {
          if ((n()) || ((m()) && (y()))) {
            g();
          }
          ab = (paramCursor.getLong(f) * 1000L);
          k = wd.a(paramContext, ab, 1, true);
          if ((130 != u) || (m())) {
            break;
          }
          ag = (paramCursor.getLong(G) * 1000L);
          l = c.getString(2131492961, new Object[] { wd.a(c, ag, false) });
          return;
          paramBoolean = false;
          break label793;
          paramBoolean = false;
          break label916;
        } while ((!m()) || (TextUtils.isEmpty(Z)) || (Z.lastIndexOf("/") < 0) || (!TextUtils.isEmpty(x)));
      }
    }
    throw new MmsException("Unknown type of the message: " + paramString);
  }
  
  private void a(MzEncodedStringValue paramMzEncodedStringValue, Uri paramUri)
  {
    if (paramMzEncodedStringValue != null)
    {
      m = paramMzEncodedStringValue.getString();
      if (!TextUtils.isEmpty(m)) {
        break label46;
      }
    }
    label46:
    for (paramMzEncodedStringValue = "";; paramMzEncodedStringValue = gm.a(m, false).g())
    {
      n = paramMzEncodedStringValue;
      return;
      m = zg.a(c, paramUri);
      break;
    }
  }
  
  private void a(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length <= 1) {}
    do
    {
      return;
      paramArrayOfString = TextUtils.split(paramArrayOfString[1], ":");
    } while (paramArrayOfString.length <= 1);
    aa = Math.max(1, Integer.parseInt(paramArrayOfString[1]));
  }
  
  private void b(String paramString)
  {
    if (e <= 0L) {}
    for (;;)
    {
      return;
      String[] arrayOfString;
      if (!TextUtils.isEmpty(paramString))
      {
        arrayOfString = TextUtils.split(paramString, ";");
        if (arrayOfString.length < 1) {
          continue;
        }
      }
      try
      {
        X = Long.parseLong(arrayOfString[0]);
        int i1 = paramString.indexOf(";");
        if (i1 < paramString.length()) {
          x = paramString.substring(i1 + 1).replaceAll("cid:", "");
        }
        if ((L == 1) || (!TextUtils.isEmpty(x)) || (TextUtils.isEmpty(Z)) || (Z.startsWith("content://"))) {
          continue;
        }
        i1 = Z.lastIndexOf("/");
        if ((i1 <= 0) || (i1 >= Z.length())) {
          continue;
        }
        x = Z.substring(Z.lastIndexOf("/") + 1, Z.length());
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  private void c(String paramString)
  {
    if ((e <= 0L) || (TextUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return;
      paramString = TextUtils.split(paramString, ";");
      if (paramString.length >= 3) {
        try
        {
          i1 = Integer.parseInt(paramString[0]);
          if (i1 < 1)
          {
            Log.e(af, "Why the length of slideShow  is 0, or you need clear mmssms.db !!!");
            return;
          }
        }
        catch (Exception localException)
        {
          int i1;
          for (;;)
          {
            i1 = 0;
          }
          Object localObject;
          if (i1 > 1)
          {
            v = 4;
            if (TextUtils.isEmpty(paramString[2]))
            {
              w = 0;
              return;
            }
            paramString = TextUtils.split(paramString[2], "#");
            localObject = paramString[0];
            if ("text".equals(localObject))
            {
              w = 0;
              return;
            }
            if ("img".equals(localObject))
            {
              w = 1;
              return;
            }
            if ("video".equals(localObject))
            {
              w = 2;
              return;
            }
            if ("audio".equals(localObject))
            {
              w = 3;
              return;
            }
            if ("ref".equals(localObject))
            {
              w = 5;
              return;
            }
            if ("talk".equals(localObject))
            {
              w = 6;
              a(paramString);
              return;
            }
            if ("file".equals(localObject))
            {
              w = 7;
              return;
            }
            w = 0;
            return;
          }
          if (TextUtils.isEmpty(paramString[2]))
          {
            if (v == b) {
              v = 0;
            }
          }
          else
          {
            paramString = TextUtils.split(paramString[2], "#");
            localObject = paramString[0];
            if ("text".equals(localObject))
            {
              v = 0;
              return;
            }
            if ("img".equals(localObject))
            {
              v = 1;
              return;
            }
            if ("video".equals(localObject))
            {
              v = 2;
              return;
            }
            if ("audio".equals(localObject))
            {
              v = 3;
              return;
            }
            if ("ref".equals(localObject))
            {
              v = 5;
              return;
            }
            if ("talk".equals(localObject))
            {
              v = 6;
              a(paramString);
              return;
            }
            if ("file".equals(localObject))
            {
              v = 7;
              return;
            }
            if ("location".equals(localObject))
            {
              v = 8;
              return;
            }
            v = 0;
          }
        }
      }
    }
  }
  
  public boolean A()
  {
    boolean bool = false;
    int i1;
    if ((j()) && (h == vv.a.e) && ((K == 134) || (K == 129)))
    {
      i1 = 1;
      if ((!q()) || (h != vv.a.e)) {
        break label77;
      }
    }
    label77:
    for (int i2 = 1;; i2 = 0)
    {
      if ((i1 != 0) || (i2 != 0)) {
        bool = true;
      }
      return bool;
      i1 = 0;
      break;
    }
  }
  
  public boolean B()
  {
    boolean bool = false;
    int i1;
    if ((j()) && ((g == 4) || (g == 5) || (g == 2) || (ah())))
    {
      i1 = 1;
      if ((!q()) || ((g != 5) && (g != 4) && (g != 2) && (g != 6) && (!ah()))) {
        break label108;
      }
    }
    label108:
    for (int i2 = 1;; i2 = 0)
    {
      if ((i1 != 0) || (i2 != 0)) {
        bool = true;
      }
      return bool;
      i1 = 0;
      break;
    }
  }
  
  public boolean C()
  {
    int i1;
    if ((j()) && (E == 5))
    {
      i1 = 1;
      if ((!q()) || (g != 6)) {
        break label57;
      }
    }
    label57:
    for (int i2 = 1;; i2 = 0)
    {
      if (((i2 == 0) && (i1 == 0)) || (z())) {
        break label62;
      }
      return true;
      i1 = 0;
      break;
    }
    label62:
    return false;
  }
  
  public boolean D()
  {
    return (q()) && (L == 266);
  }
  
  public boolean E()
  {
    return (j()) && (L == 3);
  }
  
  public boolean F()
  {
    return (E()) && (u != 130) && (v == 6);
  }
  
  public boolean G()
  {
    return (E()) && (u == 130);
  }
  
  public boolean H()
  {
    return (G()) && (v == 6);
  }
  
  public CharSequence I()
  {
    return r;
  }
  
  public CharSequence J()
  {
    return s;
  }
  
  public Uri K()
  {
    return t;
  }
  
  public long L()
  {
    return z;
  }
  
  public long M()
  {
    return e;
  }
  
  public String N()
  {
    return O;
  }
  
  public int O()
  {
    return G & 0xFFFFFFFB;
  }
  
  public void P()
  {
    if ((ai != null) && (!ai.a()))
    {
      if (Log.isLoggable("Mms:app", 3)) {
        Log.v(af, "cancelPduLoading for: " + this);
      }
      ai.a(t);
      ai = null;
    }
    ab();
  }
  
  public lr Q()
  {
    return B;
  }
  
  public long R()
  {
    return ab;
  }
  
  public boolean S()
  {
    return ac;
  }
  
  public boolean T()
  {
    return (aj == 1L) || (aj == 3L);
  }
  
  public boolean U()
  {
    return (M == a) && (q()) && (P != null);
  }
  
  public byte[] V()
  {
    if (P != null) {
      return IccUtils.hexStringToBytes(P);
    }
    return null;
  }
  
  public String W()
  {
    if (TextUtils.isEmpty(n)) {
      return m;
    }
    return n;
  }
  
  public long X()
  {
    return X;
  }
  
  public String Y()
  {
    return Y;
  }
  
  public String Z()
  {
    return Z;
  }
  
  public long a()
  {
    return e;
  }
  
  public void a(Uri paramUri)
  {
    am = paramUri;
  }
  
  public void a(View paramView)
  {
    an = paramView;
  }
  
  public void a(CharSequence paramCharSequence)
  {
    r = paramCharSequence;
  }
  
  public void a(String paramString)
  {
    if ((B == null) || (B.size() != 1) || (!m())) {
      return;
    }
    B.c(paramString);
  }
  
  public void a(vv.b paramb)
  {
    if (al != null) {
      al = null;
    }
    al = paramb;
  }
  
  public void a(vv.d paramd)
  {
    ah = paramd;
  }
  
  public void a(boolean paramBoolean)
  {
    ae = paramBoolean;
  }
  
  public int aa()
  {
    return aa;
  }
  
  public void ab()
  {
    if ((n()) || ((m()) && (y())))
    {
      if (Log.isLoggable("Mms:app", 3)) {
        Log.v(af, "cancelPduLoading for: " + this);
      }
      MmsApp.c().a(t);
    }
  }
  
  public Uri ac()
  {
    return am;
  }
  
  public boolean ad()
  {
    return S == 1;
  }
  
  public boolean ae()
  {
    return !TextUtils.isEmpty(o);
  }
  
  public boolean af()
  {
    return !TextUtils.isEmpty(A);
  }
  
  public int ag()
  {
    return zv.a(U);
  }
  
  public boolean ah()
  {
    return (!j()) && (g == 7);
  }
  
  public HashMap<String, Object> ai()
  {
    if (ad == null)
    {
      ad = new HashMap();
      ad.put("simIndex", String.valueOf(T));
      ad.put("msgId", Long.valueOf(e));
      ad.put("phoneNum", m);
      ad.put("content", o);
      ad.put("ALLOW_VERCODE_MSG", "true");
    }
    return ad;
  }
  
  public boolean aj()
  {
    return ag < System.currentTimeMillis();
  }
  
  public boolean ak()
  {
    return ae;
  }
  
  public View al()
  {
    return an;
  }
  
  public HashMap b()
  {
    return ai();
  }
  
  public void b(CharSequence paramCharSequence)
  {
    s = paramCharSequence;
  }
  
  public void b(boolean paramBoolean)
  {
    ak = paramBoolean;
  }
  
  public String c()
  {
    return m;
  }
  
  public String d()
  {
    return null;
  }
  
  public long e()
  {
    return ab;
  }
  
  public String f()
  {
    return o;
  }
  
  public void g()
  {
    MmsApp.c().a(t, new vv.c());
  }
  
  public boolean h()
  {
    return X != 0L;
  }
  
  public boolean i()
  {
    return v == 4;
  }
  
  public boolean j()
  {
    return d.equals("mms");
  }
  
  public boolean k()
  {
    return (j()) && (L == 1);
  }
  
  public boolean l()
  {
    return (k()) && (u == 130);
  }
  
  public boolean m()
  {
    return (j()) && (L == 2);
  }
  
  public boolean n()
  {
    return (m()) && (u == 130);
  }
  
  public boolean o()
  {
    return (m()) && (u != 130) && (v == 6);
  }
  
  public boolean p()
  {
    return (n()) && (v == 6);
  }
  
  public boolean q()
  {
    return d.equals("sms");
  }
  
  public int r()
  {
    return L;
  }
  
  public boolean s()
  {
    return (m()) || (t());
  }
  
  public boolean t()
  {
    return L == 256;
  }
  
  public String toString()
  {
    return "type: " + d + " box: " + g + " uri: " + t + " address: " + m + " contact: " + n + " read: " + i + " delivery status: " + h;
  }
  
  public boolean u()
  {
    return Q;
  }
  
  public boolean v()
  {
    int i1;
    if ((j()) && ((g == 1) || (g == 0)))
    {
      i1 = 1;
      if ((!q()) || ((g != 1) && (g != 0))) {
        break label63;
      }
    }
    label63:
    for (int i2 = 1;; i2 = 0)
    {
      if ((i1 != 0) || (i2 != 0)) {
        break label68;
      }
      return true;
      i1 = 0;
      break;
    }
    label68:
    return false;
  }
  
  public boolean w()
  {
    return (g == 1) || (g == 0);
  }
  
  public boolean x()
  {
    boolean bool = false;
    int i1;
    if ((j()) && ((g == 4) || (g == 5)))
    {
      i1 = 1;
      if ((!q()) || ((g != 5) && (g != 4) && (g != 6))) {
        break label78;
      }
    }
    label78:
    for (int i2 = 1;; i2 = 0)
    {
      if ((i1 != 0) || (i2 != 0)) {
        bool = true;
      }
      return bool;
      i1 = 0;
      break;
    }
  }
  
  public boolean y()
  {
    return (!z()) && (x());
  }
  
  public boolean z()
  {
    int i1;
    if ((j()) && (wd.a(J)))
    {
      i1 = 1;
      if (((!q()) || (g != 5)) && ((!q()) || (h != vv.a.c) || (g == 1))) {
        break label83;
      }
    }
    label83:
    for (int i2 = 1;; i2 = 0)
    {
      if (((i1 == 0) && (i2 == 0)) || (!B())) {
        break label88;
      }
      return true;
      i1 = 0;
      break;
    }
    label88:
    return false;
  }
  
  public static enum a
  {
    private a() {}
  }
  
  public static abstract interface b
  {
    public abstract void a(vv paramvv, Uri paramUri, long paramLong1, long paramLong2);
  }
  
  public class c
    implements MmsApp.h
  {
    public c() {}
    
    public void a(Uri paramUri, long paramLong1, long paramLong2)
    {
      if (vv.c(vv.this) != null) {
        vv.c(vv.this).a(vv.this, paramUri, paramLong1, paramLong2);
      }
    }
  }
  
  public static abstract interface d
  {
    public abstract void a(vv paramvv);
  }
  
  public class e
    implements zy
  {
    public e() {}
    
    public void a(Object paramObject, Throwable arg2)
    {
      if (??? != null)
      {
        y = false;
        Log.e(vv.am(), "PduLoadedMessageItemCallback PDU couldn't be loaded: ", ???);
      }
      long l1;
      label309:
      do
      {
        return;
        y = true;
        if (vv.a(vv.this) != null) {}
        for (;;)
        {
          synchronized (vv.a(vv.this))
          {
            vv.a(vv.this).a(true);
            ??? = (aan.a)paramObject;
            if (130 != u) {
              break label309;
            }
            h = vv.a.a;
            if ((a instanceof MzNotificationInd))
            {
              paramObject = (MzNotificationInd)a;
              vv.a(vv.this, ((MzNotificationInd)paramObject).getFrom(), t);
              C = ((MzNotificationInd)paramObject).getMessageSize();
              l1 = ((MzNotificationInd)paramObject).getExpiry() * 1000L;
              if ((!x()) && (130 != u)) {
                k = wd.a(c, l1, 1, true);
              }
              if (vv.b(vv.this) == null) {
                break;
              }
              vv.b(vv.this).a(vv.this);
              return;
            }
          }
          Log.e(vv.am(), "getPdu()---wrong--" + a + "-- uri = " + t);
          Log.e(vv.am(), "getPdu()----mMessageProtocol = " + L);
          l1 = 0L;
        }
      } while (H.isClosed());
      paramObject = (MzMultimediaMessagePdu)a;
      B = b;
      if (((v != 6) && (!y())) || ((v == vv.b) && (y()))) {
        v = wd.a(B, (MzMultimediaMessagePdu)paramObject);
      }
      if (v == 8) {
        R = wd.a(B);
      }
      label470:
      label482:
      long l2;
      if (u == 132) {
        if (paramObject == null)
        {
          vv.a(vv.this, null, t);
          l1 = 0L;
          if (B != null) {
            break label832;
          }
          paramObject = null;
          if ((paramObject != null) && (((lq)paramObject).d()))
          {
            paramObject = ((lq)paramObject).p();
            o = ((lu)paramObject).a();
            p = ((lu)paramObject).j();
          }
          if ((m()) && (B != null))
          {
            paramObject = B.j();
            if (paramObject != null) {
              x = ((lm)paramObject).n();
            }
          }
          paramObject = vv.this;
          ??? = vv.this;
          if (B != null) {
            break label847;
          }
          l2 = C;
          C = l2;
          z = l2;
          paramObject = H.getString(I.q);
          if ((paramObject != null) && (m.equals(c.getString(2131493015)))) {
            break label901;
          }
          h = vv.a.a;
        }
      }
      for (;;)
      {
        paramObject = H.getString(I.r);
        if ((paramObject != null) && (m.equals(c.getString(2131493015)))) {
          break label961;
        }
        vv.this.i = false;
        break;
        paramObject = (MzRetrieveConf)paramObject;
        vv.a(vv.this, ((MzRetrieveConf)paramObject).getFrom(), t);
        l1 = ((MzRetrieveConf)paramObject).getDate() * 1000L;
        break label470;
        ??? = vv.this;
        vv localvv = vv.this;
        String str = c.getString(2131493015);
        m = str;
        n = str;
        if (paramObject == null)
        {
          l1 = 0L;
          break label470;
        }
        l1 = ((MzSendReq)paramObject).getDate() * 1000L;
        break label470;
        label832:
        paramObject = B.a(0);
        break label482;
        label847:
        long l3 = C;
        if (MmsApp.a) {}
        for (l2 = B.d();; l2 = B.e())
        {
          l2 = Math.max(l3, l2);
          break;
        }
        try
        {
          label901:
          if (Integer.parseInt((String)paramObject) != 128) {
            break label948;
          }
          h = vv.a.e;
        }
        catch (NumberFormatException paramObject)
        {
          Log.e(vv.am(), "Value for delivery report was invalid.");
          h = vv.a.a;
        }
        continue;
        label948:
        h = vv.a.a;
      }
      for (;;)
      {
        try
        {
          label961:
          int i = Integer.parseInt((String)paramObject);
          paramObject = vv.this;
          if (i != 128) {
            break label1012;
          }
          bool = true;
          i = bool;
        }
        catch (NumberFormatException paramObject)
        {
          Log.e(vv.am(), "Value for read report was invalid.");
          vv.this.i = false;
        }
        break;
        label1012:
        boolean bool = false;
      }
    }
  }
}

/* Location:
 * Qualified Name:     vv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */