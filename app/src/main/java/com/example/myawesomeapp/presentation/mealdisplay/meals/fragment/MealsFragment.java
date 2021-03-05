package com.example.myawesomeapp.presentation.mealdisplay.meals.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myawesomeapp.data.di.FakeDepedencyInjection;
import com.example.myawesomeapp.data.entity.MealEntity;
import com.example.myawesomeapp.presentation.mealdisplay.MealActivity;
import com.example.myawesomeapp.presentation.mealdisplay.meals.adapter.MealActionInterface;
import com.example.myawesomeapp.presentation.mealdisplay.meals.adapter.MealGridAdapter;
import com.example.myawesomeapp.presentation.mealdisplay.meals.adapter.MealItemViewModel;
import com.example.myawesomeapp.presentation.mealdisplay.meals.adapter.MealListAdapter;
import com.example.myawesomeapp.presentation.viewmodel.MealFavoriteViewModel;
import com.example.myawesomeapp.presentation.viewmodel.MealsViewModel;

import com.example.myawesomeapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class MealsFragment extends Fragment implements MealActionInterface {

    private RecyclerView recyclerView;
    private View view;

    private ImageView listGridBtn;
    private TextView stateLabel;

    private MealListAdapter mealListAdapter;
    private MealGridAdapter mealGridAdapter;

    final RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);

    private static boolean isList = true;

    private MealsViewModel mealsViewModel;

    private MealFavoriteViewModel mealFavoriteViewModel;

    public MealsFragment(){

    }

    public static MealsFragment newInstance() {
        return new MealsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_meals, container, false);


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        stateLabel = (TextView) getActivity().findViewById(R.id.stateLabel);
        listGridBtn = (ImageView) getActivity().findViewById(R.id.listGridBtn);

        Toolbar headerToolbar = (Toolbar) getActivity().findViewById(R.id.header);
        headerToolbar.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mealFavoriteViewModel = new ViewModelProvider(requireActivity(), FakeDepedencyInjection.getViewModelFactory()).get(MealFavoriteViewModel.class);
        initRecyclerView();
        registerViewModels();
    }

    private void registerViewModels() {
        mealsViewModel = new ViewModelProvider(requireActivity(), FakeDepedencyInjection.getViewModelFactory()).get(MealsViewModel.class);

        mealsViewModel.getAllMeals().observe(getViewLifecycleOwner(), new Observer<List<MealItemViewModel>>() {
            @Override
            public void onChanged(List<MealItemViewModel> articleViewItems) {
                mealListAdapter.bindViewModels(articleViewItems);
                mealGridAdapter.bindViewModels(articleViewItems);
            }
        });
    }

    private void initRecyclerView() {
        mealListAdapter = new MealListAdapter(this);
        mealGridAdapter = new MealGridAdapter(this);
        recyclerView.setAdapter(mealListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.listGridBtn).setVisibility(View.VISIBLE);

        if (isList)
            listGridBtn.setImageResource(R.drawable.ic_baseline_view_list_24);
        else
            listGridBtn.setImageResource(R.drawable.ic_baseline_dashboard_24);

        listGridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isList) {
                    recyclerView.setAdapter(mealListAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    isList = true;
                    listGridBtn.setImageResource(R.drawable.ic_baseline_view_list_24);
                    stateLabel.setText("List");
                } else {
                    recyclerView.setAdapter(mealGridAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
                    isList = false;
                    listGridBtn.setImageResource(R.drawable.ic_baseline_dashboard_24);
                    stateLabel.setText("Grid");
                }
            }
        });
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
    public void onFavoriteToggle(String id, String title, String description, String thumbnail, boolean isFavorite) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(id);
        mealEntity.setTitle(title);
        mealEntity.setDescription(description);
        mealEntity.setThumbnail(thumbnail);

        if (isFavorite)
            mealFavoriteViewModel.addMealToFavorite(mealEntity);
        else
            mealFavoriteViewModel.deleteMealFromFavorite(id);
    }

}
