package Ateam.turfapp;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class ReunionListAdapter extends ListAdapter<Reunion, ReunionViewHolder> {

    public ReunionListAdapter(@NonNull DiffUtil.ItemCallback<Reunion> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ReunionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReunionViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ReunionViewHolder holder, int position) {
        Reunion current = getItem(position);
        holder.bind(String.valueOf(current.getId()));
    }

    static class ReunionDiff extends DiffUtil.ItemCallback<Reunion> {

        @Override
        public boolean areItemsTheSame(@NonNull Reunion oldItem, @NonNull Reunion newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Reunion oldItem, @NonNull Reunion newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
