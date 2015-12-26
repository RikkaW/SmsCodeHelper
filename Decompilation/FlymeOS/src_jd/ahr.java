import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ahr
{
  ahf a(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    while (paramString.contains("SuccessCode=\"0\"")) {
      return null;
    }
    try
    {
      paramString = new ByteArrayInputStream(paramString.getBytes("UTF-8"));
      localSAXParserFactory = SAXParserFactory.newInstance();
      locala = new ahr.a(null);
      if (paramString == null) {}
    }
    catch (UnsupportedEncodingException paramString)
    {
      try
      {
        for (;;)
        {
          SAXParserFactory localSAXParserFactory;
          ahr.a locala;
          localSAXParserFactory.newSAXParser().parse(paramString, locala);
          paramString.close();
          a.f("network");
          if (a.h() == 0L) {
            a.a(ahz.a());
          }
          return a;
          paramString = paramString;
          paramString = null;
        }
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          paramString = paramString;
          paramString.printStackTrace();
        }
      }
      finally {}
    }
  }
  
  static class a
    extends DefaultHandler
  {
    public ahf a = new ahf();
    private String b = "";
    
    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      b = String.valueOf(paramArrayOfChar, paramInt1, paramInt2);
    }
    
    public void endElement(String paramString1, String paramString2, String paramString3)
    {
      if (paramString2.equals("retype"))
      {
        a.h(b);
        if (a.t() == null) {
          a.a(new JSONObject());
        }
      }
      do
      {
        try
        {
          if (paramString2.equals("eab"))
          {
            a.t().put(paramString2, b);
            return;
            if (paramString2.equals("adcode"))
            {
              a.k(b);
              break;
            }
            if (paramString2.equals("citycode"))
            {
              a.i(b);
              break;
            }
            if (paramString2.equals("radius"))
            {
              try
              {
                a.a(Float.valueOf(b).floatValue());
              }
              catch (Throwable paramString1)
              {
                paramString1.printStackTrace();
                a.a(3891.0F);
              }
              break;
            }
            if (paramString2.equals("cenx"))
            {
              try
              {
                b = ahv.a(Double.valueOf(b), "#.000000");
                a.a(Double.valueOf(b).doubleValue());
              }
              catch (Throwable paramString1)
              {
                paramString1.printStackTrace();
                a.a(0.0D);
              }
              break;
            }
            if (paramString2.equals("ceny"))
            {
              try
              {
                b = ahv.a(Double.valueOf(b), "#.000000");
                a.b(Double.valueOf(b).doubleValue());
              }
              catch (Throwable paramString1)
              {
                paramString1.printStackTrace();
                a.b(0.0D);
              }
              break;
            }
            if (paramString2.equals("desc"))
            {
              a.j(b);
              break;
            }
            if (paramString2.equals("country"))
            {
              a.l(b);
              break;
            }
            if (paramString2.equals("province"))
            {
              a.m(b);
              break;
            }
            if (paramString2.equals("city"))
            {
              a.n(b);
              break;
            }
            if (paramString2.equals("road"))
            {
              a.o(b);
              break;
            }
            if (paramString2.equals("street"))
            {
              a.p(b);
              break;
            }
            if (paramString2.equals("poiname"))
            {
              a.q(b);
              break;
            }
            if (paramString2.equals("BIZ"))
            {
              if (a.t() == null) {
                a.a(new JSONObject());
              }
              try
              {
                a.t().put("BIZ", b);
              }
              catch (Throwable paramString1)
              {
                paramString1.printStackTrace();
              }
              break;
            }
            if (paramString2.equals("flr"))
            {
              a.b(b);
              break;
            }
            if (paramString2.equals("pid"))
            {
              a.a(b);
              break;
            }
            if (paramString2.equals("apiTime"))
            {
              try
              {
                if ("".equals(b)) {
                  break;
                }
                long l = Long.parseLong(b);
                a.a(l);
              }
              catch (Throwable paramString1)
              {
                paramString1.printStackTrace();
                a.a(ahz.a());
              }
              break;
            }
            if (paramString2.equals("coord"))
            {
              try
              {
                a.d(b);
              }
              catch (Throwable paramString1)
              {
                paramString1.printStackTrace();
              }
              break;
            }
            if (paramString2.equals("mcell"))
            {
              try
              {
                a.e(b);
              }
              catch (Throwable paramString1)
              {
                paramString1.printStackTrace();
              }
              break;
            }
            if (!paramString2.equals("district")) {
              break;
            }
            try
            {
              a.c(b);
            }
            catch (Throwable paramString1)
            {
              paramString1.printStackTrace();
            }
            break;
          }
          if (paramString2.equals("ctl"))
          {
            a.t().put(paramString2, b);
            return;
          }
        }
        catch (Throwable paramString1)
        {
          paramString1.printStackTrace();
          return;
        }
        if (paramString2.equals("suc"))
        {
          a.t().put(paramString2, b);
          return;
        }
      } while (!paramString2.equals("spa"));
      a.t().put(paramString2, b);
    }
    
    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    {
      b = "";
    }
  }
}

/* Location:
 * Qualified Name:     ahr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */