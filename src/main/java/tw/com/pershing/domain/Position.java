package tw.com.pershing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_position")
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonProperty("id")
	private int positionId;
	private String district;
	private String block;
	private String brand;
	private String floor;
	private String productName;
	private String shopUrl;
	private int seq;
	private String brandEqual;
	private String districtEqual;
	private String blockEqual;
	private String floorEqual;
	
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrandEqual() {
		return brandEqual;
	}
	public void setBrandEqual(String brandEqual) {
		this.brandEqual = brandEqual;
	}
	public String getDistrictEqual() {
		return districtEqual;
	}
	public void setDistrictEqual(String districtEqual) {
		this.districtEqual = districtEqual;
	}
	public String getBlockEqual() {
		return blockEqual;
	}
	public void setBlockEqual(String blockEqual) {
		this.blockEqual = blockEqual;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getFloorEqual() {
		return floorEqual;
	}
	public void setFloorEqual(String floorEqual) {
		this.floorEqual = floorEqual;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
