package ru.words.gunferzs.words.screens.game_manager.list_components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.words.gunferzs.words.R;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class GamesAdapter extends RecyclerView.Adapter<GamesViewHolder> {

    GameManagerPresenterAdapter presenter;
    ItemClickListener listener;

    public GamesAdapter(ItemClickListener listener) {
        this.listener = listener;
        presenter = new GameManagerPresenterAdapter();
    }

    public void setPresenter(GameManagerPresenterAdapter presenter) {
        this.presenter = null;
        this.presenter = new GameManagerPresenterAdapter(presenter);
        notifyDataSetChanged();
    }

    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game,parent, false);
        return new GamesViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(GamesViewHolder holder, int position) {
        presenter.updateView(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.size();
    }

    public interface ItemClickListener{
        void onClickItem(String tag);
    }
}
