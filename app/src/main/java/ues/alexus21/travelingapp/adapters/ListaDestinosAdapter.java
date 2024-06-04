package ues.alexus21.travelingapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ues.alexus21.travelingapp.R;
import ues.alexus21.travelingapp.activities.PlaceReviewActivity;
import ues.alexus21.travelingapp.models.ListaDestinos;

public class ListaDestinosAdapter extends BaseAdapter {

    ArrayList<ListaDestinos> listaDestinos;
    Context context;
    TextView textViewPlaceName, textViewPlaceDescription, textViewPlaceLocation;
    ImageView imageViewFavouritePlaceMark;

    public ListaDestinosAdapter(ArrayList<ListaDestinos> listaDestinos, Context context) {
        this.listaDestinos = listaDestinos;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listaDestinos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDestinos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"InflateParams", "UseCompatLoadingForDrawables"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final boolean[] heartFlag = {false}; // Bandera para saber si el lugar es favorito

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_lista_destinos, null);
        }

        ImageView imageView = convertView.findViewById(R.id.imageView);
        textViewPlaceName = convertView.findViewById(R.id.textViewPlaceName);
        textViewPlaceDescription = convertView.findViewById(R.id.textViewPlaceDescription);
        textViewPlaceLocation = convertView.findViewById(R.id.textViewPlaceLocation);
        imageViewFavouritePlaceMark = convertView.findViewById(R.id.imageViewFavouritePlaceMark);

        imageViewFavouritePlaceMark.setOnClickListener(v -> {
            if(!heartFlag[0]) {
                imageViewFavouritePlaceMark.setImageResource(R.drawable.icon_heart_relleno);
                heartFlag[0] = true;
            } else {
                imageViewFavouritePlaceMark.setImageResource(R.drawable.icon_heart_contorno);
                heartFlag[0] = false;
            }
        });

        Glide.with(context)
                .load(listaDestinos.get(position).getImg_url())
                .into(imageView);

        imageView.setOnClickListener(v -> {
            Intent placeReviewActivity = new Intent(context, PlaceReviewActivity.class);
            placeReviewActivity.putExtra("imageUrl", listaDestinos.get(position).getImg_url());
            context.startActivity(placeReviewActivity);
        });

        return convertView;
    }
}
