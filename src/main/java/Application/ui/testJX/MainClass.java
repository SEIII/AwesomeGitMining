package Application.ui.testJX;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.JFrame;



public class MainClass {
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  /**
   * @param args
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame("JavaFX in Swing");
    final JFXPanel webBrowser = new JFXPanel();
    frame.setLayout(new BorderLayout());
    frame.add(webBrowser, BorderLayout.CENTER);
    
    
    /*
     * 通过一个线程来初始化jfxpanel的内容
     */
    Platform.runLater(new Runnable() {

      @Override
      public void run() {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        webBrowser.setScene(scene);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");
        root.getChildren().add(chart);
       
     }
    });

    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(WIDTH, HEIGHT);
    frame.setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);
    frame.setVisible(true);
  }

}

