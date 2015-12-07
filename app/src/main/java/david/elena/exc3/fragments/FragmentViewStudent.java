package david.elena.exc3.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.activities.MainActivity;
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
    int studentPos;
    Student currStudent;


    public FragmentViewStudent() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        studentPos = getArguments().getInt(ViewStudentActivity.BUNDLE_STUDENT_POSITION);
        mRootView = inflater.inflate(R.layout.fragment_view_student, container, false);

        currStudent = StudentDB.getInstance().getStudent(studentPos);

        firstName = (EditText) mRootView.findViewById(R.id.edit_text_student_first_name_view_student);
        id = (EditText) mRootView.findViewById(R.id.edit_text_student_id_view_student);
        lastName = (EditText) mRootView.findViewById(R.id.edit_text_student_last_name_view_student);
        phone = (EditText) mRootView.findViewById(R.id.edit_text_student_phone_view_student);
        address = (EditText) mRootView.findViewById(R.id.edit_text_student_address_view_student);
        checkBox = (CheckBox) mRootView.findViewById(R.id.checkbox_add_student_view_student);

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
        }else {
            Toast.makeText(mRootView.getContext(), "Failed to open student details", Toast.LENGTH_SHORT).show();
        }
    }
}