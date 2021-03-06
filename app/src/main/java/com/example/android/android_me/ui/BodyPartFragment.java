package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gadik
 */

public class BodyPartFragment extends Fragment {

    public static final String TAG = BodyPartFragment.class.getName();

    public static final String KEY_LIST_IDS = "key-list-ids";
    public static final String KEY_INDEX = "key-index";

    private List<Integer> mListIds;
    private int mIndex;

    public void setmListIds(List<Integer> mListIds) {
        this.mListIds = mListIds;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    public BodyPartFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mListIds = savedInstanceState.getIntegerArrayList(KEY_LIST_IDS);
            mIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        final ImageView bodyPartImageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        if(mListIds != null){
            bodyPartImageView.setImageResource(mListIds.get(mIndex));

            // Change the image to the next one in the list every time the users clicks on it
            bodyPartImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mIndex < (mListIds.size() - 1)){
                        mIndex++;
                    } else {
                        mIndex = 0;
                    }

                    bodyPartImageView.setImageResource(mListIds.get(mIndex));
                }
            });
        } else {
            Log.e(TAG, "This frament have a null list of image id's");
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(KEY_LIST_IDS, (ArrayList<Integer>) mListIds);
        outState.putInt(KEY_INDEX, mIndex);
    }
}
