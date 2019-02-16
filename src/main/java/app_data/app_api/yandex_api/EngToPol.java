package app_data.app_api.yandex_api;

import app_data.app_api.Api;
import app_data.app_files.EasyLevel;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EngToPol extends EasyLevel implements Api{
    private String translatedWord;
    public String getTranslatedWord() {
        return translatedWord;
    }
    public static final String SERVER_ADDRESS
            = "https://translate.yandex.net/api/v1.5/tr.json/translate?lang=en-pl&key=trnsl.1.1.20181218T213600Z.7179416372605f18.436b9c3625f64fc942b57cd4c4d6574ab8224dbf&text=";

    public void translate(String word) throws UnirestException {
        UnirestUtils.setJacksonObjectMapper();
         EngToPolDTO engToPolDTO =
                Unirest.get(SERVER_ADDRESS + word)
                        .asObject(EngToPolDTO.class)
                        .getBody();
        translatedWord =(String.valueOf(engToPolDTO.getText().get(0)));
    }

}
