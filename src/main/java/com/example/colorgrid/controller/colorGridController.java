package com.example.colorgrid.controller;

import com.example.colorgrid.service.ColorGridService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/colorGrid")
@AllArgsConstructor
public class colorGridController {

    private final ColorGridService colorGridService;

    @PostMapping("getData")
    public String getAllCustomers(@RequestParam("cols") int cols,
                                  @RequestParam("rows") int rows){
        return  colorGridService.getDate(cols,rows);
    }
}
