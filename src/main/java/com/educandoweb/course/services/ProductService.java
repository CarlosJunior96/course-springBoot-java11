package com.educandoweb.course.services;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        List<Product> lista = productRepository.findAll();
        return lista;
    }

    public Product findById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.get();
    }

}
