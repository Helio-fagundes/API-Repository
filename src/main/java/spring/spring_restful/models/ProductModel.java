package spring.spring_restful.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    private static final long serialVersionUID = 1L; //usado para serialização(controle de versão) sem perder a compatibilidade.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //gerar um @ID automaticamente
    private UUID idProduct; //Indentificador Unico Universal
    private String Name;
    private BigDecimal value; //BigDecimal serve para valores exatos sem arredondamento

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
