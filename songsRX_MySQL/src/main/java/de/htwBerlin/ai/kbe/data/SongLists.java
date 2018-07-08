package de.htwBerlin.ai.kbe.data;

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
	// 1 is public, 0 is private

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "SongListsMapping", joinColumns = {
			@JoinColumn(name = "songlists_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "song_id", referencedColumnName = "id") })
	private List<Song> songs;

	public SongLists(boolean securityType, User user, List<Song> songs) {
		this.securityType = securityType;
		this.user = user;
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

	public void setSecurityType(boolean securityType) {
		this.securityType = securityType;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "SongLists [id=" + id + ", securityType=" + securityType + ", songs=" + songs + ", user=" + user + "]";
	}

}
