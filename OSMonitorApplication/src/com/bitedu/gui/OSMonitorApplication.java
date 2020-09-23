package com.bitedu.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OSMonitorApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // 1. 加载 .fxml 文件
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("os_monitor_tab.fxml"));
        Parent root = loader.load();

        OSMinitorController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        // 2. 创建一个场景对象
        Scene scene = new Scene(root, 800, 600);
        // 3. 给舞台对象设置标题
        primaryStage.setTitle("OS Monitor");
        // 4. 给舞台对象 stage 设置场景对象 scene
        primaryStage.setScene(scene);
        // 5. 展示舞台
        primaryStage.show();

/*
        写法优化
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("os_monitor_tab.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
 */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
