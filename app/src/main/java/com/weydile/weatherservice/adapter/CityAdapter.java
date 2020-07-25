package com.weydile.weatherservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.weydile.weatherservice.R;
import com.weydile.weatherservice.model.database.entity.City;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityVH> {

    private ArrayList<City> cities;
    private Context context;
    private Activity activity;

    public CityAdapter(ArrayList<City> cities, Context context) {
        this.cities = cities;
        this.context = context;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public CityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CityVH(LayoutInflater.from(context)
                .inflate(R.layout.city_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CityVH holder, int position) {
        holder.cityName.setText(cities.get(position).getName());
        holder.cityCard
                .setOnClickListener(v -> activity.showCityWeather(cities.get(position).getName()));
        holder.cityCard.setOnLongClickListener(v -> {
            activity.deleteCity(cities.get(position));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public interface Activity {
        void showCityWeather(String cityName);

        void deleteCity(City city);
    }

    static class CityVH extends RecyclerView.ViewHolder {

        TextView cityName;
        CardView cityCard;

        public CityVH(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.name_of_city);
            cityCard = itemView.findViewById(R.id.city_card_item);
        }
    }
}
