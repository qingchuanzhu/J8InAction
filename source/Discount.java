class Discount {
	enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

		private final int percentage;

		Code(int percentage) {
			this.percentage = percentage;
		}
	}
}

class Quote {
	private final String shopName;
	private final double price;
	private final Discount.Code discountCode;

	Quote(String shopName, double price, Discount.Code code) {
		this.shopName = shopName;
		this.price = price;
		this.discountCode = code;
	}

	static Quote parse(String s) {
		String[] split = s.split(":");
		String shopName = split[0];
		double price = Double.parseDouble(split[1]);
		Discount.Code discountCode = Discount.Code.valueOf(split[2]);
		return new Quote(shopName, price, discountCode);
	}

	String getShopName(){ return shopName;}
	double getPrice(){ return price; }
	Discount.Code getDiscountCode() { return discountCode; }
}