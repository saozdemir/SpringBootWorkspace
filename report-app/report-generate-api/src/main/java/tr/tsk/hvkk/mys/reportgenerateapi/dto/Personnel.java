package tr.tsk.hvkk.mys.reportgenerateapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Personnel {
    private Long id;
    private String firstName;
    private String lastName;
    private Department department;
}
