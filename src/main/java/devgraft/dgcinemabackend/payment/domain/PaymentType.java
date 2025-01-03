package devgraft.dgcinemabackend.payment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
	PURCHASE("구매"),
	REFUND("환불");

	private final String description;
}
