package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

/**
 * Created by gadik
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.onImageClickListener{

    private int mHeadIndex;
    private int mBodyIndex;
    private int mLegsIndex;

    private static final int NUM_OF_PARTS = 12;

    public static final String KEY_HEAD_INDEX = "key-head-index";
    public static final String KEY_BODY_INDEX = "key-body-index";
    public static final String KEY_LEGS_INDEX = "key-legs-index";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onItemSelected(int position) {
        Toast.makeText(this, "Item selected = " + position, Toast.LENGTH_SHORT).show();

        // Find the right body part and list index of the selected image
        int bodyPartNumber = position / NUM_OF_PARTS;
        int listIndex = position - (NUM_OF_PARTS * bodyPartNumber);

        switch (bodyPartNumber){
            case 0:
                mHeadIndex = listIndex;
                break;
            case 1:
                mBodyIndex = listIndex;
                break;
            case 2:
                mLegsIndex = listIndex;
                break;
            default:
                break;
        }

        Bundle b = new Bundle();
        b.putInt(KEY_HEAD_INDEX, mHeadIndex);
        b.putInt(KEY_BODY_INDEX, mBodyIndex);
        b.putInt(KEY_LEGS_INDEX, mLegsIndex);

        final Intent startDetailActivity = new Intent(this, AndroidMeActivity.class);
        startDetailActivity.putExtras(b);

        Button nextButton = (Button) findViewById(R.id.next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startDetailActivity);
            }
        });
    }
}
