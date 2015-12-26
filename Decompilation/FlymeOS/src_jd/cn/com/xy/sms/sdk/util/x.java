package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import java.util.HashMap;
import java.util.Random;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class x
{
  public static int a(String paramString)
  {
    if (!StringUtils.isNull(paramString)) {
      try
      {
        int i = Integer.parseInt(paramString);
        return i;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return 0;
  }
  
  public static String a(Element paramElement, String paramString)
  {
    try
    {
      paramElement = paramElement.getElementsByTagName(paramString);
      if ((paramElement != null) && (paramElement.getLength() > 0))
      {
        paramElement = a(paramElement.item(0)).toString();
        return paramElement;
      }
    }
    catch (Throwable paramElement)
    {
      paramElement.printStackTrace();
    }
    return "";
  }
  
  public static String a(Node paramNode)
  {
    if (paramNode != null) {
      try
      {
        paramNode = paramNode.getFirstChild();
        if (paramNode != null)
        {
          paramNode = paramNode.getNodeValue().trim();
          return paramNode;
        }
      }
      catch (Throwable paramNode) {}
    }
    return "";
  }
  
  public static HashMap<String, String> a(Document paramDocument)
  {
    int j = 0;
    if (paramDocument == null) {
      return null;
    }
    for (;;)
    {
      try
      {
        NodeList localNodeList = paramDocument.getElementsByTagName("popu");
        if ((localNodeList == null) || (localNodeList.getLength() <= 0)) {
          break;
        }
        int i = localNodeList.getLength();
        paramDocument = new HashMap();
        if (i > 1)
        {
          i = new Random().nextInt(i * 3) % i;
          localNodeList = localNodeList.item(i).getChildNodes();
          i = j;
          if (i >= localNodeList.getLength()) {
            return paramDocument;
          }
          Node localNode = localNodeList.item(i);
          paramDocument.put(localNode.getNodeName(), a(localNode));
          i += 1;
          continue;
        }
        i = 0;
      }
      catch (Throwable paramDocument)
      {
        return null;
      }
    }
  }
  
  public static HashMap<String, String> a(Document paramDocument, String paramString)
  {
    if (paramDocument == null) {
      return null;
    }
    for (;;)
    {
      int k;
      int i;
      try
      {
        if (ViewUtil.getChannelType() == 7)
        {
          paramDocument = paramDocument.getFirstChild().getChildNodes();
          if ((paramDocument != null) && (paramDocument.getLength() > 0))
          {
            k = paramDocument.getLength();
            paramString = new HashMap();
            i = 0;
            break label224;
          }
        }
        else
        {
          paramDocument = paramDocument.getElementsByTagName("title_" + paramString);
          continue;
          NodeList localNodeList = ((Element)paramDocument.item(i)).getElementsByTagName("popu");
          if ((localNodeList == null) || (localNodeList.getLength() <= 0)) {
            break label232;
          }
          j = localNodeList.getLength();
          if (j <= 1) {
            break label219;
          }
          j = new Random().nextInt(j * 3) % j;
          localNodeList = localNodeList.item(j).getChildNodes();
          j = 0;
          if (j >= localNodeList.getLength()) {
            break label232;
          }
          Node localNode = localNodeList.item(j);
          paramString.put(localNode.getNodeName(), a(localNode));
          j += 1;
          continue;
        }
        return null;
      }
      catch (Throwable paramDocument)
      {
        return null;
      }
      label219:
      int j = 0;
      continue;
      label224:
      while (i >= k)
      {
        return paramString;
        label232:
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.x
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */