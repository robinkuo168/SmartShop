package tw.com.pershing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tb_carousel")
public class Carousel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonProperty("id")
	private int carouselId;
	@Column(name="photo_name")
	private String photoName;
	@Column(name="google_drive")
	private String googleDrive;
	private int seq;
	public int getCarouselId() {
		return carouselId;
	}
	public void setCarouselId(int carouselId) {
		this.carouselId = carouselId;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getGoogleDrive() {
		return googleDrive;
	}
	public void setGoogleDrive(String googleDrive) {
		this.googleDrive = googleDrive;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
}
