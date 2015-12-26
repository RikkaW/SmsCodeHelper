/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.android.mms.service;

import android.content.ContentValues;
import android.util.Log;
import com.android.mms.service.MmsConfig;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MmsConfigXmlProcessor {
    private final XmlPullParser mInputParser;
    private final StringBuilder mLogStringBuilder = new StringBuilder();
    private MmsConfigHandler mMmsConfigHandler;

    private MmsConfigXmlProcessor(XmlPullParser xmlPullParser) {
        this.mInputParser = xmlPullParser;
        this.mMmsConfigHandler = null;
    }

    private int advanceToNextEvent(int n) throws XmlPullParserException, IOException {
        int n2;
        while ((n2 = this.mInputParser.next()) != n && n2 != 1) {
        }
        return n2;
    }

    public static MmsConfigXmlProcessor get(XmlPullParser xmlPullParser) {
        return new MmsConfigXmlProcessor(xmlPullParser);
    }

    private void processMmsConfig() throws IOException, XmlPullParserException {
        int n;
        do {
            if ((n = this.mInputParser.next()) == 4) {
                continue;
            }
            if (n != 2) break;
            this.processMmsConfigKeyValue();
        } while (true);
        if (n == 3) {
            return;
        }
        throw new XmlPullParserException("MmsConfig: expecting start or end tag @" + this.xmlParserDebugContext());
    }

    private void processMmsConfigKeyValue() throws IOException, XmlPullParserException {
        String string = this.mInputParser.getAttributeValue(null, "name");
        String string2 = this.mInputParser.getName();
        int n = this.mInputParser.next();
        String string3 = null;
        int n2 = n;
        if (n == 4) {
            string3 = this.mInputParser.getText();
            n2 = this.mInputParser.next();
        }
        if (n2 != 3) {
            throw new XmlPullParserException("MmsConfigXmlProcessor: expecting end tag @" + this.xmlParserDebugContext());
        }
        if (MmsConfig.isValidKey(string, string2)) {
            if (this.mMmsConfigHandler != null) {
                this.mMmsConfigHandler.process(string, string3, string2);
            }
            return;
        }
        Log.w((String)"MmsService", (String)("MmsConfig: invalid key=" + string + " or type=" + string2));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String xmlParserDebugContext() {
        int n;
        block6 : {
            this.mLogStringBuilder.setLength(0);
            if (this.mInputParser == null) return "Unknown";
            n = this.mInputParser.getEventType();
            this.mLogStringBuilder.append(MmsConfigXmlProcessor.xmlParserEventString(n));
            if (n == 2 || n == 3) break block6;
            if (n != 4) return this.mLogStringBuilder.toString();
        }
        this.mLogStringBuilder.append('<').append(this.mInputParser.getName());
        n = 0;
        do {
            if (n >= this.mInputParser.getAttributeCount()) break;
            this.mLogStringBuilder.append(' ').append(this.mInputParser.getAttributeName(n)).append('=').append(this.mInputParser.getAttributeValue(n));
            ++n;
            continue;
            break;
        } while (true);
        try {
            this.mLogStringBuilder.append("/>");
            return this.mLogStringBuilder.toString();
        }
        catch (XmlPullParserException var2_3) {
            Log.e((String)"MmsService", (String)("xmlParserDebugContext: " + (Object)var2_3), (Throwable)var2_3);
        }
        return "Unknown";
    }

    private static String xmlParserEventString(int n) {
        switch (n) {
            default: {
                return Integer.toString((int)n);
            }
            case 0: {
                return "START_DOCUMENT";
            }
            case 1: {
                return "END_DOCUMENT";
            }
            case 2: {
                return "START_TAG";
            }
            case 3: {
                return "END_TAG";
            }
            case 4: 
        }
        return "TEXT";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void process() {
        try {
            if (this.advanceToNextEvent(2) != 2) {
                throw new XmlPullParserException("MmsConfigXmlProcessor: expecting start tag @" + this.xmlParserDebugContext());
            }
            new ContentValues();
            if (!"mms_config".equals((Object)this.mInputParser.getName())) return;
            {
                this.processMmsConfig();
                return;
            }
        }
        catch (IOException var1_1) {
            Log.e((String)"MmsService", (String)("MmsConfigXmlProcessor: I/O failure " + (Object)((Object)var1_1)), (Throwable)var1_1);
            return;
        }
        catch (XmlPullParserException var1_2) {
            Log.e((String)"MmsService", (String)("MmsConfigXmlProcessor: parsing failure " + (Object)var1_2), (Throwable)var1_2);
            return;
        }
    }

    public MmsConfigXmlProcessor setMmsConfigHandler(MmsConfigHandler mmsConfigHandler) {
        this.mMmsConfigHandler = mmsConfigHandler;
        return this;
    }

    public static interface MmsConfigHandler {
        public void process(String var1, String var2, String var3);
    }

}

