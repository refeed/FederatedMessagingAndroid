package com.refeed_ppb1.federatedmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServerRVAdapter extends RecyclerView.Adapter<ServerRVAdapter.ViewHolder> {
    private List<Server> mServer;

    public ServerRVAdapter(List<Server> servers) {
        mServer = servers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View serverItemView = inflater.inflate(R.layout.item_server, parent, false);

        ViewHolder viewHolder = new ViewHolder(serverItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Server server = mServer.get(position);

        holder.serverNameTextView.setText(server.getName());
    }

    @Override
    public int getItemCount() {
        return mServer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView serverNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serverNameTextView = (TextView) itemView.findViewById(R.id.server_name_tv);
        }
    }
}
