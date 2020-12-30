import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.util.*

class ReactorTest2 {

    @Test
    fun `사과가 들어있는지 확인`() {
        val flux: Flux<String> = Flux.just("사과", "바나나", "딸기")

        StepVerifier
            .create(flux)
            .recordWith { ArrayList() }
            .expectNextCount(3)
            .expectRecordedMatches { fruits ->
                fruits.contains("사과")
            }
            .expectComplete()
            .verify()
    }
}