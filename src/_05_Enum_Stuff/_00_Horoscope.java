package _05_Enum_Stuff;

import javax.swing.JOptionPane;

public class _00_Horoscope {
	public static void main(String[] args) {
		Zodiac zod = Zodiac.ARIES;
		horoscope(zod);
	}
	// 1. Create an enum in a separate file called Zodiac that contains a category for
	//    all 12 zodiac signs.
	
	// 2. Write a method that takes in a Zodiac enum object and uses a JOPtionPane to display
	//    a different horoscope based on the Zodiac's state.
	static void horoscope (Zodiac zod) {
		switch (zod) {
		case ARIES:
			System.out.println("Aries");
			break;
		case TAURUS:
			System.out.println("Taurus");
			break;
		case GEMINI:
			System.out.println("Gemini");
			break;
		case CANCER:
			System.out.println("Cancer");
			break;
		case LEO:
			System.out.println("Leo");
			break;
		case VIRGO: 
			System.out.println("Virgo");
			break;
		case LIBRA:
			System.out.println("Libra");
			break;
		case SCORPIO:
			System.out.println("Scorpio");
			break;
		case SAGITTARIUS:
			System.out.println("Sagittarius");
			break;
		case CAPRICORN:
			System.out.println("Capricorn");
			break;
		case AQUARIUS:
			System.out.println("Aquarius");
			break;
		case PISCES:
			System.out.println("Pices");
			break;
		}
	}
	// 3. Make a main method to test your method
}
