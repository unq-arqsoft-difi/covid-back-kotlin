package hi

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object HelloSpec : Spek({
    val hello by memoized { Hello() }

    describe("Testing test") {
        it("say hi") {
            assertEquals("hello there", hello.hi())
        }
    }
})
