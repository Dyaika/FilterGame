package pojo;

public class ServerLevel {
    private long id;
    private String userName;
    private String date;
    private int height;
    private long views;
    private String solution_matrix_str;

    public ServerLevel(long id, String userName, String date, int height, long views, String solution_matrix_str) {
        this.id = id;
        this.userName = userName;
        this.date = date;
        this.height = height;
        this.views = views;
        this.solution_matrix_str = solution_matrix_str;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDate() {
        return date;
    }

    public int getHeight() {
        return height;
    }

    public long getViews() {
        return views;
    }

    public String getSolution_matrix_str() {
        return solution_matrix_str;
    }

    public void addViews(long views) {
        this.views += Math.abs(views);
    }

}
