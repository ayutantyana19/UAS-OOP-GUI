import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    
    public static void main(String[] args) {
        launch();
    }
    TableView<Pengunjung> tableView = new TableView<Pengunjung>();
    TextField idInput, NamaInput, JenisKelaminInput, JamMasukInput;
    
    @Override
    public void start(Stage stage) {

        TableColumn<Pengunjung, String> columnid = new TableColumn<>("ID");
        columnid.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Pengunjung, String> columnNama = new TableColumn<>("NAMA");
        columnNama.setCellValueFactory(new PropertyValueFactory<>("Nama"));

        TableColumn<Pengunjung, String> columnJenisKelamin = new TableColumn<>("JENIS KELAMIN");
        columnJenisKelamin.setCellValueFactory(new PropertyValueFactory<>("JenisKelamin"));

        TableColumn<Pengunjung, String> columnJamMasuk = new TableColumn<>("JAM MASUK");
        columnJamMasuk.setCellValueFactory(new PropertyValueFactory<>("JamMasuk"));
        
        tableView.getColumns().add(columnid);
        tableView.getColumns().add(columnNama);
        tableView.getColumns().add(columnJenisKelamin);
        tableView.getColumns().add(columnJamMasuk);
        ToolBar toolBar = new ToolBar();
        // Button

        Button addButton = new Button("Add");
        toolBar.getItems().add(addButton);
        addButton.setOnAction(e -> add());

        Button deleteButton = new Button("Delete");
        toolBar.getItems().add(deleteButton);
        deleteButton.setOnAction(e -> delete());

        Button editButton = new Button("Edit");
        toolBar.getItems().add(editButton);
        editButton.setOnAction(e -> edit());

        VBox vbox = new VBox(tableView, toolBar);
 
        Scene scene = new Scene(vbox);
 
        stage.setScene(scene);
        stage.show();
        loadData();
       Statement stmt;
        try {
           Database db= new Database();
           stmt = db.conn.createStatement();
           
            ResultSet record = stmt.executeQuery("select * from pengunjung");
            tableView.getItems().clear();
            while (record.next()){
                tableView.getItems()
                .add(new Pengunjung(record.getInt("id"), record.getString("Nama"), record.getString("JenisKelamin"), record.getString("JamMasuk")));
            }
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("koneksi gagal");
        }


    }
 
    public void add() {
        Stage addStage = new Stage();
        Button save = new Button("simpan");

        addStage.setTitle("add data Menu");

        TextField namaField = new TextField();
        TextField jeniskelaminField = new TextField();
        TextField jammasukField = new TextField();
        Label labelNama = new Label("Nama");
        Label labelJenisKelamin = new Label("Jenis Kelamin");
        Label labelJamMasuk = new Label("Jam Masuk");

        VBox hbox1 = new VBox(5, labelNama, namaField);
        VBox hbox2 = new VBox(5, labelJenisKelamin, jeniskelaminField);
        VBox hbox3 = new VBox(5, labelJamMasuk, jammasukField);
        VBox vbox = new VBox(20, hbox1, hbox2, hbox3, save);
        
        Scene scene = new Scene(vbox, 400, 400);
        
        save.setOnAction(e -> {
            Database db = new Database();
            try {
                Statement state = db.conn.createStatement();
                String sql = "insert into pengunjung SET nama='%s', jeniskelamin='%s', jammasuk='%s'";
                sql = String.format(sql, namaField.getText(), jeniskelaminField.getText(), jammasukField.getText());
                state.execute(sql);
                addStage.close();
                loadData();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        });

        addStage.setScene(scene);
        addStage.show();
    }

    public void edit() {
        Stage addStage = new Stage();
        Button save = new Button("simpan");

        addStage.setTitle("add data Menu");

        TextField namaField = new TextField();
        TextField jammasukField = new TextField();
        Label labelNama = new Label("Nama");
        Label labelJamMasuk = new Label("Jam Masuk");

        VBox hbox1 = new VBox(5, labelNama, namaField);
        VBox hbox2 = new VBox(5, labelJamMasuk, jammasukField);
        VBox vbox = new VBox(20, hbox1, hbox2, save);

        Scene scene = new Scene(vbox, 400, 400);

        save.setOnAction(e -> {
            Database db = new Database();
            try {
                Statement state = db.conn.createStatement();
                String sql = "UPDATE pengunjung SET jammasuk='%s' WHERE nama='%s'";
                sql = String.format(sql, jammasukField.getText(), namaField.getText());
                state.execute(sql);
                addStage.close();
                loadData();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
        });

        addStage.setScene(scene);
        addStage.show();
    }

    public void delete() {
        Stage addStage = new Stage();
        Button save = new Button("Delete");

        addStage.setTitle("add data Menu");

        TextField namaField = new TextField();
        Label labelNama = new Label("Nama");

        VBox hbox1 = new VBox(5, labelNama, namaField);
        VBox vbox = new VBox(20, hbox1, save);

        Scene scene = new Scene(vbox, 400, 400);

        save.setOnAction(e -> {
            Database db = new Database();
            try {
                Statement state = db.conn.createStatement();
                String sql = "Delete from pengunjung WHERE nama='%s'";
                sql = String.format(sql, namaField.getText());
                state.execute(sql);
                addStage.close();
                loadData();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        addStage.setScene(scene);
        addStage.show();
    }
    

    //load data
    private void loadData() {
        Statement stmt;
        try {
            Database db = new Database();
            stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pengunjung");
            tableView.getItems().clear();
            while(rs.next()){
                tableView.getItems().add(new Pengunjung(rs.getInt("id"), rs.getString("Nama"), rs.getString("JenisKelamin"), rs.getString("JamMasuk")));
            }
            
            stmt.close();
            db.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}