package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter {
    private LayoutInflater mInflate;

    public UserAdapter(Context context, int rid, List<User> list){
        super(context,rid,list);
        mInflate=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        User user = (User)getItem(position);
        View view = mInflate.inflate(R.layout.user,null);

        TextView username;
        username=(TextView)view.findViewById(R.id.user);
        username.setText(user.username);

        return view;

    }




}
