package com.arnab.webservice.service;

import com.arnab.webservice.template.ContactDetailsTemplate;
import com.arnab.webservice.template.TransactionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

//token
@Service
public class CustomerService {
    @Autowired
    private RestTemplate restTemplate;
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcm5hYiIsImV4cCI6MTY2MDEzMzg1MiwiaWF0IjoxNjI4NTk3ODUyfQ.iMXaxYDRkdc0zv7smePVKufew2lMwNPWklBeq2BjoPjWWJzPdQZi9nJdbgShuDdnuFwHt7XttywRTe_Fsxcjzg";



    // customer api
    public ResponseEntity<Object[]> getCustomerByName(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        return restTemplate.exchange("http://localhost:8080/get/" + name , HttpMethod.GET,entity, Object[].class);
    }

    public Object getCustomerById(Long id) {
        while(true){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer "+token);

            HttpEntity<String> entity = new HttpEntity<String>("body",headers);

            try{
                return restTemplate.exchange("http://localhost:8080/cus/" + id, HttpMethod.GET,entity, Object.class);
            }catch (Exception e){
                e.printStackTrace();
                Map<String,String>response = new HashMap<>();
                response.put("error","id [" + id + "] not found");
                response.put("httpStatus", "404");
                return ResponseEntity.badRequest().body(response);
            }
        }

        //url, HttpMethod.POST, entity, String.class
        //return this.restTemplate.getForObject("http://localhost:8080/cus/" + id , Object.class);
    }

    public Object getCustomerByUsername(String userName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        try{
            return restTemplate.exchange("http://localhost:8080/username/" + userName ,HttpMethod.GET,entity, Object.class);
        }catch(Exception e){
            e.printStackTrace();
            Map<String,String>response = new HashMap<>();
            response.put("error","id [" + "" + "] not found");
            response.put("httpStatus", "404");
            return ResponseEntity.badRequest().body(response);
        }
    }


    //product api
    public Object getProductById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        try{
            return restTemplate.exchange("http://localhost:8080/pro/" + id ,HttpMethod.GET,entity, Object.class);
        }catch(Exception e){
            e.printStackTrace();
            Map<String,String>response = new HashMap<>();
            response.put("error","id [" + id + "] not found");
            response.put("httpStatus", "404");
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<Object[]> productSortedByPrice() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        return restTemplate.exchange("http://localhost:8080/psort/" , HttpMethod.GET,entity,Object[].class);
    }

    //transaction api
    public ResponseEntity<Object[]> getTransactionForOneDay(String date) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        return restTemplate.exchange("http://localhost:8080/transaction/" + date,HttpMethod.GET,entity, Object[].class);
    }

    public ResponseEntity<Object[]> getTransactionByDate(String from, String to) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        return restTemplate.exchange("http://localhost:8080/transaction/" + from + "/" + to,HttpMethod.GET,entity,
                Object[].class);
    }

    public ResponseEntity<Long> getSellByDate(String date){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        return restTemplate.exchange("http://localhost:8080/sell/" + date , HttpMethod.GET,entity,Long.class);
    }

    public ResponseEntity<Object[]> getAllTransaction() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        return restTemplate.exchange("http://localhost:8080/transaction/" ,HttpMethod.GET,entity, Object[].class);
    }



    public Object postTransaction(TransactionTemplate transactionTemplate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        //MultiValueMap<String, TransactionTemplate> map= new LinkedMultiValueMap<String, TransactionTemplate>();
        //map.add("email", transactionTemplate);
        HttpEntity<TransactionTemplate> request = new HttpEntity<>(transactionTemplate, headers);
        //HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        try{
            return restTemplate.postForObject("http://localhost:8080/transaction/" ,request,Object.class);
        }catch (Exception e){
            Map<String,String>response = new HashMap<>();
            response.put("error","BAD Request");
            response.put("httpStatus", "404");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //contact details api
    public Object postContactDetails(ContactDetailsTemplate contactDetailsTemplate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);
        //MultiValueMap<String, TransactionTemplate> map= new LinkedMultiValueMap<String, TransactionTemplate>();
        //map.add("email", transactionTemplate);
        HttpEntity<ContactDetailsTemplate> request = new HttpEntity<>(contactDetailsTemplate, headers);
        //HttpEntity<String> entity = new HttpEntity<String>("body",headers);
        return restTemplate.postForObject("http://localhost:8080/cd_save/" ,request,Object.class);
    }
}
