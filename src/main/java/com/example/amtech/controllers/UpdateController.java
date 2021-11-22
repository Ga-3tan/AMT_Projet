package com.example.amtech.controllers;

import com.example.amtech.models.CategoryService;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class UpdateController {
    ProductService productService;
    CategoryService categoryService;

    @GetMapping("/update-product/{id}")
    public String updateQuantity(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("id",id);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateQuantityPost(@PathVariable String id, @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        model.addAttribute("product", product);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "error";
        }

        productService.updateProduct(id, product);
        return "redirect:/product/" + id;
    }
}