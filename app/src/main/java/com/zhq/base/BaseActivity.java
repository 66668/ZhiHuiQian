package com.zhq.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.zhq.dialog.LoadingDialog;
import com.zhq.utils.MLog;

import java.util.List;

/**
 * actvitiy基类
 */

public class BaseActivity extends AppCompatActivity {
    //自定义弹窗 加载
    public LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        //弹窗显示登录状态
        loadingDialog = new LoadingDialog(BaseActivity.this);
        loadingDialog.setCanceledOnTouchOutside(false);//弹窗之外触摸无效
        loadingDialog.setCancelable(true);//true:可以按返回键back取消

        //
    }

    /**
     * 用于处理 fragment多层嵌套的无返回处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int index = 0; index < fragmentManager.getFragments().size(); index++) {
            Fragment fragment = fragmentManager.getFragments().get(index); //找到第一层Fragment
            if (fragment == null)
                MLog.e("Activity result no fragment exists for index: 0x" + Integer.toHexString(requestCode));
            else
                handleResult(fragment, requestCode, resultCode, data);
        }
    }

    /**
     * 递归调用，对所有的子Fragment生效
     *
     * @param fragment
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
        fragment.onActivityResult(requestCode, resultCode, data);//调用每个Fragment的onActivityResult
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments(); //找到第二层Fragment
        if (childFragment != null)
            for (Fragment f : childFragment)
                if (f != null) {
                    handleResult(f, requestCode, resultCode, data);
                }
        if (childFragment == null)
            MLog.e("BaseActivity");
    }

    /**
     * act页面跳转简化操作
     *
     * @param newClass
     */
    public void startActivity(Class<?> newClass) {
        Intent intent = new Intent(this, newClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // (2)
    public void startActivity(Class<?> newClass, Bundle extras) {
        Intent intent = new Intent(this, newClass);
        intent.putExtras(extras);
        //退出多个Activity的程序
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * =================================================
     * ================设置异步handler操作==================
     * =================================================
     */
    public static final int MESSAGE_TOAST = 1001;
    @SuppressLint("HandlerLeak")
    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            BaseActivity.this.handleMessage(msg);
        }
    };

    /**
     * @param msg
     */
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_TOAST:
                break;
            default:
                break;
        }
    }
}
