package Railway;

import java.time.LocalDate;

import Constant.SeatType;
import Constant.StationCity;

public class BookTicketData {
	
	//Fields
	private LocalDate departDate;
	private StationCity departFrom;
	private StationCity arriveAt;
	private SeatType seatType;
	private int ticketAmount;
	
	//Elements
	public LocalDate getDepartDate() {
		return departDate;
	}
	public StationCity getDepartFrom() {
		return departFrom;
	}
	public StationCity getArriveAt() {
		return arriveAt;
	}
	public SeatType getSeatType() {
		if(seatType == null) {
			return SeatType.HS;
		}
		else {
			return seatType;
		}
	}
	public int getTicketAmount() {
		return ticketAmount;
	}
	
	public void setDepartDate(LocalDate departDate) {
		this.departDate = departDate;
	}
	public void setDepartFrom(StationCity departFrom) {
		this.departFrom = departFrom;
	}
	public void setArriveAt(StationCity arriveAt) {
		this.arriveAt = arriveAt;
	}
	public void setSeatType(SeatType seatType) {
		if(seatType == null) {
			seatType = SeatType.HS;
		}
		else {
			this.seatType = seatType;
		}
	}
	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	
	//Constructor
	public BookTicketData() {}
	
	public BookTicketData(LocalDate departDate, StationCity departFrom, StationCity arriveAt, SeatType seatType, int ticketAmount) {
	    this.departDate = departDate;
	    this.departFrom = departFrom;
	    this.arriveAt = arriveAt;
	    this.seatType = seatType;
	    this.ticketAmount = ticketAmount;
	}
	
}
