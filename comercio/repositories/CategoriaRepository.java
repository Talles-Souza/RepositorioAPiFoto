package com.residencia.comercio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.comercio.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

	Boolean existsByNomeImagem(String nomeImagem); 

}