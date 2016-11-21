package model;

import java.util.ArrayList;

public class TrackList {
    private ArrayList<Track> tracks;

    public TrackList() {
        this.tracks = new ArrayList();
    }

    public TrackList(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public Track getTrack(int index) {
        return tracks.get(index);
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void addTrack(int index, Track newTrack) {
        tracks.add(index, newTrack);
    }

    public void deleteTrack(int index) {
        tracks.remove(index);
    }

    public void setTrack(int index, String nameOfTrack, String band, String album, String duration, String genre) {
        Track changedTrack = tracks.get(index);
        changedTrack.setAlbum(album);
        changedTrack.setBand(band);
        changedTrack.setDuration(duration);
        changedTrack.setName(nameOfTrack);
        changedTrack.setGenre(genre);
    }

    public Track getTrackByNameOfTrack(String nameOfTrack) {
        Track track = null;
        for (int i = 0; i < this.tracks.size(); i++)
            if (nameOfTrack.equals(this.tracks.get(i).getName()))
                track = tracks.get(i);
        return track;
    }

    public void sortTracks(String type) {
        if (type.equals("Duration"))
            tracks.sort((o1, o2) -> o1.getDuration().compareTo(o2.getDuration()));
        if (type.equals("Name"))
            tracks.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        if (type.equals("Album"))
            tracks.sort((o1, o2) -> o1.getAlbum().compareTo(o2.getAlbum()));
        if (type.equals("Band"))
            tracks.sort((o1, o2) -> o1.getBand().compareTo(o2.getBand()));
        if (type.equals("Genre"))
            tracks.sort((o1, o2) -> o1.getGenre().compareTo(o2.getGenre()));
    }

    public ArrayList<String> getGenres() {
        boolean flag;
        ArrayList<String> genresmass = new ArrayList<String>();
        for (int i = 0; i < tracks.size(); i++) {
            flag = true;
            for (int j = 0; j < genresmass.size(); j++)
                if (genresmass.get(j).equals(tracks.get(i).getGenre())) {
                    flag = false;
                    break;
                }
            if (flag)
                genresmass.add(tracks.get(i).getGenre());
        }
        return genresmass;
    }
}
