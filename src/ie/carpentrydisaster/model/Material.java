package ie.carpentrydisaster.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Material {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String description;
	@Column
	private String item;
	@Column
	private BigDecimal unitExcl;
	@Column
	private BigDecimal totalExcl;
	@Column
	private BigDecimal totalIncl;
	@Column
	private int customerId;
	public Material() {
		super();
	}
	public Material(String description, String item, BigDecimal unitExcl, BigDecimal totalExcl, BigDecimal totalIncl,
			int customerId) {
		super();
		this.description = description;
		this.item = item;
		this.unitExcl = unitExcl;
		this.totalExcl = totalExcl;
		this.totalIncl = totalIncl;
		this.customerId = customerId;
	}
	public Material(int id, String description, String item, BigDecimal unitExcl, BigDecimal totalExcl,
			BigDecimal totalIncl, int customerId) {
		super();
		this.id = id;
		this.description = description;
		this.item = item;
		this.unitExcl = unitExcl;
		this.totalExcl = totalExcl;
		this.totalIncl = totalIncl;
		this.customerId = customerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public BigDecimal getUnitExcl() {
		return unitExcl;
	}
	public void setUnitExcl(BigDecimal unitExcl) {
		this.unitExcl = unitExcl;
	}
	public BigDecimal getTotalExcl() {
		return totalExcl;
	}
	public void setTotalExcl(BigDecimal totalExcl) {
		this.totalExcl = totalExcl;
	}
	public BigDecimal getTotalIncl() {
		return totalIncl;
	}
	public void setTotalIncl(BigDecimal totalIncl) {
		this.totalIncl = totalIncl;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((totalExcl == null) ? 0 : totalExcl.hashCode());
		result = prime * result + ((totalIncl == null) ? 0 : totalIncl.hashCode());
		result = prime * result + ((unitExcl == null) ? 0 : unitExcl.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (customerId != other.customerId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (totalExcl == null) {
			if (other.totalExcl != null)
				return false;
		} else if (!totalExcl.equals(other.totalExcl))
			return false;
		if (totalIncl == null) {
			if (other.totalIncl != null)
				return false;
		} else if (!totalIncl.equals(other.totalIncl))
			return false;
		if (unitExcl == null) {
			if (other.unitExcl != null)
				return false;
		} else if (!unitExcl.equals(other.unitExcl))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Material [id=" + id + ", description=" + description + ", item=" + item + ", unitExcl=" + unitExcl
				+ ", totalExcl=" + totalExcl + ", totalIncl=" + totalIncl + ", customerId=" + customerId + "]";
	}
	
	
}
