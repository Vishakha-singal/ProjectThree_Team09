package ser516.project3.client.view;

import com.alee.laf.button.WebButton;
import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.model.PerformanceMetricModel;
import ser516.project3.utilities.NumberTextField;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

/**
 * This class contains performance metrics view
 * where emotion button is displayed on the right
 * and graph is displayed on the left.
 *
 * @author Mohan Vasantrao Yadav, Adhiraj Tikku
 */
public class PerformanceMetricView extends JPanel implements ViewInterface {
    private PerformanceMetricModel performanceMetricModel;

    private JPanel mainPanel;
    private JLabel displaylengthLabel;
    private JLabel secondsLabel;
    private NumberTextField displayLengthField;
    private WebButton interestButton;
    private WebButton engagementButton;
    private WebButton stressButton;
    private WebButton relaxationButton;
    private WebButton excitementButton;
    private WebButton focusButton;

    private static final int FONT_SIZE = 17;

    /**
     * This constructor initializes the model of performance metric view
     *
     * @param performanceMetricModel
     */
    public PerformanceMetricView(PerformanceMetricModel performanceMetricModel) {
        this.performanceMetricModel = performanceMetricModel;
    }

    /**
     * This method initializes the Graph view, creates components and
     * configures positions on layout.
     *
     * @param subViews an array of ViewInterface objects
     */
    @Override
    public void initializeView(ViewInterface[] subViews) {
        GraphView graphView = (GraphView) subViews[0];
        setLayout(new GridBagLayout());
        setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(graphView, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);

        createEmotionPanel();
        GridBagConstraints bagConstraints = new GridBagConstraints();
        createEmotionButtons(bagConstraints);
        createDisplayLengthLabel(bagConstraints);
        createDisplayLengthField(bagConstraints);
        createSecondsLabel(bagConstraints);

        add(mainPanel, gridBagConstraints);
        setVisible(true);
    }

