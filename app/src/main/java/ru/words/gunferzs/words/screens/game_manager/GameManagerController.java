package ru.words.gunferzs.words.screens.game_manager;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindView;
import ru.words.gunferzs.words.R;
import ru.words.gunferzs.words.screens.base.MvpBaseController;
import ru.words.gunferzs.words.screens.game_frills.GameFrillsController;
import ru.words.gunferzs.words.screens.game_frills_settings.GameFrillsSettingsController;
import ru.words.gunferzs.words.screens.game_manager.list_components.GameManagerPresenterAdapter;
import ru.words.gunferzs.words.screens.game_manager.list_components.GamesAdapter;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class GameManagerController extends MvpBaseController<GameManagerView,GameManagerPresenter> implements GameManagerView{

    public static final String TAG = "frills";
    public static final int NAME = R.string.frills;
    public static final int RULES = R.string.frill_rules;


    @BindView(R.id.rvList)
    public RecyclerView rvList;
    @BindView(R.id.gameScreen)
    public ViewGroup gameScreen;
    GamesAdapter gamesAdapter;

    public GameManagerController() {

    }

    GamesAdapter.ItemClickListener itemClickListener = new GamesAdapter.ItemClickListener() {
        @Override
        public void onClickItem(String tag) {
            showScreen(tag);
        }
    };

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        if(isAttached()){
            getPresenter().updateList();
        }
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_game_manager,container,false);
    }

    void showScreen(String tag){
        switch(tag){
            case GameFrillsController.TAG:
                getRouter().pushController(RouterTransaction.with(new GameFrillsSettingsController()));
                break;
        }
    }

    @Override
    protected void viewBound(View view) {
        gamesAdapter = new GamesAdapter(itemClickListener);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @NonNull
    @Override
    public GameManagerPresenter createPresenter() {
        return new GameManagerPresenter();
    }

    @Override
    public void updateList(GameManagerPresenterAdapter presenter) {
        gamesAdapter.setPresenter(presenter);
        rvList.setAdapter(gamesAdapter);
    }
}
