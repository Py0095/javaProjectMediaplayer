/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mediaplayerproject;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
//import javax.swing.JFileChooser;

/**
 *
 * @author Py0095
 */
public class MediaplayerProject extends Application {

    //kreasyon de yon stage 
    Stage window;
    BorderPane root = new BorderPane();
    Media media;
    MediaPlayer player;
    MediaView view;
    FileChooser chooser;
    boolean playerState = false;
    boolean muteState = false;
    Slider time = new Slider();
    Slider vol = new Slider();
    TableView<PlayerModel> table_view = new TableView<>();
    ArrayList<String> list = new ArrayList<>();
    Button btnPlay_Pause = new Button();
    HBox wrp_btnPlay_Pause = new HBox();
    int cpt = 0, id;
    String name, path;
    File fileselect1;
    FileChooser file_chooser;
    ObservableList<PlayerModel> list1 = FXCollections.observableArrayList();
   boolean table_vide =true;


    /**
     * @param args the command line arguments
     */
    //Kreyasyon metod yo
    void CreateWindow() {
        window.setTitle("vlc Media Player");
        window.getIcons().add(new Image(getClass().getResourceAsStream("vlc1.png")));

//        add the borderPane in the scene 
        Scene scene = new Scene(root, 300, 300);

//          add a default bacground in the borderpane 
        root.setStyle("-fx-background-color: black");

//      add the scene in the window 
        window.setScene(scene);
//        window.setMaximized(true);
        window.show();

        if (player != null) {
            playerState = true;
            muteState = true;
        }

    }

    void CreateMenu() {
//        create all menu
        MenuBar menuBar = new MenuBar();
        Menu media1 = new Menu("Media");

        Menu playback = new Menu("Playback");
        Menu audio = new Menu("Audio");
        Menu video = new Menu("Video");
//        Menu subtitle = new Menu("Subtitle");
//        Menu tooll = new Menu("Tool");
        Menu view1 = new Menu("View");
        Menu help = new Menu("Help");
//        
//         create all sub menu

//        media sub menu
        MenuItem openFile = new MenuItem("Open File");
        openFile.setOnAction(e -> openFile());
        MenuItem openFiles = new MenuItem("Open Mutiple Files");
        openFiles.setOnAction(e -> openFiles());
        Menu addMedia = new Menu("Add Media");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> exitMedia());

//        add sub media menu in menu media
        media1.getItems().addAll(openFile, openFiles, addMedia, quit);
//        end

//      playback sub menu 
        MenuItem title = new MenuItem("Title");
        Menu speed = new Menu("Speed");
        MenuItem play = new MenuItem("Play");
        MenuItem stop = new MenuItem("Stop");
        MenuItem previous = new MenuItem("Previous");
        MenuItem next = new MenuItem("Next");
        MenuItem record = new MenuItem("Record");
        //        add sub media menu in menu media
        playback.getItems().addAll(title, speed, play, stop, previous, next, record);
//        end
//        speed sub menu 
        MenuItem faster = new MenuItem("Faster(CTR +)");
//        MenuItem fasterFine = new MenuItem("Faster (fine)");
        MenuItem normal = new MenuItem("Normal");
        MenuItem slow = new MenuItem("Slow(CTR -)");
//        MenuItem slowFine = new MenuItem("Slow (fine)");
        //        add sub media menu in menu media
        speed.getItems().addAll(faster, normal, slow);
//        end

//       audio sub menu
        MenuItem increaseVolume = new MenuItem("Increase Volume");
        MenuItem decreaseVolume = new MenuItem("Decrease Volume");
        MenuItem mute = new MenuItem("Mute");
        //        add sub media menu in menu media
        audio.getItems().addAll(increaseVolume, decreaseVolume, mute);
//        end

//       video sub menu
        MenuItem fllScreen = new MenuItem("Full Screen (CTR+F)");
        fllScreen.setOnAction(e -> fullScreen());

        MenuItem normalScreen = new MenuItem("Normal Screen (CTR+N)");
        normalScreen.setOnAction(e -> normalScreen());

//        add sub menu video in menu video
        video.getItems().addAll(fllScreen, normalScreen);
//        end 

//        view sub menu
        MenuItem bgr = new MenuItem("Change background");
        //   add sub menu video in menu video
        view1.getItems().addAll(bgr);
//        bgc.setOnAction(changeColor());

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            Color color = colorPicker.getValue();
            root.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        bgr.setGraphic(colorPicker);
//        end 

//      help sub menu
        MenuItem help1 = new MenuItem("Help");
        MenuItem about = new MenuItem("About");
        //   add sub menu video in menu video
        help.getItems().addAll(help1, about);
        //        end 

//         Add media sub menu
        MenuItem tbvMusicVideo = new MenuItem("Add music/video");
        MenuItem tbvMusicsVideos = new MenuItem("add musics/videos");
        //   add sub menu video in menu video
        addMedia.getItems().addAll(tbvMusicVideo, tbvMusicsVideos);
        tbvMusicVideo.setOnAction(e -> {
            creationTableView();
//        FileChooser chooser1 = new FileChooser();
//        chooser1.setTitle("ChOOSE A MUSIC OR A VIDEO");
//        chooser1.getExtensionFilters().addAll(new ExtensionFilter("Audio/Video","*.mp3","*.mp4","*.flv","*.3gp","*.wma","*.wav","*.ogg","*.wmv","v.avg","*.avi"));
        });
        //        end 
        
