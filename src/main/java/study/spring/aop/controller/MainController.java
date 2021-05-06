package study.spring.aop.controller;

import org.springframework.web.bind.annotation.*;
import study.spring.aop.dto.User;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/get/{id}")
    public String  get(@PathVariable Long id, @RequestParam String name){
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        return  id + " "+name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        System.out.println("post method = " + user);
        return user;
    }
}
