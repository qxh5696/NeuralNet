package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import data_processing.Parser;

/**
 * Created by Nicholas on 4/2/2016.
 */
 public class SaveNet extends JFrame {
    public SaveNet() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jp.add(fileName);
        fileName.setColumns(5);
        addCommand = new JButton("Start recording");
        addCommand.addActionListener(al);
        addCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCommand.setText("Stop Recording");
                setVisible(false);
                dispose();
                //addCommand.addMouseListener(new );

            }
        });
        jp.add(addCommand);
        add(jp);
        this.setSize(100, 100);
        this.setVisible(true);
    }
    JButton addCommand;
    JPanel jp = new JPanel(new FlowLayout());
    JTextField fileName = new JTextField();
    ActionListener al = new ActionListener() {
        private void handleKeyPress(int keyCode) {
            Parser.parse();
            switch (keyCode) {
                case 37://LEFT KEY
                    Parser.parse();
                    addCommand.setText("Recording left");
                    break;
                case 38://UP KEY
                    Parser.parse();
                    addCommand.setText("Recording up");
                    break;
                case 39://RIGHT KEY
                    Parser.parse();
                    addCommand.setText("Recording right");
                    break;
                case 40://DOWN KEY
                    Parser.parse();
                    addCommand.setText("Recording down");
                    break;
                default:
                    Parser.parse();
            }
        }
        public void actionPerformed(ActionEvent e) {
            processKeys();
        }
        private void processKeys(){
            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
                    new KeyEventDispatcher()  {
                        public boolean dispatchKeyEvent(KeyEvent e){
                            if(e.getID() == KeyEvent.KEY_PRESSED){
                                handleKeyPress(e.getKeyCode());
                            }
                            return false;
                        }
                    });
        }
    };
}