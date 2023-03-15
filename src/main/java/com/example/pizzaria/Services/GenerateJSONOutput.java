package com.example.pizzaria.Services;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzaria.JSON.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GenerateJSONOutput {

    @Autowired
    private GenerateJSONObject jsonGenerator;

    public GenerateJSONOutput() {
    };

    public String generateJSONString(int pedidoId) {

        JSONPedido pedidoJSON = jsonGenerator.generateJSONPedido(pedidoId);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonString = objectMapper.writeValueAsString(pedidoJSON);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public void generateJSONFile(int pedidoId, String fileName) {

        JSONPedido pedidoJSON = jsonGenerator.generateJSONPedido(pedidoId);
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(String.format("%s.json", fileName));

        try {
            objectMapper.writeValue(file, pedidoJSON);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
