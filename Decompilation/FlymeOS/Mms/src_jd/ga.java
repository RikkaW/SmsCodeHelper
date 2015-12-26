import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings.System;
import android.util.Log;
import android.view.ViewConfiguration;
import com.android.mms.MmsApp;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ga
{
  private static int A;
  private static boolean B;
  private static boolean C;
  private static boolean D;
  private static boolean E;
  private static boolean F;
  private static int G;
  private static int H;
  private static boolean I;
  private static int J;
  private static int K;
  private static int L;
  private static int M;
  private static boolean N;
  public static int a;
  public static final int b;
  private static boolean c = false;
  private static int d = 1;
  private static long e = 1073741824L;
  private static long f = 52428800L;
  private static int g;
  private static int h;
  private static int i;
  private static String j;
  private static String k;
  private static String l;
  private static String m;
  private static String n;
  private static String o;
  private static int p;
  private static int q;
  private static int r;
  private static int s;
  private static int t;
  private static int u;
  private static int v;
  private static int w;
  private static int x;
  private static boolean y;
  private static boolean z;
  
  static
  {
    if (MmsApp.a)
    {
      i1 = 300;
      g = i1 * 1024;
      h = 102400;
      i = 300;
      j = "Android-Mms/2.0";
      k = "x-wap-profile";
      l = null;
      m = null;
      n = null;
      o = null;
      p = 1280;
      q = 1280;
      r = Integer.MAX_VALUE;
      s = 10000;
      t = 1000;
      u = 2;
      v = 5000;
      w = 90000;
      x = 7;
      y = false;
      z = true;
      A = 4;
      B = true;
      C = true;
      D = true;
      E = true;
      F = true;
      G = -1;
      H = 99;
      I = false;
      J = 2;
      K = 48;
      L = 40;
      if (!MmsApp.a) {
        break label214;
      }
    }
    label214:
    for (int i1 = 10;; i1 = 5)
    {
      M = i1;
      N = false;
      b = ViewConfiguration.getDoubleTapTimeout();
      return;
      i1 = 298;
      break;
    }
  }
  
  public static int A()
  {
    return L;
  }
  
  public static int B()
  {
    return M;
  }
  
  public static boolean C()
  {
    return MmsApp.m;
  }
  
  public static boolean D()
  {
    return true;
  }
  
  public static boolean E()
  {
    return true;
  }
  
  public static Bundle F()
  {
    return null;
  }
  
  public static int a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return p();
    case 0: 
      return 500;
    }
    return 420;
  }
  
  public static int a(boolean paramBoolean)
  {
    if (true == paramBoolean) {
      return i * 1024;
    }
    return i;
  }
  
  public static Intent a(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
    localIntent.putExtra("package", paramString1);
    localIntent.putExtra("dialog_title", paramString2);
    return localIntent;
  }
  
  public static void a(Context paramContext)
  {
    Log.v("MmsConfig", "mnc/mcc: " + aau.a("PROPERTY_ICC_OPERATOR_NUMERIC"));
    f(paramContext);
    e = Settings.System.getLong(paramContext.getContentResolver(), "file_transfer_max_size", 1073741824L);
    a = paramContext.getResources().getColor(2131820727);
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    if (b(paramContext) == paramInt) {
      return;
    }
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("attach_contact_type", paramInt).apply();
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    if (d(paramContext) == paramBoolean) {
      return;
    }
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("guide_flyme_loading", paramBoolean);
    paramContext.apply();
  }
  
  public static final void a(XmlPullParser paramXmlPullParser)
  {
    int i1;
    do
    {
      i1 = paramXmlPullParser.next();
    } while ((i1 != 2) && (i1 != 1));
  }
  
  public static final void a(XmlPullParser paramXmlPullParser, String paramString)
  {
    int i1;
    do
    {
      i1 = paramXmlPullParser.next();
    } while ((i1 != 2) && (i1 != 1));
    if (i1 != 2) {
      throw new XmlPullParserException("No start tag found");
    }
    if (!paramXmlPullParser.getName().equals(paramString)) {
      throw new XmlPullParserException("Unexpected start tag: found " + paramXmlPullParser.getName() + ", expected " + paramString);
    }
  }
  
  public static boolean a()
  {
    return false;
  }
  
  public static int b()
  {
    return A;
  }
  
  public static int b(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("attach_contact_type", 1);
  }
  
  public static void b(Context paramContext, boolean paramBoolean)
  {
    if (e(paramContext) == paramBoolean) {
      return;
    }
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("create_meizu_assistant", paramBoolean);
    paramContext.apply();
  }
  
  public static boolean c()
  {
    return d == 1;
  }
  
  public static boolean c(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("sms_promo_dismissed_key", false);
  }
  
  public static long d()
  {
    return f;
  }
  
  public static boolean d(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("guide_flyme_loading", true);
  }
  
  public static long e()
  {
    return e;
  }
  
  public static boolean e(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("create_meizu_assistant", true);
  }
  
  public static int f()
  {
    return Math.min(g, 1048576);
  }
  
  private static void f(Context paramContext)
  {
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(2131230720);
    for (;;)
    {
      String str1;
      String str3;
      try
      {
        a(localXmlResourceParser, "mms_config");
        a(localXmlResourceParser);
        str1 = localXmlResourceParser.getName();
        if (str1 == null)
        {
          localXmlResourceParser.close();
          if ((!c()) || (l != null)) {
            break label1020;
          }
          paramContext = "uaProfUrl";
          if (paramContext != null) {
            Log.e("MmsConfig", String.format("MmsConfig.loadMmsSettings mms_config.xml missing %s setting", new Object[] { paramContext }));
          }
          return;
        }
        String str2 = localXmlResourceParser.getAttributeName(0);
        str3 = localXmlResourceParser.getAttributeValue(0);
        if (localXmlResourceParser.next() != 4) {
          break label1025;
        }
        paramContext = localXmlResourceParser.getText();
        Log.v("MmsConfig", "tag: " + str1 + " value: " + str3 + " - " + paramContext);
        if (!"name".equalsIgnoreCase(str2)) {
          continue;
        }
        if (!"bool".equals(str1)) {
          break label527;
        }
        if ("enabledMMS".equalsIgnoreCase(str3)) {
          if ("true".equalsIgnoreCase(paramContext))
          {
            i1 = 1;
            d = i1;
            continue;
          }
        }
      }
      catch (XmlPullParserException paramContext)
      {
        Log.e("MmsConfig", "loadMmsSettings caught ", paramContext);
        localXmlResourceParser.close();
        continue;
        int i1 = 0;
        continue;
        if ("enabledTransID".equalsIgnoreCase(str3))
        {
          c = "true".equalsIgnoreCase(paramContext);
          continue;
        }
      }
      catch (NumberFormatException paramContext)
      {
        Log.e("MmsConfig", "loadMmsSettings caught ", paramContext);
        localXmlResourceParser.close();
        continue;
        if ("enabledNotifyWapMMSC".equalsIgnoreCase(str3))
        {
          y = "true".equalsIgnoreCase(paramContext);
          continue;
        }
      }
      catch (IOException paramContext)
      {
        Log.e("MmsConfig", "loadMmsSettings caught ", paramContext);
        localXmlResourceParser.close();
        continue;
        if ("aliasEnabled".equalsIgnoreCase(str3))
        {
          I = "true".equalsIgnoreCase(paramContext);
          continue;
        }
      }
      finally
      {
        localXmlResourceParser.close();
      }
      if ("allowAttachAudio".equalsIgnoreCase(str3))
      {
        z = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableMultipartSMS".equalsIgnoreCase(str3))
      {
        B = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableSlideDuration".equalsIgnoreCase(str3))
      {
        C = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableMMSReadReports".equalsIgnoreCase(str3))
      {
        D = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableSMSDeliveryReports".equalsIgnoreCase(str3))
      {
        E = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableMMSDeliveryReports".equalsIgnoreCase(str3))
      {
        F = "true".equalsIgnoreCase(paramContext);
      }
      else if ("enableGroupMms".equalsIgnoreCase(str3))
      {
        N = "true".equalsIgnoreCase(paramContext);
        continue;
        label527:
        if ("int".equals(str1))
        {
          if ("maxMessageSize".equalsIgnoreCase(str3))
          {
            if (MmsApp.a) {
              g = Integer.parseInt("307200");
            } else {
              g = Integer.parseInt(paramContext);
            }
          }
          else if ("maxImageHeight".equalsIgnoreCase(str3))
          {
            p = Integer.parseInt(paramContext);
          }
          else if ("maxImageWidth".equalsIgnoreCase(str3))
          {
            q = Integer.parseInt(paramContext);
          }
          else if ("defaultSMSMessagesPerThread".equalsIgnoreCase(str3))
          {
            s = Integer.parseInt(paramContext);
          }
          else if ("defaultMMSMessagesPerThread".equalsIgnoreCase(str3))
          {
            t = Integer.parseInt(paramContext);
          }
          else if ("minMessageCountPerThread".equalsIgnoreCase(str3))
          {
            u = Integer.parseInt(paramContext);
          }
          else if ("maxMessageCountPerThread".equalsIgnoreCase(str3))
          {
            v = Integer.parseInt(paramContext);
          }
          else if ("recipientLimit".equalsIgnoreCase(str3))
          {
            r = Integer.parseInt(paramContext);
            if (r < 0) {
              r = Integer.MAX_VALUE;
            }
          }
          else if ("httpSocketTimeout".equalsIgnoreCase(str3))
          {
            w = Integer.parseInt(paramContext);
          }
          else if ("minimumSlideElementDuration".equalsIgnoreCase(str3))
          {
            x = Integer.parseInt(paramContext);
          }
          else if ("maxSizeScaleForPendingMmsAllowed".equalsIgnoreCase(str3))
          {
            H = Integer.parseInt(paramContext);
          }
          else if ("aliasMinChars".equalsIgnoreCase(str3))
          {
            J = Integer.parseInt(paramContext);
          }
          else if ("aliasMaxChars".equalsIgnoreCase(str3))
          {
            K = Integer.parseInt(paramContext);
          }
          else if ("smsToMmsTextThreshold".equalsIgnoreCase(str3))
          {
            A = Integer.parseInt(paramContext);
          }
          else if ("maxMessageTextSize".equalsIgnoreCase(str3))
          {
            G = Integer.parseInt(paramContext);
          }
          else if ("maxSubjectLength".equalsIgnoreCase(str3))
          {
            L = Integer.parseInt(paramContext);
          }
        }
        else if ("string".equals(str1)) {
          if ("userAgent".equalsIgnoreCase(str3))
          {
            j = paramContext;
          }
          else if ("uaProfTagName".equalsIgnoreCase(str3))
          {
            k = paramContext;
          }
          else if ("uaProfUrl".equalsIgnoreCase(str3))
          {
            l = paramContext;
          }
          else if ("httpParams".equalsIgnoreCase(str3))
          {
            m = paramContext;
          }
          else if ("httpParamsLine1Key".equalsIgnoreCase(str3))
          {
            n = paramContext;
          }
          else if ("emailGatewayNumber".equalsIgnoreCase(str3))
          {
            o = paramContext;
            continue;
            label1020:
            paramContext = null;
            continue;
            label1025:
            paramContext = null;
          }
        }
      }
    }
  }
  
  public static int g()
  {
    return Math.min(h, 1048576);
  }
  
  public static boolean h()
  {
    return c;
  }
  
  public static String i()
  {
    return l;
  }
  
  public static String j()
  {
    return o;
  }
  
  public static int k()
  {
    return p;
  }
  
  public static int l()
  {
    return q;
  }
  
  public static int m()
  {
    return 640;
  }
  
  public static int n()
  {
    return 640;
  }
  
  public static int o()
  {
    return r;
  }
  
  public static int p()
  {
    if (G > -1) {
      return G;
    }
    return 10000;
  }
  
  public static int q()
  {
    return s;
  }
  
  public static int r()
  {
    return t;
  }
  
  public static int s()
  {
    return x;
  }
  
  public static boolean t()
  {
    return B;
  }
  
  public static boolean u()
  {
    return C;
  }
  
  public static boolean v()
  {
    return y;
  }
  
  public static boolean w()
  {
    return I;
  }
  
  public static int x()
  {
    return J;
  }
  
  public static int y()
  {
    return K;
  }
  
  public static boolean z()
  {
    return z;
  }
}

/* Location:
 * Qualified Name:     ga
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */