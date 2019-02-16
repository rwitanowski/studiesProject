package app_data.app_api;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface Api {
    public void translate(String word) throws UnirestException;
}
