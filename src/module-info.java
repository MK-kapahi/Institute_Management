module InstituteManager {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.java;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens batchmanager to javafx.graphics, javafx.fxml;
	opens admission to javafx.graphics, javafx.fxml;
	opens showall to javafx.graphics, javafx.fxml,javafx.base;
	opens login to javafx.graphics, javafx.fxml,javafx.base;
	opens googler to javafx.graphics, javafx.fxml,javafx.base;
	opens feecollector to javafx.graphics, javafx.fxml;
	opens dashboard to javafx.graphics, javafx.fxml;
	opens status to javafx.graphics, javafx.fxml,javafx.base;
	opens aboutdev to javafx.graphics, javafx.fxml;
}
