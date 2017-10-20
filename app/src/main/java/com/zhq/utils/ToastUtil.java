package com.zhq.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * 弹窗util
 */

public class ToastUtil {


    //toast弹窗提示
    public static void toastLong(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    //toast弹窗提示
    public static void ToastShort(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

//    //View的顶部Snackbar显示
//    public static void SnackbarShow(@NonNull View view, String content) {
//        Snackbar.make(view, "加载数据失败！", Snackbar.LENGTH_SHORT).show();//底部提示
//    }
//
//    // 自定义toast
//    public static void toastInMiddle(Context context, String str) {
//        LayoutInflater inflater = LayoutInflater.from(MyApplication.getInstance());
//        View view = inflater.inflate(R.layout.toast_in_middle, null);
//        TextView chapterNameTV = (TextView) view.findViewById(R.id.toast);
//        chapterNameTV.setText(str);
//
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER, 0, 50);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setView(view);
//        toast.show();
//    }
}