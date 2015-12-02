package david.elena.exc3;

import java.util.LinkedList;
import java.util.List;

import david.elena.exc3.models.Student;

public class StudentDB {
    private static StudentDB ourInstance = new StudentDB();

    private List<Student> studentDB;

    public static StudentDB getInstance() {
        return ourInstance;
    }

    private StudentDB() {
        studentDB = new LinkedList<>();
        addTestData();
    }

    public List<Student> getList(){
        return studentDB;
    }

    public void addStudent(Student student){
        studentDB.add(student);
    }

    public int getSize(){
        return studentDB.size();
    }

    public Student getStudent(int pos){
        return studentDB.get(pos);
    }

    public void removeStudent(int position){
        studentDB.remove(position);
    }

    public void editStudent(Student tempStudent, int position){
        studentDB.set(position, tempStudent);
    }


    private void addTestData(){
        addStudent(new Student("Han","Solo","2486","055-4545454","Dstar",true));
        addStudent(new Student("Luke","SkyW","6842","055-1212121","Dstar",true));
    }
}
