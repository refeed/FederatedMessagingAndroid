package com.refeed_ppb1.federatedmessaging;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refeed_ppb1.federatedmessaging.models.ServerModel;

import java.util.List;

public class ServerRVAdapter extends RecyclerView.Adapter<ServerRVAdapter.ItemServerViewHolder> {
    private List<ServerModel> mServerModel;

    public ServerRVAdapter(List<ServerModel> serverModels) {
        mServerModel = serverModels;
    }

    @NonNull
    @Override
    public ItemServerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View serverItemView = inflater.inflate(R.layout.item_server, parent, false);

        ItemServerViewHolder itemServerViewHolder = new ItemServerViewHolder(serverItemView, new ServerClickListener() {
            @Override
            public void onClick(int adapterPosition) {
                Intent goToChatActivityIntent = new Intent(parent.getContext(), ChatActivity.class);
                goToChatActivityIntent.putExtra("server_name", mServerModel.get(adapterPosition).getName());
                goToChatActivityIntent.putExtra("server_address", mServerModel.get(adapterPosition).getAddress());
                parent.getContext().startActivity(goToChatActivityIntent);
            }
        });
        return itemServerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemServerViewHolder holder, int position) {
        ServerModel serverModel = mServerModel.get(position);

        holder.serverNameTextView.setText(serverModel.getName());
    }

    @Override
    public int getItemCount() {
        return mServerModel.size();
    }

    public class ItemServerViewHolder extends RecyclerView.ViewHolder {
        public TextView serverNameTextView;

        public ItemServerViewHolder(@NonNull View itemView, ServerClickListener serverClickListener) {
            super(itemView);

            serverNameTextView = (TextView) itemView.findViewById(R.id.server_name_tv);
            serverNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    serverClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface ServerClickListener {
        void onClick(int adapterPosition);
    }
}
