/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if(savedInstanceState == null){
            int headIndex = 0;
            int bodyIndex = 0;
            int legsIndex = 0;

            Bundle b = getIntent().getExtras();
            if(b != null){
                headIndex = b.getInt(MainActivity.KEY_HEAD_INDEX);
                bodyIndex = b.getInt(MainActivity.KEY_BODY_INDEX);
                legsIndex = b.getInt(MainActivity.KEY_LEGS_INDEX);
            }

            FragmentManager fragmentManager = getSupportFragmentManager();

            drawFragments(fragmentManager, headIndex, bodyIndex, legsIndex, false);
        }
    }

    public static void drawFragments(FragmentManager manager, int headIndex, int bodyIndex, int legsIndex, boolean shouldReplace){
        // Create a new body parts fragments
        BodyPartFragment headFragment = new BodyPartFragment();
        headFragment.setmListIds(AndroidImageAssets.getHeads());
        headFragment.setmIndex(headIndex);

        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setmListIds(AndroidImageAssets.getBodies());
        bodyFragment.setmIndex(bodyIndex);

        BodyPartFragment legsFragment = new BodyPartFragment();
        legsFragment.setmListIds(AndroidImageAssets.getLegs());
        legsFragment.setmIndex(legsIndex);

        // Add the fragments to their containers
        if(shouldReplace){
            manager.beginTransaction()
                    .replace(R.id.head_container, headFragment)
                    .replace(R.id.body_container, bodyFragment)
                    .replace(R.id.legs_container, legsFragment)
                    .commit();
        } else {
            manager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .add(R.id.body_container, bodyFragment)
                    .add(R.id.legs_container, legsFragment)
                    .commit();
        }
    }
}
