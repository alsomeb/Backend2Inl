package com.backend2.item.service.services;


import com.backend2.item.service.domains.Item;
import com.backend2.item.service.domains.ItemEntity;
import com.backend2.item.service.utils.DeleteResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface ItemService {

    Item createNewItemEntity(Item item, MultipartFile file) throws IOException;

    Item stringItemToJson(String itemString) throws JsonProcessingException;

    List<Item> findAllItems();

    Optional<byte[]> findItemImageById(Long id);

    Optional<Item> findItemEntityById(Long id);

    ItemEntity itemToItemEntity(Item item, MultipartFile file) throws IOException;

    Item itemEntityToItem(ItemEntity itemEntity);

    String itemURIBuilder(Long id);

    DeleteResponse deleteItemEntityById(Long id);

    Item updateItemEntity(Item item, MultipartFile file, Long id) throws IOException;

    Item updatePriceOfItemEntity(Long price, Long id);

    Item updateNameOfItemEntity(String name, Long id);

    Item updateBalanceOfItemEntity(Long price, Long id);
}
