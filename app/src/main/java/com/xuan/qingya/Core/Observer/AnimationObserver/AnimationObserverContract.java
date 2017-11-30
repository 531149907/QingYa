package com.xuan.qingya.Core.Observer.AnimationObserver;

/**
 * Created by zhouzhixuan on 2017/9/27.
 */

public interface AnimationObserverContract {
    interface AnimationObservable<T> {
        void addObserver(T observer);

        void deleteObserver(T observer);

        void notifyObservers();

        void doAnimation(String animationType);
    }

    interface AnimationObserver {
        void doExitAnimation();

        void doEnterAnimation();
    }
}
