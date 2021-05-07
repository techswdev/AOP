package study.spring.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.spring.aop.annotation.Decode;
import study.spring.aop.annotation.Timer;
import study.spring.aop.dto.User;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/get/{id}")
    public String  get(@PathVariable Long id, @RequestParam String name){
        return  id + " "+name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        return user;
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public  void delete() throws InterruptedException {
        Thread.sleep(1000*2);
    }
}
