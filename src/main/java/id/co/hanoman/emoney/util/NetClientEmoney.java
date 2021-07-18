package id.co.hanoman.emoney.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;

import com.fasterxml.jackson.databind.node.ObjectNode;
import id.co.hanoman.emoney.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.hanoman.config.YAMLConfig;
import id.co.hanoman.domain.Token;

import javax.net.ssl.*;

@Component
public class NetClientEmoney {
	static Logger log = LoggerFactory.getLogger(NetClientEmoney.class);

	@Autowired	
	YAMLConfig config ;

	public void getAllCerts() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
			public void checkClientTrusted(X509Certificate[] certs, String authType) { }
			public void checkServerTrusted(X509Certificate[] certs, String authType) { }
		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) { return true; }
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		/* End of the fix*/
	}

	public String getToken() throws Exception{
		Token token = null;
		if(token==null){

			String userAndPass = config.getUsername() +":"+ config.getPassword();
			String encodedDataString = Base64.getUrlEncoder().encodeToString(userAndPass.getBytes());

			try {
				getAllCerts();

				URL url = new URL(config.getBaseUrl() +config.getUrlToken());
				log.info("Access Token Url :"+url);
				HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

				conn.setDoOutput(false);
				conn.setDoInput(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic "+ encodedDataString);

				if (conn.getResponseCode() != 200) {
					ErrorResponse errRes = new ErrorResponse();
					errRes.setCode(String.valueOf(conn.getResponseCode()));
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
					ObjectMapper mapper = new ObjectMapper();
					JsonNode root = mapper.readTree(br);
					errRes.setError(root);
					log.error("Error Token : "+errRes);
				}else {
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					ObjectMapper mapper = new ObjectMapper();
					JsonNode root = mapper.readTree(br);
					log.info("Response Token:"+ root);
					String accessToken = root.get("jwt") != null ?root.get("jwt").asText():"";
					Token tokenNew = new Token();
					tokenNew.setToken(accessToken);
					return accessToken;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Object inquiry(TrxInquiry req) throws Exception{
		Object resCall = null;
		try {

			String transactionId = req.getTransactionId();
			String paymentId1 = req.getPaymentId1();
			String amount = req.getAmount();

			String bodyRequest = "{\"requestHeader\":{\"merchantId\":\""+config.getMerchantId()+"\",\"transactionId\":\""+transactionId+"\",\"paymentType\":\""+config.getPaymentType()+"\"},\"inquiryRequest\":{\"paymentId1\":\""+paymentId1+"\",\"amount\":\""+amount+"\"}}";

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(bodyRequest);
			resCall = root;
			resCall = callUrl(bodyRequest, config.getUrlInquiry());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}

	public Object payment(TrxPembayaran req) throws Exception{
		Object resCall = null;
		try {

			String transactionId = req.getTransactionId();
			String paymentId1 = req.getPaymentId1();
			String amount = req.getAmount();
			String reff = req.getRefference();

			String bodyRequest = "{\"requestHeader\":{\"merchantId\":\""+config.getMerchantId()+"\",\"transactionId\":\""+transactionId+"\",\"paymentType\":\""+config.getPaymentType()+"\"},\"topupRequest\":{\"paymentId1\":\""+paymentId1+"\",\"amount\":\""+amount+"\",\"refference\":\""+reff+"\"}}";

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(bodyRequest);
			resCall = root;
			resCall = callUrl(bodyRequest, config.getUrlPayment());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}

	public Object oldApplet(TrxAllApplet req) throws Exception{
		Object resCall = null;
		try {

			String dateTime = req.getDateTime();
			String cardAttribute = req.getCardAttribute();
			String amount = req.getAmount();
			String lastBalance = req.getLastBalance();
			String approvalCode = req.getApprovalCode();
			String inquiryId = req.getInquiryId();
			String cardInfo = req.getCardInfo();
			String cardUUID = req.getCardUUID();

			String bodyRequest = "{\"requestHeader\":{\"merchantId\":\""+config.getMerchantId()+"\",\"transactionId\":\""+transactionId+"\",\"paymentType\":\""+config.getPaymentType()+"\"},\"topupRequest\":{\"paymentId1\":\""+paymentId1+"\",\"amount\":\""+amount+"\",\"refference\":\""+reff+"\"}}";

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(bodyRequest);
			resCall = root;
			resCall = callUrl(bodyRequest, config.getUrlPayment());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}

	public Object newApplet(TrxAllApplet req) throws Exception{

		return null;
	}

	public Object confrimNewAppllet(TrxConfrimApplet req) throws Exception{
		Object resCall = null;
		try {

//			String transactionId = req.getTransactionId();
//			String paymentId1 = req.getPaymentId1();
//			String amount = req.getAmount();
//			String reff = req.getRefference();

			String bodyRequest = "{\"requestHeader\":{\"merchantId\":\""+config.getMerchantId()+"\",\"transactionId\":\""+transactionId+"\",\"paymentType\":\""+config.getPaymentType()+"\"},\"topupRequest\":{\"paymentId1\":\""+paymentId1+"\",\"amount\":\""+amount+"\",\"refference\":\""+reff+"\"}}";

			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(bodyRequest);
			resCall = root;
			resCall = callUrl(bodyRequest, config.getUrlPayment());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resCall;
	}

	private Object callUrl(String data, String subUrl) throws Exception{

		String jwt  = getToken();
		log.info("Access Token :"+ jwt);

		URL url = new URL(config.getBaseUrl()+subUrl);
		log.info("CallUrl Payment :"+url);

		try {

			getAllCerts();
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer "+ jwt);

			OutputStream os = conn.getOutputStream();
			os.write(data.getBytes());
			os.flush();
			log.info("Request Body :"+data);

			if (conn.getResponseCode() != 200) {
				ErrorResponse errRes = new ErrorResponse();
				String responseCode = String.valueOf(conn.getResponseCode());
				errRes.setCode(responseCode);
				errRes.setFaultCode("G01");
				errRes.setFaultMessage("Error Server Emoney");
				log.info("error responseCode :"+ responseCode);
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(br);
				errRes.setError(root);

				return errRes;

			}else {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()),"UTF-8"));
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode parentNode = mapper.createObjectNode();
				JsonNode root = mapper.readTree(br);

				String[] keys = {"status"};
//				String[] keys = {"responseHeader"};

				ArrayList<String> list = new ArrayList<String>();
				for (String key : keys) {
					JsonNode value = root.findValue(key);
					String findNode = value == null ? null : value.asText();
					list.add(findNode);
				}

				if( list.get(0) != "0" ) {
					parentNode.put("faultCode", "G02");
					parentNode.put("faultMessage", "Invalid Transaction");
					String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parentNode);
					return jsonString;
				} else{
					return root;
				}
			}
		} catch (Exception e) {
			String strErrMsg = e.toString();
			log.info("error payment :"+ strErrMsg);
			ErrorResponse errRes = new ErrorResponse();
			errRes.setCode("400");
			if(strErrMsg.equals("java.net.ConnectException: Connection refused: connect")){
				errRes.setFaultCode("G01");
				errRes.setFaultMessage("Failed to Connect");
			} else if(strErrMsg.equals("java.net.SocketTimeoutException: Read timed out")){
				errRes.setFaultCode("G01");
				errRes.setFaultMessage("Timeout from Server");
			} else if(strErrMsg.equals("java.net.SocketException: Connection reset")){
				errRes.setFaultCode("G01");
				errRes.setFaultMessage("Bad Message Request");
			}else {
				errRes.setFaultCode("G99");
				errRes.setFaultMessage("General Error");
			}
			return errRes;
		}
	}

}


