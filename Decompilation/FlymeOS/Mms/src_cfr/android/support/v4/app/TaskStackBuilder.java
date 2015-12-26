/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.PendingIntent
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilderHoneycomb;
import android.support.v4.app.TaskStackBuilderJellybean;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskStackBuilder
implements Iterable<Intent> {
    private static final TaskStackBuilderImpl IMPL = Build.VERSION.SDK_INT >= 11 ? new TaskStackBuilderImplHoneycomb() : new TaskStackBuilderImplBase();
    private static final String TAG = "TaskStackBuilder";
    private final ArrayList<Intent> mIntents = new ArrayList();
    private final Context mSourceContext;

    private TaskStackBuilder(Context context) {
        this.mSourceContext = context;
    }

    public static TaskStackBuilder create(Context context) {
        return new TaskStackBuilder(context);
    }

    public static TaskStackBuilder from(Context context) {
        return TaskStackBuilder.create(context);
    }

    public TaskStackBuilder addNextIntent(Intent intent) {
        this.mIntents.add((Object)intent);
        return this;
    }

    public TaskStackBuilder addNextIntentWithParentStack(Intent intent) {
        ComponentName componentName;
        ComponentName componentName2 = componentName = intent.getComponent();
        if (componentName == null) {
            componentName2 = intent.resolveActivity(this.mSourceContext.getPackageManager());
        }
        if (componentName2 != null) {
            this.addParentStack(componentName2);
        }
        this.addNextIntent(intent);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public TaskStackBuilder addParentStack(Activity activity) {
        Intent intent = null;
        activity = !(activity instanceof SupportParentable) || (intent = ((SupportParentable)activity).getSupportParentActivityIntent()) == null ? NavUtils.getParentActivityIntent(activity) : intent;
        if (activity != null) {
            ComponentName componentName;
            intent = componentName = activity.getComponent();
            if (componentName == null) {
                intent = activity.resolveActivity(this.mSourceContext.getPackageManager());
            }
            this.addParentStack((ComponentName)intent);
            this.addNextIntent((Intent)activity);
        }
        return this;
    }

    public TaskStackBuilder addParentStack(ComponentName componentName) {
        int n2 = this.mIntents.size();
        componentName = NavUtils.getParentActivityIntent(this.mSourceContext, componentName);
        while (componentName != null) {
            try {
                this.mIntents.add(n2, (Object)componentName);
                componentName = NavUtils.getParentActivityIntent(this.mSourceContext, componentName.getComponent());
                continue;
            }
            catch (PackageManager.NameNotFoundException var1_2) {
                Log.e((String)"TaskStackBuilder", (String)"Bad ComponentName while traversing activity parent metadata");
                throw new IllegalArgumentException((Throwable)var1_2);
            }
        }
        return this;
    }

    public TaskStackBuilder addParentStack(Class<?> class_) {
        return this.addParentStack(new ComponentName(this.mSourceContext, class_));
    }

    public Intent editIntentAt(int n2) {
        return (Intent)this.mIntents.get(n2);
    }

    public Intent getIntent(int n2) {
        return this.editIntentAt(n2);
    }

    public int getIntentCount() {
        return this.mIntents.size();
    }

    public Intent[] getIntents() {
        Intent[] arrintent = new Intent[this.mIntents.size()];
        if (arrintent.length == 0) {
            return arrintent;
        }
        arrintent[0] = new Intent((Intent)this.mIntents.get(0)).addFlags(268484608);
        for (int i2 = 1; i2 < arrintent.length; ++i2) {
            arrintent[i2] = new Intent((Intent)this.mIntents.get(i2));
        }
        return arrintent;
    }

    public PendingIntent getPendingIntent(int n2, int n3) {
        return this.getPendingIntent(n2, n3, null);
    }

    public PendingIntent getPendingIntent(int n2, int n3, Bundle bundle) {
        if (this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        }
        Intent[] arrintent = (Intent[])this.mIntents.toArray((Object[])new Intent[this.mIntents.size()]);
        arrintent[0] = new Intent(arrintent[0]).addFlags(268484608);
        return IMPL.getPendingIntent(this.mSourceContext, arrintent, n2, n3, bundle);
    }

    @Override
    public Iterator<Intent> iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        this.startActivities(null);
    }

    public void startActivities(Bundle bundle) {
        if (this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] arrintent = (Intent[])this.mIntents.toArray((Object[])new Intent[this.mIntents.size()]);
        arrintent[0] = new Intent(arrintent[0]).addFlags(268484608);
        if (!ContextCompat.startActivities(this.mSourceContext, arrintent, bundle)) {
            bundle = new Intent(arrintent[arrintent.length - 1]);
            bundle.addFlags(268435456);
            this.mSourceContext.startActivity((Intent)bundle);
        }
    }

    public static interface SupportParentable {
        public Intent getSupportParentActivityIntent();
    }

    static interface TaskStackBuilderImpl {
        public PendingIntent getPendingIntent(Context var1, Intent[] var2, int var3, int var4, Bundle var5);
    }

    static class TaskStackBuilderImplBase
    implements TaskStackBuilderImpl {
        TaskStackBuilderImplBase() {
        }

        @Override
        public PendingIntent getPendingIntent(Context context, Intent[] intent, int n2, int n3, Bundle bundle) {
            intent = new Intent(intent[intent.length - 1]);
            intent.addFlags(268435456);
            return PendingIntent.getActivity((Context)context, (int)n2, (Intent)intent, (int)n3);
        }
    }

    static class TaskStackBuilderImplHoneycomb
    implements TaskStackBuilderImpl {
        TaskStackBuilderImplHoneycomb() {
        }

        @Override
        public PendingIntent getPendingIntent(Context context, Intent[] arrintent, int n2, int n3, Bundle bundle) {
            arrintent[0] = new Intent(arrintent[0]).addFlags(268484608);
            return TaskStackBuilderHoneycomb.getActivitiesPendingIntent(context, n2, arrintent, n3);
        }
    }

    static class TaskStackBuilderImplJellybean
    implements TaskStackBuilderImpl {
        TaskStackBuilderImplJellybean() {
        }

        @Override
        public PendingIntent getPendingIntent(Context context, Intent[] arrintent, int n2, int n3, Bundle bundle) {
            arrintent[0] = new Intent(arrintent[0]).addFlags(268484608);
            return TaskStackBuilderJellybean.getActivitiesPendingIntent(context, n2, arrintent, n3, bundle);
        }
    }

}

