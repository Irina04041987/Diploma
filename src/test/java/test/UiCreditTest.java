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

public class UiCreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String appUrl = "http://localhost:8080";
        open(appUrl);
    }

    public PaymentDetails CreditTest() {
        val TripOfferPage = new TripOfferPage();
        return TripOfferPage.clickBuyCredit();
    }

    public void CreditcheckWrongFormatOfField(DataHelper.CardInformation ValidCardInformation) {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        PaymentDetailsData.fillCardDetails(ValidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();

    }

    @Test
    public void shouldCreditWithValidCard() throws SQLException {
        val PaymentDetailsData = CreditTest();
        val validCardInformation = DataHelper.getValidCardInformation();
        PaymentDetailsData.fillCardDetails(validCardInformation);
        PaymentDetailsData.checkPaymentSuccessful();
        assertEquals("APPROVED", SQLUtils.getStatusCredit());

    }

    @Test
    public void shouldCreditdWithNotValidCard() throws SQLException {
        val PaymentDetailsData = CreditTest();
        val NotvalidCardInformation = DataHelper.getNotvalidCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
        assertEquals("DECLINED", SQLUtils.getStatusCredit());
    }

    @Test
    public void shouldCreditdWithNotInGateCard() throws SQLException {
        val PaymentDetailsData = CreditTest();
        val NotvalidCardInformation = DataHelper.getNotInGateCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
    }

    @Test
    public void shouldShort15CardNumber() throws SQLException {
        val NotvalidCardInformation = DataHelper.getShort15CardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);


    }

    @Test
    public void shouldShort1CardNumber() throws SQLException {
        val NotvalidCardInformation = DataHelper.getShort1CardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongMonthCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongMonthCardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);

    }

    @Test
    public void shouldWrongMonthMoreCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongMonthMoreCardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongYearMoreCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongYearMoreCardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongYearhLessCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongYearLessCardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongCVCCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongCvcCardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldWrongHolderNameCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getWrongHolderNameCardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);
    }

    @Test
    public void shouldEmptyHolderNameCard() throws SQLException {
        val NotvalidCardInformation = DataHelper.getEmptyNameCardInformation();
        CreditcheckWrongFormatOfField(NotvalidCardInformation);
    }
}
