package com.example.vishniya.minesweeperandroid.Data.highscoreroomdatabase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vishniya.minesweeperandroid.R;

import java.util.List;

public class HighScoreListAdapter extends RecyclerView.Adapter<HighScoreListAdapter.HighScoreViewHolder> {

    private final LayoutInflater inflater;
    private List<HighScore> highScores; // cached copy of words

    HighScoreListAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HighScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.highscore_item,parent,false);
        return new HighScoreViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull HighScoreViewHolder holder, int position) {
    if(highScores!= null){
        HighScore current = highScores.get(position);
        // todo format this string properly using
        holder.highScoreItemView.setText(current.getScore()+ "      " + current.getNickname() );
    }
    else {
        // incase data not ready yet
        holder.highScoreItemView.setText("No nickname yet??");
    }

    }

    void setNicknames (List<HighScore> highScores){
        this.highScores = highScores;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (highScores != null)
            return highScores.size();
        else return 0;
    }



    public class HighScoreViewHolder extends RecyclerView.ViewHolder {
        private final TextView highScoreItemView;

        public HighScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            highScoreItemView=itemView.findViewById(R.id.highscore_text);
        }
    }



}
