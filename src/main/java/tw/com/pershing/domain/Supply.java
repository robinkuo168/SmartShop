package tw.com.pershing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_supply")
public class Supply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="supply_id")
	private int supplyId;
	private String supplyname;
	public int getId() {
		return supplyId;
	}
	public void setId(int supplyId) {
		this.supplyId = supplyId;
	}
	public String getSupplyname() {
		return supplyname;
	}
	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}
	
}
