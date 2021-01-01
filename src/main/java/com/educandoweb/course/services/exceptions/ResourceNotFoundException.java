package com.educandoweb.course.services.exceptions;

/** EXCECAO PERSONALIZADA PARA CLASSE DE SERVIÇOS **/
/** COM ISSO COLOCO A POSSIBILIDADE DA CAMADA DE SERVIÇO LANÇAR EXECÇÕES DELA **/

/** RUNTIMEEXCEPTION O COMPILADOR NÃO TE OBRIGA A TRATAR **/

public class ResourceNotFoundException extends RuntimeException{

    /** construtor responsavel de receber o id o objeto passado e ativar a excecção **/
    public ResourceNotFoundException(Object id){
        super("Recurso não encontrado. Id " + id);
    }
}
