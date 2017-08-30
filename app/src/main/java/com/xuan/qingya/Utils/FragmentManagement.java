package com.xuan.qingya.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentManagement {

    private static FragmentTransaction transaction;
    private static List<Fragment> fragmentCollection = new ArrayList<>();

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void replaceFragmentWithSharedElement(FragmentManager fragmentManager, Fragment fragment, int frameId, HashMap<View, String> sharedElements) {
        transaction = fragmentManager.beginTransaction();

        if (sharedElements != null) {
            for (View sharedView : sharedElements.keySet()) {
                transaction.addSharedElement(sharedView, sharedElements.get(sharedView));
            }
        }

        transaction.addToBackStack(null);
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    public static void switchFragment(FragmentManager fragmentManager, Fragment fragment, int frameId,boolean isAddToBackStack) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId,fragment);
        if(isAddToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}