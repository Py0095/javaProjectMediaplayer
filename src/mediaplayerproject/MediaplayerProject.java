/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mediaplayerproject;

import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import static java.time.temporal.TemporalAdjusters.previous;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import static javafx.scene.paint.Color.color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
    
    

    /**
     * @param args the command line arguments
     */
    //Kreyasyon metod yo
    void CreateWindow() {
        window.setTitle("vlc Media Player");
        window.getIcons().add( new Image(getClass().getResourceAsStream("vlc1.png")));
//        root.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), Insets.EMPTY)));
        Scene scene = new Scene(root, 300, 300); 

        root.setStyle("-fx-background-color: black");

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
        Menu media = new Menu("Media");

        Menu playback = new Menu("Playback");
        Menu audio = new Menu("Audio");
        Menu video = new Menu("Video");
//        Menu subtitle = new Menu("Subtitle");
//        Menu tooll = new Menu("Tool");
        Menu view = new Menu("View");
        Menu help = new Menu("Help");
//        
//         create all sub menu

//        media sub menu
        MenuItem openFile = new MenuItem("Open File");
        openFile.setOnAction(e -> openFile());
        MenuItem openFiles = new MenuItem("Open Mutiple Files");
        openFiles.setOnAction(e->openFiles());
        MenuItem openFolder = new MenuItem("Open Folder");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> exitMedia());

//        add sub media menu in menu media
        media.getItems().addAll(openFile, openFiles, openFolder, quit);
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
        MenuItem faster = new MenuItem("Faster");
        MenuItem fasterFine = new MenuItem("Faster (fine)");
        MenuItem normal = new MenuItem("Normal");
        MenuItem slow = new MenuItem("Slow");
        MenuItem slowFine = new MenuItem("Slow (fine)");
        //        add sub media menu in menu media
        speed.getItems().addAll(faster, fasterFine, normal, slow, slowFine);
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
        MenuItem playlist = new MenuItem("Playlist");
        //   add sub menu video in menu video
        view.getItems().addAll(playlist);
//        end 

//      help sub menu
        MenuItem help1 = new MenuItem("Help");
        MenuItem about = new MenuItem("About");
        //   add sub menu video in menu video
        help.getItems().addAll(help1, about);
        //        end 

        //ajouter tout menu yo nan menu bar la 
        menuBar.getMenus().addAll(media, playback, audio, video, view, help);
        //ajouter menuBar la nan borderPane nan 
        root.setTop(menuBar);
    }

    private void openFile() {
        chooser = new FileChooser();
        chooser.setTitle("ChOOSE A MUSIC OR A VIDEO");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("audio/video", "*.mp3", "*.mp4", "*.flv"));
        File fileselect = chooser.showOpenDialog(window);
        if (fileselect != null) {
            try {
                media = new Media(fileselect.toURI().toURL().toString());
                // passons le media au mediaplayer
                player = new MediaPlayer(media);
                // jouer le media
                player.setAutoPlay(true);
                player.play();
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

    private void openFiles(){
    Iterator<String> itr;
    ArrayList<String> listFiles = new ArrayList<String>();
    itr = listFiles.iterator();
     System.out.println(itr);
    
//    play(itr.next());
    
    
    
    
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

        Button btnPlay_Pause = new Button();
        Image image_btnPlay_Pause = new Image(getClass().getResourceAsStream("pause.png"));
        btnPlay_Pause.setGraphic(new ImageView(image_btnPlay_Pause));
        btnPlay_Pause.setOnAction(e -> {
            if (playerState == false) {
                btnPlay_Pause.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("play.png"))));
                if (player != null) {
                    playerState = !playerState;
                    player.play();
                     System.out.println("you've been clicked in play btn ");
                }

            } else {

                btnPlay_Pause.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("pause.png"))));
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
                btnPlay_Pause.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("pause.png"))));

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
        HBox wrp_btnPlay_Pause = new HBox();
        wrp_btnPlay_Pause.getChildren().addAll(btnPlay_Pause);
        wrp_btnPlay_Pause.setPadding(new Insets(10, 10, 15, 10));

        HBox wrp_prev_stop_next = new HBox(3);
        wrp_prev_stop_next.getChildren().addAll(btn_Previous, btn_stop, btn_next);
        wrp_prev_stop_next.setPadding(new Insets(10, 10, 15, 10));

        HBox wrp_mute = new HBox();
        wrp_mute.getChildren().addAll(btnMute_unMute);
        wrp_mute.setPadding(new Insets(10, 10, 15, 10));

        HBox container = new HBox();
        container.setStyle("-fx-background-color: white");
        container.getChildren().addAll(wrp_btnPlay_Pause, wrp_prev_stop_next, wrp_mute);
        root.setBottom(container);

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

}

