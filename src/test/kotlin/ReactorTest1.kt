import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class ReactorTest1 {

    @Test
    fun `짝수로 된 데이터만 통지해야 한다`() {
        val flux = Flux.just(1, 2, 3, 4)
            // rem은 나머지를 구하는 %와 같다.
            .filter { num -> num.rem(2) == 0 }

        StepVerifier
            .create(flux)
            .expectNext(2)
            .expectNext(4)
            .expectComplete()
            .verify()
    }

}