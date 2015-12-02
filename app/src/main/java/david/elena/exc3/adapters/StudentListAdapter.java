package david.elena.exc3.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import david.elena.exc3.R;
import david.elena.exc3.models.Student;
import david.elena.exc3.StudentDB;

public class StudentListAdapter extends BaseAdapter {

    private View rowView;
    private List<Student> mStudentList;
    Context _context;

    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView studentIdTextView;
    ImageView studentImage;
    CheckBox checkBox;

    public StudentListAdapter(List<Student> studentList, Context context){
        mStudentList = studentList;
        _context = context;
    }

    @Override
    public int getCount() {
        return StudentDB.getInstance().getSize();
    }

    @Override
    public Object getItem(int position) {
        return StudentDB.getInstance().getStudent(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row_layout, parent, false);
        }

        checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
        firstNameTextView = (TextView) convertView.findViewById(R.id.firstNameRow);
        lastNameTextView = (TextView) convertView.findViewById(R.id.lastNameRow);
        studentIdTextView = (TextView) convertView.findViewById(R.id.idRow);
        studentImage = (ImageView) convertView.findViewById(R.id.imageRow);

        Student student = mStudentList.get(position);

        firstNameTextView.setText(student.getFirstName());
        lastNameTextView.setText(student.getLastName());
        studentIdTextView.setText(student.getId());
        checkBox.setChecked(student.isChecked());

        return convertView;
    }
}

