package com.veyron.www.viewflipperdemo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.veyron.www.viewflipperdemo.R;

import java.util.List;

/**
 * Created by Veyron on 2017/2/20.
 * Function：自定义ViewFlipper控件
 */

public class UpView extends ViewFlipper {

    private Context mContext;
    private boolean isSetAnimDuration = false;
    private int interval = 2000;
    /**
     * 动画时间
     */
    private int animDuration = 500;

    public UpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        setFlipInterval(interval);//设置时间间隔2000毫秒
        //进来的动画
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_in);
        if (isSetAnimDuration)
            animIn.setDuration(animDuration);
        setInAnimation(animIn);
        //退出的动画
        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_out);
        if (isSetAnimDuration)
            animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */
    public void setViews(final List<View> views) {
        if (views == null || views.size() == 0) return;
        removeAllViews();
        for ( int i = 0; i < views.size(); i++) {
            final int position=i;
            addView(views.get(i));
        }
        startFlipping();    //开启翻滚
    }
}
