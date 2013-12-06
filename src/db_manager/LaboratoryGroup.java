package db_manager;

public class LaboratoryGroup {
    private Integer id;
    private String lgroup;

    public LaboratoryGroup() {
    }

    public LaboratoryGroup(Integer id, String lgroup) {
        this.id = id;
        this.lgroup = lgroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLgroup() {
        return lgroup;
    }

    public void setLgroup(String lgroup) {
        this.lgroup = lgroup;
    }
}