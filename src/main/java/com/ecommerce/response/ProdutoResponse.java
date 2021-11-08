package com.ecommerce.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoResponse {
    @JsonProperty
    private String nomeproduto;
    @JsonProperty
    private Long preco;
    @JsonProperty
    private Integer quantidade;
}
