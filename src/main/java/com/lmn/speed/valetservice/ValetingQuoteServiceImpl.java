package com.lmn.speed.valetservice;

import com.lmn.speed.valetservice.exception.ValetingQuoteException;
import com.othercompany.classification.CarClassificationService;
import com.othercompany.classification.ServiceUnAvailableException;
import com.othercompany.classification.UnknownTypeException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static com.lmn.speed.valetservice.ServiceType.BRONZE;
import static com.lmn.speed.valetservice.ServiceType.GOLD;
import static com.lmn.speed.valetservice.ServiceType.SILVER;

/**
 * Created by lsouza on 16/04/2015.
 */
public class ValetingQuoteServiceImpl implements ValetingQuoteService {

    private CarClassificationService carClassificationService;

    protected static final Map<ServiceType, Map<String, BigDecimal>> PRICE_QUOTES = new HashMap<>();
    static {
        final Map<String, BigDecimal> goldPrices = new HashMap<>();
        goldPrices.put("TYPE_A", new BigDecimal(119.99));
        goldPrices.put("TYPE_B", new BigDecimal(123.99));
        goldPrices.put("TYPE_C", new BigDecimal(130.99));
        PRICE_QUOTES.put(GOLD, goldPrices);

        final Map<String, BigDecimal> silverPrices = new HashMap<>();
        silverPrices.put("TYPE_A", new BigDecimal(89.99));
        silverPrices.put("TYPE_B", new BigDecimal(93.99));
        silverPrices.put("TYPE_C", new BigDecimal(100.99));
        PRICE_QUOTES.put(SILVER, silverPrices);

        final Map<String, BigDecimal> bronzePrices = new HashMap<>();
        bronzePrices.put("TYPE_A", new BigDecimal(79.99));
        bronzePrices.put("TYPE_B", new BigDecimal(83.99));
        bronzePrices.put("TYPE_C", new BigDecimal(90.99));
        PRICE_QUOTES.put(BRONZE, bronzePrices);
    }

    @Override
    public String requestAQuote(ServiceType serviceType, CarModel model) {
        try {
            final BigDecimal price = getPriceForQuote(serviceType, model);
            return formatPriceForQuote(price);
        } catch (ServiceUnAvailableException e) {
            throw new ValetingQuoteException(e.getMessage(), e);
        } catch (UnknownTypeException e) {
            throw new ValetingQuoteException(e.getMessage(), e);
        }
    }

    private String formatPriceForQuote(BigDecimal price) {
        StringBuffer sb = new StringBuffer("£");
        sb.append(price.setScale(2, RoundingMode.CEILING));
        return sb.toString();
    }

    private BigDecimal getPriceForQuote(ServiceType serviceType, CarModel model) throws ServiceUnAvailableException, UnknownTypeException {
        final String carType = carClassificationService.getCarType(model.getModelName());
        return PRICE_QUOTES.get(serviceType).get(carType);
    }
}
