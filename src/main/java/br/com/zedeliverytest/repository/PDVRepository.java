package br.com.zedeliverytest.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.zedeliverytest.entity.PDV;

public interface PDVRepository extends JpaRepository<PDV, Long> {
	
	String findNearestAddressesFilteredByCoverageAreaFromGivenPointCoordinates = 
			"SELECT * FROM PDV WHERE ST_CONTAINS(coverage_area, POINT(:lng, :lat)) ORDER BY ST_DISTANCE_SPHERE(address, POINT(:lng, :lat))";
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = findNearestAddressesFilteredByCoverageAreaFromGivenPointCoordinates)
	List<PDV> getNearestByLngAndLat(@Param("lng") Double lng, @Param("lat") Double lat);

}
