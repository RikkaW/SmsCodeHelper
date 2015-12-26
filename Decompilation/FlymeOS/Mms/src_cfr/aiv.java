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
 */
import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public interface aiv
extends IInterface {
    public String a(String var1, String var2, String var3, byte[] var4, String var5, boolean var6, String var7);

    public void a(String var1, PendingIntent var2);

    public void a(String var1, String var2);

    public void a(String var1, String var2, String var3, PendingIntent var4);

    public void a(String var1, String var2, String var3, PendingIntent var4, String var5);

    public void a(String var1, String var2, String var3, String var4, PendingIntent var5);

    public void a(String var1, String var2, String var3, String var4, PendingIntent var5, String var6);

    public void a(String var1, String var2, String var3, String var4, String var5, String var6, PendingIntent var7);

    public void a(String var1, String var2, String var3, String var4, String var5, String var6, String var7, PendingIntent var8);

    public void a(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, PendingIntent var11);

    public byte[] a(String var1, byte[] var2, String var3, String var4, long var5);

    public String[] a(String var1, String var2, String var3, long var4);

    public void b(String var1, String var2, String var3, PendingIntent var4);

    public void b(String var1, String var2, String var3, String var4, PendingIntent var5);

    public void b(String var1, String var2, String var3, String var4, String var5, String var6, String var7, PendingIntent var8);

    public static abstract class aiv$a
    extends Binder
    implements aiv {
        public static aiv a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.interfaces.IMzSnsService");
            if (iInterface != null && iInterface instanceof aiv) {
                return (aiv)iInterface;
            }
            return new a(iBinder);
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTransact(int n2, Parcel object, Parcel parcel, int n3) {
            switch (n2) {
                default: {
                    return super.onTransact(n2, (Parcel)object, parcel, n3);
                }
                case 1598968902: {
                    parcel.writeString("com.meizu.interfaces.IMzSnsService");
                    return true;
                }
                case 1: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string2 = object.readString();
                    String string3 = object.readString();
                    String string4 = object.readString();
                    String string5 = object.readString();
                    PendingIntent pendingIntent = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string2, string3, string4, string5, pendingIntent, object.readString());
                    parcel.writeNoException();
                    return true;
                }
                case 2: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string6 = object.readString();
                    String string7 = object.readString();
                    String string8 = object.readString();
                    PendingIntent pendingIntent = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string6, string7, string8, pendingIntent, object.readString());
                    parcel.writeNoException();
                    return true;
                }
                case 3: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string9 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string9, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 4: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string10 = object.readString();
                    String string11 = object.readString();
                    String string12 = object.readString();
                    byte[] arrby = object.createByteArray();
                    String string13 = object.readString();
                    boolean bl2 = object.readInt() != 0;
                    object = this.a(string10, string11, string12, arrby, string13, bl2, object.readString());
                    parcel.writeNoException();
                    parcel.writeString((String)object);
                    return true;
                }
                case 5: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    object = this.a(object.readString(), object.createByteArray(), object.readString(), object.readString(), object.readLong());
                    parcel.writeNoException();
                    parcel.writeByteArray((byte[])object);
                    return true;
                }
                case 6: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string14 = object.readString();
                    String string15 = object.readString();
                    String string16 = object.readString();
                    String string17 = object.readString();
                    String string18 = object.readString();
                    String string19 = object.readString();
                    String string20 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string14, string15, string16, string17, string18, string19, string20, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 7: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string21 = object.readString();
                    String string22 = object.readString();
                    String string23 = object.readString();
                    String string24 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string21, string22, string23, string24, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 8: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string25 = object.readString();
                    String string26 = object.readString();
                    String string27 = object.readString();
                    String string28 = object.readString();
                    String string29 = object.readString();
                    String string30 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string25, string26, string27, string28, string29, string30, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 9: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string31 = object.readString();
                    String string32 = object.readString();
                    String string33 = object.readString();
                    String string34 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.b(string31, string32, string33, string34, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 10: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string35 = object.readString();
                    String string36 = object.readString();
                    String string37 = object.readString();
                    String string38 = object.readString();
                    String string39 = object.readString();
                    String string40 = object.readString();
                    String string41 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.b(string35, string36, string37, string38, string39, string40, string41, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 11: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string42 = object.readString();
                    String string43 = object.readString();
                    String string44 = object.readString();
                    String string45 = object.readString();
                    String string46 = object.readString();
                    String string47 = object.readString();
                    String string48 = object.readString();
                    String string49 = object.readString();
                    String string50 = object.readString();
                    String string51 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string42, string43, string44, string45, string46, string47, string48, string49, string50, string51, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 12: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string52 = object.readString();
                    String string53 = object.readString();
                    String string54 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.a(string52, string53, string54, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 13: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    String string55 = object.readString();
                    String string56 = object.readString();
                    String string57 = object.readString();
                    object = object.readInt() != 0 ? (PendingIntent)PendingIntent.CREATOR.createFromParcel((Parcel)object) : null;
                    this.b(string55, string56, string57, (PendingIntent)object);
                    parcel.writeNoException();
                    return true;
                }
                case 14: {
                    object.enforceInterface("com.meizu.interfaces.IMzSnsService");
                    object = this.a(object.readString(), object.readString(), object.readString(), object.readLong());
                    parcel.writeNoException();
                    parcel.writeStringArray((String[])object);
                    return true;
                }
                case 15: 
            }
            object.enforceInterface("com.meizu.interfaces.IMzSnsService");
            this.a(object.readString(), object.readString());
            parcel.writeNoException();
            return true;
        }

        static class a
        implements aiv {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override
            public String a(String string2, String string3, String string4, byte[] arrby, String string5, boolean bl2, String string6) {
                Parcel parcel;
                Parcel parcel2;
                int n2;
                block4 : {
                    n2 = 0;
                    parcel2 = Parcel.obtain();
                    parcel = Parcel.obtain();
                    parcel2.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel2.writeString(string2);
                    parcel2.writeString(string3);
                    parcel2.writeString(string4);
                    parcel2.writeByteArray(arrby);
                    parcel2.writeString(string5);
                    if (!bl2) break block4;
                    n2 = 1;
                }
                try {
                    parcel2.writeInt(n2);
                    parcel2.writeString(string6);
                    this.a.transact(4, parcel2, parcel, 0);
                    parcel.readException();
                    string2 = parcel.readString();
                    return string2;
                }
                finally {
                    parcel.recycle();
                    parcel2.recycle();
                }
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void a(String string2, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(3, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String string2, String string3) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    this.a.transact(15, parcel, parcel2, 0);
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
            public void a(String string2, String string3, String string4, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(12, parcel, parcel2, 0);
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
            public void a(String string2, String string3, String string4, PendingIntent pendingIntent, String string5) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    parcel.writeString(string5);
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
            public void a(String string2, String string3, String string4, String string5, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
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
            public void a(String string2, String string3, String string4, String string5, PendingIntent pendingIntent, String string6) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    parcel.writeString(string6);
                    this.a.transact(1, parcel, parcel2, 0);
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
            public void a(String string2, String string3, String string4, String string5, String string6, String string7, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
                    parcel.writeString(string6);
                    parcel.writeString(string7);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
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
            public void a(String string2, String string3, String string4, String string5, String string6, String string7, String string8, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
                    parcel.writeString(string6);
                    parcel.writeString(string7);
                    parcel.writeString(string8);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(6, parcel, parcel2, 0);
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
            public void a(String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
                    parcel.writeString(string6);
                    parcel.writeString(string7);
                    parcel.writeString(string8);
                    parcel.writeString(string9);
                    parcel.writeString(string10);
                    parcel.writeString(string11);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(11, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public byte[] a(String string2, byte[] arrby, String string3, String string4, long l2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeByteArray(arrby);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeLong(l2);
                    this.a.transact(5, parcel, parcel2, 0);
                    parcel2.readException();
                    string2 = (String)parcel2.createByteArray();
                    return string2;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public String[] a(String arrstring, String string2, String string3, long l2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString((String)arrstring);
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeLong(l2);
                    this.a.transact(14, parcel, parcel2, 0);
                    parcel2.readException();
                    arrstring = parcel2.createStringArray();
                    return arrstring;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
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
            public void b(String string2, String string3, String string4, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(13, parcel, parcel2, 0);
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
            public void b(String string2, String string3, String string4, String string5, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
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

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void b(String string2, String string3, String string4, String string5, String string6, String string7, String string8, PendingIntent pendingIntent) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.interfaces.IMzSnsService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    parcel.writeString(string4);
                    parcel.writeString(string5);
                    parcel.writeString(string6);
                    parcel.writeString(string7);
                    parcel.writeString(string8);
                    if (pendingIntent != null) {
                        parcel.writeInt(1);
                        pendingIntent.writeToParcel(parcel, 0);
                    } else {
                        parcel.writeInt(0);
                    }
                    this.a.transact(10, parcel, parcel2, 0);
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

