package ru.words.gunferzs.words.screens.game_frills;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by GunFerzs on 04.09.2017.
 */

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class GameLogic {
    ArrayList<String> newWords = new ArrayList<>();
    ArrayList<String> oldWords = new ArrayList<>();
    Random random = new Random();

    public GameLogic(ArrayList<String> newWords) {
        this.newWords.addAll(newWords);
    }

    public String getRandomWord(){
        String result;
        int count = newWords.size();
        if(count == 1){
            result = newWords.get(0);
            newWords.addAll(oldWords);
            oldWords.clear();
        }else{
            int index = random.nextInt(count);
            result = newWords.get(index);
            oldWords.add(result);
            newWords.remove(index);
        }
        return result;
    }
}
