package one.digitalinnovation.checkout.config;

import one.digitalinnovation.checkout.streaming.CheckoutCreatedSource;
import one.digitalinnovation.checkout.streaming.PaymentPaidSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(value = {
        CheckoutCreatedSource.class,
        PaymentPaidSink.class
})
public class StreamingConfig {
}
