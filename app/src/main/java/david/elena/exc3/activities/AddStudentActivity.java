package david.elena.exc3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.fragments.FragmentAddStudent;
import david.elena.exc3.models.Student;

public class AddStudentActivity extends AppCompatActivity {

    FragmentAddStudent fragmentAdd;
    int studentPos;
    Student currStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        openFragment(new FragmentAddStudent());
        setTitle("Add Student");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.setGroupVisible(R.id.menu_group, false);
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

    public void setResultAndFinish(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", MainActivity.RESULT_FINISHED_EDITING);
        setResult(this.RESULT_OK, returnIntent);

        finish();
    }

    private void openFragment(final Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_add_student,fragment)
                .commit();
    }

}
