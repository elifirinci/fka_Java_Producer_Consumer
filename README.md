
# Kafka Java Producer-Consumer Örneği (Serialized Java Nesneleri ile)

Bu proje, Apache Kafka kullanarak Java nesnelerinin (örneğin `Student` sınıfı) **Producer** tarafından Kafka topic'ine serileştirilip gönderilmesini ve **Consumer** tarafından alınarak nesne olarak deserialize edilip konsola yazdırılmasını göstermektedir.



## Proje Özeti

- **ProducerApp**: `Student` sınıfından nesneler oluşturur ve bunları Kafka'ya byte dizisi olarak serileştirerek gönderir.
- **ConsumerApp**: Kafka'dan gelen byte dizilerini alır, `Student` nesnesine deserialize eder ve konsola yazdırır.
- Apache Kafka işlemleri, **Confluent Cloud** üzerinde yönetilen bir Kafka cluster ile gerçekleştirilir.
- Proje bağımlılıkları Maven ile yönetilmektedir.

---

## Kullanılan Teknolojiler

- Java 24
- Apache Kafka Clients 4.0.0
- Confluent Cloud (Managed Kafka Cluster)
- Maven
- SLF4J (Logging)

---

## Kurulum ve Çalıştırma

### 1. Kafka ve Confluent Cloud Ayarları

- Confluent Cloud üzerinde hesap açın.
- Kafka cluster oluşturun ve API Key & Secret bilgilerini alın.
- `student-topic` adında bir Kafka topic oluşturun.

### 2. Maven Bağımlılıkları

```xml
<dependencies>
  <dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>4.0.0</version>
  </dependency>
  <dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.36</version>
  </dependency>
</dependencies>
````

---

## Producer ve Consumer Kodlarında Confluent Cloud Bilgilerini Güncelleyin

---

## Çalışma Mantığı

* **ProducerApp**, `Student` nesnesini `ByteArraySerializer` kullanarak Kafka'ya gönderir.
* **ConsumerApp**, Kafka'dan gelen byte dizisini `ByteArrayDeserializer` ile alır ve `ObjectInputStream` ile orijinal `Student` nesnesine dönüştürür.
* Alınan `Student` nesnesinin içeriği konsola yazdırılır.

---
![Ekran görüntüsü 2025-07-06 150243](https://github.com/user-attachments/assets/c957e4a5-8709-4933-b8cb-e345ad2b40b9)


![Ekran görüntüsü 2025-07-06 150324](https://github.com/user-attachments/assets/cda45098-7293-4c5b-93e0-f258e355e4d3)
