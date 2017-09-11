package ru.words.gunferzs.words.screens.game_frills_settings;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindView;
import ru.words.gunferzs.words.R;
import ru.words.gunferzs.words.screens.base.BaseController;
import ru.words.gunferzs.words.screens.game_frills.GameFrillsController;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class GameFrillsSettingsController extends BaseController {

    public static final String TAG = "frills_settings";


    @BindView(R.id.btnPlay)
    public Button btnPlay;
    @BindView(R.id.etSeconds)
    public EditText etSeconds;
    @BindView(R.id.etMinutes)
    public EditText etMinutes;


    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_game_frills_settings,container,false);
    }

    @Override
    protected void viewBound(View view) {
        etMinutes.setText("00");
        etSeconds.setText("20");
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etMinutes.getText().toString().isEmpty()
                        &&!etSeconds.getText().toString().isEmpty()){
                    long minute = Integer.parseInt(etMinutes.getText().toString());
                    long seconds = Integer.parseInt(etSeconds.getText().toString());
                    getRouter().pushController(RouterTransaction.with(new GameFrillsController(minute*60+seconds)));
                }
            }
        });
    }
}
