package com.zhq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.zhq.base.BaseActivity;
import com.zhq.inter_face.OnConferenceListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sjy on 2017/10/19.
 */

public class MeetingDetailActivity extends BaseActivity implements OnConferenceListener {

    @BindView(R.id.tv_back)
    TextView tv_back;

    @BindView(R.id.detail_title)
    TextView detail_title;

    @BindView(R.id.swipRefreshLayout)
    SwipeRefreshLayout swipRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail);
        ButterKnife.bind(this);
        initMyView();
    }


    /**
     * ======================================================================================================
     * ==================================================显示====================================================
     * ======================================================================================================
     */
    @OnClick({R.id.tv_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
        }
    }

    private void initMyView() {

    }

    private void initShow() {

    }

    private void initRefreshShow() {

    }

    private void initMoreShow() {

    }

    /**
     * ======================================================================================================
     * ==================================================接口数据====================================================
     * ======================================================================================================
     */
    /**
     * ======================================================================================================
     * ==================================================接口回调====================================================
     * ======================================================================================================
     */

    @Override
    public void onListSuccess(String code, String msg, Object obj) {

    }

    @Override
    public void onListFailed(String code, String msg, Exception e) {

    }

    @Override
    public void onRefreshSuccess(String code, String msg, Object obj) {

    }

    @Override
    public void onRefreshFailed(String code, String msg, Exception e) {

    }

    @Override
    public void onMoreSuccess(String code, String msg, Object obj) {

    }

    @Override
    public void onMoreFailed(String code, String msg, Exception e) {

    }

    @Override
    public void onCareSuccess(String code, String msg, Object obj) {

    }

    @Override
    public void onCareFailed(String code, String msg, Exception e) {

    }

    @Override
    public void onCarelessSuccess(String code, String msg, Object obj) {

    }

    @Override
    public void onCarelessFailed(String code, String msg, Exception e) {

    }
}
