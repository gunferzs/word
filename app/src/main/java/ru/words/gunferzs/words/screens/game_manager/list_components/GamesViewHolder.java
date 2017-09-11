package ru.words.gunferzs.words.screens.game_manager.list_components;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.words.gunferzs.words.R;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class GamesViewHolder extends RecyclerView.ViewHolder implements GameManagerItemView{

    TextView tvNameOfGame;
    private GamesAdapter.ItemClickListener itemClickListener;
    View itemView;

    public GamesViewHolder(View itemView, GamesAdapter.ItemClickListener itemClickListener) {
        super(itemView);
        this.itemView = itemView;
        tvNameOfGame = (TextView) itemView.findViewById(R.id.tvNameOfGame);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void updateNameOfGame(String name) {
        tvNameOfGame.setText(name);
    }

    @Override
    public void initClickItem(final String tag) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClickItem(tag);
            }
        });
    }
}
