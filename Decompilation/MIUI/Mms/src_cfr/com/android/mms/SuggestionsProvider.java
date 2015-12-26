/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProvider
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.CharArrayBuffer
 *  android.database.Cursor
 *  android.database.CursorWrapper
 *  android.database.MatrixCursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.text.TextUtils
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.LogTag;
import com.android.mms.data.Conversation;
import com.android.mms.ui.ComposeMessageRouterActivity;
import com.android.mms.ui.MessageUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;

public class SuggestionsProvider
extends ContentProvider {
    private static final String[] COLUMNS = new String[]{"suggest_text_1", "suggest_text_2", "suggest_icon_1", "suggest_intent_action", "suggest_intent_data", "suggest_shortcut_id"};
    private static final int[] COLUMN_TYPES = new int[]{3, 3, 0, 3, 3, 3};

    public int delete(Uri uri, String string, String[] arrstring) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri object, String[] arrstring, String string, String[] arrstring2, String string2) {
        object = arrstring2[0].trim();
        if (TextUtils.isEmpty((CharSequence)object)) {
            return new MatrixCursor(COLUMNS);
        }
        return new SearchSuggestionCursorTranslator(this.getContext().getContentResolver().query(Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", arrstring2[0]).appendQueryParameter("privacy_flag", "0").build(), null, null, null, null), (String)object);
    }

    public int update(Uri uri, ContentValues contentValues, String string, String[] arrstring) {
        return 0;
    }

    private class SearchSuggestionCursorTranslator
    extends CursorWrapper {
        private final int mBodyPos;
        private final int mDatePos;
        private final String mFirstQuery;
        private final int mRowIdPos;
        private final int mSubjectPos;
        private final int mThreadIdPos;

        public SearchSuggestionCursorTranslator(Cursor cursor, String string) {
            super(cursor);
            this.mThreadIdPos = cursor.getColumnIndex("thread_id");
            this.mBodyPos = cursor.getColumnIndex("body");
            this.mSubjectPos = cursor.getColumnIndex("sub");
            this.mRowIdPos = cursor.getColumnIndex("_id");
            this.mDatePos = cursor.getColumnIndex("date");
            this.mFirstQuery = string.split(" +")[0];
        }

        public void copyStringToBuffer(int n, CharArrayBuffer charArrayBuffer) {
            throw new IllegalArgumentException("Wrong data type for column " + n);
        }

        public byte[] getBlob(int n) {
            throw new IllegalArgumentException("Wrong data type for column " + n);
        }

        public int getColumnCount() {
            return COLUMNS.length;
        }

        public int getColumnIndex(String string) {
            for (int i = 0; i < COLUMNS.length; ++i) {
                if (!COLUMNS[i].equals((Object)string)) continue;
                return i;
            }
            return -1;
        }

        public int getColumnIndexOrThrow(String string) throws IllegalArgumentException {
            int n = this.getColumnIndex(string);
            if (n == -1) {
                throw new IllegalArgumentException("Cannot find column " + string);
            }
            return n;
        }

        public String getColumnName(int n) {
            return COLUMNS[n];
        }

        public String[] getColumnNames() {
            return (String[])COLUMNS.clone();
        }

        public int getCount() {
            return this.mCursor.getCount();
        }

        public double getDouble(int n) {
            throw new IllegalArgumentException("Wrong data type for column " + n);
        }

        public float getFloat(int n) {
            throw new IllegalArgumentException("Wrong data type for column " + n);
        }

        public int getInt(int n) {
            throw new IllegalArgumentException("Wrong data type for column " + n);
        }

        public long getLong(int n) {
            throw new IllegalArgumentException("Wrong data type for column " + n);
        }

        public short getShort(int n) {
            throw new IllegalArgumentException("Wrong data type for column " + n);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public String getString(int n) {
            if (this.getColumnName(n).equals((Object)"suggest_text_1")) {
                long l = this.mCursor.getLong(this.mThreadIdPos);
                Conversation conversation = Conversation.get(SuggestionsProvider.this.getContext(), l);
                conversation.loadRecipients(true, false);
                return conversation.getContactNames();
            }
            if (this.getColumnName(n).equals((Object)"suggest_text_2")) {
                String string = this.mCursor.getString(this.mSubjectPos);
                if (string != null) {
                    if (!TextUtils.isEmpty((CharSequence)string)) {
                        string = new EncodedStringValue(106, MiuiPduPersister.getBytes((String)string)).getString();
                        do {
                            return MessageUtils.getSnippet(string, this.mFirstQuery, 20, 5);
                            break;
                        } while (true);
                    }
                    string = "";
                    return MessageUtils.getSnippet(string, this.mFirstQuery, 20, 5);
                }
                string = this.mCursor.getString(this.mBodyPos);
                return MessageUtils.getSnippet(string, this.mFirstQuery, 20, 5);
            }
            if (this.getColumnName(n).equals((Object)"suggest_icon_1")) {
                return null;
            }
            if (this.getColumnName(n).equals((Object)"suggest_intent_action")) {
                return "android.intent.action.VIEW";
            }
            if (this.getColumnName(n).equals((Object)"suggest_intent_data")) {
                long l = this.mCursor.getLong(this.mThreadIdPos);
                long l2 = this.mCursor.getLong(this.mRowIdPos);
                String string = null;
                if (l2 < 0) {
                    string = this.mCursor.getString(this.mBodyPos);
                }
                string = ComposeMessageRouterActivity.createSearchResultDataUri(l, l2, this.mFirstQuery, string).toString();
                LogTag.error("dataUri = %s", string);
                return string;
            }
            if (!this.getColumnName(n).equals((Object)"suggest_shortcut_id")) throw new IllegalArgumentException("Invalid column index " + n);
            return "_-1";
        }

        public int getType(int n) {
            return COLUMN_TYPES[n];
        }

        public boolean isNull(int n) {
            if (COLUMN_TYPES[n] == 0) {
                return true;
            }
            return false;
        }
    }

}

