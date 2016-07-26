package com.example.Web;

import com.example.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by xl on 16/7/26.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    static Map<String, User>  users = Collections.synchronizedMap(new HashMap<String, User>());

    @RequestMapping(value = "/test")
    public String test(){
        return "test is ok";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        List<User> list = new ArrayList<User>(users.values());
        return list;
    }

    @RequestMapping(value = "/", method =  RequestMethod.POST)
    public String addUser(@ModelAttribute User user){
        users.put(user.getName(), user);
        return "success";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable("name") String name){
        return users.get(name);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    public String putUser(@PathVariable("name") String name, @ModelAttribute User user){
        User temp = users.get(name);
        temp.setAge(user.getAge());
        temp.setSex(user.getSex());
        users.put(name, temp);
        return "success";
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("name") String name){
        User user = users.get(name);
        users.remove(user);
        return "success";
    }
}

