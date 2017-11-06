package com.zhq.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhq.R;
import com.zhq.base.adapterbase.BaseQuickAdapter;
import com.zhq.base.adapterbase.BaseViewHolder;
import com.zhq.bean.ConferencePersonBean;
import com.zhq.utils.GlideCircleTransform;
import com.zhq.utils.MLog;

import java.util.List;

/**
 * 我的-我的评论
 * <p>
 * 多样式布局 暂定单一布局
 */

public class ConferenceListRecylerAdapter extends BaseQuickAdapter<ConferencePersonBean.PersonBean, BaseViewHolder> {

    private Context mContext;
    private List<ConferencePersonBean.PersonBean> dataList;

    public ConferenceListRecylerAdapter(Context context, List<ConferencePersonBean.PersonBean> list) {
        super(R.layout.item_person, list);
        dataList = list;
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, ConferencePersonBean.PersonBean bean) {
        //图片显示
        ImageView img = holder.getView(R.id.person_img);
        String imgPath = bean.getImagePathYun();
        Glide.with(mContext)
                .load(imgPath)
                .error(ContextCompat.getDrawable(mContext, R.mipmap.detail_default_pic))
                .transform(new GlideCircleTransform(mContext))//自定义圆形图片
                .into(img);
        //签到
        TextView tv_time = holder.getView(R.id.person_time);
        if (bean.getIsSign() != null || !bean.getIsSign().isEmpty()) {
            if (bean.getIsSign().contains("0")) {
                MLog.d("未签到");
                tv_time.setText("未签到");
            } else if (bean.getIsSign().contains("1")) {
                MLog.d("签到时间");
                tv_time.setText("签到时间：" + bean.getSignTime());
            } else {
                tv_time.setText("未签到");
            }
        } else {
            MLog.d("未签到null");
            tv_time.setText("未签到");
        }


        //关注
        CheckBox img_care = holder.getView(R.id.img_care);
        if (bean.getIsCare() == 0) {
            img_care.setChecked(false);
            //            ContextCompat.getDrawable(mContext, R.mipmap.detail_unchoose);
        } else {
            img_care.setChecked(true);
            //            ContextCompat.getDrawable(mContext, R.mipmap.detail_choose);
        }

        holder.setText(R.id.person_name, bean.getEmployeeName())//用户名
                .addOnClickListener(R.id.img_care);


    }

}
