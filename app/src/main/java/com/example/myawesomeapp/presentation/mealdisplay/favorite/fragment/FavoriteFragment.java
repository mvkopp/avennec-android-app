package com.example.myawesomeapp.presentation.mealdisplay.favorite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myawesomeapp.data.di.FakeDepedencyInjection;
import com.example.myawesomeapp.presentation.mealdisplay.MealActivity;
import com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter.FavoriteActionInterface;
import com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter.FavoriteAdapter;
import com.example.myawesomeapp.presentation.mealdisplay.favorite.adapter.FavoriteItemViewModel;
import com.example.myawesomeapp.presentation.viewmodel.Event;
import com.example.myawesomeapp.presentation.viewmodel.MealFavoriteViewModel;
import com.example.myawesomeapp.presentation.viewmodel.MealsViewModel;

import com.example.myawesomeapp.R;

import java.util.List;

/**
 * Favorite fragment class
 */
public class FavoriteFragment extends Fragment implements FavoriteActionInterface {

    private View view;
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private MealFavoriteViewModel mealFavoriteViewModel;
    private MealsViewModel mealsViewModel;

    private ImageView listGridBtn;
    private TextView stateLabel;
    private TextView favoriteLabel;

    /**
     * Favorite fragment empty constructor
     */
    public FavoriteFragment(){

    }

    public static FavoriteFragment newInstance(){ return new FavoriteFragment(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_meals, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        // Recover the activity elements
        stateLabel = (TextView) getActivity().findViewById(R.id.stateLabel);
        listGridBtn = (ImageView) getActivity().findViewById(R.id.listGridBtn);
        favoriteLabel = (TextView) getActivity().findViewById(R.id.favoriteLabel);

        // Set stateLabel and listGridBtn to invisible
        stateLabel.setVisibility(View.INVISIBLE);
        listGridBtn.setVisibility(View.INVISIBLE);
        // Set the favorite label to visible
        favoriteLabel.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        mealFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDepedencyInjection.getViewModelFactory()).get(MealFavoriteViewModel.class);
        mealsViewModel = new ViewModelProvider(requireActivity(), FakeDepedencyInjection.getViewModelFactory()).get(MealsViewModel.class);

        mealFavoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), new Observer<List<FavoriteItemViewModel>>() {
            @Override
            public void onChanged(List<FavoriteItemViewModel> favoriteViewItems) {
                favoriteAdapter.bindViewModels(favoriteViewItems);
            }
        });

        mealFavoriteViewModel.getMealAddedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //
            }
        });

        mealFavoriteViewModel.getMealDeletedEvent().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                //
            }
        });
    }

    /**
     * Setup the recycler view
     */
    private void setupRecyclerView() {
        favoriteAdapter = new FavoriteAdapter(this);
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext())); // linear by default
    }

    @Override
    public void onMeal(String mealTitle, String mealDescription, String mealThumbnail) {

        Intent intent = new Intent(getActivity(), MealActivity.class);
        intent.putExtra("mealTitle", mealTitle);
        intent.putExtra("mealDescription", mealDescription);
        intent.putExtra("mealThumbnail", mealThumbnail);
        intent.putExtra("isFavorite", true);
        startActivity(intent);
    }

    @Override
    public void onRemoveFavorite(String id) {
        mealFavoriteViewModel.deleteMealFromFavorite(id);
        mealsViewModel.removeMealFromFavorites(id);
    }
}
