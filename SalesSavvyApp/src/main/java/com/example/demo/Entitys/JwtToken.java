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
    
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expiresAt", nullable = false)
    private LocalDateTime expiresAt = LocalDateTime.now();

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
	 * @param createdAt
	 * @param expiresAt
	 */
	public JwtToken(Integer tokenid, User user, String token, LocalDateTime createdAt, LocalDateTime expiresAt) {
		super();
		this.tokenid = tokenid;
		this.user = user;
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
	}

	/**
	 * @param user
	 * @param token
	 * @param createdAt
	 * @param expiresAt
	 */
	public JwtToken(User user, String token, LocalDateTime createdAt, LocalDateTime expiresAt) {
		super();
		this.user = user;
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	@Override
	public String toString() {
		return "JwtToken [tokenid=" + tokenid + ", user=" + user + ", token=" + token + ", createdAt=" + createdAt
				+ ", expiresAt=" + expiresAt + "]";
	}

    
}
