/*IShippingService.java
Author: Tsholofelo Mabidikane (230018165)
Date: 07 August 2025
 */
package za.ac.cput.service;

import za.ac.cput.domain.Shipping;
import java.util.List;

public interface IShippingService extends IService<Shipping, Integer>{

    boolean deleteShipping(Integer id);
    List<Shipping> getAll();
}
