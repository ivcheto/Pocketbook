package com.nutrino.pocketbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

import static android.app.AlertDialog.Builder;
import static android.content.DialogInterface.OnClickListener;

public class TagsActivity extends Activity implements View.OnTouchListener {
    private static final String LOG_TAG = TagsActivity.class.getSimpleName();
    private TagsDataSource mTagsDataSource;
    private GestureDetector mGestureDetector;
    private AppGestureDetectorListener mAppGestureDetectorListener;
    private ListView mListView;
    public View mStartCircle, mEndCircle;

    private int mCandidateForDeletion = -1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);

        mTagsDataSource = new TagsDataSource(this);
        mAppGestureDetectorListener = new AppGestureDetectorListener(this);
        mGestureDetector = new GestureDetector(this, mAppGestureDetectorListener);
        mGestureDetector.setOnDoubleTapListener(mAppGestureDetectorListener);

        try {
            mTagsDataSource.open();
        } catch (SQLException ex) {
            Log.e(LOG_TAG, "Cannot open database");
            //TODO Add some dialog or something here.
        }

        final List<Tag> tags = mTagsDataSource.getAllTags();
        final AppAdapter adapter = new AppAdapter(this, R.layout.list_tag, tags);

        mListView = (ListView)findViewById(R.id.list);
        mListView.setAdapter(adapter);
        mListView.setOnTouchListener(this);

        TextView listName = (TextView)findViewById(R.id.list_title);
        listName.setText(R.string.tags_label);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        mTagsDataSource.close();
        super.onDestroy();
    }

    public void addTag(final View view) {
        Log.i(LOG_TAG, "create new tag");

        final LayoutInflater li = getLayoutInflater();
        final View addTagView = li.inflate(R.layout.add_tag, null);
        final Builder alertDialogBuilder = new Builder(view.getContext());
        alertDialogBuilder.setView(addTagView);
        alertDialogBuilder.setTitle(R.string.add_tag_title);
        alertDialogBuilder.setPositiveButton(R.string.add_tag_ok_button, null);
        alertDialogBuilder.setNegativeButton(R.string.add_tag_cancel_button, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
            Button positive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                EditText editText = (EditText) addTagView.findViewById(R.id.tag_name_input);
                final String tagName = editText.getText().toString();

                if(!tagName.equals("")) {
                    final Tag tag = mTagsDataSource.createTag(tagName);

                    final AppAdapter adapter = (AppAdapter)mListView.getAdapter();
                    adapter.add(tag);
                    alertDialog.dismiss();
                } else {
                    TextView errorMessage = (TextView)addTagView.findViewById(R.id.tag_error_no_name);
                    errorMessage.setVisibility(View.VISIBLE);
                }
                }
            });
            }
        });
        alertDialog.show();


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mGestureDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    public void markCandidateForDeletion(int candidatePosition) {
        mCandidateForDeletion = candidatePosition;
    }

    public void deleteMarkedTag() {
        if (mCandidateForDeletion != -1) {
            AppAdapter appAdapter = (AppAdapter) mListView.getAdapter();
            Tag tag = appAdapter.getItem(mCandidateForDeletion);
            Log.d(LOG_TAG, "Delete tag " + tag.getName() + " candidate " + mCandidateForDeletion);
            appAdapter.remove(tag);
            mTagsDataSource.deleteTag(tag);
            mCandidateForDeletion = -1;
        }
    }

    public void moveMarkedTag(float xDelta) {
        if (mCandidateForDeletion != 1) {
            LinearLayout view = (LinearLayout) mListView.getChildAt(mCandidateForDeletion);
            if (view != null) {
                int leftPadding = view.getPaddingLeft();
                view.setPadding((int) xDelta - leftPadding, 0, 0, 0);
                view.invalidate();
            }
        }
    }
}
