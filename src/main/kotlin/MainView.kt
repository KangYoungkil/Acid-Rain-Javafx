import de.saxsys.mvvmfx.FxmlView
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.Pane
import de.saxsys.mvvmfx.InjectViewModel



class MainView : FxmlView<MainViewModel> {



    @FXML
    private lateinit var countLabel: Label

    @FXML
    private lateinit var container: Pane

    @FXML
    private lateinit var input: TextField

    @InjectViewModel //is provided by mvvmFX
    private lateinit var viewModel: MainViewModel

    @FXML
    fun initialize() {
        countLabel.textProperty().bindBidirectional(viewModel.count)
        input.disableProperty().bind(viewModel.getEnterCommand().executableProperty())

    }

}