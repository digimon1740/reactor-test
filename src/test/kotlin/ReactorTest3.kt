import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class ReactorTest3 {

    @Test
    fun `IllegalArgumentException 에러가 발생해야 한다`() {
        val error = IllegalArgumentException("error")
        val errorFlux = Flux.error<String>(error)

        var source = Flux.just("success-1", "success-2")
        source = source.concatWith(errorFlux)

        // 종류에 상관없이 에러가 발생하면 통과
        StepVerifier.create(source)
            .expectNext("success-1")
            .expectNext("success-2")
            .expectError()
            .verify()

        // 인자로 지정한 에러가 발생할 때만 통과
        StepVerifier.create(source)
            .expectNext("success-1")
            .expectNext("success-2")
            .expectError(IllegalArgumentException::class.java)
            .verify()
    }
}