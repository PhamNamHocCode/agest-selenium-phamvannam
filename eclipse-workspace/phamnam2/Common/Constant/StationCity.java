package Constant;

public enum StationCity {
	SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");
	
	private final String stationCity;

	StationCity(String stationCity) {
        this.stationCity = stationCity;
    }

    public String getDisplayText() {
        return stationCity;
    }
}
