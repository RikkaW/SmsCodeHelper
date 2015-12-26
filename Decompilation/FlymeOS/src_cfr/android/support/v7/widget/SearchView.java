/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.app.PendingIntent
 *  android.app.SearchableInfo
 *  android.content.ActivityNotFoundException
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.pm.ResolveInfo
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$ConstantState
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.os.ResultReceiver
 *  android.text.Editable
 *  android.text.SpannableStringBuilder
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.text.style.ImageSpan
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.KeyEvent$DispatcherState
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.View$OnKeyListener
 *  android.view.View$OnLayoutChangeListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemSelectedListener
 *  android.widget.AutoCompleteTextView
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.WeakHashMap
 */
package android.support.v7.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.SearchView$1;
import android.support.v7.widget.SearchView$10;
import android.support.v7.widget.SearchView$11;
import android.support.v7.widget.SearchView$12;
import android.support.v7.widget.SearchView$2;
import android.support.v7.widget.SearchView$3;
import android.support.v7.widget.SearchView$4;
import android.support.v7.widget.SearchView$5;
import android.support.v7.widget.SearchView$6;
import android.support.v7.widget.SearchView$7;
import android.support.v7.widget.SearchView$8;
import android.support.v7.widget.SearchView$9;
import android.support.v7.widget.SuggestionsAdapter;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView
extends LinearLayoutCompat
implements CollapsibleActionView {
    private static final boolean DBG = false;
    static final AutoCompleteTextViewReflector HIDDEN_METHOD_INVOKER;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    private static final boolean IS_AT_LEAST_FROYO;
    private static final String LOG_TAG = "SearchView";
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    private final ImageView mCloseButton;
    private final ImageView mCollapsedIcon;
    private int mCollapsedImeOptions;
    private final CharSequence mDefaultQueryHint;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    private final ImageView mGoButton;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final View.OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    private View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private View.OnClickListener mOnSearchClickListener;
    private OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable;
    private final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final Drawable mSearchHintIcon;
    private final View mSearchPlate;
    private final SearchAutoComplete mSearchSrcTextView;
    private SearchableInfo mSearchable;
    private Runnable mShowImeRunnable;
    private final View mSubmitArea;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    private CursorAdapter mSuggestionsAdapter;
    View.OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private final TintManager mTintManager;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    private final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl2 = Build.VERSION.SDK_INT >= 8;
        IS_AT_LEAST_FROYO = bl2;
        HIDDEN_METHOD_INVOKER = new AutoCompleteTextViewReflector();
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.searchViewStyle);
    }

    /*
     * Enabled aggressive block sorting
     */
    public SearchView(Context context, AttributeSet object, int n2) {
        super(context, (AttributeSet)object, n2);
        this.mShowImeRunnable = new SearchView$1(this);
        this.mUpdateDrawableStateRunnable = new SearchView$2(this);
        this.mReleaseCursorRunnable = new SearchView$3(this);
        this.mOutsideDrawablesCache = new WeakHashMap();
        this.mOnClickListener = new SearchView$7(this);
        this.mTextKeyListener = new SearchView$8(this);
        this.mOnEditorActionListener = new SearchView$9(this);
        this.mOnItemClickListener = new SearchView$10(this);
        this.mOnItemSelectedListener = new SearchView$11(this);
        this.mTextWatcher = new SearchView$12(this);
        object = TintTypedArray.obtainStyledAttributes(context, (AttributeSet)object, R.styleable.SearchView, n2, 0);
        this.mTintManager = object.getTintManager();
        LayoutInflater.from((Context)context).inflate(object.getResourceId(R.styleable.SearchView_layout, R.layout.abc_search_view), (ViewGroup)this, true);
        this.mSearchSrcTextView = (SearchAutoComplete)this.findViewById(R.id.search_src_text);
        this.mSearchSrcTextView.setSearchView(this);
        this.mSearchEditFrame = this.findViewById(R.id.search_edit_frame);
        this.mSearchPlate = this.findViewById(R.id.search_plate);
        this.mSubmitArea = this.findViewById(R.id.submit_area);
        this.mSearchButton = (ImageView)this.findViewById(R.id.search_button);
        this.mGoButton = (ImageView)this.findViewById(R.id.search_go_btn);
        this.mCloseButton = (ImageView)this.findViewById(R.id.search_close_btn);
        this.mVoiceButton = (ImageView)this.findViewById(R.id.search_voice_btn);
        this.mCollapsedIcon = (ImageView)this.findViewById(R.id.search_mag_icon);
        this.mSearchPlate.setBackgroundDrawable(object.getDrawable(R.styleable.SearchView_queryBackground));
        this.mSubmitArea.setBackgroundDrawable(object.getDrawable(R.styleable.SearchView_submitBackground));
        this.mSearchButton.setImageDrawable(object.getDrawable(R.styleable.SearchView_searchIcon));
        this.mGoButton.setImageDrawable(object.getDrawable(R.styleable.SearchView_goIcon));
        this.mCloseButton.setImageDrawable(object.getDrawable(R.styleable.SearchView_closeIcon));
        this.mVoiceButton.setImageDrawable(object.getDrawable(R.styleable.SearchView_voiceIcon));
        this.mCollapsedIcon.setImageDrawable(object.getDrawable(R.styleable.SearchView_searchIcon));
        this.mSearchHintIcon = object.getDrawable(R.styleable.SearchView_searchHintIcon);
        this.mSuggestionRowLayout = object.getResourceId(R.styleable.SearchView_suggestionRowLayout, R.layout.abc_search_dropdown_item_icons_2line);
        this.mSuggestionCommitIconResId = object.getResourceId(R.styleable.SearchView_commitIcon, 0);
        this.mSearchButton.setOnClickListener(this.mOnClickListener);
        this.mCloseButton.setOnClickListener(this.mOnClickListener);
        this.mGoButton.setOnClickListener(this.mOnClickListener);
        this.mVoiceButton.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.setOnClickListener(this.mOnClickListener);
        this.mSearchSrcTextView.addTextChangedListener(this.mTextWatcher);
        this.mSearchSrcTextView.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mSearchSrcTextView.setOnItemClickListener(this.mOnItemClickListener);
        this.mSearchSrcTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        this.mSearchSrcTextView.setOnKeyListener(this.mTextKeyListener);
        this.mSearchSrcTextView.setOnFocusChangeListener((View.OnFocusChangeListener)new SearchView$4(this));
        this.setIconifiedByDefault(object.getBoolean(R.styleable.SearchView_iconifiedByDefault, true));
        n2 = object.getDimensionPixelSize(R.styleable.SearchView_android_maxWidth, -1);
        if (n2 != -1) {
            this.setMaxWidth(n2);
        }
        this.mDefaultQueryHint = object.getText(R.styleable.SearchView_defaultQueryHint);
        this.mQueryHint = object.getText(R.styleable.SearchView_queryHint);
        n2 = object.getInt(R.styleable.SearchView_android_imeOptions, -1);
        if (n2 != -1) {
            this.setImeOptions(n2);
        }
        if ((n2 = object.getInt(R.styleable.SearchView_android_inputType, -1)) != -1) {
            this.setInputType(n2);
        }
        this.setFocusable(object.getBoolean(R.styleable.SearchView_android_focusable, true));
        object.recycle();
        this.mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent.addFlags(268435456);
        this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent.addFlags(268435456);
        this.mDropDownAnchor = this.findViewById(this.mSearchSrcTextView.getDropDownAnchor());
        if (this.mDropDownAnchor != null) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.addOnLayoutChangeListenerToDropDownAnchorSDK11();
            } else {
                this.addOnLayoutChangeListenerToDropDownAnchorBase();
            }
        }
        this.updateViewsVisibility(this.mIconifiedByDefault);
        this.updateQueryHint();
    }

    static /* synthetic */ void access$000(SearchView searchView) {
        searchView.updateFocusedState();
    }

    static /* synthetic */ CursorAdapter access$100(SearchView searchView) {
        return searchView.mSuggestionsAdapter;
    }

    static /* synthetic */ ImageView access$1000(SearchView searchView) {
        return searchView.mVoiceButton;
    }

    static /* synthetic */ void access$1100(SearchView searchView) {
        searchView.onVoiceClicked();
    }

    static /* synthetic */ SearchAutoComplete access$1200(SearchView searchView) {
        return searchView.mSearchSrcTextView;
    }

    static /* synthetic */ void access$1300(SearchView searchView) {
        searchView.forceSuggestionQuery();
    }

    static /* synthetic */ SearchableInfo access$1400(SearchView searchView) {
        return searchView.mSearchable;
    }

    static /* synthetic */ boolean access$1500(SearchView searchView, View view, int n2, KeyEvent keyEvent) {
        return searchView.onSuggestionsKey(view, n2, keyEvent);
    }

    static /* synthetic */ void access$1700(SearchView searchView, int n2, String string2, String string3) {
        searchView.launchQuerySearch(n2, string2, string3);
    }

    static /* synthetic */ boolean access$1800(SearchView searchView, int n2, int n3, String string2) {
        return searchView.onItemClicked(n2, n3, string2);
    }

    static /* synthetic */ boolean access$1900(SearchView searchView, int n2) {
        return searchView.onItemSelected(n2);
    }

    static /* synthetic */ View.OnFocusChangeListener access$200(SearchView searchView) {
        return searchView.mOnQueryTextFocusChangeListener;
    }

    static /* synthetic */ void access$2000(SearchView searchView, CharSequence charSequence) {
        searchView.onTextChanged(charSequence);
    }

    static /* synthetic */ void access$300(SearchView searchView) {
        searchView.adjustDropDownSizeAndPosition();
    }

    static /* synthetic */ ImageView access$400(SearchView searchView) {
        return searchView.mSearchButton;
    }

    static /* synthetic */ void access$500(SearchView searchView) {
        searchView.onSearchClicked();
    }

    static /* synthetic */ ImageView access$600(SearchView searchView) {
        return searchView.mCloseButton;
    }

    static /* synthetic */ void access$700(SearchView searchView) {
        searchView.onCloseClicked();
    }

    static /* synthetic */ ImageView access$800(SearchView searchView) {
        return searchView.mGoButton;
    }

    static /* synthetic */ void access$900(SearchView searchView) {
        searchView.onSubmitQuery();
    }

    private void addOnLayoutChangeListenerToDropDownAnchorBase() {
        this.mDropDownAnchor.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)new SearchView$6(this));
    }

    @TargetApi(value=11)
    private void addOnLayoutChangeListenerToDropDownAnchorSDK11() {
        this.mDropDownAnchor.addOnLayoutChangeListener((View.OnLayoutChangeListener)new SearchView$5(this));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void adjustDropDownSizeAndPosition() {
        if (this.mDropDownAnchor.getWidth() > 1) {
            int n2;
            Resources resources = this.getContext().getResources();
            int n3 = this.mSearchPlate.getPaddingLeft();
            Rect rect = new Rect();
            boolean bl2 = ViewUtils.isLayoutRtl((View)this);
            if (this.mIconifiedByDefault) {
                n2 = resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_icon_width);
                n2 = resources.getDimensionPixelSize(R.dimen.abc_dropdownitem_text_padding_left) + n2;
            } else {
                n2 = 0;
            }
            this.mSearchSrcTextView.getDropDownBackground().getPadding(rect);
            int n4 = bl2 ? - rect.left : n3 - (rect.left + n2);
            this.mSearchSrcTextView.setDropDownHorizontalOffset(n4);
            n4 = this.mDropDownAnchor.getWidth();
            int n5 = rect.left;
            int n6 = rect.right;
            this.mSearchSrcTextView.setDropDownWidth(n2 + (n4 + n5 + n6) - n3);
        }
    }

    private Intent createIntent(String string2, Uri uri, String string3, String string4, int n2, String string5) {
        string2 = new Intent(string2);
        string2.addFlags(268435456);
        if (uri != null) {
            string2.setData(uri);
        }
        string2.putExtra("user_query", this.mUserQuery);
        if (string4 != null) {
            string2.putExtra("query", string4);
        }
        if (string3 != null) {
            string2.putExtra("intent_extra_data_key", string3);
        }
        if (this.mAppSearchData != null) {
            string2.putExtra("app_data", this.mAppSearchData);
        }
        if (n2 != 0) {
            string2.putExtra("action_key", n2);
            string2.putExtra("action_msg", string5);
        }
        if (IS_AT_LEAST_FROYO) {
            string2.setComponent(this.mSearchable.getSearchActivity());
        }
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Intent createIntentFromSuggestion(Cursor cursor, int n2, String string2) {
        String string3;
        String string4;
        String string5;
        try {
            string5 = string4 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_action");
            if (string4 == null) {
                string5 = string4;
                if (Build.VERSION.SDK_INT >= 8) {
                    string5 = this.mSearchable.getSuggestIntentAction();
                }
            }
        }
        catch (RuntimeException var3_5) {
            try {
                n2 = cursor.getPosition();
            }
            catch (RuntimeException var1_2) {
                n2 = -1;
            }
            Log.w((String)"SearchView", (String)("Search suggestions cursor at row " + n2 + " returned exception."), (Throwable)var3_5);
            return null;
        }
        string4 = string5;
        if (string5 == null) {
            string4 = "android.intent.action.SEARCH";
        }
        string5 = string3 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data");
        if (IS_AT_LEAST_FROYO) {
            string5 = string3;
            if (string3 == null) {
                string5 = this.mSearchable.getSuggestIntentData();
            }
        }
        string3 = string5;
        if (string5 != null) {
            String string6 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data_id");
            string3 = string5;
            if (string6 != null) {
                string3 = string5 + "/" + Uri.encode((String)string6);
            }
        }
        string5 = string3 == null ? null : Uri.parse((String)string3);
        string3 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_query");
        return this.createIntent(string4, (Uri)string5, SuggestionsAdapter.getColumnString(cursor, "suggest_intent_extra_data"), string3, n2, string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    @TargetApi(value=8)
    private Intent createVoiceAppSearchIntent(Intent object, SearchableInfo searchableInfo) {
        int n2;
        Object object2;
        Object var6_3 = null;
        ComponentName componentName = searchableInfo.getSearchActivity();
        Object object3 = new Intent("android.intent.action.SEARCH");
        object3.setComponent(componentName);
        PendingIntent pendingIntent = PendingIntent.getActivity((Context)this.getContext(), (int)0, (Intent)object3, (int)1073741824);
        Bundle bundle = new Bundle();
        if (this.mAppSearchData != null) {
            bundle.putParcelable("app_data", (Parcelable)this.mAppSearchData);
        }
        Intent intent = new Intent((Intent)object);
        object = "free_form";
        if (Build.VERSION.SDK_INT >= 8) {
            object2 = this.getResources();
            if (searchableInfo.getVoiceLanguageModeId() != 0) {
                object = object2.getString(searchableInfo.getVoiceLanguageModeId());
            }
            object3 = searchableInfo.getVoicePromptTextId() != 0 ? object2.getString(searchableInfo.getVoicePromptTextId()) : null;
            object2 = searchableInfo.getVoiceLanguageId() != 0 ? object2.getString(searchableInfo.getVoiceLanguageId()) : null;
            n2 = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        } else {
            object2 = null;
            object3 = null;
            object = "free_form";
            n2 = 1;
        }
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", (String)object);
        intent.putExtra("android.speech.extra.PROMPT", (String)object3);
        intent.putExtra("android.speech.extra.LANGUAGE", (String)object2);
        intent.putExtra("android.speech.extra.MAX_RESULTS", n2);
        object = componentName == null ? var6_3 : componentName.flattenToShortString();
        intent.putExtra("calling_package", (String)object);
        intent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", (Parcelable)pendingIntent);
        intent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent;
    }

    /*
     * Enabled aggressive block sorting
     */
    @TargetApi(value=8)
    private Intent createVoiceWebSearchIntent(Intent object, SearchableInfo searchableInfo) {
        Intent intent = new Intent((Intent)object);
        object = searchableInfo.getSearchActivity();
        object = object == null ? null : object.flattenToShortString();
        intent.putExtra("calling_package", (String)object);
        return intent;
    }

    private void dismissSuggestions() {
        this.mSearchSrcTextView.dismissDropDown();
    }

    private void forceSuggestionQuery() {
        HIDDEN_METHOD_INVOKER.doBeforeTextChanged(this.mSearchSrcTextView);
        HIDDEN_METHOD_INVOKER.doAfterTextChanged(this.mSearchSrcTextView);
    }

    private CharSequence getDecoratedHint(CharSequence charSequence) {
        if (!this.mIconifiedByDefault || this.mSearchHintIcon == null) {
            return charSequence;
        }
        int n2 = (int)((double)this.mSearchSrcTextView.getTextSize() * 1.25);
        this.mSearchHintIcon.setBounds(0, 0, n2, n2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)"   ");
        spannableStringBuilder.setSpan((Object)new ImageSpan(this.mSearchHintIcon), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private int getPreferredWidth() {
        return this.getContext().getResources().getDimensionPixelSize(R.dimen.abc_search_view_preferred_width);
    }

    /*
     * Enabled aggressive block sorting
     */
    @TargetApi(value=8)
    private boolean hasVoiceSearch() {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (this.mSearchable == null) return bl3;
        bl3 = bl2;
        if (!this.mSearchable.getVoiceSearchEnabled()) return bl3;
        Intent intent = null;
        if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
            intent = this.mVoiceWebSearchIntent;
        } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
            intent = this.mVoiceAppSearchIntent;
        }
        bl3 = bl2;
        if (intent == null) return bl3;
        bl3 = bl2;
        if (this.getContext().getPackageManager().resolveActivity(intent, 65536) == null) return bl3;
        return true;
    }

    static boolean isLandscapeMode(Context context) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return true;
        }
        return false;
    }

    private boolean isSubmitAreaEnabled() {
        if ((this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !this.isIconified()) {
            return true;
        }
        return false;
    }

    private void launchIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            this.getContext().startActivity(intent);
            return;
        }
        catch (RuntimeException var2_2) {
            Log.e((String)"SearchView", (String)("Failed launch activity: " + (Object)intent), (Throwable)var2_2);
            return;
        }
    }

    private void launchQuerySearch(int n2, String string2, String string3) {
        string2 = this.createIntent("android.intent.action.SEARCH", null, null, string3, n2, string2);
        this.getContext().startActivity((Intent)string2);
    }

    private boolean launchSuggestion(int n2, int n3, String string2) {
        Cursor cursor = this.mSuggestionsAdapter.getCursor();
        if (cursor != null && cursor.moveToPosition(n2)) {
            this.launchIntent(this.createIntentFromSuggestion(cursor, n3, string2));
            return true;
        }
        return false;
    }

    private void onCloseClicked() {
        if (TextUtils.isEmpty((CharSequence)this.mSearchSrcTextView.getText())) {
            if (this.mIconifiedByDefault && (this.mOnCloseListener == null || !this.mOnCloseListener.onClose())) {
                this.clearFocus();
                this.updateViewsVisibility(true);
            }
            return;
        }
        this.mSearchSrcTextView.setText((CharSequence)"");
        this.mSearchSrcTextView.requestFocus();
        this.setImeVisibility(true);
    }

    private boolean onItemClicked(int n2, int n3, String string2) {
        boolean bl2 = false;
        if (this.mOnSuggestionListener == null || !this.mOnSuggestionListener.onSuggestionClick(n2)) {
            this.launchSuggestion(n2, 0, null);
            this.setImeVisibility(false);
            this.dismissSuggestions();
            bl2 = true;
        }
        return bl2;
    }

    private boolean onItemSelected(int n2) {
        if (this.mOnSuggestionListener == null || !this.mOnSuggestionListener.onSuggestionSelect(n2)) {
            this.rewriteQueryFromSuggestion(n2);
            return true;
        }
        return false;
    }

    private void onSearchClicked() {
        this.updateViewsVisibility(false);
        this.mSearchSrcTextView.requestFocus();
        this.setImeVisibility(true);
        if (this.mOnSearchClickListener != null) {
            this.mOnSearchClickListener.onClick((View)this);
        }
    }

    private void onSubmitQuery() {
        Editable editable = this.mSearchSrcTextView.getText();
        if (!(editable == null || TextUtils.getTrimmedLength((CharSequence)editable) <= 0 || this.mOnQueryChangeListener != null && this.mOnQueryChangeListener.onQueryTextSubmit(editable.toString()))) {
            if (this.mSearchable != null) {
                this.launchQuerySearch(0, null, editable.toString());
            }
            this.setImeVisibility(false);
            this.dismissSuggestions();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean onSuggestionsKey(View view, int n2, KeyEvent keyEvent) {
        if (this.mSearchable == null) {
            return false;
        }
        if (this.mSuggestionsAdapter == null) return false;
        if (keyEvent.getAction() != 0) return false;
        if (!KeyEventCompat.hasNoModifiers(keyEvent)) return false;
        if (n2 == 66) return this.onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null);
        if (n2 == 84) return this.onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null);
        if (n2 == 61) {
            return this.onItemClicked(this.mSearchSrcTextView.getListSelection(), 0, null);
        }
        if (n2 != 21 && n2 != 22) {
            if (n2 != 19) return false;
            if (this.mSearchSrcTextView.getListSelection() != 0) return false;
            return false;
        }
        n2 = n2 == 21 ? 0 : this.mSearchSrcTextView.length();
        this.mSearchSrcTextView.setSelection(n2);
        this.mSearchSrcTextView.setListSelection(0);
        this.mSearchSrcTextView.clearListSelection();
        HIDDEN_METHOD_INVOKER.ensureImeVisible(this.mSearchSrcTextView, true);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onTextChanged(CharSequence charSequence) {
        Editable editable;
        boolean bl2 = true;
        this.mUserQuery = editable = this.mSearchSrcTextView.getText();
        boolean bl3 = !TextUtils.isEmpty((CharSequence)editable);
        this.updateSubmitButton(bl3);
        bl3 = !bl3 ? bl2 : false;
        this.updateVoiceButton(bl3);
        this.updateCloseButton();
        this.updateSubmitArea();
        if (this.mOnQueryChangeListener != null && !TextUtils.equals((CharSequence)charSequence, (CharSequence)this.mOldQueryText)) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @TargetApi(value=8)
    private void onVoiceClicked() {
        SearchableInfo searchableInfo;
        block6 : {
            block5 : {
                if (this.mSearchable != null) {
                    searchableInfo = this.mSearchable;
                    try {
                        if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                            searchableInfo = this.createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo);
                            this.getContext().startActivity((Intent)searchableInfo);
                            return;
                        }
                        if (!searchableInfo.getVoiceSearchLaunchRecognizer()) break block5;
                        break block6;
                    }
                    catch (ActivityNotFoundException var1_2) {
                        Log.w((String)"SearchView", (String)"Could not find voice search activity");
                        return;
                    }
                }
            }
            return;
        }
        searchableInfo = this.createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchableInfo);
        this.getContext().startActivity((Intent)searchableInfo);
    }

    private void postUpdateFocusedState() {
        this.post(this.mUpdateDrawableStateRunnable);
    }

    private void rewriteQueryFromSuggestion(int n2) {
        Editable editable = this.mSearchSrcTextView.getText();
        Object object = this.mSuggestionsAdapter.getCursor();
        if (object == null) {
            return;
        }
        if (object.moveToPosition(n2)) {
            if ((object = this.mSuggestionsAdapter.convertToString((Cursor)object)) != null) {
                this.setQuery((CharSequence)object);
                return;
            }
            this.setQuery((CharSequence)editable);
            return;
        }
        this.setQuery((CharSequence)editable);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setImeVisibility(boolean bl2) {
        if (bl2) {
            this.post(this.mShowImeRunnable);
            return;
        } else {
            this.removeCallbacks(this.mShowImeRunnable);
            InputMethodManager inputMethodManager = (InputMethodManager)this.getContext().getSystemService("input_method");
            if (inputMethodManager == null) return;
            {
                inputMethodManager.hideSoftInputFromWindow(this.getWindowToken(), 0);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setQuery(CharSequence charSequence) {
        this.mSearchSrcTextView.setText(charSequence);
        SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
        int n2 = TextUtils.isEmpty((CharSequence)charSequence) ? 0 : charSequence.length();
        searchAutoComplete.setSelection(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateCloseButton() {
        int n2 = 1;
        int n3 = 0;
        boolean bl2 = !TextUtils.isEmpty((CharSequence)this.mSearchSrcTextView.getText());
        int n4 = n2;
        if (!bl2) {
            n4 = this.mIconifiedByDefault && !this.mExpandedInActionView ? n2 : 0;
        }
        Object object = this.mCloseButton;
        n4 = n4 != 0 ? n3 : 8;
        object.setVisibility(n4);
        Drawable drawable2 = this.mCloseButton.getDrawable();
        if (drawable2 != null) {
            object = bl2 ? (Object)ENABLED_STATE_SET : (Object)EMPTY_STATE_SET;
            drawable2.setState((int[])object);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateFocusedState() {
        int[] arrn = this.mSearchSrcTextView.hasFocus() ? FOCUSED_STATE_SET : EMPTY_STATE_SET;
        Drawable drawable2 = this.mSearchPlate.getBackground();
        if (drawable2 != null) {
            drawable2.setState(arrn);
        }
        if ((drawable2 = this.mSubmitArea.getBackground()) != null) {
            drawable2.setState(arrn);
        }
        this.invalidate();
    }

    private void updateQueryHint() {
        CharSequence charSequence = this.getQueryHint();
        SearchAutoComplete searchAutoComplete = this.mSearchSrcTextView;
        CharSequence charSequence2 = charSequence;
        if (charSequence == null) {
            charSequence2 = "";
        }
        searchAutoComplete.setHint(this.getDecoratedHint(charSequence2));
    }

    @TargetApi(value=8)
    private void updateSearchAutoComplete() {
        int n2;
        int n3 = 1;
        this.mSearchSrcTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mSearchSrcTextView.setImeOptions(this.mSearchable.getImeOptions());
        int n4 = n2 = this.mSearchable.getInputType();
        if ((n2 & 15) == 1) {
            n4 = n2 &= -65537;
            if (this.mSearchable.getSuggestAuthority() != null) {
                n4 = n2 | 65536 | 524288;
            }
        }
        this.mSearchSrcTextView.setInputType(n4);
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.changeCursor(null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            this.mSuggestionsAdapter = new SuggestionsAdapter(this.getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mSearchSrcTextView.setAdapter((ListAdapter)this.mSuggestionsAdapter);
            SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter)this.mSuggestionsAdapter;
            n4 = n3;
            if (this.mQueryRefinement) {
                n4 = 2;
            }
            suggestionsAdapter.setQueryRefinement(n4);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void updateSubmitArea() {
        var1_2 = var2_1 = 8;
        if (!this.isSubmitAreaEnabled()) ** GOTO lbl7
        if (this.mGoButton.getVisibility() == 0) ** GOTO lbl-1000
        var1_2 = var2_1;
        if (this.mVoiceButton.getVisibility() == 0) lbl-1000: // 2 sources:
        {
            var1_2 = 0;
        }
lbl7: // 4 sources:
        this.mSubmitArea.setVisibility(var1_2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void updateSubmitButton(boolean var1_1) {
        var2_3 = var3_2 = 8;
        if (!this.mSubmitButtonEnabled) ** GOTO lbl11
        var2_3 = var3_2;
        if (!this.isSubmitAreaEnabled()) ** GOTO lbl11
        var2_3 = var3_2;
        if (!this.hasFocus()) ** GOTO lbl11
        if (var1_1) ** GOTO lbl-1000
        var2_3 = var3_2;
        if (!this.mVoiceButtonEnabled) lbl-1000: // 2 sources:
        {
            var2_3 = 0;
        }
lbl11: // 6 sources:
        this.mGoButton.setVisibility(var2_3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateViewsVisibility(boolean bl2) {
        boolean bl3 = true;
        int n2 = 8;
        this.mIconified = bl2;
        int n3 = bl2 ? 0 : 8;
        boolean bl4 = !TextUtils.isEmpty((CharSequence)this.mSearchSrcTextView.getText());
        this.mSearchButton.setVisibility(n3);
        this.updateSubmitButton(bl4);
        View view = this.mSearchEditFrame;
        n3 = bl2 ? 8 : 0;
        view.setVisibility(n3);
        view = this.mCollapsedIcon;
        n3 = this.mIconifiedByDefault ? n2 : 0;
        view.setVisibility(n3);
        this.updateCloseButton();
        bl2 = !bl4 ? bl3 : false;
        this.updateVoiceButton(bl2);
        this.updateSubmitArea();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateVoiceButton(boolean bl2) {
        int n2;
        if (this.mVoiceButtonEnabled && !this.isIconified() && bl2) {
            n2 = 0;
            this.mGoButton.setVisibility(8);
        } else {
            n2 = 8;
        }
        this.mVoiceButton.setVisibility(n2);
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        this.setImeVisibility(false);
        super.clearFocus();
        this.mSearchSrcTextView.clearFocus();
        this.mClearingFocus = false;
    }

    public int getImeOptions() {
        return this.mSearchSrcTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public CharSequence getQuery() {
        return this.mSearchSrcTextView.getText();
    }

    public CharSequence getQueryHint() {
        if (this.mQueryHint != null) {
            return this.mQueryHint;
        }
        if (IS_AT_LEAST_FROYO && this.mSearchable != null && this.mSearchable.getHintId() != 0) {
            return this.getContext().getText(this.mSearchable.getHintId());
        }
        return this.mDefaultQueryHint;
    }

    int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public CursorAdapter getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }

    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }

    @Override
    public void onActionViewCollapsed() {
        this.setQuery("", false);
        this.clearFocus();
        this.updateViewsVisibility(true);
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = false;
    }

    @Override
    public void onActionViewExpanded() {
        if (this.mExpandedInActionView) {
            return;
        }
        this.mExpandedInActionView = true;
        this.mCollapsedImeOptions = this.mSearchSrcTextView.getImeOptions();
        this.mSearchSrcTextView.setImeOptions(this.mCollapsedImeOptions | 33554432);
        this.mSearchSrcTextView.setText((CharSequence)"");
        this.setIconified(false);
    }

    protected void onDetachedFromWindow() {
        this.removeCallbacks(this.mUpdateDrawableStateRunnable);
        this.post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onMeasure(int n2, int n3) {
        if (this.isIconified()) {
            super.onMeasure(n2, n3);
            return;
        }
        int n4 = View.MeasureSpec.getMode((int)n2);
        int n5 = View.MeasureSpec.getSize((int)n2);
        switch (n4) {
            default: {
                n2 = n5;
                break;
            }
            case Integer.MIN_VALUE: {
                if (this.mMaxWidth > 0) {
                    n2 = Math.min((int)this.mMaxWidth, (int)n5);
                    break;
                }
                n2 = Math.min((int)this.getPreferredWidth(), (int)n5);
                break;
            }
            case 1073741824: {
                n2 = n5;
                if (this.mMaxWidth <= 0) break;
                n2 = Math.min((int)this.mMaxWidth, (int)n5);
                break;
            }
            case 0: {
                n2 = this.mMaxWidth > 0 ? this.mMaxWidth : this.getPreferredWidth();
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int)n2, (int)1073741824), n3);
    }

    void onQueryRefine(CharSequence charSequence) {
        this.setQuery(charSequence);
    }

    void onTextFocusChanged() {
        this.updateViewsVisibility(this.isIconified());
        this.postUpdateFocusedState();
        if (this.mSearchSrcTextView.hasFocus()) {
            this.forceSuggestionQuery();
        }
    }

    public void onWindowFocusChanged(boolean bl2) {
        super.onWindowFocusChanged(bl2);
        this.postUpdateFocusedState();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean requestFocus(int n2, Rect rect) {
        if (this.mClearingFocus || !this.isFocusable()) {
            return false;
        }
        if (this.isIconified()) {
            return super.requestFocus(n2, rect);
        }
        boolean bl2 = this.mSearchSrcTextView.requestFocus(n2, rect);
        if (bl2) {
            this.updateViewsVisibility(false);
        }
        return bl2;
    }

    public void setAppSearchData(Bundle bundle) {
        this.mAppSearchData = bundle;
    }

    public void setIconified(boolean bl2) {
        if (bl2) {
            this.onCloseClicked();
            return;
        }
        this.onSearchClicked();
    }

    public void setIconifiedByDefault(boolean bl2) {
        if (this.mIconifiedByDefault == bl2) {
            return;
        }
        this.mIconifiedByDefault = bl2;
        this.updateViewsVisibility(bl2);
        this.updateQueryHint();
    }

    public void setImeOptions(int n2) {
        this.mSearchSrcTextView.setImeOptions(n2);
    }

    public void setInputType(int n2) {
        this.mSearchSrcTextView.setInputType(n2);
    }

    public void setMaxWidth(int n2) {
        this.mMaxWidth = n2;
        this.requestLayout();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.mOnCloseListener = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.mOnQueryTextFocusChangeListener = onFocusChangeListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.mOnSearchClickListener = onClickListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.mOnSuggestionListener = onSuggestionListener;
    }

    public void setQuery(CharSequence charSequence, boolean bl2) {
        this.mSearchSrcTextView.setText(charSequence);
        if (charSequence != null) {
            this.mSearchSrcTextView.setSelection(this.mSearchSrcTextView.length());
            this.mUserQuery = charSequence;
        }
        if (bl2 && !TextUtils.isEmpty((CharSequence)charSequence)) {
            this.onSubmitQuery();
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.mQueryHint = charSequence;
        this.updateQueryHint();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setQueryRefinementEnabled(boolean bl2) {
        this.mQueryRefinement = bl2;
        if (this.mSuggestionsAdapter instanceof SuggestionsAdapter) {
            SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter)this.mSuggestionsAdapter;
            int n2 = bl2 ? 2 : 1;
            suggestionsAdapter.setQueryRefinement(n2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.mSearchable = searchableInfo;
        if (this.mSearchable != null) {
            if (IS_AT_LEAST_FROYO) {
                this.updateSearchAutoComplete();
            }
            this.updateQueryHint();
        }
        boolean bl2 = IS_AT_LEAST_FROYO && this.hasVoiceSearch();
        this.mVoiceButtonEnabled = bl2;
        if (this.mVoiceButtonEnabled) {
            this.mSearchSrcTextView.setPrivateImeOptions("nm");
        }
        this.updateViewsVisibility(this.isIconified());
    }

    public void setSubmitButtonEnabled(boolean bl2) {
        this.mSubmitButtonEnabled = bl2;
        this.updateViewsVisibility(this.isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter cursorAdapter) {
        this.mSuggestionsAdapter = cursorAdapter;
        this.mSearchSrcTextView.setAdapter((ListAdapter)this.mSuggestionsAdapter);
    }

    static class AutoCompleteTextViewReflector {
        private Method doAfterTextChanged;
        private Method doBeforeTextChanged;
        private Method ensureImeVisible;
        private Method showSoftInputUnchecked;

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        AutoCompleteTextViewReflector() {
            try {
                this.doBeforeTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.doBeforeTextChanged.setAccessible(true);
            }
            catch (NoSuchMethodException var1_4) {}
            try {
                this.doAfterTextChanged = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.doAfterTextChanged.setAccessible(true);
            }
            catch (NoSuchMethodException var1_3) {}
            try {
                this.ensureImeVisible = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.ensureImeVisible.setAccessible(true);
            }
            catch (NoSuchMethodException var1_2) {}
            try {
                this.showSoftInputUnchecked = InputMethodManager.class.getMethod("showSoftInputUnchecked", new Class[]{Integer.TYPE, ResultReceiver.class});
                this.showSoftInputUnchecked.setAccessible(true);
                return;
            }
            catch (NoSuchMethodException var1_1) {
                return;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        void doAfterTextChanged(AutoCompleteTextView autoCompleteTextView) {
            if (this.doAfterTextChanged == null) return;
            try {
                this.doAfterTextChanged.invoke((Object)autoCompleteTextView, new Object[0]);
                return;
            }
            catch (Exception var1_2) {
                return;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        void doBeforeTextChanged(AutoCompleteTextView autoCompleteTextView) {
            if (this.doBeforeTextChanged == null) return;
            try {
                this.doBeforeTextChanged.invoke((Object)autoCompleteTextView, new Object[0]);
                return;
            }
            catch (Exception var1_2) {
                return;
            }
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        void ensureImeVisible(AutoCompleteTextView autoCompleteTextView, boolean bl2) {
            if (this.ensureImeVisible == null) return;
            try {
                this.ensureImeVisible.invoke((Object)autoCompleteTextView, new Object[]{bl2});
                return;
            }
            catch (Exception var1_2) {
                return;
            }
        }

        void showSoftInputUnchecked(InputMethodManager inputMethodManager, View view, int n2) {
            if (this.showSoftInputUnchecked != null) {
                try {
                    this.showSoftInputUnchecked.invoke((Object)inputMethodManager, new Object[]{n2, null});
                    return;
                }
                catch (Exception var4_4) {
                    // empty catch block
                }
            }
            inputMethodManager.showSoftInput(view, n2);
        }
    }

    public static interface OnCloseListener {
        public boolean onClose();
    }

    public static interface OnQueryTextListener {
        public boolean onQueryTextChange(String var1);

        public boolean onQueryTextSubmit(String var1);
    }

    public static interface OnSuggestionListener {
        public boolean onSuggestionClick(int var1);

        public boolean onSuggestionSelect(int var1);
    }

    public static class SearchAutoComplete
    extends AppCompatAutoCompleteTextView {
        private SearchView mSearchView;
        private int mThreshold;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int n2) {
            super(context, attributeSet, n2);
            this.mThreshold = this.getThreshold();
        }

        static /* synthetic */ boolean access$1600(SearchAutoComplete searchAutoComplete) {
            return searchAutoComplete.isEmpty();
        }

        private boolean isEmpty() {
            if (TextUtils.getTrimmedLength((CharSequence)this.getText()) == 0) {
                return true;
            }
            return false;
        }

        public boolean enoughToFilter() {
            if (this.mThreshold <= 0 || super.enoughToFilter()) {
                return true;
            }
            return false;
        }

        protected void onFocusChanged(boolean bl2, int n2, Rect rect) {
            super.onFocusChanged(bl2, n2, rect);
            this.mSearchView.onTextFocusChanged();
        }

        public boolean onKeyPreIme(int n2, KeyEvent keyEvent) {
            if (n2 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState dispatcherState = this.getKeyDispatcherState();
                    if (dispatcherState != null) {
                        dispatcherState.startTracking(keyEvent, (Object)this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState dispatcherState = this.getKeyDispatcherState();
                    if (dispatcherState != null) {
                        dispatcherState.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.mSearchView.clearFocus();
                        this.mSearchView.setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(n2, keyEvent);
        }

        public void onWindowFocusChanged(boolean bl2) {
            super.onWindowFocusChanged(bl2);
            if (bl2 && this.mSearchView.hasFocus() && this.getVisibility() == 0) {
                ((InputMethodManager)this.getContext().getSystemService("input_method")).showSoftInput((View)this, 0);
                if (SearchView.isLandscapeMode(this.getContext())) {
                    SearchView.HIDDEN_METHOD_INVOKER.ensureImeVisible(this, true);
                }
            }
        }

        public void performCompletion() {
        }

        protected void replaceText(CharSequence charSequence) {
        }

        void setSearchView(SearchView searchView) {
            this.mSearchView = searchView;
        }

        public void setThreshold(int n2) {
            super.setThreshold(n2);
            this.mThreshold = n2;
        }
    }

}

