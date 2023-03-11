package customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    private final CustomerRepo repo;

    public CustomerController(CustomerRepo repo){
        this.repo=repo;
    }

    @GetMapping("/customer")
    public List<Customer> all(){
        return repo.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id) throws RuntimeException{
        return repo.findById(id).orElseThrow(HandleException::new);
    }
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Long id){
        repo.deleteById(id);
    }

    @PostMapping("/customer")
    public Customer save(@RequestBody Customer newCustomer){
        return repo.save(newCustomer);
    }
    @PutMapping("customer/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer newCustomer){
        return repo.findById(id).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setEmail(newCustomer.getEmail());
            return repo.save(customer);
        }).orElseGet(() ->{
            return repo.save(newCustomer);
        });
    }
}
