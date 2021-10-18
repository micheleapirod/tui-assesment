package com.tui.proof.search;

import com.tui.proof.model.Client;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchSpecification implements Specification<Client> {

    private final SearchQ searchQ;

    /**
     * @param searchQ
     */
    public SearchSpecification(SearchQ searchQ) {
        this.searchQ = searchQ;
    }

    /**
     * @param root
     * @param criteriaQuery
     * @param criteriaBuilder
     * @return
     */
    @Override
    public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.<String>get(searchQ.getKey()), "%" + searchQ.getValue()+"%");
    }
}
