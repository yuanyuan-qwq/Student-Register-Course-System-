package model;

public class Professor extends user{ //professor information
    private String ProfID,office,department;

    public String getProfID() {
        return ProfID;
    }

    public void setProfID(String profID) {
        ProfID = profID;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
