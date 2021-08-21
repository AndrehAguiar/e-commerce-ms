package one.digitalinnovation.checkout.listener;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.checkout.entity.CheckoutEntity;
import one.digitalinnovation.checkout.event.PaymentCreatedEvent;
import one.digitalinnovation.checkout.repository.CheckoutRepository;
import one.digitalinnovation.checkout.streaming.PaymentPaidSink;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent event) {

        final CheckoutEntity checkoutEntity = checkoutRepository.findByCode(event.getCheckoutCode().toString()).orElseThrow();
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
    }
}
