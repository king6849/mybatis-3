package Demo.dao;

import Demo.pojo.Customer;

import java.util.List;

public interface CustomerDao {

  List<Customer> findAll();

  Customer findCustomerById(Customer customer);

}
