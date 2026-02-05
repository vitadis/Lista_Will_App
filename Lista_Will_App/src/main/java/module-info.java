module org.openjfx.Lista_Will_App {

	requires javafx.controls;
	requires javafx.base;
	requires java.desktop;

	opens clases to javafx.base;

	exports org.openjfx.Lista_Will_App;
}
