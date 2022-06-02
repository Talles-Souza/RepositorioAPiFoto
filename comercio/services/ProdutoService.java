package com.residencia.comercio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.ProdutoRepository;

@Service
public class ProdutoService {
 @Autowired
 ProdutoRepository produtoRepository;
 
 public List<Produto> findAll(){
	 return produtoRepository.findAll();
	  }
 
 public Produto findById(Integer id) {
	 return produtoRepository.findById(id).isPresent() ?produtoRepository.findById(id).get() : null;
 }
  public Produto save(Produto produto) {
	  return produtoRepository.save(produto);
  }
  public Produto update(Produto produto) {
	  return produtoRepository.save(produto);
  }
  public Produto updateComID(Produto produto, Integer id) {
	 
	  Produto produtoBD =produtoRepository.findById(id).isPresent() ?produtoRepository.findById(id).get() : null;
	  Produto produtoAtualizado = null;
	  if (null != produtoBD) {
		produtoBD.setCategoria(produto.getCategoria());
		//...
		produtoAtualizado = produtoRepository.save(produtoBD);
	}
	  return produtoAtualizado;
  }
  public void deleteById(Integer id) {
		produtoRepository.deleteById(id);
	}

  public void deleteProduto(Produto produto) {
      produtoRepository.delete(produto);
  }
  
 /* public Produto updateComId(Produto produto, Integer idProduto) {
		Produto produtoBD = produtoRepository.findById(idProduto).isPresent()
				? produtoRepository.findById(idProduto).get()
				: null;
		
		Produto produtoAtualizado = null;
		if(null != produtoBD) {
			produtoBD.setNomeProduto(produto.getNomeProduto());
			produtoBD.setSku(produto.getSku());
			produtoBD.setFornecedor(produto.getFornecedor());
			produtoBD.setCategoria(produto.getCategoria());

			produtoAtualizado = produtoReposit
dele id service
public void deleteComId(Integer idProduto) {		
		produtoRepository.deleteById(idProduto);
	}	*/
}
