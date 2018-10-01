
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import utils.ResourceHelper
import java.util.concurrent.TimeUnit

sealed class Model {
    object WordModel : Model() {

        val file = ResourceHelper.convertFile("dictionary.txt")
        private val words: List<String>

        init {
            words = file.readLines(Charsets.UTF_8)
        }

        fun words(): Observable<String> {

            val texts = Observable
                    .just(words)
                    .observeOn(Schedulers.io())
                    .flatMapIterable { it -> it }
                    .sorted()
                    .filter { it.length > 2 }
                    .repeat()
            return texts
        }

        fun sampleWord(): String {
            return words().sample(1,TimeUnit.MILLISECONDS).blockingFirst()
        }

    }


}