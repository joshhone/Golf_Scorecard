package com.disney.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ScoreAdapter  extends BaseAdapter {
    private Context mContext;
    private Score[] mScores;
    public ScoreAdapter(Context context, Score[] scores){
        mScores = scores;
        mContext = context;
    }
    @Override
    public int getCount() {
        return mScores.length;
    }

    @Override
    public Score getItem(int position) {
        return mScores[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.scorecard_item, null);
            viewHolder = new ViewHolder();
            viewHolder.mHoleScoreLabel = (TextView)convertView.findViewById(R.id.holeScoreLabel);
            viewHolder.mHoleNumberLabel = (TextView) convertView.findViewById(R.id.holeNumberLabel);
            viewHolder.mDecrementButton = (Button) convertView.findViewById(R.id.decrementButton);
            viewHolder.mIncrementButton = (Button) convertView.findViewById(R.id.incrementButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Score score = mScores[position];
        viewHolder.mHoleNumberLabel.setText("Hole "+(score.getHoleNumber()+1) + " :");
        viewHolder.mHoleScoreLabel.setText(score.getHoleScore()+"");
        viewHolder.mIncrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScores[position].setHoleScore(mScores[position].getHoleScore() + 1);
                viewHolder.mHoleScoreLabel.setText(mScores[position].getHoleScore() + "");
            }
        });
        viewHolder.mDecrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newScore = 0;
                if (mScores[position].getHoleScore() - 1 > 0) {
                    newScore = mScores[position].getHoleScore() - 1;
                }
                mScores[position].setHoleScore(newScore);
                viewHolder.mHoleScoreLabel.setText(mScores[position].getHoleScore() + "");
            }
        });
        return convertView;
    }
    private static class ViewHolder{
        TextView mHoleScoreLabel;
        TextView mHoleNumberLabel;
        Button mDecrementButton;
        Button mIncrementButton;
    }
}
