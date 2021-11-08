package com.ecommerce.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoRequest {

    private Integer codproduto;
    private String email;
    private Integer quantidade;

}
