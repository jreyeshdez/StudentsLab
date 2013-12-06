package db_manager;

public class Students {       
    private Integer id;
    private String sname;
    private String surname;
    private String email;
    private String idnum;
    private String sdate;
    private int idprob;
    private int idpract;
    private String problemgroup;
    private String labgroup;

    public Students() {
    }

    public Students(Integer id) {
        this.id = id;
    }

    public Students(Integer id, int idprob, int idpract, String idnum, String sname, String surname, String email, String problemgroup, String labgroup, String sdate) {
        this.id = id;
        this.idprob = idprob;
        this.idpract = idpract;
        this.sname = sname;
        this.surname = surname;
        this.idnum = idnum;
        this.email = email;
        this.problemgroup = problemgroup;
        this.labgroup = labgroup;
        this.sdate = sdate;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public int getIdprob() {
        return idprob;
    }

    public void setIdprob(int idprob) {
        this.idprob = idprob;
    }

    public int getIdpract() {
        return idpract;
    }

    public void setIdpract(int idpract) {
        this.idpract = idpract;
    }

    public String getProblemgroup() {
        return problemgroup;
    }

    public void setProblemgroup(String problemgroup) {
        this.problemgroup = problemgroup;
    }

    public String getLabgroup() {
        return labgroup;
    }

    public void setLabgroup(String labgroup) {
        this.labgroup = labgroup;
    }
}
