
# szs-whatap-demo

## 우선순위
1. kafka listen
2. reactive kafka
3. async completableFuture
4. 나머지는 되는 대로

## 실행방법
아래 명령어를 실행하면 됩니다.

```bash
1. docker-compose -f demo-kafka.yml up -d
2. docker-compose -f demo-mysql.yml up -d
```

## SQL 초기 설정

```sql
create table async_kafka
(
    id bigint auto_increment primary key,
    create_time datetime not null,
    update_time datetime null,
    status varchar(50)
);

create table reactor
(
    id bigint auto_increment primary key,
    create_time datetime not null,
    update_time datetime null,
    status varchar(50)
);

create table async
(
    id bigint auto_increment primary key,
    create_time datetime not null,
    update_time datetime null,
    status varchar(50)
);

create table rxjava
(
    id bigint auto_increment primary key,
    create_time datetime not null,
    update_time datetime null,
    status varchar(50)
);

create table kafka
(
    id bigint auto_increment primary key,
    create_time datetime not null,
    update_time datetime null,
    status varchar(50)
);
```

## 에이전트 설정

`whatap.conf` 파일 설정 예시:

```conf
[whatap.conf]
------------------------------------------------------------
license=
whatap.server.host=

# topology ####
actx_meter_enabled=true
httpc_host_meter_enabled=true
sql_dbc_meter_enabled=true
tx_caller_meter_enabled=true
###############

weaving=spring-boot-3.0
mtrace_rate=100
mtrace_auto_enabled=false
trace_basetime=0
trace_http_header_enabled=true
trace_kafka_header_enabled=true
trace_sql_param_enabled=true
#hook_completablefuture_patterns=whatap.v1.W.*
hook_method_patterns=org.apache.kafka.clients.producer.KafkaProducer.*
hook_service_patterns=org.apache.kafka.clients.producer.KafkaProducer.*
------------------------------------------------------------

[whatap.conf]
------------------------------------------------------------
hook_completablefuture_patterns=whatap.v1.W.*
------------------------------------------------------------
```

`com.whatap.demo.service.AsyncService.java` 파일 예시:

```java
[com.whatap.demo.service.AsyncService.java]
------------------------------------------------------------
...
import whatap.v1.W;

public class AsyncService {
    @Async
    public CompletableFuture<String> processAsync() {
        // W.trace() 추가 (사용자 코드를 dummy가 감싸는 형태)
        return CompletableFuture.supplyAsync(W.trace(() -> {
            try {
                asyncRepository.save(AsyncEntity.builder()
                    .status("async")
                    .build());
                Thread.sleep(5000); // 5초 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Completed";
        }));
    }
}
------------------------------------------------------------
```
