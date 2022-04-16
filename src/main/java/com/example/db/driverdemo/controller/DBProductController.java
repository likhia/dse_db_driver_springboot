package com.example.db.driverdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.db.driverdemo.service.DBProductService;
import com.example.db.driverdemo.service.Product;
import com.example.db.driverdemo.service.Key;
import com.example.db.driverdemo.service.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("db")
public class DBProductController {

    @Autowired
    private DBProductService dbService;

    @GetMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable String id)  {
        return dbService.getProductById(id);
    }

    @PostMapping("/add")
    public Key addProduct(@RequestBody Product newProduct) {
        return dbService.addProduct(newProduct);
    }

    @DeleteMapping("/delete/{id}")
    public Status deleteProduct(@PathVariable String id)  {
        return dbService.deleteProductById(id);
    }

    @PutMapping("update")
    public Status updateProduct(@RequestBody Product product) {
        return dbService.updateProduct(product);
    }

    

}