/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Entity
 *  android.content.Entity$NamedContentValues
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$CommonDataKinds
 *  android.provider.ContactsContract$CommonDataKinds$Im
 *  android.provider.ContactsContract$Contacts
 *  android.provider.ContactsContract$Profile
 *  android.provider.ContactsContract$RawContacts
 *  android.provider.ContactsContract$RawContactsEntity
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashSet
 */
import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class acu {
    private static final String[] a = new String[]{"_display_name"};
    private static final int b = ga.f();
    private Context c;
    private String d = "";
    private Set<b> e = new HashSet();
    private Set<c> f = new HashSet();
    private Set<i> g = new HashSet();
    private Set<g> h = new HashSet();
    private Set<h> i = new HashSet();
    private Set<f> j = new HashSet();
    private Set<d> k = new HashSet();
    private Set<e> l = new HashSet();
    private Set<a> m = new HashSet();
    private afc n;
    private afk o;

    public acu(Context context) {
        this.c = context;
    }

    /*
     * Exception decompiling
     */
    private static final Uri a(Context var0, ContentResolver var1_10, Uri var2_23, String var3_26, boolean var4_27) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[TRYBLOCK]], but top level block is 53[SIMPLE_IF_TAKEN]
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

    public static Uri a(Context context, Uri uri, boolean bl2) {
        if (uri == null) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        uri = acu.a(uri);
        return acu.a(context, contentResolver, uri, acu.a(acu.a(contentResolver, uri)), bl2);
    }

    /*
     * Exception decompiling
     */
    public static Uri a(Context var0, byte[] var1_11) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [24[CATCHBLOCK], 21[CATCHBLOCK]], but top level block is 27[CATCHBLOCK]
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

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final Uri a(Uri object) {
        String string2 = Uri.encode((String)((String)object.getPathSegments().get(1)));
        if (ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI.getLastPathSegment().equals((Object)string2)) {
            return object;
        }
        if (!(object = (String)object.getPathSegments().get(2)).equals((Object)"profile")) return ContactsContract.Contacts.CONTENT_VCARD_URI.buildUpon().appendPath((String)object).build();
        return ContactsContract.Profile.CONTENT_VCARD_URI.buildUpon().build();
    }

    private static final Uri a(Uri uri, boolean bl2) {
        return uri.buildUpon().appendQueryParameter("nophoto", String.valueOf((boolean)bl2)).build();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static final String a(ContentResolver var0, Uri var1_3) {
        var2_4 = null;
        var3_5 = var1_3.buildUpon().appendQueryParameter("limit", Integer.toString((int)1)).build();
        if ((var1_3 = var0.query((Uri)var1_3, acu.a, null, null, null)) != null) {
            var0 = var2_4;
            try {
                if (!var1_3.moveToFirst()) ** GOTO lbl17
                var0 = var1_3.getString(0);
            }
            catch (Exception var0_1) {
                Log.e((String)"VcardTools", (String)("getContentFileName()---->nameUri = " + var3_5.toString() + "--" + var0_1.getMessage()));
                var0 = null;
            }
            finally {
                var1_3.close();
            }
        } else {
            var0 = null;
        }
lbl17: // 4 sources:
        var1_3 = var0;
        if (var0 != null) return var1_3;
        return var3_5.getLastPathSegment();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static final String a(String charSequence) {
        ArrayList<act.a> arrayList = act.b().a((String)charSequence);
        int n2 = arrayList.size();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < n2; ++i2) {
            act.a a2 = (act.a)arrayList.get(i2);
            if (2 == a2.a) {
                stringBuilder.append(a2.c);
                continue;
            }
            stringBuilder.append(a2.b);
        }
        if (stringBuilder.length() < 1) {
            stringBuilder.append("ExpectationFile");
        }
        try {
            return new String(stringBuilder.toString().getBytes("ISO8859-1"), "GBK");
        }
        catch (UnsupportedEncodingException var3_3) {
            var3_3.printStackTrace();
            return stringBuilder.toString();
        }
    }

    private void a(ContentValues object) {
        if (object.containsKey("data1") && !TextUtils.isEmpty((CharSequence)(object = object.getAsString("data1")))) {
            this.d = object;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Set<g> set, ContentValues contentValues) {
        String string2;
        if (contentValues.containsKey("data1") && !TextUtils.isEmpty((CharSequence)(string2 = contentValues.getAsString("data1")))) {
            Object object = contentValues.getAsInteger("data2");
            int n2 = object != null ? object.intValue() : 1;
            object = contentValues.getAsString("data3");
            boolean bl2 = (contentValues = contentValues.getAsBoolean("is_primary")) != null ? contentValues.booleanValue() : false;
            set.add(new g(string2, n2, (String)object, bl2));
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean a(Uri var1_1, int var2_4, afb var3_5, afa var4_12, boolean var5_15, List<String> var6_16) {
        var10_17 = this.c.getContentResolver();
        var9_18 = var10_17.openInputStream(var1_1);
        this.n = new afg(var2_4);
        var8_19 = var9_18;
        this.o = new afk();
        var8_19 = var9_18;
        this.n.a(this.o);
        var8_19 = var9_18;
        this.n.a(var9_18, (afb)var3_5);
        if (var9_18 == null) return true;
        var9_18.close();
        return true;
        catch (afs var3_6) {
            var8_19 = var9_18;
            try {
                try {
                    var9_18.close();
                }
                catch (IOException var3_11) {}
                var8_19 = var9_18;
                if (this.o == null) ** GOTO lbl29
                var8_19 = var9_18;
                var7_20 = this.o.e();
                ** GOTO lbl32
lbl29: // 1 sources:
                var8_19 = var9_18;
                var7_20 = aex.b;
lbl32: // 2 sources:
                var8_19 = var9_18;
                var11_21 = new aez(var7_20, null);
                var8_19 = var9_18;
                var11_21.a(var4_12);
                var8_19 = var9_18;
                var8_19 = var3_5 = var10_17.openInputStream(var1_1);
                try {
                    this.n = new afh(var2_4);
                    var8_19 = var3_5;
                    this.o = new afk();
                    var8_19 = var3_5;
                    this.n.a(this.o);
                    var8_19 = var3_5;
                    this.n.a((InputStream)var3_5, var11_21);
                    if (var3_5 == null) return true;
                }
                catch (afs var4_13) {
                    var8_19 = var3_5;
                    throw new afn("vCard with unspported version.");
                }
                try {
                    var3_5.close();
                    return true;
                }
                catch (IOException var1_2) {
                    return true;
                }
            }
            catch (Throwable var3_7) {
                if (var8_19 == null) ** GOTO lbl62
                try {
                    try {
                        var8_19.close();
                    }
                    catch (IOException var4_14) {}
                    try {
                        throw var3_7;
                    }
                    catch (IOException var3_8) {
                        Log.e((String)"VcardTools", (String)("IOException was emitted: " + var3_8.getMessage()));
                        if (var6_16 == null) return false;
                        var6_16.add(var1_1.toString());
                        return false;
                    }
                }
                catch (afr var3_9) {
                    if (var3_9 instanceof afq && var5_15) {
                        throw (afq)var3_9;
                    }
                    if (var6_16 == null) return false;
                    var6_16.add(var1_1.toString());
                    return false;
                }
                catch (afn var3_10) {
                    if (var6_16 == null) return false;
                    var6_16.add(var1_1.toString());
                    return false;
                }
            }
        }
        catch (IOException var1_3) {
            return true;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private final void b(long var1_1) {
        block8 : {
            var4_2 = this.c.getContentResolver();
            var3_5 = ContactsContract.isProfileId((long)var1_1) != false ? ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI : ContactsContract.RawContactsEntity.CONTENT_URI;
            var3_5 = ContactsContract.RawContacts.newEntityIterator((Cursor)var4_2.query(var3_5, null, "contact_id= " + var1_1, null, null));
            try {
                do {
                    if (var3_5.hasNext() == false) return;
                    var4_2 = ((Entity)var3_5.next()).getSubValues().iterator();
lbl8: // 12 sources:
                } while (!var4_2.hasNext());
                var5_6 = ((Entity.NamedContentValues)var4_2.next()).values;
                var6_7 = var5_6.getAsString("mimetype");
                if (!"vnd.android.cursor.item/email_v2".equals((Object)var6_7)) break block8;
                this.b(this.e, var5_6);
                ** GOTO lbl8
            }
            catch (IOException var4_3) {
                return;
            }
        }
        if (!var6_7.equals((Object)"vnd.android.cursor.item/nickname")) ** GOTO lbl20
        this.e(this.k, var5_6);
        ** GOTO lbl8
lbl20: // 1 sources:
        if (!"vnd.android.cursor.item/website".equals((Object)var6_7)) ** GOTO lbl23
        this.f(this.g, var5_6);
        ** GOTO lbl8
lbl23: // 1 sources:
        if (!"vnd.android.cursor.item/phone_v2".equals((Object)var6_7)) ** GOTO lbl26
        this.a(this.h, var5_6);
        ** GOTO lbl8
lbl26: // 1 sources:
        if (!"vnd.android.cursor.item/name".equals((Object)var6_7)) ** GOTO lbl29
        this.a(var5_6);
        ** GOTO lbl8
lbl29: // 1 sources:
        if (!"vnd.android.cursor.item/im".equals((Object)var6_7)) ** GOTO lbl32
        this.c(this.f, var5_6);
        ** GOTO lbl8
lbl32: // 1 sources:
        if (!var6_7.equals((Object)"vnd.android.cursor.item/contact_event")) ** GOTO lbl35
        this.d(this.m, var5_6);
        ** GOTO lbl8
lbl35: // 1 sources:
        if (!var6_7.equals((Object)"vnd.android.cursor.item/note")) ** GOTO lbl38
        this.g(this.l, var5_6);
        ** GOTO lbl8
lbl38: // 1 sources:
        if (!var6_7.equals((Object)"vnd.android.cursor.item/postal-address_v2")) ** GOTO lbl41
        this.h(this.i, var5_6);
        ** GOTO lbl8
lbl41: // 1 sources:
        if (!var6_7.equals((Object)"vnd.android.cursor.item/organization")) ** GOTO lbl8
        this.i(this.j, var5_6);
        ** GOTO lbl8
        finally {
            var3_5.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Set<b> set, ContentValues object) {
        Object object2;
        if (!object.containsKey("data1") || TextUtils.isEmpty((CharSequence)(object2 = object.getAsString("data1")))) {
            return;
        }
        Object object3 = object.getAsInteger("data2");
        int n2 = object3 != null ? object3.intValue() : 1;
        object3 = object.getAsString("data3");
        String string2 = object.getAsString("data4");
        boolean bl2 = object.getAsBoolean("is_primary");
        object = string2 == null ? (object3 != null ? object3 : object2) : string2;
        set.add(new b((String)object2, n2, (String)object, bl2));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(Set<c> set, ContentValues contentValues) {
        String string2;
        if (contentValues.containsKey("data1") && !TextUtils.isEmpty((CharSequence)(string2 = contentValues.getAsString("data1")))) {
            Object object = contentValues.getAsInteger("data5");
            int n2 = object != null ? object.intValue() : 0;
            object = contentValues.getAsString("data6");
            CharSequence charSequence = ContactsContract.CommonDataKinds.Im.getProtocolLabel((Resources)this.c.getResources(), (int)n2, (CharSequence)object);
            Integer n3 = contentValues.getAsInteger("data2");
            int n4 = n3 != null ? n3 : 1;
            boolean bl2 = (contentValues = contentValues.getAsBoolean("is_primary")) != null ? contentValues.booleanValue() : false;
            set.add(new c(n2, (String)object, charSequence, string2, n4, bl2));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(Set<a> set, ContentValues object) {
        Integer n2 = object.getAsInteger("data2");
        if (n2 == null || !n2.equals((Object)3) || !object.containsKey("data1") || TextUtils.isEmpty((CharSequence)(object = object.getAsString("data1")))) {
            return;
        }
        set.add(new a((String)object));
    }

    private void e(Set<d> set, ContentValues object) {
        if (!object.containsKey("data1") || TextUtils.isEmpty((CharSequence)(object = object.getAsString("data1")))) {
            return;
        }
        set.add(new d((String)object));
    }

    private void f(Set<i> set, ContentValues object) {
        if (!object.containsKey("data1") || TextUtils.isEmpty((CharSequence)(object = object.getAsString("data1")))) {
            return;
        }
        set.add(new i((String)object));
    }

    private void g(Set<e> set, ContentValues object) {
        if (!object.containsKey("data1") || TextUtils.isEmpty((CharSequence)(object = object.getAsString("data1").replaceAll("\n", "\r\n")))) {
            return;
        }
        set.add(new e((String)object));
    }

    private void h(Set<h> set, ContentValues object) {
        if (!object.containsKey("data1") || TextUtils.isEmpty((CharSequence)(object = object.getAsString("data1").replaceAll("\n", "\r\n")))) {
            return;
        }
        set.add(new h((String)object));
    }

    private void i(Set<f> set, ContentValues object) {
        if (!object.containsKey("data1") || TextUtils.isEmpty((CharSequence)(object = object.getAsString("data1").replaceAll("\n", "\r\n")))) {
            return;
        }
        set.add(new f((String)object));
    }

    public String a(long l2) {
        Iterator iterator;
        int n2;
        this.b(l2);
        StringBuffer stringBuffer = new StringBuffer();
        Resources resources = this.c.getResources();
        stringBuffer.append(resources.getString(2131493623)).append(":").append(this.d).append("\n");
        if (this.h != null) {
            iterator = this.h.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493625)).append(":").append(((g)iterator.next()).a().replaceAll("-", "")).append("\n");
            }
        }
        if (this.e != null) {
            iterator = this.e.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493621, new Object[]{((b)iterator.next()).a()}));
            }
        }
        if (this.f != null) {
            iterator = this.f.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493622, new Object[]{((c)iterator.next()).b()}));
            }
        }
        if (this.g != null) {
            iterator = this.g.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493626, new Object[]{((i)iterator.next()).a()}));
            }
        }
        if (this.i != null) {
            iterator = this.i.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493817, new Object[]{((h)iterator.next()).a()}));
            }
        }
        if (this.j != null) {
            iterator = this.j.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493816, new Object[]{((f)iterator.next()).a()}));
            }
        }
        if (this.k != null) {
            iterator = this.k.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493814, new Object[]{((d)iterator.next()).a()}));
            }
        }
        if (this.l != null) {
            iterator = this.l.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493815, new Object[]{((e)iterator.next()).a()}));
            }
        }
        if (this.m != null) {
            iterator = this.m.iterator();
            while (iterator.hasNext()) {
                stringBuffer.append(resources.getString(2131493813, new Object[]{((a)iterator.next()).a()}));
            }
        }
        if ((n2 = stringBuffer.length() - 1) >= 0 && stringBuffer.lastIndexOf("\n") == n2) {
            stringBuffer.deleteCharAt(n2);
        }
        return stringBuffer.toString();
    }

    public String a(aey object) {
        int n2;
        Object object2;
        if (object == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Resources resources = this.c.getResources();
        Object object3 = object.k();
        if (object3 != null) {
            stringBuffer.append(resources.getString(2131493623));
            stringBuffer.append(":");
            stringBuffer.append(object3.replaceAll("-", ""));
            stringBuffer.append("\n");
        }
        if ((object3 = object.e()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.m)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)object2.b())) continue;
                stringBuffer.append(resources.getString(2131493625));
                stringBuffer.append(":");
                stringBuffer.append(object2.b().replaceAll("-", ""));
                stringBuffer.append("\n");
            }
        }
        if ((object3 = object.f()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.d)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)object2.b())) continue;
                stringBuffer.append(resources.getString(2131493621, new Object[]{object2.b()}));
            }
        }
        if ((object3 = object.i()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.h)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)object2.b())) continue;
                stringBuffer.append(resources.getString(2131493622, new Object[]{object2.b()}));
            }
        }
        if ((object3 = object.j()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.r)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)object2.b())) continue;
                stringBuffer.append(resources.getString(2131493626, new Object[]{object2.b()}));
            }
        }
        if ((object3 = object.g()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.o)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)(object2 = object2.a(-1073741824)))) continue;
                stringBuffer.append(resources.getString(2131493817, new Object[]{object2}));
            }
        }
        if ((object3 = object.h()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.l)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)(object2 = object2.b()))) continue;
                stringBuffer.append(resources.getString(2131493816, new Object[]{object2}));
            }
        }
        if ((object3 = object.b()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.j)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)(object2 = object2.b()))) continue;
                stringBuffer.append(resources.getString(2131493814, new Object[]{object2}));
            }
        }
        if ((object3 = object.d()) != null) {
            object3 = object3.iterator();
            while (object3.hasNext()) {
                object2 = (aey.k)object3.next();
                if (object2 == null || TextUtils.isEmpty((CharSequence)(object2 = object2.b()))) continue;
                stringBuffer.append(resources.getString(2131493815, new Object[]{object2}));
            }
        }
        if (!TextUtils.isEmpty((CharSequence)(object = object.c()))) {
            stringBuffer.append(resources.getString(2131493813, new Object[]{object}));
        }
        if ((n2 = stringBuffer.length() - 1) >= 0 && stringBuffer.lastIndexOf("\n") == n2) {
            stringBuffer.deleteCharAt(n2);
        }
        return stringBuffer.toString();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Uri uri, afa afa2) {
        aez aez2 = new aez(-1073741824, null);
        aez2.a(afa2);
        try {
            this.a(uri, 0, aez2, afa2, true, null);
            return;
        }
        catch (afq var4_5) {
            try {
                int n2 = this.o != null ? this.o.e() : aex.b;
                aez aez3 = new aez(n2, null);
                aez3.a(afa2);
                this.a(uri, n2, aez3, afa2, false, null);
                return;
            }
            catch (afq var1_2) {
                Log.e((String)"VcardTools", (String)("Must not reach here. " + (Object)((Object)var1_2)));
                return;
            }
        }
    }

    public static class a {
        private final String a;

        public a(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof a)) {
                return false;
            }
            object = (a)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "birthday: " + this.a;
        }
    }

    public static class b {
        private final String a;
        private final int b;
        private final String c;
        private final boolean d;

        public b(String string2, int n2, String string3, boolean bl2) {
            this.b = n2;
            this.a = string2;
            this.c = string3;
            this.d = bl2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof b)) {
                return false;
            }
            object = (b)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }
    }

    public static class c {
        private final String a;
        private final int b;
        private final String c;
        private final CharSequence d;
        private final int e;
        private final boolean f;

        public c(int n2, String charSequence, CharSequence charSequence2, String string2, int n3, boolean bl2) {
            this.b = n2;
            this.c = charSequence;
            charSequence = charSequence2;
            if (TextUtils.isEmpty((CharSequence)charSequence2)) {
                charSequence = "Im";
            }
            this.d = charSequence;
            this.e = n3;
            this.a = string2;
            this.f = bl2;
        }

        public CharSequence a() {
            return this.d;
        }

        public String b() {
            return this.a;
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof c)) {
                return false;
            }
            object = (c)object;
            if (!TextUtils.equals((CharSequence)this.a(), (CharSequence)object.a())) return false;
            if (TextUtils.equals((CharSequence)this.a, (CharSequence)object.a)) return true;
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        public int hashCode() {
            int n2 = 0;
            int n3 = this.b;
            int n4 = this.a != null ? this.a.hashCode() : 0;
            if (this.d != null) {
                n2 = this.d.hashCode();
            }
            return (n4 + n3 * 31) * 31 + n2;
        }

        public String toString() {
            return String.format((String)"type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", (Object[])new Object[]{this.e, this.b, this.c, this.a, this.f});
        }
    }

    public static class d {
        private final String a;

        public d(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (!(object instanceof d)) {
                return false;
            }
            object = (d)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "nickname: " + this.a;
        }
    }

    public static class e {
        public final String a;

        public e(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof e)) {
                return false;
            }
            object = (e)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "note: " + this.a;
        }
    }

    public static class f {
        private final String a;

        public f(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof f)) {
                return false;
            }
            object = (f)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Organization: " + this.a;
        }
    }

    public static class g {
        private final String a;
        private final int b;
        private final String c;
        private boolean d;

        public g(String string2, int n2, String string3, boolean bl2) {
            this.a = string2;
            this.b = n2;
            this.c = string3;
            this.d = bl2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof g)) {
                return false;
            }
            object = (g)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return String.format((String)"type: %d, data: %s, label: %s, isPrimary: %s", (Object[])new Object[]{this.b, this.a, this.c, this.d});
        }
    }

    public static class h {
        private final String a;

        public h(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof h)) {
                return false;
            }
            object = (h)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "postal: " + this.a;
        }
    }

    public static class i {
        private final String a;

        public i(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof i)) {
                return false;
            }
            object = (i)object;
            return TextUtils.equals((CharSequence)this.a, (CharSequence)object.a);
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "website: " + this.a;
        }
    }

}

