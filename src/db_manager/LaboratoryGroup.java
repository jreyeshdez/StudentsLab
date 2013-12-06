package db_manager;

public class LaboratoryGroup {
    private Integer id;
    private String group;

    public LaboratoryGroup() {
    }

    public LaboratoryGroup(Integer id, String group) {
        this.id = id;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}