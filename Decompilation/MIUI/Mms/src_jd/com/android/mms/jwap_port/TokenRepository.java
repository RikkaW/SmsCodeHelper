package com.android.mms.jwap_port;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TokenRepository
{
  private static byte CODEPAGE_DEFAULT = 0;
  private static byte currentCodepage = CODEPAGE_DEFAULT;
  private static Properties urnMappings;
  private Hashtable[][] codepages;
  Context mContext;
  private Document tokenDoc;
  
  public TokenRepository(String paramString, Context paramContext)
  {
    Object localObject6 = null;
    InputStream localInputStream2 = null;
    Object localObject5 = null;
    Object localObject4 = null;
    InputStream localInputStream1 = localInputStream2;
    Object localObject2 = localObject4;
    Object localObject3 = localObject6;
    Object localObject1 = localObject5;
    for (;;)
    {
      try
      {
        mContext = paramContext;
        localInputStream1 = localInputStream2;
        localObject2 = localObject4;
        localObject3 = localObject6;
        localObject1 = localObject5;
        Properties localProperties = new Properties();
        localInputStream1 = localInputStream2;
        localObject2 = localObject4;
        localObject3 = localObject6;
        localObject1 = localObject5;
        localInputStream2 = paramContext.getAssets().open("jwap_port/tokenRepositoryMappings.properties");
        localInputStream1 = localInputStream2;
        localObject2 = localObject4;
        localObject3 = localInputStream2;
        localObject1 = localObject5;
        localProperties.load(localInputStream2);
        localInputStream1 = localInputStream2;
        localObject2 = localObject4;
        localObject3 = localInputStream2;
        localObject1 = localObject5;
        paramString = localProperties.getProperty(paramString);
        localInputStream1 = localInputStream2;
        localObject2 = localObject4;
        localObject3 = localInputStream2;
        localObject1 = localObject5;
        paramString = paramContext.getAssets().open(paramString);
        localInputStream1 = localInputStream2;
        localObject2 = paramString;
        localObject3 = localInputStream2;
        localObject1 = paramString;
        tokenDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramString);
        localInputStream1 = localInputStream2;
        localObject2 = paramString;
        localObject3 = localInputStream2;
        localObject1 = paramString;
        initializeURNMappings();
        localInputStream1 = localInputStream2;
        localObject2 = paramString;
        localObject3 = localInputStream2;
        localObject1 = paramString;
        initializeHeaderInfo();
        localInputStream1 = localInputStream2;
        localObject2 = paramString;
        localObject3 = localInputStream2;
        localObject1 = paramString;
        currentCodepage = CODEPAGE_DEFAULT;
      }
      catch (Exception paramString)
      {
        localObject3 = localInputStream1;
        localObject1 = localObject2;
        paramString.printStackTrace();
        if (localInputStream1 == null) {
          continue;
        }
        try
        {
          localInputStream1.close();
          if (localObject2 == null) {
            continue;
          }
          try
          {
            ((InputStream)localObject2).close();
            return;
          }
          catch (Exception paramString)
          {
            paramString.printStackTrace();
            return;
          }
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
          continue;
        }
      }
      finally
      {
        if (localObject3 == null) {
          break label325;
        }
      }
      try
      {
        localInputStream2.close();
        if (paramString == null) {}
      }
      catch (Exception paramContext)
      {
        try
        {
          paramString.close();
          return;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
          return;
        }
        paramContext = paramContext;
        paramContext.printStackTrace();
      }
    }
    try
    {
      ((InputStream)localObject3).close();
      label325:
      if (localObject1 == null) {}
    }
    catch (Exception paramContext)
    {
      try
      {
        ((InputStream)localObject1).close();
        throw paramString;
        paramContext = paramContext;
        paramContext.printStackTrace();
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
        }
      }
    }
  }
  
  private byte getByteValue(String paramString)
  {
    try
    {
      byte b = Integer.valueOf(paramString, 16).byteValue();
      return b;
    }
    catch (Exception paramString)
    {
      System.out.println("token not found!!!, returning literal");
    }
    return 4;
  }
  
  private Element getCodepage(int paramInt)
  {
    NodeList localNodeList = tokenDoc.getElementsByTagName("codepage");
    int i = 0;
    while (i < localNodeList.getLength())
    {
      Element localElement = (Element)localNodeList.item(i);
      if (Integer.parseInt(localElement.getAttribute("number").trim()) == paramInt) {
        return localElement;
      }
      i += 1;
    }
    return null;
  }
  
  private String getKeyFromValue(Iterator paramIterator, byte paramByte)
  {
    while (paramIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramIterator.next();
      if (getByteValue(localEntry.getValue().toString()) == paramByte) {
        return localEntry.getKey().toString();
      }
    }
    return null;
  }
  
  private void initializeAttributeNameTokens(Element paramElement, int paramInt)
  {
    paramElement = new Hashtable();
    NodeList localNodeList = tokenDoc.getElementsByTagName("attribute-start");
    int i = 0;
    while (i < localNodeList.getLength())
    {
      Object localObject = (Element)localNodeList.item(i);
      int j = getByteValue(((Element)localObject).getAttribute("token-value"));
      String str = ((Element)localObject).getAttribute("name");
      localObject = ((Element)localObject).getAttribute("prefix");
      paramElement.put(String.valueOf(j).toLowerCase(), new String[] { str, localObject });
      i += 1;
    }
    codepages[paramInt][4] = paramElement;
  }
  
  private void initializeAttributeNames(Element paramElement, int paramInt)
  {
    Hashtable localHashtable1 = new Hashtable();
    Hashtable localHashtable2 = new Hashtable();
    paramElement = paramElement.getElementsByTagName("attribute-start");
    int i = 0;
    if (i < paramElement.getLength())
    {
      Object localObject = (Element)paramElement.item(i);
      String str1 = ((Element)localObject).getAttribute("name");
      String str2 = ((Element)localObject).getAttribute("prefix");
      localHashtable1.put((str1 + str2).toLowerCase(), ((Element)localObject).getAttribute("token-value"));
      if (localHashtable2.containsKey(str1)) {
        ((Vector)localHashtable2.get(str1)).addElement(str2);
      }
      for (;;)
      {
        i += 1;
        break;
        localObject = new Vector();
        ((Vector)localObject).addElement(str2);
        localHashtable2.put(str1, localObject);
      }
    }
    codepages[paramInt][1] = localHashtable1;
    codepages[paramInt][2] = localHashtable2;
  }
  
  private void initializeAttributeValues(Element paramElement, int paramInt)
  {
    Hashtable localHashtable = new Hashtable();
    paramElement = paramElement.getElementsByTagName("attribute-value");
    int i = 0;
    while (i < paramElement.getLength())
    {
      Element localElement = (Element)paramElement.item(i);
      localHashtable.put(localElement.getAttribute("name").toLowerCase(), localElement.getAttribute("token-value"));
      i += 1;
    }
    codepages[paramInt][3] = localHashtable;
  }
  
  private void initializeHeaderInfo()
  {
    codepages = ((Hashtable[][])Array.newInstance(Hashtable.class, new int[] { tokenDoc.getElementsByTagName("codepage").getLength(), 5 }));
    int i = 0;
    while (i < codepages.length)
    {
      Element localElement = getCodepage(i);
      initializeTags(localElement, i);
      initializeAttributeNames(localElement, i);
      initializeAttributeNameTokens(localElement, i);
      initializeAttributeValues(localElement, i);
      i += 1;
    }
  }
  
  private void initializeTags(Element paramElement, int paramInt)
  {
    Hashtable localHashtable = new Hashtable();
    paramElement = paramElement.getElementsByTagName("tag");
    int i = 0;
    while (i < paramElement.getLength())
    {
      Element localElement = (Element)paramElement.item(i);
      localHashtable.put(localElement.getAttribute("name").toLowerCase(), localElement.getAttribute("token-value"));
      i += 1;
    }
    codepages[paramInt][0] = localHashtable;
  }
  
  private void initializeURNMappings()
  {
    urnMappings = new Properties();
    Object localObject5 = null;
    Object localObject1 = null;
    Object localObject3 = null;
    for (;;)
    {
      try
      {
        localInputStream = mContext.getAssets().open("jwap_port/urnCodepageMappings.properties");
        localObject3 = localInputStream;
        localObject5 = localInputStream;
        localObject1 = localInputStream;
        urnMappings.load(localInputStream);
      }
      catch (FileNotFoundException localFileNotFoundException2)
      {
        InputStream localInputStream;
        Object localObject2 = localObject3;
        localFileNotFoundException2.printStackTrace();
        if (localObject3 == null) {
          continue;
        }
        try
        {
          ((InputStream)localObject3).close();
          return;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          return;
        }
      }
      catch (IOException localIOException)
      {
        FileNotFoundException localFileNotFoundException1 = localFileNotFoundException2;
        localIOException.printStackTrace();
        if (localFileNotFoundException2 == null) {
          continue;
        }
        try
        {
          localFileNotFoundException2.close();
          return;
        }
        catch (Exception localException3)
        {
          localException3.printStackTrace();
          return;
        }
      }
      finally
      {
        if (localException3 == null) {
          break label117;
        }
      }
      try
      {
        localInputStream.close();
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        return;
      }
    }
    try
    {
      localException3.close();
      label117:
      throw ((Throwable)localObject4);
    }
    catch (Exception localException4)
    {
      for (;;)
      {
        localException4.printStackTrace();
      }
    }
  }
  
  public static void setCurrentCodepage(byte paramByte)
  {
    currentCodepage = paramByte;
  }
  
  public String[] getAttributeNameAndPrefix(byte paramByte)
  {
    return (String[])codepages[currentCodepage][4].get(String.valueOf(paramByte));
  }
  
  public String getAttributeValue(byte paramByte)
  {
    return getKeyFromValue(codepages[currentCodepage][3].entrySet().iterator(), paramByte);
  }
  
  public String getTagName(byte paramByte)
  {
    return getKeyFromValue(codepages[currentCodepage][0].entrySet().iterator(), paramByte);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.jwap_port.TokenRepository
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */