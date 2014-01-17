package com.nutrino.pocketbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/*
 * Connnects the view with the datasouce
 */
public class AppAdapter extends ArrayAdapter<Tag> {
    private List<Tag> mItems;

    public AppAdapter(TagListActivity context, int textViewResourceId, List<Tag> objects) {
        super(context, textViewResourceId, objects);
        mItems = objects;
    }

    /*
     *
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.list_item, null);
        }

        final Tag tag = mItems.get(position);
        if (tag != null) {
            LinearLayout view = (LinearLayout)convertView.findViewById(R.id.list_tag_body);
            if (view != null) {
                TextView textView = (TextView) view.findViewById(R.id.list_tag_name);
                textView.setText(tag.getName());
            }


            convertView.setTag("" + position);

            convertView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        TagListActivity tagListActivity = (TagListActivity)getContext();
                        int candidatePosition = Integer.parseInt((String)view.getTag());
                        tagListActivity.markCandidateForDeletion(candidatePosition);
                    }
                    return false;
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent openNotesList = new Intent(getContext(), NoteListActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString(getContext().getString(R.string.tag_list_title), tag.getName());
                    openNotesList.putExtras(extras);
                    getContext().startActivity(openNotesList);
                }
            });
        }

        return convertView;
    }
}
