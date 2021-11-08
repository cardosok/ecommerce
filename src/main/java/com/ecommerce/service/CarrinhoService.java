package com.ecommerce.service;

import com.ecommerce.entity.ProdutoEntity;
import com.ecommerce.model.Carrinho;
import com.ecommerce.model.Produto;
import com.ecommerce.request.CarrinhoRequest;
import com.ecommerce.response.CarrinhoResponse;
import com.ecommerce.response.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class CarrinhoService {

    private List<Carrinho> listaCarrinho = new ArrayList<>();

    @Autowired
    private ProdutoService produtoService;

    public ResponseEntity checkoutUsuario(String emailUsuario){

        Optional<Carrinho> carrinho = listaCarrinho.stream()
                .filter(carrinho1 -> carrinho1.getEmail().equals(emailUsuario))
                .findFirst();

        if (carrinho.isPresent()) {
            List<Produto> produtoList = carrinho.get().getListaProdutos();

            Long valorTotal = 0L;
            List<ProdutoResponse> produtoResponses = new ArrayList<>();
            for (Produto produto : produtoList) {
                Long valortotalProduto = 0L;
                Optional<ProdutoEntity> entity = produtoService.find(produto.getCodigo());

                if (entity.isPresent()) {
                    produtoResponses.add(ProdutoResponse.builder()
                            .nomeproduto(entity.get().getNome())
                            .preco(entity.get().getPreco())
                            .quantidade(produto.getQuanidade())
                            .build());
                    valortotalProduto = entity.get().getPreco() * produto.getQuanidade();
                }

                valorTotal += valortotalProduto;
            }

            CarrinhoResponse carrinhoResponse = CarrinhoResponse.builder()
                    .produtoResponse(produtoResponses)
                    .valorTotal(valorTotal)
                    .build();

            listaCarrinho.remove(carrinho.get());
            return ResponseEntity.ok(carrinhoResponse);
        }else{
            return ResponseEntity.noContent().build();
        }

    }


    public ResponseEntity addProdutoCarrinho(CarrinhoRequest carrinhoRequest){

        Optional<Carrinho> carrinho = listaCarrinho.stream()
                .filter(car -> car.getEmail().equals(carrinhoRequest.getEmail()))
                .findFirst();

        if (carrinho.isEmpty()){
            Carrinho carrinhoModel = builderCarrinho(carrinhoRequest);
            listaCarrinho.add(carrinhoModel);
            return ResponseEntity.ok(carrinhoModel);
        }else{
            AtomicReference<Boolean> existe = new AtomicReference<>(false);
            carrinho.get().getListaProdutos().forEach(produto -> {
                if(produto.getCodigo().equals(carrinhoRequest.getCodproduto())){
                    produto.setQuanidade(produto.getQuanidade() + carrinhoRequest.getQuantidade());
                    existe.set(true);
                }
            });

            if(!existe.get()){
                carrinho.get().getListaProdutos().add(Produto.builder()
                        .codigo(carrinhoRequest.getCodproduto())
                        .quanidade(carrinhoRequest.getQuantidade())
                        .build());
            }

            return ResponseEntity.ok(carrinho.get());
        }
    }

    private Carrinho builderCarrinho(CarrinhoRequest carrinhoRequest) {


        List<Produto> produtos =new ArrayList<>();
        produtos.add(new Produto().builder()
                .codigo(carrinhoRequest.getCodproduto())
                .quanidade(carrinhoRequest.getQuantidade())
                .build());


        Carrinho carrinhoModel = Carrinho.builder()
                .Email(carrinhoRequest.getEmail())
                .listaProdutos(produtos)
                .build();
        return carrinhoModel;
    }

}
