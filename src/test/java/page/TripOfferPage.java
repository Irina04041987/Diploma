package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TripOfferPage {
    private SelenideElement buttonBuyCard = $(byText("Купить"));
    private SelenideElement buttonBuyCredit = $(byText("Купить в кредит"));
    private SelenideElement HeaderBySelectedWay = $("#root > div > h3");


    public PaymentDetails clickBuyCard() {
        buttonBuyCard.click();
        HeaderBySelectedWay.shouldHave(Condition.text("Оплата по карте"));
        return new PaymentDetails();
    }

    public PaymentDetails clickBuyCredit() {
        buttonBuyCredit.click();
        HeaderBySelectedWay.shouldHave(Condition.text("Кредит по данным карты"));
        return new PaymentDetails();
    }
}
