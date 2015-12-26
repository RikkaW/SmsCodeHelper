/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
 *  android.preference.PreferenceManager
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  java.io.BufferedReader
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Locale
 *  java.util.StringTokenizer
 *  java.util.Vector
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.mms.LogTag;
import com.android.mms.ui.PhraseListItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

public class PhraseListAdapter
extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private final int mItemLayout;
    private SmsPhraseData mSmsPhraseData;

    public PhraseListAdapter(Context context, int n) {
        this.mContext = context;
        this.mItemLayout = n;
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }

    public void addPhrase(String string2) {
        if (this.mSmsPhraseData == null) {
            return;
        }
        this.mSmsPhraseData.addPhrase(string2);
        this.notifyDataSetChanged();
    }

    public void deletePhrase(int n) {
        if (this.mSmsPhraseData == null) {
            return;
        }
        this.mSmsPhraseData.deletePhrase(n);
        this.notifyDataSetChanged();
    }

    public int getCount() {
        if (this.mSmsPhraseData == null) {
            return 0;
        }
        return this.mSmsPhraseData.getCount();
    }

    public Object getItem(int n) {
        if (this.mSmsPhraseData == null) {
            return null;
        }
        return this.mSmsPhraseData.getPhrase(n);
    }

    public long getItemId(int n) {
        return n;
    }

    public View getView(int n, View view, ViewGroup viewGroup) {
        if (this.mSmsPhraseData == null) {
            return null;
        }
        if (view == null) {
            view = this.mInflater.inflate(this.mItemLayout, viewGroup, false);
        }
        if (view instanceof PhraseListItem) {
            ((PhraseListItem)view).bind(this.mSmsPhraseData.getPhrase(n));
            return view;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isFull() {
        if (this.mSmsPhraseData == null || this.mSmsPhraseData.getCount() <= 50) {
            return false;
        }
        return true;
    }

    public void load() {
        this.mSmsPhraseData = new SmsPhraseData();
        this.notifyDataSetChanged();
    }

    public void setPhrase(int n, String string2) {
        if (this.mSmsPhraseData == null) {
            return;
        }
        this.mSmsPhraseData.setPhrase(n, string2);
        this.notifyDataSetChanged();
    }

    private class SmsPhraseData {
        private final String PREF_SMS_PHRASE_DATA;
        private final String PREF_SMS_PHRASE_DATA_COUNT;
        private final String PREF_SMS_PHRASE_VERSION;
        private SharedPreferences mPref;
        private Vector<String> mSmsPhrases;
        private final String separator;

        public SmsPhraseData() {
            this.PREF_SMS_PHRASE_VERSION = "sms_phrase_version";
            this.separator = "\t";
            this.mPref = PreferenceManager.getDefaultSharedPreferences((Context)((PhraseListAdapter)((Object)PhraseListAdapter.this)).mContext);
            this.upgradeSmsPhraseData();
            PhraseListAdapter.this = Locale.getDefault().getLanguage();
            String string2 = Locale.getDefault().getCountry();
            this.PREF_SMS_PHRASE_DATA_COUNT = "sms_phrase_data_count_" + (String)PhraseListAdapter.this + "_" + string2;
            this.PREF_SMS_PHRASE_DATA = "sms_phrase_data_" + (String)PhraseListAdapter.this + "_" + string2;
            PhraseListAdapter.this = this.mPref.getString(this.PREF_SMS_PHRASE_DATA, "");
            if (this.mPref.getInt(this.PREF_SMS_PHRASE_DATA_COUNT, -1) == -1) {
                this.initSmsPhraseList();
                this.saveSmsPhraseDate();
                return;
            }
            this.parseSmsPhraseData((String)PhraseListAdapter.this);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        private void initSmsPhraseList() {
            block20 : {
                var3_1 = null;
                var1_7 = null;
                var4_13 = null;
                this.mSmsPhrases = new Vector();
                var2_14 = new BufferedReader((Reader)new InputStreamReader(PhraseListAdapter.access$000(PhraseListAdapter.this).getResources().openRawResource(2131165186)));
                try {
                    while ((var1_7 = var2_14.readLine()) != null) {
                        this.mSmsPhrases.add(var1_7);
                    }
                    break block20;
                }
                catch (Resources.NotFoundException var3_2) {}
                ** GOTO lbl-1000
            }
            if (var2_14 == null) return;
            try {
                var2_14.close();
                return;
            }
            catch (IOException var1_8) {
                var1_8.printStackTrace();
                return;
            }
            catch (IOException var1_10) {
                var2_14 = var3_1;
                var3_1 = var1_10;
                ** GOTO lbl31
                catch (Throwable var3_4) {
                    var1_7 = var2_14;
                    var2_14 = var3_4;
                    ** GOTO lbl-1000
                }
                catch (IOException var3_5) {}
lbl31: // 2 sources:
                var1_7 = var2_14;
                var3_1.printStackTrace();
                if (var2_14 == null) return;
                try {
                    var2_14.close();
                    return;
                }
                catch (IOException var1_11) {
                    var1_11.printStackTrace();
                    return;
                }
lbl-1000: // 2 sources:
                {
                    do {
                        var1_7 = var2_14;
                        try {
                            var3_3.printStackTrace();
                            if (var2_14 == null) return;
                        }
                        catch (Throwable var2_15) lbl-1000: // 2 sources:
                        {
                            if (var1_7 == null) throw var2_14;
                            try {
                                var1_7.close();
                            }
                            catch (IOException var1_12) {
                                var1_12.printStackTrace();
                                throw var2_14;
                            }
                            throw var2_14;
                        }
                        try {
                            var2_14.close();
                            return;
                        }
                        catch (IOException var1_9) {
                            var1_9.printStackTrace();
                            return;
                        }
                        break;
                    } while (true);
                }
            }
            catch (Resources.NotFoundException var3_6) {
                var2_14 = var4_13;
                ** continue;
            }
        }

        private void parseSmsPhraseData(String string2) {
            this.mSmsPhrases = new Vector();
            string2 = new StringTokenizer(string2, "\t");
            while (string2.hasMoreTokens()) {
                this.mSmsPhrases.add((Object)string2.nextToken());
            }
        }

        private void saveSmsPhraseDate() {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator iterator = this.mSmsPhrases.iterator();
            while (iterator.hasNext()) {
                stringBuilder.append((String)iterator.next());
                stringBuilder.append("\t");
            }
            if (stringBuilder.length() != 0) {
                stringBuilder.delete(stringBuilder.lastIndexOf("\t"), stringBuilder.length());
            }
            iterator = this.mPref.edit();
            iterator.putString(this.PREF_SMS_PHRASE_DATA, stringBuilder.toString());
            iterator.putInt(this.PREF_SMS_PHRASE_DATA_COUNT, this.mSmsPhrases.size());
            iterator.commit();
        }

        /*
         * Enabled aggressive block sorting
         */
        private void upgradeSmsPhraseData() {
            int n = this.mPref.getInt("sms_phrase_version", 1);
            if (n == 2) {
                return;
            }
            LogTag.debug("Upgrading phrase data from ver %d to %d", n, 2);
            switch (n) {
                default: {
                    break;
                }
                case 1: {
                    String string2 = this.mPref.getString("sms_phrase_data_zh_TW", "");
                    if (string2.isEmpty()) break;
                    string2 = string2.replaceAll("\u7a0d\u5f8c\u56de\u8907", "\u7a0d\u5f8c\u56de\u8986");
                    this.mPref.edit().putString("sms_phrase_data_zh_TW", string2).commit();
                }
            }
            this.mPref.edit().putInt("sms_phrase_version", 2).commit();
        }

        public void addPhrase(String string2) {
            this.mSmsPhrases.add(0, (Object)string2);
            this.saveSmsPhraseDate();
        }

        public void deletePhrase(int n) {
            if (n >= 0 && n < this.mSmsPhrases.size()) {
                this.mSmsPhrases.remove(n);
                this.saveSmsPhraseDate();
            }
        }

        public int getCount() {
            return this.mSmsPhrases.size();
        }

        public String getPhrase(int n) {
            if (n < 0 || n >= this.mSmsPhrases.size()) {
                return null;
            }
            return (String)this.mSmsPhrases.get(n);
        }

        public void setPhrase(int n, String string2) {
            if (n >= 0 && n < this.mSmsPhrases.size()) {
                this.mSmsPhrases.set(n, (Object)string2);
                this.saveSmsPhraseDate();
            }
        }
    }

}

