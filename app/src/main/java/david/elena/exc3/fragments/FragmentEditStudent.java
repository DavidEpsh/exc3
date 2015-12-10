package david.elena.exc3.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.activities.ViewStudentActivity;
import david.elena.exc3.models.Student;


public class FragmentEditStudent extends Fragment {

    private View mRootView;
    EditText firstName;
    EditText id;
    EditText lastName;
    EditText phone;
    EditText address;
    CheckBox checkBox;
    int studentPos;
    Student currStudent;
    ViewStudentActivity mActivity;
    Button buttonBirthDate;
    Button buttonBirthTime;
    int[] mBirthDate, mBirthTime;
    String AM_PM;

    public FragmentEditStudent() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_edit_student, container, false);
        mActivity = (ViewStudentActivity) getActivity();

        firstName = (EditText) mRootView.findViewById(R.id.edit_text_student_first_name_edit_student);
        id = (EditText) mRootView.findViewById(R.id.edit_text_student_id_edit_student);
        lastName = (EditText) mRootView.findViewById(R.id.edit_text_student_last_name_edit_student);
        phone = (EditText) mRootView.findViewById(R.id.edit_text_student_phone_edit_student);
        address = (EditText) mRootView.findViewById(R.id.edit_text_student_address_edit_student);
        checkBox = (CheckBox) mRootView.findViewById(R.id.checkbox_edit_student);
        buttonBirthDate = (Button) mRootView.findViewById(R.id.button_birth_date_edit_student);
        buttonBirthTime = (Button) mRootView.findViewById(R.id.button_birth_time_edit_student);

        mBirthDate = currStudent.getBirthDate();
        mBirthTime = currStudent.getBirthTime();

        getActivity().setTitle("Edit Student Details");
        setValues();
        setDateAndTime();

        Button cancel = (Button) mRootView.findViewById(R.id.button_cancel_edit_student);
        Button delete = (Button) mRootView.findViewById(R.id.button_delete_edit_student);
        Button save = (Button) mRootView.findViewById(R.id.button_save_edit_student);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Toast.makeText(v.getContext(),"Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentDB.getInstance().removeStudent(studentPos);
                mActivity.setResultAndFinish();
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

        StudentDB.getInstance().editStudent(newSt, studentPos);
        mActivity.setResultAndFinish();
        Toast.makeText(getContext(), firstName.getText().toString() + " saved", Toast.LENGTH_SHORT).show();
    }

    public void setValues(){

        if(currStudent != null) {
            firstName.setText(currStudent.getFirstName());
            lastName.setText(currStudent.getLastName());
            id.setText(currStudent.getId());
            phone.setText(currStudent.getPhoneNumber());
            address.setText(currStudent.getAddress());
            checkBox.setChecked(currStudent.isChecked());
            buttonBirthTime.setText(setBirthTime(currStudent.getBirthTime()));
            buttonBirthDate.setText(setBirthDate(currStudent.getBirthDate()));
        }else {
            Toast.makeText(getContext(), "Failed to open student details", Toast.LENGTH_SHORT).show();
        }
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

    public void setStudent(Student student, int studentPos){
        this.currStudent = student;
        this.studentPos = studentPos;
    }

    private void setDateAndTime(){
        final Calendar c = Calendar.getInstance();

        final DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                mBirthDate[0] = dayOfMonth;
                mBirthDate[1] = monthOfYear;
                mBirthDate[2] = year;

                buttonBirthDate.setText(setBirthTime(mBirthDate));
            }

        },mBirthDate[2],mBirthDate[1],mBirthDate[0]);

        buttonBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        final TimePickerDialog timePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                mBirthTime[0] = hourOfDay;
                mBirthTime[1] = minute;
                buttonBirthTime.setText(setBirthTime(mBirthTime));
            }
        },mBirthTime[0], mBirthTime[1], true);

        buttonBirthTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show();
            }
        });
    }

    private String setBirthTime(int[] birthTime){
        String sMinute = "",AM_PM, total;
        int tempHour = birthTime[0];
        if (mBirthTime[2] == Calendar.AM){
            AM_PM = "AM";
        }else{
            AM_PM = "PM";
        }

        if(birthTime[0] > 12){
            tempHour = tempHour % 12;
            mBirthTime[2] = Calendar.PM;
            AM_PM = "PM";
        }else if(birthTime[0] == 12) {
            mBirthTime[2] = Calendar.PM;
            AM_PM = "PM";
        }else{
            mBirthTime[2] = Calendar.AM;
            AM_PM = "AM";
        }

        if(birthTime[1] < 10){
            sMinute = "0" + Integer.toString(birthTime[1]);
        }else{
            sMinute = Integer.toString(birthTime[1]);
        }
        total = Integer.toString(tempHour) + ":" + sMinute + " " + AM_PM;

        return total;
    }

    private String setBirthDate(int[] birthDate){

        return birthDate[0] + "/" + (birthDate[1]+1) + "/" + birthDate[2];
    }
}