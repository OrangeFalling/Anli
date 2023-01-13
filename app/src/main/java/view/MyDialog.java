package view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.anli.R;

public class MyDialog extends Dialog {
    private Button btn_dialog_cancel, btn_dialog_exit;
    private TextView tv;    //定义标题文字
    public MyDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.mydialog_layout,null);
        tv = view.findViewById(R.id.dialog_title);
        btn_dialog_cancel = view.findViewById(R.id.btn_dialog_cancel);
        btn_dialog_exit = view.findViewById(R.id.btn_dialog_exit);
        setContentView(view);     //设置显示的视图
    }

    /**
     * 取消按钮的监听事件
     * @param listener
     */
    public void setOnCancelListener(View.OnClickListener listener){
        btn_dialog_cancel.setOnClickListener(listener);
    }
    /**
     * 退出按钮的监听事件
     * @param listener
     */
    public void setOnExitListener(View.OnClickListener listener){
        btn_dialog_exit.setOnClickListener(listener);
    }

}
