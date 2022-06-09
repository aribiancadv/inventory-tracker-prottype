package com.javatpoint.service;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatpoint.model.InvItems;
import com.javatpoint.repository.ItemsRepository;

import org.hibernate.Filter;
import org.hibernate.Session;

//defining the business logic
@Service
public class ItemsService 
{
    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private EntityManager entityManager;

    //getting all items record by using the method findaAll() of CrudRepository
    public List<InvItems> getAllItems() 
    {
        List<InvItems> items = new ArrayList<InvItems>();
        itemsRepository.findAll().forEach(items1 -> items.add(items1));
        return items;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public InvItems getItemsById(int id) 
    {
        return itemsRepository.findById(id).get();
    }

    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(InvItems items) 
    {
        itemsRepository.save(items);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {        
        itemsRepository.deleteById(id);
    }

    //updating a record
    public void update(InvItems items, int itemid) 
    {
        itemsRepository.save(items);
    }

    public InvItems create(InvItems invitem)
    {
        return itemsRepository.save(invitem);
    }

    public Iterable<InvItems> findAll(boolean isDeleted)
    {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedItemFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<InvItems> invitems = itemsRepository.findAll();
        session.disableFilter("deletedItemFilter");
        return invitems;
    }
}