package com.veyron.www.viewflipperdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.veyron.www.viewflipperdemo.View.UpView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veyron on 2017/2/20.
 * Function：主界面，加载布局
 */
public class MainActivity extends AppCompatActivity {
        private UpView upview1;   //自定义的ViewFlipper
        private ViewFlipper mViewFlipper;  //直接使用该控件

        List<String> data = new ArrayList<>();   //文字数据集合
        List<View> views = new ArrayList<>();    //滚动的view集合

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initdata();
            initView();
        }

    private void initView() {
        //自定义的
        upview1 = (UpView) findViewById(R.id.upview1);
        setView();
        upview1.setViews(views);//给自定义的ViewFlipper设置滚动的view布局


        //非自定义的，直接使用控件的
        mViewFlipper = (ViewFlipper) findViewById(R.id.upview2);
        mViewFlipper.addView(View.inflate(this, R.layout.view1, null));
        mViewFlipper.addView(View.inflate(this, R.layout.view2, null));
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_view, null);
            //初始化布局里面的控件,只要设置这两个控件的监听就达到目的
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,  data.get(position).toString(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG",data.get(position).toString());
                    /**
                     * 添加业务代码
                     */
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,  data.get(position+1).toString(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG",data.get(position+1).toString());
                    /**
                     * 添加业务代码
                     */
                }
            });


            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);   //也就是滚动的item_view
        }
    }
    /**
     * 初始化数据
     */
    private void initdata() {
        data = new ArrayList<>();
        data.add("美剧《行尸走肉》上线Steam 每一集售价2.99...");
        data.add("2017四月新番动画全预览！你期待那部");
        data.add("生娃后，老公有过这些举动，你却没加错人！");
        data.add("汽车开空调耗油？只因为按错了一个键");
        data.add("心疼S7 edge 三星官方‘虐机’视频上线");
    }
}
