/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextPaint
 *  android.text.style.ClickableSpan
 *  android.view.View
 */
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.ted.android.data.BubbleEntity;

class abx
extends ClickableSpan {
    final /* synthetic */ BubbleEntity a;
    final /* synthetic */ abu b;

    abx(abu abu2, BubbleEntity bubbleEntity) {
        this.b = abu2;
        this.a = bubbleEntity;
    }

    public void onClick(View view) {
        abu.a(this.b, this.a);
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(textPaint.linkColor);
        textPaint.setUnderlineText(false);
    }
}

