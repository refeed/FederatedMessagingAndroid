package com.refeed_ppb1.federatedmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.refeed_ppb1.federatedmessaging.models.MessageModel;

import java.util.List;

public class MessageRVAdapter extends RecyclerView.Adapter<MessageRVAdapter.ItemChatViewHolder> {
    private List<MessageModel> mMessageModels;

    public MessageRVAdapter(List<MessageModel> messageModels) {
        mMessageModels = messageModels;
    }

    @NonNull
    @Override
    public ItemChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View serverItemView = inflater.inflate(R.layout.item_chat, parent, false);

        ItemChatViewHolder itemChatViewHolder = new ItemChatViewHolder(serverItemView, new MessageClickListener() {
            @Override
            public void onClick(int adapterPosition) {
                Toast.makeText(parent.getContext(), mMessageModels.get(adapterPosition).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        return itemChatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemChatViewHolder holder, int position) {
        MessageModel messageModel = mMessageModels.get(position);

        holder.senderTextView.setText(messageModel.getSender());
        holder.dateTextView.setText(messageModel.getUpdatedAt());
        holder.bodyTextView.setText(messageModel.getBody());
    }

    @Override
    public int getItemCount() {
        return mMessageModels.size();
    }

    public class ItemChatViewHolder extends RecyclerView.ViewHolder {
        public TextView senderTextView;
        public TextView dateTextView;
        public TextView bodyTextView;
        public LinearLayout fullMessageView;

        public ItemChatViewHolder(@NonNull View itemView, MessageClickListener messageClickListener) {
            super(itemView);

            senderTextView = (TextView) itemView.findViewById(R.id.sender_chat_tv);
            dateTextView = (TextView) itemView.findViewById(R.id.date_chat_tv);
            bodyTextView = (TextView) itemView.findViewById(R.id.body_chat_tv);
            fullMessageView = (LinearLayout) itemView.findViewById(R.id.full_msg_item);

            fullMessageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }

    public interface MessageClickListener {
        // TODO: Move this as RVItemClickListener instead
        void onClick(int adapterPosition);
    }
}
