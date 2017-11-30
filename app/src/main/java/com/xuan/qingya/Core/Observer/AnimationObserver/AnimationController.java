package com.xuan.qingya.Core.Observer.AnimationObserver;

import java.util.ArrayList;

/**
 * Created by zhouzhixuan on 2017/9/28.
 */

public class AnimationController implements AnimationObserverContract.AnimationObservable<AnimationObserverContract.AnimationObserver> {

    private static ArrayList<AnimationObserverContract.AnimationObserver> observers = new ArrayList<>();
    private static int type = 0;
    private static AnimationController controller = new AnimationController();

    private AnimationController() {
    }

    public static AnimationController getInstance() {
        return controller;
    }

    @Override
    public void addObserver(AnimationObserverContract.AnimationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(AnimationObserverContract.AnimationObserver observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        if (type == 0) {
            for (int i = 0; i < observers.size(); i++) {
                AnimationObserverContract.AnimationObserver observer = observers.get(i);
                observer.doEnterAnimation();
            }
        } else {
            for (int i = 0; i < observers.size(); i++) {
                AnimationObserverContract.AnimationObserver observer = observers.get(i);
                observer.doExitAnimation();
            }
        }
    }

    public void cleanObservers() {
        observers.clear();
    }

    @Override
    public void doAnimation(String animationType) {
        if (animationType.equals("ENTER")) {
            type = 0;
        } else {
            type = 1;
        }
        notifyObservers();
    }
}
