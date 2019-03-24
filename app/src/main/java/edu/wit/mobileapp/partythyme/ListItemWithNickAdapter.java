package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListItemWithNickAdapter extends ArrayAdapter<ListItemWithNick> {

    private LayoutInflater mInflater;

    public ListItemWithNickAdapter(Context context, int rid, List<ListItemWithNick> list){
        super(context, rid, list);
        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        //Retrieve data
        ListItemWithNick item = (ListItemWithNick) getItem(position);

        //Use layout file to generate view
        View view = mInflater.inflate(R.layout.list_item_with_nick, null);

        //Set image
        ImageView image;
        image = (ImageView) view.findViewById(R.id.listWithNickImage);
        image.setImageBitmap(item.image);

        //Set Name
        TextView name;
        name = (TextView) view.findViewById(R.id.listNameName);
        name.setText(item.name);

        //Set Nick
        TextView nick;
        nick = (TextView) view.findViewById(R.id.listNickNick);
        nick.setText(item.nick);

        return view;
    }
}
