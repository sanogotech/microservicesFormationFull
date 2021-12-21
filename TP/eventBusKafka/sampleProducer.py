
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:9092')
producer.send('bondecommande', b'Hello, World! V2')
producer.send('bondecommande', key=b'message-3', value=b'This is Kafka-Python V3')

print("Bonjour Le Monde")