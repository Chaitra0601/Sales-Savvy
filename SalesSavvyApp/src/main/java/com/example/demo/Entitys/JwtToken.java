package com.example.demo.Entitys;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jwttokens")
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenid;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(nullable = false, length = 512)
    private String token;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expires_at = LocalDateTime.now();

	/**
	 * 
	 */
	public JwtToken() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tokenid
	 * @param user
	 * @param token
	 * @param created_at
	 * @param expires_at
	 */
	public JwtToken(Integer tokenid, User user, String token, LocalDateTime created_at, LocalDateTime expires_at) {
		super();
		this.tokenid = tokenid;
		this.user = user;
		this.token = token;
		this.created_at = created_at;
		this.expires_at = expires_at;
	}

	/**
	 * @param user
	 * @param token
	 * @param created_at
	 * @param expires_at
	 */
	public JwtToken(User user, String token, LocalDateTime created_at, LocalDateTime expires_at) {
		super();
		this.user = user;
		this.token = token;
		this.created_at = created_at;
		this.expires_at = expires_at;
	}

	public Integer getTokenid() {
		return tokenid;
	}

	public void setTokenid(Integer tokenid) {
		this.tokenid = tokenid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getExpires_at() {
		return expires_at;
	}

	public void setExpires_at(LocalDateTime expires_at) {
		this.expires_at = expires_at;
	}
	
	
}