/*ShippingFactory.java
Shipping model Factory class
Author: Tsholofelo Mabidikane (230018165)
Date: 23 April 2025
 */
package za.ac.cput.factory.business;

import za.ac.cput.domain.business.Order;
import za.ac.cput.domain.business.Shipping;
import za.ac.cput.domain.business.Status;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class ShippingFactory {

    public static Shipping createShipping(Order order, String address, Status status, LocalDate estimatedDeliveryDate, String trackingNumber) {

        if (order == null ||
                Helper.isNullOrEmpty(address) ||
                status == null ||
                estimatedDeliveryDate == null ||
                Helper.isNullOrEmpty(trackingNumber)) {
            return null;
        }

        return new Shipping.Builder()
                .setOrder(order)
                .setAddress(address)
                .setStatus(status)
                .setEstimatedDeliveryDate(estimatedDeliveryDate)
                .setTrackingNumber(trackingNumber)
                .build();
    }
}
