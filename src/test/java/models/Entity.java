package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder

public class Entity {

    private Integer id;

    private Addition addition;

    @Builder.Default
    private List<Integer> important_numbers = List.of(42, 87, 15);

    @Builder.Default
    private String title = "Заголовок сущности";

    @Builder.Default
    private Boolean verified = true;
}
