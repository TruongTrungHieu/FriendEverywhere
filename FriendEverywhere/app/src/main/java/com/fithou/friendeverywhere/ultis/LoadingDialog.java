package com.fithou.friendeverywhere.ultis;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class LoadingDialog extends Dialog {
    public enum ProgressStyle {
        NORMAL,
        LARGE,
        SMALL
    }

    private static LoadingDialog instance;
    private static Context context;

    private LoadingDialog(Context context, ProgressStyle style, boolean isDimScreen) {
        super(context);
        init(context, style, isDimScreen);
    }

    public static LoadingDialog getDialog(Context context) {
        return getDialog(context, ProgressStyle.NORMAL, true);
    }

    public static LoadingDialog getDialog(Context context, ProgressStyle style, boolean isDimScreen) {
        if (LoadingDialog.context != context || instance == null)
            instance = new LoadingDialog(context, style, isDimScreen);
        return instance;
    }

    public LoadingDialog showBelow(final View anchorBelow) {
        anchorBelow.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.y = anchorBelow.getHeight();
                getWindow().setAttributes(params);
                anchorBelow.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                show();
            }
        });
        return this;
    }

    private void init(Context context, ProgressStyle style, boolean isDimScreen) {
        LoadingDialog.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        if (!isDimScreen)
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        LinearLayout view = new LinearLayout(context);
        view.setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        int progressStyle;
        switch (style) {
            case LARGE:
                progressStyle = android.R.attr.progressBarStyleLarge;
                break;
            case SMALL:
                progressStyle = android.R.attr.progressBarStyleSmall;
                break;
            default:
                progressStyle = android.R.attr.progressBarStyle;
                break;
        }
        ProgressBar progress = new ProgressBar(context, null, progressStyle);
        view.addView(progress, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setContentView(view);
    }

}
