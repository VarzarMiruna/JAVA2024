package home.repository;

import home.entities.PublishingHouse;

import javax.persistence.EntityManager;

public class PublishingHouseRepository extends AbstractRepository<PublishingHouse> {
    public PublishingHouseRepository(){
        name="PublishingHouse";
    }
}
