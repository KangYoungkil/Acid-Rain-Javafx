import io.reactivex.Observable
import io.reactivex.rxjavafx.observables.JavaFxObservable
import io.reactivex.rxkotlin.zipWith
import javafx.application.Platform
import javafx.beans.property.ReadOnlyStringWrapper
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.SplitPane
import javafx.scene.layout.HBox
import java.util.concurrent.TimeUnit


infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
    require(start.isFinite())
    require(endInclusive.isFinite())
    require(step > 0.0) { "Step must be positive, was: $step." }
    val sequence = generateSequence(start) { previous ->
        if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
        val next = previous + step
        if (next > endInclusive) null else next
    }
    return sequence.asIterable()
}


class MainViewModel {

    private val worModel = Model.WordModel

    val MAX_CONTAINER = 5
    val count = SimpleIntegerProperty(0)
    val currentInput = SimpleStringProperty()
    val countString = ReadOnlyStringWrapper()
    lateinit var enterEvents: Observable<ActionEvent>
    val stackPaneList = FXCollections.observableArrayList<HBox>((1..MAX_CONTAINER).map { HBox() })
    val stageObservable = Observable.just(stackPaneList).flatMapIterable { it -> it }.repeat()

    val labelObservable = Observable.just(Label(worModel.sampleWord()))

    private val INTERVAL_TIME: Long = 1 // seconds
    val trigger = Observable.interval(INTERVAL_TIME, TimeUnit.SECONDS)

    init {
        countString.bind(count.asString())

        labelObservable
                .map {
                    it.translateY += 100
                    it
                }
                .zipWith(trigger)
                .repeat()
                .subscribe {
                    Platform.runLater {
                        stageSample().children.add(it.first)
                    }
                }

    }

    fun bindEnterEvent(node: Node) {
        enterEvents = JavaFxObservable.actionEventsOf(node)
        enterEvents.subscribe {
            count.value++
            currentInput.value = ""

        }
    }

    fun bindContainer(container: SplitPane) {
        container.items.addAll(stackPaneList)
        val increment = (1 / stackPaneList.size.toDouble())
        container.setDividerPositions(*increment.rangeTo(1.0).step(increment).map { Math.round(it * 100) / 100.0 }.toDoubleArray())
    }

    fun stageSample(): HBox {
        return stageObservable.sample(1, TimeUnit.MICROSECONDS).blockingFirst()
    }

}