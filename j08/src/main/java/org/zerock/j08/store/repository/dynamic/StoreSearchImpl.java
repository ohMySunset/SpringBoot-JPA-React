package org.zerock.j08.store.repository.dynamic;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j08.store.entity.QProduct;
import org.zerock.j08.store.entity.QStore;
import org.zerock.j08.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class StoreSearchImpl extends QuerydslRepositorySupport implements StoreSearch {

    public StoreSearchImpl() {
        super(Store.class);
    }

    @Override
    public Page<Object[]> getSearchList(String keyword, String type, Pageable pageable) {

        QStore store = QStore.store;
        QProduct product = QProduct.product;

        JPQLQuery<Store> query = from(store);

        query.leftJoin(product).on(product.store.eq(store));

        JPQLQuery<Tuple> tuple = query.select(store, product.countDistinct());

        if (keyword != null && type != null) {
            BooleanBuilder condition = new BooleanBuilder();

            String[] arrType = keyword.split("");

            for (String i : arrType) {
                if (type.equals("n")) {
                    condition.or(store.storeName.contains(keyword));
                } else if (type.equals("a")) {
                    condition.or(store.address.contains(keyword));
                }

            }//end for
            tuple.where(condition);
        }

        tuple.where(store.id.gt(0));
        tuple.groupBy(store);
        tuple.orderBy(store.id.desc());

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> arrList = tupleList.stream().map(tuple1 ->
                tuple1.toArray()).collect(Collectors.toList());

        long totalCount = tuple.fetchCount();

        return new PageImpl<>(arrList, pageable, totalCount);
    }
}
