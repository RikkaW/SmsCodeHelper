.class public interface abstract Lcom/android/mms/service/MmsRequest$RequestManager;
.super Ljava/lang/Object;
.source "MmsRequest.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/MmsRequest;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "RequestManager"
.end annotation


# virtual methods
.method public abstract addSimRequest(Lcom/android/mms/service/MmsRequest;)V
.end method

.method public abstract getAutoPersistingPref()Z
.end method

.method public abstract readPduFromContentUri(Landroid/net/Uri;I)[B
.end method

.method public abstract writePduToContentUri(Landroid/net/Uri;[B)Z
.end method
