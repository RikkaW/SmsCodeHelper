/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.Pair
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.android.mms.model;

import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ContactMessage {
    private ArrayList<ContactRecord> mContactRecords;
    private String mMessage;
    private ArrayList<Object> mMessageParts;
    private int mPosition;
    private HashMap<String, String> mTypeLabelMap;

    public ContactMessage(String string, HashMap<String, String> hashMap) {
        this.mMessage = string;
        this.mContactRecords = new ArrayList();
        this.mPosition = 0;
        this.mTypeLabelMap = hashMap;
    }

    public void addRecord(String object, String string, int n, int n2) {
        if (n >= this.mPosition && n < n2) {
            ContactRecord contactRecord = null;
            if (!this.mContactRecords.isEmpty()) {
                contactRecord = (ContactRecord)this.mContactRecords.get(this.mContactRecords.size() - 1);
            }
            String string2 = this.mMessage.substring(this.mPosition, n);
            if (contactRecord == null || !contactRecord.addRecord((String)object, string, n, n2, string2)) {
                object = new ContactRecord((String)object, string, n, n2);
                this.mContactRecords.add(object);
            }
        }
        this.mPosition = n2;
    }

    public int count() {
        return this.mContactRecords.size();
    }

    public ArrayList<ContactRecord> getContactRecords() {
        return this.mContactRecords;
    }

    public ArrayList<Object> split() {
        if (this.mMessageParts == null) {
            this.mMessageParts = new ArrayList();
            int n = 0;
            Object object = this.mContactRecords.iterator();
            while (object.hasNext()) {
                String string;
                ContactRecord contactRecord = (ContactRecord)object.next();
                int n2 = contactRecord.mStart;
                int n3 = contactRecord.mEnd;
                if (n2 > n && !TextUtils.isEmpty((CharSequence)(string = this.mMessage.substring(n, n2).trim()))) {
                    this.mMessageParts.add((Object)string);
                }
                this.mMessageParts.add((Object)contactRecord);
                n = n3;
            }
            if (n < this.mMessage.length() && !TextUtils.isEmpty((CharSequence)(object = this.mMessage.substring(n, this.mMessage.length()).trim()))) {
                this.mMessageParts.add(object);
            }
        }
        return this.mMessageParts;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.mMessage);
        for (int i = this.mContactRecords.size() - 1; i >= 0; --i) {
            ContactRecord contactRecord = (ContactRecord)this.mContactRecords.get(i);
            stringBuilder.replace(contactRecord.mStart, contactRecord.mEnd, contactRecord.toString());
        }
        return stringBuilder.toString();
    }

    public class ContactRecord {
        private ArrayList<Pair<String, String>> mContact;
        int mEnd;
        int mStart;

        public ContactRecord(String string, String string2, int n, int n2) {
            this.mContact = new ArrayList();
            this.mContact.add((Object)new Pair((Object)string, (Object)string2));
            this.mStart = n;
            this.mEnd = n2;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean addRecord(String string, String string2, int n, int n2, String string3) {
            if ("vnd.android.cursor.item/name".equalsIgnoreCase(string) || this.mEnd != n && TextUtils.getTrimmedLength((CharSequence)string3) != 0) {
                return false;
            }
            this.mContact.add((Object)new Pair((Object)string, (Object)string2));
            this.mEnd = n2;
            return true;
        }

        public ArrayList<Pair<String, String>> getContactRecord() {
            return this.mContact;
        }

        public String getPreviewString() {
            if (this.mContact.size() >= 1) {
                return (String)((Pair)this.mContact.get((int)0)).second;
            }
            return "";
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Pair pair : this.mContact) {
                stringBuilder.append("[" + (String)ContactMessage.this.mTypeLabelMap.get(pair.first) + "] " + (String)pair.second + ";\n");
            }
            if (stringBuilder.length() != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            return stringBuilder.toString();
        }
    }

}

