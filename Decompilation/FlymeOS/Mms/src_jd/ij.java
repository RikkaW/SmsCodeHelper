public class ij
  extends ia
  implements aui
{
  ij(hy paramhy, String paramString)
  {
    super(paramhy, paramString);
  }
  
  private int a(String paramString, boolean paramBoolean)
  {
    if (paramString.endsWith("px")) {
      return Integer.parseInt(paramString.substring(0, paramString.indexOf("px")));
    }
    if (paramString.endsWith("%"))
    {
      double d = Integer.parseInt(paramString.substring(0, paramString.length() - 1)) * 0.01D;
      if (paramBoolean) {}
      for (d = ((auc)getOwnerDocument()).m().b().f() * d;; d = ((auc)getOwnerDocument()).m().b().e() * d) {
        return (int)Math.round(d);
      }
    }
    return Integer.parseInt(paramString);
  }
  
  public String a()
  {
    String str = getAttribute("fit");
    if ("fill".equalsIgnoreCase(str)) {
      return "fill";
    }
    if ("meet".equalsIgnoreCase(str)) {
      return "meet";
    }
    if ("scroll".equalsIgnoreCase(str)) {
      return "scroll";
    }
    if ("slice".equalsIgnoreCase(str)) {
      return "slice";
    }
    return "hidden";
  }
  
  public void a(int paramInt)
  {
    setAttribute("left", String.valueOf(paramInt));
  }
  
  public void a(String paramString)
  {
    setAttribute("id", paramString);
  }
  
  public int b()
  {
    try
    {
      i = a(getAttribute("left"), true);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        int i = ((auc)getOwnerDocument()).m().b().f();
        int j = a(getAttribute("right"), true);
        int k = a(getAttribute("width"), true);
        return i - j - k;
      }
      catch (NumberFormatException localNumberFormatException2) {}
    }
    return 0;
  }
  
  public void b(int paramInt)
  {
    setAttribute("top", String.valueOf(paramInt));
  }
  
  public void b(String paramString)
  {
    if ((paramString.equalsIgnoreCase("fill")) || (paramString.equalsIgnoreCase("meet")) || (paramString.equalsIgnoreCase("scroll")) || (paramString.equalsIgnoreCase("slice")))
    {
      setAttribute("fit", paramString.toLowerCase());
      return;
    }
    setAttribute("fit", "hidden");
  }
  
  public int c()
  {
    try
    {
      i = a(getAttribute("top"), false);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        int i = ((auc)getOwnerDocument()).m().b().e();
        int j = a(getAttribute("bottom"), false);
        int k = a(getAttribute("height"), false);
        return i - j - k;
      }
      catch (NumberFormatException localNumberFormatException2) {}
    }
    return 0;
  }
  
  public void c(int paramInt)
  {
    setAttribute("height", String.valueOf(paramInt) + "px");
  }
  
  public void c(String paramString)
  {
    setAttribute("backgroundColor", paramString);
  }
  
  public String d()
  {
    return getAttribute("backgroundColor");
  }
  
  public void d(int paramInt)
  {
    setAttribute("width", String.valueOf(paramInt) + "px");
  }
  
  public int e()
  {
    int j;
    int i;
    try
    {
      j = a(getAttribute("height"), false);
      i = j;
      if (j == 0) {
        i = ((auc)getOwnerDocument()).m().b().e();
      }
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      i = ((auc)getOwnerDocument()).m().b().e();
    }
    for (;;)
    {
      try
      {
        j = a(getAttribute("top"), false);
        i -= j;
      }
      catch (NumberFormatException localNumberFormatException3)
      {
        continue;
      }
      try
      {
        j = a(getAttribute("bottom"), false);
        return i - j;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        return i;
      }
    }
  }
  
  public int f()
  {
    int j;
    int i;
    try
    {
      j = a(getAttribute("width"), true);
      i = j;
      if (j == 0) {
        i = ((auc)getOwnerDocument()).m().b().f();
      }
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      i = ((auc)getOwnerDocument()).m().b().f();
    }
    for (;;)
    {
      try
      {
        j = a(getAttribute("left"), true);
        i -= j;
      }
      catch (NumberFormatException localNumberFormatException3)
      {
        continue;
      }
      try
      {
        j = a(getAttribute("right"), true);
        return i - j;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        return i;
      }
    }
  }
  
  public String j()
  {
    return getAttribute("id");
  }
  
  public String toString()
  {
    return super.toString() + ": id=" + j() + ", width=" + f() + ", height=" + e() + ", left=" + b() + ", top=" + c();
  }
}

/* Location:
 * Qualified Name:     ij
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */