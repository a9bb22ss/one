package com.example.chenf.screenoncount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenf on 2015/10/7.
 */
public class DurationAdapter extends ArrayAdapter<Duration> {
    private int resourceId;
    public DurationAdapter(Context context, int resource, List<Duration> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*Duration duration = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView number = (TextView) view.findViewById(R.id.number);
        TextView textView = (TextView) view.findViewById(R.id.duration);
        number.setText(duration.getSequenceNum());
        textView.setText(duration.getDuration());
        return view;*/
        Duration duration = getItem(position);
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder.number = (TextView) convertView.findViewById(R.id.number);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.duration);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.number.setText(String.valueOf(duration.getSequenceNum()));
        viewHolder.duration.setText(duration.getDuration());
        return convertView;
    }

    class ViewHolder{
        public TextView number;
        public TextView duration;
    }
}
