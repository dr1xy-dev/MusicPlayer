import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {
    public static void main(String[] args) {
        
        String songPath = "song.wav";
        File file = new File(songPath);

        try(Scanner scanner = new Scanner(System.in); AudioInputStream audioStream = AudioSystem.getAudioInputStream(file)){
        
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            String response = "";

            System.out.println("What would you like to do? (play, pause, restart, stop)");
            response = scanner.nextLine();

            while (!response.equals("quit")) {
                if (response.equals("play")) {
                    clip.start();
                }
                else if (response.equals("pause")) {
                    clip.stop();
                }
                else if (response.equals("restart")) {
                    clip.setMicrosecondPosition(0);
                    clip.start();
                }
                else if (response.equals("stop")) {
                    clip.stop();
                    clip.setMicrosecondPosition(0);
                }
                else if (response.equals("quit")) {
                    clip.stop();
                    clip.close();
                    System.out.println("Exiting program.");
                    break;
                }
                else {
                    System.out.println("Invalid command.");
                }
                response = scanner.nextLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("The specified audio file was not found.");
        }
        catch (LineUnavailableException e) {
            System.out.println("Audio line for playing back is unavailable.");
        }
        catch (UnsupportedAudioFileException e) {
            System.out.println("The specified audio file is not supported.");
        }
        catch(IOException e) {
            System.out.println("An error occurred.");
        }
        finally {
            System.out.println("Execution completed.");
        }

    }

}
