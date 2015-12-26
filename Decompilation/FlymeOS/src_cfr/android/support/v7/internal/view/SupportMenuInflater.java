/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.res.TypedArray
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.InflateException
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MenuItem$OnMenuItemClickListener
 *  android.view.SubMenu
 *  android.view.View
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Constructor
 *  java.lang.reflect.Method
 *  org.xmlpull.v1.XmlPullParser
 */
package android.support.v7.internal.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

public class SupportMenuInflater
extends MenuInflater {
    private static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class};
    private static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    private static final String LOG_TAG = "SupportMenuInflater";
    private static final int NO_ID = 0;
    private static final String XML_GROUP = "group";
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";
    private final Object[] mActionProviderConstructorArguments;
    private final Object[] mActionViewConstructorArguments;
    private Context mContext;
    private Object mRealOwner;

    public SupportMenuInflater(Context context) {
        super(context);
        this.mContext = context;
        this.mActionProviderConstructorArguments = this.mActionViewConstructorArguments = new Object[]{context};
    }

    /*
     * Enabled aggressive block sorting
     */
    private Object findRealOwner(Object object) {
        if (object instanceof Activity || !(object instanceof ContextWrapper)) {
            return object;
        }
        return this.findRealOwner((Object)((ContextWrapper)object).getBaseContext());
    }

    private Object getRealOwner() {
        if (this.mRealOwner == null) {
            this.mRealOwner = this.findRealOwner((Object)this.mContext);
        }
        return this.mRealOwner;
    }

    /*
     * Exception decompiling
     */
    private void parseMenu(XmlPullParser var1_1, AttributeSet var2_2, Menu var3_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Exception decompiling
     */
    public void inflate(int var1_1, Menu var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[CATCHBLOCK]], but top level block is 5[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    static class InflatedOnMenuItemClickListener
    implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = new Class[]{MenuItem.class};
        private Method mMethod;
        private Object mRealOwner;

        public InflatedOnMenuItemClickListener(Object object, String string2) {
            this.mRealOwner = object;
            Class class_ = object.getClass();
            try {
                this.mMethod = class_.getMethod(string2, PARAM_TYPES);
                return;
            }
            catch (Exception var1_2) {
                string2 = new InflateException("Couldn't resolve menu item onClick handler " + string2 + " in class " + class_.getName());
                string2.initCause((Throwable)var1_2);
                throw string2;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    return (Boolean)this.mMethod.invoke(this.mRealOwner, new Object[]{menuItem});
                }
                this.mMethod.invoke(this.mRealOwner, new Object[]{menuItem});
                return true;
            }
            catch (Exception var1_2) {
                throw new RuntimeException(var1_2);
            }
        }
    }

    class MenuState {
        private static final int defaultGroupId = 0;
        private static final int defaultItemCategory = 0;
        private static final int defaultItemCheckable = 0;
        private static final boolean defaultItemChecked = false;
        private static final boolean defaultItemEnabled = true;
        private static final int defaultItemId = 0;
        private static final int defaultItemOrder = 0;
        private static final boolean defaultItemVisible = true;
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        private ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private boolean itemEnabled;
        private int itemIconResId;
        private int itemId;
        private String itemListenerMethodName;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private boolean itemVisible;
        private Menu menu;

        public MenuState(Menu menu) {
            this.menu = menu;
            this.resetGroup();
        }

        static /* synthetic */ ActionProvider access$000(MenuState menuState) {
            return menuState.itemActionProvider;
        }

        private char getShortcut(String string2) {
            if (string2 == null) {
                return '\u0000';
            }
            return string2.charAt(0);
        }

        private <T> T newInstance(String string2, Class<?>[] object, Object[] arrobject) {
            try {
                object = SupportMenuInflater.this.mContext.getClassLoader().loadClass(string2).getConstructor(object).newInstance(arrobject);
            }
            catch (Exception var2_3) {
                Log.w((String)"SupportMenuInflater", (String)("Cannot instantiate class: " + string2), (Throwable)var2_3);
                return null;
            }
            return (T)object;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void setItem(MenuItem menuItem) {
            boolean bl2 = true;
            MenuItem menuItem2 = menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled);
            boolean bl3 = this.itemCheckable >= 1;
            menuItem2.setCheckable(bl3).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId).setAlphabeticShortcut(this.itemAlphabeticShortcut).setNumericShortcut(this.itemNumericShortcut);
            if (this.itemShowAsAction >= 0) {
                MenuItemCompat.setShowAsAction(menuItem, this.itemShowAsAction);
            }
            if (this.itemListenerMethodName != null) {
                if (SupportMenuInflater.this.mContext.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener)new InflatedOnMenuItemClickListener(SupportMenuInflater.this.getRealOwner(), this.itemListenerMethodName));
            }
            if (menuItem instanceof MenuItemImpl) {
                menuItem2 = (MenuItemImpl)menuItem;
            }
            if (this.itemCheckable >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl)menuItem).setExclusiveCheckable(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS)menuItem).setExclusiveCheckable(true);
                }
            }
            if (this.itemActionViewClassName != null) {
                MenuItemCompat.setActionView(menuItem, (View)this.newInstance(this.itemActionViewClassName, ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments));
            } else {
                bl2 = false;
            }
            if (this.itemActionViewLayout > 0) {
                if (!bl2) {
                    MenuItemCompat.setActionView(menuItem, this.itemActionViewLayout);
                } else {
                    Log.w((String)"SupportMenuInflater", (String)"Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            if (this.itemActionProvider != null) {
                MenuItemCompat.setActionProvider(menuItem, this.itemActionProvider);
            }
        }

        public void addItem() {
            this.itemAdded = true;
            this.setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu subMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            this.setItem(subMenu.getItem());
            return subMenu;
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        public void readGroup(AttributeSet attributeSet) {
            attributeSet = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, R.styleable.MenuGroup);
            this.groupId = attributeSet.getResourceId(R.styleable.MenuGroup_android_id, 0);
            this.groupCategory = attributeSet.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = attributeSet.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = attributeSet.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = attributeSet.getBoolean(R.styleable.MenuGroup_android_visible, true);
            this.groupEnabled = attributeSet.getBoolean(R.styleable.MenuGroup_android_enabled, true);
            attributeSet.recycle();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void readItem(AttributeSet attributeSet) {
            int n2;
            int n3 = 1;
            attributeSet = SupportMenuInflater.this.mContext.obtainStyledAttributes(attributeSet, R.styleable.MenuItem);
            this.itemId = attributeSet.getResourceId(R.styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = attributeSet.getInt(R.styleable.MenuItem_android_menuCategory, this.groupCategory) & -65536 | attributeSet.getInt(R.styleable.MenuItem_android_orderInCategory, this.groupOrder) & 65535;
            this.itemTitle = attributeSet.getText(R.styleable.MenuItem_android_title);
            this.itemTitleCondensed = attributeSet.getText(R.styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = attributeSet.getResourceId(R.styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = this.getShortcut(attributeSet.getString(R.styleable.MenuItem_android_alphabeticShortcut));
            this.itemNumericShortcut = this.getShortcut(attributeSet.getString(R.styleable.MenuItem_android_numericShortcut));
            if (attributeSet.hasValue(R.styleable.MenuItem_android_checkable)) {
                n2 = attributeSet.getBoolean(R.styleable.MenuItem_android_checkable, false) ? 1 : 0;
                this.itemCheckable = n2;
            } else {
                this.itemCheckable = this.groupCheckable;
            }
            this.itemChecked = attributeSet.getBoolean(R.styleable.MenuItem_android_checked, false);
            this.itemVisible = attributeSet.getBoolean(R.styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = attributeSet.getBoolean(R.styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = attributeSet.getInt(R.styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = attributeSet.getString(R.styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = attributeSet.getResourceId(R.styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = attributeSet.getString(R.styleable.MenuItem_actionViewClass);
            this.itemActionProviderClassName = attributeSet.getString(R.styleable.MenuItem_actionProviderClass);
            n2 = this.itemActionProviderClassName != null ? n3 : 0;
            if (n2 != 0 && this.itemActionViewLayout == 0 && this.itemActionViewClassName == null) {
                this.itemActionProvider = (ActionProvider)this.newInstance(this.itemActionProviderClassName, ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionProviderConstructorArguments);
            } else {
                if (n2 != 0) {
                    Log.w((String)"SupportMenuInflater", (String)"Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.itemActionProvider = null;
            }
            attributeSet.recycle();
            this.itemAdded = false;
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }
    }

}

