package Constant;

public enum SeatType {
	// Seat type options
	HARD_SEAT("Hard seat"),
    SOFT_SEAT("Soft seat"),
    SOFT_SEAT_WITH_AIR_CONDITIONER("Soft seat with air conditioner"),
    HARD_BED("Hard bed"),
    SOFT_BED("Soft bed"),
    SOFT_BED_WITH_AIR_CONDITIONER("Soft bed with air conditioner");

	// Display text
    private final String seatType;

    // Build seat type
    SeatType(String seatType) {
        this.seatType = seatType;
    }

    // Accessory
    public String getDisplayText() {
        return seatType;
    }
}
