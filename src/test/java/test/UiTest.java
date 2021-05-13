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


public class UiTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        String appUrl = "http://localhost:8080";
        open(appUrl);
    }


    @Test
    public void shouldPaymentCardWithValidCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCard();
        val validCardInformation = DataHelper.getValidCardInformation();
        PaymentDetailsData.fillCardDetails(validCardInformation);
        PaymentDetailsData.checkPaymentSuccessful();
        assertEquals("APPROVED", SQLUtils.getStatusPaymentCard());
        assertEquals(4500000, SQLUtils.getAmountPaymentCard());

    }

    @Test
    public void shouldCreditWithValidCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val validCardInformation = DataHelper.getValidCardInformation();
        PaymentDetailsData.fillCardDetails(validCardInformation);
        PaymentDetailsData.checkPaymentSuccessful();
        assertEquals("APPROVED", SQLUtils.getStatusCredit());

    }

    @Test
    public void shouldPaymentCardWithNotValidCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCard();
        val NotvalidCardInformation = DataHelper.getNotvalidCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
        assertEquals("DECLINED", SQLUtils.getStatusPaymentCard());

    }

    @Test
    public void shouldCreditdWithNotValidCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getNotvalidCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
        assertEquals("DECLINED", SQLUtils.getStatusCredit());
    }

    @Test
    public void shouldPaymentCardWithNotInGateCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCard();
        val NotvalidCardInformation = DataHelper.getNotInGateCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
    }

    @Test
    public void shouldCreditdWithNotInGateCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getNotInGateCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkPaymentNotSuccessful();
    }

    //-------------------------
    @Test
    public void shouldShort15CardNumber() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getShort15CardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();

    }

    @Test
    public void shouldShort1CardNumber() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getShort1CardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();
    }

    @Test
    public void shouldWrongMonthCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getWrongMonthCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();

    }

    @Test
    public void shouldWrongMonthMoreCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getWrongMonthMoreCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();
    }

    @Test
    public void shouldWrongYearMoreCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getWrongYearMoreCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();
    }

    @Test
    public void shouldWrongYearhLessCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getWrongYearLessCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();
    }

    @Test
    public void shouldWrongCVCCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getWrongCvcCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();
    }

    @Test
    public void shouldWrongHolderNameCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getWrongHolderNameCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();
    }

    @Test
    public void shouldEmptyHolderNameCard() throws SQLException {
        val TripOfferPage = new TripOfferPage();
        val PaymentDetailsData = TripOfferPage.clickBuyCredit();
        val NotvalidCardInformation = DataHelper.getEmptyNameCardInformation();
        PaymentDetailsData.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsData.checkWrongFormatOfField();
        val PaymentDetailsDataCard = TripOfferPage.clickBuyCard();
        PaymentDetailsDataCard.fillCardDetails(NotvalidCardInformation);
        PaymentDetailsDataCard.checkWrongFormatOfField();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
}
