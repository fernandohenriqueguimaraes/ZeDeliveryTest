package br.com.zedeliverytest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;

import lombok.Data;


@Entity
@Data
@Table(name = "pdv")
public class PDV implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@JsonProperty("tradingName")
	@Column(nullable = false)
	private String tradingName;
	
	@JsonProperty("ownerName")
	@Column(nullable = false)
	private String ownerName;
	
	@JsonProperty("document")
	@Column(nullable = false, unique = true)
	private String document;
	
	@JsonProperty("coverageArea")
    @Column(name="coverage_area", nullable = false)
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private MultiPolygon coverageArea;
	
	@JsonProperty("address")
	@Column(name="address", nullable = false)
	@JsonSerialize(using = GeometrySerializer.class)
	@JsonDeserialize(contentUsing = GeometryDeserializer.class)
	private Point address;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public MultiPolygon getCoverageArea() {
		return coverageArea;
	}

	public void setCoverageArea(MultiPolygon coverageArea) {
		this.coverageArea = coverageArea;
	}

	public Point getAddress() {
		return address;
	}

	public void setAddress(Point address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PDV [id=" + id + ", tradingName=" + tradingName + ", ownerName=" + ownerName + ", document=" + document
				+ ", coverageArea=" + coverageArea + ", address=" + address + "]";
	}

}
