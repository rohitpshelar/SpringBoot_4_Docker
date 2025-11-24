package com.rt.order;

// Import Avro-generated class representing our event structure
// Think of this as the “template” for an order ticket.

//import com.example.avro.OrderCreated;
//import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


/**
 * ==============================
 * 🚀 REST Controller Layer
 * ==============================
 *
 * This exposes an HTTP endpoint to create new orders.
 * When a request comes in, it builds an `OrderCreated` event
 * and sends it to Kafka.
 */
@RestController
@RequestMapping("/orders") // base URL path for all endpoints in this controller
public class OrderController {

    // KafkaTemplate = ready-to-use producer helper
    // It's like a "courier service" that knows how to send events to Kafka.
//    private final KafkaTemplate<String, Object> template;
//
//    // The topic name is loaded from application.yml
//    // (e.g., app.topic.order = order.v1)
//    @Value("${app.topic.order}")
//    private String orderTopic;
//
//    // Constructor-based dependency injection
//    // Spring will automatically inject the KafkaTemplate bean from KafkaProducerConfig
//    public OrderController(KafkaTemplate<String, Object> template) {
//        this.template = template;
//    }

    /**
     * =========================================
     * ✉️ POST /orders → publish OrderCreated event
     * =========================================
     *
     * This endpoint receives JSON input like:
     *   {
     *      "orderId": "o-101",
     *      "symbol": "AAPL",
     *      "qty": 10,
     *      "price": 175.5
     *   }
     *
     * Steps:
     *  1️⃣ Parse the request into an OrderRequest object.
     *  2️⃣ Build an Avro event (OrderCreated).
     *  3️⃣ Send it to Kafka via KafkaTemplate.
     */
//    @PostMapping
//    public String create(@RequestBody OrderRequest req) {
//
//        // 1️⃣ Build the Avro event from request
//        // This event matches a schema defined in src/main/avro/OrderCreated.avsc
//        // It's like filling out a “standardized order form”.
//        OrderCreated event = OrderCreated.newBuilder()
//                .setOrderId(req.getOrderId())
//                .setSymbol(req.getSymbol())
//                .setQty(req.getQty())
//                .setPrice(req.getPrice())
//                .build();
//
//        // 2️⃣ Publish to Kafka topic
//        // We create a ProducerRecord (like an addressed envelope):
//        //   - topic = which mailbox to drop it in
//        //   - key   = event.getOrderId() (used for partitioning)
//        //   - value = the actual Avro event (serialized and sent)
//        template.send(new ProducerRecord<>(orderTopic, event.getOrderId(), event));
//
//        // 3️⃣ Return simple confirmation to API client
//        return "Order accepted: " + req.getOrderId();
//    }

    @GetMapping
    public void welcome(){
        System.out.println("hi");
    }
}
