package pojo.Request;

import java.util.List;

public class Entity {

    public Addition addition;
    public List<Integer> important_numbers;
    public String title;
    public Boolean verified;

    public Entity(Addition addition, List<Integer> important_numbers, String title, Boolean verified) {
        this.addition = addition;
        this.important_numbers = important_numbers;
        this.title = title;
        this.verified = verified;
    }

    public Addition getAddition() {
        return addition;
    }

    public void setAddition(Addition addition) {
        this.addition = addition;
    }

    public List<Integer> getImportant_numbers() {
        return important_numbers;
    }

    public void setImportant_numbers(List<Integer> important_numbers) {
        this.important_numbers = important_numbers;
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
}
