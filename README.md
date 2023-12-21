# szs-whatap-demo
# 우선순위
1. kafka listen
2. reactive kafka
3. async completableFuture
4. 나머지는 되는 대로

# 실행방법
# 아래 명령어를 실행하면 된다.
docker-compose -f demo-kafka.yml up -d
docker-compose -f demo-mysql.yml up -d

# sql init


<< 에이전트 설정 >>
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