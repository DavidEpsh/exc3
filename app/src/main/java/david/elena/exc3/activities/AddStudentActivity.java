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
import android.widget.Toast;

import david.elena.exc3.R;
import david.elena.exc3.StudentDB;
import david.elena.exc3.models.Student;

public class AddStudentActivity extends AppCompatActivity {

    EditText firstName;
    EditText id;
    EditText lastName;
    EditText phone;
    EditText address;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        firstName = (EditText) findViewById(R.id.edit_text_student_first_name);
        id = (EditText) findViewById(R.id.edit_text_student_id);
        lastName = (EditText) findViewById(R.id.edit_text_student_last_name);
        phone = (EditText) findViewById(R.id.edit_text_student_phone);
        address = (EditText) findViewById(R.id.edit_text_student_address);
        checkBox = (CheckBox) findViewById(R.id.checkbox_add_student);

        Button cancel = (Button) findViewById(R.id.button_cancel);
        Button save = (Button) findViewById(R.id.button_save_student);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(v.getContext(),"Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","save pressed");
                if(requiredFieldsFilled()) {
                    saveStudent();
                }
            }
        });

        addTestText();
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

    public boolean requiredFieldsFilled() {

        boolean isFilled = true;

        if (firstName.getText().toString().trim().equalsIgnoreCase("")) {
            firstName.setError("Enter Student Name");
            isFilled = false;
        }
         if (lastName.getText().toString().trim().equalsIgnoreCase("")) {
             lastName.setError("Enter Student Last Name");
             isFilled = false;
         }
        if (id.getText() == null) {
            id.setError("Enter Student ID");
            isFilled = false;
        }
        if (phone.getText() == null) {
            phone.setError("Enter Phone Number");
            isFilled = false;
        }
        if (address.getText() == null) {
            address.setError("Enter Address");
            isFilled = false;
        }
        return isFilled;
    }

    public void saveStudent(){
        Student newSt = new Student(firstName.getText().toString(),
                                    lastName.getText().toString(),
                                    id.getText().toString(),
                                    phone.getText().toString(),
                                    address.getText().toString(),
                                    checkBox.isChecked());

        StudentDB.getInstance().addStudent(newSt);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", MainActivity.RESULT_ADD_STUDENT);
        setResult(this.RESULT_OK, returnIntent);

        finish();

        Toast.makeText(this, firstName.getText().toString() + " saved", Toast.LENGTH_SHORT).show();
    }

    public void addTestText(){
        firstName.setText("aaa");
        lastName.setText("bbb");
        id.setText("123");
        phone.setText("321");
        address.setText("ccc");
    }


}
