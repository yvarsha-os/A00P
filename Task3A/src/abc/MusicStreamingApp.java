package abc; 
interface MusicPlayer {
    void play();
}

// Adaptee classes: Different Music Sources
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

// Adapter classes: Adapting different music sources to the common interface
class LocalFilePlayerAdapter implements MusicPlayer {
    private LocalFilePlayer localFilePlayer;

    public LocalFilePlayerAdapter(LocalFilePlayer localFilePlayer) {
        this.localFilePlayer = localFilePlayer;
    }

    @Override
    public void play() {
        localFilePlayer.playLocalFile();
    }
}

class OnlineStreamingPlayerAdapter implements MusicPlayer {
    private OnlineStreamingPlayer onlineStreamingPlayer;

    public OnlineStreamingPlayerAdapter(OnlineStreamingPlayer onlineStreamingPlayer) {
        this.onlineStreamingPlayer = onlineStreamingPlayer;
    }

    @Override
    public void play() {
        onlineStreamingPlayer.playOnlineStream();
    }
}

class RadioStationPlayerAdapter implements MusicPlayer {
    private RadioStationPlayer radioStationPlayer;

    public RadioStationPlayerAdapter(RadioStationPlayer radioStationPlayer) {
        this.radioStationPlayer = radioStationPlayer;
    }

    @Override
    public void play() {
        radioStationPlayer.playRadioStation();
    }
}

// Basic MusicPlayerDecorator class
abstract class MusicPlayerDecorator implements MusicPlayer {
    protected MusicPlayer decoratedMusicPlayer;

    public MusicPlayerDecorator(MusicPlayer musicPlayer) {
        this.decoratedMusicPlayer = musicPlayer;
    }

    public void play() {
        decoratedMusicPlayer.play();
    }
}

// Concrete Decorator: Equalizer
class EqualizerDecorator extends MusicPlayerDecorator {
    public EqualizerDecorator(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    @Override
    public void play() {
        super.play();
        setEqualizer();
    }

    private void setEqualizer() {
        System.out.println("Applying equalizer settings.");
    }
}

// Concrete Decorator: Volume Control
class VolumeControlDecorator extends MusicPlayerDecorator {
    public VolumeControlDecorator(MusicPlayer musicPlayer) {
        super(musicPlayer);
    }

    @Override
    public void play() {
        super.play();
        setVolume();
    }

    private void setVolume() {
        System.out.println("Adjusting the volume.");
    }
}

// Main application class
public class MusicStreamingApp {
    public static void main(String[] args) {
        // Playing music from a local file
        MusicPlayer localFilePlayer = new LocalFilePlayerAdapter(new LocalFilePlayer());
        MusicPlayer localFilePlayerWithFeatures = new EqualizerDecorator(new VolumeControlDecorator(localFilePlayer));
        localFilePlayerWithFeatures.play();

        System.out.println();

        // Playing music from an online streaming service
        MusicPlayer onlineStreamingPlayer = new OnlineStreamingPlayerAdapter(new OnlineStreamingPlayer());
        MusicPlayer onlineStreamingPlayerWithFeatures = new EqualizerDecorator(new VolumeControlDecorator(onlineStreamingPlayer));
        onlineStreamingPlayerWithFeatures.play();

        System.out.println();

        // Playing music from a radio station
        MusicPlayer radioStationPlayer = new RadioStationPlayerAdapter(new RadioStationPlayer());
        MusicPlayer radioStationPlayerWithFeatures = new EqualizerDecorator(new VolumeControlDecorator(radioStationPlayer));
        radioStationPlayerWithFeatures.play();
    }
}