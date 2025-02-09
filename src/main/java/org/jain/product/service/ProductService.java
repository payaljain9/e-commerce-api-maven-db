package org.jain.product.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jain.common.DBUtils;
import org.jain.product.dto.Product;
import org.jain.product.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	ProductResponse productResponse;
	
	@Autowired 
	Product product;
	
	ResultSet rs;
	
	//List<Product> productList=new ArrayList<>();
	
	public ProductResponse searchProduct(int id)
	{
		productResponse.getProducts().clear();
		rs=DBUtils.executeSelectQuery("select * from product where ID="+id+"");
		try 
		{
			if(rs.next())
			{	
				product.setId(rs.getInt("ID"));
				product.setName(rs.getString("Name"));
				product.setPrice(rs.getInt("Price"));
				product.setQuantity(rs.getInt("Quantity"));
				
				productResponse.getProducts().add(product);
				
				//productList.add(product);
				
			//	productResponse.setProducts(productList);
				
				productResponse.setResponseCode("0000");
				productResponse.setResponseMessage("Product details fetched..!");
				
				return productResponse;
			}
			else
			{	
				productResponse.setResponseCode("911");
				productResponse.setResponseMessage("Product not available..!");
				
				return productResponse;
			}
		} 
		catch (SQLException e) 
		{
			productResponse.setResponseCode("911");
			productResponse.setResponseMessage("Product details fetching failed..!");
			
			return productResponse;
		}
		
		
   }
	

	public ProductResponse saveProduct(Product productRequest)
	{
		productResponse.getProducts().clear();
		try
		{
			int a=DBUtils.executeDMLQuery("insert into product values("+productRequest.getId()+", '"+productRequest.getName()+"', "+productRequest.getPrice()+", "+productRequest.getQuantity()+")");
			
			if(a>0)
			{
				product.setId(productRequest.getId());
				product.setName(productRequest.getName());
				product.setPrice(productRequest.getPrice());
				product.setQuantity(productRequest.getQuantity());
				
				productResponse.getProducts().add(product);
				
				productResponse.setResponseCode("0000");
				productResponse.setResponseMessage("Product added..!");
			}
			else
			{
				productResponse.setResponseCode("911");
				productResponse.setResponseMessage("Product add failed..!");
			}
		}
		catch(Exception e)
		{
			productResponse.setResponseCode("911");
			productResponse.setResponseMessage("Product add failed..!");
		}
		
		return productResponse;	
	}
	
	
	public List<ProductResponse> getAllProducts()
	{
		productResponse.getProducts().clear();
		rs=DBUtils.executeSelectQuery("select * from product");
		List<ProductResponse> productListResponse=new ArrayList<>();
		
		try
		{
			productResponse.setResponseCode("0000");
			productResponse.setResponseMessage("Product details fetched..!");
			
			while(rs.next())
			{
				Product product=new Product();
				product.setId(rs.getInt("ID"));
				product.setName(rs.getString("Name"));
				product.setPrice(rs.getInt("Price"));
				product.setQuantity(rs.getInt("Quantity"));
				
				productResponse.getProducts().add(product);
				
				//productList.add(product);
			}
		//	productResponse.setProducts(productList);
			
		}
		catch(Exception e)
		{
			productResponse.setResponseCode("911");
			productResponse.setResponseMessage("Product details fetching failed..!");
		}
		
		productListResponse.add(productResponse);
		
		return productListResponse;
	}
	
	
	
	
	/*
	public List<ProductResponse> getAllProducts()
	{
		List<ProductResponse> productListResponse=new ArrayList<>();
		rs=DBUtils.executeSelectQuery("select * from product");
		List<Product> productList=new ArrayList<>();
		
		
		try 
		{
			productResponse.setResponseCode("0000");
			productResponse.setResponseMessage("Product details fetched..!");
			
			while(rs.next())
			{
				product.setId(rs.getInt("ID"));
				product.setName(rs.getString("Name"));
				product.setPrice(rs.getInt("Price"));
				product.setQuantity(rs.getInt("Quantity"));
				
				productList.add(product);
				
				productResponse.setProducts(productList);
				
				productListResponse.add(productResponse);
			}
		} 
		catch (SQLException e) 
		{
			productResponse.setResponseCode("911");
			productResponse.setResponseMessage("Product details fetching failed..!");
			
			productListResponse.add(productResponse);
		}
		
		return productListResponse;
	}
	*/
	
	
}
