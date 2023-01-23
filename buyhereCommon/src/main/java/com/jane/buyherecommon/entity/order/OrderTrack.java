package com.jane.buyherecommon.entity.order;


import com.jane.buyherecommon.entity.IdBasedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "order_track")
@NoArgsConstructor
@Getter
@Setter
public class OrderTrack extends IdBasedEntity {
	
	@Column(length = 256)
	private String notes;

	private Date updatedTime;

	@Enumerated(EnumType.STRING)
	@Column(length = 45, nullable = false)
	private OrderStatus status;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Transient
	public String getUpdatedTimeOnForm() {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		//dateFormatter.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Istanbul"));
		return dateFormatter.format(this.updatedTime);
	}
	
	public void setUpdatedTimeOnForm(String dateString) throws ParseException {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		//dateFormatter.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Istanbul"));
		try {
			this.updatedTime = dateFormatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}

}
