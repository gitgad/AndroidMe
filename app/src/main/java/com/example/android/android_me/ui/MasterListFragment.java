package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by gadik
 */

public class MasterListFragment extends Fragment {

    onImageClickListener mCallback;

    public interface onImageClickListener{
        void onItemSelected(int position);
    }

    public MasterListFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Check if the host activity implemented the interface
        try{
            mCallback = (onImageClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onItemSelected");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        GridView imagesGridView = (GridView) rootView.findViewById(R.id.master_gridview);

        // Set the adapter with all images to the grid view
        MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        imagesGridView.setAdapter(adapter);

        // Trigger the callback onItemSelected when an item is clicked
        imagesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onItemSelected(position);
            }
        });

        return rootView;
    }
}
