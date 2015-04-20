package com.lmn.speed.valetservice;

import com.lmn.speed.valetservice.exception.ValetingQuoteException;
import com.othercompany.classification.CarClassificationService;
import com.othercompany.classification.ServiceUnAvailableException;
import com.othercompany.classification.UnknownTypeException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.lmn.speed.valetservice.CarModel.*;
import static com.lmn.speed.valetservice.ServiceType.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

/**
 * Created by lsouza on 16/04/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ValetingQuoteServiceImplTest {

    private ValetingQuoteService underTest;

    @Mock
    private CarClassificationService carClassificationService;

    @Before
    public void setup() {
        underTest = new ValetingQuoteServiceImpl();
        setInternalState(underTest, "carClassificationService", carClassificationService);
    }

    @Test
    public void testRequestAQuoteForSmallBronze() throws Exception {
        final ServiceType serviceType = BRONZE;
        final CarModel model = SMALL;

        when(carClassificationService.getCarType(model.getModelName())).thenReturn("TYPE_A");

        final String quote = underTest.requestAQuote(serviceType, model);
        assertEquals("£79.99", quote);
    }

    @Test
    public void testRequestAQuoteForMiniSilver() throws Exception {
        final ServiceType serviceType = SILVER;
        final CarModel model = MINI;

        when(carClassificationService.getCarType(model.getModelName())).thenReturn("TYPE_A");

        final String quote = underTest.requestAQuote(serviceType, model);
        assertEquals("£89.99", quote);
    }

    @Test
    public void testRequestAQuoteFor4x4Gold() throws Exception {
        final ServiceType serviceType = GOLD;
        final CarModel model = _4X4;

        when(carClassificationService.getCarType(model.getModelName())).thenReturn("TYPE_C");

        final String quote = underTest.requestAQuote(serviceType, model);
        assertEquals("£131.00", quote);
    }

    @Test
    public void testRequestAQuoteForMPVSilver() throws Exception {
        final ServiceType serviceType = SILVER;
        final CarModel model = MPV;

        when(carClassificationService.getCarType(model.getModelName())).thenReturn("TYPE_C");

        final String quote = underTest.requestAQuote(serviceType, model);
        assertEquals("£100.99", quote);
    }

    @Test(expected = ValetingQuoteException.class)
    public void testCarClassificationServiceUnavailable() throws Exception{
        final ServiceType serviceType = SILVER;
        final CarModel model = MINI;

        when(carClassificationService.getCarType(model.getModelName())).thenThrow(new ServiceUnAvailableException());
        underTest.requestAQuote(serviceType, model);

    }

    @Test(expected = ValetingQuoteException.class)
    public void testCarClassificationServiceUnknownType() throws Exception{
        final ServiceType serviceType = SILVER;
        final CarModel model = MINI;

        when(carClassificationService.getCarType(model.getModelName())).thenThrow(new UnknownTypeException());
        underTest.requestAQuote(serviceType, model);
    }

    @Test
    public void testPriceQuotesGoldTypeA() throws Exception {
        assertEquals(new BigDecimal(119.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(GOLD).get("TYPE_A"));
    }

    @Test
    public void testPriceQuotesGoldTypeB() throws Exception {
        assertEquals(new BigDecimal(123.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(GOLD).get("TYPE_B"));
    }

    @Test
    public void testPriceQuotesGoldTypeC() throws Exception {
        assertEquals(new BigDecimal(130.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(GOLD).get("TYPE_C"));
    }

    @Test
    public void testPriceQuotesSilverTypeA() throws Exception {
        assertEquals(new BigDecimal(89.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(SILVER).get("TYPE_A"));
    }

    @Test
    public void testPriceQuotesSilverTypeB() throws Exception {
        assertEquals(new BigDecimal(93.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(SILVER).get("TYPE_B"));
    }

    @Test
    public void testPriceQuotesSilverTypeC() throws Exception {
        assertEquals(new BigDecimal(100.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(SILVER).get("TYPE_C"));
    }

    @Test
    public void testPriceQuotesBronzeTypeA() throws Exception {
        assertEquals(new BigDecimal(79.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(BRONZE).get("TYPE_A"));
    }

    @Test
    public void testPriceQuotesBronzeTypeB() throws Exception {
        assertEquals(new BigDecimal(83.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(BRONZE).get("TYPE_B"));
    }

    @Test
    public void testPriceQuotesBronzeTypeC() throws Exception {
        assertEquals(new BigDecimal(90.99), ValetingQuoteServiceImpl.PRICE_QUOTES.get(BRONZE).get("TYPE_C"));
    }
}
