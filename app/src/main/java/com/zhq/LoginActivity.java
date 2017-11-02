package com.zhq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhq.base.BaseActivity;
import com.zhq.base.Constants;
import com.zhq.base.MyApplication;
import com.zhq.bean.LoginBean;
import com.zhq.inter_face.OnCommonListener;
import com.zhq.presenter.LoginPresenterImpl;
import com.zhq.utils.SPUtil;
import com.zhq.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */

public class LoginActivity extends BaseActivity implements OnCommonListener {

    @BindView(R.id.et_co)
    EditText et_co;

    @BindView(R.id.et_user)
    EditText et_user;

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.tv_log)
    TextView tv_log;
    private LoginBean bean;
    private LoginPresenterImpl presenter;
    private String strCo;
    private String strUser;
    private String strPs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
        initMyView();
    }

    private void initMyView() {
        presenter = new LoginPresenterImpl(this, this);

        //获取保存数据
        et_co.setText(SPUtil.getUserCo());
        et_user.setText(SPUtil.getUserName());
        et_password.setText(SPUtil.getPassword());
    }

    @OnClick(R.id.tv_log)
    public void onLoginClick(View view) {
        switch (view.getId()) {
            case R.id.tv_log:
                getEtData();
                if (isNull()) {
                    return;
                }
                postLogData();

                break;
        }
    }

    private void getEtData() {
        strCo = et_co.getText().toString().trim();
        strUser = et_user.getText().toString().trim();
        strPs = et_password.getText().toString().trim();
    }

    private boolean isNull() {
        if (strCo.isEmpty()) {
            ToastUtil.ToastShort(this, "公司账号不为空！");
            return true;
        }
        if (strUser.isEmpty()) {
            ToastUtil.ToastShort(this, "用户账号不为空！");
            return true;
        }
        if (strPs.isEmpty()) {
            ToastUtil.ToastShort(this, "密码不为空！");
            return true;
        }

        return false;
    }

    private void initLogSuccess() {
        ToastUtil.ToastShort(LoginActivity.this, "登录成功！");
        startActivity(MainActivity.class);

        //保存状态
        SPUtil.setIsLogin(true);
        SPUtil.setPassword(strPs);
        SPUtil.setUserName(strUser);
        SPUtil.setUserCo(strCo);
        //保存返回值
        SPUtil.putString(Constants.SOTOREID, bean.getStoreID());
        SPUtil.putString(Constants.SOTOREUSERID, bean.getStoreUserId());

        MyApplication.getInstance().setLogin(true);

        this.finish();
    }

    private void postLogData() {
        loadingDialog.show();
        presenter.pLogin(strCo, strUser, strPs);
    }

    @Override
    public void onDataSuccess(String code, String msg, Object obj) {
        loadingDialog.dismiss();
        if (code.contains("1")) {
            bean = (LoginBean) obj;
            initLogSuccess();
        } else if (code.contains("0")) {
            ToastUtil.ToastShort(LoginActivity.this, msg);
        }
    }

    @Override
    public void onDataFailed(String code, String msg, Exception e) {
        loadingDialog.dismiss();
    }

}
