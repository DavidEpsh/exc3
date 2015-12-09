package david.elena.exc3.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.activities.AddStudentActivity;
import david.elena.exc3.models.Student;


public class FragmentAddStudent extends Fragment {

    private View mRootView;
    EditText firstName;
    EditText id;
    EditText lastName;
    EditText phone;
    EditText address;
    CheckBox checkBox;
    Student currStudent;
    AddStudentActivity mActivity;
    Button buttonBirthDate;
    Button buttonBirthTime;
    int[] mBirthDate, mBirthTime;
    int mYear, mMonth, mDay;
    int mHour, mMinutes;
    String AM_PM;

    public FragmentAddStudent() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_add_student, container, false);
        mActivity = (AddStudentActivity) getActivity();

        firstName = (EditText) mRootView.findViewById(R.id.edit_text_student_first_name_add_student);
        id = (EditText) mRootView.findViewById(R.id.edit_text_student_id_add_student);
        lastName = (EditText) mRootView.findViewById(R.id.edit_text_student_last_name_add_student);
        phone = (EditText) mRootView.findViewById(R.id.edit_text_student_phone_add_student);
        address = (EditText) mRootView.findViewById(R.id.edit_text_student_address_add_student);
        checkBox = (CheckBox) mRootView.findViewById(R.id.checkbox_add_student);
        buttonBirthDate = (Button) mRootView.findViewById(R.id.birth_date_button);
        buttonBirthTime = (Button) mRootView.findViewById(R.id.birth_time_button);

        addTestText();
        setDateAndTime();

        Button cancel = (Button) mRootView.findViewById(R.id.button_cancel_add_student);
        Button save = (Button) mRootView.findViewById(R.id.button_save_student_add_student);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Toast.makeText(v.getContext(),"Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requiredFieldsFilled()) {
                    saveStudent();
                }
            }
        });

        return mRootView;
    }

    public void saveStudent(){
        Student newSt = new Student(firstName.getText().toString(),
                lastName.getText().toString(),
                id.getText().toString(),
                phone.getText().toString(),
                address.getText().toString(),
                checkBox.isChecked(),
                mBirthDate,
                mBirthTime);

        StudentDB.getInstance().addStudent(newSt);
        mActivity.setResultAndFinish();
        Toast.makeText(getContext(), firstName.getText().toString() + " saved", Toast.LENGTH_SHORT).show();
    }

    public boolean requiredFieldsFilled() {

        boolean isFilled = true;

        if (firstName.getText().toString().trim().equalsIgnoreCase("")) {
            firstName.setError("Enter Student Name");
            isFilled = false;
        }
        if (lastName.getText().toString().trim().equalsIgnoreCase("")) {
            lastName.setError("Enter Student Last Name");
            isFilled = false;
        }
        if (id.getText() == null) {
            id.setError("Enter Student ID");
            isFilled = false;
        }
        if (phone.getText() == null) {
            phone.setError("Enter Phone Number");
            isFilled = false;
        }
        if (address.getText() == null) {
            address.setError("Enter Address");
            isFilled = false;
        }
        return isFilled;
    }

    public void addTestText(){
        firstName.setText("aaa");
        lastName.setText("bbb");
        id.setText("123");
        phone.setText("321");
        address.setText("ccc");
    }

    private void setDateAndTime(){
        final Calendar c = Calendar.getInstance();
        mBirthDate = new int[3];
        mBirthTime = new int[3];

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mBirthTime[0] = c.get(Calendar.HOUR);
        mBirthTime[1] = c.get(Calendar.SECOND);
        mBirthTime[2] = c.get(Calendar.AM_PM);

        buttonBirthTime.setText(getTimeString(mBirthTime));

        mBirthDate[0] = mDay;
        mBirthDate[1] = mMonth;
        mBirthDate[2] = mYear;
        buttonBirthDate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);

        final DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int newYear = year;
                int newMonth = monthOfYear;
                int newDay = dayOfMonth;

                buttonBirthDate.setText(newDay + "/" + (newMonth + 1) + "/" + newYear);
                mBirthDate[0] = dayOfMonth;
                mBirthDate[1] = monthOfYear;
                mBirthDate[2] = year;
            }

        },mYear,mMonth,mDay);

        buttonBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        final TimePickerDialog timePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if(hourOfDay >= 12){
                    hourOfDay = hourOfDay % 12;
                    mBirthTime[3] = Calendar.PM;
                    AM_PM = "PM";
                }else{
                    mBirthTime[3] = Calendar.AM;
                    AM_PM = "AM";
                }

                mBirthTime[0] = hourOfDay;
                mBirthTime[1] = minute;
                buttonBirthTime.setText(getTimeString(mBirthTime));
            }
        },Calendar.HOUR_OF_DAY, mMinutes, false);

        buttonBirthTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show();
            }
        });
    }

    public String getTimeString(int[] birthTime){
        String sMinute = "", total;

        if (mBirthTime[3] == Calendar.AM){
            AM_PM = "AM";
        }else{
            AM_PM = "PM";
        }
        if(birthTime[1] < 10){
            sMinute = "0" + Integer.toString(birthTime[1]);
        }else{
            sMinute = Integer.toString(birthTime[1]);
        }
        total = Integer.toString(birthTime[0]) + ":" + sMinute + " " + AM_PM;

        return total;
    }

    public void setStudent(Student student){
        this.currStudent = student;
    }
}