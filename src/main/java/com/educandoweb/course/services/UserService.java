package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repository.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){

        /** findById RETORNA UM OPTIONAL/OBJETO DO BANCO DE DADOS **/
        Optional<User> objeto = userRepository.findById(id);

        /** orElseThrow = ele tenta dá o get do optional caso não encontre o objeto ele da uma excecção **/
        return objeto.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insertUser(User usuario){
        return userRepository.save(usuario);
    }

    public void deletarUser(Long id){
        try{
            userRepository.deleteById(id);

            /** captura uma excecção do tipo EmptyResultDataAccessException e lança a excecção ResourceNotFoundException **/
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public User atualizarUser(Long id, User usuario){
        try {
            /** getOne ELE INSTANCIA UM OBJETO E DEIXA ELE MONITORADO PELO JPA, MAS NÃO VAI AINDA NO BD É MELHOR QUE findById
             * getOne = ele prepara objeto monitorado para trabalhar e depois mexer no banco de dados */
            User entidade = userRepository.getOne(id);
            atualizarDados(entidade, usuario);
            /** QUANDO ELE SALVA O OBJETO NO BANCO O JPA DEIXA DE MONITORAR E PREPARAR ESSA ENTIDADE **/
            return userRepository.save(entidade);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void atualizarDados(User entidade, User usuario){
        entidade.setName(usuario.getName());
        entidade.setEmail(usuario.getEmail());
        entidade.setPhone(usuario.getPhone());
    }
}
