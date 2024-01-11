package com.pernudo.FP_Practice.chapter14;

import com.github.javafaker.Faker;
import com.pernudo.FP_Practice.pojos.Product;
import com.pernudo.FP_Practice.utils.ProductJavaFaker;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.stream.Stream;

public class MainNumberResult {

    public static void main(String[] args) throws Exception {

        var productFaker = new Faker(new Locale("es", "ES")).commerce();
        var lstProducts = Stream.generate(() -> ProductJavaFaker.createProduct(productFaker))
                .limit(200).toList();

        var decimalformat = new DecimalFormat("#.##");

        var averagePrice = lstProducts.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElseThrow(IllegalStateException::new);
        var sumPrice = lstProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("La media de precios de los productos es " + decimalformat.format(averagePrice));
        System.out.println("La suma de precios de los productos es " + decimalformat.format(sumPrice));
    }

}
