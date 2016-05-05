package com.fithou.friendeverywhere.ultis;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;

public class VLIDialog extends Dialog{

	private static VLIDialog dialog;
	private static Context mContext;
	private Button btnAccept;
	private TextView lblMsg;

	public VLIDialog(Context context) {
		super(context);
		mContext = context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCancelable(false);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		setContentView(R.layout.dialog_vli);
		lblMsg = (TextView) findViewById(R.id.lblMessage);
		btnAccept = (Button) findViewById(R.id.btnAccept);
	}

	public static void dismissDialog() {
		if(dialog != null && dialog.isShowing()) dialog.dismiss();
	}

	public static VLIDialog show(Context context, String message){
		return show(context, message, null, null);
	}

	public static VLIDialog show(Context context, String message, String txtAccept){
		return show(context, message, txtAccept, null);
	}

	public static VLIDialog show(Context context, String message, View.OnClickListener accept){
		return show(context, message, null, accept);
	}

	public static VLIDialog show(Context context, String message,
			String txtAccept, View.OnClickListener accept){
		if (dialog != null && mContext == context && dialog.isShowing())
			return dialog;
		dialog = new VLIDialog(context);
		dialog.lblMsg.setText(message);
		if(TextUtils.isEmpty(txtAccept))
			txtAccept = "Đóng";
		dialog.btnAccept.setText(txtAccept);
		if(accept == null){
			accept = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			};
		}
		dialog.btnAccept.setOnClickListener(accept);
		dialog.show();
		return dialog;
	}

	public static void clear(){
		dialog = null;
		mContext = null;
	}
}
