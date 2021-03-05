package com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myawesomeapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Favorite Adapter
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{

    private List<FavoriteItemViewModel> favoriteItemViewModelList;
    private FavoriteActionInterface favoriteActionInterface;

    /**
     * Favorite Adapter constructor
     * @param favoriteActionInterface - a favorite action
     */
    public FavoriteAdapter(FavoriteActionInterface favoriteActionInterface) {
        favoriteItemViewModelList = new ArrayList<>();
        this.favoriteActionInterface = favoriteActionInterface;
    }

    /**
     * Add list to class variable
     * @param favoriteItemViewModelList - the list of favorite items
     */
    public void bindViewModels(List<FavoriteItemViewModel> favoriteItemViewModelList) {
        this.favoriteItemViewModelList.clear();
        this.favoriteItemViewModelList.addAll(favoriteItemViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_meal_favorite, parent, false);
        FavoriteViewHolder favoriteViewHolder = new FavoriteViewHolder(view, favoriteActionInterface);
        return favoriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder,final int position) {
        holder.bind(favoriteItemViewModelList.get(position));
        holder.mealLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteActionInterface.onMeal(favoriteItemViewModelList.get(position).getTitle(),
                    favoriteItemViewModelList.get(position).getDescription(), favoriteItemViewModelList.get(position).getThumbnail());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteItemViewModelList.size();
    }

    /**
     * Favorite View Holder static class
     */
    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private FavoriteActionInterface favoriteActionInterface;
        private ConstraintLayout mealLayout;
        private ImageView mealThumbnail;
        private TextView mealTitle;
        private CheckBox isFavorite;

        private View view;

        private FavoriteItemViewModel favoriteItemViewModel;

        /**
         * Favorite View Holder constructor
         * @param itemView - a view
         * @param favoriteActionInterface - a favorite action
         */
        public FavoriteViewHolder(@NonNull View itemView, final FavoriteActionInterface favoriteActionInterface) {
            super(itemView);
            view = itemView;

            // Recover the details
            mealTitle = view.findViewById(R.id.mealTitle);
            mealThumbnail = view.findViewById(R.id.mealThumbnail);
            isFavorite = view.findViewById(R.id.isFavorite);
            mealLayout = view.findViewById(R.id.mealLayout);

            setupListeners();
            // Save the action
            this.favoriteActionInterface = favoriteActionInterface;
        }

        /**
         * Setup the favorite btn listener
         */
        private void setupListeners() {
            isFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!isChecked)
                        favoriteActionInterface.onRemoveFavorite(favoriteItemViewModel.getId());


                }
            });
        }

        void bind(FavoriteItemViewModel favoriteItemViewModel){
            this.favoriteItemViewModel = favoriteItemViewModel;
            mealTitle.setText(favoriteItemViewModel.getTitle());
            Glide.with(view)
                .load(favoriteItemViewModel.getThumbnail())
                .into(mealThumbnail);
            isFavorite.setChecked(true);
        }
    }
}
