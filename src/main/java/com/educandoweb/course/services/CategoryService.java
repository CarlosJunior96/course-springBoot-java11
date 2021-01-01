package com.educandoweb.course.services;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        List<Category> lista = categoryRepository.findAll();
        return lista;
    }

    public Category findById(Long id){
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.get();
    }
}
