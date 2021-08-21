package one.digitalinnovation.checkout.service;

import one.digitalinnovation.checkout.entity.CheckoutEntity;
import one.digitalinnovation.checkout.resource.CheckoutRequest;

import java.util.List;
import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest);
    List<CheckoutEntity> findAll();

}
