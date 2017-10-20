package com.zhq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhq.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sjy on 2017/10/19.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_co)
    EditText et_co;

    @BindView(R.id.et_user)
    EditText et_user;

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.tv_log)
    TextView tv_log;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_log)
    public void onLoginClick(View view) {
        switch (view.getId()) {
            case R.id.tv_log:
                startActivity(MainActivity.class);
                break;
        }
    }

//    /**
//     * 登录页做沉浸样式
//     *
//     * @param hasFocus
//     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//
//    }
}
