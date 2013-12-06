package db_manager;

public class Students {       
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String idnum;
    private String date;
    private int idprob;
    private int idpract;
    private String problemgroup;
    private String labgroup;

    public Students() {
    }

    public Students(Integer id) {
        this.id = id;
    }

    public Students(Integer id, int idprob, int idpract, String idnum, String name, String surname, String email, String problemgroup, String labgroup, String date) {
        this.id = id;
        this.idprob = idprob;
        this.idpract = idpract;
        this.name = name;
        this.surname = surname;
        this.idnum = idnum;
        this.email = email;
        this.problemgroup = problemgroup;
        this.labgroup = labgroup;
        this.date = date;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
