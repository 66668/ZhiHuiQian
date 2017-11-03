package com.zhq;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhq.adapter.MainListRecylerAdapter;
import com.zhq.base.BaseActivity;
import com.zhq.base.Constants;
import com.zhq.base.adapterbase.BaseQuickAdapter;
import com.zhq.bean.MainBean;
import com.zhq.inter_face.OnMainListener;
import com.zhq.presenter.MainPresenterImpl;
import com.zhq.utils.MLog;
import com.zhq.utils.SPUtil;
import com.zhq.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements OnMainListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.tv_welcome)
    TextView tv_welcome;

    @BindView(R.id.tv_number)
    TextView tv_number;

    @BindView(R.id.tv_conferenceName)
    TextView tv_conferenceName;

    @BindView(R.id.swipRefreshLayout)
    SwipeRefreshLayout swipRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MainPresenterImpl presenter;
    private LinearLayoutManager manager;
    private MainBean bean;
    private List<MainBean.ConferenceItemBean> listData;
    private MainListRecylerAdapter adapter;
    private String storeID;
    private String storeUserID;
    private String minTime = "";
    private String maxTime = "";
    private int size = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        initMyView();
        loadData();
    }

    /**
     * 设置透明状态栏
     * //flag的详细用法见Readme讲解
     *
     * @param hasFocus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();

            //设置自动隐藏+手动显示的透明状态栏
            //            decorView.setSystemUiVisibility(
            //                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            //                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            //                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
            //                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
            //                            | View.SYSTEM_UI_FLAG_IMMERSIVE);


            //设置固定 透明状态栏
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * ======================================================================================================
     * ==================================================显示====================================================
     * ======================================================================================================
     */
    private void initMyView() {

        presenter = new MainPresenterImpl(this, this);
        minTime = "";
        maxTime = "";
        storeID = SPUtil.getString(Constants.SOTOREID, "");
        //显示登录人
        tv_welcome.setText("欢迎您， " + SPUtil.getUserName());
        //
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        //设置SwipeRefreshLayout样式
        swipRefreshLayout.setOnRefreshListener(this);
        //颜色变化
        swipRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.log_hint_color)
                , ContextCompat.getColor(this, R.color.log_color)
                , ContextCompat.getColor(this, R.color.log_hint_color)
                , ContextCompat.getColor(this, R.color.log_color));
    }

    private void initShow() {


        adapter = new MainListRecylerAdapter(this, listData);
        adapter.setOnLoadMoreListener(this, recyclerView);
        //item监听 详情会议跳转
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MainBean.ConferenceItemBean bean = (MainBean.ConferenceItemBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("id", bean.getConferenceID());
                bundle.putString("title", bean.getConferenceName());
                startActivity(MeetingDetailActivity.class, bundle);
            }
        });

        if (listData != null) {
            if (listData.size() <= 0) {
                ToastUtil.ToastShort(this, "暂时没有会议记录！");
                adapter.loadMoreEnd();
            } else if (listData.size() < 20 && listData.size() > 0) {
                //数据全部加载完毕，显示 没有更多数据
                adapter.loadMoreEnd();
            } else {
                //本次加载结束，再次加载仍可用
                adapter.loadMoreComplete();
            }
        } else {
            ToastUtil.ToastShort(this, "暂时没有会议记录！");
            adapter.loadMoreEnd();
        }
        recyclerView.setAdapter(adapter);
        //
        tv_number.setText(bean.getConferCount() + "");
        tv_conferenceName.setText("最近的一场是:   " + bean.getNewestConfer());
        //
        getMinMaxTime();


    }

    private void initRefreshShow() {
        adapter.setNewData(listData);
        if (listData != null) {
            if (listData.size() <= 0) {
                ToastUtil.ToastShort(this, "暂时没有会议记录！");
                adapter.loadMoreEnd();
            } else if (listData.size() < 20 && listData.size() > 0) {
                //数据全部加载完毕，显示 没有更多数据
                adapter.loadMoreEnd();
            } else {
                //本次加载结束，再次加载仍可用
                adapter.loadMoreComplete();
                adapter.setEnableLoadMore(true);//允许加载
            }
        } else {
            ToastUtil.ToastShort(this, "暂时没有会议记录！");
            adapter.loadMoreEnd();
        }

        getMinMaxTime();
    }

    private void initMoreShow() {


        if (listData != null) {
            if (listData.size() <= 0) {
                adapter.loadMoreEnd();
            } else if (listData.size() < 20 && listData.size() > 0) {
                //数据全部加载完毕，显示 没有更多数据
                adapter.loadMoreEnd();
            } else {
                //本次加载结束，再次加载仍可用
                adapter.loadMoreComplete();
                adapter.setEnableLoadMore(true);//允许加载
            }
        } else {
            //本次加载结束，再次加载仍可用
            adapter.loadMoreEnd();
        }
        adapter.addData(listData);
        getMinMaxTime();
    }

    /**
     * ======================================================================================================
     * ==================================================私有方法====================================================
     * ======================================================================================================
     */
    private void getMinMaxTime() {
        if (listData.size() <= 0) {
            return;
        } else {
            int size = listData.size();
            minTime = listData.get(size - 1).getCreateTime();
            maxTime = listData.get(0).getCreateTime();

            if (minTime == null) {
                minTime = "";
            }

            if (maxTime == null) {
                maxTime = "";
            }
        }

    }

    /**
     * ======================================================================================================
     * ==================================================接口数据====================================================
     * ======================================================================================================
     */
    @Override
    public void onRefresh() {
        minTime = "";
        maxTime = "";
        refreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        moreData();
    }

    private void loadData() {
        loadingDialog.show();
        presenter.pGetData("", "", size, storeID);

    }

    private void moreData() {
        presenter.pMoreData("", minTime, size, storeID);

    }

    private void refreshData() {
        swipRefreshLayout.setRefreshing(true);
        presenter.pRefreshData("", "", size, storeID);

    }

    /**
     * ======================================================================================================
     * ==================================================接口回调====================================================
     * ======================================================================================================
     */
    @Override
    public void onListSuccess(String code, String msg, Object obj) {
        loadingDialog.dismiss();
        if (code.contains("1")) {
            bean = (MainBean) obj;
            listData = bean.getObj();
            initShow();
        } else {
            ToastUtil.ToastShort(this, msg);
        }

    }

    @Override
    public void onListFailed(String code, String msg, Exception e) {
        loadingDialog.dismiss();
        ToastUtil.ToastShort(this, msg);
    }

    @Override
    public void onRefreshSuccess(String code, String msg, Object obj) {
        swipRefreshLayout.setRefreshing(false);
        if (code.contains("1")) {
            listData = (List<MainBean.ConferenceItemBean>) obj;
            initRefreshShow();
            MLog.e("onRefreshSuccess", code + msg);
        } else if (code.contains("0")) {
            listData = new ArrayList<>();
            initRefreshShow();
            MLog.e("onRefreshSuccess", code + msg);
        } else {
            listData = new ArrayList<>();
            initRefreshShow();
            MLog.e(msg);
        }
    }

    @Override
    public void onRefreshFailed(String code, String msg, Exception e) {
        swipRefreshLayout.setRefreshing(false);
        if (code.contains("0")) {
            listData = new ArrayList<>();
            initRefreshShow();
            MLog.e("onRefreshFailed1", code + msg + e.toString());

        } else {
            listData = new ArrayList<>();
            initRefreshShow();
            MLog.e(msg);
            MLog.e("onRefreshFailed2", code + msg + e.toString());
        }
    }

    @Override
    public void onMoreSuccess(String code, String msg, Object obj) {
        if (code.contains("1")) {
            listData = (List<MainBean.ConferenceItemBean>) obj;
            initMoreShow();
            MLog.e("onMoreSuccess1" + msg);

        } else if (code.contains("0")) {
            listData = new ArrayList<>();
            initMoreShow();
            MLog.e("onMoreSuccess2" + msg);
        } else {
            listData = new ArrayList<>();
            initMoreShow();
            MLog.e("onMoreSuccess3" + msg);
        }

    }

    @Override
    public void onMoreFailed(String code, String msg, Exception e) {
        if (code.contains("0")) {
            listData = new ArrayList<>();
            initMoreShow();
            MLog.e("onMoreFailed" + msg);
        } else {
            listData = new ArrayList<>();
            initMoreShow();
            MLog.e("onMoreFailed2" + msg);
        }
    }

}
