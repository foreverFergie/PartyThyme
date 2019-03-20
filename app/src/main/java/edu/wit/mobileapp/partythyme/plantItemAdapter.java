package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class plantItemAdapter extends RecyclerView.Adapter<plantItemAdapter.plantViewHolder>{

    private List<plant_temp> plants;
    private static List<String> actions;
    private Context context;
    public static class plantViewHolder extends RecyclerView.ViewHolder  {
        public ImageView image;
        public TextView name;
        //public Spinner spinner;
        public plantViewHolder(View v){
            super(v);
            image=(ImageView)v.findViewById(R.id.plant);
            name=(TextView)v.findViewById(R.id.plant_name);
            //spinner=(Spinner) v.findViewById(R.id.spinner);

        }

    }



    public plantItemAdapter(List<plant_temp> plants,Context context){
       this.plants=plants;
       //actions=dropdown;
       this.context=context;
    }

    @Override
    public plantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plant,viewGroup,false);
        return new plantViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull plantViewHolder pvh, int i) {
        pvh.image.setImageBitmap(plants.get(pvh.getAdapterPosition()).image);
        pvh.name.setText(plants.get(pvh.getAdapterPosition()).name);
        //ArrayAdapter<String> dropDown = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, actions);

        //pvh.spinner.setAdapter(dropDown);


    }

    public int getItemCount(){
        return plants.size();
    }


}
