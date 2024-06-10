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
import com.shop24.model.Receipt;

public class DTOMapper {

    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setClient(toClientDTO(order.getClient()));
        orderDTO.setDrinks(order.getDrinks().stream().map(DTOMapper::toDrinkDTO).collect(Collectors.toList()));
        orderDTO.setPaid(order.isPaid());
        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        orderDTO.setCompleted(order.isCompleted());
        
        // Map cargo company information
        if (order.getCargoCompany() != null) {
            CargoCompanyDTO cargoCompanyDTO = new CargoCompanyDTO();
            cargoCompanyDTO.setCargoCompanyId(order.getCargoCompany().getId());
            cargoCompanyDTO.setName(order.getCargoCompany().getName());
            cargoCompanyDTO.setAddress(order.getCargoCompany().getAddress());
            cargoCompanyDTO.setLatitude(order.getCargoCompany().getLatitude());
            cargoCompanyDTO.setLongitude(order.getCargoCompany().getLongitude());
            orderDTO.setCargoCompany(cargoCompanyDTO);
        }
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
    
    public static OrderDTO toOrderDTOWithCargoCompany(Order order) {
        OrderDTO orderDTO = toOrderDTO(order);
        if (order.getCargoCompany() != null) {
            orderDTO.setCargoCompany(toCargoCompanyDTO(order.getCargoCompany()));
        }
        return orderDTO;
    }
    
//    public static ReceiptDTO toReceiptDTO(Receipt receipt) {
//        ReceiptDTO receiptDTO = new ReceiptDTO();
//        receiptDTO.setId(receipt.getId());
//        receiptDTO.setOrderId(receipt.getOrder().getId());
//        receiptDTO.setClientId(receipt.getClient().getId());
//        receiptDTO.setClientName(receipt.getClient().getName());
//        receiptDTO.setCargoCompanyId(receipt.getCargoCompany().getId());
//        receiptDTO.setCargoCompanyName(receipt.getCargoCompany().getName());
//        receiptDTO.setDrinks(receipt.getDrinks().stream().map(DTOMapper::toDrinkDTO).collect(Collectors.toList()));
//        receiptDTO.setIssuedAt(receipt.getIssuedAt());
//        receiptDTO.setTotalAmount(receipt.getTotalAmount());
//        return receiptDTO;
//    }

}
