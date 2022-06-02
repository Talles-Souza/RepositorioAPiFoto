package com.residencia.comercio.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.comercio.dtos.CepDTO;
import com.residencia.comercio.dtos.CnpjDTO;
import com.residencia.comercio.dtos.FornecedorDTO;
import com.residencia.comercio.entities.Fornecedor;
import com.residencia.comercio.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	@Autowired
	FornecedorRepository fornecedorRepository;

	public List<Fornecedor> findAllFornecedor() {
		return fornecedorRepository.findAll();
	}

	public Fornecedor findFornecedorById(Integer id) {
		return fornecedorRepository.findById(id).isPresent() ? fornecedorRepository.findById(id).get() : null;
	}

	public FornecedorDTO findFornecedorDTOById(Integer id) {
		Fornecedor fornecedor = fornecedorRepository.findById(id).isPresent() ? fornecedorRepository.findById(id).get()
				: null;

		FornecedorDTO fornecedorDTO = new FornecedorDTO();
		if (null != fornecedor) {
			fornecedorDTO = converterEntidadeParaDto(fornecedor);
		}
		return fornecedorDTO;
	}

	public Fornecedor saveFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public FornecedorDTO saveFornecedorDTO(FornecedorDTO fornecedorDTO) {

		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setIdFornecedor(fornecedorDTO.getIdFornecedor());
		Fornecedor novoFornecedor = fornecedorRepository.save(fornecedor);

		return converterEntidadeParaDto(novoFornecedor);
	}

	public Fornecedor updateFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public void deleteFornecedor(Integer id) {
		Fornecedor inst = fornecedorRepository.findById(id).get();
		fornecedorRepository.delete(inst);
	}

	public void deleteFornecedor(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}

	private Fornecedor convertDTOToEntidade(FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setIdFornecedor(fornecedorDTO.getIdFornecedor());
		return fornecedor;
	}

	private FornecedorDTO converterEntidadeParaDto(Fornecedor fornecedor) {
		FornecedorDTO fornecedorDTO = new FornecedorDTO();
		fornecedorDTO.setIdFornecedor(fornecedor.getIdFornecedor());
		return fornecedorDTO;
	}

	public CnpjDTO consultarDadosPorCnpj(String cnpj) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://www.receitaws.com.br/v1/cnpj/{cnpj}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cnpj", cnpj);

		CnpjDTO receitaDadosCnpj = restTemplate.getForObject(uri, CnpjDTO.class, params);

		return receitaDadosCnpj;
	}

	public CepDTO consultarDadosPorCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		CepDTO viaCep = restTemplate.getForObject(url, CepDTO.class, params);

		return viaCep;
	}

	private Fornecedor fornecedorDTOparaEntity(FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = new Fornecedor();

		fornecedor.setBairro(fornecedorDTO.getBairro());
		fornecedor.setCep(fornecedorDTO.getCep());
		fornecedor.setCnpj(fornecedorDTO.getCnpj());
		fornecedor.setComplemento(fornecedorDTO.getComplemento());
		fornecedor.setDataAbertura(fornecedorDTO.getDataAbertura());
		fornecedor.setEmail(fornecedorDTO.getEmail());
		fornecedor.setIdFornecedor(fornecedorDTO.getIdFornecedor());
		fornecedor.setLogradouro(fornecedorDTO.getLogradouro());
		fornecedor.setMunicipio(fornecedorDTO.getMunicipio());
		fornecedor.setNomeFantasia(fornecedorDTO.getNomeFantasia());
		fornecedor.setNumero(fornecedorDTO.getNumero());
		fornecedor.setRazaoSocial(fornecedorDTO.getRazaoSocial());
		fornecedor.setStatusSituacao(fornecedorDTO.getStatusSituacao());
		fornecedor.setTelefone(fornecedorDTO.getTelefone());
		fornecedor.setTipo(fornecedorDTO.getTipo());
		fornecedor.setUf(fornecedorDTO.getUf());

		return fornecedor;
	}

	private FornecedorDTO fornecedorParaDTO(Fornecedor fornecedor) {
		FornecedorDTO fornecedorDTO = new FornecedorDTO();

		fornecedorDTO.setBairro(fornecedor.getBairro());
		fornecedorDTO.setCep(fornecedor.getCep());
		fornecedorDTO.setCnpj(fornecedor.getCnpj());
		fornecedorDTO.setComplemento(fornecedor.getComplemento());
		fornecedorDTO.setDataAbertura(fornecedor.getDataAbertura());
		fornecedorDTO.setEmail(fornecedor.getEmail());
		fornecedorDTO.setIdFornecedor(fornecedor.getIdFornecedor());
		fornecedorDTO.setLogradouro(fornecedor.getLogradouro());
		fornecedorDTO.setMunicipio(fornecedor.getMunicipio());
		fornecedorDTO.setNomeFantasia(fornecedor.getNomeFantasia());
		fornecedorDTO.setNumero(fornecedor.getNumero());
		fornecedorDTO.setRazaoSocial(fornecedor.getRazaoSocial());
		fornecedorDTO.setStatusSituacao(fornecedor.getStatusSituacao());
		fornecedorDTO.setTelefone(fornecedor.getTelefone());
		fornecedorDTO.setTipo(fornecedor.getTipo());
		fornecedorDTO.setUf(fornecedor.getUf());

		return fornecedorDTO;
	}
// Usar esse método para fazer o FornecedorDTO receber cepDTO e CnpjDto
	public Fornecedor CnpjDTOtoFornecedor(CnpjDTO cnpjDTO) {
		Fornecedor fornecedor = new Fornecedor();

		Date data = new Date();
		try {
			data = new SimpleDateFormat("dd/MM/yyyy").parse(cnpjDTO.getAbertura());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		fornecedor.setCnpj(cnpjDTO.getCnpj());
		fornecedor.setDataAbertura(data);
		fornecedor.setEmail(cnpjDTO.getEmail());
		fornecedor.setNomeFantasia(cnpjDTO.getFantasia());
		fornecedor.setRazaoSocial(cnpjDTO.getNome());
		fornecedor.setStatusSituacao(cnpjDTO.getSituacao());
		fornecedor.setTelefone(cnpjDTO.getTelefone());
		fornecedor.setTipo(cnpjDTO.getTipo());

		fornecedor.setCep(cnpjDTO.getCep());
		fornecedor.setNumero(cnpjDTO.getNumero());

		fornecedor.setLogradouro(cnpjDTO.getLogradouro());
		fornecedor.setBairro(cnpjDTO.getBairro());
		fornecedor.setComplemento(cnpjDTO.getComplemento());
		fornecedor.setMunicipio(cnpjDTO.getMunicipio());
		fornecedor.setUf(cnpjDTO.getUf());

		return fornecedor;
	}

}
