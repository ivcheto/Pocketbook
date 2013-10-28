package com.eipipuz.ivaslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends ArrayAdapter<Tag> {
    private List<Tag> mItems;

    public AppAdapter(HomeActivity context, int textViewResourceId, List<Tag> objects) {
        super(context, textViewResourceId, objects);
        mItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.list_tag, null);
        }

        Tag tag = mItems.get(position);
        if (tag != null) {
            TextView textView = (TextView)convertView.findViewById(R.id.list_tag_name);
            if (textView != null) {
                textView.setText(tag.getName());
            }
        }

        convertView.setTag("" + position);
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    HomeActivity homeActivity = (HomeActivity)getContext();
                    int candidatePosition = Integer.parseInt((String)view.getTag());
                    homeActivity.markCandidateForDeletion(candidatePosition);
                }
                return false;
            }
        });

        return convertView;
    }
}
