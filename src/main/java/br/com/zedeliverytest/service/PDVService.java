package br.com.zedeliverytest.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zedeliverytest.entity.PDV;
import br.com.zedeliverytest.repository.PDVRepository;

@Service
public class PDVService {

	@Autowired
	private PDVRepository pdvRepository;
	
	public List<PDV> findAll() {
		return pdvRepository.findAll();
	}

	public Optional<PDV> findById(Long id) throws Exception{
		return pdvRepository.findById(id);
	}

	public PDV save(@Valid PDV pdv) throws Exception {
		return pdvRepository.save(pdv);
	}

	public List<PDV> GetNearestByLngAndLat(Double lng, Double lat) {
		return pdvRepository.getNearestByLngAndLat(lng, lat);
	}
	
}
