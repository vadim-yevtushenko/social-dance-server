package com.example.socialdanceserver.persistence.dao;

import com.example.socialdanceserver.persistence.entity.AbstractBaseEntity;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.PluralAttribute;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class EntityDao <T extends AbstractBaseEntity> {

    private Class<T> persistentClass;

    @Autowired
    protected EntityManager entityManager;

    public EntityDao() {
        Class<?> clazz = getClass();
        while (persistentClass == null && clazz.getSuperclass() != null) {
            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                this.persistentClass = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
            } else {
                clazz = clazz.getSuperclass();
            }
        }
    }

    public List<T> load(PaginationRequest paginationRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(persistentClass);
        Root<T> root = criteriaQuery.from(persistentClass);
        criteriaQuery.distinct(true);
        criteriaQuery.select(root);

        if (paginationRequest.getOrders() != null && !paginationRequest.getOrders().isEmpty()){
            List<Order> orders = buildOrders(paginationRequest.getOrders(), builder, root);
            criteriaQuery.orderBy(orders);
        }

        if (paginationRequest.getPredicates() != null && !paginationRequest.getPredicates().isEmpty()){
            List<Predicate> predicateList = buildPredicates(paginationRequest.getPredicates(), builder, root);
            criteriaQuery.where(predicateList.toArray(new Predicate[]{}));
        }

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult(paginationRequest.calculateFirstIndex());
        query.setMaxResults(paginationRequest.getPageSize());

        return query.getResultList();
    }

    public int getTotal(Map<String, String> predicates) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Number> criteriaQuery = builder.createQuery(Number.class);
        Root<T> root = criteriaQuery.from(persistentClass);

        if (predicates != null && !predicates.isEmpty()){
            List<Predicate> predicateList = buildPredicates(predicates, builder, root);
            criteriaQuery.where(predicateList.toArray(new Predicate[]{}));
        }

        criteriaQuery.select(builder.count(root));

        TypedQuery<Number> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult().intValue();
    }

    protected abstract List<Order> buildOrders(List<String> orders, CriteriaBuilder builder, Root<T> root);

    protected abstract List<Predicate> buildPredicates(Map<String, String> predicates, CriteriaBuilder builder, Root<T> root);

    protected Predicate buildLikeContainingStringIgnoringCase(CriteriaBuilder builder, Expression<String> property, String value) {
        return builder.like(builder.lower(property), builder.lower(builder.literal("%" + value + "%")));
    }

    protected <E extends AbstractBaseEntity> Join<T, E> getExistingOrCreateNewJoin(Root<T> root, PluralAttribute<T, ?, E> attribute) {
        return (Join<T, E>) root.getJoins().stream()
                .filter(join -> attribute.getName().equals(join.getAttribute().getName())).findFirst()
                .orElseGet(() -> root.join(attribute.getName()));
    }

}
