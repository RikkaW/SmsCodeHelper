/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActionBar
 *  android.app.ActionBar$Tab
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  miui.app.ActionBar
 *  miui.app.Activity
 *  miui.os.Build
 *  miui.telephony.SubscriptionManager
 *  miui.telephony.SubscriptionManager$OnSubscriptionsChangedListener
 */
package com.android.mms.ui;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.MmsSystemEventReceiver;
import com.android.mms.ui.ISmsTextSizeAdjustHost;
import com.android.mms.ui.SimMessagesFragment;
import com.android.mms.ui.SmsTextSizeAdjust;
import com.android.mms.util.MSimUtils;
import miui.app.Activity;
import miui.os.Build;
import miui.telephony.SubscriptionManager;

public class ManageSimMessages
extends Activity
implements ISmsTextSizeAdjustHost {
    private miui.app.ActionBar mActionBar;
    private SubscriptionManager.OnSubscriptionsChangedListener mSimInfoChangeListener;
    private SimState mSimState;
    private MmsSystemEventReceiver.SimStateChangedListener mSimStateChagnedListener;

    public ManageSimMessages() {
        this.mSimState = new SimState((Context)this);
        this.mSimInfoChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener(){

            public void onSubscriptionsChanged() {
                Log.d((String)"ManageSimMessages", (String)"update sim info change");
                ManageSimMessages.this.updateSimState();
            }
        };
        this.mSimStateChagnedListener = new MmsSystemEventReceiver.SimStateChangedListener(){

            @Override
            public void onSimStateChanged(String string2) {
                Log.d((String)"ManageSimMessages", (String)"update sim info change");
                if (!MSimUtils.isSimInserted()) {
                    ManageSimMessages.this.finish();
                }
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private int getTabPosition(int n) {
        if (n == 0 || n != 1) {
            return 0;
        }
        return this.mActionBar.getFragmentTabCount() - 1;
    }

    private void registerSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.registerChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().registerSimStateChangedListener(this.mSimStateChagnedListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setSelected(int n) {
        if (n == 0) {
            if (!this.mSimState.mSim0Inserted) return;
            {
                n = this.getTabPosition(n);
                this.mActionBar.selectTab(this.mActionBar.getTabAt(n));
                Log.d((String)"ManageSimMessages", (String)"set selected is slotId 0");
                return;
            }
        } else {
            if (n != 1 || !this.mSimState.mSim1Inserted) return;
            {
                n = this.getTabPosition(n);
                this.mActionBar.selectTab(this.mActionBar.getTabAt(n));
                Log.d((String)"ManageSimMessages", (String)"set selected is slotId 1");
                return;
            }
        }
    }

    private void unRegisterSimRelatedListener() {
        if (MSimUtils.isMSim()) {
            MSimUtils.unregisterChangeListener((Context)this, this.mSimInfoChangeListener);
            return;
        }
        MmsSystemEventReceiver.getInstance().unRegisterSimStateChangedListener(this.mSimStateChagnedListener);
    }

    private void updateFragments() {
        Bundle bundle;
        boolean bl = false;
        if (!this.mSimState.mStateChanged) {
            return;
        }
        FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (this.mActionBar.getFragmentTabCount() > 0) {
            this.mActionBar.removeAllFragmentTab();
        }
        if (this.mSimState.mSim0Inserted) {
            bundle = new Bundle();
            bundle.putInt(MSimUtils.SLOT_ID, 0);
            this.mActionBar.addFragmentTab("sim0", this.mActionBar.newTab().setText((CharSequence)""), (Class)SimMessagesFragment.class, bundle, false);
            Log.d((String)"ManageSimMessages", (String)"updateFragments add sim0 fragment");
        }
        if (this.mSimState.mSim1Inserted) {
            bundle = new Bundle();
            bundle.putInt(MSimUtils.SLOT_ID, 1);
            this.mActionBar.addFragmentTab("sim1", this.mActionBar.newTab().setText((CharSequence)""), (Class)SimMessagesFragment.class, bundle, false);
            Log.d((String)"ManageSimMessages", (String)"updateFragments add sim1 fragment");
        }
        bundle = this.mActionBar;
        if (this.mActionBar.getFragmentTabCount() <= 1) {
            bl = true;
        }
        bundle.setTabsMode(bl);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    private void updateSimInfos() {
        this.updateSimInfo(0);
        this.updateSimInfo(1);
    }

    private void updateSimState() {
        this.mSimState.updateSimState();
        if (this.mSimState.mSimCount == 0) {
            Log.d((String)"ManageSimMessages", (String)"updateSimState sim count == 0");
            this.finish();
            return;
        }
        this.updateFragments();
        this.updateSimInfos();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActionBar = this.getActionBar();
        this.mActionBar.setFragmentViewPagerMode((Context)this, this.getFragmentManager());
        this.updateSimState();
        this.setSelected(MSimUtils.getSlotIdFromIntent(this.getIntent()));
        MessagingNotification.cancelNotification(this.getApplicationContext(), 234);
        this.registerSimRelatedListener();
    }

    protected void onDestroy() {
        this.unRegisterSimRelatedListener();
        super.onDestroy();
    }

    public void onStart() {
        super.onStart();
        SmsTextSizeAdjust.getInstance().init(this, (android.app.Activity)this);
        SmsTextSizeAdjust.getInstance().refresh();
    }

    public void onStop() {
        super.onStop();
        SmsTextSizeAdjust.getInstance();
        SmsTextSizeAdjust.clear(this);
    }

    @Override
    public void setTextSize(float f2) {
        SimMessagesFragment.sTextSize = f2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void updateSimInfo(int n) {
        if (n == 0) {
            if (!this.mSimState.mSim0Inserted) return;
            {
                int n2 = this.getTabPosition(n);
                int n3 = ((SimMessagesFragment)this.mActionBar.getFragmentAt(n2)).getMessageCount();
                this.mSimState.updateSimInfo(n, n3);
                if (!this.mSimState.mInfoChanged) return;
                {
                    if (this.mActionBar.getFragmentTabCount() > 1) {
                        this.mActionBar.getTabAt(n2).setText((CharSequence)this.mSimState.mSim0Name);
                    } else {
                        this.mActionBar.setTitle((CharSequence)this.mSimState.mSim0Name);
                    }
                    Log.d((String)"ManageSimMessages", (String)"update sim info for slotId 0");
                    return;
                }
            }
        }
        if (n != 1 || !this.mSimState.mSim1Inserted) return;
        int n4 = this.getTabPosition(n);
        int n5 = ((SimMessagesFragment)this.mActionBar.getFragmentAt(n4)).getMessageCount();
        this.mSimState.updateSimInfo(n, n5);
        if (!this.mSimState.mInfoChanged) {
            return;
        }
        if (this.mActionBar.getFragmentTabCount() > 1) {
            this.mActionBar.getTabAt(n4).setText((CharSequence)this.mSimState.mSim1Name);
        } else {
            this.mActionBar.setTitle((CharSequence)this.mSimState.mSim1Name);
        }
        Log.d((String)"ManageSimMessages", (String)"update sim info for slotId 1");
    }

    static class SimState {
        public Context mContext;
        public boolean mInfoChanged;
        public boolean mSim0Inserted;
        public String mSim0Name;
        public boolean mSim1Inserted;
        public String mSim1Name;
        public int mSimCount;
        public boolean mStateChanged;

        public SimState(Context context) {
            this.mContext = context;
        }

        private boolean checkEquals(String string2, String string3) {
            if (string2 != null && string2.equals((Object)string3)) {
                return true;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        private String getSimDisplayName(int n, int n2) {
            String string2;
            String string3 = string2 = MSimUtils.getSimDisplayName(this.mContext, n);
            if (TextUtils.isEmpty((CharSequence)string2)) {
                n = MSimUtils.isMSim() ? (n == 1 ? 2131362236 : 2131362235) : 2131361798;
                string3 = this.mContext.getResources().getString(n);
            }
            string2 = string3;
            if (!Build.IS_CM_CUSTOMIZATION_TEST) return string2;
            return string3 + " (" + n2 + ")";
        }

        /*
         * Enabled aggressive block sorting
         */
        public void updateSimInfo(int n, int n2) {
            this.mInfoChanged = false;
            if (n == 0) {
                String string2 = this.getSimDisplayName(n, n2);
                if (!this.checkEquals(string2, this.mSim0Name)) {
                    this.mInfoChanged = true;
                }
                this.mSim0Name = string2;
                return;
            } else {
                if (n == 1) {
                    String string3 = this.getSimDisplayName(n, n2);
                    if (!this.checkEquals(string3, this.mSim1Name)) {
                        this.mInfoChanged = true;
                    }
                    this.mSim1Name = string3;
                    return;
                }
                if (n != -1) return;
                {
                    String string4 = this.getSimDisplayName(0, n2);
                    if (!this.checkEquals(string4, this.mSim0Name)) {
                        this.mInfoChanged = true;
                    }
                    this.mSim0Name = string4;
                    string4 = this.getSimDisplayName(1, n2);
                    if (!this.checkEquals(string4, this.mSim1Name)) {
                        this.mInfoChanged = true;
                    }
                    this.mSim1Name = string4;
                    return;
                }
            }
        }

        public void updateSimState() {
            this.mSimCount = 0;
            this.mStateChanged = false;
            boolean bl = MSimUtils.isSimInserted(0);
            if (this.mSim0Inserted != bl) {
                this.mStateChanged = true;
            }
            this.mSim0Inserted = bl;
            if (this.mSim0Inserted) {
                ++this.mSimCount;
            }
            if (this.mSim1Inserted != (bl = MSimUtils.isSimInserted(1))) {
                this.mStateChanged = true;
            }
            this.mSim1Inserted = bl;
            if (this.mSim1Inserted) {
                ++this.mSimCount;
            }
            Log.d((String)"ManageSimMessages", (String)("updateSimState sim count is " + this.mSimCount));
        }
    }

}

