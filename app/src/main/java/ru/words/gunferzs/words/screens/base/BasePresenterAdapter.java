package ru.words.gunferzs.words.screens.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GunFerzs on 28.08.2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BasePresenterAdapter<M, V> {

    protected List<M> model;

    public BasePresenterAdapter() {
        this.model = new ArrayList<>();
    }

    public List<M> getModel() {
        return model;
    }

    public void setModel(List<M> model) {
        this.model = model;
    }

    public abstract void updateView(V view, int position);

    public abstract int size();
}
