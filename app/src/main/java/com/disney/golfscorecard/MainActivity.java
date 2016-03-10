package com.disney.golfscorecard;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends ListActivity {

    private Score[] mScores;
    @Bind(android.R.id.list) ListView mListView;
    private ScoreAdapter mListAdapter;
    private static final String PREFS_FILE = "com.disney.golfscorecard.preferences";
    private static final String KEY_STROKECOUNT = "KEY_STROKECOUNT";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initScores();
        mListAdapter = new ScoreAdapter(this, mScores);
        mListView.setAdapter(mListAdapter);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        for (int i = 0; i < mScores.length; i++){
            mScores[i].setHoleScore(mSharedPreferences.getInt(KEY_STROKECOUNT+i,0));
        }
    }

    private void initScores(){
        mScores = new Score[18];
        for(int i = 0; i < mScores.length; i++){
            Score score = new Score();
            score.setHoleNumber(i);
            score.setHoleScore(Score.DEFAULT_SCORE);
            mScores[i] = score;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(int i = 0; i < mScores.length; i++){
            mEditor.putInt(KEY_STROKECOUNT+i,mScores[i].getHoleScore());
        }
        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_clear_strokes){
            mEditor.clear();
            mEditor.apply();
            for (Score score : mScores){
                score.setHoleScore(0);
            }
            mListAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
