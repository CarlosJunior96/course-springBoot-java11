package com.educandoweb.course.entities.enums;

import com.educandoweb.course.entities.Order;

public enum OrderStatus {
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    /** CONSTRUTOR DO TIPO ENUMERADO **/
    OrderStatus(int code){
        this.code = code;
    }

    /** RETORNANDO MEU CODE **/
    public int getCode(){
        return code;
    }

    /** CONVERTENDO VALOR NUMERICO PARA TIPO ENUMERADO **/
    /** STATIC SIGNIFICA QUE FUNCIONA SEM PRECISAR INSTANCIA
     * values() = percorre todos valores possíveis do ENUM **/
    public static OrderStatus valueOf(int code){
        for(OrderStatus value : OrderStatus.values()){
            if (value.getCode() == code)
                return value;
        }
        throw new IllegalArgumentException("Invalid OrderStatus Code!");
    }
}
