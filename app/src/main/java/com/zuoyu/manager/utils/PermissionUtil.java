package com.zuoyu.manager.utils;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.zuoyu.manager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <pre>
 * Function：Android6.0以上系统权限动态申请工具类
 *
 * 1.checkSelfPermission：检查是否拥有这个权限
 * 2.requestPermissions：请求权限，一般会弹出一个系统对话框，询问用户是否开启这个权限。
 * 3.shouldShowRequestPermissionRationale：Android原生系统中，如果第二次弹出权限申请的对话框，
 *          会出现“以后不再弹出”的提示框，如果用户勾选了，你再申请权限，
 *          则shouldShowRequestPermissionRationale返回true，意思是说要给用户一个解释，告诉用户为什么要这个权限。
 *          然而，在实际开发中，需要注意的是，很多手机对原生系统做了修改，
 *          比如小米，小米4的6.0的shouldShowRequestPermissionRationale 就一直返回false，
 *          而且在申请权限时，如果用户选择了拒绝，则不会再弹出对话框了。。。。
 *          所以说这个地方有坑，我的解决方法是，在回调里面处理，如果用户拒绝了这个权限，则打开本应用信息界面，由用户自己手动开启这个权限。
 * 4.每个应用都有自己的权限管理界面，里面有本应用申请的权限以及各种状态，即使用户已经同意了你申请的权限，他也随时可以关闭
 *
 *
 * Created by JoannChen on 2017/5/15 11:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class PermissionUtil {

    /**
     * 权限码
     */
    public static final int CODE_RECORD_AUDIO = 0;
    public static final int CODE_GET_ACCOUNTS = 1;
    public static final int CODE_READ_PHONE_STATE = 2;
    public static final int CODE_CALL_PHONE = 3;
    public static final int CODE_CAMERA = 4;
    public static final int CODE_ACCESS_FINE_LOCATION = 5;
    public static final int CODE_ACCESS_COARSE_LOCATION = 6;
    public static final int CODE_READ_EXTERNAL_STORAGE = 7;
    public static final int CODE_WRITE_EXTERNAL_STORAGE = 8;
    private static final int CODE_MULTI_PERMISSION = 100;


    /**
     * 权限名称
     */
    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    private static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    private static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    private static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE; // 拨打电话
    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;// 相机
    private static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private static final String[] requestPermissions = {
            PERMISSION_RECORD_AUDIO,
            PERMISSION_GET_ACCOUNTS,
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_CALL_PHONE,
            PERMISSION_CAMERA,
            PERMISSION_ACCESS_FINE_LOCATION,
            PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE
    };

    public interface PermissionGrant {
        void onPermissionGranted(int requestCode);
    }

    /**
     * Requests permission.
     *
     * @param activity    当前Activity
     * @param requestCode request code, e.g. if you need request CAMERA permission,parameters is PermissionUtil.CODE_CAMERA
     */
    public static void requestPermission(final Activity activity, final int requestCode, PermissionGrant permissionGrant) {
        if (activity == null) {
            return;
        }

        LogUtil.i("requestPermission requestCode:" + requestCode);
        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            LogUtil.w("requestPermission illegal requestCode:" + requestCode);
            return;
        }

        final String requestPermission = requestPermissions[requestCode];

        //如果是6.0以下的手机，ActivityCompat.checkSelfPermission()会始终等于PERMISSION_GRANTED，
        // 但是，如果用户关闭了你申请的权限，ActivityCompat.checkSelfPermission(),会导致程序崩溃(java.lang.RuntimeException: Unknown exception code: 1 msg null)，
        // 你可以使用try{}catch(){},处理异常，也可以判断系统版本，低于23就不申请权限，直接做你想做的。permissionGrant.onPermissionGranted(requestCode);
//        if (Build.VERSION.SDK_INT < 23) {
//            permissionGrant.onPermissionGranted(requestCode);
//            return;
//        }

        int checkSelfPermission;
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
        } catch (RuntimeException e) {
            LogUtil.e("RuntimeException:" + e.getMessage());
            return;
        }

        // 检查权限是否授予
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            LogUtil.d("检查权限结果：此权限未被授予！");

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {

                LogUtil.d("检查权限结果：此权限之前已被用户禁止，提示用户打开本应用信息界面，由用户自己手动开启这个权限！");
                String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
                showMessageDialog(activity, permissionsHint[requestCode],
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{requestPermission}, requestCode);
                            }
                        });
            } else {
                LogUtil.d("检查权限结果：此权限之前已被用户禁止，提示再次开启！");
                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
            }

        } else {
            LogUtil.d("检查权限结果：此权限已被授予！");
            permissionGrant.onPermissionGranted(requestCode);
        }
    }

    /**
     * 一次申请多个权限回调
     */
    private static void requestMultiResult(Activity activity, String[] permissions, int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }

        //TODO
        Map<String, Integer> perms = new HashMap<>();

        ArrayList<String> notGranted = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            LogUtil.d("permissions: [i]:" + i + ", permissions[i]" + permissions[i] + ",grantResults[i]:" + grantResults[i]);
            perms.put(permissions[i], grantResults[i]);
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }

        if (notGranted.size() == 0) {
            permissionGrant.onPermissionGranted(CODE_MULTI_PERMISSION);
        } else {
            openSettingActivity(activity, "这些权限未被授予!");
        }

    }


    /**
     * 一次申请多个权限
     */
    public static void requestMultiPermissions(final Activity activity, PermissionGrant grant) {

        final List<String> permissionsList = getNoGrantedPermission(activity, false);
        final List<String> shouldRationalePermissionsList = getNoGrantedPermission(activity, true);

        //TODO checkSelfPermission
        if (permissionsList == null || shouldRationalePermissionsList == null) {
            return;
        }
        LogUtil.d("requestMultiPermissions permissionsList:" + permissionsList.size() + ",shouldRationalePermissionsList:" + shouldRationalePermissionsList.size());

        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                    CODE_MULTI_PERMISSION);
        } else if (shouldRationalePermissionsList.size() > 0) {
            showMessageDialog(activity, "should open those permission",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
                                    CODE_MULTI_PERMISSION);
                        }
                    });
        } else {
            grant.onPermissionGranted(CODE_MULTI_PERMISSION);
        }

    }


    /**
     * 当禁止掉某权限后，再次检测时提示开启的对话框
     *
     * @param context         上下文
     * @param message         提示信息
     * @param onClickListener 确定事件
     */
    private static void showMessageDialog(final Activity context, String message, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton("拒绝", null)
                .setPositiveButton("去开启", onClickListener)
                .create()
                .show();

    }

    /**
     * 请求权限的回调
     *
     * @param activity     当前类
     * @param requestCode  Need consistent with requestPermission
     * @param permissions  权限
     * @param grantResults 授予结果
     */
    public static void requestPermissionsResult(
            final Activity activity,
            final int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }

        LogUtil.d("【requestPermissionsResult requestCode】" + requestCode);

        if (requestCode == CODE_MULTI_PERMISSION) {
            requestMultiResult(activity, permissions, grantResults, permissionGrant);
            return;
        }

        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            LogUtil.d("【非法权限请求码】" + requestCode);
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //TODO success, do something, can use callback
            permissionGrant.onPermissionGranted(requestCode);

        } else {
            //TODO hint user this permission function
            LogUtil.d("【请求的权限未被授予】onRequestPermissionsResult PERMISSION NOT GRANTED");
            //TODO
            String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);

            openSettingActivity(activity, permissionsHint[requestCode]);
        }

    }

    /**
     * 开启权限设置提示框
     *
     * @param activity 上下文
     * @param message  提示信息
     */
    private static void openSettingActivity(final Activity activity, String message) {

        showMessageDialog(activity, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

                LogUtil.d("getPackageName(): " + activity.getPackageName());
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
    }


    /**
     * @param activity          当前Activity
     * @param isShouldRationale true: return no granted and shouldShowRequestPermissionRationale permissions,
     *                          false:return no granted and !shouldShowRequestPermissionRationale
     * @return ArrayList
     */
    public static ArrayList<String> getNoGrantedPermission(Activity activity, boolean isShouldRationale) {

        ArrayList<String> permissions = new ArrayList<>();

        for (int i = 0; i < requestPermissions.length; i++) {
            String requestPermission = requestPermissions[i];


            //TODO checkSelfPermission
            int checkSelfPermission = -1;
            try {
                checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
            } catch (RuntimeException e) {
                LogUtil.e("RuntimeException:" + e.getMessage());
                return null;
            }

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                    if (isShouldRationale) {
                        permissions.add(requestPermission);
                    }

                } else {

                    if (!isShouldRationale) {
                        permissions.add(requestPermission);
                    }
                }

            }
        }

        return permissions;
    }

}
