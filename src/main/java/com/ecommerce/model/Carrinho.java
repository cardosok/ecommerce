package com.ecommerce.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrinho {

    private String Email;
    private List<Produto> listaProdutos;
}
