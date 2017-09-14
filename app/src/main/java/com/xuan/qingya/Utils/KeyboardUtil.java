package com.xuan.qingya.Utils;

import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zhouzhixuan on 2017/9/14.
 */

public class KeyboardUtil {
    public static void hideKeyboard(InputMethodManager inputMethodManager, IBinder iBinder) {
        inputMethodManager.hideSoftInputFromWindow(iBinder, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
