import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class ReactorTest9 {

    @Test
    fun `An Error Occurred 에러 메시지가 발생해야 한다`() {
        val error = IllegalArgumentException("An error occurred")
        val errorFlux = Flux.error<String>(error)

        val source = Flux.just("success-1", "success-2")
            .concatWith(errorFlux)

        StepVerifier.create(source)
            .expectNext("success-1")
            .expectNext("success-2")
//            .expectErrorSatisfies {
//                assertTrue(it is IllegalArgumentException)
//                assertEquals("An error occurred", it.message)
//            }
//            .verify()
            .verifyErrorSatisfies {
                assertTrue(it is IllegalArgumentException)
                assertEquals("An error occurred", it.message)
            }
    }
}