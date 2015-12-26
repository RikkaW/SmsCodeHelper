package com.meizu.i18n.phonenumbers.geocoding;

import com.meizu.i18n.phonenumbers.PhoneNumberUtil;
import com.meizu.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.meizu.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhoneNumberOfflineGeocoder
{
  private static final Logger LOGGER = Logger.getLogger(PhoneNumberOfflineGeocoder.class.getName());
  private static final String MAPPING_DATA_DIRECTORY = "/com/meizu/i18n/phonenumbers/geocoding/data/";
  private static PhoneNumberOfflineGeocoder instance = null;
  private Map<String, AreaCodeMap> availablePhonePrefixMaps = new HashMap();
  private MappingFileProvider mappingFileProvider = new MappingFileProvider();
  private final String phonePrefixDataDirectory;
  private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
  
  PhoneNumberOfflineGeocoder(String paramString)
  {
    phonePrefixDataDirectory = paramString;
    loadMappingFileProvider();
  }
  
  private boolean canBeGeocoded(PhoneNumberUtil.PhoneNumberType paramPhoneNumberType)
  {
    return (paramPhoneNumberType == PhoneNumberUtil.PhoneNumberType.FIXED_LINE) || (paramPhoneNumberType == PhoneNumberUtil.PhoneNumberType.MOBILE) || (paramPhoneNumberType == PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE);
  }
  
  private static void close(InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException paramInputStream)
    {
      LOGGER.log(Level.WARNING, paramInputStream.toString());
    }
  }
  
  private String getAreaDescriptionForNumber(Phonenumber.PhoneNumber paramPhoneNumber, String paramString1, String paramString2, String paramString3)
  {
    int j = paramPhoneNumber.getCountryCode();
    int i;
    if (j == 86)
    {
      String str = String.valueOf(paramPhoneNumber.getNationalNumber());
      if ((str.length() >= 7) && (str.charAt(0) == '1') && (str.charAt(1) != '0'))
      {
        i = Integer.valueOf(str.substring(0, 3)).intValue() + 86000;
        paramString2 = getPhonePrefixDescriptions(i, paramString1, paramString2, paramString3);
        if (paramString2 == null) {
          break label170;
        }
      }
    }
    label170:
    for (paramString2 = paramString2.lookup(paramPhoneNumber);; paramString2 = null)
    {
      if (((paramString2 != null) && (paramString2.length() != 0)) || (!mayFallBackToEnglish(paramString1))) {
        break label190;
      }
      paramString1 = getPhonePrefixDescriptions(i, "en", "", "");
      if (paramString1 != null) {
        break label175;
      }
      paramString1 = "";
      return paramString1;
      i = 860;
      break;
      i = j;
      if (j != 1) {
        break;
      }
      i = (int)(paramPhoneNumber.getNationalNumber() / 10000000L) + 1000;
      break;
    }
    label175:
    label190:
    for (paramPhoneNumber = paramString1.lookup(paramPhoneNumber);; paramPhoneNumber = paramString2)
    {
      paramString1 = paramPhoneNumber;
      if (paramPhoneNumber != null) {
        break;
      }
      return "";
    }
  }
  
  private String getCountryNameForNumber(Phonenumber.PhoneNumber paramPhoneNumber, Locale paramLocale)
  {
    return getRegionDisplayName(phoneUtil.getRegionCodeForNumber(paramPhoneNumber), paramLocale);
  }
  
  public static PhoneNumberOfflineGeocoder getInstance()
  {
    try
    {
      if (instance == null) {
        instance = new PhoneNumberOfflineGeocoder("/com/meizu/i18n/phonenumbers/geocoding/data/");
      }
      PhoneNumberOfflineGeocoder localPhoneNumberOfflineGeocoder = instance;
      return localPhoneNumberOfflineGeocoder;
    }
    finally {}
  }
  
  private AreaCodeMap getPhonePrefixDescriptions(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = mappingFileProvider.getFileName(paramInt, paramString1, paramString2, paramString3);
    if (paramString1.length() == 0) {
      return null;
    }
    if (!availablePhonePrefixMaps.containsKey(paramString1)) {
      loadAreaCodeMapFromFile(paramString1);
    }
    return (AreaCodeMap)availablePhonePrefixMaps.get(paramString1);
  }
  
  private String getRegionDisplayName(String paramString, Locale paramLocale)
  {
    if ((paramString == null) || (paramString.equals("ZZ")) || (paramString.equals("001"))) {
      return "";
    }
    return new Locale("", paramString).getDisplayCountry(paramLocale);
  }
  
  /* Error */
  private void loadAreaCodeMapFromFile(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: new 206	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   9: aload_0
    //   10: getfield 63	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:phonePrefixDataDirectory	Ljava/lang/String;
    //   13: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: aload_1
    //   17: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: invokevirtual 216	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   26: astore_2
    //   27: new 218	java/io/ObjectInputStream
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 220	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   35: astore_3
    //   36: aload_3
    //   37: astore_2
    //   38: new 145	com/meizu/i18n/phonenumbers/geocoding/AreaCodeMap
    //   41: dup
    //   42: invokespecial 221	com/meizu/i18n/phonenumbers/geocoding/AreaCodeMap:<init>	()V
    //   45: astore 4
    //   47: aload_3
    //   48: astore_2
    //   49: aload 4
    //   51: aload_3
    //   52: invokevirtual 225	com/meizu/i18n/phonenumbers/geocoding/AreaCodeMap:readExternal	(Ljava/io/ObjectInput;)V
    //   55: aload_3
    //   56: astore_2
    //   57: aload_0
    //   58: getfield 61	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:availablePhonePrefixMaps	Ljava/util/Map;
    //   61: aload_1
    //   62: aload 4
    //   64: invokeinterface 229 3 0
    //   69: pop
    //   70: aload_3
    //   71: invokestatic 231	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:close	(Ljava/io/InputStream;)V
    //   74: return
    //   75: astore 4
    //   77: aconst_null
    //   78: astore_1
    //   79: aload_1
    //   80: astore_2
    //   81: getstatic 38	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:LOGGER	Ljava/util/logging/Logger;
    //   84: getstatic 94	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   87: aload 4
    //   89: invokevirtual 97	java/io/IOException:toString	()Ljava/lang/String;
    //   92: invokevirtual 101	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   95: aload_1
    //   96: invokestatic 231	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:close	(Ljava/io/InputStream;)V
    //   99: return
    //   100: astore_1
    //   101: aconst_null
    //   102: astore_2
    //   103: aload_2
    //   104: invokestatic 231	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:close	(Ljava/io/InputStream;)V
    //   107: aload_1
    //   108: athrow
    //   109: astore_1
    //   110: goto -7 -> 103
    //   113: astore 4
    //   115: aload_3
    //   116: astore_1
    //   117: goto -38 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	this	PhoneNumberOfflineGeocoder
    //   0	120	1	paramString	String
    //   26	78	2	localObject	Object
    //   35	81	3	localObjectInputStream	java.io.ObjectInputStream
    //   45	18	4	localAreaCodeMap	AreaCodeMap
    //   75	13	4	localIOException1	IOException
    //   113	1	4	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   27	36	75	java/io/IOException
    //   27	36	100	finally
    //   38	47	109	finally
    //   49	55	109	finally
    //   57	70	109	finally
    //   81	95	109	finally
    //   38	47	113	java/io/IOException
    //   49	55	113	java/io/IOException
    //   57	70	113	java/io/IOException
  }
  
  /* Error */
  private void loadMappingFileProvider()
  {
    // Byte code:
    //   0: ldc 2
    //   2: new 206	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   9: aload_0
    //   10: getfield 63	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:phonePrefixDataDirectory	Ljava/lang/String;
    //   13: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: ldc -23
    //   18: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: invokevirtual 216	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   27: astore_1
    //   28: new 218	java/io/ObjectInputStream
    //   31: dup
    //   32: aload_1
    //   33: invokespecial 220	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   36: astore_2
    //   37: aload_2
    //   38: astore_1
    //   39: aload_0
    //   40: getfield 56	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:mappingFileProvider	Lcom/meizu/i18n/phonenumbers/geocoding/MappingFileProvider;
    //   43: aload_2
    //   44: invokevirtual 234	com/meizu/i18n/phonenumbers/geocoding/MappingFileProvider:readExternal	(Ljava/io/ObjectInput;)V
    //   47: aload_2
    //   48: invokestatic 231	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:close	(Ljava/io/InputStream;)V
    //   51: return
    //   52: astore_3
    //   53: aconst_null
    //   54: astore_2
    //   55: aload_2
    //   56: astore_1
    //   57: getstatic 38	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:LOGGER	Ljava/util/logging/Logger;
    //   60: getstatic 94	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   63: aload_3
    //   64: invokevirtual 97	java/io/IOException:toString	()Ljava/lang/String;
    //   67: invokevirtual 101	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   70: aload_2
    //   71: invokestatic 231	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:close	(Ljava/io/InputStream;)V
    //   74: return
    //   75: astore_2
    //   76: aconst_null
    //   77: astore_1
    //   78: aload_1
    //   79: invokestatic 231	com/meizu/i18n/phonenumbers/geocoding/PhoneNumberOfflineGeocoder:close	(Ljava/io/InputStream;)V
    //   82: aload_2
    //   83: athrow
    //   84: astore_2
    //   85: goto -7 -> 78
    //   88: astore_3
    //   89: goto -34 -> 55
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	PhoneNumberOfflineGeocoder
    //   27	52	1	localObject1	Object
    //   36	35	2	localObjectInputStream	java.io.ObjectInputStream
    //   75	8	2	localObject2	Object
    //   84	1	2	localObject3	Object
    //   52	12	3	localIOException1	IOException
    //   88	1	3	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   28	37	52	java/io/IOException
    //   28	37	75	finally
    //   39	47	84	finally
    //   57	70	84	finally
    //   39	47	88	java/io/IOException
  }
  
  private boolean mayFallBackToEnglish(String paramString)
  {
    return (!paramString.equals("zh")) && (!paramString.equals("ja")) && (!paramString.equals("ko"));
  }
  
  public String getDescriptionForNumber(Phonenumber.PhoneNumber paramPhoneNumber, Locale paramLocale)
  {
    PhoneNumberUtil.PhoneNumberType localPhoneNumberType = phoneUtil.getNumberType(paramPhoneNumber);
    if (localPhoneNumberType == PhoneNumberUtil.PhoneNumberType.UNKNOWN) {
      return "";
    }
    if (!canBeGeocoded(localPhoneNumberType)) {
      return getCountryNameForNumber(paramPhoneNumber, paramLocale);
    }
    return getDescriptionForValidNumber(paramPhoneNumber, paramLocale);
  }
  
  public String getDescriptionForNumber(Phonenumber.PhoneNumber paramPhoneNumber, Locale paramLocale, String paramString)
  {
    PhoneNumberUtil.PhoneNumberType localPhoneNumberType = phoneUtil.getNumberType(paramPhoneNumber);
    if (localPhoneNumberType == PhoneNumberUtil.PhoneNumberType.UNKNOWN) {
      return "";
    }
    if (!canBeGeocoded(localPhoneNumberType)) {
      return getCountryNameForNumber(paramPhoneNumber, paramLocale);
    }
    return getDescriptionForValidNumber(paramPhoneNumber, paramLocale, paramString);
  }
  
  public String getDescriptionForValidNumber(Phonenumber.PhoneNumber paramPhoneNumber, Locale paramLocale)
  {
    String str = getAreaDescriptionForNumber(paramPhoneNumber, paramLocale.getLanguage(), "", paramLocale.getCountry());
    if (str.length() > 0) {
      return str;
    }
    return getCountryNameForNumber(paramPhoneNumber, paramLocale);
  }
  
  public String getDescriptionForValidNumber(Phonenumber.PhoneNumber paramPhoneNumber, Locale paramLocale, String paramString)
  {
    return getDescriptionForValidNumber(paramPhoneNumber, paramLocale, paramString, false);
  }
  
  public String getDescriptionForValidNumber(Phonenumber.PhoneNumber paramPhoneNumber, Locale paramLocale, String paramString, boolean paramBoolean)
  {
    String str1 = phoneUtil.getRegionCodeForNumber(paramPhoneNumber);
    if (paramString.equals(str1)) {
      return getDescriptionForValidNumber(paramPhoneNumber, paramLocale);
    }
    if (paramBoolean)
    {
      paramString = paramLocale.getLanguage();
      paramPhoneNumber = getAreaDescriptionForNumber(paramPhoneNumber, paramString, "", paramLocale.getCountry());
      if ((paramPhoneNumber != null) && (!paramPhoneNumber.equals("")))
      {
        String str2 = getRegionDisplayName(str1, paramLocale);
        if ((str2 != null) && (!str2.equals("")))
        {
          if ((paramString.equals("zh")) || (paramString.equals("ja")) || (paramString.equals("ko"))) {
            return str2 + " " + paramPhoneNumber;
          }
          return paramPhoneNumber + " " + str2;
        }
      }
    }
    return getRegionDisplayName(str1, paramLocale);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */