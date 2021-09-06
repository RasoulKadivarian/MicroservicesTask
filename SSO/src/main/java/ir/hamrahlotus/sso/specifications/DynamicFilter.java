package ir.hamrahlotus.sso.specifications;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicFilter {
    private String field;
    private QueryOperator queryOperator;
    private Object value;
    private List<Object> inValues;
}
