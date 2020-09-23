package com.bitedu.gui;

import com.bitedu.osm.FileScanner;
import com.bitedu.osm.FileTreeNode;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class OSMinitorController {
    @FXML private LineChart cpuChart;
    @FXML private TreeTableView<FileTreeNode> fileStat;

    private Stage primaryStage = null;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void handleCPUSelectionChanged(Event event) {
    }

    public void handleSelectFile(ActionEvent actionEvent) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(primaryStage);



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                FileTreeNode rootNode = new FileTreeNode();
                rootNode.setFile(file);
                rootNode.setFileName(file.getName());

                FileScanner.scannerDirectory(rootNode);

                TreeItem rootItem = new TreeItem(rootNode);
            }
        });

        thread.start();
    }
}
