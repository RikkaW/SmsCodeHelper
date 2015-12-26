/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.mms.R;
import com.android.mms.ui.MessageListView;
import com.android.mms.ui.PrivateEntryView;

public final class MessageListPullView
extends MessageListView {
    private final float MIN_TIMES_TO_REFRESH = 3.0f;
    private int mActivePointerId = -1;
    private int mDownY = -1;
    private boolean mHasMore = false;
    private boolean mHasPrivateEntry = false;
    private View mHeaderView;
    private int mHeaderViewHeight = 0;
    private boolean mIsRecorded = false;
    private boolean mNeedMoreData = false;
    private OnMoreListener mOnMoreListener = null;
    private PrivateEntryView mPrivateView = null;
    private ProgressBar mProgressBar = null;
    private RefreshStatus mRefreshStatus = RefreshStatus.none;
    private int mScaleMotionY = 0;
    private int mScaleOffset = 0;
    private float mScaleScrollY = 0.0f;
    private ScrolledListener mScrolledListener;
    private TextView mTextView = null;

    public MessageListPullView(Context context) {
        super(context);
        this.init(context, null);
    }

    public MessageListPullView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.init(context, attributeSet);
    }

    private void doMore() {
        this.setRefreshStatus(RefreshStatus.refreshing);
        this.mHeaderView.setPadding(0, 0, 0, 0);
        if (this.mOnMoreListener != null) {
            this.mOnMoreListener.onMore();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            attributeSet = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.EditableListView, 0, 0);
            this.mHasPrivateEntry = attributeSet.getBoolean(1, this.mHasPrivateEntry);
            this.mHasMore = attributeSet.getBoolean(2, this.mHasMore);
        }
        if (this.mHasPrivateEntry) {
            this.mHeaderView = LayoutInflater.from((Context)context).inflate(2130968597, (ViewGroup)this, false);
            this.mPrivateView = (PrivateEntryView)this.mHeaderView.findViewById(2131820602);
            this.mPrivateView.setBackgroundResId(2130837904);
        } else if (this.mHasMore) {
            this.mHeaderView = LayoutInflater.from((Context)context).inflate(2130968662, (ViewGroup)this, false);
            this.mProgressBar = (ProgressBar)this.mHeaderView.findViewById(2131820777);
            this.mTextView = (TextView)this.mHeaderView.findViewById(2131820778);
        }
        this.mHeaderViewHeight = 0;
        if (this.mHeaderView != null) {
            this.mHeaderView.measure(View.MeasureSpec.makeMeasureSpec((int)0, (int)0), View.MeasureSpec.makeMeasureSpec((int)0, (int)0));
            this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
            this.addHeaderView(this.mHeaderView, (Object)null, false);
            this.doneMore();
        }
        return;
        finally {
            attributeSet.recycle();
        }
    }

    private boolean isTopVisible() {
        if (this.getFirstVisiblePosition() == 0) {
            return true;
        }
        return false;
    }

    private void onPrivateViewScroll(int n) {
        if (this.mScrolledListener != null) {
            this.mScrolledListener.onScrolled(n);
        }
        if (this.mPrivateView != null) {
            this.mPrivateView.onScroll(n);
        }
    }

    private void refreshStatusString() {
        switch (.$SwitchMap$com$android$mms$ui$MessageListPullView$RefreshStatus[this.mRefreshStatus.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.mTextView.setText(2131362307);
                this.mProgressBar.setProgress(0);
                return;
            }
            case 2: {
                this.mTextView.setText(2131362308);
                return;
            }
            case 3: {
                this.mTextView.setText(2131362309);
                return;
            }
            case 4: 
        }
        this.mTextView.setText(2131362310);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int scaleOverScrollDeltaY(int var1_1, int var2_2) {
        var3_3 = 0.0f;
        var6_4 = (int)(Math.signum((float)var1_1) * 1.0f);
        var5_5 = 0;
        do {
            if (var5_5 >= Math.abs((int)var1_1)) ** GOTO lbl-1000
            var4_6 = (float)Math.pow((double)(Math.abs((float)((float)var2_2 + this.mScaleScrollY + var3_3)) / 2000.0f), (double)3.0);
            if ((double)Math.abs((float)(var4_6 = (float)var6_4 / (var4_6 * 98.0f + 2.0f))) < 0.001) lbl-1000: // 2 sources:
            {
                var1_1 = (int)(this.mScaleScrollY + var3_3);
                this.mScaleScrollY = this.mScaleScrollY + var3_3 - (float)var1_1;
                return var1_1;
            }
            var3_3 += var4_6;
            ++var5_5;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setRefreshStatus(RefreshStatus refreshStatus) {
        if (this.mHasMore && this.mRefreshStatus != refreshStatus) {
            this.mRefreshStatus = refreshStatus;
            if (this.mRefreshStatus == RefreshStatus.refreshing) {
                this.mProgressBar.setVisibility(0);
            } else {
                this.mProgressBar.setVisibility(8);
            }
            this.refreshStatusString();
            this.invalidate();
        }
    }

    public void doneMore() {
        if (this.mHasMore) {
            this.setRefreshStatus(RefreshStatus.normal);
        }
        this.mHeaderView.setPadding(0, this.mHeaderViewHeight * -1 + 1, 0, 0);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean onTouchEvent(MotionEvent var1_1) {
        var2_2 = 1;
        if (!this.mHasMore && !this.mHasPrivateEntry) {
            return super.onTouchEvent(var1_1);
        }
        switch (var1_1.getActionMasked()) {
            case 0: {
                if (this.mHasMore && !this.mNeedMoreData) {
                    this.setRefreshStatus(RefreshStatus.norefresh);
                    ** break;
                }
                if (this.mIsRecorded != false) return super.onTouchEvent(var1_1);
                if (this.isTopVisible() == false) return super.onTouchEvent(var1_1);
                if (!this.mHasMore || this.mRefreshStatus != RefreshStatus.normal) {
                    if (this.mHasPrivateEntry == false) return super.onTouchEvent(var1_1);
                }
                this.mScaleMotionY = this.mDownY = (int)var1_1.getY(0);
                this.mScaleOffset = 0;
                this.mIsRecorded = true;
                this.mActivePointerId = var1_1.getPointerId(0);
                ** break;
            }
            case 1: 
            case 3: {
                this.mIsRecorded = false;
                this.mScaleScrollY = 0.0f;
                this.mScaleOffset = 0;
                if (this.mHasMore && this.mRefreshStatus == RefreshStatus.willrefresh) {
                    this.doMore();
                    ** break;
                }
                this.mHeaderView.setPadding(0, - this.mHeaderViewHeight, 0, 0);
                if (this.mHasPrivateEntry == false) return super.onTouchEvent(var1_1);
                this.onPrivateViewScroll(0);
                ** break;
            }
            case 2: {
                if (!this.mIsRecorded && this.isTopVisible()) {
                    if (!this.mHasMore || this.mRefreshStatus != RefreshStatus.normal) {
                        if (this.mHasPrivateEntry == false) return super.onTouchEvent(var1_1);
                    }
                    var2_2 = var3_3 = var1_1.findPointerIndex(this.mActivePointerId);
                    if (var3_3 == -1) {
                        var2_2 = 0;
                        this.mActivePointerId = var1_1.getPointerId(0);
                    }
                    this.mScaleMotionY = this.mDownY = (int)var1_1.getY(var2_2);
                    this.mScaleOffset = 0;
                    this.mIsRecorded = true;
                    ** break;
                }
                if (this.mIsRecorded == false) return super.onTouchEvent(var1_1);
                var2_2 = var3_4 = var1_1.findPointerIndex(this.mActivePointerId);
                if (var3_4 == -1) {
                    var2_2 = 0;
                    this.mActivePointerId = var1_1.getPointerId(0);
                }
                var2_2 = (int)var1_1.getY(var2_2);
                this.mScaleOffset += this.scaleOverScrollDeltaY(var2_2 - this.mScaleMotionY, this.mScaleOffset);
                if (this.mScaleOffset > 0) {
                    if (this.mHasMore) {
                        if ((float)this.mScaleOffset >= 3.0f * (float)this.mHeaderViewHeight) {
                            this.setRefreshStatus(RefreshStatus.willrefresh);
                        } else {
                            this.setRefreshStatus(RefreshStatus.normal);
                        }
                    }
                    this.setSelection(0);
                    this.mHeaderView.setPadding(0, - this.mHeaderViewHeight + this.mScaleOffset, 0, 0);
                    if (this.mHasPrivateEntry) {
                        this.onPrivateViewScroll(this.mScaleOffset);
                    }
                }
                this.mScaleMotionY = var2_2;
                ** break;
            }
            case 5: {
                this.mActivePointerId = -1;
                if (this.mIsRecorded == false) return super.onTouchEvent(var1_1);
                var2_2 = var1_1.getActionIndex();
                this.mActivePointerId = var1_1.getPointerId(var2_2);
                this.mScaleMotionY = (int)var1_1.getY(var2_2);
            }
lbl66: // 8 sources:
            default: {
                return super.onTouchEvent(var1_1);
            }
            case 6: 
        }
        if (this.mIsRecorded == false) return super.onTouchEvent(var1_1);
        var3_5 = (var1_1.getAction() & 65280) >> 8;
        if (var1_1.getPointerId(var3_5) != this.mActivePointerId) return super.onTouchEvent(var1_1);
        if (var3_5 != 0) {
            var2_2 = 0;
        }
        this.mActivePointerId = var1_1.getPointerId(var2_2);
        this.mScaleMotionY = (int)var1_1.getY(var2_2);
        return super.onTouchEvent(var1_1);
    }

    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        if (!bl && this.mScrolledListener != null) {
            this.mScrolledListener.onScrolled(this.mScrollY);
        }
    }

    public void setNeedMoreData(boolean bl) {
        this.mNeedMoreData = bl;
    }

    public void setOnMoreListener(OnMoreListener onMoreListener) {
        if (this.mHasMore) {
            this.mOnMoreListener = onMoreListener;
        }
    }

    public void setOnPrivateColorChangeListener(PrivateEntryView.OnPrivateColorChangeListener onPrivateColorChangeListener) {
        if (this.mHasPrivateEntry && this.mPrivateView != null) {
            this.mPrivateView.setOnColorChangeListener(onPrivateColorChangeListener);
        }
    }

    public void setScrolledListener(ScrolledListener scrolledListener) {
        this.mScrolledListener = scrolledListener;
    }

    public static interface OnMoreListener {
        public void onMore();
    }

    static enum RefreshStatus {
        none,
        normal,
        willrefresh,
        refreshing,
        norefresh;
        

        private RefreshStatus() {
        }
    }

    public static interface ScrolledListener {
        public void onScrolled(int var1);
    }

}

