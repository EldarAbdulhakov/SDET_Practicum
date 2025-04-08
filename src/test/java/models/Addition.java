package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class Addition {

    private Integer id;

    @Builder.Default
    private String additional_info = "Дополнительные сведения";

    @Builder.Default
    private Integer additional_number = 123;
}
