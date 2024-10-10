package br.com.fiap.mvc.controller;

import br.com.fiap.mvc.model.Product;
import br.com.fiap.mvc.model.ProductCategory;
import br.com.fiap.mvc.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("register")
    public String register(Product product, Model model){
        model.addAttribute("categoryValue", ProductCategory.values());
        return "product/register";
    }

    @PostMapping("register")
    @Transactional
    public String register(Product product, RedirectAttributes redirectAttributes){
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("msg", "Produto registrado!");
        return "redirect:/product/register";
    }

    @GetMapping("list")
    public String list(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "isAvailable", required = false) String isAvailable,
                       @RequestParam(value = "minValue", required = false) Double minValue,
                       @RequestParam(value = "maxValue", required = false) Double maxValue,
                       Model model){
        List<Product> products = productRepository.findByCriteria(name, isAvailable, minValue, maxValue);
        model.addAttribute("products", products);
        return "product/list";
    }

    @PostMapping("update")
    @Transactional
    public String update(Product product, RedirectAttributes redirectAttributes){
        productRepository.save(product);
        redirectAttributes.addFlashAttribute("msg", "Produto atualizado!");
        return "redirect:/product/list";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productRepository.findById(id));
        model.addAttribute("categoryValue", ProductCategory.values());
        return "product/update";
    }


    @PostMapping("delete")
    @Transactional
    public String delete(Long idProduct, RedirectAttributes redirectAttributes){
        productRepository.deleteById(idProduct);
        redirectAttributes.addFlashAttribute("msg", "Produto deletado!");
        return "redirect:/product/list";
    }

}
