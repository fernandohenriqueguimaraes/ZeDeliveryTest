package br.com.zedeliverytest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.WKTReader;

import br.com.zedeliverytest.entity.PDV;
import br.com.zedeliverytest.repository.PDVRepository;
import br.com.zedeliverytest.service.PDVService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PDVController.class)
public class PDVControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(PDVController.class);
    
    @Autowired
	private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

	@MockBean
	private PDVService service;
	
	@MockBean
    private PDVRepository repository;
	
	public static WKTReader wktReader = new WKTReader();
	
	public static final Long PDV_ID = 1L;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
    @Before
	public void init() {}

	@Test
	public void createPartner() throws Exception {
		
		log.info("Início - [createPartner].");
		//given
		PDV pdv = new PDV();
		pdv.setTradingName("Adega Sao Paulo");
		pdv.setOwnerName("Pedro Silva");
		pdv.setDocument("04666182390");
		pdv.setAddress((Point) wktReader.read("POINT(-38.59826 -3.774186)"));
		pdv.setCoverageArea((MultiPolygon) wktReader.read("MULTIPOLYGON (((-46.70103 -23.61731, -46.72086 -23.63517, -46.7357 -23.62738, -46.74618 -23.60575, -46.7557 -23.60855, -46.76999 -23.5987, -46.7721 -23.58224, -46.76326 -23.57079, -46.73433 -23.54613, -46.70644 -23.56163, -46.70335 -23.56973, -46.69073 -23.58475, -46.70103 -23.61731)))"));
		
		objectMapper.registerModule(new JtsModule());
		objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
	    String requestJson= ow.writeValueAsString(pdv);
	    
	    //when and then
		mockMvc.perform(post("/pdv")
		        .contentType(APPLICATION_JSON_UTF8)
		        .content(requestJson)).andExpect(status().isCreated());
		
		log.info("Fim - [createPartner].");
		
	}
	
	@Test
	public void findById() throws Exception {
		
		log.info("Início - [findById].");
		//given
		PDV pdv = new PDV();
    	pdv.setId(PDV_ID);
		pdv.setTradingName("Adega Emporio");
		pdv.setOwnerName("Ronaldinho gaucho");
		pdv.setDocument("09.444.848/0001-84");
		pdv.setAddress((Point) wktReader.read("POINT(-46.588654 -23.709635)"));
		pdv.setCoverageArea((MultiPolygon) wktReader.read("MULTIPOLYGON (((-46.61026 -23.66622, -46.62596 -23.66985, -46.63481 -23.6749, -46.64012 -23.69742, -46.63566 -23.71857, -46.63154 -23.74041, -46.63078 -23.75411, -46.61701 -23.75216, -46.59878 -23.74832, -46.58076 -23.73916, -46.56257 -23.73662, -46.55038 -23.73378, -46.54404 -23.73016, -46.53368 -23.72612, -46.52488 -23.71453, -46.52502 -23.70481, -46.52786 -23.69098, -46.5573 -23.66818, -46.57335 -23.66606, -46.61026 -23.66622)))"));
		
		// when
		Optional<PDV> opdv = Optional.of(pdv);
		when(service.findById(pdv.getId())).thenReturn(opdv);

		//then
		mockMvc.perform(MockMvcRequestBuilders.get("/pdv/{id}", PDV_ID)
				.param("id", PDV_ID.toString())
		        .contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		log.info("Fim - [findById].");
		
	}
	
	@Test
	public void GetNearestByLngAndLat() throws Exception {
		
		log.info("Início - [GetNearestByLngAndLat].");
		//given
		List<PDV> pdvs = new ArrayList<PDV>();
		
		PDV pdv1 = new PDV();
    	pdv1.setId(PDV_ID);
		pdv1.setTradingName("Adega Emporio");
		pdv1.setOwnerName("Ronaldinho gaucho");
		pdv1.setDocument("09.444.848/0001-84");
		pdv1.setAddress((Point) wktReader.read("POINT(-46.720875 -23.584986)"));
		pdv1.setCoverageArea((MultiPolygon) wktReader.read("MULTIPOLYGON (((-46.70103 -23.61731, -46.72086 -23.63517, -46.7357 -23.62738, -46.74618 -23.60575, -46.7557 -23.60855, -46.76999 -23.5987, -46.7721 -23.58224, -46.76326 -23.57079, -46.73433 -23.54613, -46.70644 -23.56163, -46.70335 -23.56973, -46.69073 -23.58475, -46.70103 -23.61731)))"));
		pdvs.add(pdv1);
		
		PDV pdv2 = new PDV();
		pdv1.setId(2L);
		pdv2.setTradingName("Adega Sao Paulo");
		pdv2.setOwnerName("Pedro Silva");
		pdv2.setDocument("04666182390");
		pdv2.setAddress((Point) wktReader.read("POINT(-46.693768 -23.569365)"));
		pdv2.setCoverageArea((MultiPolygon) wktReader.read("MULTIPOLYGON (((-46.76338 -23.53597, -46.7311 -23.60489, -46.70055 -23.61936, -46.6842 -23.63009, -46.6766 -23.63894, -46.66641 -23.62915, -46.66131 -23.62771, -46.66186 -23.6196, -46.6595 -23.61805, -46.6508 -23.62341, -46.64678 -23.62989, -46.62982 -23.62927, -46.62673 -23.61484, -46.62811 -23.60982, -46.6209 -23.59442, -46.61515 -23.58345, -46.6094 -23.57719, -46.60764 -23.57397, -46.60785 -23.56925, -46.61397 -23.55929, -46.62352 -23.55578, -46.62871 -23.54404, -46.62485 -23.52008, -46.6778 -23.51402, -46.68331 -23.51027, -46.69636 -23.50809, -46.71939 -23.50878, -46.73314 -23.50409, -46.75288 -23.4986, -46.751 -23.51262, -46.76338 -23.53597)))"));
		pdvs.add(pdv2);
		
		// when
		when(service.GetNearestByLngAndLat(-46.720875, -23.584986)).thenReturn(pdvs);
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.get("/pdv/lng/{lng}/lat/{lat}", -46.720875, -23.584986)
				.param("lng", "-46.720875")
				.param("lat", "-23.584986")
		        .contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		
		log.info("Fim - [GetNearestByLngAndLat].");
		
	}
}
