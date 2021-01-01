package com.educandoweb.course.entities;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** CLASSE INDEPENDENTE DE PAGAMENTO **/

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    /** TIPO RELACIONAMENTO **/
    /** NOME DA CHAVE ESTRANGEIRA NA COLUNA **/
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User client;

    /** É DECLARADO ASSIM PORQUE QUE RELMENTE TEM O OBJETO PEDIDO É O ORDER
     * ENTÃO É USADO  id.order **/
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    /** CASCADE TYPE ALL INDICA QUE O JPA VAI ESTÁ MAPEADO PARA O PAGAMENTO TER O MESMO ID DO PEDIDO **/
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public Order(){

    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        super();
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    /** RECEBENDO UM INTEIRO E TRANSFORMANDO PARA OrderStatus **/
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null)
            this.orderStatus = orderStatus.getCode();
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getTotal(){
        double auxiliar = 0.0;
        for (OrderItem x : items){
            auxiliar += x.getSubTotal();
        }
        return auxiliar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
