package de.adnova.doenerapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.adnova.doenerapp.model.OrderList;
import de.adnova.doenerapp.repository.DoenerAppRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DoenerAppController {

	@Autowired
	DoenerAppRepository foodOrderRepository;

	@GetMapping("/GetAllOrderLists")
	public ResponseEntity<List<OrderList>> getAllOrderLists(@RequestParam(required = false) String restaurant) {
		try {
		    List<OrderList> orderLists = new ArrayList<OrderList>();		    

		    if (restaurant == null)
		    	foodOrderRepository.findAll().forEach(orderLists::add);
		    else
		    	foodOrderRepository.findByRestaurantContaining(restaurant).forEach(orderLists::add);
		    
		    Collections.sort(orderLists);

		    if (orderLists.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }

		    return new ResponseEntity<>(orderLists, HttpStatus.OK);
		  } catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	}

	@GetMapping("/GetOrderList/{id}")
	public ResponseEntity<OrderList> getOrderListById(@PathVariable("id") String id) {
		Optional<OrderList> orderListData = foodOrderRepository.findById(id);

		  if (orderListData.isPresent()) {
		    return new ResponseEntity<>(orderListData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}

	@PostMapping("/CreateOrderList")
	public ResponseEntity<OrderList> createOrderList(@RequestBody OrderList orderList) {
		try {
			OrderList _orderList = foodOrderRepository.save(orderList);
			return new ResponseEntity<>(_orderList, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/UpdateOrderList/{id}")
	public ResponseEntity<OrderList> updateOrderList(@PathVariable("id") String id, @RequestBody OrderList orderList) {
		Optional<OrderList> data = foodOrderRepository.findById(id);

		  if (data.isPresent()) {
			  OrderList _orderList = data.get();
		    _orderList.setRestaurant(orderList.getRestaurant());
		    _orderList.setAbholer(orderList.getAbholer());
		    _orderList.setOrders(orderList.getOrders());
		    return new ResponseEntity<>(foodOrderRepository.save(_orderList), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	}

	@DeleteMapping("/DeleteOrderList/{id}")
	public ResponseEntity<HttpStatus> deleteOrderList(@PathVariable("id") String id) {
		try {
			foodOrderRepository.deleteById(id);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		  }
	}

	@DeleteMapping("/DeleteAllOrderLists")
	public ResponseEntity<HttpStatus> deleteAllOrderLists() {
		try {
			foodOrderRepository.deleteAll();
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  } catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		  }
	}


}
