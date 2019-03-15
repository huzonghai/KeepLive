package com.keep.live.onePx;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 一像素act
 */
public class OnePxActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams attrParams = window.getAttributes();
        attrParams.x = 0;
        attrParams.y = 0;
        attrParams.width = 1;
        attrParams.width = 1;
        window.setAttributes(attrParams);

        //传入act引用
        KeepManger.getInstance().setKeepActivty(this);
    }

}
