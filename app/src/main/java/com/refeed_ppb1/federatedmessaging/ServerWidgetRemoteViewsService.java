package com.refeed_ppb1.federatedmessaging;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class ServerWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ServerWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}