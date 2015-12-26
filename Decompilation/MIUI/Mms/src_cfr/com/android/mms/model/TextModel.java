/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Log
 *  com.google.android.mms.pdu.CharacterSets
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.model;

import android.content.Context;
import android.util.Log;
import com.android.mms.model.RegionMediaModel;
import com.android.mms.model.RegionModel;
import com.google.android.mms.pdu.CharacterSets;
import java.io.UnsupportedEncodingException;
import org.w3c.dom.events.Event;

public class TextModel
extends RegionMediaModel {
    private final int mCharset;
    private CharSequence mText;

    /*
     * Enabled aggressive block sorting
     */
    public TextModel(Context context, String string, String string2, int n, byte[] arrby, RegionModel regionModel) {
        byte[] arrby2 = arrby != null ? arrby : new byte[]{};
        super(context, "text", string, string2, arrby2, regionModel);
        int n2 = n;
        if (n == 0) {
            n2 = 4;
        }
        this.mCharset = n2;
        this.mText = this.extractTextFromData(arrby);
    }

    public TextModel(Context context, String string, String string2, RegionModel regionModel) {
        this(context, string, string2, 106, new byte[0], regionModel);
    }

    private CharSequence extractTextFromData(byte[] arrby) {
        if (arrby != null) {
            try {
                if (this.mCharset == 0) {
                    return new String(arrby);
                }
                String string = new String(arrby, CharacterSets.getMimeName((int)this.mCharset));
                return string;
            }
            catch (UnsupportedEncodingException var2_3) {
                Log.e((String)"Mms/text", (String)("Unsupported encoding: " + this.mCharset), (Throwable)var2_3);
                return new String(arrby);
            }
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     */
    public void cloneText() {
        String string = this.mText != null ? this.mText.toString() : "";
        this.mText = new String(string);
    }

    public int getCharset() {
        return this.mCharset;
    }

    public String getText() {
        if (this.mText == null) {
            this.mText = this.extractTextFromData(this.getData());
        }
        if (!(this.mText instanceof String)) {
            this.mText = this.mText.toString();
        }
        return this.mText.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleEvent(Event event) {
        if (event.getType().equals((Object)"SmilMediaStart")) {
            this.mVisible = true;
        } else if (this.mFill != 1) {
            this.mVisible = false;
        }
        this.notifyModelChanged(false);
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence;
        this.notifyModelChanged(true);
    }
}

