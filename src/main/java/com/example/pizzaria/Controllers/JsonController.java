package com.example.pizzaria.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.pizzaria.JSON.JSONPedido;
import com.example.pizzaria.Services.GenerateJSONObject;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@ResponseBody
public class JsonController {
    
    @RequestMapping("/see_json")
    public JSONPedido seeJson(HttpServletRequest request, @RequestParam String pedido_id) {
        GenerateJSONObject generator = new GenerateJSONObject();
        int pedidoId = Integer.parseInt(pedido_id);

        JSONPedido jsonPedido = generator.generateJSONPedido(pedidoId, new String[] { "pizza", "bebida", "sobremesa" });
        return jsonPedido;
    }
}
