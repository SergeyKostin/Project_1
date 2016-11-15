package trackList;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.io.IOException;

public class RepositoryClass
{
    public ArrayList<Track> scanForTrack(String path) throws IOException  {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        byte[] buffer = new byte[128];
        ArrayList<Track> trackList = new ArrayList<Track>();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".mp3"))
            {
                RandomAccessFile mp3file = new RandomAccessFile(listOfFiles[i], "rw");
                mp3file.seek(mp3file.length()-128);
                mp3file.read(buffer);
                String nameOfTrack = "";
                String album = "";
                String band = "";
                for (int j = 3; j < 33; j++)
                    nameOfTrack += buffer[j];
                for (int j = 33; j<63; j++)
                    band +=buffer[j];
                for (int j = 63; j<93; j++)
                    album += buffer[j];
                Track newTrack = new Track(nameOfTrack ,album, band, "", "");
                trackList.add(i,newTrack);
            }
        }
        return trackList;
    }
}
