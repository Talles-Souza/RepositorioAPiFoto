package com.residencia.comercio.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Arquivo2Service {

	@Value ("${pasta.arquivos.imagem}")
	private String diretorioArquivos;
	
	private Path localFinalArquivo;
	
	@Autowired
	EmailService emailService;
	
	public void criarArquivo (String fileName, MultipartFile file) throws IOException {
	try {
		if(fileName.contains("..")) {
			throw new Exception("Nome do arquivo invalido"); 
		}
		this.localFinalArquivo = Paths.get(diretorioArquivos).toAbsolutePath().normalize();
		
		//System.out.println(this.localFinalArquivo);
		
		Path destinoLocation = localFinalArquivo.resolve(fileName);
		//System.out.println(destinoLocation);
		Files.copy(file.getInputStream(), destinoLocation, StandardCopyOption.REPLACE_EXISTING);
		
		//Cuidado para definir um endereço de destinatario valido abaixo
		String corpoEmail = "Foi cadastrada uma nova categoria";
		emailService.enviarEmailTexto("teste@teste.com", fileName, corpoEmail);
		
	}catch(Exception ex) {
		throw new IOException ("Não foi possivel mover o arquivo " + ex.getStackTrace());
	}
}
}
