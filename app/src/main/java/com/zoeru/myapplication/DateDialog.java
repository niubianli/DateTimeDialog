package com.zoeru.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator on 2016/4/29 14:31
 * Created by Administrator on 2016/4/29.
 */
public class DateDialog extends Dialog {
    public static final int MODE_1 = 001; // 日期 时间
    public static final int MODE_2 = 002;// 日期
    public static final int MODE_3 = 003;
    private int mMode = MODE_1;
    private String mTitle = "初始时间";

    InterfaceDateDialog interfaceDateDialog;

    public DateDialog(Context context, InterfaceDateDialog interfaceDateDialog) {
        super(context);
        this.interfaceDateDialog = interfaceDateDialog;
    }

    public DateDialog(Context context, String title, int mode, InterfaceDateDialog interfaceDateDialog) {
        super(context);
        this.interfaceDateDialog = interfaceDateDialog;
        mMode = mode;
        mTitle = title;

    }

    public DateDialog(Context context, int theme) {
        super(context, theme);
    }

    private DatePicker mDp_datePicker; //
    private TimePicker mTp_timePicker;
    private TextView mTv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date);
        mDp_datePicker = (DatePicker) findViewById(R.id.dp_datePicker);
        mTp_timePicker = (TimePicker) findViewById(R.id.tp_timePicker);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mTv_title.setText(mTitle);
        mTp_timePicker.setIs24HourView(true);
        setCanceledOnTouchOutside(true);


        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        switch (mMode) {
            case MODE_1:


                resizePikcer(mDp_datePicker);//调整datepicker大小
                resizePikcer(mTp_timePicker);//调整timepicker大小
                findViewById(R.id.btn_succes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date = "";
                        date = String.format("%d-%02d-%02d", mDp_datePicker.getYear(), mDp_datePicker.getMonth() + 1, mDp_datePicker.getDayOfMonth())
                                + " " + String.format("%02d:%02d", mTp_timePicker.getCurrentHour(), mTp_timePicker.getCurrentMinute());
                        interfaceDateDialog.getTime(date);
                        dismiss();

                    }
                });
                break;
            case  MODE_2:
                mTp_timePicker.setVisibility(View.GONE);
                findViewById(R.id.btn_succes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date = "";
                        date = String.format("%d-%02d-%02d", mDp_datePicker.getYear(), mDp_datePicker.getMonth() + 1, mDp_datePicker.getDayOfMonth());
                        interfaceDateDialog.getTime(date);
                        dismiss();

                    }
                });
                break;
            case MODE_3:

                mDp_datePicker.setVisibility(View.GONE);
                findViewById(R.id.btn_succes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date = "";
                        date =String.format("%02d:%02d", mTp_timePicker.getCurrentHour(), mTp_timePicker.getCurrentMinute());
                        interfaceDateDialog.getTime(date);
                        dismiss();

                    }
                });
                break;

        }


    }

    /**
     * 调整FrameLayout大小
     *
     * @param tp
     */
    private void resizePikcer(FrameLayout tp) {
        List<NumberPicker> npList = findNumberPicker(tp);
        for (NumberPicker np : npList) {
            resizeNumberPicker(np);
        }
    }

    /**
     * 得到viewGroup里面的numberpicker组件
     *
     * @param viewGroup
     * @return
     */
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    child.setPadding(0, 0, 0, 0);

                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                } else if (child instanceof TextView) {
                    child.setVisibility(View.GONE);
                }

            }
        }
        return npList;
    }

    private void resizeNumberPicker(NumberPicker np) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        np.setLayoutParams(params);
    }

    public interface InterfaceDateDialog {
        void getTime(String dateTime);
    }


}
