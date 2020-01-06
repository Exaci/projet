package model;

public enum CASTLE {

    BLUE("view/resources/castlechooser/castle_blue.png"),
    GREEN("view/resources/castlechooser/castle_green.png"),
    RED("view/resources/castlechooser/castle_red.png"),
    ORANGE("view/resources/castlechooser/castle_orange.png");
    String urlCastle;

    private CASTLE(String urlCastle) {
        this.urlCastle=urlCastle;
    }

	public String getUrlCastle() {
		return urlCastle;
	}

	public void setUrlCastle(String urlCastle) {
		this.urlCastle = urlCastle;
	}

}