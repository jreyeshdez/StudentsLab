package db_manager;

public class ProblemGroups {
    private Integer id;
    private String pgroup;

    public ProblemGroups() {
    }

    public ProblemGroups(Integer id, String pgroup) {
        this.id = id;
        this.pgroup = pgroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPgroup() {
        return pgroup;
    }

    public void setPgroup(String pgroup) {
        this.pgroup = pgroup;
    }
}
