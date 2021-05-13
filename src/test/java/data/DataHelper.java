package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {
    static Faker faker = new Faker(new Locale("en"));
    static String notValidMonth = Integer.toString(faker.number().numberBetween(1, 9));
    static String notValidMonthMore = Integer.toString(faker.number().numberBetween(13, 20));
    static String validCVC = Integer.toString(faker.number().numberBetween(100, 599));
    static String notvalidCVC = Integer.toString(faker.number().numberBetween(1, 99));
    static String notValidYearMore = Integer.toString(faker.number().numberBetween(27, 99));
    static String notValidYearLess = Integer.toString(faker.number().numberBetween(10, 20));

    private DataHelper() {

    }

    @Value
    public static class CardInformation {
        private String number, year, month, holder, cvc;
    }

    public static CardInformation getValidCardInformation() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", faker.name().fullName(), validCVC);
    }

    public static CardInformation getNotvalidCardInformation() {
        return new CardInformation("4444 4444 4444 4442", "22", "11", faker.name().fullName(), validCVC);
    }

    public static CardInformation getNotInGateCardInformation() {
        return new CardInformation("4444 4444 4444 4443", "22", "11", faker.name().fullName(), validCVC);
    }

    public static CardInformation getShort15CardInformation() {
        return new CardInformation("4444 4444 4444 444", "22", "11", faker.name().fullName(), validCVC);
    }

    public static CardInformation getShort1CardInformation() {
        return new CardInformation("4", "22", "11", faker.name().fullName(), validCVC);
    }

    public static CardInformation getWrongMonthCardInformation() {
        return new CardInformation("4444 4444 4444 4441", "22", notValidMonth, faker.name().fullName(), validCVC);
    }

    public static CardInformation getWrongMonthMoreCardInformation() {
        return new CardInformation("4444 4444 4444 4441", "22", notValidMonthMore, faker.name().fullName(), validCVC);
    }

    public static CardInformation getWrongYearMoreCardInformation() {
        return new CardInformation("4444 4444 4444 4441", notValidYearMore, "12", faker.name().fullName(), validCVC);
    }

    public static CardInformation getWrongYearLessCardInformation() {
        return new CardInformation("4444 4444 4444 4441", notValidYearLess, "12", faker.name().fullName(), validCVC);
    }

    public static CardInformation getWrongCvcCardInformation() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", faker.name().fullName(), notvalidCVC);
    }

    public static CardInformation getWrongHolderNameCardInformation() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", validCVC, validCVC);
    }

    public static CardInformation getEmptyNameCardInformation() {
        return new CardInformation("4444 4444 4444 4441", "22", "11", " ", validCVC);
    }

}