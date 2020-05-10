package frontend.swing;

import controllers.LoginMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Jpassword extends JPanel implements ActionListener {
    private static String OK = "ok";
    private static String HELP = "help";
    ActionListener callback;

    private static char[] output;
    private JFrame controllingFrame; //needed for dialogs
    private JPasswordField passwordField;

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public Jpassword(JFrame f, String name, ActionListener callback) {

        controllingFrame = f;
        //Use the default FlowLayout.
        this.callback = callback;
        //Create everything.
        passwordField = new JPasswordField(20);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(callback);

        JLabel label = new JLabel(name + " Enter your password: ");
        label.setLabelFor(passwordField);

        JComponent buttonPane = createButtonPanel();

        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label);
        textPane.add(passwordField);

        add(textPane);
        add(buttonPane);
    }

    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0, 1));
        JButton okButton = new JButton("OK");
        JButton helpButton = new JButton("Help");

        okButton.setActionCommand(OK);
        helpButton.setActionCommand(HELP);
        okButton.addActionListener(callback);
        helpButton.addActionListener(callback);

        p.add(okButton);
        p.add(helpButton);

        return p;
    }


    public void resetFocus() {
        passwordField.requestFocusInWindow();
    }

    public void closeFrame(){
        controllingFrame.dispose();
    }

    // Callback function Jpassword
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (OK.equals(cmd)) { //Process the password.
            char[] input = passwordField.getPassword();
                //JOptionPane.showMessageDialog(controllingFrame,"wachtwoord ok.");
                controllingFrame.dispose();
                //Zero out the possible password, for security.
                output = input;

        } else { //The user has asked for help.
            JOptionPane.showMessageDialog(controllingFrame,
                    "Deze help functie is nog niet geimplementeerd");//TODO: dit mist nog
        }
    }

    public void showCorrect(){
        JOptionPane.showMessageDialog(controllingFrame,"wachtwoord ok.");
    }

    public void setFail(){
        JOptionPane.showMessageDialog(controllingFrame,"wachtwoord fout, probeer opnieuw.");
        passwordField.selectAll();
        resetFocus();
    }

    public void showHelp(){
        JOptionPane.showMessageDialog(controllingFrame,
                "Deze help functie is nog niet geimplementeerd");//TODO: dit mist nog
        setFail();
    }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static Jpassword createAndShowGUIPassword(String name, ActionListener callback) {
        //Create and set up the window.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content pane.
        final Jpassword newContentPane = new Jpassword(frame, name, callback);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //Make sure the focus goes to the right component
        //whenever the frame is initially given the focus.
        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        return newContentPane;

    }

}
