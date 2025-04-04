package spring.spring_restful.dtos;

//são anotações que servem para implementar a validação de dados em Java, no caso: NotBlank e NotNull.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

//DTO(Data Transfer Object) - Objeto de Transferência de Dados
//O DTO é um padrão de projeto usado para transferir dados entre processos, como entre um cliente e um servidor.
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value) {


}
