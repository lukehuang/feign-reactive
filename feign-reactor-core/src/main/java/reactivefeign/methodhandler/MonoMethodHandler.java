package reactivefeign.methodhandler;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class MonoMethodHandler implements MethodHandler {

	private final MethodHandler methodHandler;

	public MonoMethodHandler(MethodHandler methodHandler) {
		this.methodHandler = methodHandler;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Mono<Object> invoke(final Object[] argv) {
		try {
			return Mono.fromDirect((Publisher<Object>)methodHandler.invoke(argv));
		} catch (Throwable throwable) {
			return Mono.error(throwable);
		}
	}
}
