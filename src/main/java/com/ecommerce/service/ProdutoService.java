package com.ecommerce.service;

import com.ecommerce.entity.ProdutoEntity;
import com.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoEntity save(ProdutoEntity produto){
        return  produtoRepository.save(produto);
    }

    public Optional<ProdutoEntity> find(Integer idProduto){
        return  produtoRepository.findById(idProduto);
    }

}
