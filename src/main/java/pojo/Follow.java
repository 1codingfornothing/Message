package pojo;

public class Follow {
    private Integer followed_id;
    private String follow_name;
    private Integer id;

    public Integer getFollowed_id() {
        return followed_id;
    }

    public void setFollowed_id(Integer followed_id) {
        this.followed_id = followed_id;
    }

    public String getFollow_name() {
        return follow_name;
    }

    public void setFollow_name(String follow_name) {
        this.follow_name = follow_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followed_id=" + followed_id +
                ", follow_name='" + follow_name + '\'' +
                ", id=" + id +
                '}';
    }

    public Follow(Integer followed_id, String follow_name, Integer id) {
        this.followed_id = followed_id;
        this.follow_name = follow_name;
        this.id = id;
    }

    public Follow() {
    }
}
