package david.elena.exc3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.fragments.FragmentViewStudent;
import david.elena.exc3.models.Student;

public class ViewStudentActivity extends AppCompatActivity {

    static public String BUNDLE_STUDENT_POSITION = "student_position";

    EditText firstName;
    EditText id;
    EditText lastName;
    EditText phone;
    EditText address;
    CheckBox checkBox;
    int studentPos;
    Student currStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        if(getIntent() != null) {
            studentPos = getIntent().getIntExtra(MainActivity.ITEM_IN_LIST, 0);
            currStudent = StudentDB.getInstance().getStudent(studentPos);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_STUDENT_POSITION, studentPos);
        FragmentViewStudent fragment = new FragmentViewStudent();
        fragment.setArguments(bundle);
        openFragment(fragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_add).setVisible(false);

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

        }else if(id == R.id.action_edit){
            Intent intent = new Intent(this, EditStudentActivity.class);
            intent.putExtra(MainActivity.ITEM_IN_LIST,studentPos);
            startActivityForResult(intent, MainActivity.RESULT_FINISHED_EDITING);

        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.RESULT_FINISHED_EDITING) {
            if (resultCode == RESULT_OK) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", MainActivity.RESULT_FINISHED_EDITING);
                setResult(this.RESULT_OK, returnIntent);

                finish();
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void openFragment(final Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_view_student,fragment)
                .commit();
    }

}
