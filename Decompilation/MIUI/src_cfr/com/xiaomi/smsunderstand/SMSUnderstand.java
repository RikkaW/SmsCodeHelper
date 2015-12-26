/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Boolean
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.BitSet
 *  java.util.Collections
 *  java.util.HashMap
 *  java.util.HashSet
 */
package com.xiaomi.smsunderstand;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.ACAutomationable;
import com.xiaomi.common.ConfigProcess;
import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.common.StringString;
import com.xiaomi.nlp.OntologyActionManagement;
import com.xiaomi.nlp.OntologyTaskManagement;
import com.xiaomi.nlp.Parser;
import com.xiaomi.smsunderstand.CardIndex;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.OntologyResults;
import com.xiaomi.smsunderstand.RecognitionResult;
import com.xiaomi.smsunderstand.SMSDic2Pattern;
import com.xiaomi.smsunderstand.task.BankCardNumberRecognition;
import com.xiaomi.smsunderstand.task.ChongzhiRecognition;
import com.xiaomi.smsunderstand.task.CreditCardHuankuanRecognition;
import com.xiaomi.smsunderstand.task.DateTimeRecognition;
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
import java.util.Set;

public class SMSUnderstand {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$smsunderstand$CardIndex;
    private static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$smsunderstand$EntityType;
    private static int NumberMinLength;
    private static int SMScontentMinLength;
    private static ACAutomation acPhoneNumberPrefix2Domain;
    private static OntologyActionManagement actionOntology;
    private static String cleanNumberRegex;
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
    private long smsTime = -1;
    private HashSet<String> specialEntityPats;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$smsunderstand$CardIndex() {
        int[] arrn;
        arrn = $SWITCH_TABLE$com$xiaomi$smsunderstand$CardIndex;
        if (arrn != null) {
            return arrn;
        }
        arrn = new int[CardIndex.values().length];
        try {
            arrn[CardIndex.CARD1.ordinal()] = 1;
        }
        catch (NoSuchFieldError var1_2) {}
        try {
            arrn[CardIndex.CARD2.ordinal()] = 2;
        }
        catch (NoSuchFieldError var1_1) {}
        $SWITCH_TABLE$com$xiaomi$smsunderstand$CardIndex = arrn;
        return arrn;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$smsunderstand$EntityType() {
        int[] arrn;
        arrn = $SWITCH_TABLE$com$xiaomi$smsunderstand$EntityType;
        if (arrn != null) {
            return arrn;
        }
        arrn = new int[EntityType.values().length];
        try {
            arrn[EntityType.BANKCARDNUMBER.ordinal()] = 17;
        }
        catch (NoSuchFieldError var1_25) {}
        try {
            arrn[EntityType.CHAR.ordinal()] = 3;
        }
        catch (NoSuchFieldError var1_24) {}
        try {
            arrn[EntityType.CREDITCARDHUANKUAN.ordinal()] = 10;
        }
        catch (NoSuchFieldError var1_23) {}
        try {
            arrn[EntityType.DROP.ordinal()] = 25;
        }
        catch (NoSuchFieldError var1_22) {}
        try {
            arrn[EntityType.DUOKANTUSHUQUAN.ordinal()] = 9;
        }
        catch (NoSuchFieldError var1_21) {}
        try {
            arrn[EntityType.ENG.ordinal()] = 4;
        }
        catch (NoSuchFieldError var1_20) {}
        try {
            arrn[EntityType.EXPRESSNAME.ordinal()] = 18;
        }
        catch (NoSuchFieldError var1_19) {}
        try {
            arrn[EntityType.EXPRESSNUMBER.ordinal()] = 19;
        }
        catch (NoSuchFieldError var1_18) {}
        try {
            arrn[EntityType.FLOW.ordinal()] = 15;
        }
        catch (NoSuchFieldError var1_17) {}
        try {
            arrn[EntityType.HUMAN_NAME.ordinal()] = 1;
        }
        catch (NoSuchFieldError var1_16) {}
        try {
            arrn[EntityType.MONEY.ordinal()] = 16;
        }
        catch (NoSuchFieldError var1_15) {}
        try {
            arrn[EntityType.NUMBER.ordinal()] = 2;
        }
        catch (NoSuchFieldError var1_14) {}
        try {
            arrn[EntityType.PHONENUMBER.ordinal()] = 20;
        }
        catch (NoSuchFieldError var1_13) {}
        try {
            arrn[EntityType.REALNUMBER.ordinal()] = 13;
        }
        catch (NoSuchFieldError var1_12) {}
        try {
            arrn[EntityType.RESPONSE.ordinal()] = 11;
        }
        catch (NoSuchFieldError var1_11) {}
        try {
            arrn[EntityType.SPECIALENTITY.ordinal()] = 5;
        }
        catch (NoSuchFieldError var1_10) {}
        try {
            arrn[EntityType.TIME.ordinal()] = 22;
        }
        catch (NoSuchFieldError var1_9) {}
        try {
            arrn[EntityType.TIMESPAN.ordinal()] = 14;
        }
        catch (NoSuchFieldError var1_8) {}
        try {
            arrn[EntityType.TOPUP.ordinal()] = 12;
        }
        catch (NoSuchFieldError var1_7) {}
        try {
            arrn[EntityType.UNKNOWN.ordinal()] = 24;
        }
        catch (NoSuchFieldError var1_6) {}
        try {
            arrn[EntityType.URL.ordinal()] = 21;
        }
        catch (NoSuchFieldError var1_5) {}
        try {
            arrn[EntityType.VERIFICATIONCODE.ordinal()] = 23;
        }
        catch (NoSuchFieldError var1_4) {}
        try {
            arrn[EntityType.XIAOMICAIPIAO.ordinal()] = 7;
        }
        catch (NoSuchFieldError var1_3) {}
        try {
            arrn[EntityType.XIAOMIDINGDAN.ordinal()] = 8;
        }
        catch (NoSuchFieldError var1_2) {}
        try {
            arrn[EntityType.ZHIFUBAO.ordinal()] = 6;
        }
        catch (NoSuchFieldError var1_1) {}
        $SWITCH_TABLE$com$xiaomi$smsunderstand$EntityType = arrn;
        return arrn;
    }

    static {
        SMScontentMinLength = 4;
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
        cleanNumberRegex = "(\\\\n)|(\\\\r)|(\\\\t)";
    }

    public SMSUnderstand(String string2, String string3) {
        long l = System.currentTimeMillis();
        SMSUnderstand.initialWithNoPara();
        this.SMScontent = null;
        this.SMScontent_LowerCase = null;
        String string4 = string2;
        if (string2 == null) {
            string4 = "";
        }
        string2 = string3;
        if (string3 == null) {
            string2 = "";
        }
        this.fromPhoneNumber = PhoneNumberRecognition.nomalPhoneNumber(string4);
        this.fromPhoneNumberPlace = string2;
        this.recognizeEntities = null;
        resourceFromSpecialEntity = null;
        try {
            this.isMobileNo = this.addTask(this.fromPhoneNumber, this.fromPhoneNumberPlace);
        }
        catch (Exception var2_3) {
            Log.i("NumberRecognition", String.valueOf((Object)string4) + "\t" + string2);
            return;
        }
        long l2 = System.currentTimeMillis();
        Log.i("Time", "initial:" + (l2 - l));
    }

    public SMSUnderstand(String string2, String string3, Set<EntityType> set) {
        long l = System.currentTimeMillis();
        SMSUnderstand.initialWithNoPara();
        this.SMScontent = null;
        this.SMScontent_LowerCase = null;
        this.fromPhoneNumber = PhoneNumberRecognition.nomalPhoneNumber(string2);
        this.fromPhoneNumberPlace = string3;
        this.recognizeEntities = set;
        try {
            this.isMobileNo = this.addTask(this.fromPhoneNumber, this.fromPhoneNumberPlace);
        }
        catch (Exception var3_4) {
            Log.i("NumberRecognition", String.valueOf((Object)string2) + "\t" + string3);
            return;
        }
        long l2 = System.currentTimeMillis();
        Log.i("Time", "initial:" + (l2 - l));
    }

    private void addRecognitionTask(EntityType entityType) {
        this.recognitionTask.set(entityType.ordinal(), true);
        SMSUnderstand.intialResource(entityType);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean addTask(String string2, String string3) {
        string3 = new ArrayList(10);
        boolean bl = SMSUnderstand.getTask(string2, string2, string3);
        if (this.recognizeEntities == null || this.recognizeEntities.size() == 0) {
            int n = 0;
            do {
                if (n >= string3.size()) {
                    return bl;
                }
                this.addRecognitionTask((EntityType)((Object)string3.get(n)));
                ++n;
            } while (true);
        }
        int n = 0;
        while (n < string3.size()) {
            if (this.recognizeEntities.contains(string3.get(n))) {
                this.addRecognitionTask((EntityType)((Object)string3.get(n)));
            }
            ++n;
        }
        return bl;
    }

    public static void freeAllResource() {
        Iterator iterator = tasksIntialResource.iterator();
        do {
            if (!iterator.hasNext()) {
                tasksIntialResource.clear();
                residentTasksIntialResource.clear();
                return;
            }
            SMSUnderstand.freeResource((EntityType)((Object)iterator.next()));
        } while (true);
    }

    public static void freeOntology() {
        taskOntology = new OntologyTaskManagement();
        actionOntology = new OntologyActionManagement();
        SMSUnderstand.freeAllResource();
        System.gc();
        ifInitial = false;
    }

    private static void freeResource(EntityType entityType) {
        switch (SMSUnderstand.$SWITCH_TABLE$com$xiaomi$smsunderstand$EntityType()[entityType.ordinal()]) {
            default: {
                return;
            }
            case 17: {
                BankCardNumberRecognition.freeResource();
                return;
            }
            case 12: {
                ChongzhiRecognition.freeResource();
                return;
            }
            case 19: {
                ExpressNumberRecognition.freeResource();
                return;
            }
            case 22: {
                DateTimeRecognition.freeResource();
                return;
            }
            case 14: {
                TimeSpanRecognition.freeResource();
                return;
            }
            case 16: {
                MoneyRecognition.freeResource();
                return;
            }
            case 15: {
                FlowRecognition.freeResource();
                return;
            }
            case 10: {
                CreditCardHuankuanRecognition.freeResource();
                return;
            }
            case 20: {
                PhoneNumberRecognition.freeResource();
                return;
            }
            case 21: {
                URLRecognition.freeResource();
                return;
            }
            case 11: {
                PhoneNumberRecognition.freeResource();
                return;
            }
            case 23: {
                VerificationCodeRecognition.freeResource();
                return;
            }
            case 5: 
        }
        SpecialEntityRecognition.freeResource();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void freeResource(String iterator, String iterator2) {
        Object object = new ArrayList(10);
        SMSUnderstand.getTask(iterator, iterator2, object);
        iterator2 = object.iterator();
        do {
            if (!iterator2.hasNext()) {
                SpecialEntityRecognition.freeParseHard();
                SMSUnderstand.getResourceFromSpecialEntity(iterator);
                if (resourceFromSpecialEntity == null) return;
                {
                    break;
                }
            }
            object = (EntityType)((Object)iterator2.next());
            if (residentTasksIntialResource.contains(object)) continue;
            SMSUnderstand.freeResource((EntityType)((Object)object));
            if (!tasksIntialResource.contains(object)) continue;
            tasksIntialResource.remove(object);
        } while (true);
        iterator = resourceFromSpecialEntity.iterator();
        while (iterator.hasNext()) {
            SpecialEntityRecognition.freeParseHard(iterator.next());
        }
        return;
    }

    public static int getActionCount(int n, int n2) {
        return actionOntology.getActionCount(n, n2);
    }

    public static String getBtnAction(int n, int n2, int n3) {
        return actionOntology.getBtnAction(n, n2, n3);
    }

    public static int getBtnNumber(int n) {
        return actionOntology.getBtnNumber(n);
    }

    public static String getBtnTitle(int n, int n2) {
        return actionOntology.getBtnTitle(n, n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getDomainByPhoneNumber(String list) {
        if (acPhoneNumberPrefix2Domain == null || (list = acPhoneNumberPrefix2Domain.startWith((String)((Object)list), 0)) == null || list.size() == 0) {
            return -1;
        }
        return phoneNumberPrefix2Domain.get(list.get(0)[0]).getNum();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getLocalHostPhoneNumber() {
        Object object = sNumberLockObject;
        synchronized (object) {
            return localHostPhoneNumber;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getLocalHostPlace() {
        Object object = sPlaceLockObject;
        synchronized (object) {
            return localHostPlace;
        }
    }

    public static int getPlatform() {
        return platform;
    }

    public static boolean getResourceFromSpecialEntity(String string2) {
        if (smsDic2Pattern == null) {
            return false;
        }
        if (resourceFromSpecialEntity == null) {
            resourceFromSpecialEntity = smsDic2Pattern.match(string2, SMSUnderstand.getLocalHostPlace(), "");
        }
        return true;
    }

    public static List<String> getResourceFromSpecialEntityReturn(String string2) {
        if (smsDic2Pattern == null) {
            return null;
        }
        return smsDic2Pattern.match(string2, SMSUnderstand.getLocalHostPlace(), "");
    }

    public static int getSystemLevel() {
        return systemLevel;
    }

    private static boolean getTask(String string2, String string3, List<EntityType> list) {
        boolean bl = PhoneNumberRecognition.ifMobileNo(string2);
        if (bl) {
            list.add(EntityType.URL);
            list.add(EntityType.BANKCARDNUMBER);
            list.add(EntityType.VERIFICATIONCODE);
            list.add(EntityType.EXPRESSNUMBER);
            list.add(EntityType.PHONENUMBER);
            list.add(EntityType.TIME);
            return bl;
        }
        switch (SMSUnderstand.getDomainByPhoneNumber(string2)) {
            default: {
                list.add(EntityType.VERIFICATIONCODE);
                list.add(EntityType.EXPRESSNUMBER);
                list.add(EntityType.MONEY);
                list.add(EntityType.RESPONSE);
                list.add(EntityType.PHONENUMBER);
                list.add(EntityType.TIME);
                list.add(EntityType.URL);
                return bl;
            }
            case 0: 
            case 1: 
            case 2: {
                list.add(EntityType.URL);
                list.add(EntityType.TOPUP);
                list.add(EntityType.FLOW);
                list.add(EntityType.RESPONSE);
                list.add(EntityType.PHONENUMBER);
                list.add(EntityType.TIME);
                list.add(EntityType.VERIFICATIONCODE);
                return bl;
            }
            case 100: 
        }
        list.add(EntityType.TIME);
        list.add(EntityType.URL);
        list.add(EntityType.VERIFICATIONCODE);
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String getValueBySummation(String string2, double d2, String string3) {
        int n = 0;
        if (string2 == null) {
            return String.valueOf((Object)string3) + ":" + d2;
        }
        String[] arrstring = string2.split("[:#]");
        if (arrstring.length % 2 != 0) {
            Log.i("Time", string2);
            Log.println(string2);
            return null;
        }
        string2 = new StringBuffer();
        boolean bl = false;
        do {
            if (n >= arrstring.length) {
                if (!bl) {
                    string2.append(string3).append(":").append(d2).append("#");
                }
                string2.deleteCharAt(string2.length() - 1);
                return string2.toString();
            }
            if (!arrstring[n].equals((Object)string3)) {
                string2.append(arrstring[n]).append(":").append(arrstring[n + 1]).append("#");
            } else {
                string2.append(arrstring[n]).append(":").append(Double.valueOf((String)arrstring[n + 1]) + d2).append("#");
                bl = true;
            }
            n += 2;
        } while (true);
    }

    public static String getVersion() {
        return "2.1.4";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean initial() {
        SMSUnderstand.setLocalHostPhoneNumber("");
        SMSUnderstand.setLocalHostPlace("");
        if (!resourceInJar) {
            dictionaryPath = "/data/data/com.android.mms/app_understand/smsUnderstandDic";
            do {
                return SMSUnderstand.initialWithNoPara();
                break;
            } while (true);
        }
        dictionaryPath = "smsUnderstandDic";
        return SMSUnderstand.initialWithNoPara();
    }

    public static boolean initial(String string2) {
        configPath = string2;
        return SMSUnderstand.initialWithNoPara();
    }

    private static boolean initialBodyKeyword2Pattern() {
        try {
            smsDic2Pattern = new SMSDic2Pattern(String.valueOf((Object)dictionaryPath) + "/patterns/Keyword2Pattern.txt");
            if (smsDic2Pattern.getPatternSize() == 0) {
                smsDic2Pattern = null;
                return false;
            }
        }
        catch (Exception var0) {
            smsDic2Pattern = null;
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean initialWithNoPara() {
        if (ifInitial) {
            return true;
        }
        if (!resourceInJar) {
            HashMap<String, String> hashMap = ConfigProcess.readConfig(configPath);
            if (hashMap == null) {
                Log.i("NumberRecognition", "Config Read Error!!!");
            } else {
                dictionaryPath = (String)hashMap.get((Object)"dictionaryDir");
                String string2 = (String)hashMap.get((Object)"parserTimeout");
                if (string2 != null) {
                    Parser.setTimeOutThr(Integer.valueOf((String)string2));
                }
                if ((hashMap = (String)hashMap.get((Object)"localTest")) != null) {
                    Log.setLocalTest(Boolean.valueOf((String)hashMap));
                }
            }
        }
        if (dictionaryPath == null || dictionaryPath.equals((Object)"")) {
            Log.i("NumberRecognition", "dictionaryDir No find!!!");
            return false;
        }
        if (!taskOntology.loadOntology(dictionaryPath)) {
            Log.i("NumberRecognition", "Load Task Ontology Error!!!");
            return false;
        }
        if (!actionOntology.loadOntology(dictionaryPath)) {
            Log.i("NumberRecognition", "Load Action Ontology Error!!!");
            return false;
        }
        if (!SMSUnderstand.readPhoneNumberPrefix2Domain()) {
            Log.i("NumberRecognition", "readPhoneNumberPrefix2Domain Error!!!");
            return false;
        }
        danweiWordsFileName = String.valueOf((Object)dictionaryPath) + "/qua_unit.txt";
        if (!SMSUnderstand.initialBodyKeyword2Pattern()) {
            Log.i("NumberRecognition", "initialBodyKeyword2Pattern Error!!!");
            return false;
        }
        SMSUnderstand.intialResource(EntityType.PHONENUMBER);
        SMSUnderstand.intialResource(EntityType.URL);
        SMSUnderstand.intialResource(EntityType.VERIFICATIONCODE);
        SMSUnderstand.intialResource(EntityType.TIME);
        SMSUnderstand.intialResource(EntityType.EXPRESSNUMBER);
        SMSUnderstand.intialResource(EntityType.SPECIALENTITY);
        residentTasksIntialResource.add((Object)EntityType.PHONENUMBER);
        residentTasksIntialResource.add((Object)EntityType.URL);
        residentTasksIntialResource.add((Object)EntityType.VERIFICATIONCODE);
        residentTasksIntialResource.add((Object)EntityType.EXPRESSNUMBER);
        residentTasksIntialResource.add((Object)EntityType.SPECIALENTITY);
        danweiWordsMaxLength = 0;
        ifInitial = true;
        return true;
    }

    /*
     * Exception decompiling
     */
    private static void intialResource(EntityType var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[CASE]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public static boolean isResourceInJar() {
        return resourceInJar;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean isSentenceNoChinese(String var1_1) {
        if (var1_1.contains((CharSequence)"ems")) {
            return false;
        }
        var3_2 = 0;
        var4_3 = 0;
        do {
            if (var3_2 >= var1_1.length()) {
                if ((double)var4_3 / (double)var1_1.length() < 0.9) return false;
                return true;
            }
            var5_5 = var1_1.charAt(var3_2);
            if (var5_5 < '0' || var5_5 > '9') ** GOTO lbl13
            var2_4 = var4_3 + 1;
            ** GOTO lbl23
lbl13: // 1 sources:
            if (var5_5 < 'a' || var5_5 > 'z') ** GOTO lbl16
            var2_4 = var4_3 + 1;
            ** GOTO lbl23
lbl16: // 1 sources:
            if (var5_5 < 'A' || var5_5 > 'Z') ** GOTO lbl19
            var2_4 = var4_3 + 1;
            ** GOTO lbl23
lbl19: // 1 sources:
            if (var5_5 == '.' || var5_5 == ' ' || var5_5 == '?') ** GOTO lbl-1000
            var2_4 = var4_3;
            if (var5_5 == '!') lbl-1000: // 2 sources:
            {
                var2_4 = var4_3 + 1;
            }
lbl23: // 6 sources:
            ++var3_2;
            var4_3 = var2_4;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean loadResourceForResident(String object, String string2) {
        ArrayList arrayList = new ArrayList(10);
        List<String> list = object;
        if (object == null) {
            list = "";
        }
        object = string2;
        if (string2 == null) {
            object = "";
        }
        string2 = PhoneNumberRecognition.nomalPhoneNumber((String)((Object)list));
        SMSUnderstand.getTask(string2, (String)object, arrayList);
        if (arrayList.size() == 0) {
            return false;
        }
        long l = System.currentTimeMillis();
        int n = 0;
        do {
            long l2;
            if (n >= arrayList.size()) {
                l2 = System.currentTimeMillis();
                Log.i("Time", "loadResourceForResident " + (l2 - l) + " number: " + string2);
                object = SMSUnderstand.getResourceFromSpecialEntityReturn(string2);
                if (object == null) return true;
                {
                    break;
                }
            }
            l2 = System.currentTimeMillis();
            SMSUnderstand.intialResource((EntityType)((Object)arrayList.get(n)));
            residentTasksIntialResource.add((Object)((EntityType)((Object)arrayList.get(n))));
            long l3 = System.currentTimeMillis();
            Log.i("Time", "loadResourceForResident " + (l3 - l2) + " task: " + ((EntityType)((Object)arrayList.get(n))).toString());
            ++n;
        } while (true);
        object = object.iterator();
        while (object.hasNext()) {
            SpecialEntityRecognition.loadParse((String)object.next());
        }
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void normalizationOnlyOne() {
        var16_1 = 0;
        var9_2 = 0;
        var10_3 = -1;
        var12_4 = -1;
        var3_5 = 0.0;
        var11_6 = -1;
        var1_7 = 0.0;
        do {
            if (var9_2 >= this.entityInfos.size()) break;
            if (this.entityInfos.get(var9_2).getEntityType() != EntityType.EXPRESSNUMBER) ** GOTO lbl24
            if (this.entityInfos.get(var9_2).getConfidence() > var1_7) ** GOTO lbl-1000
            var13_10 = var10_3;
            var14_11 = var12_4;
            var5_8 = var3_5;
            var15_12 = var11_6;
            var7_9 = var1_7;
            if (var9_2 == -1) lbl-1000: // 2 sources:
            {
                var7_9 = this.entityInfos.get(var9_2).getConfidence();
                var15_12 = var9_2;
                var5_8 = var3_5;
                var14_11 = var12_4;
                var13_10 = var10_3;
            }
            ** GOTO lbl44
lbl24: // 1 sources:
            var13_10 = var10_3;
            var14_11 = var12_4;
            var5_8 = var3_5;
            var15_12 = var11_6;
            var7_9 = var1_7;
            if (this.entityInfos.get(var9_2).getEntityType() != EntityType.VERIFICATIONCODE) ** GOTO lbl44
            if (this.entityInfos.get(var9_2).getRemark().contains((CharSequence)"::=")) {
                var10_3 = var9_2;
            }
            if (this.entityInfos.get(var9_2).getConfidence() > var3_5) ** GOTO lbl-1000
            var13_10 = var10_3;
            var14_11 = var12_4;
            var5_8 = var3_5;
            var15_12 = var11_6;
            var7_9 = var1_7;
            if (var9_2 == var10_3) lbl-1000: // 2 sources:
            {
                var5_8 = this.entityInfos.get(var9_2).getConfidence();
                var14_11 = var9_2;
                var13_10 = var10_3;
                var15_12 = var11_6;
                var7_9 = var1_7;
            }
lbl44: // 5 sources:
            ++var9_2;
            var10_3 = var13_10;
            var12_4 = var14_11;
            var3_5 = var5_8;
            var11_6 = var15_12;
            var1_7 = var7_9;
        } while (true);
        var9_2 = var16_1;
        do {
            if (var9_2 >= this.entityInfos.size()) {
                return;
            }
            if (this.entityInfos.get(var9_2).getEntityType() == EntityType.EXPRESSNUMBER) {
                if (var9_2 != var11_6) {
                    this.entityInfos.get(var9_2).setEntityType(EntityType.DROP);
                    this.entityInfos.get(var9_2).setConfidence(0.0);
                }
            } else if (this.entityInfos.get(var9_2).getEntityType() == EntityType.VERIFICATIONCODE && var9_2 != var12_4) {
                this.entityInfos.get(var9_2).setEntityType(EntityType.DROP);
                this.entityInfos.get(var9_2).setConfidence(0.0);
            }
            ++var9_2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void normalizationResults() {
        int n = -1;
        int n2 = 0;
        while (n2 < this.entityInfos.size()) {
            int n3;
            if (this.entityInfos.get(n2).getEntityType() == EntityType.DROP) {
                n3 = n;
            } else if (n < 0) {
                n3 = n2;
            } else if (this.entityInfos.get(n2).getStartPosition() >= this.entityInfos.get(n).getStartPosition() && this.entityInfos.get(n2).getStartPosition() < this.entityInfos.get(n).getEndPosition()) {
                if (this.entityInfos.get(n).getEntityType() != EntityType.REALNUMBER && this.entityInfos.get(n2).getConfidence() < this.entityInfos.get(n).getConfidence()) {
                    this.entityInfos.get(n2).setEntityType(EntityType.DROP);
                    n3 = n;
                } else if (this.entityInfos.get(n2).getEntityType() != EntityType.REALNUMBER && this.entityInfos.get(n2).getConfidence() > this.entityInfos.get(n).getConfidence()) {
                    this.entityInfos.get(n).setEntityType(EntityType.DROP);
                    n3 = n2;
                } else if (this.entityInfos.get(n2).getEntityType() != EntityType.REALNUMBER && this.entityInfos.get(n).getEntityType() == EntityType.REALNUMBER) {
                    this.entityInfos.get(n).setEntityType(EntityType.DROP);
                    n3 = n2;
                } else if (this.entityInfos.get(n2).getEntityType() == EntityType.REALNUMBER && this.entityInfos.get(n).getEntityType() != EntityType.REALNUMBER) {
                    this.entityInfos.get(n2).setEntityType(EntityType.DROP);
                    n3 = n;
                } else {
                    n3 = this.entityInfos.get(n2).getGroupEntity() != null ? 1 : 0;
                    int n4 = this.entityInfos.get(n).getGroupEntity() != null ? 1 : 0;
                    if (n3 != 0 && n4 == 0) {
                        this.entityInfos.get(n).setEntityType(EntityType.DROP);
                        n3 = n2;
                    } else if (n3 == 0 && n4 != 0) {
                        this.entityInfos.get(n2).setEntityType(EntityType.DROP);
                        n3 = n;
                    } else if (n3 != 0 && n4 != 0) {
                        n3 = n2;
                    } else {
                        n4 = this.entityInfos.get(n2).getEndPosition() - this.entityInfos.get(n2).getStartPosition() - this.entityInfos.get(n).getEndPosition() + this.entityInfos.get(n).getStartPosition();
                        if (n4 > 0) {
                            this.entityInfos.get(n).setEntityType(EntityType.DROP);
                            n3 = n2;
                        } else {
                            n3 = n;
                            if (n4 < 0) {
                                this.entityInfos.get(n2).setEntityType(EntityType.DROP);
                                n3 = n;
                            }
                        }
                    }
                }
            } else {
                n3 = n2;
            }
            ++n2;
            n = n3;
        }
        return;
    }

    private static StringString parseKnowledgePoint(String string2) {
        int n = string2.indexOf("_");
        return new StringString(string2.substring(0, n), string2.substring(n + 1));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean readPhoneNumberPrefix2Domain() {
        try {}
        catch (IOException var0_1) {
            return false;
        }
        Object object = FileOperator.readFile(String.valueOf((Object)dictionaryPath) + "/phoneNumberPrefix2Domain.txt");
        phoneNumberPrefix2Domain = new ArrayList(object.size());
        object = object.iterator();
        do {
            if (!object.hasNext()) {
                acPhoneNumberPrefix2Domain = new ACAutomation(phoneNumberPrefix2Domain, true);
                return true;
            }
            String[] arrstring = ((String)object.next()).split("\\t", 3);
            if (arrstring.length < 2) continue;
            phoneNumberPrefix2Domain.add(new StringInt(Integer.valueOf((String)arrstring[1]), arrstring[0]));
        } while (true);
    }

    private void recognizeNumberSequence(EntityInfo entityInfo, int n) throws Exception {
        double d2;
        double d3;
        double d4;
        System.currentTimeMillis();
        if (this.recognitionTask.get(EntityType.MONEY.ordinal()) && (d4 = MoneyRecognition.isRightNumber(entityInfo, this.SMScontent)) > 0.0) {
            entityInfo.setEntityType(EntityType.MONEY);
            entityInfo.setConfidence(d4);
            return;
        }
        if (this.recognitionTask.get(EntityType.TIMESPAN.ordinal()) && (d4 = TimeSpanRecognition.isRightNumber(entityInfo, this.SMScontent)) > 0.0) {
            entityInfo.setEntityType(EntityType.TIMESPAN);
            entityInfo.setConfidence(d4);
            return;
        }
        if (StringProcess.StartWithDicWithVagueDistance(this.SMScontent.substring(entityInfo.getEndPosition()).toLowerCase(), danweiWords, danweiWordsMaxLength, 0) >= 0) {
            d4 = RealNumberRecognition.isRightNumber(entityInfo, this.SMScontent_LowerCase);
            if (d4 > 0.3) {
                entityInfo.setEntityType(EntityType.REALNUMBER);
                entityInfo.setConfidence(d4);
                entityInfo.noNomal();
                return;
            }
            entityInfo.setEntityType(EntityType.UNKNOWN);
            return;
        }
        entityInfo.setRemark("");
        double d5 = URLRecognition.isRightNumber(entityInfo, this.SMScontent);
        if (d5 >= 0.8) {
            entityInfo.setEntityType(EntityType.URL);
            entityInfo.setConfidence(d5);
            return;
        }
        String string2 = entityInfo.getRemark();
        entityInfo.setRemark("");
        double d6 = 0.0;
        String string3 = null;
        if (this.recognitionTask.get(EntityType.VERIFICATIONCODE.ordinal())) {
            d6 = VerificationCodeRecognition.isRightNumber(entityInfo, this.SMScontent_LowerCase, n);
            if (d6 >= 1.0) {
                entityInfo.setEntityType(EntityType.VERIFICATIONCODE);
                entityInfo.setConfidence(d6);
                return;
            }
            string3 = entityInfo.getRemark();
            entityInfo.setRemark("");
        }
        if ((d3 = PhoneNumberRecognition.isRightNumber(entityInfo, this.SMScontent_LowerCase)) >= 1.0) {
            entityInfo.setEntityType(EntityType.PHONENUMBER);
            entityInfo.setConfidence(d3);
            return;
        }
        String string4 = entityInfo.getRemark();
        entityInfo.setRemark("");
        double d7 = 0.0;
        String string5 = null;
        if (this.recognitionTask.get(EntityType.BANKCARDNUMBER.ordinal())) {
            d7 = BankCardNumberRecognition.isRightNumber(entityInfo, this.SMScontent_LowerCase);
            if (d7 >= 1.0) {
                entityInfo.setEntityType(EntityType.BANKCARDNUMBER);
                entityInfo.setConfidence(d7);
                return;
            }
            string5 = entityInfo.getRemark();
            entityInfo.setRemark("");
        }
        d4 = d2 = 0.0;
        if (this.recognitionTask.get(EntityType.EXPRESSNUMBER.ordinal())) {
            d4 = d2;
            if (this.expressName != null) {
                entityInfo.setRemark(this.expressName);
                d2 = ExpressNumberRecognition.isRightNumber(entityInfo, this.SMScontent_LowerCase);
                this.expressName = entityInfo.getRemark();
                d4 = d2;
                if (d2 >= 1.0) {
                    entityInfo.setEntityType(EntityType.EXPRESSNUMBER);
                    entityInfo.setConfidence(d2);
                    return;
                }
            }
        }
        System.currentTimeMillis();
        System.currentTimeMillis();
        double d8 = 0.0;
        String string6 = null;
        if (this.recognitionTask.get(EntityType.MONEY.ordinal())) {
            d8 = RealNumberRecognition.isRightNumber(entityInfo, this.SMScontent_LowerCase);
            string6 = entityInfo.getRemark();
        }
        entityInfo.setRemark("");
        String string7 = "";
        d2 = 0.1;
        if (0.1 < d6) {
            entityInfo.setEntityType(EntityType.VERIFICATIONCODE);
            d2 = d6;
            string7 = string3;
        }
        d6 = d2;
        if (d2 < d5) {
            d6 = d5;
            entityInfo.setEntityType(EntityType.URL);
            string7 = string2;
        }
        d2 = d6;
        if (d6 < d3) {
            d2 = d3;
            entityInfo.setEntityType(EntityType.PHONENUMBER);
            string7 = string4;
        }
        d6 = d2;
        if (d2 < d7) {
            entityInfo.setEntityType(EntityType.BANKCARDNUMBER);
            d6 = d7;
            string7 = string5;
        }
        d7 = d6;
        if (d6 < d4) {
            entityInfo.setEntityType(EntityType.EXPRESSNUMBER);
            string7 = this.expressName;
            d7 = d4;
        }
        d4 = d7;
        if (d7 < 0.0) {
            d4 = 0.0;
            entityInfo.setEntityType(EntityType.TIME);
            string7 = null;
        }
        if (d4 >= 0.3) {
            entityInfo.setConfidence(d4);
            if (entityInfo.getEntityType() == EntityType.TIME) {
                entityInfo.setTargetNomal(string7);
                return;
            }
            entityInfo.setRemark(string7);
            return;
        }
        if (d8 == 1.0) {
            entityInfo.setConfidence(d8);
            entityInfo.setEntityType(EntityType.REALNUMBER);
            entityInfo.setRemark(string6);
            return;
        }
        entityInfo.setEntityType(EntityType.UNKNOWN);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setLocalHostPhoneNumber(String object) {
        String string2 = PhoneNumberRecognition.nomalPhoneNumber((String)object);
        object = sNumberLockObject;
        synchronized (object) {
            localHostPhoneNumber = SMSUnderstand.localHostPhoneNumber1 = string2;
            return;
        }
    }

    public static void setLocalHostPhoneNumber(String string2, CardIndex cardIndex) {
        switch (SMSUnderstand.$SWITCH_TABLE$com$xiaomi$smsunderstand$CardIndex()[cardIndex.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                SMSUnderstand.setLocalHostPhoneNumber1(string2);
                return;
            }
            case 2: 
        }
        SMSUnderstand.setLocalHostPhoneNumber2(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setLocalHostPhoneNumber1(String object) {
        String string2 = PhoneNumberRecognition.nomalPhoneNumber((String)object);
        object = sNumberLockObject;
        synchronized (object) {
            localHostPhoneNumber = SMSUnderstand.localHostPhoneNumber1 = string2;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setLocalHostPhoneNumber2(String object) {
        String string2 = PhoneNumberRecognition.nomalPhoneNumber((String)object);
        object = sNumberLockObject;
        synchronized (object) {
            localHostPhoneNumber2 = string2;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setLocalHostPlace(String string2) {
        Object object = sPlaceLockObject;
        synchronized (object) {
            localHostPlace = string2;
            localHostPlace1 = string2;
            return;
        }
    }

    public static void setLocalHostPlace(String string2, CardIndex cardIndex) {
        String string3 = string2;
        if (string2 == null) {
            string3 = "";
        }
        switch (SMSUnderstand.$SWITCH_TABLE$com$xiaomi$smsunderstand$CardIndex()[cardIndex.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                SMSUnderstand.setLocalHostPlace1(string3);
                return;
            }
            case 2: 
        }
        SMSUnderstand.setLocalHostPlace2(string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setLocalHostPlace1(String string2) {
        Object object = sPlaceLockObject;
        synchronized (object) {
            localHostPlace = string2;
            localHostPlace1 = string2;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setLocalHostPlace2(String string2) {
        Object object = sPlaceLockObject;
        synchronized (object) {
            localHostPlace2 = string2;
            return;
        }
    }

    public static void setPlatform(int n) {
        platform = n;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean tagNumberSequence() throws IOException {
        this.entityInfos.clear();
        var4_1 = -1;
        var5_2 = 0;
        var3_3 = 0;
        var2_4 = 0;
        block0 : do {
            if (var5_2 >= this.SMScontent.length()) {
                return true;
            }
            var1_5 = this.SMScontent.charAt(var5_2);
            var11_11 = StringProcess.getASCType(var1_5);
            var7_7 = var2_4;
            var8_8 = var3_3;
            var6_6 = var4_1;
            if (var11_11 == StringProcess.ASCType.Other) ** GOTO lbl35
            var7_7 = var2_4;
            var8_8 = var3_3;
            var6_6 = var4_1;
            if (var11_11 == StringProcess.ASCType.ChineseNumber) ** GOTO lbl35
            var9_9 = var4_1;
            if (var4_1 == -1) {
                var9_9 = var5_2;
            }
            if (var11_11 != StringProcess.ASCType.Number) ** GOTO lbl27
            var8_8 = var3_3 + 1;
            var6_6 = var9_9;
            var7_7 = var2_4;
            ** GOTO lbl35
lbl27: // 1 sources:
            if (var11_11 == StringProcess.ASCType.EnglishLowerCase) ** GOTO lbl-1000
            var7_7 = var2_4;
            var8_8 = var3_3;
            var6_6 = var9_9;
            if (var11_11 == StringProcess.ASCType.EnglishUpper) lbl-1000: // 2 sources:
            {
                var7_7 = var2_4 + 1;
                var8_8 = var3_3;
                var6_6 = var9_9;
            }
lbl35: // 6 sources:
            if (var5_2 == this.SMScontent.length() - 1) ** GOTO lbl109
            if (var11_11 == StringProcess.ASCType.Other) ** GOTO lbl41
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var11_11 != StringProcess.ASCType.ChineseNumber) ** GOTO lbl135
lbl41: // 2 sources:
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '\u4e00') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '_') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '=') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '?') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == ' ') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '.') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '/') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '+') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '-') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '%') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '@') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == ':') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '&') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '\u2215') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '*') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '\u2014') ** GOTO lbl135
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var1_5 == '\uff0f') ** GOTO lbl135
lbl109: // 2 sources:
            var2_4 = var7_7;
            var3_3 = var8_8;
            var4_1 = var6_6;
            if (var6_6 < 0) ** GOTO lbl135
            var3_3 = var11_11 != StringProcess.ASCType.Other && var11_11 != StringProcess.ASCType.ChineseNumber ? var5_2 + 1 : var5_2;
            var13_13 = this.SMScontent.substring(var6_6, var3_3);
            if (var13_13.length() >= 1) ** GOTO lbl120
            var4_1 = -1;
            var3_3 = 0;
            var2_4 = 0;
            ** GOTO lbl135
lbl120: // 1 sources:
            if (var13_13.length() < 5 || var13_13.charAt(0) != 'u' && var13_13.charAt(0) != 'U' || var6_6 <= 0 || this.SMScontent.charAt(var6_6 - 1) != '\\') ** GOTO lbl125
            var4_1 = -1;
            var3_3 = 0;
            var2_4 = 0;
            ** GOTO lbl135
lbl125: // 1 sources:
            var4_1 = 0;
            var14_14 = var13_13.split("[/\u2215\uff0f]");
            var2_4 = var4_1;
            if (var14_14.length > 1) {
                var15_15 = EntityInfo.nomal(var14_14[0]);
                var11_11 = PhoneNumberRecognition.isPhoneNumber(var14_14[0], var15_15);
                var2_4 = var4_1;
                if (!var11_11.equals((Object)"\u975e\u7535\u8bdd\u53f7\u7801")) {
                    break;
                }
            }
            ** GOTO lbl154
lbl135: // 22 sources:
            do {
                ++var5_2;
                continue block0;
                break;
            } while (true);
            break;
        } while (true);
        var4_1 = 0;
        var9_9 = var6_6;
        do {
            if (var4_1 < var14_14.length) ** GOTO lbl144
            var2_4 = 1;
            ** GOTO lbl154
lbl144: // 1 sources:
            if (var4_1 == 0) {
                var12_12 = var14_14[var4_1];
            } else {
                var2_4 = var11_11.equals((Object)"\u670d\u52a1\u7535\u8bdd") != false ? 0 : 1;
                var10_10 = StringProcess.getNumberCount(var14_14[var4_1]);
                if (var10_10 != var14_14[var4_1].length() || var10_10 > var15_15.length()) {
                    var11_11 = PhoneNumberRecognition.isPhoneNumber(var14_14[var4_1], EntityInfo.nomal(var14_14[var4_1]));
                    if (var11_11.equals((Object)"\u975e\u7535\u8bdd\u53f7\u7801")) {
                        Log.i("NumberRecognition", "tagNumberSequence Error:  " + this.SMScontent);
                        var2_4 = 1;
lbl154: // 3 sources:
                        if (var2_4 == 0 && (var8_8 > 0 || URLRecognition.checkURL(var13_13))) {
                            var11_11 = new EntityInfo();
                            var11_11.setTarget(var13_13);
                            var11_11.setTarget_nomal(var13_13);
                            var11_11.setStartPosition(var6_6);
                            var11_11.setEndPosition(var3_3);
                            var11_11.setEngCharCount(var7_7);
                            var11_11.setNumberCount(var8_8);
                            var11_11.setEntityType(EntityType.UNKNOWN);
                            this.trimEnd((EntityInfo)var11_11);
                            this.entityInfos.add((EntityInfo)var11_11);
                        }
                        var4_1 = -1;
                        var3_3 = 0;
                        var2_4 = 0;
                        ** continue;
                    }
                    var2_4 = 0;
                }
                var12_12 = var2_4 != 0 ? String.valueOf((Object)var15_15.substring(0, var15_15.length() - var10_10)) + var14_14[var4_1] : var14_14[var4_1];
            }
            if ((var2_4 = StringProcess.getNumberCount(var12_12)) > 0 || URLRecognition.checkURL(var13_13)) {
                var10_10 = StringProcess.getEngCharCount(var12_12);
                var16_16 = new EntityInfo();
                var16_16.setTarget(var14_14[var4_1]);
                var16_16.setTarget_nomal(var12_12);
                var16_16.setStartPosition(var9_9);
                var16_16.setEndPosition(var14_14[var4_1].length() + var9_9);
                var16_16.setEngCharCount(var10_10);
                var16_16.setNumberCount(var2_4);
                var16_16.setConfidence(0.95);
                var16_16.setEntityType(EntityType.PHONENUMBER);
                var16_16.setRemark((String)var11_11);
                this.trimEnd(var16_16);
                this.entityInfos.add(var16_16);
            }
            var9_9 += var14_14[var4_1].length() + 1;
            ++var4_1;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void trimEnd(EntityInfo entityInfo) {
        int n;
        for (n = entityInfo.getTarget().length() - 1; n >= 0 && StringProcess.getASCType(entityInfo.getTarget().charAt(n)) == StringProcess.ASCType.Other; --n) {
        }
        if (n != entityInfo.getTarget().length() - 1) {
            int n2 = n;
            if (n < 0) {
                n2 = 0;
            }
            entityInfo.setEndPosition(entityInfo.getEndPosition() - entityInfo.getTarget().length() + n2 + 1);
            entityInfo.setTarget(entityInfo.getTarget().substring(0, n2 + 1));
        }
    }

    public boolean addTaskByBody(String iterator) {
        if (smsDic2Pattern == null) {
            return false;
        }
        long l = System.currentTimeMillis();
        iterator = smsDic2Pattern.match(this.fromPhoneNumber, SMSUnderstand.getLocalHostPlace(), (String)((Object)iterator));
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l);
        this.specialEntityPats = new HashSet(iterator.size());
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            String string2 = (String)iterator.next();
            if (this.recognizeEntities == null || this.recognizeEntities.size() == 0) {
                this.specialEntityPats.add((Object)string2);
                this.addRecognitionTask(EntityType.SPECIALENTITY);
                Log.i("Time", "addTaskByBody:" + string2);
                continue;
            }
            try {
                EntityType entityType = EntityType.valueOf(string2.toUpperCase());
                if (!this.recognizeEntities.contains((Object)entityType)) continue;
                this.specialEntityPats.add((Object)string2);
                this.addRecognitionTask(EntityType.SPECIALENTITY);
                Log.i("Time", "addTaskByBody:" + string2);
            }
            catch (Exception var6_5) {
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void freeTaskByBody() {
        if (this.specialEntityPats == null) {
            return;
        }
        SMSUnderstand.getResourceFromSpecialEntity(this.fromPhoneNumber);
        Iterator iterator = this.specialEntityPats.iterator();
        do {
            boolean bl;
            Iterator<String> iterator2;
            if (!iterator.hasNext()) {
                this.specialEntityPats = null;
                return;
            }
            String string2 = (String)iterator.next();
            if (resourceFromSpecialEntity != null) {
                bl = false;
                iterator2 = resourceFromSpecialEntity.iterator();
            } else {
                SpecialEntityRecognition.freeParseSoft(string2);
                continue;
            }
            while (iterator2.hasNext()) {
                if (!iterator2.next().equals((Object)string2)) continue;
                bl = true;
                break;
            }
            if (bl) continue;
            SpecialEntityRecognition.freeParseSoft(string2);
        } while (true);
    }

    public void getExpressName() {
        List<StringInt> list = ExpressNumberRecognition.findExpNamesFromText(this.SMScontent_LowerCase);
        if (list.size() > 0) {
            this.expressName = list.get(0).getName();
            return;
        }
        this.expressName = null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public ArrayList<RecognitionResult> recognizeNER() throws Exception {
        var12_1 = new ArrayList();
        if (this.SMScontent == null) return var12_1;
        if (this.SMScontent.length() < SMSUnderstand.SMScontentMinLength) {
            return var12_1;
        }
        var8_3 = System.currentTimeMillis();
        try {
            this.tagNumberSequence();
        }
        catch (Exception var12_2) {
            throw new Exception(this.SMScontent);
        }
        var6_4 = System.currentTimeMillis();
        Log.i("Time", "tagNumberSequence:" + (var6_4 - var8_3));
        if (this.entityInfos.size() <= 0) ** GOTO lbl16
        if (!this.recognitionTask.get(EntityType.EXPRESSNUMBER.ordinal())) ** GOTO lbl19
        this.getExpressName();
        ** GOTO lbl19
lbl16: // 1 sources:
        var3_6 = 0;
        var4_7 = 0;
        ** GOTO lbl24
lbl19: // 2 sources:
        var13_5 = this.entityInfos.iterator();
        var3_6 = 0;
        var4_7 = 0;
        do {
            if (var13_5.hasNext()) ** GOTO lbl36
lbl24: // 2 sources:
            var10_10 = System.currentTimeMillis();
            Log.i("Time", "recognizeNumberSequence:" + (var10_10 - var6_4));
            if (this.recognitionTask.get(EntityType.TOPUP.ordinal()) && (var1_11 = ChongzhiRecognition.isRight(this.SMScontent_LowerCase)) >= 1.0) {
                var13_5 = new EntityInfo();
                var13_5.setEntityType(EntityType.TOPUP);
                var13_5.setConfidence(var1_11);
                this.entityInfos.add((EntityInfo)var13_5);
            }
            var8_3 = System.currentTimeMillis();
            Log.i("Time", "Chongzhi:" + (var8_3 - var10_10));
            if (this.recognitionTask.get(EntityType.TIME.ordinal())) {
                break;
            }
            ** GOTO lbl56
lbl36: // 1 sources:
            var14_8 = var13_5.next();
            if (var14_8.getTarget_nomal().length() < 3 && var14_8.getTarget().length() < 3 || var14_8.getTarget().length() < 3 && var14_8.getTarget().length() == var14_8.getNumberCount() || EntityType.UNKNOWN != var14_8.getEntityType()) continue;
            try {
                this.recognizeNumberSequence(var14_8, this.entityInfos.size());
                if (var14_8.getEntityType() == EntityType.EXPRESSNUMBER) {
                    ++var4_7;
                    continue;
                }
                if (var14_8.getEntityType() == EntityType.VERIFICATIONCODE) {
                    ++var3_6;
                    continue;
                }
                if (var14_8.getEntityType() != EntityType.URL) continue;
                var14_8.noNomal();
            }
            catch (Exception var14_9) {}
        } while (true);
        var13_5 = this.SMScontent;
        var6_4 = this.smsTime == -1 ? System.currentTimeMillis() : this.smsTime;
        var13_5 = DateTimeRecognition.findKnowledge((String)var13_5, var6_4).iterator();
        do {
            if (var13_5.hasNext()) ** GOTO lbl62
lbl56: // 2 sources:
            var6_4 = System.currentTimeMillis();
            Log.i("Time", "Time:" + (var6_4 - var8_3));
            var5_12 = var3_6;
            if (this.recognitionTask.get(EntityType.VERIFICATIONCODE.ordinal())) {
                break;
            }
            ** GOTO lbl71
lbl62: // 1 sources:
            var14_8 = var13_5.next();
            var5_12 = var14_8.getTarget().charAt(var14_8.getTarget().length() - 1);
            if (var5_12 >= 48 && var5_12 <= 57 && StringProcess.StartWithDicWithVagueDistance(this.SMScontent.substring(var14_8.getEndPosition()).toLowerCase(), SMSUnderstand.danweiWords, SMSUnderstand.danweiWordsMaxLength, 0) >= 0) continue;
            this.entityInfos.add(var14_8);
        } while (true);
        var13_5 = VerificationCodeRecognition.findKnowledge(this.SMScontent).iterator();
        do {
            if (var13_5.hasNext()) ** GOTO lbl74
            var5_12 = var3_6;
lbl71: // 2 sources:
            if (this.recognitionTask.get(EntityType.FLOW.ordinal()) && (var13_5 = FlowRecognition.findKnowledge(this.SMScontent)) != null) {
                break;
            }
            ** GOTO lbl81
lbl74: // 1 sources:
            var14_8 = var13_5.next();
            this.entityInfos.add(var14_8);
            ++var3_6;
        } while (true);
        var13_5 = var13_5.iterator();
        do {
            if (var13_5.hasNext()) ** GOTO lbl91
lbl81: // 2 sources:
            if (this.recognitionTask.get(EntityType.CREDITCARDHUANKUAN.ordinal()) && CreditCardHuankuanRecognition.checkMes(this.fromPhoneNumber, this.SMScontent)) {
                var13_5 = new EntityInfo();
                var13_5.setEntityType(EntityType.CREDITCARDHUANKUAN);
                var13_5.setConfidence(1.0);
                this.entityInfos.add((EntityInfo)var13_5);
            }
            var8_3 = System.currentTimeMillis();
            Log.i("Time", "VerificationCode:" + (var8_3 - var6_4));
            if (this.recognitionTask.get(EntityType.RESPONSE.ordinal())) {
                break;
            }
            ** GOTO lbl98
lbl91: // 1 sources:
            var14_8 = (EntityInfo)var13_5.next();
            if (var14_8.getConfidence() < 0.5) continue;
            this.entityInfos.add(var14_8);
        } while (true);
        var13_5 = PhoneNumberRecognition.findKnowledge(this.SMScontent, this.fromPhoneNumber).iterator();
        do {
            if (var13_5.hasNext()) ** GOTO lbl103
lbl98: // 2 sources:
            var6_4 = System.currentTimeMillis();
            Log.i("Time", "FasongNeirong:" + (var6_4 - var8_3));
            if (this.recognitionTask.get(EntityType.SPECIALENTITY.ordinal())) {
                break;
            }
            ** GOTO lbl-1000
lbl103: // 1 sources:
            var14_8 = var13_5.next();
            this.entityInfos.add(var14_8);
        } while (true);
        var13_5 = SpecialEntityRecognition.findKnowledge(this.SMScontent, this.fromPhoneNumber, this.specialEntityPats).iterator();
        do {
            if (!var13_5.hasNext()) lbl-1000: // 2 sources:
            {
                Collections.sort(this.entityInfos);
                if (var4_7 > 0 || var5_12 > 0) {
                    this.normalizationOnlyOne();
                    break;
                }
                break;
            }
            var14_8 = var13_5.next();
            this.entityInfos.add(var14_8);
        } while (true);
        this.normalizationResults();
        var13_5 = this.entityInfos.iterator();
        do {
            if (!var13_5.hasNext()) {
                return var12_1;
            }
            var14_8 = var13_5.next();
            if (var14_8.getEntityType() == EntityType.DROP || var14_8.getEntityType() == EntityType.UNKNOWN || var14_8.getConfidence() < 0.1 || !this.recognitionTask.get(var14_8.getEntityType().ordinal()) && var14_8.getEntityType() != EntityType.SPECIALENTITY) continue;
            if (var14_8.getEntityType() == EntityType.VERIFICATIONCODE) {
                var14_8.noNomal();
            }
            if (!this.recognitionTask.get(var14_8.getEntityType().ordinal())) continue;
            var12_1.add((Object)new RecognitionResult(var14_8.getStartPosition(), var14_8.getEndPosition(), var14_8.getGroupStartPosition(), var14_8.getGroupEndPosition(), var14_8.getEntityType(), var14_8.getConfidence(), var14_8.getTarget_nomal(), var14_8.getRemark(), var14_8.getGroupEntity()));
        } while (true);
    }

    /*
     * Exception decompiling
     */
    public List<OntologyResults> understand(String var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public List<OntologyResults> understand(String string2, long l) {
        this.smsTime = l;
        return this.understand(string2);
    }
}

