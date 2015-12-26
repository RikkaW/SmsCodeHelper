package com.meizu.i18n.phonenumbers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

class MetadataManager
{
  private static final String ALTERNATE_FORMATS_FILE_PREFIX = "/com/meizu/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto";
  private static final Logger LOGGER = Logger.getLogger(MetadataManager.class.getName());
  private static final Map<Integer, Phonemetadata.PhoneMetadata> callingCodeToAlternateFormatsMap = Collections.synchronizedMap(new HashMap());
  private static final Set<Integer> countryCodeSet = AlternateFormatsCountryCodeSet.getCountryCodeSet();
  
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
  
  static Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int paramInt)
  {
    if (!countryCodeSet.contains(Integer.valueOf(paramInt))) {
      return null;
    }
    synchronized (callingCodeToAlternateFormatsMap)
    {
      if (!callingCodeToAlternateFormatsMap.containsKey(Integer.valueOf(paramInt))) {
        loadMetadataFromFile(paramInt);
      }
      return (Phonemetadata.PhoneMetadata)callingCodeToAlternateFormatsMap.get(Integer.valueOf(paramInt));
    }
  }
  
  /* Error */
  private static void loadMetadataFromFile(int paramInt)
  {
    // Byte code:
    //   0: ldc 107
    //   2: new 109	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 110	java/lang/StringBuilder:<init>	()V
    //   9: ldc 112
    //   11: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: iload_0
    //   15: invokevirtual 119	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   18: invokevirtual 120	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: invokevirtual 124	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   24: astore_1
    //   25: new 126	java/io/ObjectInputStream
    //   28: dup
    //   29: aload_1
    //   30: invokespecial 128	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   33: astore_2
    //   34: aload_2
    //   35: astore_1
    //   36: new 130	com/meizu/i18n/phonenumbers/Phonemetadata$PhoneMetadataCollection
    //   39: dup
    //   40: invokespecial 131	com/meizu/i18n/phonenumbers/Phonemetadata$PhoneMetadataCollection:<init>	()V
    //   43: astore_3
    //   44: aload_2
    //   45: astore_1
    //   46: aload_3
    //   47: aload_2
    //   48: invokevirtual 135	com/meizu/i18n/phonenumbers/Phonemetadata$PhoneMetadataCollection:readExternal	(Ljava/io/ObjectInput;)V
    //   51: aload_2
    //   52: astore_1
    //   53: aload_3
    //   54: invokevirtual 139	com/meizu/i18n/phonenumbers/Phonemetadata$PhoneMetadataCollection:getMetadataList	()Ljava/util/List;
    //   57: invokeinterface 145 1 0
    //   62: astore_3
    //   63: aload_2
    //   64: astore_1
    //   65: aload_3
    //   66: invokeinterface 151 1 0
    //   71: ifeq +61 -> 132
    //   74: aload_2
    //   75: astore_1
    //   76: aload_3
    //   77: invokeinterface 155 1 0
    //   82: checkcast 105	com/meizu/i18n/phonenumbers/Phonemetadata$PhoneMetadata
    //   85: astore 4
    //   87: aload_2
    //   88: astore_1
    //   89: getstatic 45	com/meizu/i18n/phonenumbers/MetadataManager:callingCodeToAlternateFormatsMap	Ljava/util/Map;
    //   92: aload 4
    //   94: invokevirtual 159	com/meizu/i18n/phonenumbers/Phonemetadata$PhoneMetadata:getCountryCode	()I
    //   97: invokestatic 84	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   100: aload 4
    //   102: invokeinterface 163 3 0
    //   107: pop
    //   108: goto -45 -> 63
    //   111: astore_3
    //   112: aload_2
    //   113: astore_1
    //   114: getstatic 32	com/meizu/i18n/phonenumbers/MetadataManager:LOGGER	Ljava/util/logging/Logger;
    //   117: getstatic 69	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   120: aload_3
    //   121: invokevirtual 72	java/io/IOException:toString	()Ljava/lang/String;
    //   124: invokevirtual 76	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   127: aload_2
    //   128: invokestatic 165	com/meizu/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   131: return
    //   132: aload_2
    //   133: invokestatic 165	com/meizu/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   136: return
    //   137: astore_2
    //   138: aconst_null
    //   139: astore_1
    //   140: aload_1
    //   141: invokestatic 165	com/meizu/i18n/phonenumbers/MetadataManager:close	(Ljava/io/InputStream;)V
    //   144: aload_2
    //   145: athrow
    //   146: astore_2
    //   147: goto -7 -> 140
    //   150: astore_3
    //   151: aconst_null
    //   152: astore_2
    //   153: goto -41 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	paramInt	int
    //   24	117	1	localObject1	Object
    //   33	100	2	localObjectInputStream	java.io.ObjectInputStream
    //   137	8	2	localObject2	Object
    //   146	1	2	localObject3	Object
    //   152	1	2	localObject4	Object
    //   43	34	3	localObject5	Object
    //   111	10	3	localIOException1	IOException
    //   150	1	3	localIOException2	IOException
    //   85	16	4	localPhoneMetadata	Phonemetadata.PhoneMetadata
    // Exception table:
    //   from	to	target	type
    //   36	44	111	java/io/IOException
    //   46	51	111	java/io/IOException
    //   53	63	111	java/io/IOException
    //   65	74	111	java/io/IOException
    //   76	87	111	java/io/IOException
    //   89	108	111	java/io/IOException
    //   25	34	137	finally
    //   36	44	146	finally
    //   46	51	146	finally
    //   53	63	146	finally
    //   65	74	146	finally
    //   76	87	146	finally
    //   89	108	146	finally
    //   114	127	146	finally
    //   25	34	150	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.MetadataManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */