.class public Lcom/android/mms/service/MmsConfigXmlProcessor;
.super Ljava/lang/Object;
.source "MmsConfigXmlProcessor.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;
    }
.end annotation


# instance fields
.field private final mInputParser:Lorg/xmlpull/v1/XmlPullParser;

.field private final mLogStringBuilder:Ljava/lang/StringBuilder;

.field private mMmsConfigHandler:Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;


# direct methods
.method private constructor <init>(Lorg/xmlpull/v1/XmlPullParser;)V
    .locals 1
    .param p1, "parser"    # Lorg/xmlpull/v1/XmlPullParser;

    .prologue
    .line 46
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 42
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iput-object v0, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mLogStringBuilder:Ljava/lang/StringBuilder;

    .line 47
    iput-object p1, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    .line 48
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mMmsConfigHandler:Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;

    .line 49
    return-void
.end method

.method private advanceToNextEvent(I)I
    .locals 2
    .param p1, "eventType"    # I
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lorg/xmlpull/v1/XmlPullParserException;,
            Ljava/io/IOException;
        }
    .end annotation

    .prologue
    .line 70
    :cond_0
    iget-object v1, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v1}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v0

    .line 71
    .local v0, "nextEvent":I
    if-eq v0, p1, :cond_1

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 73
    :cond_1
    return v0
.end method

.method public static get(Lorg/xmlpull/v1/XmlPullParser;)Lcom/android/mms/service/MmsConfigXmlProcessor;
    .locals 1
    .param p0, "parser"    # Lorg/xmlpull/v1/XmlPullParser;

    .prologue
    .line 52
    new-instance v0, Lcom/android/mms/service/MmsConfigXmlProcessor;

    invoke-direct {v0, p0}, Lcom/android/mms/service/MmsConfigXmlProcessor;-><init>(Lorg/xmlpull/v1/XmlPullParser;)V

    return-object v0
.end method

.method private processMmsConfig()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lorg/xmlpull/v1/XmlPullParserException;
        }
    .end annotation

    .prologue
    .line 154
    :cond_0
    :goto_0
    iget-object v1, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v1}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v0

    .local v0, "nextEvent":I
    const/4 v1, 0x4

    if-eq v0, v1, :cond_0

    .line 155
    const/4 v1, 0x2

    if-ne v0, v1, :cond_1

    .line 157
    invoke-direct {p0}, Lcom/android/mms/service/MmsConfigXmlProcessor;->processMmsConfigKeyValue()V

    goto :goto_0

    .line 158
    :cond_1
    const/4 v1, 0x3

    if-ne v0, v1, :cond_2

    .line 165
    return-void

    .line 161
    :cond_2
    new-instance v1, Lorg/xmlpull/v1/XmlPullParserException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "MmsConfig: expecting start or end tag @"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-direct {p0}, Lcom/android/mms/service/MmsConfigXmlProcessor;->xmlParserDebugContext()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2}, Lorg/xmlpull/v1/XmlPullParserException;-><init>(Ljava/lang/String;)V

    throw v1
.end method

.method private processMmsConfigKeyValue()V
    .locals 7
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;,
            Lorg/xmlpull/v1/XmlPullParserException;
        }
    .end annotation

    .prologue
    .line 174
    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    const/4 v5, 0x0

    const-string v6, "name"

    invoke-interface {v4, v5, v6}, Lorg/xmlpull/v1/XmlPullParser;->getAttributeValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 177
    .local v0, "key":Ljava/lang/String;
    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v4}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v2

    .line 178
    .local v2, "type":Ljava/lang/String;
    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v4}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v1

    .line 179
    .local v1, "nextEvent":I
    const/4 v3, 0x0

    .line 180
    .local v3, "value":Ljava/lang/String;
    const/4 v4, 0x4

    if-ne v1, v4, :cond_0

    .line 181
    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v4}, Lorg/xmlpull/v1/XmlPullParser;->getText()Ljava/lang/String;

    move-result-object v3

    .line 182
    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v4}, Lorg/xmlpull/v1/XmlPullParser;->next()I

    move-result v1

    .line 184
    :cond_0
    const/4 v4, 0x3

    if-eq v1, v4, :cond_1

    .line 185
    new-instance v4, Lorg/xmlpull/v1/XmlPullParserException;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "MmsConfigXmlProcessor: expecting end tag @"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-direct {p0}, Lcom/android/mms/service/MmsConfigXmlProcessor;->xmlParserDebugContext()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v4, v5}, Lorg/xmlpull/v1/XmlPullParserException;-><init>(Ljava/lang/String;)V

    throw v4

    .line 188
    :cond_1
    invoke-static {v0, v2}, Lcom/android/mms/service/MmsConfig;->isValidKey(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 190
    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mMmsConfigHandler:Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;

    if-eqz v4, :cond_2

    .line 191
    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mMmsConfigHandler:Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;

    invoke-interface {v4, v0, v3, v2}, Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;->process(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 196
    :cond_2
    :goto_0
    return-void

    .line 194
    :cond_3
    const-string v4, "MmsService"

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "MmsConfig: invalid key="

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, " or type="

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v4, v5}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method

.method private xmlParserDebugContext()Ljava/lang/String;
    .locals 6

    .prologue
    .line 117
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mLogStringBuilder:Ljava/lang/StringBuilder;

    const/4 v4, 0x0

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->setLength(I)V

    .line 118
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    if-eqz v3, :cond_3

    .line 120
    :try_start_0
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v3}, Lorg/xmlpull/v1/XmlPullParser;->getEventType()I

    move-result v1

    .line 121
    .local v1, "eventType":I
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mLogStringBuilder:Ljava/lang/StringBuilder;

    invoke-static {v1}, Lcom/android/mms/service/MmsConfigXmlProcessor;->xmlParserEventString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 122
    const/4 v3, 0x2

    if-eq v1, v3, :cond_0

    const/4 v3, 0x3

    if-eq v1, v3, :cond_0

    const/4 v3, 0x4

    if-ne v1, v3, :cond_2

    .line 125
    :cond_0
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mLogStringBuilder:Ljava/lang/StringBuilder;

    const/16 v4, 0x3c

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v4}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 126
    const/4 v2, 0x0

    .local v2, "i":I
    :goto_0
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v3}, Lorg/xmlpull/v1/XmlPullParser;->getAttributeCount()I

    move-result v3

    if-ge v2, v3, :cond_1

    .line 127
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mLogStringBuilder:Ljava/lang/StringBuilder;

    const/16 v4, 0x20

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v4, v2}, Lorg/xmlpull/v1/XmlPullParser;->getAttributeName(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const/16 v4, 0x3d

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v4, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v4, v2}, Lorg/xmlpull/v1/XmlPullParser;->getAttributeValue(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 126
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 132
    :cond_1
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mLogStringBuilder:Ljava/lang/StringBuilder;

    const-string v4, "/>"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 134
    .end local v2    # "i":I
    :cond_2
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mLogStringBuilder:Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    :try_end_0
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v3

    .line 139
    .end local v1    # "eventType":I
    :goto_1
    return-object v3

    .line 135
    :catch_0
    move-exception v0

    .line 136
    .local v0, "e":Lorg/xmlpull/v1/XmlPullParserException;
    const-string v3, "MmsService"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "xmlParserDebugContext: "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 139
    .end local v0    # "e":Lorg/xmlpull/v1/XmlPullParserException;
    :cond_3
    const-string v3, "Unknown"

    goto :goto_1
.end method

.method private static xmlParserEventString(I)Ljava/lang/String;
    .locals 1
    .param p0, "event"    # I

    .prologue
    .line 103
    packed-switch p0, :pswitch_data_0

    .line 110
    invoke-static {p0}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v0

    :goto_0
    return-object v0

    .line 104
    :pswitch_0
    const-string v0, "START_DOCUMENT"

    goto :goto_0

    .line 105
    :pswitch_1
    const-string v0, "END_DOCUMENT"

    goto :goto_0

    .line 106
    :pswitch_2
    const-string v0, "START_TAG"

    goto :goto_0

    .line 107
    :pswitch_3
    const-string v0, "END_TAG"

    goto :goto_0

    .line 108
    :pswitch_4
    const-string v0, "TEXT"

    goto :goto_0

    .line 103
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
        :pswitch_2
        :pswitch_3
        :pswitch_4
    .end packed-switch
.end method


# virtual methods
.method public process()V
    .locals 6

    .prologue
    const/4 v4, 0x2

    .line 81
    const/4 v3, 0x2

    :try_start_0
    invoke-direct {p0, v3}, Lcom/android/mms/service/MmsConfigXmlProcessor;->advanceToNextEvent(I)I

    move-result v3

    if-eq v3, v4, :cond_1

    .line 82
    new-instance v3, Lorg/xmlpull/v1/XmlPullParserException;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "MmsConfigXmlProcessor: expecting start tag @"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-direct {p0}, Lcom/android/mms/service/MmsConfigXmlProcessor;->xmlParserDebugContext()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4}, Lorg/xmlpull/v1/XmlPullParserException;-><init>(Ljava/lang/String;)V

    throw v3
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_0 .. :try_end_0} :catch_1

    .line 95
    :catch_0
    move-exception v0

    .line 96
    .local v0, "e":Ljava/io/IOException;
    const-string v3, "MmsService"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "MmsConfigXmlProcessor: I/O failure "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    .line 100
    .end local v0    # "e":Ljava/io/IOException;
    :cond_0
    :goto_0
    return-void

    .line 87
    :cond_1
    :try_start_1
    new-instance v2, Landroid/content/ContentValues;

    invoke-direct {v2}, Landroid/content/ContentValues;-><init>()V

    .line 88
    .local v2, "values":Landroid/content/ContentValues;
    iget-object v3, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mInputParser:Lorg/xmlpull/v1/XmlPullParser;

    invoke-interface {v3}, Lorg/xmlpull/v1/XmlPullParser;->getName()Ljava/lang/String;

    move-result-object v1

    .line 91
    .local v1, "tagName":Ljava/lang/String;
    const-string v3, "mms_config"

    invoke-virtual {v3, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 93
    invoke-direct {p0}, Lcom/android/mms/service/MmsConfigXmlProcessor;->processMmsConfig()V
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catch Lorg/xmlpull/v1/XmlPullParserException; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_0

    .line 97
    .end local v1    # "tagName":Ljava/lang/String;
    .end local v2    # "values":Landroid/content/ContentValues;
    :catch_1
    move-exception v0

    .line 98
    .local v0, "e":Lorg/xmlpull/v1/XmlPullParserException;
    const-string v3, "MmsService"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "MmsConfigXmlProcessor: parsing failure "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method public setMmsConfigHandler(Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;)Lcom/android/mms/service/MmsConfigXmlProcessor;
    .locals 0
    .param p1, "handler"    # Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;

    .prologue
    .line 56
    iput-object p1, p0, Lcom/android/mms/service/MmsConfigXmlProcessor;->mMmsConfigHandler:Lcom/android/mms/service/MmsConfigXmlProcessor$MmsConfigHandler;

    .line 57
    return-object p0
.end method
