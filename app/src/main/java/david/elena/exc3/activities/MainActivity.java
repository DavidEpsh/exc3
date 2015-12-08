package david.elena.exc3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import david.elena.exc3.R;
import david.elena.exc3.adapters.StudentListAdapter;
import david.elena.exc3.fragments.FragmentStudentList;
import david.elena.exc3.models.Student;

public class MainActivity extends AppCompatActivity {

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

        openFragment(new FragmentStudentList());
        setTitle("Student List");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.findItem(R.id.action_edit).setVisible(false);
        menu.findItem(R.id.action_add).setVisible(true);

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

        }else if(id == R.id.action_add){
            Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
            startActivityForResult(intent, RESULT_ADD_STUDENT);
        }

        return super.onOptionsItemSelected(item);
    }

    private void openFragment(final Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }
}
