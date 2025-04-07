package pojo.Response;

public class ResponseAddition {

    private Integer id;
    private String additional_info;
    private Integer additional_number;

    public ResponseAddition(Integer id, String additional_info, Integer additional_number) {
        this.id = id;
        this.additional_info = additional_info;
        this.additional_number = additional_number;
    }

    public ResponseAddition() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public Integer getAdditional_number() {
        return additional_number;
    }

    public void setAdditional_number(Integer additional_number) {
        this.additional_number = additional_number;
    }
}
