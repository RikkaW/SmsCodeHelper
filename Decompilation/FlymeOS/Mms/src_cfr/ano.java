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
 *  java.lang.Throwable
 */
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public interface ano
extends IInterface {
    public void a(IBinder var1);

    public void a(String var1);

    public void a(String var1, int var2);

    public void a(String var1, IBinder var2);

    public void a(String var1, ann var2);

    public void a(String var1, anp var2);

    public void a(String var1, String var2);

    public void a(String var1, String[] var2);

    public boolean a();

    public void b();

    public void b(String var1);

    public void b(String var1, String var2);

    public void c(String var1, String var2);

    public boolean c();

    public void d();

    public static abstract class ano$a
    extends Binder
    implements ano {
        public static ano b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
            if (iInterface != null && iInterface instanceof ano) {
                return (ano)iInterface;
            }
            return new a(iBinder);
        }

        public boolean onTransact(int n2, Parcel parcel, Parcel parcel2, int n3) {
            int n4 = 0;
            int n5 = 0;
            switch (n2) {
                default: {
                    return super.onTransact(n2, parcel, parcel2, n3);
                }
                case 1598968902: {
                    parcel2.writeString("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readString(), ann.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                }
                case 5: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                }
                case 6: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    boolean bl2 = this.a();
                    parcel2.writeNoException();
                    n2 = n5;
                    if (bl2) {
                        n2 = 1;
                    }
                    parcel2.writeInt(n2);
                    return true;
                }
                case 7: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readString(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                }
                case 8: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 9: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.b();
                    parcel2.writeNoException();
                    return true;
                }
                case 10: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a(parcel.readString(), anp.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                }
                case 11: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.b(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 12: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.b(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 13: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    boolean bl3 = this.c();
                    parcel2.writeNoException();
                    n2 = n4;
                    if (bl3) {
                        n2 = 1;
                    }
                    parcel2.writeInt(n2);
                    return true;
                }
                case 14: {
                    parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.d();
                    parcel2.writeNoException();
                    return true;
                }
                case 15: 
            }
            parcel.enforceInterface("com.meizu.voiceassistant.support.IVoiceAssistantService");
            this.c(parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        static class a
        implements ano {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override
            public void a(IBinder iBinder) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeStrongBinder(iBinder);
                    this.a.transact(5, parcel, parcel2, 0);
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
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
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
            public void a(String string2, int n2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
                    parcel.writeInt(n2);
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
            public void a(String string2, IBinder iBinder) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
                    parcel.writeStrongBinder(iBinder);
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
            public void a(String string2, ann ann2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
                    string2 = ann2 != null ? ann2.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)string2);
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
            public void a(String string2, anp anp2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
                    string2 = anp2 != null ? anp2.asBinder() : null;
                    parcel.writeStrongBinder((IBinder)string2);
                    this.a.transact(10, parcel, parcel2, 0);
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
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    this.a.transact(8, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void a(String string2, String[] arrstring) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
                    parcel.writeStringArray(arrstring);
                    this.a.transact(7, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public boolean a() {
                Parcel parcel;
                boolean bl2;
                Parcel parcel2;
                block2 : {
                    bl2 = false;
                    parcel = Parcel.obtain();
                    parcel2 = Parcel.obtain();
                    try {
                        parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                        this.a.transact(6, parcel, parcel2, 0);
                        parcel2.readException();
                        int n2 = parcel2.readInt();
                        if (n2 == 0) break block2;
                        bl2 = true;
                    }
                    catch (Throwable var5_5) {
                        parcel2.recycle();
                        parcel.recycle();
                        throw var5_5;
                    }
                }
                parcel2.recycle();
                parcel.recycle();
                return bl2;
            }

            public IBinder asBinder() {
                return this.a;
            }

            @Override
            public void b() {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
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
            public void b(String string2) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
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
            public void b(String string2, String string3) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    parcel.writeString(string2);
                    parcel.writeString(string3);
                    this.a.transact(12, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void c(String string2, String string3) {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
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

            @Override
            public boolean c() {
                Parcel parcel;
                boolean bl2;
                Parcel parcel2;
                block2 : {
                    bl2 = false;
                    parcel = Parcel.obtain();
                    parcel2 = Parcel.obtain();
                    try {
                        parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                        this.a.transact(13, parcel, parcel2, 0);
                        parcel2.readException();
                        int n2 = parcel2.readInt();
                        if (n2 == 0) break block2;
                        bl2 = true;
                    }
                    catch (Throwable var5_5) {
                        parcel2.recycle();
                        parcel.recycle();
                        throw var5_5;
                    }
                }
                parcel2.recycle();
                parcel.recycle();
                return bl2;
            }

            @Override
            public void d() {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.meizu.voiceassistant.support.IVoiceAssistantService");
                    this.a.transact(14, parcel, parcel2, 0);
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

