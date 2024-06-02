package com.shop24.util;

import java.util.stream.Collectors;

import com.shop24.dto.CargoCompanyDTO;
import com.shop24.dto.ClientDTO;
import com.shop24.dto.DrinkDTO;
import com.shop24.dto.OrderDTO;
import com.shop24.model.CargoCompany;
import com.shop24.model.Client;
import com.shop24.model.Drink;
import com.shop24.model.Order;

public class DTOMapper {

    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setClient(toClientDTO(order.getClient()));
        orderDTO.setDrinks(order.getDrinks().stream().map(DTOMapper::toDrinkDTO).collect(Collectors.toList()));
        orderDTO.setPaid(order.isPaid());
        orderDTO.setCompleted(order.isCompleted());
        return orderDTO;
    }

    public static ClientDTO toClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setLatitude(client.getLatitude());
        clientDTO.setLongitude(client.getLongitude());
        return clientDTO;
    }

    public static DrinkDTO toDrinkDTO(Drink drink) {
        DrinkDTO drinkDTO = new DrinkDTO();
        drinkDTO.setDrinkId(drink.getId());
        drinkDTO.setName(drink.getName());
        drinkDTO.setDescription(drink.getDescription());
        drinkDTO.setPrice(drink.getPrice());
        drinkDTO.setQuantity(drink.getQuantity());
        return drinkDTO;
    }
    
    public static CargoCompanyDTO toCargoCompanyDTO(CargoCompany cargoCompany) {
        CargoCompanyDTO cargoCompanyDTO = new CargoCompanyDTO();
        cargoCompanyDTO.setCargoCompanyId(cargoCompany.getId());
        cargoCompanyDTO.setName(cargoCompany.getName());
        cargoCompanyDTO.setAddress(cargoCompany.getAddress());
        cargoCompanyDTO.setLatitude(cargoCompany.getLatitude());
        cargoCompanyDTO.setLongitude(cargoCompany.getLongitude());
        return cargoCompanyDTO;
    }
}
