/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.HashMap
 *  javax.xml.parsers.SAXParser
 *  javax.xml.parsers.SAXParserFactory
 *  miui.os.Build
 *  org.xml.sax.SAXException
 */
package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.RegionModel;
import com.android.mms.ui.SimplePduPage;
import com.android.mms.ui.SimplePduPart;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import miui.os.Build;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimplePduDoc {
    private static final String[] PART_PROJECTION = new String[]{"_id", "chset", "cd", "cid", "cl", "ct", "fn", "name", "text"};
    private Map<String, SimplePduPart> mCidParts = new HashMap();
    private Integer mCompleteSize;
    private ContentResolver mContentResolver;
    private Context mContext;
    private SimplePduPart mDocPart;
    private int mDocType = -1;
    private EncodedStringValue mFrom;
    private long mId;
    private boolean mIsSlideShow = false;
    private LayoutModel mLayoutModel;
    private ArrayList<SimplePduPage> mPages;
    private ArrayList<SimplePduPart> mParts = new ArrayList();
    private Map<String, SimplePduPart> mSrcParts = new HashMap();
    private Uri mUri;

    public SimplePduDoc(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        if (Build.IS_CM_CUSTOMIZATION || Build.IS_CU_CUSTOMIZATION) {
            this.mLayoutModel = new LayoutModel();
        }
    }

    /*
     * Exception decompiling
     */
    private void loadAddress() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:486)
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

    private boolean loadAttachments() {
        boolean bl = false;
        if (!this.mParts.isEmpty()) {
            this.mPages = new ArrayList();
            if (this.loadAttachments(this.mPages, this.mParts) || this.mPages.size() > 1) {
                bl = true;
            }
            this.mIsSlideShow = bl;
            return true;
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    private boolean loadAttachments(ArrayList<SimplePduPage> var1_1, ArrayList<SimplePduPart> var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Statement already marked as first in another block
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.markFirstStatementInBlock(Op03SimpleStatement.java:412)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.markWholeBlock(Misc.java:219)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.considerAsSimpleIf(ConditionalRewriter.java:619)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.identifyNonjumpingConditionals(ConditionalRewriter.java:45)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:669)
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

    private byte[] loadByteArray(Cursor object, int n) {
        if ((object = object.getString(n)) != null) {
            return MiuiPduPersister.getBytes((String)object);
        }
        return null;
    }

    private Integer loadInt(Cursor cursor, int n) {
        try {
            n = cursor.getInt(n);
        }
        catch (NumberFormatException var1_2) {
            return null;
        }
        return n;
    }

    /*
     * Exception decompiling
     */
    private void loadParts() throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 4[WHILELOOP]
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
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean loadSlides() {
        block7 : {
            if (this.mDocType != 0) {
                return false;
            }
            this.mPages = new ArrayList();
            var5_1 = new ArrayList<SimplePduPart>(this.mParts);
            try {
                var6_5 = SAXParserFactory.newInstance().newSAXParser();
                var7_6 = this.mDocPart.getData();
                if (var7_6 == null) break block7;
                var6_5.parse((InputStream)new ByteArrayInputStream((byte[])var7_6), var5_1);
            }
            catch (SAXException var5_2) {
                return false;
            }
            catch (ParserConfigurationException var5_3) {
                return false;
            }
            catch (IOException var5_4) {
                return false;
            }
        }
        var4_7 = var5_1.isSlideshow();
        var5_1 = var5_1.getRestParts();
        var3_8 = var4_7;
        if (var5_1 == null) ** GOTO lbl33
        var3_8 = var4_7;
        if (var5_1.isEmpty()) ** GOTO lbl33
        var4_7 = this.loadAttachments(this.mPages, (ArrayList<SimplePduPart>)var5_1) != false || var4_7 != false;
        var3_8 = var4_7;
        if (this.mParts.size() != 2) ** GOTO lbl34
        var3_8 = var4_7;
        if (this.mPages.size() == 2) {
            var5_1 = (SimplePduPart)this.mParts.get(0);
            var6_5 = (SimplePduPart)this.mParts.get(1);
            var1_9 = var5_1.getAttachmentType();
            var2_10 = var6_5.getAttachmentType();
            if (var1_9 == 0 && var2_10 != 0 || var2_10 == 0 && var1_9 != 0) {
                this.mPages.clear();
                var7_6 = new SimplePduPage();
                var7_6.addPart((SimplePduPart)var5_1);
                var7_6.addPart((SimplePduPart)var6_5);
                this.mPages.add(var7_6);
            }
            var3_8 = false;
        }
lbl33: // 5 sources:
        if (var3_8) ** GOTO lbl35
lbl34: // 2 sources:
        if (this.mPages.size() <= 1) ** GOTO lbl43
lbl35: // 2 sources:
        var3_8 = true;
        ** GOTO lbl44
lbl43: // 1 sources:
        var3_8 = false;
lbl44: // 2 sources:
        this.mIsSlideShow = var3_8;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean canShow() {
        boolean bl = false;
        int n = this.getPageAppearanceType(0);
        if (this.isSlideShow()) return true;
        boolean bl2 = bl;
        if (n == -1) return bl2;
        bl2 = bl;
        if (n == 0) return bl2;
        return true;
    }

    public int getCompleteSize() {
        if (this.mCompleteSize == null) {
            this.mCompleteSize = 0;
            if (this.mDocPart != null && this.mDocPart.loadData()) {
                this.mCompleteSize = this.mCompleteSize + this.mDocPart.getData().length;
            }
            for (SimplePduPart simplePduPart : this.mParts) {
                if (!simplePduPart.loadData()) continue;
                this.mCompleteSize = this.mCompleteSize + simplePduPart.getData().length;
            }
        }
        return this.mCompleteSize;
    }

    public SimplePduPage getPage(int n) {
        if (this.mPages != null && n < this.getPageSize()) {
            return (SimplePduPage)this.mPages.get(n);
        }
        return null;
    }

    public SimplePduPart getPageAppearancePart(int n) {
        if (this.mPages != null && n < this.getPageSize()) {
            return this.getPage(n).getPageAppearancePart();
        }
        return null;
    }

    public int getPageAppearanceType(int n) {
        SimplePduPart simplePduPart = this.getPageAppearancePart(n);
        if (simplePduPart != null) {
            return simplePduPart.getAttachmentType();
        }
        return -1;
    }

    public int getPageSize() {
        if (this.mPages != null) {
            return this.mPages.size();
        }
        return 0;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean isLayoutImageBottom() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.mLayoutModel != null) {
            bl2 = bl;
            if (this.mLayoutModel.getImageRegion().getTop() > this.mLayoutModel.getTextRegion().getTop()) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public boolean isSlideShow() {
        return this.mIsSlideShow;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean load(Uri var1_1) {
        block3 : {
            var3_3 = false;
            this.mUri = var1_1;
            this.mId = ContentUris.parseId((Uri)var1_1);
            this.loadAddress();
            try {
                this.loadParts();
                if (this.mDocType != 0) break block3;
            }
            catch (MmsException var1_2) {
                return false;
            }
            if (!this.loadSlides()) {
                this.loadAttachments();
            }
            ** GOTO lbl15
        }
        this.loadAttachments();
lbl15: // 2 sources:
        var2_4 = var3_3;
        if (this.mPages == null) return var2_4;
        var2_4 = var3_3;
        if (this.mPages.isEmpty() != false) return var2_4;
        return true;
    }

    private class SlideHandler
    extends DefaultHandler {
        private SimplePduPage mCurrPage;
        private ArrayList<SimplePduPart> mPartsRecord;
        private boolean mStartPart;
        private int mediaCount;
        private int textCount;

        public SlideHandler(ArrayList<SimplePduPart> arrayList) {
            this.mStartPart = false;
            this.mPartsRecord = (ArrayList)arrayList.clone();
            this.textCount = 0;
            this.mediaCount = 0;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void parseRegion(Attributes object) {
            String string2 = object.getValue("id");
            String string3 = object.getValue("top");
            int n = 0;
            if (TextUtils.isEmpty((CharSequence)string3)) return;
            if (string3.endsWith("px")) {
                object = string3.substring(0, string3.indexOf("px"));
            } else {
                object = string3;
                if (string3.endsWith("%")) {
                    object = string3.substring(0, string3.length() - 1);
                }
            }
            if (TextUtils.isEmpty((CharSequence)object)) return;
            try {
                int n2;
                n = n2 = Integer.parseInt((String)object);
            }
            catch (Exception var1_2) {
                Log.e((String)"Mms/SimplePduDoc", (String)("parse number error! " + (Object)((Object)var1_2)));
            }
            if ("Image".equals((Object)string2)) {
                if (SimplePduDoc.this.mLayoutModel == null) return;
                {
                    SimplePduDoc.this.mLayoutModel.getImageRegion().setTop(n);
                    return;
                }
            }
            if (!"Text".equals((Object)string2) || SimplePduDoc.this.mLayoutModel == null) {
                return;
            }
            SimplePduDoc.this.mLayoutModel.getTextRegion().setTop(n);
        }

        @Override
        public void characters(char[] arrc, int n, int n2) {
        }

        @Override
        public void endElement(String string2, String string3, String string4) throws SAXException {
            if ("par".equals((Object)string3)) {
                this.mStartPart = false;
                if (this.mCurrPage != null) {
                    if (this.mCurrPage.getPartSize() != 0 && !this.mCurrPage.isEmpty() || Build.IS_CM_CUSTOMIZATION_TEST) {
                        SimplePduDoc.this.mPages.add((Object)this.mCurrPage);
                    }
                    this.mCurrPage = null;
                }
            }
        }

        public ArrayList<SimplePduPart> getRestParts() {
            return this.mPartsRecord;
        }

        public boolean isSlideshow() {
            if (this.textCount > 1 || this.mediaCount > 1 || SimplePduDoc.this.mPages.size() > 1) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void startElement(String object, String string2, String string3, Attributes attributes) throws SAXException {
            if ("par".equals((Object)string2)) {
                this.mStartPart = true;
                this.mCurrPage = new SimplePduPage();
                return;
            } else if (this.mStartPart) {
                object = attributes.getValue("src");
                if (object == null) return;
                {
                    int n;
                    if (object.startsWith("cid:")) {
                        object = object.substring("cid:".length());
                        object = (SimplePduPart)((Object)SimplePduDoc.this.mCidParts.get(object));
                    } else {
                        object = (SimplePduPart)((Object)SimplePduDoc.this.mSrcParts.get(object));
                    }
                    if (object == null || (n = object.getAttachmentType()) == -1) return;
                    {
                        this.mCurrPage.addPart((SimplePduPart)((Object)object));
                        for (int i = this.mPartsRecord.size() - 1; i >= 0; --i) {
                            if (!Arrays.equals((byte[])((SimplePduPart)((Object)this.mPartsRecord.get(i))).getContentLocation(), (byte[])object.getContentLocation())) continue;
                            this.mPartsRecord.remove(i);
                        }
                        if (n == 0) {
                            ++this.textCount;
                            return;
                        }
                        ++this.mediaCount;
                        return;
                    }
                }
            } else {
                if (!"region".equals((Object)string2) || !Build.IS_CM_CUSTOMIZATION && !Build.IS_CU_CUSTOMIZATION) return;
                {
                    this.parseRegion(attributes);
                    return;
                }
            }
        }
    }

}

