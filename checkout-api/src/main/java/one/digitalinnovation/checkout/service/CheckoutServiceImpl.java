package one.digitalinnovation.checkout.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.checkout.entity.CheckoutEntity;
import one.digitalinnovation.checkout.event.CheckoutCreatedEvent;
import one.digitalinnovation.checkout.repository.CheckoutRepository;
import one.digitalinnovation.checkout.resource.CheckoutRequest;
import one.digitalinnovation.checkout.streaming.CheckoutCreatedSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(CheckoutEntity.Status.CREATED)
                .build();

        final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);
        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setCheckoutStatus(entity.getStatus().name())
                .build();

        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(entity);
    }

    @Override
    public List<CheckoutEntity> findAll() {
        return checkoutRepository.findAll();
    }
}
