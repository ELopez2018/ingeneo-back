/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistics.config;

/**
 *
 * @author estar
 */
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfigurationOrigin {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public List<String> getListAllowedOrigins(){
        List<String> origins= new ArrayList<>();
        origins.add("http://localhost:4200");
        return origins;
    }
}
