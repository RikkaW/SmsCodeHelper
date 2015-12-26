/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public interface ais
extends IInterface {
    public void a(ait var1);

    public void a(String var1);

    public void a(String var1, aiu var2);

    public void a(String var1, String var2, PendingIntent var3, String var4, boolean var5);

    public void a(String var1, String var2, String var3, String var4, long var5);

    public void a(String[] var1, PendingIntent var2);

    public void a(String[] var1, String var2, byte[] var3, String var4, boolean var5, String var6);

    public void b(ait var1);

    public void b(String var1, aiu var2);

    public static abstract class ais$a
    extends Binder
    implements ais {
        public static ais a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
            if (iInterface != null && iInterface instanceof ais) {
                return (ais)iInterface;
            }
            return new a(iBinder);
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTransact(int n2, Parcel parcel, Parcel parcel2, int n3) {
            PendingIntent pendingIntent = null;
            boolean bl2 = false;
            boolean bl3 = false;
            switch (n2) {
                default: {
                    return super.onTransact(n2, parcel, parcel2, n3);
                }
                case 1598968902: {
                    parcel2.writeString("com.meizu.flymesms.interfaces.IFlymeSMS");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    String string2 = parcel.readString();
                    String string3 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                    }
                    String string4 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bl3 = true;
                    }
                    this.a(string2, string3, pendingIntent, string4, bl3);
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    this.a(ait.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    this.b(ait.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    this.a(parcel.readString(), aiu.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    this.b(parcel.readString(), aiu.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    pendingIntent = parcel.createStringArray();
                    String string5 = parcel.readString();
                    byte[] arrby = parcel.createByteArray();
                    String string6 = parcel.readString();
                    bl3 = bl2;
                    if (parcel.readInt() != 0) {
                        bl3 = true;
                    }
                    this.a((String[])pendingIntent, string5, arrby, string6, bl3, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    this.a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
                    this.a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 9: 
            }
            parcel.enforceInterface("com.meizu.flymesms.interfaces.IFlymeSMS");
            pendingIntent = parcel.createStringArray();
            parcel = parcel.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel) : null;
            this.a((String[])pendingIntent, (PendingIntent)parcel);
            parcel2.writeNoException();
            return true;
        }

        static class a
        implements ais {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void a(ait ait2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    ait2 = ait2 != null ? ait2.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)ait2);
                    this.a.transact(2, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String string2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    parcel.writeString(string2);
                    this.a.transact(8, parcel, parcel2, 0);
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
            public void a(String string2, aiu aiu2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    parcel.writeString(string2);
                    string2 = aiu2 != null ? aiu2.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)string2);
                    this.a.transact(4, parcel, parcel2, 0);
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
            public void a(String string2, String string3, PendingIntent pendingIntent, String string4, boolean bl2) {
                int n2;
                Parcel parcel;
                Parcel parcel2;
                block6 : {
                    block5 : {
                        n2 = 1;
                        parcel2 = Parcel.obtain();
                        parcel = Parcel.obtain();
                        try {
                            parcel2.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                            parcel2.writeString(string2);
                            parcel2.writeString(string3);
                            if (pendingIntent != null) {
                                parcel2.writeInt(1);
                                pendingIntent.writeToParcel(parcel2, 0);
                            } else {
                                parcel2.writeInt(0);
                            }
                            parcel2.writeString(string4);
                            if (!bl2) break block5;
                            break block6;
                        }
                        catch (Throwable var1_2) {
                            parcel.recycle();
                            parcel2.recycle();
                            throw var1_2;
                        }
                    }
                    n2 = 0;
                }
                parcel2.writeInt(n2);
                this.a.transact(1, parcel2, parcel, 0);
                parcel.readException();
                parcel.recycle();
                parcel2.recycle();
            }

            @Override
            public void a(String string2, String string3, String string4, String string5, long l2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
                    parcel.writeLong(l2);
                    this.a.transact(7, parcel, parcel2, 0);
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
            public void a(String[] arrstring, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    parcel.writeStringArray(arrstring);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(9, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String[] arrstring, String string2, byte[] arrby, String string3, boolean bl2, String string4) {
                Parcel parcel;
                Parcel parcel2;
                int n2;
                block4 : {
                    n2 = 0;
                    parcel2 = Parcel.obtain();
                    parcel = Parcel.obtain();
                    parcel2.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    parcel2.writeStringArray(arrstring);
                    parcel2.writeString(string2);
                    parcel2.writeByteArray(arrby);
                    parcel2.writeString(string3);
                    if (!bl2) break block4;
                    n2 = 1;
                }
                try {
                    parcel2.writeInt(n2);
                    parcel2.writeString(string4);
                    this.a.transact(6, parcel2, parcel, 0);
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

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void b(ait ait2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    ait2 = ait2 != null ? ait2.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)ait2);
                    this.a.transact(3, parcel, parcel2, 0);
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
            public void b(String string2, aiu aiu2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.flymesms.interfaces.IFlymeSMS");
                    parcel.writeString(string2);
                    string2 = aiu2 != null ? aiu2.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)string2);
                    this.a.transact(5, parcel, parcel2, 0);
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

