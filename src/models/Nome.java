package models;

/**
 * Created by pedro on 28/11/16.
 */
public class Nome {

    private String primeiro;
    private String familia;

    public Nome(String primeiro, String familia) {
        this.primeiro = primeiro;
        this.familia = familia;
    }

    public String getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(String primeiro) {
        this.primeiro = primeiro;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }
}
