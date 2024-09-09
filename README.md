# ud-sb-micro-hex-arch
Jay's proj repo for Microservices with SpringBoot, Docker, Kubernetes training (by A. Gelenler - udemy)

- initial repo commit

proj: fos (food ordering system)
- created java packages jayslabs.fos.order.domain.* (entity, vo, exception, event); StreetAddress vo; Started on Order Aggregate Root Class
- created VO: CustomerId, ProductId, RestaurantId, OrderStatus; Interface: DomainEvent
- created common-domain module, entities:BaseEntity, AggregateRoot; VOs: BaseId, OrderId, Money
- updated pom
- added parent and module dependency structure; generate degraph, graphviz files
- created project modules: 
  - order-service
    - order-domain 
      - order-domain-core
      - order-application-service
    - order-application
    - order-dataaccess
    - order-messaging
    - order-container
- created main fos project plus module hierarchy

proj: infra
- added docker-compose.yml: zookeeper, kafka, postgres, pgadmin4
- initial commit