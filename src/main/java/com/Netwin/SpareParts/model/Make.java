package com.Netwin.SpareParts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Make")
public class Make {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int makeId;
	private String makeName;

	public Make() {

	}

	public Make(String makeName) {
		this.makeName = makeName;
	}

	public int getMakeId() {
		return makeId;
	}

	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}

	public String getMakeName() {
		return makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	@Override
	public String toString() {
		return "Make [makeId=" + makeId + ", makeName=" + makeName + "]";
	}
}
