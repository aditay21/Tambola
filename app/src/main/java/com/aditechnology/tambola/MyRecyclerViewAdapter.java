package com.aditechnology.tambola;

import android.content.Context;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private SparseBooleanArray isSelected = new SparseBooleanArray();
    /* access modifiers changed from: private */
    public ItemClickListener mClickListener;
    private String[] mData;
    private LayoutInflater mInflater;

    public interface ItemClickListener {
        void onItemClick(View view, int i);
    }

    MyRecyclerViewAdapter(Context context, String[] strArr) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = strArr;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(R.layout.recyclerview_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.isSelected.get(i)) {
            viewHolder.myTextView.setTextColor(ContextCompat.getColor(viewHolder.
                    myTextView.getContext(),R.color.white));
            viewHolder.layout.setBackgroundColor(ContextCompat.getColor(viewHolder.layout.getContext(), R.color.colorAccent));
        } else if (Build.VERSION.SDK_INT >= 16) {
            viewHolder.myTextView.setTextColor(ContextCompat.getColor(viewHolder.
                    myTextView.getContext(),R.color.black));
            viewHolder.layout.setBackground(ContextCompat.getDrawable(viewHolder.layout.getContext(), R.drawable.border));
        } else {
            viewHolder.myTextView.setTextColor(ContextCompat.getColor(viewHolder.
                    myTextView.getContext(),R.color.black));
            viewHolder.layout.setBackgroundResource(R.drawable.border);
        }
        viewHolder.myTextView.setText(this.mData[i]);

    }

    public int getItemCount() {
        return this.mData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout layout;
        TextView myTextView;

        ViewHolder(View view) {
            super(view);
            this.myTextView = (TextView) view.findViewById(R.id.info_text);
            this.layout = (LinearLayout) view.findViewById(R.id.layout);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (MyRecyclerViewAdapter.this.mClickListener != null) {
                MyRecyclerViewAdapter.this.mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getItem(int i) {
        return this.mData[i];
    }

    /* access modifiers changed from: package-private */
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    /* access modifiers changed from: protected */
    public void updateView(int i) {
        this.isSelected.put(i, true);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void updateAll() {
        this.isSelected.clear();
        notifyDataSetChanged();
    }
}
