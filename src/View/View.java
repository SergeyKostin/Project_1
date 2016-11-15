package View;
import trackList.TrackList;

public interface View {
    public void printTrackList(TrackList trackList);
    public void printTracksByNameOfType(String type,TrackList trackList, String typeName);
}