    /**
     * This method creates panel and configures components on the panel.
     */
    private void createEmotionPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new TitledBorder(null, ClientConstants.EMOTIONS, TitledBorder.CENTER,
                TitledBorder.TOP, new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE), null));
        mainPanel.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
    }

    /**
     * This method creates buttons for performance metric.
     *
     * @param gridBagConstraints an object of GridBagContraints class
     *                           to configure the positions of buttons on
     *                           panel.
     */
    private void createEmotionButtons(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        interestButton = createEmotionButton(ClientConstants.INTEREST, performanceMetricModel.getInterestColor());
        mainPanel.add(interestButton, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        engagementButton = createEmotionButton(ClientConstants.ENGAGEMENT, performanceMetricModel.getEngagementColor());
        mainPanel.add(engagementButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        stressButton = createEmotionButton(ClientConstants.STRESS, performanceMetricModel.getStressColor());
        mainPanel.add(stressButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        relaxationButton = createEmotionButton(ClientConstants.RELAXATION, performanceMetricModel.getRelaxationColor());
        mainPanel.add(relaxationButton, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        excitementButton = createEmotionButton(ClientConstants.EXCITEMENT, performanceMetricModel.getExcitementColor());
        mainPanel.add(excitementButton, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        focusButton = createEmotionButton(ClientConstants.FOCUS, performanceMetricModel.getFocusColor());
        mainPanel.add(focusButton, gridBagConstraints);
    }

    /**
     * This method creates label to show display length
     *
     * @param gridBagConstraints
     */
    private void createDisplayLengthLabel(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.insets = new Insets(10, 5, 10, 5);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        displaylengthLabel = new JLabel(ClientConstants.DISPLAY_LENGTH);
        displaylengthLabel.setForeground(Color.WHITE);
        displaylengthLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        displaylengthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displaylengthLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        mainPanel.add(displaylengthLabel, gridBagConstraints);
    }

    /**
     * This method creates text field to input display length.
     *
     * @param gridBagConstraints
     */
    private void createDisplayLengthField(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        displayLengthField = new NumberTextField("" + performanceMetricModel.getDisplayLength(), false);
        displayLengthField.setBackground(Color.decode(ClientConstants.TEXT_FIELD_COLOR_HEX));
        displayLengthField.setForeground(Color.WHITE);
        displayLengthField.setHorizontalAlignment(SwingConstants.CENTER);
        displayLengthField.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        displayLengthField.setBorder(null);
        mainPanel.add(displayLengthField, gridBagConstraints);
    }

    /**
     * This method creates label to display units for display length.
     *
     * @param gridBagConstraints
     */
    private void createSecondsLabel(GridBagConstraints gridBagConstraints) {
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        secondsLabel = new JLabel(ClientConstants.SECONDS);
        secondsLabel.setForeground(Color.WHITE);
        secondsLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        secondsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        secondsLabel.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        mainPanel.add(secondsLabel, gridBagConstraints);
    }

    /**
     * This method adds Emotion buttons into the panel where dimensions,color
     * and emotion name is passed as arguments
     *
     * @param emotion String
     * @param color   Color object
     * @return emotionButton a WebButton
     */
    private WebButton createEmotionButton(String emotion, Color color) {
        WebButton emotionButton = new WebButton(emotion);
        emotionButton.setBottomBgColor(color);
        emotionButton.setTopSelectedBgColor(color);
        emotionButton.setForeground(Color.WHITE);
        emotionButton.setBackground(Color.decode(ClientConstants.PANEL_COLOR_HEX));
        emotionButton.setOpaque(true);
        emotionButton.setHorizontalTextPosition(SwingConstants.CENTER);
        emotionButton.setRound(30);
        emotionButton.setHorizontalAlignment(SwingConstants.CENTER);
        emotionButton.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
        return emotionButton;
    }

    /**
     * This method handles events on emotion buttons.
     *
     * @param actionListener
     */
    public void addEmotionButtonsListener(ActionListener actionListener) {
        interestButton.addActionListener(actionListener);
        engagementButton.addActionListener(actionListener);
        stressButton.addActionListener(actionListener);
        relaxationButton.addActionListener(actionListener);
        excitementButton.addActionListener(actionListener);
        focusButton.addActionListener(actionListener);
    }

    /**
     * This method handles key events on display length field.
     *
     * @param keyAdapter
     * @param documentListener
     */
    public void addDisplayLengthListener(KeyAdapter keyAdapter, DocumentListener documentListener) {
        displayLengthField.addKeyListener(keyAdapter);
        displayLengthField.getDocument().addDocumentListener(documentListener);
    }

    /**
     * This method updates the performance metric view buttons on color selection.
     *
     * @param performanceMetricModel
     */
    public void updatePerformanceMetricView(PerformanceMetricModel performanceMetricModel) {
        this.performanceMetricModel = performanceMetricModel;
        interestButton.setBottomBgColor(this.performanceMetricModel.getInterestColor());
        engagementButton.setBottomBgColor(this.performanceMetricModel.getEngagementColor());
        stressButton.setBottomBgColor(this.performanceMetricModel.getStressColor());
        relaxationButton.setBottomBgColor(this.performanceMetricModel.getRelaxationColor());
        excitementButton.setBottomBgColor(this.performanceMetricModel.getExcitementColor());
        focusButton.setBottomBgColor(this.performanceMetricModel.getFocusColor());
        displayLengthField.setText("" + this.performanceMetricModel.getDisplayLength());

        interestButton.setTopSelectedBgColor(this.performanceMetricModel.getInterestColor());
        engagementButton.setTopSelectedBgColor(this.performanceMetricModel.getEngagementColor());
        stressButton.setTopSelectedBgColor(this.performanceMetricModel.getStressColor());
        relaxationButton.setTopSelectedBgColor(this.performanceMetricModel.getRelaxationColor());
        excitementButton.setTopSelectedBgColor(this.performanceMetricModel.getExcitementColor());
        focusButton.setTopSelectedBgColor(this.performanceMetricModel.getFocusColor());
        displayLengthField.setText("" + this.performanceMetricModel.getDisplayLength());
    }
}
