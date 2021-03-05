package com.example.myawesomeapp.presentation.mealdisplay.meals.adapter;

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
 * Meal Grid Adapter
 */
public class MealGridAdapter extends RecyclerView.Adapter<MealGridAdapter.MealGridViewHolder> {

    /**
     * Meal Grid View Holder static class
     */
    public static class MealGridViewHolder extends RecyclerView.ViewHolder {

        TextView mealTitle;
        ImageView mealThumbnail;
        ConstraintLayout mealLayout;
        CheckBox isFavorite;

        View view;

        MealItemViewModel mealItemViewModel ;
        MealActionInterface mealActionInterface;

        /**
         * Meal Grid View Holder constructor
         * @param itemView - a view
         * @param mealActionInterface - a meal action
         */
        public MealGridViewHolder(@NonNull View itemView, MealActionInterface mealActionInterface) {
            super(itemView);
            view = itemView;

            mealTitle = itemView.findViewById(R.id.mealTitle);
            mealThumbnail = itemView.findViewById(R.id.mealThumbnail);
            mealLayout = itemView.findViewById(R.id.mealLayout);
            isFavorite = itemView.findViewById(R.id.isFavorite);
            this.mealActionInterface = mealActionInterface;

            setupListeners();
        }

        /**
         * Setup the favorite btn listener
         */
        private void setupListeners() {
            isFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    mealActionInterface.onFavoriteToggle(mealItemViewModel.getId(), mealItemViewModel.getTitle(),
                        mealItemViewModel.getDescription(), mealItemViewModel.getThumbnail(), isChecked);
                    mealItemViewModel.setFavorite(isChecked);
                }
            });
        }

        void bind(MealItemViewModel mealItemViewModel) {
            this.mealItemViewModel = mealItemViewModel;
            mealTitle.setText(mealItemViewModel.getTitle());
            Glide.with(view)
                .load(mealItemViewModel.getThumbnail())
                .into(mealThumbnail);
            isFavorite.setChecked(mealItemViewModel.isFavorite());

        }
    }

    private List<MealItemViewModel> mealItemViewModelList;
    private MealActionInterface mealActionInterface;

    /**
     * Meal Grid Adapter constructor
     * @param mealActionInterface - a meal action
     */
    public MealGridAdapter(MealActionInterface mealActionInterface) {
        mealItemViewModelList = new ArrayList<>();
        this.mealActionInterface = mealActionInterface;
    }

    /**
     * Add list to class variable
     * @param mealItemViewModelList - the list of meal items
     */
    public void bindViewModels(List<MealItemViewModel> mealItemViewModelList) {
        this.mealItemViewModelList.clear();
        this.mealItemViewModelList.addAll(mealItemViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_meal_grid, parent, false);
        MealGridAdapter.MealGridViewHolder mealGridViewHolder = new MealGridAdapter.MealGridViewHolder(view, mealActionInterface);
        return mealGridViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealGridViewHolder holder, final int position) {
        holder.bind(mealItemViewModelList.get(position));
        holder.mealLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mealActionInterface.onMeal(mealItemViewModelList.get(position).getTitle(),
                    mealItemViewModelList.get(position).getDescription(),
                    mealItemViewModelList.get(position).getThumbnail());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealItemViewModelList.size();
    }
}
