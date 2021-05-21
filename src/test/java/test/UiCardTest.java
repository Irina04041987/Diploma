package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.PaymentDetails;
import page.TripOfferPage;
import sql.SQLUtils;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiCardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String appUrl = "http://localhost:8080";
        open(appUrl);
    }

    public PaymentDetails CardTest() {
        val TripOfferPage = new TripOfferPage();
        return TripOfferPage.clickBuyCard();
    }

    public void CardcheckWrongFormatOfField(DataHelper.CardInformation ValidCardInformation) {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCard();
        PaymentDetailsData.fillCardDetails(ValidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();

    }

    @Test
    public void shouldPaymentCardWithValidCard() throws SQLException {
        val PaymentDetailsData = CardTest();
        val validCardInformation = DataHelper.getValidCardInformation();
        PaymentDetailsData.fillCardDetails(validCardInformation);
        PaymentDetailsData.checkPaymentSuccessful();
        assertEquals("APPROVED", SQLUtils.getStatusPaymentCard());
        assertEquals(4500000, SQLUtils.getAmountPaymentCard());

    }

    @Test
    public void shouldPaymentCardWithNotValidCard() throws SQLException {
        val PaymentDetailsData = CardTest();
        val NotvalidCardInformation = DataHelper.getNotvalidCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
        assertEquals("DECLINED", SQLUtils.getStatusPaymentCard());

    }

    @Test
    public void shouldPaymentCardWithNotInGateCard() throws SQLException {
        val PaymentDetailsData = CardTest();
        val NotvalidCardInformation = DataHelper.getNotInGateCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
    }

    @Test
    public void shouldShort15CardNumber() throws SQLException {
        val NotvalidCardInformation = DataHelper.getShort15CardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);


    }

    @Test
    public void shouldShort1CardNumber() throws SQLException {
        val NotvalidCardInformation = DataHelper.getShort1CardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongMonthCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongMonthCardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);

    }

    @Test
    public void shouldWrongMonthMoreCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongMonthMoreCardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongYearMoreCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongYearMoreCardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongYearhLessCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongYearLessCardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongCVCCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongCvcCardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongHolderNameCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongHolderNameCardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldEmptyHolderNameCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getEmptyNameCardInformation();
        CardcheckWrongFormatOfField(NotvalidCardInformation);
    }


    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
}
