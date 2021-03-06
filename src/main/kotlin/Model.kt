import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import utils.ResourceHelper
import java.util.concurrent.TimeUnit
import java.util.Random


sealed class Model {
    object WordModel : Model() {

        val file = ResourceHelper.convertFile("dictionary.txt")
        private val textsObservable: Observable<String>

        init {
            textsObservable = Observable.fromArray(file.readLines(Charsets.UTF_8))
                    .observeOn(Schedulers.io())
                    .flatMapIterable { it -> it }
                    .filter { it.length > 2 }
                    .repeat()

        }

        fun sampleWord(): String {
            return textsObservable
                    .sample(100, TimeUnit.NANOSECONDS)
                    .blockingFirst()
        }

    }


}