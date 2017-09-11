package ru.words.gunferzs.words;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.words.gunferzs.words.game.GameInfo;
import ru.words.gunferzs.words.game.GameSettings;
import ru.words.gunferzs.words.screens.game_manager.GameManagerController;
import ru.words.gunferzs.words.utils.SensorsManage;

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class MainActivity extends AppCompatActivity {

    Router router;
    @BindView(R.id.parent_container)
    FrameLayout container;
    static List<GameSettings> gameSettings;

    @Inject
    SensorsManage sensorsManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getAppComponent().inject(this);
        setContentView(R.layout.activity_main);
        gameSettings = new ArrayList<>();
        gameSettings.add(new GameSettings(new GameInfo(getString(GameManagerController.NAME),
                getString(GameManagerController.RULES),GameManagerController.TAG),true, 3*60*60* 1000L,false,false));
        ButterKnife.bind(this);

        router = Conductor.attachRouter(this,container,savedInstanceState);
        if(!router.hasRootController()){
            router.setRoot(RouterTransaction.with(new GameManagerController()));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorsManage.destroySensorManager();
    }

    public static List<GameSettings> getGameSettings() {
        return gameSettings;
    }

    @Override
    public void onBackPressed() {
        if(!router.handleBack()) {
            super.onBackPressed();
        }
    }
}
