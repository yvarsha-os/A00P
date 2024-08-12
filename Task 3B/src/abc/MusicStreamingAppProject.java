package abc;

//Common MusicSource interface
interface MusicSource {
 void play();
}

//Adaptee classes: Different Music Sources
class LocalFilePlayer {
 public void playLocalFile() {
     System.out.println("Playing music from a local file.");
 }
}

class OnlineStreamingPlayer {
 public void playOnlineStream() {
     System.out.println("Playing music from an online streaming service.");
 }
}

class RadioStationPlayer {
 public void playRadioStation() {
     System.out.println("Playing music from a radio station.");
 }
}

//Adapter classes: Adapting different music sources to the common interface
class LocalFilePlayerAdapter implements MusicSource {
 private LocalFilePlayer localFilePlayer;

 public LocalFilePlayerAdapter(LocalFilePlayer localFilePlayer) {
     this.localFilePlayer = localFilePlayer;
 }

 @Override
 public void play() {
     localFilePlayer.playLocalFile();
 }
}

class OnlineStreamingPlayerAdapter implements MusicSource {
 private OnlineStreamingPlayer onlineStreamingPlayer;

 public OnlineStreamingPlayerAdapter(OnlineStreamingPlayer onlineStreamingPlayer) {
     this.onlineStreamingPlayer = onlineStreamingPlayer;
 }

 @Override
 public void play() {
     onlineStreamingPlayer.playOnlineStream();
 }
}

class RadioStationPlayerAdapter implements MusicSource {
 private RadioStationPlayer radioStationPlayer;

 public RadioStationPlayerAdapter(RadioStationPlayer radioStationPlayer) {
     this.radioStationPlayer = radioStationPlayer;
 }

 @Override
 public void play() {
     radioStationPlayer.playRadioStation();
 }
}

//Implementor interface for the Bridge pattern
interface MusicPlayback {
 void playMusic();
}

//Concrete Implementor for basic playback functionality
class BasicPlayback implements MusicPlayback {
 private MusicSource musicSource;

 public BasicPlayback(MusicSource musicSource) {
     this.musicSource = musicSource;
 }

 @Override
 public void playMusic() {
     musicSource.play();
 }
}

//Abstraction class for the Bridge pattern
abstract class MusicPlayer {
 protected MusicPlayback musicPlayback;

 public MusicPlayer(MusicPlayback musicPlayback) {
     this.musicPlayback = musicPlayback;
 }

 public abstract void play();
}

//Refined Abstraction that adds advanced playback capabilities
class AdvancedMusicPlayer extends MusicPlayer {
 public AdvancedMusicPlayer(MusicPlayback musicPlayback) {
     super(musicPlayback);
 }

 @Override
 public void play() {
     System.out.println("Advanced music player in action.");
     musicPlayback.playMusic();
 }
}

//Basic Decorator class for adding features to MusicPlayback
abstract class MusicPlayerDecorator implements MusicPlayback {
 protected MusicPlayback decoratedMusicPlayback;

 public MusicPlayerDecorator(MusicPlayback musicPlayback) {
     this.decoratedMusicPlayback = musicPlayback;
 }

 public void playMusic() {
     decoratedMusicPlayback.playMusic();
 }
}

//Concrete Decorator: Equalizer
class EqualizerDecorator extends MusicPlayerDecorator {
 public EqualizerDecorator(MusicPlayback musicPlayback) {
     super(musicPlayback);
 }

 @Override
 public void playMusic() {
     super.playMusic();
     setEqualizer();
 }

 private void setEqualizer() {
     System.out.println("Applying equalizer settings.");
 }
}

//Concrete Decorator: Volume Control
class VolumeControlDecorator extends MusicPlayerDecorator {
 public VolumeControlDecorator(MusicPlayback musicPlayback) {
     super(musicPlayback);
 }

 @Override
 public void playMusic() {
     super.playMusic();
     setVolume();
 }

 private void setVolume() {
     System.out.println("Adjusting the volume.");
 }
}

//Main application class
public class MusicStreamingApp {
 public static void main(String[] args) {
     // Adapting different music sources
     MusicSource localFileSource = new LocalFilePlayerAdapter(new LocalFilePlayer());
     MusicSource onlineStreamingSource = new OnlineStreamingPlayerAdapter(new OnlineStreamingPlayer());
     MusicSource radioStationSource = new RadioStationPlayerAdapter(new RadioStationPlayer());

     // Using Bridge pattern for playback
     MusicPlayback basicPlaybackLocal = new BasicPlayback(localFileSource);
     MusicPlayback basicPlaybackOnline = new BasicPlayback(onlineStreamingSource);
     MusicPlayback basicPlaybackRadio = new BasicPlayback(radioStationSource);

     // Adding features using Decorator pattern
     MusicPlayback localWithFeatures = new EqualizerDecorator(new VolumeControlDecorator(basicPlaybackLocal));
     MusicPlayback onlineWithFeatures = new EqualizerDecorator(new VolumeControlDecorator(basicPlaybackOnline));
     MusicPlayback radioWithFeatures = new EqualizerDecorator(new VolumeControlDecorator(basicPlaybackRadio));

     // Using AdvancedMusicPlayer to play music
     MusicPlayer advancedLocalPlayer = new AdvancedMusicPlayer(localWithFeatures);
     MusicPlayer advancedOnlinePlayer = new AdvancedMusicPlayer(onlineWithFeatures);
     MusicPlayer advancedRadioPlayer = new AdvancedMusicPlayer(radioWithFeatures);

     // Playing music from different sources
     advancedLocalPlayer.play();
     System.out.println();

     advancedOnlinePlayer.play();
     System.out.println();

     advancedRadioPlayer.play();
 }
}
