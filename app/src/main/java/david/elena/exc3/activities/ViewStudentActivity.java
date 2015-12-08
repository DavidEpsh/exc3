package david.elena.exc3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.fragments.FragmentEditStudent;
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
    FragmentEditStudent fragmentEdit;
    FragmentViewStudent fragmentView;
    public Menu menu;


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

        fragmentEdit = new FragmentEditStudent();
        fragmentEdit.setStudent(currStudent, studentPos);

        fragmentView = new FragmentViewStudent();
        fragmentView.setStudent(currStudent);

        openFragment(fragmentView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        showOnlyItem(R.id.action_edit);

        return true;
    }

    public void showOnlyItem(int id){

        menu.setGroupVisible(R.id.menu_group, false);

        if(id != 0)
            menu.findItem(id).setVisible(true);
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
            openFragment(fragmentEdit);
            showOnlyItem(0);

        }

        return super.onOptionsItemSelected(item);
    }

    public void setResultAndFinish(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", MainActivity.RESULT_FINISHED_EDITING);
        setResult(this.RESULT_OK, returnIntent);

        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.RESULT_FINISHED_EDITING) {
            if (resultCode == RESULT_OK) {
                setResultAndFinish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openFragment(final Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
        transaction.replace(R.id.container_view_student, fragment)
                            .addToBackStack(null)
                            .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }



}
