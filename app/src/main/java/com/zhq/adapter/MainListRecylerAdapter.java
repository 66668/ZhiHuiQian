package com.zhq.adapter;

import android.content.Context;

import com.zhq.R;
import com.zhq.base.adapterbase.BaseQuickAdapter;
import com.zhq.base.adapterbase.BaseViewHolder;
import com.zhq.bean.MainBean;
import com.zhq.utils.MLog;

import java.util.List;

/**
 * 我的-我的评论
 * <p>
 * 多样式布局 暂定单一布局
 */

public class MainListRecylerAdapter extends BaseQuickAdapter<MainBean.ConferenceItemBean, BaseViewHolder> {

    private Context mContext;
    private List<MainBean.ConferenceItemBean> dataList;

    public MainListRecylerAdapter(Context context, List<MainBean.ConferenceItemBean> list) {
        super(R.layout.item_conference, list);
        dataList = list;
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, MainBean.ConferenceItemBean bean) {
        MLog.d(bean.getConferenceName());

        holder.setText(R.id.conferenceName, bean.getConferenceName())//用户名
                .addOnClickListener(R.id.layout_item);//监听

    }

}
