package model;

public class Registration { //combine student and course information to make table for registration
    private String MID,C_courseID,grade,ProfID,courseName;
    private int creditHours;

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getC_courseID() {
        return C_courseID;
    }

    public void setC_courseID(String c_courseID) {
        C_courseID = c_courseID;
    }

    public String getProfID() {
        return ProfID;
    }

    public void setProfID(String prodID) {
        ProfID = prodID;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
}
