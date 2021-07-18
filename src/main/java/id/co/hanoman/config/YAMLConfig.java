package id.co.hanoman.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("emoney")

public class YAMLConfig {
  
	private String username;
	private String password;
	private String baseUrl;
	private String urlToken;
	private String urlInquiry;
	private String urlPayment;
	private String urlUpdate;
	private String paymentType;
	private String merchantId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUrlInquiry() {
		return urlInquiry;
	}

	public void setUrlInquiry(String urlInquiry) {
		this.urlInquiry = urlInquiry;
	}

	public String getUrlPayment() {
		return urlPayment;
	}

	public void setUrlPayment(String urlPayment) {
		this.urlPayment = urlPayment;
	}

	public String getUrlUpdate() {
		return urlUpdate;
	}

	public void setUrlUpdate(String urlUpdate) {
		this.urlUpdate = urlUpdate;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getUrlToken() {
		return urlToken;
	}

	public void setUrlToken(String urlToken) {
		this.urlToken = urlToken;
	}
}