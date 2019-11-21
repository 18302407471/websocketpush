package com.websocketpush.controller;

import com.websocketpush.ThreaPool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public ModelAndView welcome(){
        ModelAndView view = new ModelAndView("index");
        return view;
    }


    @GetMapping("/senMessage")
    public void senMessage(){
       ThreaPool threaPool = new ThreaPool(5,5);
       while (true){
            int i=0;
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
           threaPool.submitTask((i++)+"str");
       }
    }

}
