package com.example.alokshah.samsung_movieapp;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by alokshah on 3/17/18.
 */

public class ListAdapter extends ArrayAdapter<RowItem> {

    Context context;

    public ListAdapter(FragmentActivity context, int resourceId,
                       List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView mImage;
        TextView mName;
        TextView mPopularity;
        TextView mGenre;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.mPopularity = (TextView) convertView.findViewById(R.id.popularity);
            holder.mName = (TextView) convertView.findViewById(R.id.name);
            holder.mGenre = (TextView)convertView.findViewById(R.id.genre) ;
            holder.mImage = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.mPopularity.setText(rowItem.getPopularity());
        holder.mName.setText(rowItem.getName());
        holder.mGenre.setText(rowItem.getgenre());
        holder.mImage.setImageResource(rowItem.getImageId());

        return convertView;
    }
}