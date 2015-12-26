.class public Lcom/android/mms/service/exception/MmsHttpException;
.super Ljava/lang/Exception;
.source "MmsHttpException.java"


# instance fields
.field private final mStatusCode:I


# direct methods
.method public constructor <init>(ILjava/lang/String;)V
    .locals 0
    .param p1, "statusCode"    # I
    .param p2, "message"    # Ljava/lang/String;

    .prologue
    .line 33
    invoke-direct {p0, p2}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    .line 34
    iput p1, p0, Lcom/android/mms/service/exception/MmsHttpException;->mStatusCode:I

    .line 35
    return-void
.end method

.method public constructor <init>(ILjava/lang/String;Ljava/lang/Throwable;)V
    .locals 0
    .param p1, "statusCode"    # I
    .param p2, "message"    # Ljava/lang/String;
    .param p3, "cause"    # Ljava/lang/Throwable;

    .prologue
    .line 43
    invoke-direct {p0, p2, p3}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 44
    iput p1, p0, Lcom/android/mms/service/exception/MmsHttpException;->mStatusCode:I

    .line 45
    return-void
.end method

.method public constructor <init>(ILjava/lang/Throwable;)V
    .locals 0
    .param p1, "statusCode"    # I
    .param p2, "cause"    # Ljava/lang/Throwable;

    .prologue
    .line 38
    invoke-direct {p0, p2}, Ljava/lang/Exception;-><init>(Ljava/lang/Throwable;)V

    .line 39
    iput p1, p0, Lcom/android/mms/service/exception/MmsHttpException;->mStatusCode:I

    .line 40
    return-void
.end method


# virtual methods
.method public getStatusCode()I
    .locals 1

    .prologue
    .line 48
    iget v0, p0, Lcom/android/mms/service/exception/MmsHttpException;->mStatusCode:I

    return v0
.end method
