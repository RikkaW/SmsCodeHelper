/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.database.DataSetObserver
 *  android.os.Handler
 *  android.os.Message
 *  android.util.AttributeSet
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewConfiguration
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.Adapter
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemLongClickListener
 *  android.widget.BaseAdapter
 *  android.widget.CheckBox
 *  android.widget.HeaderViewListAdapter
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.util.HashMap
 *  java.util.HashSet
 *  junit.framework.Assert
 */
package com.android.mms.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.R;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import junit.framework.Assert;

public class EditableListView
extends ListView {
    private static final int DOUBLE_CLICK_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    private ActionMode mActionMode;
    private EditableListData mCheckedData;
    private DataSetObserver mDataSetObserver;
    private boolean mDragEnabled = false;
    private float mDragStartX = 0.0f;
    private float mDragStartY = 0.0f;
    private Draggable mDraggingItem;
    private ImageView mEmptyImgView;
    private TextView mEmptyTxtView;
    private View mFooterContainer;
    private View mFooterView;
    private Handler mHandler;
    private boolean mHasFillArea;
    private boolean mHasFirstEvent = false;
    private int mHeaderHeight;
    private boolean mHeaderHidden = true;
    private boolean mHeaderHiddenLayoutEnabled;
    private int mHiddenItemCount = 1;
    private long mId = 0;
    private Integer mInitPosition;
    private Message mMessage = null;
    private EditModeWrapper mModeCallback;
    private int mMoveMode = 0;
    private boolean mNeedToScrollEnd = false;
    private OnItemDoubleClickListener mOnDoubleClickListener;
    private AdapterView<?> mParent = null;
    private int mPosition = -1;
    private int mPositionHolder = -1;
    private boolean mPreClickable;
    private AdapterView.OnItemClickListener mPreItemClickListener;
    private boolean mPreLongClickable;
    private int mRowHeight;
    private View mView = null;

    public EditableListView(Context context) {
        super(context);
        this.mHandler = new Handler(){

            public void handleMessage(Message message) {
                switch (message.what) {
                    default: {
                        return;
                    }
                    case 1: {
                        EditableListView.this.mOnDoubleClickListener.onSingleClick(EditableListView.this.mParent, EditableListView.this.mView, EditableListView.this.mPosition, EditableListView.this.mId);
                        EditableListView.this.mHasFirstEvent = false;
                        return;
                    }
                    case 2: 
                }
                EditableListView.this.mOnDoubleClickListener.onDoubleClick(EditableListView.this.mParent, EditableListView.this.mView, EditableListView.this.mPosition, EditableListView.this.mId);
                EditableListView.this.mHasFirstEvent = false;
            }
        };
        this.mDataSetObserver = new DataSetObserver(){

            public void onChanged() {
                if (EditableListView.this.mHasFillArea && EditableListView.this.mHeaderHidden) {
                    EditableListView.this.unregisterDataSetObserver();
                    EditableListView.this.mHeaderHiddenLayoutEnabled = true;
                }
            }
        };
        this.init(null);
    }

    public EditableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new ;
        this.mDataSetObserver = new ;
        this.init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            attributeSet = this.getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.EditableListView, 0, 0);
            this.mHasFillArea = attributeSet.getBoolean(0, this.mHasFillArea);
        }
        if (this.mHasFillArea) {
            this.mFooterView = LayoutInflater.from((Context)this.getContext()).inflate(2130968599, (ViewGroup)this, false);
            this.addFooterView(this.mFooterView);
            this.mFooterContainer = this.mFooterView.findViewById(2131820606);
            this.mEmptyTxtView = (TextView)this.mFooterView.findViewById(2131820608);
            this.mEmptyImgView = (ImageView)this.mFooterView.findViewById(2131820607);
        }
        return;
        finally {
            attributeSet.recycle();
        }
    }

    private void onDraggableItemDragged(int n, int n2) {
        View view;
        if (this.mDraggingItem == null && (view = this.getChildAt(n)) instanceof Draggable) {
            this.mDraggingItem = (Draggable)view;
        }
        if (this.mDraggingItem != null) {
            this.mDraggingItem.onItemDragged(n2);
        }
    }

    private void onDraggableItemRelease(int n) {
        if (this.mDraggingItem != null) {
            this.mDraggingItem.onItemDragReleased(n);
            this.mDraggingItem = null;
        }
    }

    private void registerDataSetObserver() {
        ListAdapter listAdapter = this.getAdapter();
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mDataSetObserver);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setListHeaderHeight(int n) {
        ListAdapter listAdapter = this.getAdapter();
        if (listAdapter == null) {
            this.resetHeaderHeight();
            return;
        } else {
            if (this.mHeaderHeight != 0) return;
            {
                int n2 = this.mHiddenItemCount;
                if (!this.mHeaderHidden) {
                    n2 = 0;
                }
                do {
                    View view;
                    if (n2 >= this.getHeaderViewsCount()) {
                        this.mHeaderHeight = Math.max((int)this.mHeaderHeight, (int)0);
                        return;
                    }
                    if (listAdapter.getItemViewType(n2) == -2 && (view = listAdapter.getView(n2, null, null)) != null) {
                        view.measure(n, 0);
                        this.mHeaderHeight += view.getMeasuredHeight();
                    }
                    ++n2;
                } while (true);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setListItemHeight(int n) {
        ListAdapter listAdapter = this.getAdapter();
        if (listAdapter == null) {
            this.mRowHeight = 0;
            return;
        } else {
            if (this.mRowHeight != 0 || listAdapter.getItemViewType(this.getHeaderViewsCount()) == -2) return;
            {
                if ((listAdapter = listAdapter.getView(this.getHeaderViewsCount(), null, null)) != null) {
                    listAdapter.measure(n, 0);
                    this.mRowHeight = listAdapter.getMeasuredHeight();
                }
                this.mRowHeight = Math.max((int)this.mRowHeight, (int)0);
                return;
            }
        }
    }

    private void triggerDragEvent(MotionEvent motionEvent) {
        this.onDraggableItemDragged(this.pointToPosition((int)this.mDragStartX, (int)this.mDragStartY) - this.getFirstVisiblePosition(), (int)(motionEvent.getX() - this.mDragStartX));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void unregisterDataSetObserver() {
        ListAdapter listAdapter = this.getAdapter();
        if (listAdapter == null) return;
        try {
            listAdapter.unregisterDataSetObserver(this.mDataSetObserver);
            return;
        }
        catch (IllegalStateException var1_2) {
            return;
        }
    }

    public void clearDragAnimation() {
        if (this.mDraggingItem != null) {
            this.mDraggingItem.onItemDragged(0);
            this.mDraggingItem = null;
        }
    }

    public void clearEmptyImgAndTxt() {
        this.mEmptyImgView.setVisibility(8);
        this.mEmptyTxtView.setVisibility(8);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean dispatchTouchEvent(MotionEvent var1_1) {
        if (this.mDragEnabled == false) return super.dispatchTouchEvent(var1_1);
        if (this.isEditMode()) {
            return super.dispatchTouchEvent(var1_1);
        }
        switch (var1_1.getAction()) {
            case 0: {
                this.mMoveMode = 1;
                this.mDragStartX = var1_1.getX();
                this.mDragStartY = var1_1.getY();
                ** break;
            }
            case 2: {
                if (this.mMoveMode != 1) {
                    if (this.mMoveMode != 2) return super.dispatchTouchEvent(var1_1);
                    this.triggerDragEvent(var1_1);
                    var1_1.setAction(3);
                    super.dispatchTouchEvent(var1_1);
                    return true;
                }
                if (var1_1.getX() - this.mDragStartX > 30.0f && Math.abs((float)(var1_1.getY() - this.mDragStartY)) < 15.0f) {
                    this.triggerDragEvent(var1_1);
                    this.mMoveMode = 2;
                    var1_1.setAction(3);
                    super.dispatchTouchEvent(var1_1);
                    return true;
                }
                if (Math.abs((float)(var1_1.getY() - this.mDragStartY)) <= 100.0f) return super.dispatchTouchEvent(var1_1);
                this.mMoveMode = 0;
            }
lbl25: // 3 sources:
            default: {
                return super.dispatchTouchEvent(var1_1);
            }
            case 1: 
        }
        if (this.mMoveMode == 2) {
            this.onDraggableItemRelease((int)(var1_1.getX() - this.mDragStartX));
        }
        this.mMoveMode = 0;
        return super.dispatchTouchEvent(var1_1);
    }

    public void enableDragEvent(boolean bl) {
        this.mDragEnabled = bl;
    }

    public void enableEmptyImgView(boolean bl) {
        if (this.mHasFillArea) {
            this.mEmptyImgView.setEnabled(bl);
        }
    }

    public void enterEditMode(int n) {
        if (this.mModeCallback == null || this.mCheckedData.mEditMode) {
            return;
        }
        this.mInitPosition = n;
        this.mActionMode = this.startActionMode((ActionMode.Callback)this.mModeCallback);
    }

    public void exitEditMode() {
        if (this.mModeCallback == null || !this.mCheckedData.mEditMode) {
            return;
        }
        this.mInitPosition = null;
        this.mActionMode.finish();
    }

    public EditableListViewCheckable getEditableListViewCheckable() {
        if (this.mModeCallback != null) {
            return this.mCheckedData;
        }
        return null;
    }

    public boolean isDataSetChanged() {
        if (this.mModeCallback != null) {
            return this.mCheckedData.mDataSetChanged;
        }
        return false;
    }

    public boolean isEditMode() {
        if (this.mModeCallback != null) {
            return this.mCheckedData.mEditMode;
        }
        return false;
    }

    public void moveToEnd() {
        int n = this.getAdapter().getCount() - this.getFooterViewsCount();
        if (n > 0) {
            this.setSelectionFromTop(n - 1, -100000);
        }
    }

    protected void onDetachedFromWindow() {
        this.unregisterDataSetObserver();
        super.onDetachedFromWindow();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        if (this.mHasFillArea && this.mHeaderHidden && this.mHeaderHiddenLayoutEnabled) {
            this.mHeaderHiddenLayoutEnabled = false;
            this.setSelectionFromTop(this.mHiddenItemCount, 0);
        } else if (this.mNeedToScrollEnd) {
            this.moveToEnd();
        }
        super.onLayout(bl, n, n2, n3, n4);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n, int n2) {
        int n3 = 8;
        if (this.mHasFillArea) {
            int n4 = View.MeasureSpec.getMode((int)n2);
            int n5 = View.MeasureSpec.getSize((int)n2);
            if (n4 == 1073741824 || n4 == Integer.MIN_VALUE) {
                n4 = 0;
                this.setListItemHeight(n);
                ListAdapter listAdapter = this.getAdapter();
                if (listAdapter != null) {
                    int n6;
                    int n7 = listAdapter.getCount() - this.getHeaderViewsCount() - this.getFooterViewsCount();
                    n4 = n6 = n7 * this.mRowHeight;
                    if (this.mEmptyImgView.isEnabled()) {
                        listAdapter = this.mEmptyImgView;
                        n4 = n7 == 0 ? 0 : 8;
                        listAdapter.setVisibility(n4);
                        listAdapter = this.mEmptyTxtView;
                        n4 = n3;
                        if (n7 == 0) {
                            n4 = 0;
                        }
                        listAdapter.setVisibility(n4);
                        n4 = n6;
                    }
                }
                this.setListHeaderHeight(n);
                this.mFooterContainer.getLayoutParams().height = n4 = Math.max((int)(n5 - (n4 + this.mHeaderHeight)), (int)0);
                if (n4 > 0) {
                    this.setVerticalScrollBarEnabled(false);
                } else {
                    this.setVerticalScrollBarEnabled(true);
                }
            }
        }
        super.onMeasure(n, n2);
    }

    public void resetHeaderHeight() {
        this.mHeaderHeight = 0;
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.unregisterDataSetObserver();
        super.setAdapter(listAdapter);
        this.registerDataSetObserver();
        if (this.mModeCallback != null) {
            this.mCheckedData.setAdapter(listAdapter);
        }
    }

    public void setEditModeListener(EditModeListener editModeListener) {
        this.setEditModeListener(editModeListener, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setEditModeListener(EditModeListener editModeListener, boolean bl) {
        if (editModeListener == null) {
            this.mModeCallback = null;
            return;
        }
        if (this.mModeCallback == null) {
            this.mModeCallback = new EditModeWrapper();
        }
        this.mModeCallback.setWrapped(editModeListener);
        if (this.mCheckedData == null) {
            this.mCheckedData = new EditableListData();
        } else {
            this.mCheckedData.clear();
        }
        if (bl) {
            this.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int n, long l) {
                    if (EditableListView.this.getAdapter().getItemViewType(n) == -2) {
                        return false;
                    }
                    EditableListView.this.enterEditMode(n);
                    return true;
                }
            });
        }
        if (!this.mCheckedData.hasAdapter() && this.getAdapter() != null) {
            ListAdapter listAdapter;
            editModeListener = listAdapter = this.getAdapter();
            if (listAdapter instanceof HeaderViewListAdapter) {
                editModeListener = ((HeaderViewListAdapter)listAdapter).getWrappedAdapter();
            }
            this.mCheckedData.setAdapter((ListAdapter)editModeListener);
        }
    }

    public void setEmptyImgViewImageResource(int n) {
        if (this.mHasFillArea) {
            this.mEmptyImgView.setImageResource(n);
        }
    }

    public void setEmptyTxtViewText(int n) {
        if (this.mHasFillArea) {
            this.mEmptyTxtView.setText(n);
        }
    }

    public void setHeaderHidden(boolean bl) {
        this.mHeaderHidden = bl;
    }

    public void setNeedToScrollEnd(boolean bl) {
        this.mNeedToScrollEnd = bl;
    }

    public void setOnItemDoubleClickListener(OnItemDoubleClickListener onItemDoubleClickListener) {
        this.mOnDoubleClickListener = onItemDoubleClickListener;
        if (this.mOnDoubleClickListener == null) {
            return;
        }
        this.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                EditableListView.this.mParent = adapterView;
                EditableListView.this.mView = view;
                EditableListView.this.mPosition = n;
                EditableListView.this.mId = l;
                if (!EditableListView.this.mHasFirstEvent) {
                    EditableListView.this.mPositionHolder = n;
                    EditableListView.this.mHasFirstEvent = true;
                    EditableListView.this.mMessage = EditableListView.this.mHandler.obtainMessage();
                    EditableListView.this.mHandler.removeMessages(1);
                    EditableListView.access$700((EditableListView)EditableListView.this).what = 1;
                    EditableListView.this.mHandler.sendMessageDelayed(EditableListView.this.mMessage, (long)DOUBLE_CLICK_TIMEOUT);
                    return;
                }
                if (EditableListView.this.mPositionHolder == n) {
                    EditableListView.this.mHandler.removeMessages(1);
                    EditableListView.this.mPosition = n;
                    EditableListView.this.mMessage = EditableListView.this.mHandler.obtainMessage();
                    EditableListView.access$700((EditableListView)EditableListView.this).what = 2;
                    EditableListView.this.mHandler.sendMessageAtFrontOfQueue(EditableListView.this.mMessage);
                    EditableListView.this.mHasFirstEvent = false;
                    return;
                }
                EditableListView.this.mMessage = EditableListView.this.mHandler.obtainMessage();
                EditableListView.this.mHandler.removeMessages(1);
                EditableListView.this.mHasFirstEvent = true;
                EditableListView.access$700((EditableListView)EditableListView.this).what = 1;
                EditableListView.this.mPositionHolder = n;
                EditableListView.this.mHandler.sendMessageDelayed(EditableListView.this.mMessage, (long)DOUBLE_CLICK_TIMEOUT);
            }
        });
    }

    public static interface Draggable {
        public void onItemDragReleased(int var1);

        public void onItemDragged(int var1);
    }

    public static interface EditModeListener
    extends ActionMode.Callback {
        public void onCheckStateChanged(EditableListViewCheckable var1);

        public void onVisibleViewCheckStateChanged(View var1, boolean var2);
    }

    class EditModeWrapper
    implements EditModeListener {
        private EditModeListener mWrapped;

        EditModeWrapper() {
        }

        private int handleHeadFooterPosition(int n) {
            if (n < 0) {
                return n;
            }
            int n2 = EditableListView.this.getHeaderViewsCount();
            if (n < n2 || n >= EditableListView.this.getCount() - EditableListView.this.getFooterViewsCount()) {
                return -1;
            }
            return n - n2;
        }

        private void innerEnterEditMode(Integer n) {
            if (EditableListView.access$1900((EditableListView)EditableListView.this).mEditMode) {
                return;
            }
            EditableListView.this.mPreClickable = EditableListView.this.isClickable();
            EditableListView.this.mPreLongClickable = EditableListView.this.isLongClickable();
            EditableListView.this.mPreItemClickListener = EditableListView.this.getOnItemClickListener();
            EditableListView.this.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
                    if ((n = EditModeWrapper.this.handleHeadFooterPosition(n)) != -1) {
                        EditableListView.this.mCheckedData.toggleAt(view, n);
                    }
                }
            });
            EditableListView.this.setLongClickable(false);
            Integer n2 = null;
            if (n != null) {
                n2 = n = Integer.valueOf((int)this.handleHeadFooterPosition(n));
                if (n == -1) {
                    n2 = null;
                }
            }
            EditableListView.this.mCheckedData.enterEditMode(n2);
        }

        private void innerExitEditMode() {
            if (!EditableListView.access$1900((EditableListView)EditableListView.this).mEditMode) {
                return;
            }
            EditableListView.this.setOnItemClickListener(EditableListView.this.mPreItemClickListener);
            EditableListView.this.setClickable(EditableListView.this.mPreClickable);
            EditableListView.this.setLongClickable(EditableListView.this.mPreLongClickable);
            EditableListView.this.mCheckedData.exitEditMode();
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override
        public void onCheckStateChanged(EditableListViewCheckable editableListViewCheckable) {
            this.mWrapped.onCheckStateChanged(editableListViewCheckable);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            if (this.mWrapped.onCreateActionMode(actionMode, menu)) {
                this.innerEnterEditMode(EditableListView.this.mInitPosition);
                return true;
            }
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            EditableListView.this.mActionMode = null;
            this.innerExitEditMode();
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }

        @Override
        public void onVisibleViewCheckStateChanged(View view, boolean bl) {
            this.mWrapped.onVisibleViewCheckStateChanged(view, bl);
        }

        public void setWrapped(EditModeListener editModeListener) {
            this.mWrapped = editModeListener;
        }

    }

    private class EditableListData
    extends DataSetObserver
    implements EditableListViewCheckable {
        private ListAdapter mAdapter;
        private HashSet<Long> mCheckedIds;
        public boolean mDataSetChanged;
        public boolean mEditMode;
        private HashMap<Long, Integer> mIdPositionMap;
        private boolean mInnerDateSetChange;
        private boolean mIsCheckDataDirty;
        private Integer mToggleIndex;

        protected EditableListData() {
            this.clear();
        }

        private void cleanupCheckedData() {
            if (this.mIsCheckDataDirty) {
                HashSet hashSet = new HashSet();
                this.mIdPositionMap.clear();
                for (int i = 0; i < this.mAdapter.getCount(); ++i) {
                    Long l = this.getIdFromPosition(i);
                    if (this.mCheckedIds.contains((Object)l)) {
                        hashSet.add((Object)l);
                    }
                    this.mIdPositionMap.put((Object)l, (Object)i);
                }
                this.mCheckedIds = hashSet;
                this.mIsCheckDataDirty = false;
            }
        }

        private void enterEditMode(Integer n) {
            if (!this.mEditMode && this.mAdapter != null && EditableListView.this.mModeCallback != null) {
                this.mEditMode = true;
                this.mCheckedIds.clear();
                this.mToggleIndex = n;
                if (n != null) {
                    this.innerToggleAt(n);
                    EditableListView.this.mModeCallback.onCheckStateChanged(this);
                }
                if (this.mAdapter instanceof BaseAdapter) {
                    this.mInnerDateSetChange = true;
                    this.mDataSetChanged = true;
                    ((BaseAdapter)this.mAdapter).notifyDataSetChanged();
                    this.mDataSetChanged = false;
                }
            }
        }

        private void exitEditMode() {
            if (this.mEditMode && this.mAdapter != null && EditableListView.this.mModeCallback != null) {
                this.mEditMode = false;
                this.mCheckedIds.clear();
                if (this.mAdapter instanceof BaseAdapter) {
                    this.mInnerDateSetChange = true;
                    this.mDataSetChanged = true;
                    ((BaseAdapter)this.mAdapter).notifyDataSetChanged();
                    this.mDataSetChanged = false;
                }
            }
        }

        private long getIdFromPosition(int n) {
            if (this.mAdapter instanceof EditableListIdMapper) {
                return ((EditableListIdMapper)this.mAdapter).mapPositionToId(n);
            }
            return this.mAdapter.getItemId(n);
        }

        private boolean hasAdapter() {
            if (this.mAdapter != null) {
                return true;
            }
            return false;
        }

        private boolean innerToggleAt(int n) {
            if (this.mAdapter instanceof ICheckableAdapter && !((ICheckableAdapter)this.mAdapter).allowChecked(n)) {
                return false;
            }
            long l = this.getIdFromPosition(n);
            if (this.mCheckedIds.contains((Object)l)) {
                this.mCheckedIds.remove((Object)l);
                return false;
            }
            this.mCheckedIds.add((Object)l);
            return true;
        }

        private void notifyCheckStateChanged(Integer n) {
            this.mToggleIndex = n;
            if (EditableListView.this.mModeCallback != null) {
                EditableListView.this.mModeCallback.onCheckStateChanged(this);
            }
        }

        private void setAdapter(ListAdapter listAdapter) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver((DataSetObserver)this);
            }
            this.mAdapter = listAdapter;
            if (this.mAdapter != null) {
                Assert.assertEquals((boolean)true, (boolean)(this.mAdapter instanceof BaseAdapter));
                if (!this.mAdapter.hasStableIds()) {
                    Assert.assertEquals((boolean)true, (boolean)(this.mAdapter instanceof EditableListIdMapper));
                }
                this.mAdapter.registerDataSetObserver((DataSetObserver)this);
            }
            this.mCheckedIds.clear();
            this.mIdPositionMap.clear();
            this.mIsCheckDataDirty = true;
            if (this.mEditMode) {
                this.notifyCheckStateChanged(null);
            }
        }

        private Boolean toggleAt(View view, int n) {
            if (this.mEditMode && this.mAdapter != null) {
                boolean bl = this.innerToggleAt(n);
                CheckBox checkBox = (CheckBox)view.findViewById(16908289);
                if (checkBox != null && checkBox.isEnabled()) {
                    checkBox.setChecked(bl);
                }
                if (EditableListView.this.mModeCallback != null) {
                    EditableListView.this.mModeCallback.onVisibleViewCheckStateChanged(view, bl);
                }
                this.notifyCheckStateChanged(n);
                return bl;
            }
            return null;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void checkAll() {
            if (this.mEditMode && this.mAdapter != null) {
                int n;
                long l;
                ICheckableAdapter iCheckableAdapter;
                if (!(this.mAdapter instanceof ICheckableAdapter)) {
                    for (n = 0; n < this.mAdapter.getCount(); ++n) {
                        l = this.getIdFromPosition(n);
                        this.mCheckedIds.add((Object)l);
                    }
                } else {
                    iCheckableAdapter = (ICheckableAdapter)this.mAdapter;
                    int n2 = iCheckableAdapter.getDisableCheckedCount();
                    int n3 = 0;
                    for (n = 0; n < this.mAdapter.getCount(); ++n) {
                        if (n3 == n2) {
                            l = this.getIdFromPosition(n);
                            this.mCheckedIds.add((Object)l);
                            continue;
                        }
                        if (iCheckableAdapter.allowChecked(n)) {
                            l = this.getIdFromPosition(n);
                            this.mCheckedIds.add((Object)l);
                            continue;
                        }
                        ++n3;
                    }
                }
                for (n = 0; n < EditableListView.this.getChildCount(); ++n) {
                    iCheckableAdapter = EditableListView.this.getChildAt(n);
                    CheckBox checkBox = (CheckBox)iCheckableAdapter.findViewById(16908289);
                    if (checkBox != null && checkBox.isEnabled()) {
                        checkBox.setChecked(true);
                    }
                    if (EditableListView.this.mModeCallback == null) continue;
                    EditableListView.this.mModeCallback.onVisibleViewCheckStateChanged((View)iCheckableAdapter, true);
                }
                this.notifyCheckStateChanged(null);
            }
        }

        @Override
        public void checkNothing() {
            if (this.mEditMode && this.mAdapter != null) {
                this.mCheckedIds.clear();
                for (int i = 0; i < EditableListView.this.getChildCount(); ++i) {
                    View view = EditableListView.this.getChildAt(i);
                    CheckBox checkBox = (CheckBox)view.findViewById(16908289);
                    if (checkBox != null && checkBox.isEnabled()) {
                        checkBox.setChecked(false);
                    }
                    if (EditableListView.this.mModeCallback == null) continue;
                    EditableListView.this.mModeCallback.onVisibleViewCheckStateChanged(view, false);
                }
                this.notifyCheckStateChanged(null);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void clear() {
            this.mInnerDateSetChange = false;
            this.mEditMode = false;
            this.mIsCheckDataDirty = true;
            if (this.mCheckedIds == null) {
                this.mCheckedIds = new HashSet();
            } else {
                this.mCheckedIds.clear();
            }
            this.mToggleIndex = null;
            if (this.mIdPositionMap == null) {
                this.mIdPositionMap = new HashMap();
                return;
            }
            this.mIdPositionMap.clear();
        }

        @Override
        public int count() {
            if (this.mEditMode && this.mAdapter != null) {
                this.cleanupCheckedData();
                return this.mCheckedIds.size();
            }
            return 0;
        }

        @Override
        public HashSet<Long> getCheckedItemInIds() {
            if (this.mEditMode && this.mAdapter != null) {
                this.cleanupCheckedData();
                return new HashSet(this.mCheckedIds);
            }
            return new HashSet();
        }

        @Override
        public HashSet<Integer> getCheckedItemInPositions() {
            HashSet hashSet = new HashSet();
            if (this.mEditMode && this.mAdapter != null) {
                this.cleanupCheckedData();
                for (Long l : this.mCheckedIds) {
                    hashSet.add(this.mIdPositionMap.get((Object)l));
                }
            }
            return hashSet;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public boolean isAllChecked() {
            int n;
            int n2;
            ICheckableAdapter iCheckableAdapter;
            boolean bl = true;
            boolean bl2 = true;
            if (!this.mEditMode) return false;
            if (this.mAdapter == null) return false;
            this.cleanupCheckedData();
            if (this.mAdapter instanceof ICheckableAdapter) {
                iCheckableAdapter = (ICheckableAdapter)this.mAdapter;
                n = iCheckableAdapter.getDisableCheckedCount();
                n2 = 0;
            } else {
                if (this.mAdapter.getCount() != this.mCheckedIds.size()) return false;
                return bl;
            }
            for (int i = 0; i < this.mAdapter.getCount(); ++i) {
                long l;
                if (n2 == n) {
                    l = this.getIdFromPosition(i);
                    if (this.mCheckedIds.contains((Object)l)) continue;
                    return false;
                }
                if (iCheckableAdapter.allowChecked(i)) {
                    l = this.getIdFromPosition(i);
                    if (!this.mCheckedIds.contains((Object)l)) return false;
                    continue;
                }
                ++n2;
            }
            if (this.mCheckedIds.size() <= 0) return false;
            return bl2;
        }

        public void onChanged() {
            if (!this.mInnerDateSetChange) {
                this.mIsCheckDataDirty = true;
                if (this.mEditMode) {
                    this.notifyCheckStateChanged(null);
                }
            }
            this.mInnerDateSetChange = false;
        }

        public void onInvalidated() {
            this.mCheckedIds.clear();
            this.mIdPositionMap.clear();
            this.mIsCheckDataDirty = true;
            if (this.mEditMode) {
                this.notifyCheckStateChanged(null);
            }
        }
    }

    public static interface EditableListIdMapper {
        public long mapPositionToId(int var1);
    }

    public static interface EditableListViewCheckable {
        public void checkAll();

        public void checkNothing();

        public int count();

        public HashSet<Long> getCheckedItemInIds();

        public HashSet<Integer> getCheckedItemInPositions();

        public boolean isAllChecked();
    }

    public static interface ICheckableAdapter {
        public boolean allowChecked(int var1);

        public int getDisableCheckedCount();
    }

    public static interface OnItemDoubleClickListener {
        public void onDoubleClick(AdapterView<?> var1, View var2, int var3, long var4);

        public void onSingleClick(AdapterView<?> var1, View var2, int var3, long var4);
    }

}