        tbvMusicsVideos.setOnAction(e->{
            openMusicMethode();
        });

        //ajouter tout menu yo nan menu bar la 
        menuBar.getMenus().addAll(media1, playback, audio, video, view1, help);
        //ajouter menuBar la nan borderPane nan 
        root.setTop(menuBar);
    }

    private void openFile() {
        chooser = new FileChooser();
        chooser.setTitle("ChOOSE A MUSIC OR A VIDEO");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Audio/Video", "*.mp3", "*.mp4", "*.flv", "*.3gp", "*.wma", "*.wav", "*.ogg", "*.wmv", "*.avg", "*.avi"));
        File fileselect = chooser.showOpenDialog(window);
        if (fileselect != null) {
            try {
                media = new Media(fileselect.toURI().toURL().toString());
                // passons le media au mediaplayer
                player = new MediaPlayer(media);
                // jouer le media

                player.setAutoPlay(true);

                playerState = true;

                if (playerState == true) {
                    btnPlay_Pause.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("play.png"))));
                }

                //ajouter fonksyon ki pou jere temp piste kap jwe ak slider nou li ap update li
                player.currentTimeProperty().addListener((javafx.beans.Observable ov) -> updatesValues());

                // 
                time.valueProperty().addListener((javafx.beans.Observable ov) -> {
                    if (time.isPressed()) {
                        player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
                    }
                });

                //
                vol.valueProperty().addListener((javafx.beans.Observable ov) -> {
                    if (vol.isPressed()) {
                        player.setVolume(vol.getValue() / 100);
                    }
                });

                // passons le mediaPlayer au mediaView
                view = new MediaView(player);
                // ajouter le MediaView au centre du BorderPane

                root.setCenter(view);
            } catch (MalformedURLException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur ");
                alert.setContentText(ex.getMessage());
                alert.show();
            }
        }
    }

    protected void updatesValues() {
        Platform.runLater(() -> {
            time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
        });
    }

    ;

    private void openFiles() {
        FileChooser file_chooser = new FileChooser();
        file_chooser.getExtensionFilters().add(new ExtensionFilter("Audio/Video", "*.mp3", "*.mp4", "*.flv", "*.3gp", "*.wma", "*.wav", "*.ogg", "*.wmv", "v.avg", "*.avi"));
        List<File> select = file_chooser.showOpenMultipleDialog(null);

        System.out.println(select);
        if (select != null) {
            for (File f : select) {
//                player.

            }
        }
    }

    private void exitMedia() {
        // System.exit(0);
        window.close();
    }

    //full screen method
    private void fullScreen() {
        window.setFullScreen(true);
        window.setFullScreenExitHint("");
        window.setFullScreenExitKeyCombination(KeyCombination.keyCombination("CTRL+F"));
    }
