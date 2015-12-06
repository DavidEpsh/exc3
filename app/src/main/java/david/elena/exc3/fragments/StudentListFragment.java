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

public class StudentListFragment extends Fragment {

    private View mRootView;
    private ListView studentList;
    private StudentListAdapter mAdapter;
    private List<Student> mStudentListDB;



    public StudentListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_student_list, container, false);
        studentList = (ListView) mRootView.findViewById(R.id.studentListView);
        mStudentListDB = StudentDB.getInstance().getList();
        mAdapter = new StudentListAdapter(mStudentListDB, getActivity());
        studentList.setAdapter(mAdapter);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "row " + position + " selected");
                Intent intent = new Intent(mRootView.getContext(), ViewStudentActivity.class);
                intent.putExtra(MainActivity.ITEM_IN_LIST, position);
                startActivityForResult(intent, MainActivity.RESULT_FINISHED_EDITING);
            }
        });

        return mRootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }



}