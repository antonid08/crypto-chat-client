package com.antonid.chatclient.gui.chat;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antonid.chatclient.R;
import com.antonid.chatclient.models.Message;
import com.antonid.chatclient.models.User;

import java.util.ArrayList;
import java.util.List;

class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private User loggedUser;

    private List<Message> messages;

    MessagesAdapter(User loggedUser, List<Message> messages) {
        this.loggedUser = loggedUser;
        this.messages = messages != null ? messages : new ArrayList<Message>();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages != null ? messages.size() : 0;
    }

    void add(Message message) {
        messages.add(message);
        notifyItemInserted(messages.size());
    }


    class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        MessageViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_item, parent));

            text = (TextView) itemView.findViewById(R.id.text);
        }

        void bind(Message message) {
            text.setText(message.getText());

            if (loggedUser.getUsername().equals(message.getSenderUsername())) {
                ((RelativeLayout) itemView).setGravity(Gravity.START);
            } else {
                ((RelativeLayout) itemView).setGravity(Gravity.END);
            }
        }
    }

}
