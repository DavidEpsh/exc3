package david.elena.exc3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.adapters.StudentListAdapter;
import david.elena.exc3.models.Student;

public class MainActivity extends AppCompatActivity {

    ListView studentList;
    List<Student> mStudentListDB;

    public static String ITEM_IN_LIST = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStudentListDB = StudentDB.getInstance().getList();
        studentList = (ListView)findViewById(R.id.studentListView);
        StudentListAdapter adapter = new StudentListAdapter(mStudentListDB, this);
        studentList.setAdapter(adapter);

        ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this,R.layout.student_row_layout,R.id.firstNameRow,mStudentListDB);
        studentList.setAdapter(adapter);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "row " + position + " selected");
                Intent intent = new Intent(MainActivity.this, ViewStudentActivity.class);
                intent.putExtra(ITEM_IN_LIST,position);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
