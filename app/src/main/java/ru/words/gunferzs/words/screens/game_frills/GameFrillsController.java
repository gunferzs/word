package ru.words.gunferzs.words.screens.game_frills;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import ru.words.gunferzs.words.MyApplication;
import ru.words.gunferzs.words.R;
import ru.words.gunferzs.words.screens.base.MvpBaseController;
import ru.words.gunferzs.words.utils.BundleBuilder;
import ru.words.gunferzs.words.utils.GestureOrientation;
import ru.words.gunferzs.words.utils.MySensorEvent;
import ru.words.gunferzs.words.utils.SensorListener;
import ru.words.gunferzs.words.utils.SensorsManage;
import ru.words.gunferzs.words.utils.SpatialGestureListener;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class GameFrillsController extends MvpBaseController<GameFrillsView,GameFrillsPresenter> implements GameFrillsView{

    public static final String TAG = "frills";
    public static final int NAME = R.string.frills;
    public static final int RULES = R.string.frill_rules;
    public static final String KEY_TIME = "time";
    @BindColor(R.color.successColor)
    int successColor;
    @BindColor(R.color.failureColor)
    int failureColor;
    @BindColor(R.color.transparent)
    int transparent;
    boolean inGame = false;

    GestureOrientation gestureOrientation;

    SpatialGestureListener spatialGestureListener  = new SpatialGestureListener() {
        @Override
        public void onNod(String id) {
            System.out.println("nod "+id);
            if(inGame) {
                switch (id) {
                    case GestureOrientation.XY_COMPONENT:
                        System.out.println("failure");
                        getPresenter().addFailure();
                        rlBase.setBackgroundColor(failureColor);
                        break;
                    case GestureOrientation.YZ_COMPONENT:
                        System.out.println("success");
                        getPresenter().addSuccess();
                        rlBase.setBackgroundColor(successColor);
                        break;
                }
            }
        }
    };

    View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(inGame){
                float x = event.getX();
                float centerY = rlBase.getWidth()/2;
                if(event.getAction()==MotionEvent.ACTION_UP) {
                    if (centerY > x) {
                        System.out.println("success");
                        getPresenter().addSuccess();
                        rlBase.setBackgroundColor(successColor);
                    } else {
                        System.out.println("failure");
                        getPresenter().addFailure();
                        rlBase.setBackgroundColor(failureColor);
                    }
                }
            }
            return true;
        }
    };

    long time;


    @BindView(R.id.finishLayout)
    LinearLayout finishLayout;
    @BindView(R.id.btnFinish)
    public Button btnFinish;
    @BindView(R.id.btnReplay)
    public Button btnReplay;

    @BindView(R.id.rlStatistics)
    RelativeLayout rlStatistics;
    @BindView(R.id.tvSuccessValue)
    public TextView tvSuccessValue;
    @BindView(R.id.tvFailureValue)
    public TextView tvFailureValue;

    @BindView(R.id.rlBase)
    RelativeLayout rlBase;
    @BindView(R.id.timer)
    public TextView timer;
    @BindView(R.id.word)
    public TextView word;

    @Inject
    SensorsManage sensorsManage;

    public GameFrillsController(long time){
        super(new BundleBuilder(new Bundle()).putLong(KEY_TIME,time).build());
        gestureOrientation = new GestureOrientation(spatialGestureListener);
        this.time = time;
    }

    public GameFrillsController(){

    }


    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        if(isAttached()){
            time = getArgs().getLong(KEY_TIME);
            getPresenter().startGame(time);
        }
    }



    SensorListener listener = new SensorListener() {
        @Override
        public void OnChangeSensorData(MySensorEvent e) {
            if(gestureOrientation==null){
                gestureOrientation = new GestureOrientation(spatialGestureListener);
            }
            gestureOrientation.updateComponentsWithPrepareAngles(e.getValues()[0],e.getValues()[1],e.getValues()[2]);
        }
    };

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.screen_game_frills,container,false);
    }

    @Override
    protected void viewBound(View view) {
        MyApplication.getAppComponent().inject(this);
        sensorsManage.initOrientationBundle();
        sensorsManage.registerListenerSensor(SensorsManage.TYPE_ORIENTATION,listener);
        rlBase.setOnTouchListener(touchListener);
        rlBase.setBackgroundColor(transparent);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().startGame(time);
            }
        });
    }

    @NonNull
    @Override
    public GameFrillsPresenter createPresenter() {
        return new GameFrillsPresenter();
    }


    @Override
    public void startGame() {
        inGame = true;
        visibleTimerAndText(true);
        finishLayout.setVisibility(View.GONE);
        rlStatistics.setVisibility(View.GONE);
        rlBase.setBackgroundColor(transparent);
    }

    @Override
    public void updateTimerText(String time) {
        timer.setText(time);
    }

    @Override
    public void updateWord(String word) {
        this.word.setText(word);
        rlBase.setBackgroundColor(transparent);
    }

    @Override
    public void finishGame(int success, int failure) {
        inGame = false;
        finishLayout.setVisibility(View.VISIBLE);
        rlStatistics.setVisibility(View.VISIBLE);
        tvSuccessValue.setText(success+"");
        tvFailureValue.setText(failure+"");
        visibleTimerAndText(false);
    }

    void visibleTimerAndText(boolean state){
        int visible = state ? View.VISIBLE : View.GONE;
        word.setVisibility(visible);
        timer.setVisibility(visible);
    }
}