//    void buttonMediaPlayer() {
//
//        // bouton 
//        Button btnPlay = new Button("Play");
//        Image image_Play = new Image(getClass().getResourceAsStream("play.png"));
//        btnPlay.setGraphic(new ImageView(image_Play));
//
//        Button btnStop = new Button("Stop");
//        Image image_Stop = new Image(getClass().getResourceAsStream("stop.png"));
//        btnStop.setGraphic(new ImageView(image_Stop));
//
//        Button btnPrevious = new Button("Previous");
//        Image image_Previous = new Image(getClass().getResourceAsStream("previous.png"));
//        btnPrevious.setGraphic(new ImageView(image_Previous));
//
//        Button btnNext = new Button("Next");
//        Image image_Next = new Image(getClass().getResourceAsStream("next.png"));
//        btnNext.setGraphic(new ImageView(image_Next));
//
//        Button btnPause = new Button("Pause");
//        Image image_Pause = new Image(getClass().getResourceAsStream("pause.png"));
//        btnPause.setGraphic(new ImageView(image_Pause));
//
//        Button btnfllScreen = new Button();
//        Image image_fllScreen = new Image(getClass().getResourceAsStream("full-screen.png"));
//        btnfllScreen.setGraphic(new ImageView(image_fllScreen));
//
//        Button btnnormalScreen = new Button();
//        Image imagenmScreen = new Image(getClass().getResourceAsStream("normal-screen.png"));
//        btnnormalScreen.setGraphic(new ImageView(imagenmScreen));
//        btnnormalScreen.setOnAction(e -> fullScreen());
//
//        Button btnAudio = new Button();
//        Image image_btnAudio = new Image(getClass().getResourceAsStream("audio.png"));
//        btnAudio.setGraphic(new ImageView(image_btnAudio));
//
//        Button btnNoAudio = new Button();
//        Image image_noAudio = new Image(getClass().getResourceAsStream("no-audio.png"));
//        btnNoAudio.setGraphic(new ImageView(image_noAudio));
//
//        //        ajouter yon hBox 
//        HBox hbMere = new HBox(60);
//        HBox hb = new HBox();
//        HBox hb0 = new HBox(5);
//        HBox hb1 = new HBox(5);
//
//        hb.getChildren().addAll(btnnormalScreen);
//        hb1.getChildren().addAll(btnAudio);
////        hb1.setPadding(new Insets(0,0,0,300));
//
////ajoute btn yo nan HBox mwen an
//        //        hb.getChildren().addAll(btnStop, btnPrevious, btnStop, btnNext);
//        hb0.getChildren().addAll(btnPlay, btnPrevious, btnStop, btnNext);
//
//        hbMere.getChildren().addAll(hb, hb0, hb1);
//        hbMere.setPadding(new Insets(10, 0, 10, 0));
//        hbMere.setAlignment(Pos.CENTER);
//
//        root.setBottom(hbMere);
//
//    }
//
//    private void quitterMedia() {
//     // System.exit(0);
//      stage.close();
//    }
//
//    private void dullScreen() {
//        window.setMaximized(true);
//    }
//
//##############################################################################
//   
//    
//    void creerMenu(){
//        MenuBar menuBar=new MenuBar();
//        Menu fichier=new Menu("Fichier");
//        Menu edit=new Menu();
//        Label lbEdit=new Label("Edition");
//        edit.setGraphic(lbEdit);
//        lbEdit.setOnMouseClicked(e->callWindow());
//        Menu view=new Menu("Affichage");
//        Menu apropos=new Menu("Apropos");
//        
//        // sous-menus
//        MenuItem ouvrir=new MenuItem("Ouvrir media");
//        ouvrir.setOnAction(e->ouvrirMedia());
//        MenuItem quitter=new MenuItem("Quitter");
//        quitter.setOnAction(e->quitterMedia());
//        MenuItem maximiser=new MenuItem("Maximiser");
//        maximiser.setOnAction(e->maximiser());
//        MenuItem pleinEcran=new MenuItem("Plein Ecran");
//        pleinEcran.setOnAction(e->pleinEcran());
//      
//        // ajouter les MenuItem dans les menus
//        fichier.getItems().addAll(ouvrir,quitter);
//        view.getItems().addAll(maximiser,pleinEcran);
//        // ajouter les Menus dans la barre de menu
//        menuBar.getMenus().addAll(fichier,edit,view,apropos);
//        // ajouter le menuBar dans le top du BorderPane
//        pane.setTop(menuBar);
//        
//    }
//    
//    void showFenetre(){
//        stage.setMaximized(true);
//        stage.show();
//    }
//    
//    void boutonMedia(){
//        HBox hb=new HBox(10);
//        Button btStop=new Button("Stop");
//        btStop.setOnAction(e->{
//           // Alert a=new Alert(AlertType.INFORMATION,btStop.getText());
//            //a.setContentText(btStop.getText());
//           // a.show();
//           if(btStop.getText().equals("Stop")){
//              btStop.setText("Play"); 
//              Tooltip t=new Tooltip();
//              t.setText("Info bulle");
//              btStop.setTooltip(t);
//               if(player!=null){
//                player.play();
//            }
//           
//           }else{
//               btStop.setText("Stop");
//                if(player!=null){
//                player.stop();
//            }
//           }
//        });
//        Button btPause=new Button("Pause");
//         btPause.setOnAction(e->{
//            if(player!=null){
//                player.pause();
//            }
//        });
//        Button btMute=new Button("Mute");
//        btMute.setOnAction(e->{
//            if(player!=null){
//                player.setMute(true);
//            }
//        });
//        hb.getChildren().addAll(btStop,btPause,btMute);
//         hb.setPadding(new Insets(10,10,15,10));
//        pane.setBottom(hb);
//        
//        
//    }
//    private void ouvrirMedia() {
//     chooser=new FileChooser();
//     chooser.setTitle("Choisir une musique ou une video");
//     chooser.getExtensionFilters().addAll(new ExtensionFilter("audio/video","*.mp3","*.mp4","*.flv"));
//     File fileselect=chooser.showOpenDialog(stage);
//     if(fileselect!=null){
//         try {
//             media=new Media(fileselect.toURI().toURL().toString());
//            // passons le media au mediaplayer
//            player=new MediaPlayer(media);
//            // jouer le media
//            player.setAutoPlay(true);
//            player.play();
//            // passons le mediaPlayer au mediaView
//            view=new MediaView(player);
//            // ajouter le MediaView au centre du BorderPane
//            pane.setCenter(view);
//         } catch (MalformedURLException ex) {
//            Alert alert=new Alert(AlertType.ERROR);
//            alert.setTitle("Erreur ");
//            alert.setContentText(ex.getMessage());
//            alert.show();
//         }
//     }
//    }
//
//    private void quitterMedia() {
//     // System.exit(0);
//      stage.close();
//    }
//
//    private void maximiser() {
//        stage.setMaximized(true);
//    }
//
//    private void pleinEcran() {
//      stage.setFullScreen(true);
//      stage.setFullScreenExitHint("Pressez CTRL+X pour sortir");
//      stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("CTRL+X"));
//    }
////
////    private void callWindow() {
////        FenetreOnglet ong=new FenetreOnglet();
////        ong.initModality(Modality.WINDOW_MODAL);
////        ong.initOwner(stage);
////        //ong.initStyle(StageStyle.UTILITY);
////        ong.showAndWait();
////    }
//    
//    
//    
//}
