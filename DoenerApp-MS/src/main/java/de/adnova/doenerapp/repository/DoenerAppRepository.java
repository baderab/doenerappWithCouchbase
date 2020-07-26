package de.adnova.doenerapp.repository;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;

import de.adnova.doenerapp.model.OrderList;

public interface DoenerAppRepository extends CouchbasePagingAndSortingRepository<OrderList, String>{
	List<OrderList> findByRestaurantContaining(String place);
}
