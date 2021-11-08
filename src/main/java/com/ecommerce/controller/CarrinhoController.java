package com.ecommerce.controller;

import com.ecommerce.model.Carrinho;
import com.ecommerce.request.CarrinhoRequest;
import com.ecommerce.response.CarrinhoResponse;
import com.ecommerce.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ecommerce/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/add")
    public ResponseEntity<Carrinho> addProduto(@RequestBody CarrinhoRequest carrinhoRequest){
        return carrinhoService.addProdutoCarrinho(carrinhoRequest);
    }

    @GetMapping("/checkout")
    public ResponseEntity<CarrinhoResponse> checkoutUsuario(@RequestBody String usuario){
        return carrinhoService.checkoutUsuario(usuario);
    }
}
