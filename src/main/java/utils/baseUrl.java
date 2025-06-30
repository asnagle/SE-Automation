package utils;

public class baseUrl {
	public enum PageType {
	    LOGIN("https://admin-demo.nopcommerce.com/login"),
	    ADMIN("https://admin-demo.nopcommerce.com/admin");

	    private final String url;

	    PageType(String url) {
	        this.url = url;
	    }

	    public String getUrl() {
	        return url;
	    }
	}


}
