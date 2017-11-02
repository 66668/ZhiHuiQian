package com.zhq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.zhq.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sjy on 2017/10/19.
 */

public class MeetingDetailActivity extends BaseActivity {

    @BindView(R.id.swipRefreshLayout)
    SwipeRefreshLayout swipRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail);
        ButterKnife.bind(this);
        initMyView();
    }


    private  void initMyView() {

    }
}
