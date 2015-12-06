package david.elena.exc3.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.adapters.StudentListAdapter;
import david.elena.exc3.models.Student;

public class StudentListFragment extends Fragment {

    private View mRootView;
    private ListView mListView;
    private StudentListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Student> mStudentList;



    public StudentListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_student_list, container, false);

        mListView = (ListView) mRootView.findViewById(R.id.studentListView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.seLay(mLayoutManager);

        mStudentList = StudentDB.getInstance().getList();

        mAdapter = new StudentListAdapter(mStudentList, getActivity());
        mListView.setAdapter(mAdapter);

        return mRootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

}