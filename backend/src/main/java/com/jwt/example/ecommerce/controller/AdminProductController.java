package com.jwt.example.ecommerce.controller;

import com.jwt.example.ecommerce.Exception.ProductException;
import com.jwt.example.ecommerce.model.Product;
import com.jwt.example.ecommerce.request.CreateProductRequest;
import com.jwt.example.ecommerce.response.ApiResponse;
import com.jwt.example.ecommerce.service.product.iface.ProductServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private ProductServiceInterface productService;

    public AdminProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req) throws ProductException{

        Product createdProduct = productService.createProduct(req);

        return new ResponseEntity<Product>(createdProduct,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException{

        System.out.println("dlete product controller .... ");
        String msg=productService.deleteProduct(productId);
        System.out.println("dlete product controller .... msg "+msg);
        ApiResponse res=new ApiResponse(msg,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct(){

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Product>> recentlyAddedProduct(){

        List<Product> products = productService.recentlyAddedProduct();

        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }


    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProductHandler(@RequestBody Product req,@PathVariable Long productId) throws ProductException{

        Product updatedProduct=productService.updateProduct(productId, req);

        return new ResponseEntity<Product>(updatedProduct,HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws ProductException {

        for(CreateProductRequest product:reqs) {
            productService.createProduct(product);
        }

        ApiResponse res=new ApiResponse("products created successfully",true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
    }


}
