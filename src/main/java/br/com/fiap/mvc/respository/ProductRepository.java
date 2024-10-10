package br.com.fiap.mvc.respository;


import br.com.fiap.mvc.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    default List<Product> findByCriteria(String name, String isAvailable, Double minValue, Double maxValue) {
        return findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty())
                predicates.add(builder.like(root.get("name"), "%" + name + "%"));

            if (isAvailable != null && !isAvailable.isEmpty()) {
                boolean available = Boolean.parseBoolean(isAvailable);
                predicates.add(builder.equal(root.get("isAvailable"), available));
            }

            if (minValue != null)
                predicates.add(builder.greaterThanOrEqualTo(root.get("value"), minValue));

            if (maxValue != null)
                predicates.add(builder.lessThanOrEqualTo(root.get("value"), maxValue));


            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}