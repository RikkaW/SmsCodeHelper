/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  java.lang.Object
 *  java.lang.String
 */
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.meizu.statsapp.UsageStatsProxy;

public interface ajj
extends IInterface {
    public void a(UsageStatsProxy.Event var1);

    public void a(String var1, boolean var2, String var3, long var4);

    public void a(boolean var1);

    public void b(UsageStatsProxy.Event var1);

    public static abstract class ajj$a
    extends Binder
    implements ajj {
        public ajj$a() {
            this.attachInterface((IInterface)this, "com.meizu.stats.IUsageStatsManager");
        }

        public static ajj a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.stats.IUsageStatsManager");
            if (iInterface != null && iInterface instanceof ajj) {
                return (ajj)iInterface;
            }
            return new a(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        /*
         * Enabled aggressive block sorting
         */
        protected boolean onTransact(int n2, Parcel parcel, Parcel parcel2, int n3) {
            boolean bl2 = false;
            boolean bl3 = false;
            switch (n2) {
                default: {
                    return super.onTransact(n2, parcel, parcel2, n3);
                }
                case 1598968902: {
                    parcel2.writeString("com.meizu.stats.IUsageStatsManager");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.meizu.stats.IUsageStatsManager");
                    String string2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bl3 = true;
                    }
                    this.a(string2, bl3, parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.meizu.stats.IUsageStatsManager");
                    this.a(new UsageStatsProxy.Event(parcel));
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.meizu.stats.IUsageStatsManager");
                    this.b(new UsageStatsProxy.Event(parcel));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: 
            }
            parcel.enforceInterface("com.meizu.stats.IUsageStatsManager");
            bl3 = parcel.readInt() == 0 ? bl2 : true;
            this.a(bl3);
            parcel2.writeNoException();
            return true;
        }

        static class a
        implements ajj {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override
            public void a(UsageStatsProxy.Event event) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.stats.IUsageStatsManager");
                    event.writeToParcel(parcel, 0);
                    this.a.transact(2, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void a(String string2, boolean bl2, String string3, long l2) {
                int n2 = 1;
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.stats.IUsageStatsManager");
                    parcel.writeString(string2);
                    if (!bl2) {
                        n2 = 0;
                    }
                    parcel.writeInt(n2);
                    parcel.writeString(string3);
                    parcel.writeLong(l2);
                    this.a.transact(1, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(boolean bl2) {
                int n2;
                Parcel parcel;
                Parcel parcel2;
                block4 : {
                    n2 = 0;
                    parcel2 = Parcel.obtain();
                    parcel = Parcel.obtain();
                    parcel2.writeInterfaceToken("com.meizu.stats.IUsageStatsManager");
                    if (!bl2) break block4;
                    n2 = 1;
                }
                try {
                    parcel2.writeInt(n2);
                    this.a.transact(4, parcel2, parcel, 0);
                    parcel.readException();
                    return;
                }
                finally {
                    parcel.recycle();
                    parcel2.recycle();
                }
            }

            public IBinder asBinder() {
                return this.a;
            }

            @Override
            public void b(UsageStatsProxy.Event event) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.stats.IUsageStatsManager");
                    event.writeToParcel(parcel, 0);
                    this.a.transact(3, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }
        }

    }

}

