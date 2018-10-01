import com.sun.javafx.scene.EventHandlerProperties
import de.saxsys.mvvmfx.ViewModel
import de.saxsys.mvvmfx.utils.commands.Action
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.awt.event.KeyEvent
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import de.saxsys.mvvmfx.utils.commands.Command
import de.saxsys.mvvmfx.utils.commands.DelegateCommand


class MainViewModel : ViewModel {

//    val wordEvent = BehaviorSubject.create<String>()!!
//    val inputWord = BehaviorSubject.create<String>()
//

    val count = SimpleStringProperty()

    private val enterCommand: Command = DelegateCommand {
        object : Action() {
            @Throws(Exception::class)
            override fun action() {

            }
        }
    }

    init {
    }

    fun getEnterCommand(): Command {
        return enterCommand
    }
}