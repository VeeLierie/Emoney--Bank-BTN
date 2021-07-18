package id.co.hanoman.controllers;

import id.co.hanoman.emoney.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.hanoman.emoney.util.NetClientEmoney;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="emoney", description="Gateway for emoney api")
@RestController
@RequestMapping("/emoney")

public class EmoneyController {

	@Autowired
	NetClientEmoney netClientEmoney;
	
	static Logger log = LoggerFactory.getLogger(NetClientEmoney.class);

	@ApiOperation(value = "inquiry",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	@RequestMapping(value = "/inquiry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> inquiry(@RequestBody TrxInquiry req){
		Object res = null;
		try {
			log.info("request inquiry : "+getJson(req));
			res = netClientEmoney.inquiry(req);
			log.info("response inquiry : "+getJson(res));
		} catch (Exception e) {
			log.error("inquiry",e);
		}
		return ResponseEntity.ok(res);
	}

	@ApiOperation(value = "topUp",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	@RequestMapping(value = "/topUp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> topUp(@RequestBody TrxPembayaran req){
		Object res = null;
		try {
			log.info("request topUp : "+getJson(req));
			res = netClientEmoney.payment(req);
			log.info("response topUp : "+getJson(res));
		} catch (Exception e) {
			log.error("topUp",e);
		}
		return ResponseEntity.ok(res);
	}

	@ApiOperation(value = "oldApplet",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	@RequestMapping(value = "/oldApplet", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> oldApplet(@RequestBody TrxAllApplet req){
		Object res = null;
		try {
			log.info("request oldApplet : "+getJson(req));
			res = netClientEmoney.oldApplet(req);
			log.info("response oldApplet : "+getJson(res));
		} catch (Exception e) {
			log.error("oldApplet",e);
		}
		return ResponseEntity.ok(res);
	}

	@ApiOperation(value = "newApplet",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	@RequestMapping(value = "/newApplet", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> newApplet(@RequestBody TrxAllApplet req){
		Object res = null;
		try {
			log.info("request newApplet : "+getJson(req));
			res = netClientEmoney.newApplet(req);
			log.info("response newApplet : "+getJson(res));
		} catch (Exception e) {
			log.error("newApplet",e);
		}
		return ResponseEntity.ok(res);
	}

	@ApiOperation(value = "confrimNewAppllet",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

	@RequestMapping(value = "/confrimNewAppllet", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> update(@RequestBody TrxConfrimApplet req){
		Object res = null;
		try {
			log.info("request confrimNewAppllet : "+getJson(req));
			res = netClientEmoney.confrimNewAppllet(req);
			log.info("response confrimNewAppllet : "+getJson(res));
		} catch (Exception e) {
			log.error("confrimNewAppllet",e);
		}
		return ResponseEntity.ok(res);
	}


	public JsonNode getJson(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode reqJson = mapper.valueToTree(obj);
		return reqJson;
	}
	
}
