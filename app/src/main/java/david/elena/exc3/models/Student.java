package david.elena.exc3.models;


public class Student {

    String firstName;
    String lastName;
    String id;
    String phoneNumber;
    String address;
    boolean isChecked;
    int[] birthDate, birthTime;
    int tempStudentImage;
    String studentImageGallery;

    public Student(String firstName,String lastName, String id, String phoneNumber, String address, boolean ischecked, int[] birthDate, int[] birthTime){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isChecked = ischecked;
        this.birthDate = birthDate;
        this.birthTime = birthTime;
    }

    public void setBirthDate(int[] birthDate){
        this.birthDate = birthDate;
    }

    public int[] getBirthDate(){
        return this.birthDate;
    }

    public void setBirthTime(int[] birthTime){
        this.birthTime = birthTime;
    }

    public int[] getBirthTime(){
        return this.birthTime;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }



}
