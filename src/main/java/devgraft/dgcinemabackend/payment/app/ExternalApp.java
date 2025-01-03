package devgraft.dgcinemabackend.payment.app;

import org.springframework.stereotype.Service;

/**
 * 결제 외부 API는 더미로 만듦
 */
@Service
class ExternalApp implements ExternalUseCase {

	@Override
	public Boolean purchase() {
		return Boolean.TRUE;
	}

	@Override
	public Boolean refund() {
		return Boolean.TRUE;
	}
}
