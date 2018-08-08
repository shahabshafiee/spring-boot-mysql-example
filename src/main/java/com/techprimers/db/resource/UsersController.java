package com.techprimers.db.resource;

import com.techprimers.db.model.Users;
import com.techprimers.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping(value = "/all/{id}")
    public Users getUserById(@PathVariable int id){
      Users users =  usersRepository.findOne(id);
      return users;
    }

    @PutMapping(value = "/all/{id}")
    public Users updateUserById(@PathVariable int id, @RequestBody Users users){
        if(users.getId().equals(id)) {
            usersRepository.save(users);
            return users;
        }
        final Users o = null;
        return o;
    }

    @DeleteMapping(value = "/all/{id}")
    public String deleteUserById(@PathVariable int id){
        usersRepository.delete(id);
        return ("User with id: "+ id + " was deleted successfully");
    }

    @PostMapping(value = "/load")
    public List<Users> persist(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }

}
