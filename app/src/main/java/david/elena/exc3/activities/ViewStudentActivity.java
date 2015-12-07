package david.elena.exc3.activities;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.models.Student;

public class ViewStudentActivity extends AppCompatActivity {

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

        firstName = (EditText) findViewById(R.id.edit_text_student_first_name_view_student);
        id = (EditText) findViewById(R.id.edit_text_student_id_view_student);
        lastName = (EditText) findViewById(R.id.edit_text_student_last_name_view_student);
        phone = (EditText) findViewById(R.id.edit_text_student_phone_view_student);
        address = (EditText) findViewById(R.id.edit_text_student_address_view_student);
        checkBox = (CheckBox) findViewById(R.id.checkbox_add_student_view_student);

        setValues();

        Button btnEdit = (Button) findViewById(R.id.button_edit_view_student);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStudentActivity.this, EditStudentActivity.class);
                intent.putExtra(MainActivity.ITEM_IN_LIST,studentPos);
                startActivityForResult(intent, MainActivity.RESULT_FINISHED_EDITING);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem edit = menu.findItem(R.id.action_edit);
        edit.setIcon(R.drawable.ic_mode_edit_white);
        edit.setTitle("Edit Student");


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


    public void setValues(){

        if(currStudent != null) {
            firstName.setText(currStudent.getFirstName());
            lastName.setText(currStudent.getLastName());
            id.setText(currStudent.getId());
            phone.setText(currStudent.getPhoneNumber());
            address.setText(currStudent.getAddress());
            checkBox.setChecked(currStudent.isChecked());
        }else {
            Toast.makeText(this, "Failed to open student details", Toast.LENGTH_SHORT).show();
        }
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

}
