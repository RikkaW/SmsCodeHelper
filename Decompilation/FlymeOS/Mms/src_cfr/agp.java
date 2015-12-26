/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.GpsStatus
 *  android.location.GpsStatus$Listener
 *  android.location.GpsStatus$NmeaListener
 *  android.os.Message
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.Locale
 */
import android.location.GpsStatus;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class agp
implements GpsStatus.Listener,
GpsStatus.NmeaListener {
    private long a;
    private long b;
    private boolean c;
    private List d;
    private String e;
    private String f;
    private String g;
    private /* synthetic */ aie h;

    protected agp(aie aie2) {
        this.h = aie2;
        this.a = 0;
        this.b = 0;
        this.c = false;
        this.d = new ArrayList();
        this.e = null;
        this.f = null;
        this.g = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void a(String string2) {
        if (System.currentTimeMillis() - this.b > 400 && this.c && this.d.size() > 0) {
            block11 : {
                try {
                    aic aic2 = new aic(this.d, this.e, null, this.g);
                    if (aic2.a()) {
                        aie.e(this.h, aie.a(this.h, aic2, aie.o(this.h)));
                        if (aie.p(this.h) > 0) {
                            aie.b(this.h, String.format((Locale)Locale.CHINA, (String)"&nmea=%.1f|%.1f&g_tp=%d", (Object[])new Object[]{aic2.c(), aic2.b(), aie.p(this.h)}));
                        }
                        break block11;
                    }
                    aie.e(this.h, 0);
                }
                catch (Exception var2_3) {
                    aie.e(this.h, 0);
                }
            }
            this.d.clear();
            this.g = null;
            this.f = null;
            this.e = null;
            this.c = false;
        }
        if (string2.startsWith("$GPGGA")) {
            this.c = true;
            this.e = string2.trim();
        } else if (string2.startsWith("$GPGSV")) {
            this.d.add(string2.trim());
        } else if (string2.startsWith("$GPGSA")) {
            this.g = string2.trim();
        }
        this.b = System.currentTimeMillis();
    }

    /*
     * Exception decompiling
     */
    public final void onGpsStatusChanged(int var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [7[CASE]], but top level block is 3[TRYBLOCK]
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
    public final void onNmeaReceived(long l2, String string2) {
        if (!aie.a) {
            return;
        }
        if (string2 == null) return;
        try {
            if (string2.equals((Object)"") || string2.length() < 9 || string2.length() > 150) return;
            aie.n(this.h).sendMessage(aie.n(this.h).obtainMessage(1, (Object)string2));
            return;
        }
        catch (Exception var3_3) {
            // empty catch block
        }
    }
}

