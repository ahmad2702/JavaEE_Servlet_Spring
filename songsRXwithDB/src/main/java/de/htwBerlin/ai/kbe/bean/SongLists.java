package de.htwBerlin.ai.kbe.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "songlists")
@Entity
@Table(name = "SongLists")
public class SongLists {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private boolean securityType;
	//1 is public, 2 is private

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "SongListsMapping", joinColumns = { @JoinColumn(name = "songlists_id") }, inverseJoinColumns = {
			@JoinColumn(name = "song_id") })
	private List<Song> songs;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public SongLists(boolean securityType, User user, List<Song> songs) {
		super();
		this.securityType = securityType;
		this.user = user;
		this.songs = songs;
	}

	public SongLists(boolean securityType, List<Song> songs) {
		super();
		this.securityType = securityType;
		this.songs = songs;
	}

	public SongLists() {
	}

	public int getId() {
		return this.id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public boolean securityType() {
		return securityType;
	}

	public void securityType(boolean isPublic) {
		this.securityType = isPublic;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "SongLists [id=" + id + ", isPublic=" + securityType + ", songs=" + songs + ", user=" + user + "]";
	}

}
