/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.SearchManager
 *  android.app.SearchableInfo
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.pm.ActivityInfo
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.res.ColorStateList
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
 *  android.content.res.Resources$Theme
 *  android.database.Cursor
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$ConstantState
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Bundle
 *  android.text.SpannableString
 *  android.text.TextUtils
 *  android.text.style.TextAppearanceSpan
 *  android.util.Log
 *  android.util.TypedValue
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.io.FileNotFoundException
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.WeakHashMap
 */
package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R;
import android.support.v7.widget.SearchView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter
extends ResourceCursorAdapter
implements View.OnClickListener {
    private static final boolean DBG = false;
    static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = "SuggestionsAdapter";
    private static final int QUERY_LIMIT = 50;
    static final int REFINE_ALL = 2;
    static final int REFINE_BY_ENTRY = 1;
    static final int REFINE_NONE = 0;
    private boolean mClosed = false;
    private final int mCommitIconResId;
    private int mFlagsCol = -1;
    private int mIconName1Col = -1;
    private int mIconName2Col = -1;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement = 1;
    private final SearchManager mSearchManager;
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mText1Col = -1;
    private int mText2Col = -1;
    private int mText2UrlCol = -1;
    private ColorStateList mUrlColor;

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.mSearchManager = (SearchManager)this.mContext.getSystemService("search");
        this.mSearchView = searchView;
        this.mSearchable = searchableInfo;
        this.mCommitIconResId = searchView.getSuggestionCommitIconResId();
        this.mProviderContext = context;
        this.mOutsideDrawablesCache = weakHashMap;
    }

    private Drawable checkIconCache(String string2) {
        if ((string2 = (Drawable.ConstantState)this.mOutsideDrawablesCache.get((Object)string2)) == null) {
            return null;
        }
        return string2.newDrawable();
    }

    private CharSequence formatUrl(CharSequence charSequence) {
        TypedValue typedValue;
        if (this.mUrlColor == null) {
            typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, typedValue, true);
            this.mUrlColor = this.mContext.getResources().getColorStateList(typedValue.resourceId);
        }
        typedValue = new SpannableString(charSequence);
        typedValue.setSpan((Object)new TextAppearanceSpan(null, 0, 0, this.mUrlColor, null), 0, charSequence.length(), 33);
        return typedValue;
    }

    private Drawable getActivityIcon(ComponentName componentName) {
        ActivityInfo activityInfo;
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            activityInfo = packageManager.getActivityInfo(componentName, 128);
        }
        catch (PackageManager.NameNotFoundException var1_2) {
            Log.w((String)"SuggestionsAdapter", (String)var1_2.toString());
            return null;
        }
        int n2 = activityInfo.getIconResource();
        if (n2 == 0) {
            return null;
        }
        if ((packageManager = packageManager.getDrawable(componentName.getPackageName(), n2, activityInfo.applicationInfo)) == null) {
            Log.w((String)"SuggestionsAdapter", (String)("Invalid icon resource " + n2 + " for " + componentName.flattenToShortString()));
            return null;
        }
        return packageManager;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Drawable getActivityIconWithCache(ComponentName componentName) {
        Object var2_2 = null;
        String string2 = componentName.flattenToShortString();
        if (this.mOutsideDrawablesCache.containsKey((Object)string2)) {
            componentName = (Drawable.ConstantState)this.mOutsideDrawablesCache.get((Object)string2);
            if (componentName == null) {
                return null;
            }
            return componentName.newDrawable(this.mProviderContext.getResources());
        }
        Drawable drawable2 = this.getActivityIcon(componentName);
        componentName = drawable2 == null ? var2_2 : drawable2.getConstantState();
        this.mOutsideDrawablesCache.put((Object)string2, (Object)componentName);
        return drawable2;
    }

    public static String getColumnString(Cursor cursor, String string2) {
        return SuggestionsAdapter.getStringOrNull(cursor, cursor.getColumnIndex(string2));
    }

    private Drawable getDefaultIcon1(Cursor cursor) {
        cursor = this.getActivityIconWithCache(this.mSearchable.getSearchActivity());
        if (cursor != null) {
            return cursor;
        }
        return this.mContext.getPackageManager().getDefaultActivityIcon();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private Drawable getDrawable(Uri var1_1) {
        block13 : {
            try {
                var2_2 = "android.resource".equals((Object)var1_1.getScheme());
                if (var2_2) {
                    try {
                        return this.getDrawableFromResourceUri(var1_1);
                    }
                    catch (Resources.NotFoundException var3_4) {
                        throw new FileNotFoundException("Resource does not exist: " + (Object)var1_1);
                    }
                }
                var3_6 = this.mProviderContext.getContentResolver().openInputStream(var1_1);
                if (var3_6 != null) break block13;
                throw new FileNotFoundException("Failed to open " + (Object)var1_1);
            }
            catch (FileNotFoundException var3_5) {
                Log.w((String)"SuggestionsAdapter", (String)("Icon not found: " + (Object)var1_1 + ", " + var3_5.getMessage()));
                return null;
            }
        }
        try {
            var4_9 = Drawable.createFromStream((InputStream)var3_6, (String)null);
        }
        catch (Throwable var4_10) {
            try {
                var3_6.close();
            }
            catch (IOException var3_8) {
                Log.e((String)"SuggestionsAdapter", (String)("Error closing icon stream for " + (Object)var1_1), (Throwable)var3_8);
                throw var4_10;
            }
            throw var4_10;
        }
        ** try [egrp 5[TRYBLOCK] [7 : 145->149)] { 
lbl-1000: // 1 sources:
        {
            var3_6.close();
            return var4_9;
        }
lbl29: // 1 sources:
        catch (IOException var3_7) {
            Log.e((String)"SuggestionsAdapter", (String)("Error closing icon stream for " + (Object)var1_1), (Throwable)var3_7);
            return var4_9;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Drawable getDrawableFromResourceValue(String string2) {
        Drawable drawable2;
        if (string2 == null) return null;
        if (string2.length() == 0) return null;
        if ("0".equals((Object)string2)) {
            return null;
        }
        int n2 = Integer.parseInt((String)string2);
        String string3 = "android.resource://" + this.mProviderContext.getPackageName() + "/" + n2;
        Drawable drawable3 = drawable2 = this.checkIconCache(string3);
        if (drawable2 != null) return drawable3;
        try {
            drawable3 = ContextCompat.getDrawable(this.mProviderContext, n2);
            this.storeInIconCache(string3, drawable3);
            return drawable3;
        }
        catch (NumberFormatException var3_3) {
            drawable3 = drawable2 = this.checkIconCache(string2);
            if (drawable2 != null) return drawable3;
            drawable3 = this.getDrawable(Uri.parse((String)string2));
            this.storeInIconCache(string2, drawable3);
            return drawable3;
        }
        catch (Resources.NotFoundException var3_4) {
            Log.w((String)"SuggestionsAdapter", (String)("Icon resource not found: " + string2));
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Drawable getIcon1(Cursor cursor) {
        Drawable drawable2;
        if (this.mIconName1Col == -1) {
            return null;
        }
        Drawable drawable3 = drawable2 = this.getDrawableFromResourceValue(cursor.getString(this.mIconName1Col));
        if (drawable2 != null) return drawable3;
        return this.getDefaultIcon1(cursor);
    }

    private Drawable getIcon2(Cursor cursor) {
        if (this.mIconName2Col == -1) {
            return null;
        }
        return this.getDrawableFromResourceValue(cursor.getString(this.mIconName2Col));
    }

    private static String getStringOrNull(Cursor object, int n2) {
        if (n2 == -1) {
            return null;
        }
        try {
            object = object.getString(n2);
            return object;
        }
        catch (Exception var0_1) {
            Log.e((String)"SuggestionsAdapter", (String)"unexpected error retrieving valid column from cursor, did the remote process die?", (Throwable)var0_1);
            return null;
        }
    }

    private void setViewDrawable(ImageView imageView, Drawable drawable2, int n2) {
        imageView.setImageDrawable(drawable2);
        if (drawable2 == null) {
            imageView.setVisibility(n2);
            return;
        }
        imageView.setVisibility(0);
        drawable2.setVisible(false, false);
        drawable2.setVisible(true, false);
    }

    private void setViewText(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty((CharSequence)charSequence)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
    }

    private void storeInIconCache(String string2, Drawable drawable2) {
        if (drawable2 != null) {
            this.mOutsideDrawablesCache.put((Object)string2, (Object)drawable2.getConstantState());
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void updateSpinnerState(Cursor cursor) {
        if (cursor == null) return;
        if ((cursor = cursor.getExtras()) != null && !cursor.getBoolean("in_progress")) return;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void bindView(View object, Context object2, Cursor cursor) {
        object2 = (ChildViewCache)object.getTag();
        int n2 = this.mFlagsCol != -1 ? cursor.getInt(this.mFlagsCol) : 0;
        if (object2.mText1 != null) {
            object = SuggestionsAdapter.getStringOrNull(cursor, this.mText1Col);
            this.setViewText(object2.mText1, (CharSequence)object);
        }
        if (object2.mText2 != null) {
            object = SuggestionsAdapter.getStringOrNull(cursor, this.mText2UrlCol);
            object = object != null ? this.formatUrl((CharSequence)object) : SuggestionsAdapter.getStringOrNull(cursor, this.mText2Col);
            if (TextUtils.isEmpty((CharSequence)object)) {
                if (object2.mText1 != null) {
                    object2.mText1.setSingleLine(false);
                    object2.mText1.setMaxLines(2);
                }
            } else if (object2.mText1 != null) {
                object2.mText1.setSingleLine(true);
                object2.mText1.setMaxLines(1);
            }
            this.setViewText(object2.mText2, (CharSequence)object);
        }
        if (object2.mIcon1 != null) {
            this.setViewDrawable(object2.mIcon1, this.getIcon1(cursor), 4);
        }
        if (object2.mIcon2 != null) {
            this.setViewDrawable(object2.mIcon2, this.getIcon2(cursor), 8);
        }
        if (this.mQueryRefinement == 2 || this.mQueryRefinement == 1 && (n2 & 1) != 0) {
            object2.mIconRefine.setVisibility(0);
            object2.mIconRefine.setTag((Object)object2.mText1.getText());
            object2.mIconRefine.setOnClickListener((View.OnClickListener)this);
            return;
        }
        object2.mIconRefine.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void changeCursor(Cursor cursor) {
        if (this.mClosed) {
            Log.w((String)"SuggestionsAdapter", (String)"Tried to change cursor after adapter was closed.");
            if (cursor == null) return;
            {
                cursor.close();
                return;
            }
        }
        try {
            super.changeCursor(cursor);
            if (cursor == null) return;
            this.mText1Col = cursor.getColumnIndex("suggest_text_1");
            this.mText2Col = cursor.getColumnIndex("suggest_text_2");
            this.mText2UrlCol = cursor.getColumnIndex("suggest_text_2_url");
            this.mIconName1Col = cursor.getColumnIndex("suggest_icon_1");
            this.mIconName2Col = cursor.getColumnIndex("suggest_icon_2");
            this.mFlagsCol = cursor.getColumnIndex("suggest_flags");
            return;
        }
        catch (Exception var1_2) {
            Log.e((String)"SuggestionsAdapter", (String)"error changing cursor and caching columns", (Throwable)var1_2);
            return;
        }
    }

    public void close() {
        this.changeCursor(null);
        this.mClosed = true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public CharSequence convertToString(Cursor object) {
        if (object == null) {
            return null;
        }
        String string2 = SuggestionsAdapter.getColumnString((Cursor)object, "suggest_intent_query");
        if (string2 != null) {
            return string2;
        }
        if (this.mSearchable.shouldRewriteQueryFromData() && (string2 = SuggestionsAdapter.getColumnString((Cursor)object, "suggest_intent_data")) != null) {
            return string2;
        }
        if (!this.mSearchable.shouldRewriteQueryFromText()) return null;
        if ((object = SuggestionsAdapter.getColumnString((Cursor)object, "suggest_text_1")) == null) return null;
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    Drawable getDrawableFromResourceUri(Uri uri) {
        Resources resources;
        String string2 = uri.getAuthority();
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new FileNotFoundException("No authority: " + (Object)uri);
        }
        try {
            resources = this.mContext.getPackageManager().getResourcesForApplication(string2);
        }
        catch (PackageManager.NameNotFoundException var3_3) {
            throw new FileNotFoundException("No package found for authority: " + (Object)uri);
        }
        List list = uri.getPathSegments();
        if (list == null) {
            throw new FileNotFoundException("No path: " + (Object)uri);
        }
        int n2 = list.size();
        if (n2 == 1) {
            try {
                n2 = Integer.parseInt((String)((String)list.get(0)));
            }
            catch (NumberFormatException var3_4) {
                throw new FileNotFoundException("Single path segment is not a resource ID: " + (Object)uri);
            }
        } else {
            if (n2 != 2) {
                throw new FileNotFoundException("More than two path segments: " + (Object)uri);
            }
            n2 = resources.getIdentifier((String)list.get(1), (String)list.get(0), string2);
        }
        if (n2 == 0) {
            throw new FileNotFoundException("No resource found for: " + (Object)uri);
        }
        return resources.getDrawable(n2);
    }

    public int getQueryRefinement() {
        return this.mQueryRefinement;
    }

    /*
     * Enabled aggressive block sorting
     */
    Cursor getSearchManagerSuggestions(SearchableInfo arrstring, String string2, int n2) {
        String string3;
        if (arrstring == null || (string3 = arrstring.getSuggestAuthority()) == null) {
            return null;
        }
        string3 = new Uri.Builder().scheme("content").authority(string3).query("").fragment("");
        String string4 = arrstring.getSuggestPath();
        if (string4 != null) {
            string3.appendEncodedPath(string4);
        }
        string3.appendPath("search_suggest_query");
        string4 = arrstring.getSuggestSelection();
        if (string4 != null) {
            arrstring = new String[]{string2};
        } else {
            string3.appendPath(string2);
            arrstring = null;
        }
        if (n2 > 0) {
            string3.appendQueryParameter("limit", String.valueOf((int)n2));
        }
        string2 = string3.build();
        return this.mContext.getContentResolver().query((Uri)string2, null, string4, arrstring, null);
    }

    @Override
    public View getView(int n2, View view, ViewGroup viewGroup) {
        try {
            view = super.getView(n2, view, viewGroup);
            return view;
        }
        catch (RuntimeException var2_3) {
            Log.w((String)"SuggestionsAdapter", (String)"Search suggestions cursor threw exception.", (Throwable)var2_3);
            viewGroup = this.newView(this.mContext, this.mCursor, viewGroup);
            if (viewGroup != null) {
                ((ChildViewCache)viewGroup.getTag()).mText1.setText((CharSequence)var2_3.toString());
            }
            return viewGroup;
        }
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        context = super.newView(context, cursor, viewGroup);
        context.setTag((Object)new ChildViewCache((View)context));
        ((ImageView)context.findViewById(R.id.edit_query)).setImageResource(this.mCommitIconResId);
        return context;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.updateSpinnerState(this.getCursor());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        this.updateSpinnerState(this.getCursor());
    }

    public void onClick(View object) {
        if ((object = object.getTag()) instanceof CharSequence) {
            this.mSearchView.onQueryRefine((CharSequence)object);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        charSequence = charSequence == null ? "" : charSequence.toString();
        if (this.mSearchView.getVisibility() != 0) return null;
        if (this.mSearchView.getWindowVisibility() != 0) {
            return null;
        }
        try {
            charSequence = this.getSearchManagerSuggestions(this.mSearchable, (String)charSequence, 50);
            if (charSequence == null) return null;
            {
                charSequence.getCount();
                return charSequence;
            }
        }
        catch (RuntimeException var1_2) {
            Log.w((String)"SuggestionsAdapter", (String)"Search suggestions query threw an exception.", (Throwable)var1_2);
        }
        return null;
    }

    public void setQueryRefinement(int n2) {
        this.mQueryRefinement = n2;
    }

    static final class ChildViewCache {
        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;

        public ChildViewCache(View view) {
            this.mText1 = (TextView)view.findViewById(16908308);
            this.mText2 = (TextView)view.findViewById(16908309);
            this.mIcon1 = (ImageView)view.findViewById(16908295);
            this.mIcon2 = (ImageView)view.findViewById(16908296);
            this.mIconRefine = (ImageView)view.findViewById(R.id.edit_query);
        }
    }

}

