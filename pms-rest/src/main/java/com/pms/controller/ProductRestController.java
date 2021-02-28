package com.pms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pms.dto.ProductDTO;
import com.pms.entity.Product;
import com.pms.repository.ProductRepository;

@RestController
public class ProductRestController {
	@Autowired
	ProductRepository repository;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		
		List<Product> productsList=repository.findAll();
		List<ProductDTO> productsDtoList= new ArrayList<>();
		
		productsList.forEach(product->{
			ProductDTO pDTO=new ProductDTO();
			BeanUtils.copyProperties(product, pDTO);
			productsDtoList.add(pDTO);
		});
		return new ResponseEntity<List<ProductDTO>>(productsDtoList,HttpStatus.OK);
	}
	
	@GetMapping("/products/{productid}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable("productid")Integer productId){
		Optional<Product> opt=repository.findById(productId);
		Product product=opt.get();
		ProductDTO pDTO=new ProductDTO();
		BeanUtils.copyProperties(product, pDTO);
		
		return new ResponseEntity<ProductDTO>(pDTO,HttpStatus.OK); 
		
	}
		

}
