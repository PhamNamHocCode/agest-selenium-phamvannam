package Constant;

public enum SeatType {
	
	// Menu items
	HS("Hard seat"),
    SS("Soft seat"),
    SSC("Soft seat with air conditioner"),
    HB("Hard bed"),
    SB("Soft bed"),
    SBC("Soft bed with air conditioner");

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
