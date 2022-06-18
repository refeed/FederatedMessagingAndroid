package com.refeed_ppb1.federatedmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refeed_ppb1.federatedmessaging.models.ServerModel;

import java.util.List;

public class ServerRVAdapter extends RecyclerView.Adapter<ServerRVAdapter.ViewHolder> {
    private List<ServerModel> mServerModel;

    public ServerRVAdapter(List<ServerModel> serverModels) {
        mServerModel = serverModels;
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
        ServerModel serverModel = mServerModel.get(position);

        holder.serverNameTextView.setText(serverModel.getName());
    }

    @Override
    public int getItemCount() {
        return mServerModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView serverNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serverNameTextView = (TextView) itemView.findViewById(R.id.server_name_tv);
        }
    }
}
