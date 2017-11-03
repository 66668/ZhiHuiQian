package com.zhq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhq.adapter.ConferenceListRecylerAdapter;
import com.zhq.base.BaseActivity;
import com.zhq.base.adapterbase.BaseQuickAdapter;
import com.zhq.bean.ConferencePersonBean;
import com.zhq.inter_face.OnConferenceListener;
import com.zhq.presenter.ConferencePresenterImpl;
import com.zhq.utils.MLog;
import com.zhq.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sjy on 2017/10/19.
 */

public class MeetingDetailActivity extends BaseActivity implements OnConferenceListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.tv_back)
    ImageView tv_back;

    @BindView(R.id.detail_title)
    TextView detail_title;

    @BindView(R.id.tv_location)
    TextView tv_location;

    @BindView(R.id.totolNum)
    TextView totolNum;

    @BindView(R.id.tv_signed)
    TextView tv_signed;

    @BindView(R.id.tv_unsigned)
    TextView tv_unsigned;

    @BindView(R.id.swipRefreshLayout)
    SwipeRefreshLayout swipRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ConferencePersonBean bean;
    private List<ConferencePersonBean.PersonBean> listData;

    private ConferenceListRecylerAdapter adapter;
    private ConferencePresenterImpl presenter;
    private int size = 20;
    private String conferenceID;
    private String conferenceName;
    private String minTime = "";
    private String maxTime = "";
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail);
        ButterKnife.bind(this);
        initMyView();
        if (!conferenceID.isEmpty()) {
            loadData();
        } else {
            ToastUtil.ToastShort(this, "会议id为空，无法获取数据！");
        }
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
        getIntentData();


        presenter = new ConferencePresenterImpl(this, this);
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

    private void getIntentData() {
        conferenceID = getIntent().getExtras().getString("id", "");
        conferenceName = getIntent().getExtras().getString("title", "");
        detail_title.setText(conferenceName);
    }
    private void initShow() {
        adapter = new ConferenceListRecylerAdapter(this, listData);
        adapter.setOnLoadMoreListener(this, recyclerView);
        //item监听
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ConferencePersonBean.PersonBean bean = (ConferencePersonBean.PersonBean) adapter.getItem(position);
                String employeeID = bean.getEmployeeID();
                CheckBox box = (CheckBox) view;
                if (box.isChecked() == true) {
                    carePersonPost(employeeID);
                    box.setChecked(true);
                } else {
                    CarelessPost(employeeID);
                    box.setChecked(false);
                }

            }
        });

        if (listData != null) {
            if (listData.size() <= 0) {
                ToastUtil.ToastShort(this, "暂时人员记录！");
                adapter.loadMoreEnd();
            } else if (listData.size() < 20 && listData.size() > 0) {
                //数据全部加载完毕，显示 没有更多数据
                adapter.loadMoreEnd();
            } else {
                //本次加载结束，再次加载仍可用
                adapter.loadMoreComplete();
            }
        } else {
            ToastUtil.ToastShort(this, "暂时人员记录！");
            adapter.loadMoreEnd();
        }
        recyclerView.setAdapter(adapter);
        //
        detail_title.setText(bean.getConferenceName());
        tv_location.setText(bean.getLocation() + "人");
        totolNum.setText(bean.getEmpCount() + "人");
        tv_signed.setText(bean.getSignCount() + "人");
        tv_unsigned.setText(bean.getNosignCount() + "人");
        //
        getMinMaxTime();
    }

    private void initRefreshShow() {
        adapter.setNewData(listData);
        if (listData != null) {
            if (listData.size() <= 0) {
                ToastUtil.ToastShort(this, "暂时人员记录！");
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
            ToastUtil.ToastShort(this, "暂时人员记录！");
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
     * 首次加载空数据，显示空白view
     */
    private void setEmptyRecyclerView() {
        listData = new ArrayList<>();
        adapter = new ConferenceListRecylerAdapter(this, listData);
//        adapter.setEmptyView();

    }

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
        RefreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        MoreData();
    }

    private void loadData() {
        loadingDialog.show();
        presenter.pGetData("", "", size, conferenceID);
    }

    private void RefreshData() {
        swipRefreshLayout.setRefreshing(true);
        presenter.pRefreshData("", "", size, conferenceID);
    }

    private void MoreData() {
        presenter.pMoreData("", minTime, size, conferenceID);

    }

    private void carePersonPost(String employeeID) {
        loadingDialog.show();
        presenter.pPostCare(employeeID);
    }

    private void CarelessPost(String employeeID) {
        loadingDialog.show();
        presenter.pPostCareless(employeeID);

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
            bean = (ConferencePersonBean) obj;
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
        if (code.contains("0")) {
            setEmptyRecyclerView();
        }
    }

    @Override
    public void onRefreshSuccess(String code, String msg, Object obj) {
        swipRefreshLayout.setRefreshing(false);
        if (code.contains("1")) {
            listData = (List<ConferencePersonBean.PersonBean>) obj;
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
            listData = (List<ConferencePersonBean.PersonBean>) obj;
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

    @Override
    public void onCareSuccess(String code, String msg, Object obj) {
        loadingDialog.dismiss();
        ToastUtil.ToastShort(this,msg);
    }

    @Override
    public void onCareFailed(String code, String msg, Exception e) {
        loadingDialog.dismiss();
        ToastUtil.ToastShort(this,msg);
    }

    @Override
    public void onCarelessSuccess(String code, String msg, Object obj) {
        loadingDialog.dismiss();
        ToastUtil.ToastShort(this,msg);
    }

    @Override
    public void onCarelessFailed(String code, String msg, Exception e) {
        loadingDialog.dismiss();
        ToastUtil.ToastShort(this,msg);
    }


}
