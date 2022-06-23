package com.refeed_ppb1.federatedmessaging.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.refeed_ppb1.federatedmessaging.R;
import com.refeed_ppb1.federatedmessaging.models.DBHelper;
import com.refeed_ppb1.federatedmessaging.models.ServerModel;

import java.util.ArrayList;
import java.util.List;

public class ServerWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<ServerModel> serverModels = new ArrayList<>();

    public ServerWidgetRemoteViewsFactory(Context applicationContext, Intent intent) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        DBHelper dbHelper = new DBHelper(mContext.getApplicationContext());
        serverModels = dbHelper.getAllServers();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return serverModels.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.item_server);
        rv.setTextViewText(R.id.server_name_tv, serverModels.get(i).getName());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("server_name", serverModels.get(i).getName());
        fillInIntent.putExtra("server_address", serverModels.get(i).getAddress());
        rv.setOnClickFillInIntent(R.id.server_name_tv, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
