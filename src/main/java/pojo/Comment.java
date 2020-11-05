package pojo;

public class Comment {
    private Integer id;
    private Integer message_id;
    private String content;
    private String created_by;
    private Integer like;
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment(Integer id, Integer message_id, String content, String created_by, Integer like) {
        this.id = id;
        this.message_id = message_id;
        this.content = content;
        this.created_by = created_by;
        this.like = like;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", message_id=" + message_id +
                ", content='" + content + '\'' +
                ", created_by='" + created_by + '\'' +
                ", like=" + like +
                '}';
    }
}
