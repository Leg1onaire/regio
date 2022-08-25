package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Location {
    OSTRAVA(10202000, "Ostrava"),
    BRNO(10202002, "Brno");

    @Getter
    Integer id;
    @Getter
    String name;
}
