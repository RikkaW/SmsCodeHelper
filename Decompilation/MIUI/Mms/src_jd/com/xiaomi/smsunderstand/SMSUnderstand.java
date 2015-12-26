package com.xiaomi.smsunderstand;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.ConfigProcess;
import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.common.StringProcess.ASCType;
import com.xiaomi.common.StringString;
import com.xiaomi.nlp.OntologyActionManagement;
import com.xiaomi.nlp.OntologyTaskFrame;
import com.xiaomi.nlp.OntologyTaskManagement;
import com.xiaomi.nlp.Parser;
import com.xiaomi.smsunderstand.task.BankCardNumberRecognition;
import com.xiaomi.smsunderstand.task.ChongzhiRecognition;
import com.xiaomi.smsunderstand.task.CreditCardHuankuanRecognition;
import com.xiaomi.smsunderstand.task.DateTimeRecognition;
import com.xiaomi.smsunderstand.task.Express;
import com.xiaomi.smsunderstand.task.ExpressNumberRecognition;
import com.xiaomi.smsunderstand.task.FlowRecognition;
import com.xiaomi.smsunderstand.task.MoneyRecognition;
import com.xiaomi.smsunderstand.task.PhoneNumberRecognition;
import com.xiaomi.smsunderstand.task.RealNumberRecognition;
import com.xiaomi.smsunderstand.task.SpecialEntityRecognition;
import com.xiaomi.smsunderstand.task.TimeSpanRecognition;
import com.xiaomi.smsunderstand.task.URLRecognition;
import com.xiaomi.smsunderstand.task.VerificationCodeRecognition;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class SMSUnderstand
{
  private static int NumberMinLength;
  private static int SMScontentMinLength = 4;
  private static ACAutomation acPhoneNumberPrefix2Domain;
  private static OntologyActionManagement actionOntology;
  private static String cleanNumberRegex = "(\\\\n)|(\\\\r)|(\\\\t)";
  private static String configPath;
  private static HashSet<String> danweiWords;
  private static String danweiWordsFileName;
  private static int danweiWordsMaxLength;
  public static String dictionaryPath;
  private static boolean ifInitial;
  private static String localHostPhoneNumber;
  private static String localHostPhoneNumber1;
  private static String localHostPhoneNumber2;
  private static String localHostPlace;
  private static String localHostPlace1;
  private static String localHostPlace2;
  private static List<StringInt> phoneNumberPrefix2Domain;
  private static int platform;
  private static HashSet<EntityType> residentTasksIntialResource;
  private static List<String> resourceFromSpecialEntity;
  private static boolean resourceInJar;
  private static Object sNumberLockObject;
  private static Object sPlaceLockObject;
  private static SMSDic2Pattern smsDic2Pattern;
  private static int systemLevel;
  private static OntologyTaskManagement taskOntology;
  private static HashSet<EntityType> tasksIntialResource;
  private String SMScontent;
  private String SMScontent_LowerCase;
  private List<EntityInfo> entityInfos = new ArrayList();
  private String expressName;
  private String fromPhoneNumber = null;
  private String fromPhoneNumberPlace = null;
  private boolean isMobileNo = true;
  private BitSet recognitionTask = new BitSet(100);
  private Set<EntityType> recognizeEntities;
  private long smsTime = -1L;
  private HashSet<String> specialEntityPats;
  
  static
  {
    NumberMinLength = 3;
    ifInitial = false;
    danweiWords = new HashSet();
    danweiWordsMaxLength = -1;
    dictionaryPath = null;
    danweiWordsFileName = null;
    configPath = "smsUnderstand.config";
    sNumberLockObject = new Object();
    sPlaceLockObject = new Object();
    localHostPhoneNumber = null;
    localHostPlace = null;
    localHostPhoneNumber1 = null;
    localHostPlace1 = null;
    localHostPhoneNumber2 = null;
    localHostPlace2 = null;
    taskOntology = new OntologyTaskManagement();
    actionOntology = new OntologyActionManagement();
    platform = 7;
    systemLevel = 10;
    resourceInJar = false;
    tasksIntialResource = new HashSet();
    residentTasksIntialResource = new HashSet();
  }
  
  public SMSUnderstand(String paramString1, String paramString2)
  {
    long l1 = System.currentTimeMillis();
    initialWithNoPara();
    SMScontent = null;
    SMScontent_LowerCase = null;
    String str = paramString1;
    if (paramString1 == null) {
      str = "";
    }
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    fromPhoneNumber = PhoneNumberRecognition.nomalPhoneNumber(str);
    fromPhoneNumberPlace = paramString1;
    recognizeEntities = null;
    resourceFromSpecialEntity = null;
    try
    {
      isMobileNo = addTask(fromPhoneNumber, fromPhoneNumberPlace);
      long l2 = System.currentTimeMillis();
      Log.i("Time", "initial:" + (l2 - l1));
      return;
    }
    catch (Exception paramString2)
    {
      Log.i("NumberRecognition", str + "\t" + paramString1);
    }
  }
  
  public SMSUnderstand(String paramString1, String paramString2, Set<EntityType> paramSet)
  {
    long l1 = System.currentTimeMillis();
    initialWithNoPara();
    SMScontent = null;
    SMScontent_LowerCase = null;
    fromPhoneNumber = PhoneNumberRecognition.nomalPhoneNumber(paramString1);
    fromPhoneNumberPlace = paramString2;
    recognizeEntities = paramSet;
    try
    {
      isMobileNo = addTask(fromPhoneNumber, fromPhoneNumberPlace);
      long l2 = System.currentTimeMillis();
      Log.i("Time", "initial:" + (l2 - l1));
      return;
    }
    catch (Exception paramSet)
    {
      Log.i("NumberRecognition", paramString1 + "\t" + paramString2);
    }
  }
  
  private void addRecognitionTask(EntityType paramEntityType)
  {
    recognitionTask.set(paramEntityType.ordinal(), true);
    intialResource(paramEntityType);
  }
  
  private boolean addTask(String paramString1, String paramString2)
  {
    paramString2 = new ArrayList(10);
    boolean bool = getTask(paramString1, paramString1, paramString2);
    int i;
    if ((recognizeEntities == null) || (recognizeEntities.size() == 0))
    {
      i = 0;
      if (i < paramString2.size()) {}
    }
    for (;;)
    {
      return bool;
      addRecognitionTask((EntityType)paramString2.get(i));
      i += 1;
      break;
      i = 0;
      while (i < paramString2.size())
      {
        if (recognizeEntities.contains(paramString2.get(i))) {
          addRecognitionTask((EntityType)paramString2.get(i));
        }
        i += 1;
      }
    }
  }
  
  public static void freeAllResource()
  {
    Iterator localIterator = tasksIntialResource.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        tasksIntialResource.clear();
        residentTasksIntialResource.clear();
        return;
      }
      freeResource((EntityType)localIterator.next());
    }
  }
  
  public static void freeOntology()
  {
    taskOntology = new OntologyTaskManagement();
    actionOntology = new OntologyActionManagement();
    freeAllResource();
    System.gc();
    ifInitial = false;
  }
  
  private static void freeResource(EntityType paramEntityType)
  {
    switch (paramEntityType)
    {
    case ENG: 
    case EXPRESSNAME: 
    case EXPRESSNUMBER: 
    case FLOW: 
    case PHONENUMBER: 
    case TIMESPAN: 
    default: 
      return;
    case TIME: 
      BankCardNumberRecognition.freeResource();
      return;
    case NUMBER: 
      ChongzhiRecognition.freeResource();
      return;
    case TOPUP: 
      ExpressNumberRecognition.freeResource();
      return;
    case VERIFICATIONCODE: 
      DateTimeRecognition.freeResource();
      return;
    case REALNUMBER: 
      TimeSpanRecognition.freeResource();
      return;
    case SPECIALENTITY: 
      MoneyRecognition.freeResource();
      return;
    case RESPONSE: 
      FlowRecognition.freeResource();
      return;
    case HUMAN_NAME: 
      CreditCardHuankuanRecognition.freeResource();
      return;
    case UNKNOWN: 
      PhoneNumberRecognition.freeResource();
      return;
    case URL: 
      URLRecognition.freeResource();
      return;
    case MONEY: 
      PhoneNumberRecognition.freeResource();
      return;
    case XIAOMICAIPIAO: 
      VerificationCodeRecognition.freeResource();
      return;
    }
    SpecialEntityRecognition.freeResource();
  }
  
  public static void freeResource(String paramString1, String paramString2)
  {
    Object localObject = new ArrayList(10);
    getTask(paramString1, paramString2, (List)localObject);
    paramString2 = ((List)localObject).iterator();
    if (!paramString2.hasNext())
    {
      SpecialEntityRecognition.freeParseHard();
      getResourceFromSpecialEntity(paramString1);
      if (resourceFromSpecialEntity != null) {
        paramString1 = resourceFromSpecialEntity.iterator();
      }
    }
    for (;;)
    {
      if (!paramString1.hasNext())
      {
        return;
        localObject = (EntityType)paramString2.next();
        if (residentTasksIntialResource.contains(localObject)) {
          break;
        }
        freeResource((EntityType)localObject);
        if (!tasksIntialResource.contains(localObject)) {
          break;
        }
        tasksIntialResource.remove(localObject);
        break;
      }
      SpecialEntityRecognition.freeParseHard((String)paramString1.next());
    }
  }
  
  public static int getActionCount(int paramInt1, int paramInt2)
  {
    return actionOntology.getActionCount(paramInt1, paramInt2);
  }
  
  public static String getBtnAction(int paramInt1, int paramInt2, int paramInt3)
  {
    return actionOntology.getBtnAction(paramInt1, paramInt2, paramInt3);
  }
  
  public static int getBtnNumber(int paramInt)
  {
    return actionOntology.getBtnNumber(paramInt);
  }
  
  public static String getBtnTitle(int paramInt1, int paramInt2)
  {
    return actionOntology.getBtnTitle(paramInt1, paramInt2);
  }
  
  public static int getDomainByPhoneNumber(String paramString)
  {
    if (acPhoneNumberPrefix2Domain == null) {}
    do
    {
      return -1;
      paramString = acPhoneNumberPrefix2Domain.startWith(paramString, 0);
    } while ((paramString == null) || (paramString.size() == 0));
    return ((StringInt)phoneNumberPrefix2Domain.get(((int[])paramString.get(0))[0])).getNum();
  }
  
  public static String getLocalHostPhoneNumber()
  {
    synchronized (sNumberLockObject)
    {
      String str = localHostPhoneNumber;
      return str;
    }
  }
  
  public static String getLocalHostPlace()
  {
    synchronized (sPlaceLockObject)
    {
      String str = localHostPlace;
      return str;
    }
  }
  
  public static int getPlatform()
  {
    return platform;
  }
  
  public static boolean getResourceFromSpecialEntity(String paramString)
  {
    if (smsDic2Pattern == null) {
      return false;
    }
    if (resourceFromSpecialEntity == null) {
      resourceFromSpecialEntity = smsDic2Pattern.match(paramString, getLocalHostPlace(), "");
    }
    return true;
  }
  
  public static List<String> getResourceFromSpecialEntityReturn(String paramString)
  {
    if (smsDic2Pattern == null) {
      return null;
    }
    return smsDic2Pattern.match(paramString, getLocalHostPlace(), "");
  }
  
  public static int getSystemLevel()
  {
    return systemLevel;
  }
  
  private static boolean getTask(String paramString1, String paramString2, List<EntityType> paramList)
  {
    boolean bool = PhoneNumberRecognition.ifMobileNo(paramString1);
    if (bool)
    {
      paramList.add(EntityType.URL);
      paramList.add(EntityType.BANKCARDNUMBER);
      paramList.add(EntityType.VERIFICATIONCODE);
      paramList.add(EntityType.EXPRESSNUMBER);
      paramList.add(EntityType.PHONENUMBER);
      paramList.add(EntityType.TIME);
      return bool;
    }
    switch (getDomainByPhoneNumber(paramString1))
    {
    default: 
      paramList.add(EntityType.VERIFICATIONCODE);
      paramList.add(EntityType.EXPRESSNUMBER);
      paramList.add(EntityType.MONEY);
      paramList.add(EntityType.RESPONSE);
      paramList.add(EntityType.PHONENUMBER);
      paramList.add(EntityType.TIME);
      paramList.add(EntityType.URL);
      return bool;
    case 0: 
    case 1: 
    case 2: 
      paramList.add(EntityType.URL);
      paramList.add(EntityType.TOPUP);
      paramList.add(EntityType.FLOW);
      paramList.add(EntityType.RESPONSE);
      paramList.add(EntityType.PHONENUMBER);
      paramList.add(EntityType.TIME);
      paramList.add(EntityType.VERIFICATIONCODE);
      return bool;
    }
    paramList.add(EntityType.TIME);
    paramList.add(EntityType.URL);
    paramList.add(EntityType.VERIFICATIONCODE);
    return bool;
  }
  
  private static String getValueBySummation(String paramString1, double paramDouble, String paramString2)
  {
    int i = 0;
    if (paramString1 == null) {
      return paramString2 + ":" + paramDouble;
    }
    String[] arrayOfString = paramString1.split("[:#]");
    if (arrayOfString.length % 2 != 0)
    {
      Log.i("Time", paramString1);
      Log.println(paramString1);
      return null;
    }
    paramString1 = new StringBuffer();
    int j = 0;
    if (i >= arrayOfString.length)
    {
      if (j == 0) {
        paramString1.append(paramString2).append(":").append(paramDouble).append("#");
      }
      paramString1.deleteCharAt(paramString1.length() - 1);
      return paramString1.toString();
    }
    if ((j != 0) || (!arrayOfString[i].equals(paramString2))) {
      paramString1.append(arrayOfString[i]).append(":").append(arrayOfString[(i + 1)]).append("#");
    }
    for (;;)
    {
      i += 2;
      break;
      paramString1.append(arrayOfString[i]).append(":").append(Double.valueOf(arrayOfString[(i + 1)]).doubleValue() + paramDouble).append("#");
      j = 1;
    }
  }
  
  public static String getVersion()
  {
    return "2.1.4";
  }
  
  public static boolean initial()
  {
    setLocalHostPhoneNumber("");
    setLocalHostPlace("");
    if (!resourceInJar) {}
    for (dictionaryPath = "/data/data/com.android.mms/app_understand/smsUnderstandDic";; dictionaryPath = "smsUnderstandDic") {
      return initialWithNoPara();
    }
  }
  
  public static boolean initial(String paramString)
  {
    configPath = paramString;
    return initialWithNoPara();
  }
  
  private static boolean initialBodyKeyword2Pattern()
  {
    try
    {
      smsDic2Pattern = new SMSDic2Pattern(dictionaryPath + "/patterns/Keyword2Pattern.txt");
      if (smsDic2Pattern.getPatternSize() == 0)
      {
        smsDic2Pattern = null;
        return false;
      }
    }
    catch (Exception localException)
    {
      smsDic2Pattern = null;
      return false;
    }
    return true;
  }
  
  private static boolean initialWithNoPara()
  {
    if (ifInitial) {
      return true;
    }
    Object localObject;
    if (!resourceInJar)
    {
      localObject = ConfigProcess.readConfig(configPath);
      if (localObject != null) {
        break label63;
      }
      Log.i("NumberRecognition", "Config Read Error!!!");
    }
    while ((dictionaryPath == null) || (dictionaryPath.equals("")))
    {
      Log.i("NumberRecognition", "dictionaryDir No find!!!");
      return false;
      label63:
      dictionaryPath = (String)((HashMap)localObject).get("dictionaryDir");
      String str = (String)((HashMap)localObject).get("parserTimeout");
      if (str != null) {
        Parser.setTimeOutThr(Integer.valueOf(str).intValue());
      }
      localObject = (String)((HashMap)localObject).get("localTest");
      if (localObject != null) {
        Log.setLocalTest(Boolean.valueOf((String)localObject).booleanValue());
      }
    }
    if (!taskOntology.loadOntology(dictionaryPath))
    {
      Log.i("NumberRecognition", "Load Task Ontology Error!!!");
      return false;
    }
    if (!actionOntology.loadOntology(dictionaryPath))
    {
      Log.i("NumberRecognition", "Load Action Ontology Error!!!");
      return false;
    }
    if (!readPhoneNumberPrefix2Domain())
    {
      Log.i("NumberRecognition", "readPhoneNumberPrefix2Domain Error!!!");
      return false;
    }
    danweiWordsFileName = dictionaryPath + "/qua_unit.txt";
    if (!initialBodyKeyword2Pattern())
    {
      Log.i("NumberRecognition", "initialBodyKeyword2Pattern Error!!!");
      return false;
    }
    intialResource(EntityType.PHONENUMBER);
    intialResource(EntityType.URL);
    intialResource(EntityType.VERIFICATIONCODE);
    intialResource(EntityType.TIME);
    intialResource(EntityType.EXPRESSNUMBER);
    intialResource(EntityType.SPECIALENTITY);
    residentTasksIntialResource.add(EntityType.PHONENUMBER);
    residentTasksIntialResource.add(EntityType.URL);
    residentTasksIntialResource.add(EntityType.VERIFICATIONCODE);
    residentTasksIntialResource.add(EntityType.EXPRESSNUMBER);
    residentTasksIntialResource.add(EntityType.SPECIALENTITY);
    danweiWordsMaxLength = 0;
    ifInitial = true;
    return true;
  }
  
  private static void intialResource(EntityType paramEntityType)
  {
    for (;;)
    {
      try
      {
        switch (paramEntityType)
        {
        case ENG: 
        case EXPRESSNAME: 
        case EXPRESSNUMBER: 
        case FLOW: 
        case PHONENUMBER: 
        case TIMESPAN: 
          tasksIntialResource.add(paramEntityType);
          return;
        }
      }
      catch (Exception paramEntityType)
      {
        Log.i("NumberRecognition", paramEntityType.getMessage());
        return;
      }
      BankCardNumberRecognition.initial();
      continue;
      ChongzhiRecognition.initial();
      continue;
      ExpressNumberRecognition.initial();
      continue;
      DateTimeRecognition.initial();
      continue;
      TimeSpanRecognition.initial();
      continue;
      MoneyRecognition.initial();
      continue;
      FlowRecognition.initial();
      continue;
      CreditCardHuankuanRecognition.initial();
      continue;
      URLRecognition.initial();
      continue;
      PhoneNumberRecognition.initial();
      continue;
      PhoneNumberRecognition.initial();
      continue;
      VerificationCodeRecognition.initial();
      continue;
      SpecialEntityRecognition.initial();
    }
  }
  
  public static boolean isResourceInJar()
  {
    return resourceInJar;
  }
  
  private boolean isSentenceNoChinese(String paramString)
  {
    if (paramString.contains("ems")) {}
    int j;
    int k;
    do
    {
      return false;
      j = 0;
      k = 0;
      if (j < paramString.length()) {
        break;
      }
    } while (k / paramString.length() < 0.9D);
    return true;
    int m = paramString.charAt(j);
    int i;
    if ((m >= 48) && (m <= 57)) {
      i = k + 1;
    }
    for (;;)
    {
      j += 1;
      k = i;
      break;
      if ((m >= 97) && (m <= 122))
      {
        i = k + 1;
      }
      else if ((m >= 65) && (m <= 90))
      {
        i = k + 1;
      }
      else if ((m != 46) && (m != 32) && (m != 63))
      {
        i = k;
        if (m != 33) {}
      }
      else
      {
        i = k + 1;
      }
    }
  }
  
  public static boolean loadResourceForResident(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList(10);
    String str = paramString1;
    if (paramString1 == null) {
      str = "";
    }
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    paramString2 = PhoneNumberRecognition.nomalPhoneNumber(str);
    getTask(paramString2, paramString1, localArrayList);
    if (localArrayList.size() == 0) {
      return false;
    }
    long l1 = System.currentTimeMillis();
    int i = 0;
    long l2;
    if (i >= localArrayList.size())
    {
      l2 = System.currentTimeMillis();
      Log.i("Time", "loadResourceForResident " + (l2 - l1) + " number: " + paramString2);
      paramString1 = getResourceFromSpecialEntityReturn(paramString2);
      if (paramString1 != null) {
        paramString1 = paramString1.iterator();
      }
    }
    for (;;)
    {
      if (!paramString1.hasNext())
      {
        return true;
        l2 = System.currentTimeMillis();
        intialResource((EntityType)localArrayList.get(i));
        residentTasksIntialResource.add((EntityType)localArrayList.get(i));
        long l3 = System.currentTimeMillis();
        Log.i("Time", "loadResourceForResident " + (l3 - l2) + " task: " + ((EntityType)localArrayList.get(i)).toString());
        i += 1;
        break;
      }
      SpecialEntityRecognition.loadParse((String)paramString1.next());
    }
  }
  
  private void normalizationOnlyOne()
  {
    int i3 = 0;
    int i = 0;
    int j = -1;
    int m = -1;
    double d2 = 0.0D;
    int k = -1;
    double d1 = 0.0D;
    if (i >= entityInfos.size())
    {
      i = i3;
      if (i < entityInfos.size()) {}
    }
    else
    {
      int n;
      int i1;
      double d3;
      int i2;
      double d4;
      if (((EntityInfo)entityInfos.get(i)).getEntityType() == EntityType.EXPRESSNUMBER) {
        if (((EntityInfo)entityInfos.get(i)).getConfidence() <= d1)
        {
          n = j;
          i1 = m;
          d3 = d2;
          i2 = k;
          d4 = d1;
          if (i != -1) {}
        }
        else
        {
          d4 = ((EntityInfo)entityInfos.get(i)).getConfidence();
          i2 = i;
          d3 = d2;
          i1 = m;
          n = j;
        }
      }
      for (;;)
      {
        i += 1;
        j = n;
        m = i1;
        d2 = d3;
        k = i2;
        d1 = d4;
        break;
        n = j;
        i1 = m;
        d3 = d2;
        i2 = k;
        d4 = d1;
        if (((EntityInfo)entityInfos.get(i)).getEntityType() == EntityType.VERIFICATIONCODE)
        {
          if (((EntityInfo)entityInfos.get(i)).getRemark().contains("::=")) {
            j = i;
          }
          if (((EntityInfo)entityInfos.get(i)).getConfidence() <= d2)
          {
            n = j;
            i1 = m;
            d3 = d2;
            i2 = k;
            d4 = d1;
            if (i != j) {}
          }
          else
          {
            d3 = ((EntityInfo)entityInfos.get(i)).getConfidence();
            i1 = i;
            n = j;
            i2 = k;
            d4 = d1;
          }
        }
      }
    }
    if (((EntityInfo)entityInfos.get(i)).getEntityType() == EntityType.EXPRESSNUMBER) {
      if (i != k)
      {
        ((EntityInfo)entityInfos.get(i)).setEntityType(EntityType.DROP);
        ((EntityInfo)entityInfos.get(i)).setConfidence(0.0D);
      }
    }
    for (;;)
    {
      i += 1;
      break;
      if ((((EntityInfo)entityInfos.get(i)).getEntityType() == EntityType.VERIFICATIONCODE) && (i != m))
      {
        ((EntityInfo)entityInfos.get(i)).setEntityType(EntityType.DROP);
        ((EntityInfo)entityInfos.get(i)).setConfidence(0.0D);
      }
    }
  }
  
  private void normalizationResults()
  {
    int k = -1;
    int i = 0;
    if (i >= entityInfos.size()) {
      return;
    }
    int j;
    if (((EntityInfo)entityInfos.get(i)).getEntityType() == EntityType.DROP) {
      j = k;
    }
    for (;;)
    {
      i += 1;
      k = j;
      break;
      if (k < 0) {
        j = i;
      } else if ((((EntityInfo)entityInfos.get(i)).getStartPosition() >= ((EntityInfo)entityInfos.get(k)).getStartPosition()) && (((EntityInfo)entityInfos.get(i)).getStartPosition() < ((EntityInfo)entityInfos.get(k)).getEndPosition()))
      {
        if ((((EntityInfo)entityInfos.get(k)).getEntityType() != EntityType.REALNUMBER) && (((EntityInfo)entityInfos.get(i)).getConfidence() < ((EntityInfo)entityInfos.get(k)).getConfidence()))
        {
          ((EntityInfo)entityInfos.get(i)).setEntityType(EntityType.DROP);
          j = k;
        }
        else if ((((EntityInfo)entityInfos.get(i)).getEntityType() != EntityType.REALNUMBER) && (((EntityInfo)entityInfos.get(i)).getConfidence() > ((EntityInfo)entityInfos.get(k)).getConfidence()))
        {
          ((EntityInfo)entityInfos.get(k)).setEntityType(EntityType.DROP);
          j = i;
        }
        else if ((((EntityInfo)entityInfos.get(i)).getEntityType() != EntityType.REALNUMBER) && (((EntityInfo)entityInfos.get(k)).getEntityType() == EntityType.REALNUMBER))
        {
          ((EntityInfo)entityInfos.get(k)).setEntityType(EntityType.DROP);
          j = i;
        }
        else if ((((EntityInfo)entityInfos.get(i)).getEntityType() == EntityType.REALNUMBER) && (((EntityInfo)entityInfos.get(k)).getEntityType() != EntityType.REALNUMBER))
        {
          ((EntityInfo)entityInfos.get(i)).setEntityType(EntityType.DROP);
          j = k;
        }
        else
        {
          if (((EntityInfo)entityInfos.get(i)).getGroupEntity() != null)
          {
            j = 1;
            label451:
            if (((EntityInfo)entityInfos.get(k)).getGroupEntity() == null) {
              break label511;
            }
          }
          label511:
          for (int m = 1;; m = 0)
          {
            if ((j == 0) || (m != 0)) {
              break label517;
            }
            ((EntityInfo)entityInfos.get(k)).setEntityType(EntityType.DROP);
            j = i;
            break;
            j = 0;
            break label451;
          }
          label517:
          if ((j == 0) && (m != 0))
          {
            ((EntityInfo)entityInfos.get(i)).setEntityType(EntityType.DROP);
            j = k;
          }
          else if ((j != 0) && (m != 0))
          {
            j = i;
          }
          else
          {
            m = ((EntityInfo)entityInfos.get(i)).getEndPosition() - ((EntityInfo)entityInfos.get(i)).getStartPosition() - ((EntityInfo)entityInfos.get(k)).getEndPosition() + ((EntityInfo)entityInfos.get(k)).getStartPosition();
            if (m > 0)
            {
              ((EntityInfo)entityInfos.get(k)).setEntityType(EntityType.DROP);
              j = i;
            }
            else
            {
              j = k;
              if (m < 0)
              {
                ((EntityInfo)entityInfos.get(i)).setEntityType(EntityType.DROP);
                j = k;
              }
            }
          }
        }
      }
      else {
        j = i;
      }
    }
  }
  
  private static StringString parseKnowledgePoint(String paramString)
  {
    int i = paramString.indexOf("_");
    return new StringString(paramString.substring(0, i), paramString.substring(i + 1));
  }
  
  private static boolean readPhoneNumberPrefix2Domain()
  {
    for (;;)
    {
      try
      {
        Object localObject = FileOperator.readFile(dictionaryPath + "/phoneNumberPrefix2Domain.txt");
        phoneNumberPrefix2Domain = new ArrayList(((List)localObject).size());
        localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext())
        {
          acPhoneNumberPrefix2Domain = new ACAutomation(phoneNumberPrefix2Domain, true);
          return true;
        }
      }
      catch (IOException localIOException)
      {
        return false;
      }
      String[] arrayOfString = ((String)localIOException.next()).split("\\t", 3);
      if (arrayOfString.length >= 2) {
        phoneNumberPrefix2Domain.add(new StringInt(Integer.valueOf(arrayOfString[1]).intValue(), arrayOfString[0]));
      }
    }
  }
  
  private void recognizeNumberSequence(EntityInfo paramEntityInfo, int paramInt)
    throws Exception
  {
    System.currentTimeMillis();
    if (recognitionTask.get(EntityType.MONEY.ordinal()))
    {
      d1 = MoneyRecognition.isRightNumber(paramEntityInfo, SMScontent);
      if (d1 > 0.0D)
      {
        paramEntityInfo.setEntityType(EntityType.MONEY);
        paramEntityInfo.setConfidence(d1);
        return;
      }
    }
    if (recognitionTask.get(EntityType.TIMESPAN.ordinal()))
    {
      d1 = TimeSpanRecognition.isRightNumber(paramEntityInfo, SMScontent);
      if (d1 > 0.0D)
      {
        paramEntityInfo.setEntityType(EntityType.TIMESPAN);
        paramEntityInfo.setConfidence(d1);
        return;
      }
    }
    if (StringProcess.StartWithDicWithVagueDistance(SMScontent.substring(paramEntityInfo.getEndPosition()).toLowerCase(), danweiWords, danweiWordsMaxLength, 0) >= 0)
    {
      d1 = RealNumberRecognition.isRightNumber(paramEntityInfo, SMScontent_LowerCase);
      if (d1 > 0.3D)
      {
        paramEntityInfo.setEntityType(EntityType.REALNUMBER);
        paramEntityInfo.setConfidence(d1);
        paramEntityInfo.noNomal();
        return;
      }
      paramEntityInfo.setEntityType(EntityType.UNKNOWN);
      return;
    }
    paramEntityInfo.setRemark("");
    double d6 = URLRecognition.isRightNumber(paramEntityInfo, SMScontent);
    if (d6 >= 0.8D)
    {
      paramEntityInfo.setEntityType(EntityType.URL);
      paramEntityInfo.setConfidence(d6);
      return;
    }
    String str5 = paramEntityInfo.getRemark();
    paramEntityInfo.setRemark("");
    double d3 = 0.0D;
    String str2 = null;
    if (recognitionTask.get(EntityType.VERIFICATIONCODE.ordinal()))
    {
      d3 = VerificationCodeRecognition.isRightNumber(paramEntityInfo, SMScontent_LowerCase, paramInt);
      if (d3 >= 1.0D)
      {
        paramEntityInfo.setEntityType(EntityType.VERIFICATIONCODE);
        paramEntityInfo.setConfidence(d3);
        return;
      }
      str2 = paramEntityInfo.getRemark();
      paramEntityInfo.setRemark("");
    }
    double d7 = PhoneNumberRecognition.isRightNumber(paramEntityInfo, SMScontent_LowerCase);
    if (d7 >= 1.0D)
    {
      paramEntityInfo.setEntityType(EntityType.PHONENUMBER);
      paramEntityInfo.setConfidence(d7);
      return;
    }
    String str6 = paramEntityInfo.getRemark();
    paramEntityInfo.setRemark("");
    double d2 = 0.0D;
    String str1 = null;
    if (recognitionTask.get(EntityType.BANKCARDNUMBER.ordinal()))
    {
      d2 = BankCardNumberRecognition.isRightNumber(paramEntityInfo, SMScontent_LowerCase);
      if (d2 >= 1.0D)
      {
        paramEntityInfo.setEntityType(EntityType.BANKCARDNUMBER);
        paramEntityInfo.setConfidence(d2);
        return;
      }
      str1 = paramEntityInfo.getRemark();
      paramEntityInfo.setRemark("");
    }
    double d4 = 0.0D;
    double d1 = d4;
    if (recognitionTask.get(EntityType.EXPRESSNUMBER.ordinal()))
    {
      d1 = d4;
      if (expressName != null)
      {
        paramEntityInfo.setRemark(expressName);
        d4 = ExpressNumberRecognition.isRightNumber(paramEntityInfo, SMScontent_LowerCase);
        expressName = paramEntityInfo.getRemark();
        d1 = d4;
        if (d4 >= 1.0D)
        {
          paramEntityInfo.setEntityType(EntityType.EXPRESSNUMBER);
          paramEntityInfo.setConfidence(d4);
          return;
        }
      }
    }
    System.currentTimeMillis();
    System.currentTimeMillis();
    double d5 = 0.0D;
    String str4 = null;
    if (recognitionTask.get(EntityType.MONEY.ordinal()))
    {
      d5 = RealNumberRecognition.isRightNumber(paramEntityInfo, SMScontent_LowerCase);
      str4 = paramEntityInfo.getRemark();
    }
    paramEntityInfo.setRemark("");
    String str3 = "";
    d4 = 0.1D;
    if (0.1D < d3)
    {
      paramEntityInfo.setEntityType(EntityType.VERIFICATIONCODE);
      d4 = d3;
      str3 = str2;
    }
    d3 = d4;
    if (d4 < d6)
    {
      d3 = d6;
      paramEntityInfo.setEntityType(EntityType.URL);
      str3 = str5;
    }
    d4 = d3;
    if (d3 < d7)
    {
      d4 = d7;
      paramEntityInfo.setEntityType(EntityType.PHONENUMBER);
      str3 = str6;
    }
    d3 = d4;
    if (d4 < d2)
    {
      paramEntityInfo.setEntityType(EntityType.BANKCARDNUMBER);
      d3 = d2;
      str3 = str1;
    }
    d2 = d3;
    if (d3 < d1)
    {
      paramEntityInfo.setEntityType(EntityType.EXPRESSNUMBER);
      str3 = expressName;
      d2 = d1;
    }
    d1 = d2;
    if (d2 < 0.0D)
    {
      d1 = 0.0D;
      paramEntityInfo.setEntityType(EntityType.TIME);
      str3 = null;
    }
    if (d1 >= 0.3D)
    {
      paramEntityInfo.setConfidence(d1);
      if (paramEntityInfo.getEntityType() == EntityType.TIME)
      {
        paramEntityInfo.setTargetNomal(str3);
        return;
      }
      paramEntityInfo.setRemark(str3);
      return;
    }
    if (d5 == 1.0D)
    {
      paramEntityInfo.setConfidence(d5);
      paramEntityInfo.setEntityType(EntityType.REALNUMBER);
      paramEntityInfo.setRemark(str4);
      return;
    }
    paramEntityInfo.setEntityType(EntityType.UNKNOWN);
  }
  
  public static void setLocalHostPhoneNumber(String arg0)
  {
    String str = PhoneNumberRecognition.nomalPhoneNumber(???);
    synchronized (sNumberLockObject)
    {
      localHostPhoneNumber1 = str;
      localHostPhoneNumber = localHostPhoneNumber1;
      return;
    }
  }
  
  public static void setLocalHostPhoneNumber(String paramString, CardIndex paramCardIndex)
  {
    switch (paramCardIndex)
    {
    default: 
      return;
    case CARD1: 
      setLocalHostPhoneNumber1(paramString);
      return;
    }
    setLocalHostPhoneNumber2(paramString);
  }
  
  public static void setLocalHostPhoneNumber1(String arg0)
  {
    String str = PhoneNumberRecognition.nomalPhoneNumber(???);
    synchronized (sNumberLockObject)
    {
      localHostPhoneNumber1 = str;
      localHostPhoneNumber = localHostPhoneNumber1;
      return;
    }
  }
  
  public static void setLocalHostPhoneNumber2(String arg0)
  {
    String str = PhoneNumberRecognition.nomalPhoneNumber(???);
    synchronized (sNumberLockObject)
    {
      localHostPhoneNumber2 = str;
      return;
    }
  }
  
  public static void setLocalHostPlace(String paramString)
  {
    synchronized (sPlaceLockObject)
    {
      localHostPlace = paramString;
      localHostPlace1 = paramString;
      return;
    }
  }
  
  public static void setLocalHostPlace(String paramString, CardIndex paramCardIndex)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    switch (paramCardIndex)
    {
    default: 
      return;
    case CARD1: 
      setLocalHostPlace1(str);
      return;
    }
    setLocalHostPlace2(str);
  }
  
  public static void setLocalHostPlace1(String paramString)
  {
    synchronized (sPlaceLockObject)
    {
      localHostPlace = paramString;
      localHostPlace1 = paramString;
      return;
    }
  }
  
  public static void setLocalHostPlace2(String paramString)
  {
    synchronized (sPlaceLockObject)
    {
      localHostPlace2 = paramString;
      return;
    }
  }
  
  public static void setPlatform(int paramInt)
  {
    platform = paramInt;
  }
  
  private boolean tagNumberSequence()
    throws IOException
  {
    entityInfos.clear();
    int m = -1;
    int n = 0;
    int k = 0;
    int j = 0;
    if (n >= SMScontent.length()) {
      return true;
    }
    int i = SMScontent.charAt(n);
    Object localObject = StringProcess.getASCType(i);
    int i2 = j;
    int i3 = k;
    int i1 = m;
    int i4;
    if (localObject != StringProcess.ASCType.Other)
    {
      i2 = j;
      i3 = k;
      i1 = m;
      if (localObject != StringProcess.ASCType.ChineseNumber)
      {
        i4 = m;
        if (m == -1) {
          i4 = n;
        }
        if (localObject != StringProcess.ASCType.Number) {
          break label508;
        }
        i3 = k + 1;
        i1 = i4;
        i2 = j;
      }
    }
    label119:
    label471:
    String str2;
    if (n != SMScontent.length() - 1)
    {
      if (localObject != StringProcess.ASCType.Other)
      {
        j = i2;
        k = i3;
        m = i1;
        if (localObject != StringProcess.ASCType.ChineseNumber) {}
      }
      else
      {
        j = i2;
        k = i3;
        m = i1;
        if (i != 19968)
        {
          j = i2;
          k = i3;
          m = i1;
          if (i != 95)
          {
            j = i2;
            k = i3;
            m = i1;
            if (i != 61)
            {
              j = i2;
              k = i3;
              m = i1;
              if (i != 63)
              {
                j = i2;
                k = i3;
                m = i1;
                if (i != 32)
                {
                  j = i2;
                  k = i3;
                  m = i1;
                  if (i != 46)
                  {
                    j = i2;
                    k = i3;
                    m = i1;
                    if (i != 47)
                    {
                      j = i2;
                      k = i3;
                      m = i1;
                      if (i != 43)
                      {
                        j = i2;
                        k = i3;
                        m = i1;
                        if (i != 45)
                        {
                          j = i2;
                          k = i3;
                          m = i1;
                          if (i != 37)
                          {
                            j = i2;
                            k = i3;
                            m = i1;
                            if (i != 64)
                            {
                              j = i2;
                              k = i3;
                              m = i1;
                              if (i != 58)
                              {
                                j = i2;
                                k = i3;
                                m = i1;
                                if (i != 38)
                                {
                                  j = i2;
                                  k = i3;
                                  m = i1;
                                  if (i != 8725)
                                  {
                                    j = i2;
                                    k = i3;
                                    m = i1;
                                    if (i != 42)
                                    {
                                      j = i2;
                                      k = i3;
                                      m = i1;
                                      if (i != 8212)
                                      {
                                        j = i2;
                                        k = i3;
                                        m = i1;
                                        if (i == 65295) {}
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    else
    {
      j = i2;
      k = i3;
      m = i1;
      if (i1 >= 0)
      {
        if ((localObject == StringProcess.ASCType.Other) || (localObject == StringProcess.ASCType.ChineseNumber)) {
          break label549;
        }
        k = n + 1;
        str2 = SMScontent.substring(i1, k);
        if (str2.length() >= 1) {
          break label555;
        }
        m = -1;
        k = 0;
      }
    }
    for (j = 0;; j = 0)
    {
      n += 1;
      break;
      label508:
      if (localObject != StringProcess.ASCType.EnglishLowerCase)
      {
        i2 = j;
        i3 = k;
        i1 = i4;
        if (localObject != StringProcess.ASCType.EnglishUpper) {
          break label119;
        }
      }
      i2 = j + 1;
      i3 = k;
      i1 = i4;
      break label119;
      label549:
      k = n;
      break label471;
      label555:
      if ((str2.length() < 5) || ((str2.charAt(0) != 'u') && (str2.charAt(0) != 'U')) || (i1 <= 0) || (SMScontent.charAt(i1 - 1) != '\\')) {
        break label617;
      }
      m = -1;
      k = 0;
    }
    label617:
    m = 0;
    String[] arrayOfString = str2.split("[/∕／]");
    j = m;
    String str3;
    if (arrayOfString.length > 1)
    {
      str3 = EntityInfo.nomal(arrayOfString[0]);
      localObject = PhoneNumberRecognition.isPhoneNumber(arrayOfString[0], str3);
      j = m;
      if (!((String)localObject).equals("非电话号码"))
      {
        m = 0;
        i4 = i1;
      }
    }
    String str1;
    int i5;
    for (;;)
    {
      if (m >= arrayOfString.length)
      {
        j = 1;
        if ((j == 0) && ((i3 > 0) || (URLRecognition.checkURL(str2))))
        {
          localObject = new EntityInfo();
          ((EntityInfo)localObject).setTarget(str2);
          ((EntityInfo)localObject).setTarget_nomal(str2);
          ((EntityInfo)localObject).setStartPosition(i1);
          ((EntityInfo)localObject).setEndPosition(k);
          ((EntityInfo)localObject).setEngCharCount(i2);
          ((EntityInfo)localObject).setNumberCount(i3);
          ((EntityInfo)localObject).setEntityType(EntityType.UNKNOWN);
          trimEnd((EntityInfo)localObject);
          entityInfos.add(localObject);
        }
        m = -1;
        k = 0;
        j = 0;
        break;
      }
      if (m != 0) {
        break label958;
      }
      str1 = arrayOfString[m];
      j = StringProcess.getNumberCount(str1);
      if ((j > 0) || (URLRecognition.checkURL(str2)))
      {
        i5 = StringProcess.getEngCharCount(str1);
        EntityInfo localEntityInfo = new EntityInfo();
        localEntityInfo.setTarget(arrayOfString[m]);
        localEntityInfo.setTarget_nomal(str1);
        localEntityInfo.setStartPosition(i4);
        localEntityInfo.setEndPosition(arrayOfString[m].length() + i4);
        localEntityInfo.setEngCharCount(i5);
        localEntityInfo.setNumberCount(j);
        localEntityInfo.setConfidence(0.95D);
        localEntityInfo.setEntityType(EntityType.PHONENUMBER);
        localEntityInfo.setRemark((String)localObject);
        trimEnd(localEntityInfo);
        entityInfos.add(localEntityInfo);
      }
      i4 += arrayOfString[m].length() + 1;
      m += 1;
    }
    label958:
    if (((String)localObject).equals("服务电话")) {}
    for (j = 0;; j = 1)
    {
      i5 = StringProcess.getNumberCount(arrayOfString[m]);
      if ((i5 == arrayOfString[m].length()) && (i5 <= str3.length())) {
        break label1125;
      }
      localObject = PhoneNumberRecognition.isPhoneNumber(arrayOfString[m], EntityInfo.nomal(arrayOfString[m]));
      if (!((String)localObject).equals("非电话号码")) {
        break label1069;
      }
      Log.i("NumberRecognition", "tagNumberSequence Error:  " + SMScontent);
      j = 1;
      break;
    }
    label1069:
    j = 0;
    label1125:
    for (;;)
    {
      if (j != 0)
      {
        str1 = str3.substring(0, str3.length() - i5) + arrayOfString[m];
        break;
      }
      str1 = arrayOfString[m];
      break;
    }
  }
  
  private void trimEnd(EntityInfo paramEntityInfo)
  {
    int i = paramEntityInfo.getTarget().length() - 1;
    for (;;)
    {
      if (i < 0) {}
      while (StringProcess.getASCType(paramEntityInfo.getTarget().charAt(i)) != StringProcess.ASCType.Other)
      {
        if (i != paramEntityInfo.getTarget().length() - 1)
        {
          int j = i;
          if (i < 0) {
            j = 0;
          }
          paramEntityInfo.setEndPosition(paramEntityInfo.getEndPosition() - paramEntityInfo.getTarget().length() + j + 1);
          paramEntityInfo.setTarget(paramEntityInfo.getTarget().substring(0, j + 1));
        }
        return;
      }
      i -= 1;
    }
  }
  
  public boolean addTaskByBody(String paramString)
  {
    if (smsDic2Pattern == null) {
      return false;
    }
    long l1 = System.currentTimeMillis();
    paramString = smsDic2Pattern.match(fromPhoneNumber, getLocalHostPlace(), paramString);
    long l2 = System.currentTimeMillis();
    System.out.println(l2 - l1);
    specialEntityPats = new HashSet(paramString.size());
    paramString = paramString.iterator();
    for (;;)
    {
      if (!paramString.hasNext()) {
        return true;
      }
      String str = (String)paramString.next();
      if ((recognizeEntities == null) || (recognizeEntities.size() == 0))
      {
        specialEntityPats.add(str);
        addRecognitionTask(EntityType.SPECIALENTITY);
        Log.i("Time", "addTaskByBody:" + str);
      }
      else
      {
        try
        {
          EntityType localEntityType = EntityType.valueOf(str.toUpperCase());
          if (recognizeEntities.contains(localEntityType))
          {
            specialEntityPats.add(str);
            addRecognitionTask(EntityType.SPECIALENTITY);
            Log.i("Time", "addTaskByBody:" + str);
          }
        }
        catch (Exception localException) {}
      }
    }
  }
  
  public void freeTaskByBody()
  {
    if (specialEntityPats == null) {
      return;
    }
    getResourceFromSpecialEntity(fromPhoneNumber);
    Iterator localIterator1 = specialEntityPats.iterator();
    for (;;)
    {
      if (!localIterator1.hasNext())
      {
        specialEntityPats = null;
        return;
      }
      String str = (String)localIterator1.next();
      if (resourceFromSpecialEntity != null)
      {
        int i = 0;
        Iterator localIterator2 = resourceFromSpecialEntity.iterator();
        label67:
        if (!localIterator2.hasNext()) {}
        for (;;)
        {
          if (i != 0) {
            break label109;
          }
          SpecialEntityRecognition.freeParseSoft(str);
          break;
          if (!((String)localIterator2.next()).equals(str)) {
            break label67;
          }
          i = 1;
        }
      }
      else
      {
        label109:
        SpecialEntityRecognition.freeParseSoft(str);
      }
    }
  }
  
  public void getExpressName()
  {
    List localList = ExpressNumberRecognition.findExpNamesFromText(SMScontent_LowerCase);
    if (localList.size() > 0)
    {
      expressName = ((StringInt)localList.get(0)).getName();
      return;
    }
    expressName = null;
  }
  
  public ArrayList<RecognitionResult> recognizeNER()
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    if ((SMScontent == null) || (SMScontent.length() < SMScontentMinLength)) {
      return localArrayList;
    }
    long l2 = System.currentTimeMillis();
    for (;;)
    {
      long l1;
      Object localObject;
      int i;
      int j;
      int k;
      try
      {
        tagNumberSequence();
        l1 = System.currentTimeMillis();
        Log.i("Time", "tagNumberSequence:" + (l1 - l2));
        if (entityInfos.size() <= 0) {
          break label1278;
        }
        if (recognitionTask.get(EntityType.EXPRESSNUMBER.ordinal())) {
          getExpressName();
        }
        localObject = entityInfos.iterator();
        i = 0;
        j = 0;
        if (!((Iterator)localObject).hasNext())
        {
          long l3 = System.currentTimeMillis();
          Log.i("Time", "recognizeNumberSequence:" + (l3 - l1));
          if (recognitionTask.get(EntityType.TOPUP.ordinal()))
          {
            double d = ChongzhiRecognition.isRight(SMScontent_LowerCase);
            if (d >= 1.0D)
            {
              localObject = new EntityInfo();
              ((EntityInfo)localObject).setEntityType(EntityType.TOPUP);
              ((EntityInfo)localObject).setConfidence(d);
              entityInfos.add(localObject);
            }
          }
          l2 = System.currentTimeMillis();
          Log.i("Time", "Chongzhi:" + (l2 - l3));
          if (recognitionTask.get(EntityType.TIME.ordinal()))
          {
            localObject = SMScontent;
            if (smsTime != -1L) {
              break label881;
            }
            l1 = System.currentTimeMillis();
            localObject = DateTimeRecognition.findKnowledge((String)localObject, l1).iterator();
            if (((Iterator)localObject).hasNext()) {
              break label890;
            }
          }
          l1 = System.currentTimeMillis();
          Log.i("Time", "Time:" + (l1 - l2));
          k = i;
          if (recognitionTask.get(EntityType.VERIFICATIONCODE.ordinal()))
          {
            localObject = VerificationCodeRecognition.findKnowledge(SMScontent).iterator();
            if (((Iterator)localObject).hasNext()) {
              break label979;
            }
            k = i;
          }
          if (recognitionTask.get(EntityType.FLOW.ordinal()))
          {
            localObject = FlowRecognition.findKnowledge(SMScontent);
            if (localObject != null)
            {
              localObject = ((List)localObject).iterator();
              if (((Iterator)localObject).hasNext()) {
                break label1010;
              }
            }
          }
          if ((recognitionTask.get(EntityType.CREDITCARDHUANKUAN.ordinal())) && (CreditCardHuankuanRecognition.checkMes(fromPhoneNumber, SMScontent)))
          {
            localObject = new EntityInfo();
            ((EntityInfo)localObject).setEntityType(EntityType.CREDITCARDHUANKUAN);
            ((EntityInfo)localObject).setConfidence(1.0D);
            entityInfos.add(localObject);
          }
          l2 = System.currentTimeMillis();
          Log.i("Time", "VerificationCode:" + (l2 - l1));
          if (recognitionTask.get(EntityType.RESPONSE.ordinal()))
          {
            localObject = PhoneNumberRecognition.findKnowledge(SMScontent, fromPhoneNumber).iterator();
            if (((Iterator)localObject).hasNext()) {
              break label1049;
            }
          }
          l1 = System.currentTimeMillis();
          Log.i("Time", "FasongNeirong:" + (l1 - l2));
          if (recognitionTask.get(EntityType.SPECIALENTITY.ordinal()))
          {
            localObject = SpecialEntityRecognition.findKnowledge(SMScontent, fromPhoneNumber, specialEntityPats).iterator();
            if (((Iterator)localObject).hasNext()) {
              break label1076;
            }
          }
          Collections.sort(entityInfos);
          if ((j > 0) || (k > 0)) {
            normalizationOnlyOne();
          }
          normalizationResults();
          localObject = entityInfos.iterator();
          if (((Iterator)localObject).hasNext()) {
            break label1103;
          }
          return localArrayList;
        }
      }
      catch (Exception localException1)
      {
        throw new Exception(SMScontent);
      }
      EntityInfo localEntityInfo = (EntityInfo)((Iterator)localObject).next();
      if (((localEntityInfo.getTarget_nomal().length() >= 3) || (localEntityInfo.getTarget().length() >= 3)) && ((localEntityInfo.getTarget().length() >= 3) || (localEntityInfo.getTarget().length() != localEntityInfo.getNumberCount())) && (EntityType.UNKNOWN == localEntityInfo.getEntityType()))
      {
        label881:
        label890:
        label979:
        label1010:
        label1049:
        label1076:
        label1103:
        try
        {
          recognizeNumberSequence(localEntityInfo, entityInfos.size());
          if (localEntityInfo.getEntityType() == EntityType.EXPRESSNUMBER)
          {
            j += 1;
            continue;
          }
          if (localEntityInfo.getEntityType() == EntityType.VERIFICATIONCODE)
          {
            i += 1;
            continue;
          }
          if (localEntityInfo.getEntityType() != EntityType.URL) {
            continue;
          }
          localEntityInfo.noNomal();
        }
        catch (Exception localException2) {}
        l1 = smsTime;
        continue;
        localEntityInfo = (EntityInfo)((Iterator)localObject).next();
        k = localEntityInfo.getTarget().charAt(localEntityInfo.getTarget().length() - 1);
        if ((k < 48) || (k > 57) || (StringProcess.StartWithDicWithVagueDistance(SMScontent.substring(localEntityInfo.getEndPosition()).toLowerCase(), danweiWords, danweiWordsMaxLength, 0) < 0))
        {
          entityInfos.add(localEntityInfo);
          continue;
          localEntityInfo = (EntityInfo)((Iterator)localObject).next();
          entityInfos.add(localEntityInfo);
          i += 1;
          continue;
          localEntityInfo = (EntityInfo)((Iterator)localObject).next();
          if (localEntityInfo.getConfidence() >= 0.5D)
          {
            entityInfos.add(localEntityInfo);
            continue;
            localEntityInfo = (EntityInfo)((Iterator)localObject).next();
            entityInfos.add(localEntityInfo);
            continue;
            localEntityInfo = (EntityInfo)((Iterator)localObject).next();
            entityInfos.add(localEntityInfo);
            continue;
            localEntityInfo = (EntityInfo)((Iterator)localObject).next();
            if ((localEntityInfo.getEntityType() != EntityType.DROP) && (localEntityInfo.getEntityType() != EntityType.UNKNOWN) && (localEntityInfo.getConfidence() >= 0.1D) && ((recognitionTask.get(localEntityInfo.getEntityType().ordinal())) || (localEntityInfo.getEntityType() == EntityType.SPECIALENTITY)))
            {
              if (localEntityInfo.getEntityType() == EntityType.VERIFICATIONCODE) {
                localEntityInfo.noNomal();
              }
              if (recognitionTask.get(localEntityInfo.getEntityType().ordinal()))
              {
                localException1.add(new RecognitionResult(localEntityInfo.getStartPosition(), localEntityInfo.getEndPosition(), localEntityInfo.getGroupStartPosition(), localEntityInfo.getGroupEndPosition(), localEntityInfo.getEntityType(), localEntityInfo.getConfidence(), localEntityInfo.getTarget_nomal(), localEntityInfo.getRemark(), localEntityInfo.getGroupEntity()));
                continue;
                continue;
                label1278:
                i = 0;
                j = 0;
              }
            }
          }
        }
      }
    }
  }
  
  public List<OntologyResults> understand(String paramString)
  {
    long l1 = System.currentTimeMillis();
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    SMScontent = str.replace(65306, ':').replace(65292, ',');
    SMScontent_LowerCase = SMScontent.toLowerCase();
    if ((str.length() >= 1000) || (isSentenceNoChinese(SMScontent_LowerCase)))
    {
      smsTime = -1L;
      return new ArrayList();
    }
    addTaskByBody(str);
    for (;;)
    {
      ArrayList localArrayList1;
      ArrayList localArrayList2;
      int i;
      try
      {
        localArrayList1 = recognizeNER();
        paramString = new HashMap();
        localArrayList2 = new ArrayList();
        i = 0;
        if (i >= localArrayList1.size())
        {
          localObject1 = paramString.entrySet().iterator();
          if (((Iterator)localObject1).hasNext()) {
            break label874;
          }
          long l2 = System.currentTimeMillis();
          Log.i("Time", "Full understand:" + (l2 - l1));
          Log.i("Time", "Sentence:" + str);
          Log.i("Time", "");
          smsTime = -1L;
          freeTaskByBody();
          Collections.sort(localArrayList2);
          return localArrayList2;
        }
      }
      catch (Exception paramString)
      {
        Log.i("NumberRecognition", str);
        smsTime = -1L;
        freeTaskByBody();
        return new ArrayList();
      }
      Object localObject1 = (RecognitionResult)localArrayList1.get(i);
      try
      {
        switch (localObject1.getEntityType())
        {
        case TOPUP: 
          localObject2 = (OntologyTaskFrame)taskOntology.getOntologyByOntologyName("express");
          if (localObject2 == null) {
            break label2520;
          }
          localObject3 = new OntologyResults((OntologyTaskFrame)localObject2);
          ((AttrInfo)((OntologyResults)localObject3).getAttrInfos().get(((OntologyTaskFrame)localObject2).getAttrIndexByName("expressname"))).setValue(((RecognitionResult)localObject1).getParameter());
          localObject4 = (AttrInfo)((OntologyResults)localObject3).getAttrInfos().get(((OntologyTaskFrame)localObject2).getAttrIndexByName("expressnumber"));
          ((AttrInfo)localObject4).setValue(((RecognitionResult)localObject1).getRegularizationResult());
          ((AttrInfo)localObject4).setHas(1);
          ((AttrInfo)localObject4).setStartPosition(((RecognitionResult)localObject1).getStartPosition());
          ((AttrInfo)localObject4).setEndPosition(((RecognitionResult)localObject1).getEndPosition());
          ((AttrInfo)((OntologyResults)localObject3).getAttrInfos().get(((OntologyTaskFrame)localObject2).getAttrIndexByName("bizcode"))).setValue(Express.getBizCodeByName(((RecognitionResult)localObject1).getParameter()));
          localArrayList2.add(localObject3);
        }
      }
      catch (Exception localException)
      {
        Object localObject2;
        Object localObject3;
        Object localObject4;
        for (;;) {}
      }
      localObject3 = (OntologyTaskFrame)taskOntology.getOntologyByOntologyName("verificationcode");
      if (localObject3 != null)
      {
        localObject2 = new OntologyResults((OntologyTaskFrame)localObject3);
        localObject3 = (AttrInfo)((OntologyResults)localObject2).getAttrInfos().get(((OntologyTaskFrame)localObject3).getAttrIndexByName("verificationcode"));
        ((AttrInfo)localObject3).setValue(((RecognitionResult)localObject1).getRegularizationResult());
        ((AttrInfo)localObject3).setHas(1);
        ((AttrInfo)localObject3).setStartPosition(((RecognitionResult)localObject1).getStartPosition());
        ((AttrInfo)localObject3).setEndPosition(((RecognitionResult)localObject1).getEndPosition());
        localArrayList2.add(localObject2);
        break label2520;
        localObject1 = (OntologyTaskFrame)taskOntology.getOntologyByOntologyName("topup");
        if (localObject1 != null)
        {
          localArrayList2.add(new OntologyResults((OntologyTaskFrame)localObject1));
          break label2520;
          localObject1 = (OntologyTaskFrame)taskOntology.getOntologyByOntologyName("creditcardhuankuan");
          if (localObject1 != null)
          {
            localArrayList2.add(new OntologyResults((OntologyTaskFrame)localObject1));
            break label2520;
            localObject3 = (OntologyTaskFrame)taskOntology.getOntologyByOntologyName("datetime");
            if (localObject3 != null)
            {
              localObject2 = new OntologyResults((OntologyTaskFrame)localObject3);
              localObject3 = (AttrInfo)((OntologyResults)localObject2).getAttrInfos().get(((OntologyTaskFrame)localObject3).getAttrIndexByName("datetimenomal"));
              ((AttrInfo)localObject3).setValue(((RecognitionResult)localObject1).getRegularizationResult());
              ((AttrInfo)localObject3).setHas(1);
              ((AttrInfo)localObject3).setStartPosition(((RecognitionResult)localObject1).getStartPosition());
              ((AttrInfo)localObject3).setEndPosition(((RecognitionResult)localObject1).getEndPosition());
              localArrayList2.add(localObject2);
              break label2520;
              localObject1 = ((RecognitionResult)localObject1).getGroupEntity();
              if (!paramString.containsKey(localObject1)) {
                paramString.put(localObject1, new ArrayList());
              }
              ((List)paramString.get(localObject1)).add(Integer.valueOf(i));
              break label2520;
              label874:
              paramString = (Map.Entry)((Iterator)localObject1).next();
              localObject2 = (String)paramString.getKey();
              localObject3 = ((RecognitionResult)localArrayList1.get(((Integer)((List)paramString.getValue()).get(0)).intValue())).getEntityType();
              switch (localObject3)
              {
              default: 
                break;
              case DUOKANTUSHUQUAN: 
                localObject2 = ((String)localObject2).substring(((String)localObject2).indexOf('_') + 1);
                localObject2 = (OntologyTaskFrame)taskOntology.getOntologyByOntologyName((String)localObject2);
                if ((localObject2 == null) || (!((OntologyTaskFrame)localObject2).isEffective(platform, systemLevel, getLocalHostPlace(), "2.1.4"))) {
                  continue;
                }
                localObject3 = new OntologyResults((OntologyTaskFrame)localObject2);
                localObject4 = ((List)paramString.getValue()).iterator();
                label1040:
                if (!((Iterator)localObject4).hasNext())
                {
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(103, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    paramString = getLocalHostPhoneNumber();
                    if (paramString == null) {
                      break label2444;
                    }
                    label1074:
                    ((AttrInfo)localObject4).setValue(paramString);
                    ((AttrInfo)localObject4).setStartPosition(0);
                    ((AttrInfo)localObject4).setEndPosition(0);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(104, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    paramString = fromPhoneNumber;
                    if (paramString == null) {
                      break label2451;
                    }
                    label1117:
                    ((AttrInfo)localObject4).setValue(paramString);
                    ((AttrInfo)localObject4).setStartPosition(0);
                    ((AttrInfo)localObject4).setEndPosition(0);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(105, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    paramString = getLocalHostPlace();
                    if (paramString == null) {
                      break label2458;
                    }
                    label1159:
                    ((AttrInfo)localObject4).setValue(paramString);
                    ((AttrInfo)localObject4).setStartPosition(0);
                    ((AttrInfo)localObject4).setEndPosition(0);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(106, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    paramString = fromPhoneNumberPlace;
                    if (paramString == null) {
                      break label2465;
                    }
                    label1202:
                    ((AttrInfo)localObject4).setValue(paramString);
                    ((AttrInfo)localObject4).setStartPosition(0);
                    ((AttrInfo)localObject4).setEndPosition(0);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(102, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    if (smsTime == -1L) {
                      break label2472;
                    }
                    paramString = DateTimeRecognition.formatDateTime(smsTime);
                    label1255:
                    ((AttrInfo)localObject4).setValue(paramString);
                    ((AttrInfo)localObject4).setStartPosition(0);
                    ((AttrInfo)localObject4).setEndPosition(0);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(1021, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    if (smsTime == -1L) {
                      break label2479;
                    }
                    paramString = DateTimeRecognition.formatDate(smsTime);
                    label1309:
                    ((AttrInfo)localObject4).setValue(paramString);
                    ((AttrInfo)localObject4).setStartPosition(0);
                    ((AttrInfo)localObject4).setEndPosition(0);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(101, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    paramString = ((AttrInfo)localObject4).getValue();
                    if ((paramString == null) || (paramString.equals(""))) {
                      break label2486;
                    }
                    paramString = DateTimeRecognition.formatDateTime(paramString, smsTime);
                    label1372:
                    ((AttrInfo)localObject4).setValue(paramString);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(1011, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    paramString = ((AttrInfo)localObject4).getValue();
                    if ((paramString == null) || (paramString.equals(""))) {
                      break label2493;
                    }
                    paramString = DateTimeRecognition.formatDate(paramString, smsTime);
                    label1424:
                    ((AttrInfo)localObject4).setValue(paramString);
                  }
                  localObject4 = ((OntologyResults)localObject3).getAttrByType(1012, (OntologyTaskFrame)localObject2);
                  if (localObject4 != null)
                  {
                    paramString = ((AttrInfo)localObject4).getValue();
                    if ((paramString == null) || (paramString.equals(""))) {
                      break label2500;
                    }
                  }
                }
                label2429:
                label2444:
                label2451:
                label2458:
                label2465:
                label2472:
                label2479:
                label2486:
                label2493:
                label2500:
                for (paramString = DateTimeRecognition.formatTime(paramString, smsTime);; paramString = OntologyResults.getValueDefault())
                {
                  ((AttrInfo)localObject4).setValue(paramString);
                  if (((OntologyResults)localObject3).getSummation() <= 0) {
                    break label2507;
                  }
                  if (!FlowRecognition.lackOfFlow((OntologyResults)localObject3, (OntologyTaskFrame)localObject2)) {
                    break;
                  }
                  localArrayList2.add(localObject3);
                  break;
                  RecognitionResult localRecognitionResult = (RecognitionResult)localArrayList1.get(((Integer)((Iterator)localObject4).next()).intValue());
                  paramString = parseKnowledgePoint(localRecognitionResult.getParameter());
                  AttrInfo localAttrInfo1 = ((OntologyResults)localObject3).getAttrByName(paramString.getSecond(), (OntologyTaskFrame)localObject2);
                  if (localAttrInfo1 == null) {
                    break label1040;
                  }
                  if (((OntologyResults)localObject3).getSummation() <= 0) {
                    switch (localAttrInfo1.getType())
                    {
                    default: 
                      localAttrInfo1.setValue(localRecognitionResult.getRegularizationResult());
                    }
                  }
                  for (;;)
                  {
                    localAttrInfo1.setHas(1);
                    if ((((OntologyResults)localObject3).getSummation() > 0) || (localRecognitionResult.getStartPosition() < 0)) {
                      break label2429;
                    }
                    localAttrInfo1.setStartPosition(localRecognitionResult.getStartPosition());
                    localAttrInfo1.setEndPosition(localRecognitionResult.getEndPosition());
                    break;
                    paramString = ExpressNumberRecognition.findExpNamesFromText(localRecognitionResult.getRegularizationResult().toLowerCase());
                    if ((paramString != null) && (paramString.size() > 0))
                    {
                      localAttrInfo1.setValue(((StringInt)paramString.get(0)).getName());
                      AttrInfo localAttrInfo2 = ((OntologyResults)localObject3).getAttrByType(4, (OntologyTaskFrame)localObject2);
                      if (localAttrInfo2 != null)
                      {
                        localAttrInfo2.setValue(Express.getBizCodeByName(((StringInt)paramString.get(0)).getName()));
                        localAttrInfo2.setStartPosition(0);
                        localAttrInfo2.setEndPosition(0);
                      }
                    }
                    else
                    {
                      localAttrInfo1.setValue(localRecognitionResult.getRegularizationResult());
                      continue;
                      paramString = localRecognitionResult.getRegularizationResult();
                      if ((paramString != null) && (!paramString.equals(""))) {}
                      for (paramString = DateTimeRecognition.formatDateTime(paramString, smsTime);; paramString = OntologyResults.getValueDefault())
                      {
                        localAttrInfo1.setValue(paramString);
                        localAttrInfo1.setStartPosition(localRecognitionResult.getStartPosition());
                        localAttrInfo1.setEndPosition(localRecognitionResult.getEndPosition());
                        break;
                      }
                      paramString = localRecognitionResult.getRegularizationResult();
                      if ((paramString != null) && (!paramString.equals(""))) {}
                      for (paramString = DateTimeRecognition.formatDate(paramString, smsTime);; paramString = OntologyResults.getValueDefault())
                      {
                        localAttrInfo1.setValue(paramString);
                        localAttrInfo1.setStartPosition(localRecognitionResult.getStartPosition());
                        localAttrInfo1.setEndPosition(localRecognitionResult.getEndPosition());
                        break;
                      }
                      paramString = localRecognitionResult.getRegularizationResult();
                      if ((paramString != null) && (!paramString.equals(""))) {}
                      for (paramString = DateTimeRecognition.formatTime(paramString, smsTime);; paramString = OntologyResults.getValueDefault())
                      {
                        localAttrInfo1.setValue(paramString);
                        localAttrInfo1.setStartPosition(localRecognitionResult.getStartPosition());
                        localAttrInfo1.setEndPosition(localRecognitionResult.getEndPosition());
                        break;
                      }
                      paramString = localRecognitionResult.getRegularizationResult();
                      if ((paramString != null) && (!paramString.equals(""))) {}
                      for (paramString = paramString.replaceAll("[,， \t]+", "");; paramString = OntologyResults.getValueDefault())
                      {
                        localAttrInfo1.setValue(paramString);
                        localAttrInfo1.setStartPosition(localRecognitionResult.getStartPosition());
                        localAttrInfo1.setEndPosition(localRecognitionResult.getEndPosition());
                        break;
                      }
                      paramString = localRecognitionResult.getRegularizationResult();
                      if ((paramString != null) && (!paramString.equals(""))) {}
                      for (paramString = URLRecognition.formatUrl(paramString);; paramString = OntologyResults.getValueDefault())
                      {
                        localAttrInfo1.setValue(paramString);
                        localAttrInfo1.setStartPosition(localRecognitionResult.getStartPosition());
                        localAttrInfo1.setEndPosition(localRecognitionResult.getEndPosition());
                        break;
                      }
                      double d = FlowRecognition.regularizationFlow(localRecognitionResult.getRegularizationResult());
                      if (d < 0.0D) {
                        break;
                      }
                      localAttrInfo1.setValue(getValueBySummation(localAttrInfo1.getValue(), d, paramString.getFirst()));
                    }
                  }
                  localAttrInfo1.setStartPosition(0);
                  localAttrInfo1.setEndPosition(0);
                  break label1040;
                  paramString = OntologyResults.getValueDefault();
                  break label1074;
                  paramString = OntologyResults.getValueDefault();
                  break label1117;
                  paramString = OntologyResults.getValueDefault();
                  break label1159;
                  paramString = OntologyResults.getValueDefault();
                  break label1202;
                  paramString = OntologyResults.getValueDefault();
                  break label1255;
                  paramString = OntologyResults.getValueDefault();
                  break label1309;
                  paramString = OntologyResults.getValueDefault();
                  break label1372;
                  paramString = OntologyResults.getValueDefault();
                  break label1424;
                }
                label2507:
                localArrayList2.add(localObject3);
                break;
              }
            }
          }
        }
      }
      label2520:
      i += 1;
    }
  }
  
  public List<OntologyResults> understand(String paramString, long paramLong)
  {
    smsTime = paramLong;
    return understand(paramString);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.SMSUnderstand
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */