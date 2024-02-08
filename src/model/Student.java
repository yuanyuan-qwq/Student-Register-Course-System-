package model;

public class Student extends user{ 	//student information
    private String MID,program;
    private float CGPA;
    private int totalCredit,year;

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getCGPA() {
        return CGPA;
    }

    public void setCGPA(float CGPA) {
        this.CGPA = CGPA;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }
/*
    public float calculateCGPA(){
        return CGPA;
    }

    public int calculateTotalCredit(){
        return totalCredit;
    }
    */
}
