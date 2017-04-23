/**
 * Created by ryanchen1 on 2017-04-22.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This is the method for user to create account
 */
public class AccountCreation extends JFrame{

private JTextField userName; //username
private JLabel state=new JLabel("");  //sucess or failure


   public AccountCreation(){
    super("accountCreation");
     this.userName=new JTextField(15);
     JButton submitButton=new JButton("CREATE ACCOUNT");
     JPanel panel=new JPanel();
     this.setMinimumSize(new Dimension(300,350));
     panel.add(this.userName);
     panel.add(submitButton);
     panel.add(this.state);
     this.add(panel);
     this.setVisible(true);

     submitButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             String name = userName.getText();
             if (check(name)) {
                 state.setText("Account" + name + "created.");
             } else state.setText("Unable to create the account.");
         }
     });
}

public boolean check(String username) {
    try {
        URL url = new URL("http://localhost:8080/AccountCreation/createAccount?userName=" + username);
        HttpURLConnection connectionAdress = (HttpURLConnection) url.openConnection();

        connectionAdress.setRequestMethod("POST");
        connectionAdress.setRequestProperty("Accept", "application/json");
        connectionAdress.setDoOutput(true);
        connectionAdress.connect();

       if(connectionAdress.getResponseCode() == 200)
        return true;
    } catch (MalformedURLException e) {
        System.out.println("Error: malformed URL!");
    } catch (IOException e) {
        System.out.println("Error: http connection not found!");
    }
    return false;
}
}

































