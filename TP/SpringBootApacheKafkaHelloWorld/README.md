## Kafka
For  redunduncy and scalability with distributed

https://www.youtube.com/watch?v=Ch5VhJzaoaI


## Info
 kafka a été conçu de manière à ce que même avec un seul courtier, il soit toujours en mode distribué, mais avec un facteur de réplication de 1.

 --Kafka utilise Zookeeper pour ce qui suit:

Élire un contrôleur . Le contrôleur est l'un des courtiers et est responsable du maintien de la relation leader / suiveur pour toutes les partitions. Lorsqu'un nœud s'arrête, c'est le contrôleur qui dit aux autres réplicas de devenir des chefs de partition pour remplacer les chefs de partition sur le nœud qui disparaît. Zookeeper est utilisé pour élire un contrôleur, assurez-vous qu'il n'y en a qu'un et en élire un nouveau s'il plante.

Appartenance au cluster - quels courtiers sont vivants et font partie du cluster? ceci est également géré via ZooKeeper.

Configuration des sujets - quels sujets existent, combien de partitions chacun possède, où se trouvent les répliques, qui est le leader préféré, quelles sont les remplacements de configuration définis pour chaque sujet

(0.9.0) - Quotas - combien de données chaque client est-il autorisé à lire et à écrire

(0.9.0) - ACL - qui est autorisé à lire et à écrire dans quel sujet (ancien consommateur de haut niveau) - Quels groupes de consommateurs existent, qui sont leurs membres et quel est le dernier décalage obtenu par chaque groupe pour chaque partition.

## Docs
- https://www.javainuse.com/spring/spring-boot-apache-kafka-hello-world


## Install Kafka  kafka_2.12-0.10.2.1

https://kafka.apache.org/downloads

## Run

-  C:\kafka_2.12-0.10.2.1>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

- C:\kafka_2.12-0.10.2.1>.\bin\windows\kafka-server-start.bat .\config\server.properties

- Next start the Spring Boot Application by running it as a Java Application.

- C:\kafka_2.12-0.10.2.1>.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic java_in_use_topic --from-beginning

- Finally hit the url as follows- http://localhost:8080//javainuse-kafka/producer?message=test