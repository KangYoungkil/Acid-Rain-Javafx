
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.SplitPane
import javafx.scene.control.TextField


class MainView {


    @FXML
    private lateinit var countLabel: Label

    @FXML
    private lateinit var input: TextField

    @FXML
    private lateinit var container: SplitPane


    @FXML
    fun initialize() {
        val viewModel = MainViewModel()
        countLabel.textProperty().bind(viewModel.countString.readOnlyProperty)
        viewModel.currentInput.bindBidirectional(input.textProperty())
        viewModel.bindEnterEvent(input)
        viewModel.bindContainer(container)

    }


}