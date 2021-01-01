package com.educandoweb.course.entities.pk;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/** CLASSE AUXILIAR DE CHAVE PRIMÁRIA COMPOSTA
 *  QUE TERÁ REFERENCIA PARA PRODUTO E PEDIDO
 *  É UMA CLASSE CRIADA COM A RESPONSABILIDADE DE FAZER A FUNÇÃO DE
 *  CHAVE COMPOSTA (PRODUTO + PEDIDO)
 */

@Embeddable
public class OrderItemPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /** PARA COMPARAR ITEM DE PEDIDO VAMOS USAR
     * TANTO O PEDIDO QUANDO O PRODUTO */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return order.equals(that.order) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
