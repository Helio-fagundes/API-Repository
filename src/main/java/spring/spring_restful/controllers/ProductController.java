package spring.spring_restful.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.spring_restful.dtos.ProductRecordDto;
import spring.spring_restful.models.ProductModel;
import spring.spring_restful.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController//indica que a classe retornará endpoints REST
public class ProductController {

    @Autowired //intetar dependências automáticas
    ProductRepository productRepository;

    //Metodo Post
    //quando alguem acessar a URL /products, o metodo saveProduct será chamado
    //o metodo retornará um ResponseEntity do tipo ProductModel
    //o metodo se chama saveProduct e recebe um corpo(dados enviados na requisição http) como um ProductRecordDto
    //o @requestBody recebe um JSON e converte para um objeto e o @valid faz a validação
    //o BeanUtils é usado para copiar as propriedades do objeto ProductRecordDto para o objeto ProductModel, em resumo, fará a conversão de productRecordDto para ProductModel
    //o ResponseEntity é usado para retornar uma resposta HTTP com o status 201(Criado) e o corpo da resposta q será salvo será o objeto ProductModel
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    //Metodo GetAll
    //quando alguem acessar a URL /products, o metodo getAllProducts será chamado
    //o metodo retornará um ResponseEntity do tipo List<ProductModel>
    //o metodo vai buscar todos os produtos no banco de dados
    //o methodOn é usado para criar um link para o metodo getOneProduct
    //o for é usado para adicionar o link de cada produto na lista de produtos
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        List<ProductModel> productsList = productRepository.findAll();
        if (!productsList.isEmpty()){
            for (ProductModel product : productsList){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    //Metodo GetOne
    //quando alguem acessar a URL /products/{id}, o metodo getOneProduct será chamado
    //o metodo retornará um ResponseEntity do tipo Object
    //o @PathVariable é usado para pegar o id do produto que foi passado na URL
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        product0.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    //Metodo Update
    //quando alguem acessar a URL /products/{id}, o metodo updateProduct será chamado
    //o metodo retornará um ResponseEntity do tipo Object
    //o metodo vai receber os novos parametros nome e valor do produto @RequestBody
    //o Optional<ProductModel> product0 = productRepository.findById(id); vai buscar o produto pelo id
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    //Metodo Delete
    //quando alguem acessar a URL /products/{id}, o metodo deleteProduct será chamado
    //o metodo retornará um ResponseEntity do tipo Object
    //o @PathVariable é usado para pegar o id do produto que foi passado na URL
    //productRepository.delete(product0.get()); vai deletar o produto
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(product0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
