package pojo.Response;

import java.util.List;

public class ResponseEntity {

    private Integer id;
    private String title;
    private Boolean verified;
    private ResponseAddition responseAddition;
    private List<Integer> important_numbers;

    public ResponseEntity(Integer id, String title, Boolean verified, ResponseAddition responseAddition, List<Integer> important_numbers) {
        this.id = id;
        this.title = title;
        this.verified = verified;
        this.responseAddition = responseAddition;
        this.important_numbers = important_numbers;
    }

    public ResponseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public ResponseAddition getResponseAddition() {
        return responseAddition;
    }

    public void setResponseAddition(ResponseAddition responseAddition) {
        this.responseAddition = responseAddition;
    }

    public List<Integer> getImportant_numbers() {
        return important_numbers;
    }

    public void setImportant_numbers(List<Integer> important_numbers) {
        this.important_numbers = important_numbers;
    }
}
