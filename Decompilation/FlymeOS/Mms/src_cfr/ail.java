/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.text.Editable
 *  android.text.Spannable
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.view.View
 *  android.view.inputmethod.BaseInputConnection
 *  android.widget.ListAdapter
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.res.Resources;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.ListAdapter;
import com.meizu.commonwidget.ParcelableImageSpan;
import com.meizu.commonwidget.RecipientEdit;

public class ail
implements TextWatcher {
    final /* synthetic */ RecipientEdit a;
    private int b;

    public ail(RecipientEdit recipientEdit) {
        this.a = recipientEdit;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void afterTextChanged(Editable object) {
        CharSequence charSequence;
        int n2;
        if (((ParcelableImageSpan[])object.getSpans(0, object.length(), (Class)ParcelableImageSpan.class)).length > 0) return;
        if (RecipientEdit.f(this.a)) {
            n2 = RecipientEdit.a(this.a).getImeOptions() & -256;
            if (TextUtils.isEmpty((CharSequence)object) && (RecipientEdit.a(this.a).getImeOptions() & 255) == 1) {
                if (this.a.focusSearch(130) != null) {
                    RecipientEdit.a(this.a).setImeOptions(n2 | 5);
                    RecipientEdit.a(this.a).setImeActionLabel(null, 5);
                } else {
                    RecipientEdit.a(this.a).setImeOptions(n2 | 6);
                    RecipientEdit.a(this.a).setImeActionLabel(null, 6);
                }
            } else if ((RecipientEdit.a(this.a).getImeOptions() & 255) != 1 && !TextUtils.isEmpty((CharSequence)object)) {
                RecipientEdit.a(this.a).setImeOptions(n2 | 1);
                RecipientEdit.a(this.a).setImeActionLabel((CharSequence)this.a.getResources().getString(aih.f.mw_recipient_edit_imeActionLabel), 1);
            }
        }
        if (this.a.d != null) {
            this.a.d.a();
        }
        n2 = object.length();
        if (this.b == 1 && object.charAt(n2 - 1) == ' ' && n2 > 1) {
            char c2 = object.charAt(n2 - 2);
            if (c2 == ',' || c2 == ';') {
                this.a.a((CharSequence)object.subSequence(0, n2 - 2).toString().trim());
                RecipientEdit.a(this.a).setText((CharSequence)"");
                this.a.b(this.a.hasFocus());
                return;
            }
            object = object.subSequence(0, n2 - 1).toString().trim();
            if (!((aii)RecipientEdit.a(this.a).getAdapter()).d((String)object)) return;
            {
                this.a.a((CharSequence)object);
                RecipientEdit.a(this.a).setText((CharSequence)"");
                this.a.b(this.a.hasFocus());
                return;
            }
        }
        if (this.b <= 1 || BaseInputConnection.getComposingSpanStart((Spannable)object) >= 0 || TextUtils.equals((CharSequence)object, (CharSequence)(charSequence = RecipientEdit.a(this.a, (CharSequence)object)))) {
            return;
        }
        RecipientEdit.a(this.a).setText(charSequence);
        RecipientEdit.a(this.a).setSelection(charSequence.length());
        this.a.b(this.a.hasFocus());
    }

    public void beforeTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
        if (RecipientEdit.e(this.a) != null) {
            RecipientEdit.e(this.a).setSelected(false);
            RecipientEdit.a(this.a, RecipientEdit.e(this.a));
            RecipientEdit.b(this.a, null);
            RecipientEdit.a(this.a).setCursorVisible(true);
        }
        if (RecipientEdit.d(this.a) != null) {
            RecipientEdit.d(this.a).a(charSequence.toString(), n2, n3, n4);
        }
    }

    public void onTextChanged(CharSequence charSequence, int n2, int n3, int n4) {
        this.b = n4;
        if (RecipientEdit.c(this.a) != null) {
            RecipientEdit.c(this.a).a(charSequence, n2, n3, n4);
        }
        if (RecipientEdit.d(this.a) != null) {
            RecipientEdit.d(this.a).b(charSequence.toString(), n2, n3, n4);
        }
    }
}

