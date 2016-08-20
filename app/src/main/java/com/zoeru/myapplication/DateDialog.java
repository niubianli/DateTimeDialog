package com.zoeru.myapplication;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Administrator on 2016/4/29 14:31
 * Created by Administrator on 2016/4/29.
 */
public class DateDialog extends Dialog {
    private Context mContext;
    InterfaceDateDialog interfaceDateDialog;

    public DateDialog(Context context, InterfaceDateDialog interfaceDateDialog) {
        super(context);
        this.interfaceDateDialog=interfaceDateDialog;
        mContext = context;
    }

    public DateDialog(Context context, int theme) {
        super(context, theme);
    }

    private DatePicker mDp_datePicker;
    private TimePicker mTp_timePicker;
    private Calendar mCTime = null; // 获取当前时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date);
        mDp_datePicker = (DatePicker) findViewById(R.id.dp_datePicker);
        mTp_timePicker= (TimePicker) findViewById(R.id.tp_timePicker);
        mTp_timePicker.setIs24HourView(true);
        setCanceledOnTouchOutside(true);
        mCTime = Calendar.getInstance();
        //resizePikcer(mDp_datePicker);//调整datepicker大小
        //resizePikcer(mTp_timePicker);//调整timepicker大小


        // 设置dialog 的padding
//        Window win = getWindow();
//        win.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = win.getAttributes();
//        lp.width = WindowManager.LayoutParams.FILL_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        win.setAttributes(lp);

        // Change DatePicker layout
        LinearLayout dpContainer = (LinearLayout)mDp_datePicker.getChildAt(0)   ;   // LinearLayout
        for (int i=0 ;i<  dpContainer.getChildCount();i++){

        }
//        LinearLayout dpSpinner = (LinearLayout)dpContainer.getChildAt(0);       // 0 : LinearLayout; 1 : CalendarView
//        for(int i = 0; i < dpSpinner.getChildCount(); i ++) {
//            NumberPicker numPicker = (NumberPicker)dpSpinner.getChildAt(i);     // 0-2 : NumberPicker
//           LayoutParams params1 = new LayoutParams(120, LayoutParams.WRAP_CONTENT);
////            params1.leftMargin = 0;
////            params1.rightMargin = 0;
//            numPicker.setLayoutParams(params1);
//        }

        // Change TimePicker layout
//        LinearLayout tpContainer = (LinearLayout)mTp_timePicker.getChildAt(0)   ;   // LinearLayout// 0 : LinearLayout; 1 : CalendarView
////        FrameLayout.LayoutParams params4 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
////        params4.setMargins(0,0,0,0);
////        tpContainer.setLayoutParams(params4);
//        for(int i = 0; i < tpContainer.getChildCount(); i ++) {
//            if (i == 1) {
////                tpContainer.getChildAt(0).setVisibility(View.GONE);
//                continue;
//            }
//            NumberPicker numPicker =  (NumberPicker)tpContainer.getChildAt(i);     // 0 : NumberPicker; 1 : TextView; 2 : NumberPicker
//            LayoutParams params3 = new LayoutParams(120, LayoutParams.WRAP_CONTENT);
////            params3.setMargins(0,0,0,0);
//            numPicker.setLayoutParams(params3);
//        }
        findViewById(R.id.btn_succes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = mDp_datePicker.getYear() + "-" + (mDp_datePicker.getMonth() + 1) + "-" + mDp_datePicker.getDayOfMonth() + "  "+mTp_timePicker.getCurrentHour()+":"+mTp_timePicker.getCurrentMinute() ;
                interfaceDateDialog.getTime(date);
                dismiss();

            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
    /**
     * 调整FrameLayout大小
     * @param tp
     */
    private void resizePikcer(FrameLayout tp){
        List<NumberPicker> npList = findNumberPicker(tp);
        for(NumberPicker np:npList){
            resizeNumberPicker(np);
        }
    }
    /**
     * 得到viewGroup里面的numberpicker组件
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if(null != viewGroup){
            for(int i = 0;i<viewGroup.getChildCount();i++){
                child = viewGroup.getChildAt(i);
                if(child instanceof NumberPicker){
                    npList.add((NumberPicker)child);
                }
                else if(child instanceof LinearLayout){
                    child.setPadding(0,0,0,0);

                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if(result.size()>0){
                        return result;
                    }
                }else if (child instanceof TextView){
                    child.setVisibility(View.GONE);
                }

            }
        }
        return npList;
    }

    private void resizeNumberPicker(NumberPicker np){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        np.setLayoutParams(params);
    }

    public  interface  InterfaceDateDialog{
        public void getTime(String dateTime);
    }

}
