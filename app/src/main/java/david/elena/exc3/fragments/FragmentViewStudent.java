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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import david.elena.exc3.R;
import david.elena.exc3.activities.ViewStudentActivity;
import david.elena.exc3.models.Student;


public class FragmentViewStudent extends Fragment {

    private View mRootView;
    EditText firstName;
    EditText id;
    EditText lastName;
    EditText phone;
    EditText address;
    CheckBox checkBox;
    TextView textViewBirthDate;
    TextView textViewBirthTime;
    ViewStudentActivity mActivity;
    Student currStudent;


    public FragmentViewStudent() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_view_student, container, false);
        mActivity = (ViewStudentActivity) getActivity();

        firstName = (EditText) mRootView.findViewById(R.id.edit_text_student_first_name_view_student);
        id = (EditText) mRootView.findViewById(R.id.edit_text_student_id_view_student);
        lastName = (EditText) mRootView.findViewById(R.id.edit_text_student_last_name_view_student);
        phone = (EditText) mRootView.findViewById(R.id.edit_text_student_phone_view_student);
        address = (EditText) mRootView.findViewById(R.id.edit_text_student_address_view_student);
        checkBox = (CheckBox) mRootView.findViewById(R.id.checkbox_add_student_view_student);
        textViewBirthDate = (TextView) mRootView.findViewById(R.id.text_birth_date_view_student);
        textViewBirthTime = (TextView) mRootView.findViewById(R.id.text_birth_time_view_student);

        setValues();

        return mRootView;
    }

    public void setValues(){

        if(currStudent != null) {
            firstName.setText(currStudent.getFirstName());
            lastName.setText(currStudent.getLastName());
            id.setText(currStudent.getId());
            phone.setText(currStudent.getPhoneNumber());
            address.setText(currStudent.getAddress());
            checkBox.setChecked(currStudent.isChecked());
            textViewBirthTime.setText(setBirthTime(currStudent.getBirthTime()));
            textViewBirthDate.setText(setBirthDate(currStudent.getBirthDate()));
        }else {
            Toast.makeText(mRootView.getContext(), "Failed to open student details", Toast.LENGTH_SHORT).show();
        }
    }

    public void setStudent(Student student){
        this.currStudent = student;
    }

    @Override
    public void onResume(){
        super.onResume();

       setTitleAndMenu();
    }

    public void setTitleAndMenu(){
        if(mActivity.menu != null) {
            mActivity.showOnlyItem(R.id.action_edit);
            mActivity.setTitle("View Student Details");
        }
    }

    private String setBirthTime(int[] birthTime){

        String sMinute = "",AM_PM, total;
        int tempHour = birthTime[0];

        if(tempHour > 12){
            tempHour = tempHour % 12;
        }
        if (birthTime[2] == Calendar.AM){
            AM_PM = "AM";
        }else{
            AM_PM = "PM";
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