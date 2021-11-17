package br.com.serratec.trabalhofinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import br.com.serratec.trabalhofinal.model.Endereco;
import br.com.serratec.trabalhofinal.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco buscarEndereco(String cep) throws HttpClientErrorException{
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return endereco.get();
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String uriViaCep = "https://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uriViaCep, Endereco.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replace("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return inserirEndereco(enderecoViaCep.get());
			} else {
				return null;
			}
		}
	}

	public Endereco inserirEndereco(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}

}