//      normal screen method

    private void normalScreen() {
        window.setFullScreen(false);
//        window.setMaximized(false);
        window.setFullScreenExitHint("");
        window.setFullScreenExitKeyCombination(KeyCombination.keyCombination("CTRL+N"));
    }

    //button method
    void buttonMediaPlayer() {

        Image image_btnPlay_Pause = new Image(getClass().getResourceAsStream("play.png"));
        btnPlay_Pause.setGraphic(new ImageView(image_btnPlay_Pause));
        btnPlay_Pause.setOnAction(e -> {
            if (playerState == false) {
                btnPlay_Pause.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("pause.png"))));
                if (player != null) {
                    playerState = !playerState;
                    player.play();
                    System.out.println("you've been clicked in play btn ");
                }

            } else {

                btnPlay_Pause.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("play.png"))));
                if (player != null) {
                    playerState = !playerState;
                    player.pause();
                }
            }
        });
        Button btn_Previous = new Button();
        btn_Previous.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("previous.png"))));
        btn_Previous.setOnAction(e -> {
            if (player != null) {
                System.out.println("you've been clicked in previous btn ");
            }
        });

        Button btn_stop = new Button();
        btn_stop.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("stop.png"))));
        btn_stop.setOnAction(e -> {
            if (player != null) {
                player.stop();
                btnPlay_Pause.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("play.png"))));

                System.out.println("you've been clicked in stop btn ");
            }
        });

        Button btn_next = new Button();
        btn_next.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("next.png"))));
        btn_next.setOnAction(e -> {
            if (player != null) {
                System.out.println("you've been clicked in next btn ");
            }
        });

        Button btnMute_unMute = new Button();
        btnMute_unMute.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("audio.png"))));
        btnMute_unMute.setOnAction(e -> {
            if (muteState == false) {
                btnMute_unMute.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("no-audio.png"))));

                if (player != null) {
                    muteState = !muteState;
                    player.setMute(true);
                    System.out.println("you've been clicked in mute btn ");
                }
            } else {
                btnMute_unMute.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("audio.png"))));
                if (player != null) {
                    muteState = !muteState;
                    player.setMute(false);
                }

            }
        });
        wrp_btnPlay_Pause.getChildren().addAll(btnPlay_Pause);
        wrp_btnPlay_Pause.setPadding(new Insets(10, 10, 15, 10));

        HBox wrp_prev_stop_next = new HBox(3);
        wrp_prev_stop_next.getChildren().addAll(btn_Previous, btn_stop, btn_next);
        wrp_prev_stop_next.setPadding(new Insets(10, 10, 15, 10));

        HBox wrp_mute = new HBox();

        vol.setPadding(new Insets(0, 10, 10, 10));
        wrp_mute.getChildren().addAll(btnMute_unMute);
        wrp_mute.setPadding(new Insets(10, 0, 0, 10));

        HBox wrp_volume = new HBox();
        wrp_volume.getChildren().add(vol);
        wrp_volume.setAlignment(Pos.CENTER);

        HBox container = new HBox();
        container.setStyle("-fx-background-color: white");
        container.getChildren().addAll(wrp_btnPlay_Pause, wrp_prev_stop_next, wrp_mute, wrp_volume);
//        timer.setPadding(new Insets(0,0,5,50));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(time, container);
        root.setBottom(vbox);
    }

//    creater a mediaplayer
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        CreateWindow();
        CreateMenu();
        buttonMediaPlayer();
    }

    public static void main(String[] args) {
        // TODO code application logic 
        //  System.out.println("Hello");
        launch(args);
    }
    
     //creation d'une table qui contiendra les media
    public void creationTableView(){
        
        //creation des colonnes ou entete pour ajouter les player 
        TableColumn<PlayerModel,String> colonne_1=new TableColumn<>("Id");
        TableColumn<PlayerModel,String> colonne_2=new TableColumn<>("Nom");
        TableColumn<PlayerModel,String> colonne_3=new TableColumn<>("Path");
    
        //ajout de la valeur dans les colonnes 
        //colonne 1 pour id
        
    colonne_1.setCellValueFactory(new PropertyValueFactory<>("id"));
    //colonne 2 pour le nom du media
    colonne_2.setCellValueFactory(new PropertyValueFactory<>("nom"));
    //colonne 3 pour le path
    colonne_3.setCellValueFactory(new PropertyValueFactory<>("path"));
    
    // ajout des colonnes dans la table view
    table_view.getColumns().addAll(colonne_1,colonne_2,colonne_3);
    // redimensionner la table vue en fonction de nombre de colonne necesaire
    table_view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    // ajout de la liste a gauche du border pane
    
    if(table_vide){
    root.setLeft(table_view);
    table_vide=false;
    table_view.setVisible(true);
     }  
}   
    void openMusicMethode(){
        file_chooser=new FileChooser();
        file_chooser.getExtensionFilters().add(new ExtensionFilter("Audio/Video", "*.mp3", "*.mp4", "*.flv", "*.3gp", "*.wma", "*.wav", "*.ogg", "*.wmv", "v.avg", "*.avi"));
        List<File> select =  file_chooser.showOpenMultipleDialog(null);
        if(select!=null){
            table_view.getItems().addAll(new PlayerModel(1,"nom","path"));
            list.add(id+ " ; "+"nom"+" ; "+"nom"+" ; \n");
               System.out.println("test");
               select.stream().map(f -> {
                   cpt++;
                return f;
            }).map(f -> {
                id=cpt;
                return f;
            }).map(f -> {
                name=f.getName();
                return f;
            }).map(f -> {
                path=f.getPath();
                return f;
            }).map(_item -> {
                table_view.getItems().add(new PlayerModel(id,name,path));
                return _item;
            }).forEachOrdered(_item -> {
                list.add(id+ " ; "+name+" ; "+path+" ; \n");
            });
        }
    }
}

