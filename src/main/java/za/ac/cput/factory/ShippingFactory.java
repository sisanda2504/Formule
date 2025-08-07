/*ShippingFactory.java
Shipping model Factory class
Author: Tsholofelo Mabidikane (230018165)
Date: 23 April 2025
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Shipping;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class ShippingFactory {

    public static Shipping createShipping(int id, int orderId, String address, String status, LocalDate estimatedDeliveryDate, String trackingNumber) {

        if(Helper.isNullOrEmpty(address) || Helper.isNullOrEmpty(status))
            return null;

        if(estimatedDeliveryDate == null)
            return null;

        if(Helper.isNullOrEmpty(trackingNumber))
            return null;

        return new Shipping.Builder()
                .setId(id)
                .setOrderId(orderId)
                .setAddress(address)
                .setStatus(status)
                .setEstimatedDeliveryDate(estimatedDeliveryDate)
                .setTrackingNumber(trackingNumber)
                .build();
    }
}
