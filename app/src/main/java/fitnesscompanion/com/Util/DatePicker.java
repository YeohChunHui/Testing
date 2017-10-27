package fitnesscompanion.com.Util;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kok Fung on 9/29/2017.
 */

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private Context context;
    private EditText editText;
    private TextView textView;
    private String defaultDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public DatePicker(Context context,EditText editText) {
        this.context=context;
        this.editText=editText;
        defaultDate = editText.getText().toString();
    }
    public DatePicker(Context context,TextView textView) {
        this.context=context;
        this.textView=textView;
        defaultDate = textView.getText().toString();
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        if(defaultDate!=null) {
            calendar=toCalendar();
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,this,year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        return datePickerDialog;
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month, dayOfMonth, 0, 0, 0);
        Date chosenDate = cal.getTime();

        if(editText==null) {
            textView.setText(dateFormat.format(chosenDate));
        }
        else{
            editText.setText(dateFormat.format(chosenDate));
        }

    }
    public Calendar toCalendar() {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new Date(String.valueOf(dateFormat.parse(defaultDate))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;

    }
}
