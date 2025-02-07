package api;

public class SuccessRegisterResponse {
    private Integer id;
    private String token;

    public SuccessRegisterResponse() {
    }

    public SuccessRegisterResponse(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}