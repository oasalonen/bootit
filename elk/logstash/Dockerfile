FROM docker.elastic.co/logstash/logstash-oss:6.1.2

RUN rm -f /usr/share/logstash/pipeline/logstash.conf
COPY pipeline.conf /usr/share/logstash/pipeline/
COPY logstash.conf /usr/share/logstash/config/