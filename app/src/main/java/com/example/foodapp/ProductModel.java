package com.example.foodapp;

public class ProductModel {

    String productid,productname,productdiscription,productprice,productquantity,productimage,catagoryid;

    public ProductModel(String productid, String productname, String productdiscription, String productprice, String productquantity, String productimage, String catagoryid) {
        this.productid = productid;
        this.productname = productname;
        this.productdiscription = productdiscription;
        this.productprice = productprice;
        this.productquantity = productquantity;
        this.productimage = productimage;
        this.catagoryid = catagoryid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }

    public String getProductdiscription() {
        return productdiscription;
    }

    public void setProductdiscription(String productdiscription) {
        this.productdiscription = productdiscription;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getCatagoryid() {
        return catagoryid;
    }

    public void setCatagoryid(String catagoryid) {
        this.catagoryid = catagoryid;
    }

}
