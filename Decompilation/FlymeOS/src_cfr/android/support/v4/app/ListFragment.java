/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.animation.Animation
 *  android.view.animation.AnimationUtils
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment$1;
import android.support.v4.app.ListFragment$2;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListFragment
extends Fragment {
    static final int INTERNAL_EMPTY_ID = 16711681;
    static final int INTERNAL_LIST_CONTAINER_ID = 16711683;
    static final int INTERNAL_PROGRESS_CONTAINER_ID = 16711682;
    ListAdapter mAdapter;
    CharSequence mEmptyText;
    View mEmptyView;
    private final Handler mHandler = new Handler();
    ListView mList;
    View mListContainer;
    boolean mListShown;
    private final AdapterView.OnItemClickListener mOnClickListener;
    View mProgressContainer;
    private final Runnable mRequestFocus;
    TextView mStandardEmptyView;

    public ListFragment() {
        this.mRequestFocus = new ListFragment$1(this);
        this.mOnClickListener = new ListFragment$2(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ensureList() {
        if (this.mList != null) {
            return;
        }
        View view = this.getView();
        if (view == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (view instanceof ListView) {
            this.mList = (ListView)view;
        } else {
            this.mStandardEmptyView = (TextView)view.findViewById(16711681);
            if (this.mStandardEmptyView == null) {
                this.mEmptyView = view.findViewById(16908292);
            } else {
                this.mStandardEmptyView.setVisibility(8);
            }
            this.mProgressContainer = view.findViewById(16711682);
            this.mListContainer = view.findViewById(16711683);
            if (!((view = view.findViewById(16908298)) instanceof ListView)) {
                if (view == null) {
                    throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                }
                throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
            }
            this.mList = (ListView)view;
            if (this.mEmptyView != null) {
                this.mList.setEmptyView(this.mEmptyView);
            } else if (this.mEmptyText != null) {
                this.mStandardEmptyView.setText(this.mEmptyText);
                this.mList.setEmptyView((View)this.mStandardEmptyView);
            }
        }
        this.mListShown = true;
        this.mList.setOnItemClickListener(this.mOnClickListener);
        if (this.mAdapter != null) {
            view = this.mAdapter;
            this.mAdapter = null;
            this.setListAdapter((ListAdapter)view);
        } else if (this.mProgressContainer != null) {
            this.setListShown(false, false);
        }
        this.mHandler.post(this.mRequestFocus);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setListShown(boolean bl2, boolean bl3) {
        this.ensureList();
        if (this.mProgressContainer == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (this.mListShown == bl2) {
            return;
        }
        this.mListShown = bl2;
        if (bl2) {
            if (bl3) {
                this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation((Context)this.getActivity(), (int)17432577));
                this.mListContainer.startAnimation(AnimationUtils.loadAnimation((Context)this.getActivity(), (int)17432576));
            } else {
                this.mProgressContainer.clearAnimation();
                this.mListContainer.clearAnimation();
            }
            this.mProgressContainer.setVisibility(8);
            this.mListContainer.setVisibility(0);
            return;
        }
        if (bl3) {
            this.mProgressContainer.startAnimation(AnimationUtils.loadAnimation((Context)this.getActivity(), (int)17432576));
            this.mListContainer.startAnimation(AnimationUtils.loadAnimation((Context)this.getActivity(), (int)17432577));
        } else {
            this.mProgressContainer.clearAnimation();
            this.mListContainer.clearAnimation();
        }
        this.mProgressContainer.setVisibility(0);
        this.mListContainer.setVisibility(8);
    }

    public ListAdapter getListAdapter() {
        return this.mAdapter;
    }

    public ListView getListView() {
        this.ensureList();
        return this.mList;
    }

    public long getSelectedItemId() {
        this.ensureList();
        return this.mList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        this.ensureList();
        return this.mList.getSelectedItemPosition();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup object, Bundle bundle) {
        object = this.getActivity();
        layoutInflater = new FrameLayout((Context)object);
        bundle = new LinearLayout((Context)object);
        bundle.setId(16711682);
        bundle.setOrientation(1);
        bundle.setVisibility(8);
        bundle.setGravity(17);
        bundle.addView((View)new ProgressBar((Context)object, null, 16842874), (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-2, -2));
        layoutInflater.addView((View)bundle, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        object = new FrameLayout((Context)object);
        object.setId(16711683);
        bundle = new TextView((Context)this.getActivity());
        bundle.setId(16711681);
        bundle.setGravity(17);
        object.addView((View)bundle, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        bundle = new ListView((Context)this.getActivity());
        bundle.setId(16908298);
        bundle.setDrawSelectorOnTop(false);
        object.addView((View)bundle, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        layoutInflater.addView((View)object, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        layoutInflater.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        return layoutInflater;
    }

    @Override
    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mList = null;
        this.mListShown = false;
        this.mListContainer = null;
        this.mProgressContainer = null;
        this.mEmptyView = null;
        this.mStandardEmptyView = null;
        super.onDestroyView();
    }

    public void onListItemClick(ListView listView, View view, int n2, long l2) {
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.ensureList();
    }

    public void setEmptyText(CharSequence charSequence) {
        this.ensureList();
        if (this.mStandardEmptyView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        this.mStandardEmptyView.setText(charSequence);
        if (this.mEmptyText == null) {
            this.mList.setEmptyView((View)this.mStandardEmptyView);
        }
        this.mEmptyText = charSequence;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setListAdapter(ListAdapter listAdapter) {
        boolean bl2 = false;
        boolean bl3 = this.mAdapter != null;
        this.mAdapter = listAdapter;
        if (this.mList != null) {
            this.mList.setAdapter(listAdapter);
            if (!this.mListShown && !bl3) {
                if (this.getView().getWindowToken() != null) {
                    bl2 = true;
                }
                this.setListShown(true, bl2);
            }
        }
    }

    public void setListShown(boolean bl2) {
        this.setListShown(bl2, true);
    }

    public void setListShownNoAnimation(boolean bl2) {
        this.setListShown(bl2, false);
    }

    public void setSelection(int n2) {
        this.ensureList();
        this.mList.setSelection(n2);
    }
}

