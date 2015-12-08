package david.elena.exc3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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

        getActivity().setTitle("Edit Student Details");
        setValues();

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
                checkBox.isChecked());

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
}