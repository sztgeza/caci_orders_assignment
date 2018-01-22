package caci.brickorder.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Order {

	private final Long id;
	private final int bricks;
	private final Status status;
	
	public Order() {		
		this(null, 0, Status.NEW);
	}
	
	public Order(Long id, int bricks, Status status) {
		super();
		this.id = id;
		this.bricks = bricks;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public int getBricks() {
		return bricks;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
