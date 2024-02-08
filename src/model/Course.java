package model;

public class Course {//course information
    private String courseID,CourseName;
    private int CreditHours;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public int getCreditHours() {
        return CreditHours;
    }

    public void setCreditHours(int creditHours) {
        CreditHours = creditHours;
    }
}
