package com.example.hxy_baseproject.http;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.example.hxy_baseproject.R;

/**
 * Created by iningke on 2016/6/4.
 */

public class LoadingDialog extends Dialog implements DialogInterface.OnDismissListener {
    private LayoutInflater inflater;
    private OnDismissListener listener;

    public LoadingDialog(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        inflater = LayoutInflater.from(context);
    }

    public void showDialog(OnDismissListener listener) {
        if (this.isShowing()) {
            dismiss();
        }

        View contentview = inflater.inflate(R.layout.item_progress, null);
        setContentView(contentview);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
//        imageView = (ImageView) contentview.findViewById(R.id.loading_imag);
        setOnDismissListener(this);
        this.listener = listener;
        show();
//        handler.sendEmptyMessage(0);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
//        handler.removeCallbacks(runnable);
        if (listener != null) {
            listener.onDismiss(dialog);
        }
    }
}
