import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javafx.scene.control.Label
import utils.ResourceHelper
import java.io.File
import java.util.concurrent.TimeUnit
import javax.annotation.Resource

sealed class Model {
    object WordModel : Model() {

        val file = ResourceHelper.convertFile("dictionary.txt")
        private val words: List<String>

        init {
            words = file.readLines(Charsets.UTF_8)
        }

        private const val INTERVAL_TIME: Long = 1 // seconds
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
            return words().blockingFirst()
        }

    }


}