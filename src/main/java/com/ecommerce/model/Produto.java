package com.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    private Integer codigo;
    private String nome;
    private Long preco;
    private Integer quanidade;


}
