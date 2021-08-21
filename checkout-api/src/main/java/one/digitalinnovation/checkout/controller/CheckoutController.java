package one.digitalinnovation.checkout.controller;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.checkout.entity.CheckoutEntity;
import one.digitalinnovation.checkout.resource.CheckoutRequest;
import one.digitalinnovation.checkout.resource.CheckoutResponse;
import one.digitalinnovation.checkout.service.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/")
    public ResponseEntity<CheckoutResponse> create(@RequestBody CheckoutRequest checkoutRequest) {
        final CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                .code(checkoutService.create(checkoutRequest).orElseThrow().getCode()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse);
    }

    @GetMapping("/")
    public ResponseEntity<List<CheckoutResponse>> findAll() {
        final List<CheckoutResponse> checkoutResponseList = checkoutService.findAll()
                .stream().map((CheckoutEntity t) -> CheckoutResponse.builder()
                        .code(t.getCode()).build()).collect(Collectors.toList());
        return ResponseEntity.ok().body(checkoutResponseList);
    }
}
