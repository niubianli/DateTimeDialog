package com.zoeru.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Administrator on 2016/4/29 14:31
 * Created by Administrator on 2016/4/29.
 */
public class DateDialog extends Dialog {
    private Context mContext;
    IntefaceDateDialog intefaceDateDialog;

    public DateDialog(Context context, IntefaceDateDialog intefaceDateDialog) {
        super(context);
        this.intefaceDateDialog=intefaceDateDialog;
        mContext = context;
    }

    public DateDialog(Context context, int theme) {
        super(context, theme);
    }

    protected DateDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
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
        long date = System.currentTimeMillis();
        mCTime = Calendar.getInstance();
     setTitle(mCTime.get(Calendar.YEAR) + "年" + (mCTime.get(Calendar.MONTH) + 1) + "月" + mCTime.get(Calendar.DAY_OF_MONTH)+"日");
        findViewById(R.id.btn_succes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = mDp_datePicker.getYear() + "-" + (mDp_datePicker.getMonth() + 1) + "-" + mDp_datePicker.getDayOfMonth() + "  "+mTp_timePicker.getCurrentHour()+":"+mTp_timePicker.getCurrentMinute() ;

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//                    Date curDate = null;//获取当前时间
//                    curDate = df.parse(mCTime.get(Calendar.YEAR) + "-" + (mCTime.get(Calendar.MONTH) + 1) + "-" + mCTime.get(Calendar.DAY_OF_MONTH));
//                    Date d1 = df.parse(date);
//                    long l = d1.getTime() - curDate.getTime();
//                    if (l < 0) {
//                        return;
//                    }
                    intefaceDateDialog.getTime(date);
                    dismiss();

            }
        });
    }

    public  interface  IntefaceDateDialog{
        public void getTime(String dateTime);
    }

}
