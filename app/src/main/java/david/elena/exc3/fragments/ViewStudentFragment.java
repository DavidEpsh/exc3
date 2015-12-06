package david.elena.exc3.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.activities.MainActivity;
import david.elena.exc3.activities.ViewStudentActivity;
import david.elena.exc3.adapters.StudentListAdapter;
import david.elena.exc3.models.Student;

public class ViewStudentFragment extends Fragment {

    private View mRootView;


    public ViewStudentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_student_list, container, false);




        return mRootView;
    }


}