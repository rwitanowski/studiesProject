package app_data.app_user;


import java.io.Serializable;

public class User implements Serializable {
    private String user;
    private int score;

    public User(String user, int score) {
        this.user = user;
        this.score = score;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return user +" Score: "+ score;
    }
}

