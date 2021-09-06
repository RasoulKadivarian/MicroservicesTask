package ir.hamrahlotus.sso.specifications;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Getter
public class GenericSpecification<T> implements Specification<T> {

    private List<DynamicFilter> filterList;

    public GenericSpecification() {
        this.filterList = new LinkedList<>();
    }

    public void addFilter(DynamicFilter filter) {
        this.filterList.add(filter);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new LinkedList<>();

        for (DynamicFilter filter : this.filterList) {
            switch (filter.getQueryOperator()) {
                case EQUAL:
                    predicates.add(criteriaBuilder.equal(root.get(filter.getField()), filter.getValue()));
                    break;
                case NOT_EQUAL:
                    predicates.add(criteriaBuilder.notEqual(root.get(filter.getField()), filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.add(criteriaBuilder.lessThan(root.get(filter.getField()), filter.getValue().toString()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(filter.getField()), filter.getValue().toString()));
                    break;
                case GREATER_THAN:
                    predicates.add(criteriaBuilder.greaterThan(root.get(filter.getField()), filter.getValue().toString()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getField()), filter.getValue().toString()));
                    break;
                case IN:
                    predicates.add(criteriaBuilder.in(root.get(filter.getField()).in(filter.getInValues())));
                    break;
                case NOT_IN:
                    predicates.add(criteriaBuilder.not(root.get(filter.getField()).in(filter.getInValues())));
                    break;
                case LIKE:
                    predicates.add(criteriaBuilder.like(root.get(filter.getField()), '%' + filter.getValue().toString() + '%'));
                    break;
                case NOT_LIKE:
                    predicates.add(criteriaBuilder.notLike(root.get(filter.getField()), '%' + filter.getValue().toString() + '%'));
                    break;
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
