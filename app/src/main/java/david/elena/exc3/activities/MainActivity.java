package david.elena.exc3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import david.elena.exc3.fragments.StudentListFragment;
import david.elena.exc3.models.Student;

public class MainActivity extends AppCompatActivity {

    ListView studentList;
    List<Student> mStudentListDB;
    StudentListAdapter adapter;

    public static String ITEM_IN_LIST = "position";
    public static int RESULT_ADD_STUDENT = 55;
    public static int RESULT_EDIT_STUDENT = 56;
    public static int RESULT_FINISHED_EDITING = 57;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mStudentListDB = StudentDB.getInstance().getList();
        studentList = (ListView)findViewById(R.id.studentListView);
        adapter = new StudentListAdapter(mStudentListDB, this);
        studentList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivityForResult(intent, RESULT_ADD_STUDENT);

            }
        });

        openFragment(new StudentListFragment());
        setTitle("Student List");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_ADD_STUDENT) {
            if (resultCode == RESULT_OK) {
                adapter.notifyDataSetChanged();
            }
        } else if(requestCode == RESULT_FINISHED_EDITING) {
            if (resultCode == RESULT_OK) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", MainActivity.RESULT_FINISHED_EDITING);
                setResult(this.RESULT_OK, returnIntent);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void openFragment(final Fragment fragment){
        getSupportFragmentManager()
        .beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }
}
