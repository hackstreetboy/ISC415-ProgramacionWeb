package edu.pucmm.data;

/**
 * Created by MEUrena on 5/26/16.
 * All rights reserved.
 */
public class Student {

    private int studentID;
    private String name;
    private String lastName;
    private String phone;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student() {

    }

    public Student(int studentID, String name, String lastName, String phone) {
        this.studentID = studentID;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
    }
}
