/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.android.mms.dom.smil;

import android.util.Log;
import com.android.mms.dom.smil.TimeImpl;
import com.android.mms.dom.smil.TimeListImpl;
import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.smil.ElementTime;
import org.w3c.dom.smil.SMILElement;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public abstract class ElementTimeImpl
implements ElementTime {
    final SMILElement mSmilElement;

    ElementTimeImpl(SMILElement sMILElement) {
        this.mSmilElement = sMILElement;
    }

    private boolean beginAndEndAreZero() {
        Object object = this.getBegin();
        Object object2 = this.getEnd();
        if (object.getLength() == 1 && object2.getLength() == 1) {
            object = object.item(0);
            object2 = object2.item(0);
            if (object.getOffset() == 0.0 && object2.getOffset() == 0.0) {
                return true;
            }
            return false;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public TimeList getBegin() {
        String[] arrstring = this.mSmilElement.getAttribute("begin").split(";");
        ArrayList arrayList = new ArrayList();
        for (int n = 0; n < arrstring.length; ++n) {
            try {
                arrayList.add((Object)new TimeImpl(arrstring[n], this.getBeginConstraints()));
                continue;
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        if (arrayList.size() == 0) {
            arrayList.add((Object)new TimeImpl("0", 255));
        }
        return new TimeListImpl(arrayList);
    }

    int getBeginConstraints() {
        return 255;
    }

    @Override
    public float getDur() {
        float f2;
        block3 : {
            String string;
            f2 = 0.0f;
            try {
                string = this.mSmilElement.getAttribute("dur");
                if (string == null) break block3;
            }
            catch (IllegalArgumentException var2_3) {
                return 0.0f;
            }
            f2 = TimeImpl.parseClockValue(string);
            f2 /= 1000.0f;
        }
        return f2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public TimeList getEnd() {
        int n;
        ArrayList arrayList = new ArrayList();
        String[] arrstring = this.mSmilElement.getAttribute("end").split(";");
        int n2 = arrstring.length;
        if (n2 != 1 || arrstring[0].length() != 0) {
            for (n = 0; n < n2; ++n) {
                try {
                    arrayList.add((Object)new TimeImpl(arrstring[n], this.getEndConstraints()));
                    continue;
                }
                catch (IllegalArgumentException var6_5) {
                    Log.e((String)"ElementTimeImpl", (String)"Malformed time value.", (Throwable)var6_5);
                }
            }
        }
        if (arrayList.size() != 0) return new TimeListImpl(arrayList);
        float f2 = this.getDur();
        if (f2 < 0.0f) {
            arrayList.add((Object)new TimeImpl("indefinite", this.getEndConstraints()));
            return new TimeListImpl(arrayList);
        }
        arrstring = this.getBegin();
        n = 0;
        while (n < arrstring.getLength()) {
            arrayList.add((Object)new TimeImpl("" + (arrstring.item(n).getResolvedOffset() + (double)f2) + "s", this.getEndConstraints()));
            ++n;
        }
        return new TimeListImpl(arrayList);
    }

    int getEndConstraints() {
        return 255;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public short getFill() {
        String string = this.mSmilElement.getAttribute("fill");
        if (string.equalsIgnoreCase("freeze")) {
            return 1;
        }
        if (string.equalsIgnoreCase("remove")) {
            return 0;
        }
        if (string.equalsIgnoreCase("hold")) {
            return 1;
        }
        if (string.equalsIgnoreCase("transition")) {
            return 1;
        }
        if (!string.equalsIgnoreCase("auto")) {
            short s;
            short s2 = s = this.getFillDefault();
            if (s != 2) return s2;
        }
        if (this.mSmilElement.getAttribute("dur").length() == 0 && this.mSmilElement.getAttribute("end").length() == 0 && this.mSmilElement.getAttribute("repeatCount").length() == 0) {
            if (this.mSmilElement.getAttribute("repeatDur").length() == 0) return 1;
        }
        if (!this.beginAndEndAreZero()) return 0;
        return 1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public short getFillDefault() {
        short s = 1;
        Object object = this.mSmilElement.getAttribute("fillDefault");
        if (object.equalsIgnoreCase("remove")) {
            return 0;
        }
        short s2 = s;
        if (object.equalsIgnoreCase("freeze")) return s2;
        if (object.equalsIgnoreCase("auto")) {
            return 2;
        }
        s2 = s;
        if (object.equalsIgnoreCase("hold")) return s2;
        s2 = s;
        if (object.equalsIgnoreCase("transition")) return s2;
        object = this.getParentElementTime();
        if (object != null) return ((ElementTimeImpl)object).getFillDefault();
        return 2;
    }

    abstract ElementTime getParentElementTime();

    @Override
    public void setDur(float f2) throws DOMException {
        this.mSmilElement.setAttribute("dur", Integer.toString((int)((int)(1000.0f * f2))) + "ms");
    }
}

