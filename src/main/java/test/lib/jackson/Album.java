package test.lib.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.rdf4j.query.algebra.Str;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Album
{
    private String title;
    private String[] links;

    public Album(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public String[] getLinks()
    {
        return links;
    }

    public void setLinks(String[] links)
    {
        this.links = links;
    }

    private List<String> songs;

    public List<String> getSongs()
    {
        return songs;
    }

    public void setSongs(List<String> songs)
    {
        this.songs = songs;
    }

    private Artist artist;

    public Artist getArtist()
    {
        return artist;
    }

    public void setArtist(Artist artist)
    {
        this.artist = artist;
    }

    private Map<String, String> musicians = new HashMap<>();

    public Map<String, String> getMusicians()
    {
        return Collections.unmodifiableMap(musicians);
    }

    public void addMusician(String key, String value)
    {
        musicians.put(key, value);
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        Album album = new Album("Kind Of Blue");
        album.setLinks(new String[]{"link1", "link2"});

        List<String> songs = new ArrayList<>();
        songs.add("So what");
        songs.add("Flamenco Sketches");
        songs.add("Freddie Freeloader");

        album.setSongs(songs);

        Artist artist = new Artist();
        artist.name = "Miles Davis";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        artist.birthDate = format.parse("26-05-1926");
        album.setArtist(artist);


        album.addMusician("Miles Davis", "Trumpet, Band leader");
        album.addMusician("Julian Adderley", "Alto Saxophone");
        album.addMusician("Paul Chambers", "double bass");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        mapper.setDateFormat(outputFormat);

        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        mapper.writeValue(System.out, album);
    }
}