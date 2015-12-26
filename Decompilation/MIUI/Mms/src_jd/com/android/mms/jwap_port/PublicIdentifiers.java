package com.android.mms.jwap_port;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class PublicIdentifiers
{
  private static PublicIdentifiers instance;
  private Hashtable publicIdentifiers;
  private Hashtable systemIdentifiers;
  
  private PublicIdentifiers()
  {
    initialize();
  }
  
  public static PublicIdentifiers getInstance()
  {
    if (instance == null) {
      instance = new PublicIdentifiers();
    }
    return instance;
  }
  
  private String getKeyFromValue(Iterator paramIterator, String paramString)
  {
    while (paramIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramIterator.next();
      if (localEntry.getValue().toString().equalsIgnoreCase(paramString)) {
        return localEntry.getKey().toString();
      }
    }
    return "1";
  }
  
  private void initialize()
  {
    publicIdentifiers = new Hashtable();
    publicIdentifiers.put("0", "STRING_TABLE");
    publicIdentifiers.put("1", "UNKNOWN");
    publicIdentifiers.put("2", "-//WAPFORUM//DTD WML 1.0//EN");
    publicIdentifiers.put("3", "-//WAPFORUM//DTD WTA 1.0//EN");
    publicIdentifiers.put("4", "-//WAPFORUM//DTD WML 1.1//EN");
    publicIdentifiers.put("5", "-//WAPFORUM//DTD SI 1.0//EN");
    publicIdentifiers.put("6", "-//WAPFORUM//DTD SL 1.0//EN");
    publicIdentifiers.put("7", "-//WAPFORUM//DTD CO 1.0//EN");
    publicIdentifiers.put("8", "-//WAPFORUM//DTD CHANNEL 1.1//EN");
    publicIdentifiers.put("9", "-//WAPFORUM//DTD WML 1.2//EN");
    publicIdentifiers.put("A", "-//WAPFORUM//DTD WML 1.3//EN");
    publicIdentifiers.put("B", "-//WAPFORUM//DTD PROV 1.0//EN");
    publicIdentifiers.put("C", "-//WAPFORUM//DTD WTA-WML 1.2//EN");
    publicIdentifiers.put("D", "-//WAPFORUM//DTD CHANNEL 1.2//EN");
    publicIdentifiers.put("E", "-//OMA//DTD DRMREL 1.0//EN");
    publicIdentifiers.put("1100", "-//PHONE.COM//DTD ALERT 1.0//EN");
    publicIdentifiers.put("FD1", "-//SYNCML//DTD SyncML 1.0//EN");
    publicIdentifiers.put("FD2", "-//SYNCML//DTD DevInf 1.0//EN");
    publicIdentifiers.put("FD3", "-//SYNCML//DTD SyncML 1.1//EN");
    publicIdentifiers.put("FD4", "-//SYNCML//DTD DevInf 1.1//EN");
    systemIdentifiers = new Hashtable();
    systemIdentifiers.put("STRING_TABLE", "");
    systemIdentifiers.put("UNKNOWN", "");
    systemIdentifiers.put("-//WAPFORUM//DTD WML 1.0//EN", "");
    systemIdentifiers.put("-//WAPFORUM//DTD WTA 1.0//EN", "");
    systemIdentifiers.put("-//WAPFORUM//DTD WML 1.1//EN", "http://www.wapforum.org/DTD/wml_1_1.dtd");
    systemIdentifiers.put("-//WAPFORUM//DTD SI 1.0//EN", "http://www.wapforum.org/DTD/si.dtd");
    systemIdentifiers.put("-//WAPFORUM//DTD SL 1.0//EN", "http://www.wapforum.org/DTD/sl.dtd");
    systemIdentifiers.put("-//WAPFORUM//DTD CO 1.0//EN", "");
    systemIdentifiers.put("-//WAPFORUM//DTD CHANNEL 1.1//EN", "");
    systemIdentifiers.put("-//WAPFORUM//DTD WML 1.2//EN", "http://www.wapforum.org/DTD/wml12.dtd");
    systemIdentifiers.put("-//WAPFORUM//DTD WML 1.3//EN", "http://www.wapforum.org/DTD/wml13.dtd");
    systemIdentifiers.put("-//WAPFORUM//DTD PROV 1.0//EN", "http://www.wapforum.org/DTD/prov.dtd");
    systemIdentifiers.put("-//WAPFORUM//DTD WTA-WML 1.2//EN", "http://www.wapforum.org/DTD/wta-wml12.dtd");
    systemIdentifiers.put("-//WAPFORUM//DTD CHANNEL 1.2//EN", "http://www.wapforum.org/DTD/channel12.dtd");
    systemIdentifiers.put("-//OMA//DTD DRMREL 1.0//EN", "");
    systemIdentifiers.put("-//PHONE.COM//DTD ALERT 1.0//EN", "");
  }
  
  public String getPublicIdentifier(int paramInt)
  {
    Object localObject = Integer.toHexString(paramInt).toUpperCase();
    String str = (String)publicIdentifiers.get(localObject);
    localObject = str;
    if (str == null) {
      localObject = (String)publicIdentifiers.get("1");
    }
    return (String)localObject;
  }
  
  public String getPublicIdentifierValueHex(String paramString)
  {
    return getKeyFromValue(publicIdentifiers.entrySet().iterator(), paramString);
  }
  
  public String getSystemIdentifier(String paramString)
  {
    return (String)systemIdentifiers.get(paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.jwap_port.PublicIdentifiers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */