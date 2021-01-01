package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    /** SO FUNCIONA SE O SERVICE ESTIVER DECLARADO COMO UM COMPONENTE DO
     * SPRING BOOT **/
    @Autowired
    private UserService service;
    /** RESPONSE ENTITY TIPO ESPECIFICO QUE RETORNA REQUISIÇÕES WEB
     *  **/

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User>buscarId(@PathVariable Long id){
        User objeto = service.findById(id);
        return ResponseEntity.ok().body(objeto);
    }

    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User usuario){
        User user = service.insertUser(usuario);

        /** CRIANDO O LOCATION URI PARA O MÉTODO CREATED DO HTTP **/
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        /** METODO CREATED DO HTTP É ESPERADO QUE ELE RETORNE O CABECALHO/ENDEREÇO DO RECURSO INSERIDO **/
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Long id){
        service.deletarUser(id);

        /** NO CONTENT INFORMA QUE NÃO TEM RETORNO E INFORMA O CÓDIGO HTTP DE EXCLUSAO 204**/
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> atualizarUser(@PathVariable Long id, @RequestBody User usuario){
        usuario =  service.atualizarUser(id, usuario);
        return ResponseEntity.ok().body(usuario);
    }
}
