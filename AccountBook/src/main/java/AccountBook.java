import files.TransactionFile;
import models.TransactionManager;
import models.User;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileNotFoundException;

public class AccountBook {
    JFrame frame;
    private JPanel contentPanel;
    private User user;
    private TransactionManager transactionManager;


    public static void main(String[] args) throws FileNotFoundException {
        AccountBook application = new AccountBook();
        application.run();
    }

    private void run() throws FileNotFoundException {
        initObject();
        initFile();
        initFrame();
    }

    private void initObject() {
        user = new User();
        transactionManager = new TransactionManager();
    }

    private void initFile() throws FileNotFoundException {
        File file = new File("Transaction.csv");
        TransactionFile transactionFile = new TransactionFile(transactionManager);
        transactionFile.initFile(file);
    }

    private void initFrame() {
        frame = new JFrame("AccountBook");
        frame.setBackground(SystemColor.activeCaption);
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        initConcentPanel();
        initButtonsPanel();

        frame.setVisible(true);
    }

    private void initConcentPanel() {
        contentPanel = new JPanel();
        contentPanel.setBounds(0, 0, 600, 370);
        contentPanel.setLayout(new GridLayout(1, 0));
        contentPanel.setBackground(Color.BLUE);

        frame.add(contentPanel);
    }

    private void initButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(0, 370, 600, 100);
        panel.setLayout(new GridLayout(1, 0));

        panel.add(createInputButton());
        panel.add(createTransactionButton());
        panel.add(createAssetButton());
        panel.add(createMemoButton());

        frame.add(panel);
    }

    private JButton createInputButton() {
        JButton inputButton = new JButton("입력");
        inputButton.addActionListener(event -> {
            InputPanel inputPanel = new InputPanel(user, transactionManager);
            updateContentPanel(inputPanel);
        });
        inputButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        return inputButton;
    }

    private JButton createTransactionButton() {
        JButton transactionButton = new JButton("내역");
        transactionButton.addActionListener(event -> {
            TransactionPanel transactionPanel = new TransactionPanel(transactionManager);
            updateContentPanel(transactionPanel);
        });
        transactionButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        return transactionButton;
    }

    private JButton createAssetButton() {
        JButton AssetButton = new JButton("자산");
        AssetButton.addActionListener(event -> {
            AssetPanel assetPanel = new AssetPanel(user);
            updateContentPanel(assetPanel);
        });
        AssetButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        return AssetButton;
    }

    private JButton createMemoButton() {
        JButton memoButton = new JButton("메모");
        memoButton.addActionListener(event -> {
            MenuPanel menuPanel = new MenuPanel();
            updateContentPanel(menuPanel);
        });

        memoButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

        return memoButton;
    }
    private void updateContentPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);

        contentPanel.setVisible(false);
        contentPanel.setVisible(true);
    }

}

