import files.CashFile;
import files.TransactionFile;
import models.Ledger;
import models.Transaction;
import models.TransactionManager;
import models.User;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

public class InputPanel extends JPanel {
    private User user;
    private Ledger ledgerManager;
    private TransactionManager transactionManager;
    private TransactionFile transactionFile;
    private CashFile cashFile;

    private JPanel contentPanel;

    public InputPanel(User user, TransactionManager transactionManager, TransactionFile transactionFile, CashFile cashFile) {
        this.user = user;
        this.transactionManager = transactionManager;
        this.transactionFile = transactionFile;
        this.cashFile = cashFile;
        ledgerManager = new Ledger(user, transactionManager);

        setBackground(SystemColor.activeCaption);
        setBounds(0, 0, 600, 420);
        setBackground(Color.ORANGE);
        setLayout(null);


        initContentPanel();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0,2));
        contentPanel.setBounds(0, 0, 600, 380);

        contentPanel.setBackground(Color.PINK);

        initButtons();

        add(contentPanel);
    }

    private void initButtons() {
        JLabel label1 = new JLabel("날짜 : ");
        label1.setHorizontalAlignment(JLabel.RIGHT);
        JTextField textField1 = new JTextField(10);

        JLabel label2 = new JLabel("수입 or 지출 : ");
        label2.setHorizontalAlignment(JLabel.RIGHT);

        JTextField textField2 = new JTextField(10);

        JLabel label3 = new JLabel("결제수단 : ");
        label3.setHorizontalAlignment(JLabel.RIGHT);

        JTextField textField3 = new JTextField(10);

        JLabel label4 = new JLabel("금액 : ");
        label4.setHorizontalAlignment(JLabel.RIGHT);
        JTextField textField4 = new JTextField(10);

        JLabel label5 = new JLabel("메모");
        label5.setHorizontalAlignment(JLabel.RIGHT);
        JTextField textField5 = new JTextField(10);

        JLabel label6 = new JLabel("");
        JButton button = new JButton("추가");
        button.addActionListener( event -> {
            String date = textField1.getText();
            String type = textField2.getText();
            String payment = textField3.getText();
            int amount = Integer.parseInt(textField4.getText());
            String comment = textField5.getText();

            ledgerManager.transferTransaction(new Transaction(
                    date,
                    type,
                    payment,
                    amount,
                    comment
            ));

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");

            try {
                transactionFile.updateFile(new File("Transaction.csv"));
                cashFile.updateFile(new File("Cash.csv"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        contentPanel.add(label1);
        contentPanel.add(textField1);
        contentPanel.add(label2);
        contentPanel.add(textField2);
        contentPanel.add(label3);
        contentPanel.add(textField3);
        contentPanel.add(label4);
        contentPanel.add(textField4);
        contentPanel.add(label5);
        contentPanel.add(textField5);
        contentPanel.add(label6);
        contentPanel.add(button);
    }
}
