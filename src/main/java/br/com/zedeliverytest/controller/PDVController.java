package br.com.zedeliverytest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zedeliverytest.service.PDVService;
import br.com.zedeliverytest.entity.PDV;

/**
 * @author Fernando Henrique Guimarães
 * 
 */
@RestController
class PDVController {
	
	private static final Logger log = LoggerFactory.getLogger(PDVController.class);
	
	@Autowired
	private PDVService service;

	/**
	 * Get partner by id
	 * @param Long id
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/pdv/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> GetById(@PathVariable(value = "id") Long id)
    {
		try {
			log.trace("## [PDV controller] Buscando o id {} do PDV", id);

			Optional<PDV> pdv = (Optional<PDV>) service.findById(id);
			
	        if(pdv.isPresent()) {
	        	log.trace("## [PDV controller] PDV encontrado: {}.", pdv);
	            return new ResponseEntity<PDV>(pdv.get(), HttpStatus.OK);
	        } else {
	        	log.trace("## [PDV controller] PDV não encontrado.");
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        
		} catch (Exception ex) {
			log.error("## [PDV controller] Erro ao buscar o pdv, mensagem: ", ex.getMessage());
    		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }

	/**
	 * Create partner
	 * @param PDV pdv
	 * @return PDV pdv
	 */
    @RequestMapping(value = "/pdv", method =  RequestMethod.POST)
    public ResponseEntity<?> Post(@Valid @RequestBody PDV pdv)
    {
    	try {
    		log.trace("## [PDV controller] Inserindo o seguinte PDV: {}", pdv);
    		return new ResponseEntity<PDV> (service.save(pdv), HttpStatus.CREATED);
    	} catch (Exception ex) {
    		log.error("## [PDV controller] Erro ao inserir o pdv, mensagem: ", ex.getMessage());
    		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }
    
    /**
	 * Search nearest partner by lng and lat
	 * @param Double lng
	 * @param Double lat
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/pdv/lng/{lng}/lat/{lat}", method = RequestMethod.GET)
    public ResponseEntity<?> GetNearestByLngAndLat(@PathVariable(value = "lng") Double lng, @PathVariable(value = "lat") Double lat)
    {
		try {
			log.trace("## [PDV controller] Buscando os PDVs mais próximos da longitude {} e latitude {}", lng, lat);
			
			List<PDV> pdvs = service.GetNearestByLngAndLat(lng, lat);
			
			if (!pdvs.isEmpty()) {
				
				log.trace("## [PDV controller] PDVs em ordem do mais próximo ao mais distante");
				
				pdvs.stream().forEach(p -> 
					log.trace("## [PDV controller] PDV: {}", p)
				);
				
				return new ResponseEntity<List<PDV>> (pdvs, HttpStatus.OK);
			} else {
				log.trace("## [PDV controller] PDVs não foram encontrados dentro da área de cobertura.");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
    	} catch (Exception ex) {
    		log.error("## [PDV controller] Erro ao inserir o pdv, mensagem: ", ex.getMessage());
    		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }
	
	/**
	 * Get all partners (NOT TEST MANDATORY)
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/pdv", method = RequestMethod.GET)
    public ResponseEntity<?> findAll()
    {
		try {
			log.trace("## [PDV controller] Buscando todos os parceiros.");
	        return new ResponseEntity<List<PDV>>(service.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("## [PDV controller] Erro ao inserir o pdv, mensagem: ", ex.getMessage());
    		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }
	
    /**
	 * Create many partners (NOT TEST MANDATORY)
	 * @param List<PDV> pdvs
	 */
    @RequestMapping(value = "/pdv/all", method =  RequestMethod.POST)
    public void Post(@Valid @RequestBody List<PDV> pdvs)
    {
    	pdvs.stream().forEach(p -> {
			try {
				log.trace("## [PDV controller] Inserindo o seguinte PDV: {}", p);
				service.save(p);
			} catch (Exception ex) {
				log.error("## [PDV controller] Erro ao inserir o pdv, mensagem: ", ex.getMessage());
				ex.printStackTrace();
			}
		});
    }
    
}
