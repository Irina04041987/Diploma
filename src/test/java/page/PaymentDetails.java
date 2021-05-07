package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentDetails {
    private SelenideElement cardNumber = $("input[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement cardMonth = $("input[placeholder=\"08\"]");
    private SelenideElement cardYear = $("input[placeholder=\"22\"]");
    private SelenideElement holder = $("fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private SelenideElement cvcCode = $("input[placeholder=\"999\"]");
    private SelenideElement buttonContinue = $("div:nth-child(4) > button > span > span");

    private ElementsCollection Notification = $$("div.notification__content");
    private SelenideElement successNotification = Notification.get(0);
    private SelenideElement errorNotification = Notification.get(1);

    private SelenideElement wrongFormatNotification = $(".input__sub");


    public void fillCardDetails(DataHelper.CardInformation cardInformation) {
        cardNumber.setValue(cardInformation.getNumber());
        cardMonth.setValue(cardInformation.getMonth());
        cardYear.setValue(cardInformation.getYear());
        holder.setValue(cardInformation.getHolder());
        cvcCode.setValue(cardInformation.getCvc());
        buttonContinue.click();
    }

    public void checkPaymentSuccessful() {
        successNotification.waitUntil(Condition.visible, 15000);
    }

    public void checkPaymentNotSuccessful() {
        errorNotification.waitUntil(Condition.visible, 15000);
    }

    public void checkWrongFormatOfField() {
        wrongFormatNotification.waitUntil(Condition.visible, 15000);

    }
}
